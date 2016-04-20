package org.mort11.commands.auton;

import org.mort11.commands.ee.MotorToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CDF extends CommandGroup {
    
    public  CDF() {
       addParallel(new MotorToAngle(10));
       addSequential(new WaitTime(0.5));
       addSequential(new DriveStraight(40));
       addParallel(new MotorToAngle(100));
       addSequential(new DriveStraight(40));
       addParallel(new MotorToAngle(10));
       addSequential(new WaitTime(0.5));
       addSequential(new DriveStraight(40));
    }
}
