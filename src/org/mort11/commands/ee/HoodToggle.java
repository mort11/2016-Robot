package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;

import org.mort11.commands.SubsystemStates;
import org.mort11.commands.auton.DriveStraight;
import org.mort11.subsystems.ee.Hood;

/**
 * HoodToggle - Toggles the hood
 *
 * @author Matt Turi
 */
public class HoodToggle extends Command {
	SubsystemStates.HoodRequest state = null;
	public HoodToggle() {
		
	}
	
	public HoodToggle(SubsystemStates.HoodRequest state) {
		this.state = state;
	}
    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
    	System.out.println("toggling");
    	if(state == null) {
    		Hood.toggleHood();
    	}
    	else {
    		Hood.setHood(state);
    	}
    }
    

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {
    }
}
