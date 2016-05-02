package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;

import org.mort11.Robot;
import org.mort11.commands.SubsystemStates;
import org.mort11.commands.auton.DriveStraight;
import org.mort11.subsystems.ee.Hood;
import org.mort11.subsystems.ee.PistonIntake;

/**
 * HoodToggle - Toggles the hood
 *
 * @author Matt Turi
 * @author chsahit
 */
public class ActuatePistonIntake extends Command {
	SubsystemStates.Intake state = null;
	public ActuatePistonIntake() {
	}
	
	public ActuatePistonIntake(SubsystemStates.Intake state) {
		this.state = state;
	}
    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
    	if(state == null) {
    		PistonIntake.toggleIntake();
    	}
    	else {
    		PistonIntake.setPistonIntake(state);
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
