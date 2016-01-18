package org.mort11.commands;

import org.mort11.Robot;

import util.Logger;
import util.PIDLoop;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivePID extends Command {
	PIDLoop loopFunction_left;
	PIDLoop loopFunction_right;
	double target = 60;
	double velLeft = 0;
	double velRight = 0;
	
    public DrivePID() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.dt);
    }
    
    public DrivePID(double target) {
    	this.target = target;
    	requires(Robot.dt);
    }
     

    // Called just before this Command runs the first time
    protected void initialize() {
    	loopFunction_left = new PIDLoop(target, 0.05, 0);
    	loopFunction_right = new PIDLoop(target, 0.05, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	velLeft = loopFunction_left.getOutput(Robot.dt.getDistLeft());
    	velRight = loopFunction_right.getOutput(Robot.dt.getDistRight());
    	System.out.println("Left- Distance:  " + Robot.dt.getDistLeft() + " PI: " + velLeft);
    	System.out.println("Right- Distance:  " + Robot.dt.getDistRight() + " PI: " + velRight);
    	Robot.dt.driveLeft(velLeft);
    	Robot.dt.driveRight(velRight);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//2 inch threshold and slow
        if(Math.abs(Robot.dt.getDistLeft()/target) > 0.98  
        		&& Math.abs(velLeft) < 0.35) {
        	return true;
        }
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.dt.resetEnc();
    	Robot.dt.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
