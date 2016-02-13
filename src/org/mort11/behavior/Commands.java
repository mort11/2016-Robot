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
}
