package org.mort11.constants;

/**
 * Constants - All the robot constants
 *
 * @author Matt Turi
 */
public class Constants {
    /**
     * Talon CAN IDs
     */
    public static final int DT_LEFT_TALON_ID_1 = 10;
    public static final int DT_LEFT_TALON_ID_2 = 9;
    public static final int DT_LEFT_TALON_ID_3 = 8;
    public static final int DT_RIGHT_TALON_ID_1 = 5;
    public static final int DT_RIGHT_TALON_ID_2 = 4;
    public static final int DT_RIGHT_TALON_ID_3 = 3;
    public static final int FLYWHEEL_TALON_ID = 7;
    public static final int INTAKE_ARM_TALON_ID = 6;
    public static final int ROLLER_TALON_ID = 1;

    /**
     * Solenoid ports
     */
    public static final int PCM_ID = 20; // PCM CAN ID
    public static final int DT_LOW_SHIFTER_PORT = 0;
    public static final int DT_HIGH_SHIFTER_PORT = 1;
    public static final int HOOD_SOLENOID_A = 2;
    public static final int HOOD_SOLENOID_B = 3;

    /**
     * LED PWMs
     */
    public static final int RED_LIGHT = 2;
    public static final int GREEN_LIGHT = 1;
    public static final int BLUE_LIGHT = 0;

    /**
     * DS Joystick ports
     */
    public static final int LEFT_JOYSTICK = 0;
    public static final int RIGHT_JOYSTICK = 1;
    public static final int EE_JOYSTICK = 2;

    /**
     * Joystick Buttons
     */
    // Right Drive stick
    public static final int SHIFT_UP_BUTTON = 7;

    // Left Joystick
    public static final int SHIFT_DOWN_BUTTON = 7;

    // EE Stick
    public static final int INTAKE_BUTTON = 3;
    public static final int OUTTAKE_BUTTON = 5;
    public static final int FULL_SPEED_BUTTON = 11;
    public static final int SPIN_UP_BUTTON = 11;
    public static final int TOGGLE_HOOD = 2;

    // TODO: 2/21/2016 Figure out what buttons Ally wants these presets mapped to
    public static final int ARM_INTERRUPT = 8;
    public static final int ARM_TO_90 = 10;
    public static final int ARM_TO_0 = 12;

    /**
     * PDP Slots
     * THESE ARE FOR THE PRACTICE BOT PDP
     */
    public static final int PDP_LEFT_DT_1 = 0;
    public static final int PDP_LEFT_DT_2 = 1;
    public static final int PDP_LEFT_DT_3 = 2;
    public static final int PDP_RIGHT_DT_1 = 15;
    public static final int PDP_RIGHT_DT_2 = 14;
    public static final int PDP_RIGHT_DT_3 = 13;
    public static final int PDP_INTAKE_ARM = 7;
    public static final int PDP_FLYWHEEL = 3;
    public static final int PDP_ROLLERS = 8;

    /**
     * Sensor ports
     */
    // DT Left
    public static final int DT_ENCODER_LEFT_A = 0;
    public static final int DT_ENCODER_LEFT_B = 1;

    // DT Right
    public static final int DT_ENCODER_RIGHT_A = 2;
    public static final int DT_ENCODER_RIGHT_B = 3;

    // Intake Arm
    public static final int INTAKE_ARM_ENCODER_A = 4;
    public static final int INTAKE_ARM_ENCODER_B = 5;

    // Arm limit switch
    public static final int ARM_LIM_SWITCH_PORT = 4;

    // Flywheel
    public static final int FLYWHEEL_ENCODER_A = 6;
    public static final int FLYWHEEL_ENCODER_B = 7;

    // Rollers
    public static final int ROLLERS_ENCODER_A = 8;
    public static final int ROLLERS_ENCODER_B = 9;

    /** ============================================================================================================= */

    /**
     * MATH AND OTHER CONSTANTS
     */
    public static final int EE_CPR = 256;
    public static final double EE_HEIGHT_PER_TURN = 5.0379; // inches
    public static final double INCHES_PER_PULSE = EE_HEIGHT_PER_TURN / EE_CPR;
    public static final double ROLLER_SPEED = 1; // Placeholder
    public static final int INTAKE_DEGREE_PER_TICK = 360 / 512;
    //public static final double INCHES_PER_PULSE_LEFT = 72.0/7473.0; // Tick count from kop robot on left side, ports 2,3
    //public static final double INCHES_PER_PULSE_RIGHT = 72.0/3871.0; // Tick count from kop robot on right side, ports 0,1
    public static final double INCHES_PER_PULSE_LEFT = 72.0 / 4409.0;
    public static final double INCHES_PER_PULSE_RIGHT = 72.0 / 8502.0;
    public static final double kRobotRadius = 9.75;
    public static final double SPEED_LIMIT = 0.75;
    public static final double MOTOR_MAX_VOLTAGE = 9; // TODO: 2/18/16 Get appropriate current limits
    public static final double MOTOR_MIN_REENABLE_VOLTAGE = 7; // TODO: 2/18/16 Get appropriate current limits
    public static final int MAX_FULL_SPEED_TIME = 10; // Seconds
}