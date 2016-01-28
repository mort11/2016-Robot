package org.mort11.commands.ee;

import org.mort11.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MotorToAngle extends Command{
	
	public MotorToAngle() {
	      requires(Robot.ShootMech);
	}

	protected void initialize() {
	}

	protected void execute() {
		if(Robot.ShootMech.getAngle() <= 90){
			Robot.ShootMech.setSpeed(.5);
		}else{
		    Robot.ShootMech.setSpeed(0);
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
