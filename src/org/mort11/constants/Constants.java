package org.mort11.constants;

/**
 * All the robot constants
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
    public static final int HOOD_SOLENOID_A = 6;
    public static final int HOOD_SOLENOID_B = 7;

    public static final int INTAKE_SOLENOID_A = 2;
    public static final int INTAKE_SOLENOID_B = 3;
    
    public static final int INDEXERS_SOLENOID_A = 4;
    public static final int INDEXERS_SOLENOID_B = 5;

    /**
     * DS Joystick ports
     */
    public static final int LEFT_JOYSTICK = 0;
    public static final int RIGHT_JOYSTICK = 1;
    public static final int EE_JOYSTICK = 2;

    /**
     * Joystick Buttons
     */
    //shifting on DT Sticks
    public static final int SHIFT_UP_BUTTON = 7;     // Right Drive stick
    public static final int SHIFT_DOWN_BUTTON = 7;    // Left Joystick

    // EE Stick
    public static final int INTAKE_BUTTON = 3;
    public static final int OUTTAKE_BUTTON = 5; 
    
    public static final int SPIN_UP_BUTTON = 11;
    public static final int STOP_FLYWHEEL = 9;
    public static final int TOGGLE_HOOD = 2;

    public static final int ARM_TO_90 = 12;
    public static final int ARM_TO_0 = 10;


    /**
     * Sensor ports
     */
    // DT Left
    public static final int DT_ENCODER_LEFT_A = 0;
    public static final int DT_ENCODER_LEFT_B = 1;

    // DT Right
    public static final int DT_ENCODER_RIGHT_A = 4;
    public static final int DT_ENCODER_RIGHT_B = 5;

    /** ============================================================================================================= */

    /**
     * MATH AND OTHER CONSTANTS
     */
    public static final double ROLLER_SPEED = 0.7; // Placeholder
    public static final double INCHES_PER_PULSE_LEFT = 72.0 / 4409.0;
    public static final double INCHES_PER_PULSE_RIGHT = 72.0 / 8502.0;
}