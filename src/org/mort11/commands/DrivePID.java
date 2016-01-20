package org.mort11.commands;

import org.mort11.Robot;
import org.mort11.util.PIDLoop;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivePID extends Command {
	PIDLoop loopFunction;
	double target = 60;
	double vel = 0;
	
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
    	loopFunction = new PIDLoop(target, 0.01, 0.01);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	vel = loopFunction.getOutput(Robot.dt.getDist());
    	System.out.println("Dist: " + Robot.dt.getDist());
    	System.out.println("Vel: " + vel);
    	Robot.dt.driveSpeed(vel);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//2 inch threshold and slow
        if(Math.abs(Robot.dt.getDist()/target) > 0.98  
        		&& Math.abs(vel) < 0.35) {
        	return true;
        }
        System.out.println(Math.abs(Robot.dt.getDist()/target));
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
