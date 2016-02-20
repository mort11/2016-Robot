package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.mort11.commands.SubsystemStates;
import org.mort11.commands.ee.IntakeRollers;
import org.mort11.commands.ee.MotorToAngle;

/**
 * LowBarLowGoal - Oh baby a triple
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class LowBarLowGoal extends CommandGroup {
    public LowBarLowGoal() {
        addParallel(new MotorToAngle(90));
        addSequential(new WaitTime(3.5));
        addSequential(new DriveStraight(120));
        addSequential(new TurnDegrees(false, 30));
        addSequential(new DriveStraight(30));
        addSequential(new IntakeRollers(1, SubsystemStates.RollerRequest.EXHAUST));
    }
}
