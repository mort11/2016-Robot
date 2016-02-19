package com.kauailabs.navx.frc;

import com.kauailabs.navx.AHRSProtocol;
import com.kauailabs.navx.IMUProtocol;
import edu.wpi.first.wpilibj.Timer;


class RegisterIO
        implements IIOProvider {
    private final double IO_TIMEOUT_SECONDS = 1.0D;
    IRegisterIO io_provider;
    byte update_rate_hz;
    boolean stop;
    IMUProtocol.GyroUpdate raw_data_update;
    AHRSProtocol.AHRSUpdate ahrs_update;
    AHRSProtocol.AHRSPosUpdate ahrspos_update;
    IIOCompleteNotification notify_sink;
    IIOCompleteNotification.BoardState board_state;
    AHRSProtocol.BoardID board_id;
    IBoardCapabilities board_capabilities;
    double last_update_time;
    int byte_count;
    int update_count;

    public RegisterIO(IRegisterIO io_provider, byte update_rate_hz, IIOCompleteNotification notify_sink, IBoardCapabilities board_capabilities) {
        this.io_provider = io_provider;
        this.update_rate_hz = update_rate_hz;
        this.board_capabilities = board_capabilities;
        this.notify_sink = notify_sink;
        this.raw_data_update = new IMUProtocol.GyroUpdate();
        this.ahrs_update = new AHRSProtocol.AHRSUpdate();
        this.ahrspos_update = new AHRSProtocol.AHRSPosUpdate();
        this.board_state = new IIOCompleteNotification.BoardState();
        this.board_id = new AHRSProtocol.BoardID();
    }

    public void stop() {
        this.stop = true;
    }

    public void run() {
        this.io_provider.init();


        setUpdateRateHz(this.update_rate_hz);
        getConfiguration();


        while (!this.stop) {
            if (this.board_state.update_rate_hz != this.update_rate_hz) {
                setUpdateRateHz(this.update_rate_hz);
            }
            getCurrentData();
            Timer.delay(1.0D / this.update_rate_hz);
        }
    }

    private boolean getConfiguration() {
        boolean success = false;
        int retry_count = 0;
        while ((retry_count < 3) && (!success)) {
            byte[] config = new byte[18];
            if (this.io_provider.read((byte) 0, config)) {
                this.board_id.hw_rev = config[1];
                this.board_id.fw_ver_major = config[2];
                this.board_id.fw_ver_minor = config[3];
                this.board_id.type = config[0];
                this.notify_sink.setBoardID(this.board_id);

                this.board_state.cal_status = config[9];
                this.board_state.op_status = config[8];
                this.board_state.selftest_status = config[10];
                this.board_state.sensor_status = AHRSProtocol.decodeBinaryUint16(config, 16);
                this.board_state.gyro_fsr_dps = AHRSProtocol.decodeBinaryUint16(config, 6);
                this.board_state.accel_fsr_g = ((short) config[5]);
                this.board_state.update_rate_hz = config[4];
                this.board_state.capability_flags = AHRSProtocol.decodeBinaryUint16(config, 11);
                this.notify_sink.setBoardState(this.board_state);
                success = true;
            } else {
                success = false;
                Timer.delay(0.05D);
            }
            retry_count++;
        }
        return success;
    }

    private void getCurrentData() {
        byte first_address = 4;
        boolean displacement_registers = this.board_capabilities.isDisplacementSupported();

        byte[] curr_data;
        if (displacement_registers) {
            curr_data = new byte[112 - first_address];
        } else {
            curr_data = new byte[86 - first_address];
        }


        if (this.io_provider.read(first_address, curr_data)) {
            long timestamp_low = AHRSProtocol.decodeBinaryUint16(curr_data, 18 - first_address);
            long timestamp_high = AHRSProtocol.decodeBinaryUint16(curr_data, 20 - first_address);
            long sensor_timestamp = (timestamp_high << 16) + timestamp_low;
            this.ahrspos_update.op_status = curr_data[(8 - first_address)];
            this.ahrspos_update.selftest_status = curr_data[(10 - first_address)];
            this.ahrspos_update.cal_status = curr_data[9];
            this.ahrspos_update.sensor_status = curr_data[(16 - first_address)];
            this.ahrspos_update.yaw = AHRSProtocol.decodeProtocolSignedHundredthsFloat(curr_data, 22 - first_address);
            this.ahrspos_update.pitch = AHRSProtocol.decodeProtocolSignedHundredthsFloat(curr_data, 26 - first_address);
            this.ahrspos_update.roll = AHRSProtocol.decodeProtocolSignedHundredthsFloat(curr_data, 24 - first_address);
            this.ahrspos_update.compass_heading = AHRSProtocol.decodeProtocolUnsignedHundredthsFloat(curr_data, 28 - first_address);
            this.ahrspos_update.mpu_temp = AHRSProtocol.decodeProtocolSignedHundredthsFloat(curr_data, 50 - first_address);
            this.ahrspos_update.linear_accel_x = AHRSProtocol.decodeProtocolSignedThousandthsFloat(curr_data, 36 - first_address);
            this.ahrspos_update.linear_accel_y = AHRSProtocol.decodeProtocolSignedThousandthsFloat(curr_data, 38 - first_address);
            this.ahrspos_update.linear_accel_z = AHRSProtocol.decodeProtocolSignedThousandthsFloat(curr_data, 40 - first_address);
            this.ahrspos_update.altitude = AHRSProtocol.decodeProtocol1616Float(curr_data, 34 - first_address);
            this.ahrspos_update.barometric_pressure = AHRSProtocol.decodeProtocol1616Float(curr_data, 72 - first_address);
            this.ahrspos_update.fused_heading = AHRSProtocol.decodeProtocolUnsignedHundredthsFloat(curr_data, 30 - first_address);
            this.ahrspos_update.quat_w = AHRSProtocol.decodeBinaryInt16(curr_data, 42 - first_address);
            this.ahrspos_update.quat_x = AHRSProtocol.decodeBinaryInt16(curr_data, 44 - first_address);
            this.ahrspos_update.quat_y = AHRSProtocol.decodeBinaryInt16(curr_data, 46 - first_address);
            this.ahrspos_update.quat_z = AHRSProtocol.decodeBinaryInt16(curr_data, 48 - first_address);
            if (displacement_registers) {
                this.ahrspos_update.vel_x = AHRSProtocol.decodeProtocol1616Float(curr_data, 88 - first_address);
                this.ahrspos_update.vel_y = AHRSProtocol.decodeProtocol1616Float(curr_data, 92 - first_address);
                this.ahrspos_update.vel_z = AHRSProtocol.decodeProtocol1616Float(curr_data, 96 - first_address);
                this.ahrspos_update.disp_x = AHRSProtocol.decodeProtocol1616Float(curr_data, 100 - first_address);
                this.ahrspos_update.disp_y = AHRSProtocol.decodeProtocol1616Float(curr_data, 104 - first_address);
                this.ahrspos_update.disp_z = AHRSProtocol.decodeProtocol1616Float(curr_data, 108 - first_address);
                this.notify_sink.setAHRSPosData(this.ahrspos_update);
            } else {
                this.ahrs_update.op_status = this.ahrspos_update.op_status;
                this.ahrs_update.selftest_status = this.ahrspos_update.selftest_status;
                this.ahrs_update.cal_status = this.ahrspos_update.cal_status;
                this.ahrs_update.sensor_status = this.ahrspos_update.sensor_status;
                this.ahrs_update.yaw = this.ahrspos_update.yaw;
                this.ahrs_update.pitch = this.ahrspos_update.pitch;
                this.ahrs_update.roll = this.ahrspos_update.roll;
                this.ahrs_update.compass_heading = this.ahrspos_update.compass_heading;
                this.ahrs_update.mpu_temp = this.ahrspos_update.mpu_temp;
                this.ahrs_update.linear_accel_x = this.ahrspos_update.linear_accel_x;
                this.ahrs_update.linear_accel_y = this.ahrspos_update.linear_accel_y;
                this.ahrs_update.linear_accel_z = this.ahrspos_update.linear_accel_z;
                this.ahrs_update.altitude = this.ahrspos_update.altitude;
                this.ahrs_update.barometric_pressure = this.ahrspos_update.barometric_pressure;
                this.ahrs_update.fused_heading = this.ahrspos_update.fused_heading;
                this.notify_sink.setAHRSData(this.ahrs_update);
            }

            this.board_state.cal_status = curr_data[(9 - first_address)];
            this.board_state.op_status = curr_data[(8 - first_address)];
            this.board_state.selftest_status = curr_data[(10 - first_address)];
            this.board_state.sensor_status = AHRSProtocol.decodeBinaryUint16(curr_data, 16 - first_address);
            this.board_state.update_rate_hz = curr_data[(4 - first_address)];
            this.board_state.gyro_fsr_dps = AHRSProtocol.decodeBinaryUint16(curr_data, 6);
            this.board_state.accel_fsr_g = ((short) curr_data[5]);
            this.board_state.capability_flags = AHRSProtocol.decodeBinaryUint16(curr_data, 11 - first_address);
            this.notify_sink.setBoardState(this.board_state);

            this.raw_data_update.gyro_x = AHRSProtocol.decodeBinaryInt16(curr_data, 52 - first_address);
            this.raw_data_update.gyro_y = AHRSProtocol.decodeBinaryInt16(curr_data, 54 - first_address);
            this.raw_data_update.gyro_z = AHRSProtocol.decodeBinaryInt16(curr_data, 56 - first_address);
            this.raw_data_update.accel_x = AHRSProtocol.decodeBinaryInt16(curr_data, 58 - first_address);
            this.raw_data_update.accel_y = AHRSProtocol.decodeBinaryInt16(curr_data, 60 - first_address);
            this.raw_data_update.accel_z = AHRSProtocol.decodeBinaryInt16(curr_data, 62 - first_address);
            this.raw_data_update.mag_x = AHRSProtocol.decodeBinaryInt16(curr_data, 64 - first_address);
            this.raw_data_update.mag_y = AHRSProtocol.decodeBinaryInt16(curr_data, 66 - first_address);
            this.raw_data_update.mag_z = AHRSProtocol.decodeBinaryInt16(curr_data, 68 - first_address);
            this.raw_data_update.temp_c = this.ahrspos_update.mpu_temp;
            this.notify_sink.setRawData(this.raw_data_update);

            this.last_update_time = Timer.getFPGATimestamp();
            this.byte_count += curr_data.length;
            this.update_count += 1;
        }
    }

    public boolean isConnected() {
        double time_since_last_update = Timer.getFPGATimestamp() - this.last_update_time;
        return time_since_last_update <= 1.0D;
    }

    public double getByteCount() {
        return this.byte_count;
    }

    public double getUpdateCount() {
        return this.update_count;
    }

    public void setUpdateRateHz(byte update_rate) {
        this.io_provider.write((byte) 4, update_rate);
    }

    public void zeroYaw() {
        this.io_provider.write((byte) 86, Byte.MIN_VALUE);
    }


    public void zeroDisplacement() {
        this.io_provider.write((byte) 86, (byte) 56);
    }
}


