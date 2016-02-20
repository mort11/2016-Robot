package com.kauailabs.navx;


public class AHRSProtocol
        extends IMUProtocol {
    public static final byte NAVX_CAL_STATUS_IMU_CAL_STATE_MASK = 3;

    public static final byte NAVX_CAL_STATUS_IMU_CAL_INPROGRESS = 0;

    public static final byte NAVX_CAL_STATUS_IMU_CAL_ACCUMULATE = 1;

    public static final byte NAVX_CAL_STATUS_IMU_CAL_COMPLETE = 2;

    public static final byte NAVX_CAL_STATUS_MAG_CAL_COMPLETE = 4;

    public static final byte NAVX_CAL_STATUS_BARO_CAL_COMPLETE = 8;

    public static final byte NAVX_SELFTEST_STATUS_COMPLETE = -128;

    public static final byte NAVX_SELFTEST_RESULT_GYRO_PASSED = 1;

    public static final byte NAVX_SELFTEST_RESULT_ACCEL_PASSED = 2;

    public static final byte NAVX_SELFTEST_RESULT_MAG_PASSED = 4;

    public static final byte NAVX_SELFTEST_RESULT_BARO_PASSED = 8;

    public static final byte NAVX_OP_STATUS_INITIALIZING = 0;

    public static final byte NAVX_OP_STATUS_SELFTEST_IN_PROGRESS = 1;

    public static final byte NAVX_OP_STATUS_ERROR = 2;

    public static final byte NAVX_OP_STATUS_IMU_AUTOCAL_IN_PROGRESS = 3;

    public static final byte NAVX_OP_STATUS_NORMAL = 4;

    public static final byte NAVX_SENSOR_STATUS_MOVING = 1;

    public static final byte NAVX_SENSOR_STATUS_YAW_STABLE = 2;

    public static final byte NAVX_SENSOR_STATUS_MAG_DISTURBANCE = 4;

    public static final byte NAVX_SENSOR_STATUS_ALTITUDE_VALID = 8;

    public static final byte NAVX_SENSOR_STATUS_SEALEVEL_PRESS_SET = 16;

    public static final byte NAVX_SENSOR_STATUS_FUSED_HEADING_VALID = 32;

    public static final short NAVX_CAPABILITY_FLAG_OMNIMOUNT = 4;

    public static final short NAVX_CAPABILITY_FLAG_OMNIMOUNT_CONFIG_MASK = 56;

    public static final short NAVX_CAPABILITY_FLAG_VEL_AND_DISP = 64;

    public static final short NAVX_CAPABILITY_FLAG_YAW_RESET = 128;

    public static final byte OMNIMOUNT_DEFAULT = 0;

    public static final byte OMNIMOUNT_YAW_X_UP = 1;

    public static final byte OMNIMOUNT_YAW_X_DOWN = 2;

    public static final byte OMNIMOUNT_YAW_Y_UP = 3;

    public static final byte OMNIMOUNT_YAW_Y_DOWN = 4;

    public static final byte OMNIMOUNT_YAW_Z_UP = 5;

    public static final byte OMNIMOUNT_YAW_Z_DOWN = 6;

    public static final byte NAVX_INTEGRATION_CTL_RESET_VEL_X = 1;

    public static final byte NAVX_INTEGRATION_CTL_RESET_VEL_Y = 2;

    public static final byte NAVX_INTEGRATION_CTL_RESET_VEL_Z = 4;

    public static final byte NAVX_INTEGRATION_CTL_RESET_DISP_X = 8;

    public static final byte NAVX_INTEGRATION_CTL_RESET_DISP_Y = 16;

    public static final byte NAVX_INTEGRATION_CTL_RESET_DISP_Z = 32;

    public static final byte NAVX_INTEGRATION_CTL_RESET_YAW = -128;

    public static final char BINARY_PACKET_INDICATOR_CHAR = '#';

    public static final byte MSGID_AHRS_UPDATE = 97;
    public static final byte MSGID_AHRSPOS_UPDATE = 112;
    public static final byte MSGID_DATA_REQUEST = 68;
    public static final byte MSGID_DATA_SET_RESPONSE = 118;
    public static final byte MSGID_INTEGRATION_CONTROL_CMD = 73;
    public static final byte MSGID_INTEGRATION_CONTROL_RESP = 105;
    public static final byte MSGID_MAG_CAL_CMD = 77;
    public static final byte MSGID_FUSION_TUNING_CMD = 84;
    public static final byte MSGID_BOARD_IDENTITY_RESPONSE = 105;
    public static final int MAX_BINARY_MESSAGE_LENGTH = 66;
    static final int AHRS_UPDATE_YAW_VALUE_INDEX = 4;
    static final int AHRS_UPDATE_PITCH_VALUE_INDEX = 6;
    static final int AHRS_UPDATE_ROLL_VALUE_INDEX = 8;
    static final int AHRS_UPDATE_HEADING_VALUE_INDEX = 10;
    static final int AHRS_UPDATE_ALTITUDE_VALUE_INDEX = 12;
    static final int AHRS_UPDATE_FUSED_HEADING_VALUE_INDEX = 16;
    static final int AHRS_UPDATE_LINEAR_ACCEL_X_VALUE_INDEX = 18;
    static final int AHRS_UPDATE_LINEAR_ACCEL_Y_VALUE_INDEX = 20;
    static final int AHRS_UPDATE_LINEAR_ACCEL_Z_VALUE_INDEX = 22;
    static final int AHRS_UPDATE_CAL_MAG_X_VALUE_INDEX = 24;
    static final int AHRS_UPDATE_CAL_MAG_Y_VALUE_INDEX = 26;
    static final int AHRS_UPDATE_CAL_MAG_Z_VALUE_INDEX = 28;
    static final int AHRS_UPDATE_CAL_MAG_NORM_RATIO_VALUE_INDEX = 30;
    static final int AHRS_UPDATE_CAL_MAG_SCALAR_VALUE_INDEX = 32;
    static final int AHRS_UPDATE_MPU_TEMP_VAUE_INDEX = 36;
    static final int AHRS_UPDATE_RAW_MAG_X_VALUE_INDEX = 38;
    static final int AHRS_UPDATE_RAW_MAG_Y_VALUE_INDEX = 40;
    static final int AHRS_UPDATE_RAW_MAG_Z_VALUE_INDEX = 42;
    static final int AHRS_UPDATE_QUAT_W_VALUE_INDEX = 44;
    static final int AHRS_UPDATE_QUAT_X_VALUE_INDEX = 46;
    static final int AHRS_UPDATE_QUAT_Y_VALUE_INDEX = 48;
    static final int AHRS_UPDATE_QUAT_Z_VALUE_INDEX = 50;
    static final int AHRS_UPDATE_BARO_PRESSURE_VALUE_INDEX = 52;
    static final int AHRS_UPDATE_BARO_TEMP_VAUE_INDEX = 56;
    static final int AHRS_UPDATE_OPSTATUS_VALUE_INDEX = 58;
    static final int AHRS_UPDATE_SENSOR_STATUS_VALUE_INDEX = 59;
    static final int AHRS_UPDATE_CAL_STATUS_VALUE_INDEX = 60;
    static final int AHRS_UPDATE_SELFTEST_STATUS_VALUE_INDEX = 61;
    static final int AHRS_UPDATE_MESSAGE_CHECKSUM_INDEX = 62;
    static final int AHRS_UPDATE_MESSAGE_TERMINATOR_INDEX = 64;
    static final int AHRS_UPDATE_MESSAGE_LENGTH = 66;
    static final int AHRSPOS_UPDATE_YAW_VALUE_INDEX = 4;
    static final int AHRSPOS_UPDATE_PITCH_VALUE_INDEX = 6;
    static final int AHRSPOS_UPDATE_ROLL_VALUE_INDEX = 8;
    static final int AHRSPOS_UPDATE_HEADING_VALUE_INDEX = 10;
    static final int AHRSPOS_UPDATE_ALTITUDE_VALUE_INDEX = 12;
    static final int AHRSPOS_UPDATE_FUSED_HEADING_VALUE_INDEX = 16;
    static final int AHRSPOS_UPDATE_LINEAR_ACCEL_X_VALUE_INDEX = 18;
    static final int AHRSPOS_UPDATE_LINEAR_ACCEL_Y_VALUE_INDEX = 20;
    static final int AHRSPOS_UPDATE_LINEAR_ACCEL_Z_VALUE_INDEX = 22;
    static final int AHRSPOS_UPDATE_VEL_X_VALUE_INDEX = 24;
    static final int AHRSPOS_UPDATE_VEL_Y_VALUE_INDEX = 28;
    static final int AHRSPOS_UPDATE_VEL_Z_VALUE_INDEX = 32;
    static final int AHRSPOS_UPDATE_DISP_X_VALUE_INDEX = 36;
    static final int AHRSPOS_UPDATE_DISP_Y_VALUE_INDEX = 40;
    static final int AHRSPOS_UPDATE_DISP_Z_VALUE_INDEX = 44;
    static final int AHRSPOS_UPDATE_QUAT_W_VALUE_INDEX = 48;
    static final int AHRSPOS_UPDATE_QUAT_X_VALUE_INDEX = 50;
    static final int AHRSPOS_UPDATE_QUAT_Y_VALUE_INDEX = 52;
    static final int AHRSPOS_UPDATE_QUAT_Z_VALUE_INDEX = 54;
    static final int AHRSPOS_UPDATE_MPU_TEMP_VAUE_INDEX = 56;
    static final int AHRSPOS_UPDATE_OPSTATUS_VALUE_INDEX = 58;
    static final int AHRSPOS_UPDATE_SENSOR_STATUS_VALUE_INDEX = 59;
    static final int AHRSPOS_UPDATE_CAL_STATUS_VALUE_INDEX = 60;
    static final int AHRSPOS_UPDATE_SELFTEST_STATUS_VALUE_INDEX = 61;
    static final int AHRSPOS_UPDATE_MESSAGE_CHECKSUM_INDEX = 62;
    static final int AHRSPOS_UPDATE_MESSAGE_TERMINATOR_INDEX = 64;
    static final int AHRSPOS_UPDATE_MESSAGE_LENGTH = 66;
    static final int DATA_REQUEST_DATATYPE_VALUE_INDEX = 4;
    static final int DATA_REQUEST_VARIABLEID_VALUE_INDEX = 5;
    static final int DATA_REQUEST_CHECKSUM_INDEX = 6;
    static final int DATA_REQUEST_TERMINATOR_INDEX = 8;
    static final int DATA_REQUEST_MESSAGE_LENGTH = 10;
    static final int DATA_SET_RESPONSE_DATATYPE_VALUE_INDEX = 4;
    static final int DATA_SET_RESPONSE_VARID_VALUE_INDEX = 5;
    static final int DATA_SET_RESPONSE_STATUS_VALUE_INDEX = 6;
    static final int DATA_SET_RESPONSE_MESSAGE_CHECKSUM_INDEX = 7;
    static final int DATA_SET_RESPONSE_MESSAGE_TERMINATOR_INDEX = 9;
    static final int DATA_SET_RESPONSE_MESSAGE_LENGTH = 11;
    static final int INTEGRATION_CONTROL_CMD_ACTION_INDEX = 4;
    static final int INTEGRATION_CONTROL_CMD_PARAMETER_INDEX = 5;
    static final int INTEGRATION_CONTROL_CMD_MESSAGE_CHECKSUM_INDEX = 9;
    static final int INTEGRATION_CONTROL_CMD_MESSAGE_TERMINATOR_INDEX = 11;
    static final int INTEGRATION_CONTROL_CMD_MESSAGE_LENGTH = 13;
    static final int INTEGRATION_CONTROL_RESP_ACTION_INDEX = 4;
    static final int INTEGRATION_CONTROL_RESP_PARAMETER_INDEX = 5;
    static final int INTEGRATION_CONTROL_RESP_MESSAGE_CHECKSUM_INDEX = 9;
    static final int INTEGRATION_CONTROL_RESP_MESSAGE_TERMINATOR_INDEX = 11;
    static final int INTEGRATION_CONTROL_RESP_MESSAGE_LENGTH = 13;
    static final int MAG_CAL_DATA_ACTION_VALUE_INDEX = 4;
    static final int MAG_X_BIAS_VALUE_INDEX = 5;
    static final int MAG_Y_BIAS_VALUE_INDEX = 7;
    static final int MAG_Z_BIAS_VALUE_INDEX = 9;
    static final int MAG_XFORM_1_1_VALUE_INDEX = 11;
    static final int MAG_XFORM_1_2_VALUE_INDEX = 15;
    static final int MAG_XFORM_1_3_VALUE_INDEX = 19;
    static final int MAG_XFORM_2_1_VALUE_INDEX = 23;
    static final int MAG_XFORM_2_2_VALUE_INDEX = 25;
    static final int MAG_XFORM_2_3_VALUE_INDEX = 31;
    static final int MAG_XFORM_3_1_VALUE_INDEX = 35;
    static final int MAG_XFORM_3_2_VALUE_INDEX = 39;
    static final int MAG_XFORM_3_3_VALUE_INDEX = 43;
    static final int MAG_CAL_EARTH_MAG_FIELD_NORM_VALUE_INDEX = 47;
    static final int MAG_CAL_CMD_MESSAGE_CHECKSUM_INDEX = 51;
    static final int MAG_CAL_CMD_MESSAGE_TERMINATOR_INDEX = 53;
    static final int MAG_CAL_CMD_MESSAGE_LENGTH = 55;
    static final int FUSION_TUNING_DATA_ACTION_VALUE_INDEX = 4;
    static final int FUSION_TUNING_CMD_VAR_ID_VALUE_INDEX = 5;
    static final int FUSION_TUNING_CMD_VAR_VALUE_INDEX = 6;
    static final int FUSION_TUNING_CMD_MESSAGE_CHECKSUM_INDEX = 10;
    static final int FUSION_TUNING_CMD_MESSAGE_TERMINATOR_INDEX = 12;
    static final int FUSION_TUNING_CMD_MESSAGE_LENGTH = 14;
    static final int BOARD_IDENTITY_BOARDTYPE_VALUE_INDEX = 4;
    static final int BOARD_IDENTITY_HWREV_VALUE_INDEX = 5;
    static final int BOARD_IDENTITY_FW_VER_MAJOR = 6;
    static final int BOARD_IDENTITY_FW_VER_MINOR = 7;
    static final int BOARD_IDENTITY_FW_VER_REVISION_VALUE_INDEX = 8;
    static final int BOARD_IDENTITY_UNIQUE_ID_0 = 10;
    static final int BOARD_IDENTITY_UNIQUE_ID_1 = 11;
    static final int BOARD_IDENTITY_UNIQUE_ID_2 = 12;
    static final int BOARD_IDENTITY_UNIQUE_ID_3 = 13;
    static final int BOARD_IDENTITY_UNIQUE_ID_4 = 14;
    static final int BOARD_IDENTITY_UNIQUE_ID_5 = 15;
    static final int BOARD_IDENTITY_UNIQUE_ID_6 = 16;
    static final int BOARD_IDENTITY_UNIQUE_ID_7 = 17;
    static final int BOARD_IDENTITY_UNIQUE_ID_8 = 18;
    static final int BOARD_IDENTITY_UNIQUE_ID_9 = 19;
    static final int BOARD_IDENTITY_UNIQUE_ID_10 = 20;
    static final int BOARD_IDENTITY_UNIQUE_ID_11 = 21;
    static final int BOARD_IDENTITY_RESPONSE_CHECKSUM_INDEX = 22;
    static final int BOARD_IDENTITY_RESPONSE_TERMINATOR_INDEX = 24;
    static final int BOARD_IDENTITY_RESPONSE_MESSAGE_LENGTH = 26;
    static final int CRC7_POLY = 145;

    public static int decodeAHRSUpdate(byte[] buffer, int offset, int length, AHRSUpdate u) {
        if (length < 66) {
            return 0;
        }
        if ((buffer[(offset + 0)] == 33) && (buffer[(offset + 1)] == 35) && (buffer[(offset + 2)] == 64) && (buffer[(offset + 3)] == 97)) {


            if (!verifyChecksum(buffer, offset, 62)) {
                return 0;
            }
            u.yaw = decodeProtocolSignedHundredthsFloat(buffer, offset + 4);
            u.pitch = decodeProtocolSignedHundredthsFloat(buffer, offset + 8);
            u.roll = decodeProtocolSignedHundredthsFloat(buffer, offset + 6);
            u.compass_heading = decodeProtocolUnsignedHundredthsFloat(buffer, offset + 10);
            u.altitude = decodeProtocol1616Float(buffer, offset + 12);
            u.fused_heading = decodeProtocolUnsignedHundredthsFloat(buffer, offset + 16);
            u.linear_accel_x = decodeProtocolSignedThousandthsFloat(buffer, offset + 18);
            u.linear_accel_y = decodeProtocolSignedThousandthsFloat(buffer, offset + 20);
            u.linear_accel_z = decodeProtocolSignedThousandthsFloat(buffer, offset + 22);
            u.cal_mag_x = decodeBinaryInt16(buffer, offset + 24);
            u.cal_mag_y = decodeBinaryInt16(buffer, offset + 26);
            u.cal_mag_z = decodeBinaryInt16(buffer, offset + 28);
            u.mag_field_norm_ratio = decodeProtocolUnsignedHundredthsFloat(buffer, offset + 30);
            u.mag_field_norm_scalar = decodeProtocol1616Float(buffer, offset + 32);
            u.mpu_temp = decodeProtocolSignedHundredthsFloat(buffer, offset + 36);
            u.raw_mag_x = decodeBinaryInt16(buffer, offset + 38);
            u.raw_mag_y = decodeBinaryInt16(buffer, offset + 40);
            u.raw_mag_z = decodeBinaryInt16(buffer, offset + 42);
            u.quat_w = decodeBinaryInt16(buffer, offset + 44);
            u.quat_x = decodeBinaryInt16(buffer, offset + 46);
            u.quat_y = decodeBinaryInt16(buffer, offset + 48);
            u.quat_z = decodeBinaryInt16(buffer, offset + 50);
            u.barometric_pressure = decodeProtocol1616Float(buffer, offset + 52);
            u.baro_temp = decodeProtocolSignedHundredthsFloat(buffer, offset + 56);
            u.op_status = buffer[58];
            u.sensor_status = buffer[59];
            u.cal_status = buffer[60];
            u.selftest_status = buffer[61];
            return 66;
        }
        return 0;
    }

    public static int decodeAHRSPosUpdate(byte[] buffer, int offset, int length, AHRSPosUpdate u) {
        if (length < 66) {
            return 0;
        }
        if ((buffer[(offset + 0)] == 33) && (buffer[(offset + 1)] == 35) && (buffer[(offset + 2)] == 64) && (buffer[(offset + 3)] == 112)) {


            if (!verifyChecksum(buffer, offset, 62)) {
                return 0;
            }
            u.yaw = decodeProtocolSignedHundredthsFloat(buffer, offset + 4);
            u.pitch = decodeProtocolSignedHundredthsFloat(buffer, offset + 8);
            u.roll = decodeProtocolSignedHundredthsFloat(buffer, offset + 6);
            u.compass_heading = decodeProtocolUnsignedHundredthsFloat(buffer, offset + 10);
            u.altitude = decodeProtocol1616Float(buffer, offset + 12);
            u.fused_heading = decodeProtocolUnsignedHundredthsFloat(buffer, offset + 16);
            u.linear_accel_x = decodeProtocolSignedThousandthsFloat(buffer, offset + 18);
            u.linear_accel_y = decodeProtocolSignedThousandthsFloat(buffer, offset + 20);
            u.linear_accel_z = decodeProtocolSignedThousandthsFloat(buffer, offset + 22);
            u.vel_x = decodeProtocol1616Float(buffer, offset + 24);
            u.vel_y = decodeProtocol1616Float(buffer, offset + 28);
            u.vel_z = decodeProtocol1616Float(buffer, offset + 32);
            u.disp_x = decodeProtocol1616Float(buffer, offset + 36);
            u.disp_y = decodeProtocol1616Float(buffer, offset + 40);
            u.disp_z = decodeProtocol1616Float(buffer, offset + 44);
            u.mpu_temp = decodeProtocolSignedHundredthsFloat(buffer, offset + 56);
            u.quat_w = decodeBinaryInt16(buffer, offset + 48);
            u.quat_x = decodeBinaryInt16(buffer, offset + 50);
            u.quat_y = decodeBinaryInt16(buffer, offset + 52);
            u.quat_z = decodeBinaryInt16(buffer, offset + 54);
            u.op_status = buffer[58];
            u.sensor_status = buffer[59];
            u.cal_status = buffer[60];
            u.selftest_status = buffer[61];
            return 66;
        }
        return 0;
    }

    public static int encodeDataGetRequest(byte[] buffer, byte type, byte var_id) {
        buffer[0] = 33;
        buffer[1] = 35;
        buffer[2] = 8;
        buffer[3] = 68;

        buffer[4] = type;
        buffer[5] = var_id;

        encodeTermination(buffer, 10, 6);
        return 10;
    }

    public static int encodeMagCalDataSetRequest(byte[] buffer, MagCalData d) {
        buffer[0] = 33;
        buffer[1] = 35;
        buffer[2] = 53;
        buffer[3] = 77;


        buffer[4] = d.action;
        for (int i = 0; i < 3; i++) {
            encodeBinaryInt16(d.mag_bias[i], buffer, 5 + i * 2);
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                encodeProtocol1616Float(d.mag_xform[i][j], buffer, 11 + i * 6 + j * 2);
            }
        }

        encodeProtocol1616Float(d.earth_mag_field_norm, buffer, 47);

        encodeTermination(buffer, 55, 51);
        return 55;
    }

    public static int decodeMagCalDataGetResponse(byte[] buffer, int offset, int length, MagCalData d) {
        if (length < 55) return 0;
        if ((buffer[0] == 33) && (buffer[1] == 35) && (buffer[2] == 53) && (buffer[3] == 77)) {


            if (!verifyChecksum(buffer, offset, 51)) {
                return 0;
            }
            d.action = buffer[4];
            for (int i = 0; i < 3; i++) {
                d.mag_bias[i] = decodeBinaryInt16(buffer, 5 + i * 2);
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    d.mag_xform[i][j] = decodeProtocol1616Float(buffer, 11 + i * 6 + j * 2);
                }
            }
            d.earth_mag_field_norm = decodeProtocol1616Float(buffer, 47);
            return 55;
        }
        return 0;
    }

    public static int encodeTuningVarSetRequest(byte[] buffer, TuningVar r) {
        buffer[0] = 33;
        buffer[1] = 35;
        buffer[2] = 12;
        buffer[3] = 84;

        buffer[4] = r.action;
        buffer[5] = r.var_id;
        encodeProtocol1616Float(r.value, buffer, 6);

        encodeTermination(buffer, 14, 10);
        return 14;
    }

    public static int decodeTuningVarGetResponse(byte[] buffer, int offset, int length, TuningVar r) {
        if (length < 14) return 0;
        if ((buffer[0] == 33) && (buffer[1] == 35) && (buffer[2] == 12) && (buffer[3] == 84)) {


            if (!verifyChecksum(buffer, offset, 10)) {
                return 0;
            }

            r.action = buffer[4];
            r.var_id = buffer[5];
            r.value = decodeProtocol1616Float(buffer, 6);
            return 14;
        }
        return 0;
    }

    public static int encodeIntegrationControlCmd(byte[] buffer, IntegrationControl u) {
        buffer[0] = 33;
        buffer[1] = 35;
        buffer[2] = 11;
        buffer[3] = 73;

        buffer[4] = u.action;
        encodeBinaryUint32(u.parameter, buffer, 5);

        encodeTermination(buffer, 13, 9);
        return 13;
    }

    public static int decodeIntegrationControlResponse(byte[] buffer, int offset, int length, IntegrationControl u) {
        if (length < 13) return 0;
        if ((buffer[0] == 33) && (buffer[1] == 35) && (buffer[2] == 11) && (buffer[3] == 105)) {


            if (!verifyChecksum(buffer, offset, 9)) {
                return 0;
            }

            u.action = buffer[4];
            u.parameter = decodeBinaryUint32(buffer, 5);
            return 13;
        }
        return 0;
    }

    public static int decodeDataSetResponse(byte[] buffer, int offset, int length, DataSetResponse d) {
        if (length < 11) return 0;
        if ((buffer[0] == 33) && (buffer[1] == 35) && (buffer[2] == 9) && (buffer[3] == 118)) {


            if (!verifyChecksum(buffer, offset, 7)) {
                return 0;
            }
            d.data_type = buffer[4];
            d.var_id = buffer[5];
            d.status = buffer[6];
            return 11;
        }
        return 0;
    }

    public static int decodeBoardIDGetResponse(byte[] buffer, int offset, int length, BoardID id) {
        if (length < 26) return 0;
        if ((buffer[0] == 33) && (buffer[1] == 35) && (buffer[2] == 24) && (buffer[3] == 105)) {


            if (!verifyChecksum(buffer, offset, 22)) return 0;
            id.type = buffer[4];
            id.hw_rev = buffer[5];
            id.fw_ver_major = buffer[6];
            id.fw_ver_minor = buffer[7];
            id.fw_revision = decodeBinaryUint16(buffer, 8);
            for (int i = 0; i < 12; i++) {
                id.unique_id[i] = buffer[(10 + i)];
            }
            return 26;
        }
        return 0;
    }

    public static short decodeBinaryUint16(byte[] buffer, int offset) {
        short lowbyte = (short) ((short) buffer[offset] & 0xFF);
        short highbyte = (short) buffer[(offset + 1)];
        highbyte = (short) (highbyte << 8);
        short decoded_value = (short) (highbyte + lowbyte);
        return decoded_value;
    }

    public static void encodeBinaryUint16(short val, byte[] buffer, int offset) {
        buffer[(offset + 0)] = ((byte) (val & 0xFF));
        buffer[(offset + 1)] = ((byte) (val >> 8 & 0xFF));
    }

    public static int decodeBinaryUint32(byte[] buffer, int offset) {
        int lowlowbyte = buffer[offset] & 0xFF;
        int lowhighbyte = buffer[(offset + 1)] & 0xFF;
        int highlowbyte = buffer[(offset + 2)] & 0xFF;
        int highhighbyte = buffer[(offset + 3)];

        lowhighbyte <<= 8;
        highlowbyte <<= 16;
        highhighbyte <<= 24;

        int result = highhighbyte + highlowbyte + lowhighbyte + lowlowbyte;
        return result;
    }

    public static void encodeBinaryUint32(int val, byte[] buffer, int offset) {
        buffer[(offset + 0)] = ((byte) (val & 0xFF));
        buffer[(offset + 1)] = ((byte) (val >> 8 & 0xFF));
        buffer[(offset + 2)] = ((byte) (val >> 16 & 0xFF));
        buffer[(offset + 3)] = ((byte) (val >> 24 & 0xFF));
    }

    public static short decodeBinaryInt16(byte[] buffer, int offset) {
        return decodeBinaryUint16(buffer, offset);
    }

    public static void encodeBinaryInt16(short val, byte[] buffer, int offset) {
        encodeBinaryUint16(val, buffer, offset);
    }

    public static float decodeProtocolSignedHundredthsFloat(byte[] buffer, int offset) {
        float signed_angle = decodeBinaryUint16(buffer, offset);
        signed_angle /= 100.0F;
        return signed_angle;
    }

    public static void encodeProtocolSignedHundredthsFloat(float input, byte[] buffer, int offset) {
        short input_as_int = (short) (int) (input * 100.0F);
        encodeBinaryInt16(input_as_int, buffer, offset);
    }

    public static short encodeSignedHundredthsFloat(float input) {
        return (short) (int) (input * 100.0F);
    }

    public static short encodeUnsignedHundredthsFloat(float input) {
        return (short) (int) (input * 100.0F);
    }

    public static float encodeRatioFloat(float input_ratio) {
        return input_ratio *= 32768.0F;
    }

    public static float encodeSignedThousandthsFloat(float input) {
        return input * 1000.0F;
    }

    public static float decodeProtocolUnsignedHundredthsFloat(byte[] buffer, int offset) {
        int uint16 = decodeBinaryUint16(buffer, offset);
        if (uint16 < 0) {
            uint16 += 65536;
        }
        float unsigned_float = uint16;
        unsigned_float /= 100.0F;
        return unsigned_float;
    }

    public static void encodeProtocolUnsignedHundredthsFloat(float input, byte[] buffer, int offset) {
        short input_as_uint = (short) (int) (input * 100.0F);
        encodeBinaryUint16(input_as_uint, buffer, offset);
    }

    public static float decodeProtocolSignedThousandthsFloat(byte[] buffer, int offset) {
        float signed_angle = decodeBinaryUint16(buffer, offset);
        signed_angle /= 1000.0F;
        return signed_angle;
    }

    public static void encodeProtocolSignedThousandthsFloat(float input, byte[] buffer, int offset) {
        short input_as_int = (short) (int) (input * 1000.0F);
        encodeBinaryInt16(input_as_int, buffer, offset);
    }

    public static float decodeProtocolRatio(byte[] buffer, int offset) {
        float ratio = decodeBinaryUint16(buffer, offset);
        ratio /= 32768.0F;
        return ratio;
    }

    public static void encodeProtocolRatio(float ratio, byte[] buffer, int offset) {
        ratio *= 32768.0F;
        encodeBinaryInt16((short) (int) ratio, buffer, offset);
    }

    public static float decodeProtocol1616Float(byte[] buffer, int offset) {
        float result = decodeBinaryUint32(buffer, offset);
        result /= 65536.0F;
        return result;
    }

    public static void encodeProtocol1616Float(float val, byte[] buffer, int offset) {
        val *= 65536.0F;
        int int_val = (int) val;
        encodeBinaryUint32(int_val, buffer, offset);
    }

    public static byte getCRC(byte[] buffer, int length) {
        int crc = 0;

        for (int i = 0; i < length; i++) {
            crc ^= 0xFF & buffer[i];
            for (int j = 0; j < 8; j++) {
                if ((crc & 0x1) != 0) {
                    crc ^= 0x91;
                }
                crc >>= 1;
            }
        }
        return (byte) crc;
    }

    public static class AHRSUpdate {
        public float yaw;
        public float pitch;
        public float roll;
        public float compass_heading;
        public float altitude;
        public float fused_heading;
        public float linear_accel_x;
        public float linear_accel_y;
        public float linear_accel_z;
        public short cal_mag_x;
        public short cal_mag_y;
        public short cal_mag_z;
        public float mag_field_norm_ratio;
        public float mag_field_norm_scalar;
        public float mpu_temp;
        public short raw_mag_x;
        public short raw_mag_y;
        public short raw_mag_z;
        public short quat_w;
        public short quat_x;
        public short quat_y;
        public short quat_z;
        public float barometric_pressure;
        public float baro_temp;
        public byte op_status;
        public byte sensor_status;
        public byte cal_status;
        public byte selftest_status;
    }

    public static class AHRSPosUpdate {
        public float yaw;
        public float pitch;
        public float roll;
        public float compass_heading;
        public float altitude;
        public float fused_heading;
        public float linear_accel_x;
        public float linear_accel_y;
        public float linear_accel_z;
        public float vel_x;
        public float vel_y;
        public float vel_z;
        public float disp_x;
        public float disp_y;
        public float disp_z;
        public float mpu_temp;
        public short quat_w;
        public short quat_x;
        public short quat_y;
        public short quat_z;
        public float barometric_pressure;
        public float baro_temp;
        public byte op_status;
        public byte sensor_status;
        public byte cal_status;
        public byte selftest_status;
    }

    public static class DataSetResponse {
        public byte data_type;
        public byte var_id;
        public byte status;
    }

    public static class IntegrationControl {
        public byte action;
        public int parameter;
    }

    public static class MagCalData {
        public short[] mag_bias;
        public float[][] mag_xform;
        public float earth_mag_field_norm;
        byte action;

        public MagCalData() {
            this.mag_bias = new short[3];
            this.mag_xform = new float[3][3];
        }
    }

    public static class TuningVar {
        public byte action;
        public byte var_id;
        float value;
    }

    public static class BoardID {
        public byte type;
        public byte hw_rev;
        public byte fw_ver_major;
        public byte fw_ver_minor;
        public short fw_revision;
        public byte[] unique_id;

        public BoardID() {
            this.unique_id = new byte[12];
        }
    }

    public class AHRS_TUNING_VAR_ID {
        public static final byte UNSPECIFIED = 0;
        public static final byte MOTION_THRESHOLD = 1;
        public static final byte YAW_STABLE_THRESHOLD = 2;
        public static final byte MAG_DISTURBANCE_THRESHOLD = 3;
        public static final byte SEA_LEVEL_PRESSURE = 4;

        public AHRS_TUNING_VAR_ID() {
        }
    }

    public class AHRS_DATA_TYPE {
        public static final byte TUNING_VARIABLE = 0;
        public static final byte MAG_CALIBRATION = 1;
        public static final byte BOARD_IDENTITY = 2;

        public AHRS_DATA_TYPE() {
        }
    }

    public class AHRS_DATA_ACTION {
        public static final byte DATA_GET = 0;
        public static final byte DATA_SET = 1;

        public AHRS_DATA_ACTION() {
        }
    }
}


