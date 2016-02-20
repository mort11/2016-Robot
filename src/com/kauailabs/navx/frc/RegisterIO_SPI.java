package com.kauailabs.navx.frc;

import com.kauailabs.navx.AHRSProtocol;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;


class RegisterIO_SPI
        implements IRegisterIO {
    static final int DEFAULT_SPI_BITRATE_HZ = 500000;
    SPI port;
    int bitrate;

    public RegisterIO_SPI(SPI spi_port) {
        this.port = spi_port;
        this.bitrate = 500000;
    }

    public RegisterIO_SPI(SPI spi_port, int bitrate) {
        this.port = spi_port;
        this.bitrate = bitrate;
    }

    public boolean init() {
        this.port.setClockRate(this.bitrate);
        this.port.setMSBFirst();
        this.port.setSampleDataOnFalling();
        this.port.setClockActiveLow();
        this.port.setChipSelectActiveLow();
        return true;
    }

    public boolean write(byte address, byte value) {
        byte[] cmd = new byte[3];
        cmd[0] = ((byte) (address | 0xFFFFFF80));
        cmd[1] = value;
        cmd[2] = AHRSProtocol.getCRC(cmd, 2);
        return this.port.write(cmd, cmd.length) == cmd.length;
    }

    public boolean read(byte first_address, byte[] buffer) {
        byte[] cmd = new byte[3];
        cmd[0] = first_address;
        cmd[1] = ((byte) buffer.length);
        cmd[2] = AHRSProtocol.getCRC(cmd, 2);
        if (this.port.write(cmd, cmd.length) != cmd.length) {
            return false;
        }

        Timer.delay(0.001D);
        byte[] received_data = new byte[buffer.length + 1];
        if (this.port.read(true, received_data, received_data.length) != received_data.length) {
            return false;
        }
        byte crc = AHRSProtocol.getCRC(received_data, received_data.length - 1);
        if (crc != received_data[(received_data.length - 1)]) {
            return false;
        }
        System.arraycopy(received_data, 0, buffer, 0, received_data.length - 1);
        return true;
    }

    public boolean shutdown() {
        return true;
    }
}


