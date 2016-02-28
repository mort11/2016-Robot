package org.mort11.commands.auton;

import org.mort11.commands.ee.MotorToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Pos4Auton extends CommandGroup {
    
    public  Pos4Auton() {
        addParallel(new MotorToAngle(90));
        addSequential(new WaitTime(3.5));
        addSequential(new DriveStraight(60));
//        addSequential(new TurnDegrees(false, 15));
//        addSequential(new DriveStraight(20));
//        addSequential(new TurnDegrees(true, 15));
        
    }
}
