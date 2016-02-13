package org.mort11.behavior;

/**
 * Commands - ToDo class description
 *
 * @author Matt Turi <mturi@mort11.org>
 * @author Jakob Shortell <jshortell@mort11.org>
 */

	
public class Commands {
	// Positions for the hood
    public enum HoodRequest { 
        NONE, POP, STOW, DISABLED
    }

    // Motor state for roller
    public enum RollerRequest {
        NONE, INTAKE, EXHAUST, DISABLED
    }

    // Preset angles for the Arm
    public enum IntakeArmPresets {
        NONE, STORED, DOWN, GROUND, MANUAL_CTRL, DISABLED
    }

    // Motor state for the fly wheel
    public enum FlywheelRequest {
        NONE, STOP, START, DISABLED
    }
}
