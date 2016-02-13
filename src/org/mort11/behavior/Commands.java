package org.mort11.behavior;

/**
 * Commands - ToDo class description
 *
 * @author Matt Turi <mturi@mort11.org>
 */
public class Commands {
    public enum HoodRequest {
        NONE, POP, STOW, DISABLED
    }

    public enum RollerRequest {
        NONE, INTAKE, EXHAUST, DISABLED
    }

    public enum IntakeArmPresets {
        NONE, STORED, DOWN, GROUND, MANUAL_CTRL, DISABLED
    }

    public enum FlywheelRequest {
        NONE, STOP, START, DISABLED
    }
}
