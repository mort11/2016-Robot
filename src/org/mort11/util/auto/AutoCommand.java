package org.mort11.util.auto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * AutoCommand - Todo file description
 *
 * @author Matt Turi <mturi@mort11.org>
 */
public class AutoCommand extends CommandGroup {
    public AutoCommand(Command[] commands) {
        for (Command cmd : commands) {
            addSequential(cmd);
        }
    }
}
