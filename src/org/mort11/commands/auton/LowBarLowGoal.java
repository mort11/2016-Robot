package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.mort11.behavior.Commands;
import org.mort11.commands.ee.IntakeRollers;

/**
 * LowBarLowGoal - Oh baby a triple
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class LowBarLowGoal extends CommandGroup {
    public LowBarLowGoal() {
        addSequential(new DriveStraight(120));
        addSequential(new TurnDegrees(false, 30));
        addSequential(new DriveStraight(30));
        addSequential(new IntakeRollers(1, Commands.RollerRequest.EXHAUST));
    }
}
