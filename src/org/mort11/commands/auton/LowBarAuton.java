package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.mort11.commands.ee.DropIntake;
import org.mort11.commands.ee.HoodToggle;
import org.mort11.commands.ee.MotorToAngle;
import org.mort11.commands.ee.Shoot;
import org.mort11.commands.ee.SpinUp;

/**
 * LowBarAuton - Does the Low bar auton
 *
 * @author Sahit Chintalapudi
 * @author Matthew Krzyzanowski
 */
public class LowBarAuton extends CommandGroup {
    public LowBarAuton() {        
          addSequential(new HoodToggle());
          addSequential(new DriveStraight(30, 40));          
          addSequential(new DriveStraight(82.5,40));	
          addParallel(new SpinUp());
          addSequential(new DriveStraight(68,40));
          addParallel(new MotorToAngle(100));
          addSequential(new WaitTime(2));
          addSequential(new TurnDegrees(false, 55));
          addSequential(new DriveStraight(36, 40));
          addParallel(new AdjustToGoal());
          addSequential(new WaitTime(1));
          addSequential(new Shoot());

//        addParallel(new WaitTime(3));
//        addSequential(new HoodToggle());
//        addSequential(new HoodToggle());
//        addParallel(new SpinUp(98000,true));
//        addSequential(new WaitTime(5));
//        addSequential(new IntakeRollers(1, RollerRequest.INTAKE));
    }
}
