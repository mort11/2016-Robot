package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Runs {@link AdjustToGoal}
 *
 * @author Jake Shortell
 */
public class CamAuton extends CommandGroup {
    public CamAuton() {
        addSequential(new AdjustToGoal());
    }
}
