package org.mort11.commands;

/**
 * Defines all possible states a given subsystem can be in
 *
 * @author Matt Turi
 * @author Jakob Shortell
 */
public class SubsystemStates {
    public enum HoodState {
        POP, STOW
    }

    public enum IndexersState {
        DOWN, UP
    }

    public enum RollerState {
        INTAKE, EXHAUST, STOP
    }

    public enum Gear {
        LOW, HIGH
    }

    public enum Intake {
        UP, DOWN
    }
}
