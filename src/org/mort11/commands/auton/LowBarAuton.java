package org.mort11.commands.auton;

import org.mort11.HardwareAdaptor;
import org.mort11.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * LowBarAuton - Code for execution during auton
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class LowBarAuton extends CommandGroup {
    
    public  LowBarAuton() {
     	System.out.println("starting low bar auton");
    	addSequential(new DriveStraight(96));
    	addSequential(new TurnDegrees(false,90));
    	addSequential(new AdjustToGoal());
    	
    }
}
