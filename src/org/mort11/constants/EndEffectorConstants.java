package org.mort11.constants;

/**
 * EndEffectorConstants - Constants for the Drivetrain. Uses CAN bus for IDs on final robot
 *
 * @author Matt Turi <mturi@mort11.org>
 */
public class EndEffectorConstants {
    public static final int ARM_TALON_PORT = 1; // TODO: 2/2/16 Get port for Arm talon
    //public static final int ARM_TALL = 10; // TODO: 2/2/16 Fix variable names and get proper ports
    public static final int SHOOTER_PORT = 6;
    public static final int ROLLER_TALON_PORT = 2;

    public static final int EE_CPR = 256;
    public static final double EE_HEIGHT_PER_TURN = 5.0379; // inches
    public static final double INCHES_PER_PULSE = EE_HEIGHT_PER_TURN / EE_CPR;
    public static final double ROLLER_SPEED = 1; // Placeholder
    
    public static final int ROLLER_DEGREE_PER_TICK = 360/512;
    
}