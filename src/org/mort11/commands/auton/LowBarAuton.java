package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.mort11.commands.ee.IntakeArmToAngle;
import org.mort11.commands.ee.SpoolFlywheel;

/**
 * Does the Low bar in auton
 *
 * @author Sahit Chintalapudi
 * @author Matthew Krzyzanowski
 */
public class LowBarAuton extends CommandGroup {
    public LowBarAuton() {
        addSequential(new DriveStraight(30, 40));
        addParallel(new IntakeArmToAngle(100));
        addSequential(new WaitCommand(2));
        addSequential(new DriveStraight(62.5, 40));
        addParallel(new SpoolFlywheel(78000, true));
        addSequential(new DriveStraight(82.5, 40));
        addSequential(new TurnDegrees(55));
        addSequential(new DriveStraight(86, 40));
    }
}
