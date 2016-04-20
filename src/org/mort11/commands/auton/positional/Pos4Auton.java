package org.mort11.commands.auton.positional;

import org.mort11.commands.auton.DriveStraight;
import org.mort11.commands.auton.TurnDegrees;
import org.mort11.commands.auton.WaitTime;
import org.mort11.commands.ee.MotorToAngle;
import org.mort11.commands.ee.Shoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Pos4Auton extends CommandGroup {
    
    public  Pos4Auton() {
    	addSequential(new DriveStraight(30, 45));
    	 addParallel(new MotorToAngle(50));
         addSequential(new WaitTime(0.5));
    	 addSequential(new DriveStraight(100, 30));
         addSequential(new TurnDegrees(false, 30),3);
         addSequential(new DriveStraight(40, 34));
         addSequential(new TurnDegrees(true, 30),3);
         addSequential(new DriveStraight(100, 50));
//         addParallel(new AdjustToGoal());
//         addSequential(new WaitTime(1));
        //addSequential(new Shoot());
    }
}
