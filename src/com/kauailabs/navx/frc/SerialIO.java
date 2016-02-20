package com.kauailabs.navx.frc;

import com.kauailabs.navx.AHRSProtocol;
import com.kauailabs.navx.IMUProtocol;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;


class SerialIO
        implements IIOProvider {
    final boolean debug = false;
    SerialPort.Port serial_port_id;
    SerialPort serial_port;
    int byte_count;
    int update_count;
    IIOCompleteNotification notify_sink;
    IIOCompleteNotification.BoardState board_state;
    IBoardCapabilities board_capabilities;
    double last_valid_packet_time;
    int x = 0; //lols
    private byte next_integration_control_action;
    private boolean signal_transmit_integration_control;
    private boolean signal_retransmit_stream_config;
    private boolean stop;
    private byte update_type;
    private byte update_rate_hz;
    private IMUProtocol.YPRUpdate ypr_update_data;
    private IMUProtocol.GyroUpdate gyro_update_data;
    private AHRSProtocol.AHRSUpdate ahrs_update_data;
    private AHRSProtocol.AHRSPosUpdate ahrspos_update_data;
    private AHRSProtocol.BoardID board_id;

    public SerialIO(SerialPort.Port port_id, byte update_rate_hz, boolean processed_data, IIOCompleteNotification notify_sink, IBoardCapabilities board_capabilities) {
        this.serial_port_id = port_id;
        this.ypr_update_data = new IMUProtocol.YPRUpdate();
        this.gyro_update_data = new IMUProtocol.GyroUpdate();
        this.ahrs_update_data = new AHRSProtocol.AHRSUpdate();
        this.ahrspos_update_data = new AHRSProtocol.AHRSPosUpdate();
        this.board_id = new AHRSProtocol.BoardID();
        this.board_state = new IIOCompleteNotification.BoardState();
        this.notify_sink = notify_sink;
        this.board_capabilities = board_capabilities;
        this.serial_port = getMaybeCreateSerialPort();
        this.update_rate_hz = update_rate_hz;
        if (processed_data) {
            this.update_type = 112;
        } else {
            this.update_type = 103;
        }
    }

    protected SerialPort resetSerialPort() {
        if (this.serial_port != null) {
            try {
                this.serial_port.free();
            } catch (Exception localException) {
            }

            this.serial_port = null;
        }
        this.serial_port = getMaybeCreateSerialPort();
        return this.serial_port;
    }

    protected SerialPort getMaybeCreateSerialPort() {
        if (this.serial_port == null) {
            try {
                this.serial_port = new SerialPort(57600, this.serial_port_id);
                this.serial_port.setReadBufferSize(256);
                this.serial_port.setTimeout(1.0D);
                this.serial_port.enableTermination('\n');
                this.serial_port.reset();
            } catch (Exception ex) {
                this.serial_port = null;
            }
        }
        return this.serial_port;
    }

    protected void enqueueIntegrationControlMessage(byte action) {
        this.next_integration_control_action = action;
        this.signal_transmit_integration_control = true;
    }

    protected void dispatchStreamResponse(IMUProtocol.StreamResponse response) {
        this.board_state.cal_status = ((byte) (response.flags & 0x3));
        this.board_state.capability_flags = ((short) (response.flags & 0xFFFFFFFC));
        this.board_state.op_status = 4;
        this.board_state.selftest_status = 7;
        this.board_state.accel_fsr_g = response.accel_fsr_g;
        this.board_state.gyro_fsr_dps = response.gyro_fsr_dps;
        this.board_state.update_rate_hz = ((byte) response.update_rate_hz);
        this.notify_sink.setBoardState(this.board_state);


        if ((this.update_type == 112) &&
                (!this.board_capabilities.isDisplacementSupported())) {
            this.update_type = 97;
            this.signal_retransmit_stream_config = true;
        }
    }


    protected int decodePacketHandler(byte[] received_data, int offset, int bytes_remaining) {
        int packet_length;

        if ((packet_length = IMUProtocol.decodeYPRUpdate(received_data, offset, bytes_remaining, this.ypr_update_data)) > 0) {
            this.notify_sink.setYawPitchRoll(this.ypr_update_data);
        } else if ((packet_length = AHRSProtocol.decodeAHRSPosUpdate(received_data, offset, bytes_remaining, this.ahrspos_update_data)) > 0) {
            this.notify_sink.setAHRSPosData(this.ahrspos_update_data);
        } else if ((packet_length = AHRSProtocol.decodeAHRSUpdate(received_data, offset, bytes_remaining, this.ahrs_update_data)) > 0) {
            this.notify_sink.setAHRSData(this.ahrs_update_data);
        } else if ((packet_length = IMUProtocol.decodeGyroUpdate(received_data, offset, bytes_remaining, this.gyro_update_data)) > 0) {
            this.notify_sink.setRawData(this.gyro_update_data);
        } else if ((packet_length = AHRSProtocol.decodeBoardIDGetResponse(received_data, offset, bytes_remaining, this.board_id)) > 0) {
            this.notify_sink.setBoardID(this.board_id);
        } else {
            packet_length = 0;
        }
        return packet_length;
    }

    public void run() {
        this.stop = false;
        boolean stream_response_received = false;
        double last_stream_command_sent_timestamp = 0.0D;
        double last_data_received_timestamp = 0.0D;
        double last_second_start_time = 0.0D;

        int partial_binary_packet_count = 0;
        int stream_response_receive_count = 0;
        int timeout_count = 0;
        int discarded_bytes_count = 0;
        int port_reset_count = 0;
        int updates_in_last_second = 0;
        int integration_response_receive_count = 0;
        try {
            this.serial_port.setReadBufferSize(256);
            this.serial_port.setTimeout(1.0D);
            this.serial_port.enableTermination('\n');
            this.serial_port.flush();
            this.serial_port.reset();
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }

        byte[] stream_command = new byte['c'];
        byte[] integration_control_command = new byte['c'];
        IMUProtocol.StreamResponse response = new IMUProtocol.StreamResponse();
        AHRSProtocol.IntegrationControl integration_control = new AHRSProtocol.IntegrationControl();
        AHRSProtocol.IntegrationControl integration_control_response = new AHRSProtocol.IntegrationControl();

        int cmd_packet_length = IMUProtocol.encodeStreamCommand(stream_command, this.update_type, this.update_rate_hz);
        try {
            this.serial_port.reset();
            this.serial_port.write(stream_command, cmd_packet_length);
            cmd_packet_length = AHRSProtocol.encodeDataGetRequest(stream_command, (byte) 2, (byte) 0);
            this.serial_port.write(stream_command, cmd_packet_length);
            this.serial_port.flush();
            port_reset_count++;


            last_stream_command_sent_timestamp = Timer.getFPGATimestamp();
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }

        int remainder_bytes = 0;
        byte[] remainder_data = null;

        while (!this.stop) {

            try {

                if (this.signal_transmit_integration_control) {
                    integration_control.action = this.next_integration_control_action;
                    this.signal_transmit_integration_control = false;
                    this.next_integration_control_action = 0;
                    cmd_packet_length = AHRSProtocol.encodeIntegrationControlCmd(integration_control_command, integration_control);
                    try {
                        this.serial_port.write(integration_control_command, cmd_packet_length);
                    } catch (RuntimeException ex2) {
                        ex2.printStackTrace();
                    }
                }

                if ((!this.stop) && (remainder_bytes == 0) && (this.serial_port.getBytesReceived() < 1)) {
                    Timer.delay(1.0D / this.update_rate_hz);
                }

                int packets_received = 0;
                byte[] received_data = this.serial_port.read(256);
                int bytes_read = received_data.length;
                this.byte_count += bytes_read;


                if (remainder_bytes > 0) {
                    byte[] resized_array = new byte[remainder_bytes + bytes_read];
                    System.arraycopy(remainder_data, 0, resized_array, 0, remainder_bytes);
                    System.arraycopy(received_data, 0, resized_array, remainder_bytes, bytes_read);
                    received_data = resized_array;
                    bytes_read += remainder_bytes;
                    remainder_bytes = 0;
                    remainder_data = null;
                }

                if (bytes_read > 0) {
                    last_data_received_timestamp = Timer.getFPGATimestamp();
                    int i = 0;

                    while (i < bytes_read) {


                        int bytes_remaining = bytes_read - i;

                        if (received_data[i] != 33) {
                            i++;
                            discarded_bytes_count++;

                        } else {

                            if ((bytes_remaining > 2) && (received_data[(i + 1)] == 35)) {

                                byte total_expected_binary_data_bytes = received_data[(i + 2)];
                                total_expected_binary_data_bytes = (byte) (total_expected_binary_data_bytes + 2);
                                while (bytes_remaining < total_expected_binary_data_bytes) {


                                    byte[] additional_received_data = this.serial_port.read(256);
                                    this.byte_count += additional_received_data.length;
                                    bytes_remaining += additional_received_data.length;


                                    byte[] c = new byte[received_data.length + additional_received_data.length];
                                    if (c.length > 0) {
                                        System.arraycopy(received_data, 0, c, 0, received_data.length);
                                        System.arraycopy(additional_received_data, 0, c, received_data.length, additional_received_data.length);
                                        received_data = c;
                                    } else {
                                        i++;
                                        bytes_remaining--;
                                        partial_binary_packet_count++;
                                    }
                                }
                            }


                            int packet_length = decodePacketHandler(received_data, i, bytes_remaining);
                            if (packet_length > 0) {
                                packets_received++;
                                this.update_count += 1;
                                this.last_valid_packet_time = Timer.getFPGATimestamp();
                                updates_in_last_second++;
                                if (this.last_valid_packet_time - last_second_start_time > 1.0D) {


                                    updates_in_last_second = 0;
                                    last_second_start_time = this.last_valid_packet_time;
                                }
                                i += packet_length;
                            } else {
                                packet_length = IMUProtocol.decodeStreamResponse(received_data, i, bytes_remaining, response);
                                if (packet_length > 0) {
                                    packets_received++;
                                    dispatchStreamResponse(response);
                                    stream_response_received = true;
                                    i += packet_length;
                                    stream_response_receive_count++;

                                } else {

                                    packet_length = AHRSProtocol.decodeIntegrationControlResponse(received_data, i, bytes_remaining, integration_control_response);

                                    if (packet_length > 0) {
                                        integration_response_receive_count++;


                                        i += packet_length;

                                    } else {
                                        boolean next_packet_start_found = false;

                                        for (int x = 0; x < bytes_remaining; x++) {
                                            if (received_data[(i + x)] != 33) {
                                                x++;
                                            } else {
                                                i += x;
                                                bytes_remaining -= x;
                                                if (x == 0) break;
                                                next_packet_start_found = true;
                                                break;
                                            }
                                        }


                                        boolean discard_remainder = false;
                                        if ((!next_packet_start_found) && (x == bytes_remaining)) {
                                            discard_remainder = true;
                                        }
                                        boolean partial_packet = false;
                                        if (discard_remainder) {
                                            i = bytes_remaining;
                                        } else if (!next_packet_start_found) {

                                            if ((bytes_remaining > 2) && (received_data[(i + 1)] == 35)) {

                                                int pkt_len = received_data[(i + 2)];
                                                pkt_len += 2;
                                                if (bytes_remaining >= pkt_len) {
                                                    bytes_remaining -= pkt_len;
                                                    i += pkt_len;
                                                    discarded_bytes_count += pkt_len;


                                                } else {

                                                    partial_packet = true;
                                                }

                                            } else {
                                                for (int x = 0; x < bytes_remaining; x++) {
                                                    if (received_data[(i + x)] == 13) {
                                                        i += x + 1;
                                                        bytes_remaining -= x + 1;
                                                        discarded_bytes_count += x + 1;
                                                        if ((bytes_remaining <= 0) || (received_data[i] != 10)) break;
                                                        bytes_remaining--;
                                                        i++;
                                                        discarded_bytes_count++;
                                                        break;
                                                    }


                                                    if (received_data[(i + x)] == 33) {
                                                        if (x > 0) {
                                                            i += x;
                                                            bytes_remaining -= x;
                                                            discarded_bytes_count += x;
                                                            break;
                                                        }


                                                        if (bytes_remaining < 53) {
                                                            partial_packet = true;
                                                            break;
                                                        }
                                                        i++;
                                                        bytes_remaining--;

                                                        break;
                                                    }
                                                }

                                                if (x == bytes_remaining) {
                                                    partial_packet = true;
                                                }
                                            }
                                        }

                                        if (partial_packet) {
                                            remainder_data = new byte[bytes_remaining];
                                            System.arraycopy(received_data, i, remainder_data, 0, bytes_remaining);
                                            remainder_bytes = bytes_remaining;
                                            i = bytes_read;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if ((packets_received == 0) && (bytes_read == 256)) {


                        this.serial_port.flush();
                        this.serial_port.reset();
                        port_reset_count++;
                    }


                    boolean retransmit_stream_config = false;
                    if (this.signal_retransmit_stream_config) {
                        retransmit_stream_config = true;
                        this.signal_retransmit_stream_config = false;
                    }


                    if ((retransmit_stream_config) || ((!stream_response_received) &&
                            (Timer.getFPGATimestamp() - last_stream_command_sent_timestamp > 3.0D))) {
                        cmd_packet_length = IMUProtocol.encodeStreamCommand(stream_command, this.update_type, this.update_rate_hz);
                        try {
                            resetSerialPort();
                            last_stream_command_sent_timestamp = Timer.getFPGATimestamp();
                            this.serial_port.write(stream_command, cmd_packet_length);
                            cmd_packet_length = AHRSProtocol.encodeDataGetRequest(stream_command, (byte) 2, (byte) 0);
                            this.serial_port.write(stream_command, cmd_packet_length);
                            this.serial_port.flush();
                        } catch (RuntimeException ex2) {
                            ex2.printStackTrace();
                        }


                    } else if ((stream_response_received) && (this.serial_port.getBytesReceived() == 0)) {
                        Timer.delay(1.0D / this.update_rate_hz);
                    }


                    if (Timer.getFPGATimestamp() - this.last_valid_packet_time > 1.0D) {
                        last_stream_command_sent_timestamp = 0.0D;
                        stream_response_received = false;
                    }

                } else if (Timer.getFPGATimestamp() - last_data_received_timestamp > 1.0D) {
                    resetSerialPort();
                }
            } catch (RuntimeException ex) {
                stream_response_received = false;
                timeout_count++;


                ex.printStackTrace();
                resetSerialPort();
            }
        }
    }


    public boolean isConnected() {
        double time_since_last_update = Timer.getFPGATimestamp() - this.last_valid_packet_time;
        return time_since_last_update <= 1.0D;
    }


    public double getByteCount() {
        return this.byte_count;
    }


    public double getUpdateCount() {
        return this.update_count;
    }

    public void setUpdateRateHz(byte update_rate) {
        this.update_rate_hz = update_rate;
    }

    public void zeroYaw() {
        enqueueIntegrationControlMessage(Byte.MIN_VALUE);
    }

    public void zeroDisplacement() {
        enqueueIntegrationControlMessage((byte) 56);
    }


    public void stop() {
        this.stop = true;
    }
}


