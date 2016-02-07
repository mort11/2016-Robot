package org.mort11.commands.auton;

import org.mort11.Robot;
import org.mort11.sensors.SensorDealer;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AdjustToGoal extends Command {
	boolean finished = false;
    public AdjustToGoal() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.adaptor.leftSide);
    	requires(Robot.adaptor.rightSide);
    	requires(Robot.adaptor.cam);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//SensorDealer.getInstance().getAHRS().zeroYaw();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.adaptor.cam.setPicture();
    	double x_pos = Robot.adaptor.cam.getX();
    	System.out.println("looping");
		double x_val =Robot.adaptor.cam.getX();
		if(x_val == -1){
			System.out.println("not found");
			finished = true;
		}
		System.out.println("Centering");
		if(x_val < 135) {
			Robot.adaptor.leftSide.set(-0.05);
			Robot.adaptor.rightSide.set(0.05);
			System.out.println("too far right");
		} else if(x_val > 185) {
			Robot.adaptor.leftSide.set(0.05);
			Robot.adaptor.rightSide.set(-0.05);
			System.out.println("too far left");
		} else {
			System.out.println("centered");
			finished = true;
		}
		Robot.adaptor.cam.setPicture();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
