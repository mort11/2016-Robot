package org.mort11.util.auto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Runs multiple commands one after another as a single command group
 *
 * @author Matt Turi
 */
public class AutoCommand extends CommandGroup {
    public AutoCommand(Command[] commands) {
        for (Command cmd : commands) {
            addSequential(cmd);
        }
    }
}
