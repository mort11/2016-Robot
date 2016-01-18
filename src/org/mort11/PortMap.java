package org.mort11;

public class PortMap {
    /**
     * These are the CAN device IDs for the TalonSRXs on the practice bot. Values will change on final bot
     */
    public static final int DT_LEFT_PORT = 1; // CAN
    public static final int DT_RIGHT_PORT = 2;// CAN

    // Encoders
    public static final int DT_ENC_LEFT_A = 0; // DIO
    public static final int DT_ENC_LEFT_B = 1; // DIO
    public static final int DT_ENC_RIGHT_A = 2; // DIO
    public static final int DT_ENC_RIGHT_B = 3; // DIO

    // Hanging arm?
    public static final int ARM_TAL_1 = 0;
    public static final int ARM_LIM = 0;
    public static final int ARM_ENC_1 = 0;
    public static final int ARM_ENC_2 = 0;
    public static final int ARM_POT = 0;
}
