package org.mort11.commands.auton;

import org.mort11.HardwareAdaptor;
import org.mort11.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarAuton extends CommandGroup {
    
    public  LowBarAuton() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new DriveStraight(96));
    	addSequential(new TurnDegrees(false, 90));
    	Robot.adaptor.cam.setPicture();
    	while(true) {
    		System.out.println("Centering");
    		if(Robot.adaptor.cam.getX() < 135) {
    			addSequential(new TurnDegrees(true, 5));
    		} else if(Robot.adaptor.cam.getX() > 185) {
    			addSequential(new TurnDegrees(false, 5));
    		} else {
    			break;
    		}
    		Robot.adaptor.cam.setPicture();
    	}
    }
}
