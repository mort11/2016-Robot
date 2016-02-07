package org.mort11.commands.auton;

import org.mort11.HardwareAdaptor;
import org.mort11.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarAuton extends CommandGroup {
    
    public  LowBarAuton() {
     	System.out.println("starting low bar auton");
    	addSequential(new DriveStraight(96));
    	addSequential(new TurnDegrees(90));
    	addSequential(new AdjustToGoal());
    	
    }
}
