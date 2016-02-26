package org.mort11.commands.led;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.mort11.commands.SubsystemStates;

/**
 * Pulse front LED strip every 1 second in a rainbow
 *
 * @author Jakob Shortell
 * @author Matt Turi
 */
public class Rainbow extends CommandGroup {
    public Rainbow() {
        addSequential(new LEDControl(SubsystemStates.Light.RED, 1));
        addSequential(new LEDControl(SubsystemStates.Light.ORANGE, 1));
        addSequential(new LEDControl(SubsystemStates.Light.YELLOW, 1));
        addSequential(new LEDControl(SubsystemStates.Light.GREEN, 1));
        addSequential(new LEDControl(SubsystemStates.Light.BLUE, 1));
        addSequential(new LEDControl(SubsystemStates.Light.PURPLE, 1));
    }
}
