	 package org.mort11.commands.ee;

import org.mort11.Robot;
import org.mort11.constants.EndEffectorConstants;

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
		     Robot.intakeRollers.setSpeed(EndEffectorConstants.ROLLER_SPEED);
		 }else{
		     Robot.intakeRollers.setSpeed(-1 * EndEffectorConstants.ROLLER_SPEED);
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
