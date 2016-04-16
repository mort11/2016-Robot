package org.mort11.commands.auton;

import org.mort11.commands.ee.HoodToggle;
import org.mort11.commands.ee.MotorToAngle;
import org.mort11.commands.ee.Shoot;
import org.mort11.commands.ee.SpinUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * LowBarAuton - Does the Low bar auton
 *
 * @author Sahit Chintalapudi
 * @author Matthew Krzyzanowski
 */
public class LowBarAuton extends CommandGroup {
    public LowBarAuton() {        
          //addSequential(new HoodToggle());
          addSequential(new DriveStraight(30, 40));
          addParallel(new MotorToAngle(100));
          addSequential(new WaitTime(2));
          addSequential(new DriveStraight(62.5,40));	
          addParallel(new SpinUp(78000,true));  
          addSequential(new DriveStraight(82.5,40));
          addSequential(new TurnDegrees(false, 55)); //55
          addSequential(new DriveStraight(86, 40));
////          //addParallel(new AdjustToGoal());
//          addSequential(new WaitTime(1));
          //addSequential(new Shoot());
              	

          

    }
}
