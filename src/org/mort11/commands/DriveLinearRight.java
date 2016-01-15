package org.mort11.commands;

import edu.wpi.first.wpilibj.command.Command;
import static org.mort11.Robot.rightSide;
import static org.mort11.Robot.oi;

/**
 *
 */
public class DriveLinearRight extends DriveLinear {
	
    public DriveLinearRight() {
    	super(rightSide);
    	
    	
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    protected double getSpeed(){
    	return oi.getRightJoy();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
