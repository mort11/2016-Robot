package org.mort11.commands.auton;

import org.mort11.commands.ee.MotorToAngle;
import org.mort11.commands.ee.Shoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Pos3Auton extends CommandGroup {
    
    public  Pos3Auton() {
    	 addParallel(new MotorToAngle(90));
         addSequential(new WaitTime(1.5));
    	 addSequential(new DriveStraight(60, 30));
         addSequential(new TurnDegrees(false, 50));
         addSequential(new DriveStraight(20, 34));
         addSequential(new TurnDegrees(true, 50));
         addParallel(new AdjustToGoal());
         addSequential(new WaitTime(1));
         addSequential(new Shoot());
    }
}
