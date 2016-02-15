package com.kauailabs.navx.frc;

import edu.wpi.first.wpilibj.I2C;


class RegisterIO_I2C
        implements IRegisterIO {
    static final int MAX_WPILIB_I2C_READ_BYTES = 127;
    I2C port;

    public RegisterIO_I2C(I2C i2c_port) {
        this.port = i2c_port;
    }

    public boolean init() {
        return true;
    }

    public boolean write(byte address, byte value) {
        return this.port.write(address | 0x80, value);
    }


    public boolean read(byte first_address, byte[] buffer) {
        int len = buffer.length;
        int buffer_offset = 0;
        while (len > 0) {
            int read_len = len > 127 ? 127 : len;
            byte[] read_buffer = new byte[read_len];
            if ((this.port.write(first_address + buffer_offset, read_len)) ||
                    (this.port.readOnly(read_buffer, read_len))) break;
            System.arraycopy(read_buffer, 0, buffer, buffer_offset, read_len);
            buffer_offset += read_len;
            len -= read_len;
        }


        return len == 0;
    }

    public boolean shutdown() {
        return true;
    }
}


