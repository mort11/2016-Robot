package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.mort11.commands.SubsystemStates;
import org.mort11.commands.ee.IntakeRollers;
import org.mort11.commands.ee.ToggleIntake;

/**
 * Dumps two balls into the courtyard
 *
 * @author Sahit Chintalapudi
 */
public class TwoBallDump extends CommandGroup {
    public TwoBallDump() {
        addSequential(new DriveStraight(160, 40));
        addSequential(new WaitCommand(1));
        addSequential(new TurnDegrees(50), 3);
        addSequential(new ToggleIntake(SubsystemStates.Intake.DOWN));
        addSequential(new IntakeRollers(2, SubsystemStates.RollerState.EXHAUST));
        addSequential(new TurnDegrees(-50), 3);
    }
}
