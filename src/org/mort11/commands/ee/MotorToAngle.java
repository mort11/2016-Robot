package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.subsystems.ee.Shooter;

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
	    


/**
 * MotorToAngle - Move motor to angle
 *
 * @author Ryan Thant <ryanthant1@gmail.com>
 * @author Seven Kurt <seven.kurt@motsd.org>
 * @author Ryan O'Toole <ryan.otoole@motsd.org>
 */

	