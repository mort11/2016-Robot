package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.mort11.commands.ee.IntakeArmToAngle;

/**
 * Crosses the Cheval de Frise
 *
 * @author Sahit Chintalapudi
 */
public class CDF extends CommandGroup {
    public CDF() {
        addParallel(new IntakeArmToAngle(10));
        addSequential(new WaitCommand(0.5));
        addSequential(new DriveStraight(40));
        addParallel(new IntakeArmToAngle(100));
        addSequential(new DriveStraight(40));
        addParallel(new IntakeArmToAngle(10));
        addSequential(new WaitCommand(0.5));
        addSequential(new DriveStraight(40));
    }
}
