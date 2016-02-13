package org.mort11.constants;

/**
 * EndEffectorConstants - Constants for the End Effector. Uses CAN bus for IDs on final robot
 *
 * @author Matt Turi <mturi@mort11.org>
 */
public class EndEffectorConstants {
    // CAN IDs
    public static final int FLYWHEEL_TALON_ID = 6; // TODO: 2/2/16 Get port for Flywheel
    public static final int INTAKE_ARM_TALON_ID = 1;
    public static final int ROLLER_TALON_ID = 2;

    // PCM Slots
    public static final int HOOD_SOLENOID_A = 2;
    public static final int HOOD_SOLENOID_B = 3;

    // Other constants
    public static final int EE_CPR = 256;
    public static final double EE_HEIGHT_PER_TURN = 5.0379; // inches
    public static final double INCHES_PER_PULSE = EE_HEIGHT_PER_TURN / EE_CPR;
    public static final double ROLLER_SPEED = 1; // Placeholder
    public static final int INTAKE_DEGREE_PER_TICK = 360 / 512;
}