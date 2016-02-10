package org.mort11.constants;

/**
 * DrivetrainConstants - Constants for the Drivetrain. Uses CAN bus for IDs on final robot
 *
 * @author Matt Turi <mturi@mort11.org>
 */
public class DrivetrainConstants {
    // CAN IDs
    public static final int DT_LEFT_TALON_ID_1 = 10;
    public static final int DT_LEFT_TALON_ID_2 = 9;
    public static final int DT_LEFT_TALON_ID_3 = 8;
    public static final int DT_RIGHT_TALON_ID_1 = 5;
    public static final int DT_RIGHT_TALON_ID_2 = 4;
    public static final int DT_RIGHT_TALON_ID_3 = 3;

    // Solenoid ports
    public static final int DT_LOW_SHIFTER_PORT = 4;
    public static final int DT_HIGH_SHIFTER_PORT = 5;

    // Other constants
    public static final double DT_P = 0.3;
    public static final double WHEEL_RADIUS_INCHES = 3; // From KoP robot
    public static final int ENC_CPR_LEFT = 2381; // Tick count from kop robot on left side, ports 2,3
    public static final int ENC_CPR_RIGHT = -2358; // Tick count from kop robot on right side, ports 0,1
    public static final double INCHES_PER_PULSE_LEFT = 6 * Math.PI / ENC_CPR_LEFT;
    public static final double INCHES_PER_PULSE_RIGHT = 6 * Math.PI / ENC_CPR_RIGHT;
    public static final double kRobotRadius = 9.75;
}
