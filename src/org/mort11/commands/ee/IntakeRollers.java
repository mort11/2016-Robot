package org.mort11.commands.ee;

import org.mort11.Robot;
import org.mort11.util.EEConstants;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeRollers extends Command{
	
	boolean in, out;

	public IntakeRollers(boolean in, boolean out){
		this.in = in;
		this.out = out;
		requires(Robot.intakeRollers);
	}
	
	protected void initialize() {
	}

	protected void execute() {
		 if(in == out){
		     Robot.intakeRollers.setSpeed(0);
		 }else if(in){
		     Robot.intakeRollers.setSpeed(EEConstants.ROLLER_SPEED);
		 }else{
		     Robot.intakeRollers.setSpeed(-1 * EEConstants.ROLLER_SPEED);
		 }
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}

}
