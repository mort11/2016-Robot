package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.mort11.commands.SubsystemStates;
import org.mort11.commands.ee.IntakeRollers;
import org.mort11.commands.ee.MotorToAngle;

/**
 * LowBarLowGoal - Oh baby a triple
 *
 * @author Sahit Chintalapudi
 */
public class LowBarLowGoal extends CommandGroup {
    public LowBarLowGoal() {
        addParallel(new MotorToAngle(80));
        addSequential(new WaitTime(3.5));
        addSequential(new DriveStraight(256));
        addSequential(new TurnDegrees(false, 55));
        addSequential(new DriveStraight(71));
        addSequential(new DriveStraight(-61));
        addSequential(new IntakeRollers(4, SubsystemStates.RollerRequest.EXHAUST));
    }
}
