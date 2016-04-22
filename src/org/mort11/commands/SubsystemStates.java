package org.mort11.commands;

/**
 * SubsystemStates - Defines all possible states a given subsystem can be in
 *
 * @author Matt Turi
 * @author Jakob Shortell
 */

public class SubsystemStates {
    // Positions for the hood
    public enum HoodRequest {
        POP, STOW
    }

    // Motor state for roller
    public enum RollerRequest {
        INTAKE, EXHAUST, STOP
    }

    // Preset angles for the Arm
    public enum IntakeArmPresets {
        STORED, DOWN, GROUND, MANUAL_CTRL, STOP
    }

    // Motor state for the fly wheel
    public enum FlywheelRequest {
        STOP, START
    }

    public enum Gear {
        LOW, HIGH
    }

    public enum Light {
        RED, ORANGE, YELLOW, GREEN, BLUE, PURPLE
    }
    
    public enum Intake {
    	UP, DOWN
    }
}
