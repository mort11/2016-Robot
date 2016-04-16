package org.mort11.commands.auton;

import org.mort11.commands.ee.MotorToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ObstacleAuton extends CommandGroup {
    
    public  ObstacleAuton() {
    	addSequential(new DriveStraight(30, 45));
        addParallel(new MotorToAngle(100));
        addSequential(new DriveStraight(160, 50));
    }
}
