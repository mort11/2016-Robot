package org.mort11.commands.auton;

import org.mort11.commands.SubsystemStates.RollerRequest;
import org.mort11.commands.ee.HoodToggle;
import org.mort11.commands.ee.IntakeRollers;
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
    	addParallel(new MotorToAngle(90));
        addSequential(new WaitTime(0.5));
        addSequential(new DriveStraight(103,30));
        addParallel(new SpinUp());
        addSequential(new DriveStraight(56));
        addSequential(new TurnDegrees(false, 57));
        addSequential(new DriveStraight(39,32));
        addSequential(new Shoot());
        
//        addParallel(new WaitTime(3));
//        addSequential(new HoodToggle());
//        addSequential(new HoodToggle());
//        addParallel(new SpinUp(98000,true));
//        addSequential(new WaitTime(5));
//        addSequential(new IntakeRollers(1, RollerRequest.INTAKE));
    }
}
