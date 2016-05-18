package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.mort11.commands.SubsystemStates;
import org.mort11.commands.ee.IntakeArmToAngle;
import org.mort11.commands.ee.IntakeRollers;

/**
 * Oh baby a triple
 *
 * @author Sahit Chintalapudi
 */
public class LowBarLowGoal extends CommandGroup {
    public LowBarLowGoal() {
        addParallel(new IntakeArmToAngle(80));
        addSequential(new WaitCommand(3.5));
        addSequential(new DriveStraight(256));
        addSequential(new TurnDegrees(55));
        addSequential(new DriveStraight(71));
        addSequential(new DriveStraight(-61));
        addSequential(new IntakeRollers(4, SubsystemStates.RollerState.EXHAUST));
    }
}
