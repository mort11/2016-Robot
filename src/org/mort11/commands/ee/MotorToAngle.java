package org.mort11.commands.ee;

import org.mort11.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MotorToAngle extends Command{

	double speed = .5;
	double tarAng = 90;
	
	public MotorToAngle() {
	      requires(Robot.ShootMech);
	}

	protected void initialize() {
	}

	protected void execute() {
		System.out.println("MotorToAngle is executed");
		if(Robot.ShootMech.getAngle() < tarAng){
			Robot.ShootMech.setSpeed(speed);
		}
	    if(Robot.ShootMech.getAngle() > tarAng){
		   	Robot.ShootMech.setSpeed(-speed);
		}
	    if(Robot.ShootMech.getAngle() == tarAng){
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
	