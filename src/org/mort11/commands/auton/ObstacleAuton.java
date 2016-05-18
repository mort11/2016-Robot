package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.mort11.commands.ee.IntakeArmToAngle;

public class ObstacleAuton extends CommandGroup {
    public ObstacleAuton() {
        addSequential(new DriveStraight(30, 45));
        addParallel(new IntakeArmToAngle(50));
        addSequential(new WaitCommand(2));
        addSequential(new DriveStraight(130, 55));
    }
}
