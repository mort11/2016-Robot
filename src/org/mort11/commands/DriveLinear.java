package org.mort11.commands;

import org.mort11.subsystems.DTSide;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public abstract class DriveLinear extends Command {
	protected DTSide side;
	
    public DriveLinear(DTSide drivetrain) {
    	requires(drivetrain);
    	setInterruptible(true);
    	side = drivetrain;
    	
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    protected abstract double getSpeed();
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	side.getSpeed();
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
