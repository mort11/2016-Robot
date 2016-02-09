package org.mort11.constants;

/**
 * EndEffectorConstants - Constants for the Drivetrain. Uses CAN bus for IDs on final robot
 *
 * @author Matt Turi <mturi@mort11.org>
 */
public class EndEffectorConstants {
    public static final int ARM_TALON_PORT = 0; // TODO: 2/2/16 Get port for Arm talon
    public static final int ARM_TALL = 10; // TODO: 2/2/16 Fix variable names and get proper ports

    public static final int ROLLER_TALON_PORT = 7;

    public static final int EE_CPR = 256;
    public static final double EE_HEIGHT_PER_TURN = 5.0379; // inches
    public static final double INCHES_PER_PULSE = EE_HEIGHT_PER_TURN / EE_CPR;
    public static final double ROLLER_SPEED = 0.5; // Placeholder
    public static final int ROLLER_CPR = 512;
    
}