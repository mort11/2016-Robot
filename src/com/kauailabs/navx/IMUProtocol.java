package com.kauailabs.navx;


public class IMUProtocol {
    public static final byte PACKET_START_CHAR = 33;
    public static final byte MSGID_YPR_UPDATE = 121;
    public static final byte MSGID_QUATERNION_UPDATE = 113;
    public static final byte MSGID_GYRO_UPDATE = 103;
    public static final byte MSGID_STREAM_CMD = 83;
    public static final int STREAM_CMD_STREAM_TYPE_YPR = 121;
    public static final int STREAM_CMD_STREAM_TYPE_QUATERNION = 113;
    public static final int STREAM_CMD_STREAM_TYPE_GYRO = 103;
    public static final byte MSG_ID_STREAM_RESPONSE = 115;
    public static final byte STREAM_MSG_TERMINATION_CHAR = 10;
    public static final short NAV6_FLAG_MASK_CALIBRATION_STATE = 3;
    public static final short NAV6_CALIBRATION_STATE_WAIT = 0;
    public static final short NAV6_CALIBRATION_STATE_ACCUMULATE = 1;
    public static final short NAV6_CALIBRATION_STATE_COMPLETE = 2;
    public static final int IMU_PROTOCOL_MAX_MESSAGE_LENGTH = 53;
    protected static final byte[] hexArray = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70};
    static final int PROTOCOL_FLOAT_LENGTH = 7;
    static final int CHECKSUM_LENGTH = 2;
    static final int TERMINATOR_LENGTH = 2;
    static final int YPR_UPDATE_YAW_VALUE_INDEX = 2;
    static final int YPR_UPDATE_PITCH_VALUE_INDEX = 9;
    static final int YPR_UPDATE_ROLL_VALUE_INDEX = 16;
    static final int YPR_UPDATE_COMPASS_VALUE_INDEX = 23;
    static final int YPR_UPDATE_CHECKSUM_INDEX = 30;
    static final int YPR_UPDATE_TERMINATOR_INDEX = 32;
    static final int YPR_UPDATE_MESSAGE_LENGTH = 34;
    static final int QUATERNION_UPDATE_MESSAGE_LENGTH = 53;
    static final int QUATERNION_UPDATE_QUAT1_VALUE_INDEX = 2;
    static final int QUATERNION_UPDATE_QUAT2_VALUE_INDEX = 6;
    static final int QUATERNION_UPDATE_QUAT3_VALUE_INDEX = 10;
    static final int QUATERNION_UPDATE_QUAT4_VALUE_INDEX = 14;
    static final int QUATERNION_UPDATE_ACCEL_X_VALUE_INDEX = 18;
    static final int QUATERNION_UPDATE_ACCEL_Y_VALUE_INDEX = 22;
    static final int QUATERNION_UPDATE_ACCEL_Z_VALUE_INDEX = 26;
    static final int QUATERNION_UPDATE_MAG_X_VALUE_INDEX = 30;
    static final int QUATERNION_UPDATE_MAG_Y_VALUE_INDEX = 34;
    static final int QUATERNION_UPDATE_MAG_Z_VALUE_INDEX = 38;
    static final int QUATERNION_UPDATE_TEMP_VALUE_INDEX = 42;
    static final int QUATERNION_UPDATE_CHECKSUM_INDEX = 49;
    static final int QUATERNION_UPDATE_TERMINATOR_INDEX = 51;
    static final int GYRO_UPDATE_GYRO_X_VALUE_INDEX = 2;
    static final int GYRO_UPDATE_GYRO_Y_VALUE_INDEX = 6;
    static final int GYRO_UPDATE_GYRO_Z_VALUE_INDEX = 10;
    static final int GYRO_UPDATE_ACCEL_X_VALUE_INDEX = 14;
    static final int GYRO_UPDATE_ACCEL_Y_VALUE_INDEX = 18;
    static final int GYRO_UPDATE_ACCEL_Z_VALUE_INDEX = 22;
    static final int GYRO_UPDATE_MAG_X_VALUE_INDEX = 26;
    static final int GYRO_UPDATE_MAG_Y_VALUE_INDEX = 30;
    static final int GYRO_UPDATE_MAG_Z_VALUE_INDEX = 34;
    static final int GYRO_UPDATE_TEMP_VALUE_INDEX = 38;
    static final int GYRO_UPDATE_CHECKSUM_INDEX = 42;
    static final int GYRO_UPDATE_TERMINATOR_INDEX = 44;
    static final int GYRO_UPDATE_MESSAGE_LENGTH = 46;
    static final int STREAM_CMD_STREAM_TYPE_INDEX = 2;
    static final int STREAM_CMD_UPDATE_RATE_HZ_INDEX = 3;
    static final int STREAM_CMD_CHECKSUM_INDEX = 5;
    static final int STREAM_CMD_TERMINATOR_INDEX = 7;
    static final int STREAM_CMD_MESSAGE_LENGTH = 9;
    static final int STREAM_RESPONSE_MESSAGE_LENGTH = 46;
    static final int STREAM_RESPONSE_STREAM_TYPE_INDEX = 2;
    static final int STREAM_RESPONSE_GYRO_FULL_SCALE_DPS_RANGE = 3;
    static final int STREAM_RESPONSE_ACCEL_FULL_SCALE_G_RANGE = 7;
    static final int STREAM_RESPONSE_UPDATE_RATE_HZ = 11;
    static final int STREAM_RESPONSE_YAW_OFFSET_DEGREES = 15;
    static final int STREAM_RESPONSE_QUAT1_OFFSET = 22;
    static final int STREAM_RESPONSE_QUAT2_OFFSET = 26;
    static final int STREAM_RESPONSE_QUAT3_OFFSET = 30;
    static final int STREAM_RESPONSE_QUAT4_OFFSET = 34;
    static final int STREAM_RESPONSE_FLAGS = 38;
    static final int STREAM_RESPONSE_CHECKSUM_INDEX = 42;
    static final int STREAM_RESPONSE_TERMINATOR_INDEX = 44;

    public static int encodeStreamCommand(byte[] protocol_buffer, byte stream_type, byte update_rate_hz) {
        protocol_buffer[0] = 33;
        protocol_buffer[1] = 83;


        protocol_buffer[2] = stream_type;
        byteToHex(update_rate_hz, protocol_buffer, 3);


        encodeTermination(protocol_buffer, 9, 5);

        return 9;
    }

    public static int decodeStreamResponse(byte[] buffer, int offset, int length, StreamResponse r) {
        if (length < 46) {
            return 0;
        }
        if ((buffer[(offset + 0)] == 33) && (buffer[(offset + 1)] == 115)) {
            if (!verifyChecksum(buffer, offset, 42)) {
                return 0;
            }

            r.stream_type = buffer[(offset + 2)];
            r.gyro_fsr_dps = decodeProtocolUint16(buffer, offset + 3);
            r.accel_fsr_g = decodeProtocolUint16(buffer, offset + 7);
            r.update_rate_hz = decodeProtocolUint16(buffer, offset + 11);
            r.yaw_offset_degrees = decodeProtocolFloat(buffer, offset + 15);
            r.q1_offset = decodeProtocolUint16(buffer, offset + 22);
            r.q2_offset = decodeProtocolUint16(buffer, offset + 26);
            r.q3_offset = decodeProtocolUint16(buffer, offset + 30);
            r.q4_offset = decodeProtocolUint16(buffer, offset + 34);
            r.flags = decodeProtocolUint16(buffer, offset + 38);

            return 46;
        }
        return 0;
    }

    public static int decodeStreamCommand(byte[] buffer, int offset, int length, StreamCommand c) {
        if (length < 9) {
            return 0;
        }
        if ((buffer[(offset + 0)] == 33) && (buffer[(offset + 1)] == 83)) {
            if (!verifyChecksum(buffer, offset, 5)) {
                return 0;
            }

            c.stream_type = buffer[(offset + 2)];
            return 9;
        }
        return 0;
    }

    public static int decodeYPRUpdate(byte[] buffer, int offset, int length, YPRUpdate u) {
        if (length < 34) {
            return 0;
        }
        if ((buffer[(offset + 0)] == 33) && (buffer[(offset + 1)] == 121)) {
            if (!verifyChecksum(buffer, offset, 30)) {
                return 0;
            }

            u.yaw = decodeProtocolFloat(buffer, offset + 2);
            u.pitch = decodeProtocolFloat(buffer, offset + 9);
            u.roll = decodeProtocolFloat(buffer, offset + 16);
            u.compass_heading = decodeProtocolFloat(buffer, offset + 23);
            return 34;
        }
        return 0;
    }

    public static int decodeQuaternionUpdate(byte[] buffer, int offset, int length, QuaternionUpdate u) {
        if (length < 53) {
            return 0;
        }
        if ((buffer[(offset + 0)] == 33) && (buffer[(offset + 1)] == 113)) {
            if (!verifyChecksum(buffer, offset, 49)) {
                return 0;
            }

            u.q1 = decodeProtocolUint16(buffer, offset + 2);
            u.q2 = decodeProtocolUint16(buffer, offset + 6);
            u.q3 = decodeProtocolUint16(buffer, offset + 10);
            u.q4 = decodeProtocolUint16(buffer, offset + 14);
            u.accel_x = decodeProtocolUint16(buffer, offset + 18);
            u.accel_y = decodeProtocolUint16(buffer, offset + 22);
            u.accel_z = decodeProtocolUint16(buffer, offset + 26);
            u.mag_x = decodeProtocolUint16(buffer, offset + 30);
            u.mag_y = decodeProtocolUint16(buffer, offset + 34);
            u.mag_z = decodeProtocolUint16(buffer, offset + 38);
            u.temp_c = decodeProtocolFloat(buffer, offset + 42);
            return 53;
        }
        return 0;
    }

    public static int decodeGyroUpdate(byte[] buffer, int offset, int length, GyroUpdate u) {
        if (length < 46) {
            return 0;
        }
        if ((buffer[(offset + 0)] == 33) && (buffer[(offset + 1)] == 103)) {
            if (!verifyChecksum(buffer, offset, 42)) {
                return 0;
            }

            u.gyro_x = decodeProtocolUint16(buffer, offset + 2);
            u.gyro_y = decodeProtocolUint16(buffer, offset + 6);
            u.gyro_z = decodeProtocolUint16(buffer, offset + 10);
            u.accel_x = decodeProtocolUint16(buffer, offset + 14);
            u.accel_y = decodeProtocolUint16(buffer, offset + 18);
            u.accel_z = decodeProtocolUint16(buffer, offset + 22);
            u.mag_x = decodeProtocolUint16(buffer, offset + 26);
            u.mag_y = decodeProtocolUint16(buffer, offset + 30);
            u.mag_z = decodeProtocolUint16(buffer, offset + 34);
            u.temp_c = decodeProtocolUnsignedHundredthsFloat(buffer, offset + 38);
            return 46;
        }
        return 0;
    }

    public static void encodeTermination(byte[] buffer, int total_length, int content_length) {
        if ((total_length >= 4) && (total_length >= content_length + 4)) {
            byte checksum = 0;
            for (int i = 0; i < content_length; i++) {
                checksum = (byte) (checksum + buffer[i]);
            }


            byteToHex(checksum, buffer, content_length);

            buffer[(content_length + 2 + 0)] = 13;
            buffer[(content_length + 2 + 1)] = 10;
        }
    }

    public static void byteToHex(byte thebyte, byte[] dest, int offset) {
        int v = thebyte & 0xFF;
        dest[(offset + 0)] = hexArray[(v >> 4)];
        dest[(offset + 1)] = hexArray[(v & 0xF)];
    }

    public static short decodeProtocolUint16(byte[] uint16_string, int offset) {
        short decoded_uint16 = 0;
        int shift_left = 12;
        for (int i = offset + 0; i < offset + 4; i++) {
            byte digit = (byte) (uint16_string[i] <= 57 ? uint16_string[i] - 48 : uint16_string[i] - 65 + 10);
            decoded_uint16 = (short) (decoded_uint16 + ((short) digit << shift_left));
            shift_left -= 4;
        }
        return decoded_uint16;
    }

    public static float decodeProtocolUnsignedHundredthsFloat(byte[] uint8_unsigned_hundredths_float, int offset) {
        float unsigned_float = decodeProtocolUint16(uint8_unsigned_hundredths_float, offset);
        unsigned_float /= 100.0F;
        return unsigned_float;
    }

    public static boolean verifyChecksum(byte[] buffer, int offset, int content_length) {
        byte checksum = 0;
        for (int i = 0; i < content_length; i++) {
            checksum = (byte) (checksum + buffer[(offset + i)]);
        }


        byte decoded_checksum = decodeUint8(buffer, offset + content_length);

        return checksum == decoded_checksum;
    }

    public static byte decodeUint8(byte[] checksum, int offset) {
        byte first_digit = (byte) (checksum[(0 + offset)] <= 57 ? checksum[(0 + offset)] - 48 : checksum[(0 + offset)] - 65 + 10);
        byte second_digit = (byte) (checksum[(1 + offset)] <= 57 ? checksum[(1 + offset)] - 48 : checksum[(1 + offset)] - 65 + 10);
        byte decoded_checksum = (byte) (first_digit * 16 + second_digit);
        return decoded_checksum;
    }

    public static float decodeProtocolFloat(byte[] buffer, int offset) {
        String float_string = new String(buffer, offset, 7);
        return Float.parseFloat(float_string);
    }

    public static class GyroUpdate {
        public short gyro_x;
        public short gyro_y;
        public short gyro_z;
        public short accel_x;
        public short accel_y;
        public short accel_z;
        public short mag_x;
        public short mag_y;
        public short mag_z;
        public float temp_c;
    }

    public static class QuaternionUpdate {
        public short q1;
        public short q2;
        public short q3;
        public short q4;
        public short accel_x;
        public short accel_y;
        public short accel_z;
        public short mag_x;
        public short mag_y;
        public short mag_z;
        public float temp_c;
    }

    public static class StreamResponse {
        public byte stream_type;
        public short gyro_fsr_dps;
        public short accel_fsr_g;
        public short update_rate_hz;
        public float yaw_offset_degrees;
        public short q1_offset;
        public short q2_offset;
        public short q3_offset;
        public short q4_offset;
        public short flags;
    }

    public static class StreamCommand {
        public byte stream_type;
    }

    public static class YPRUpdate {
        public float yaw;
        public float pitch;
        public float roll;
        public float compass_heading;
    }
}


