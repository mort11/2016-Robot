package org.mort11.commands.auton;

import org.mort11.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GhettoDriveStraight extends Command {
	double time;
	Timer timer;
    public GhettoDriveStraight(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.adaptor.leftSide);
    	requires(Robot.adaptor.rightSide);
    	this.time = time;
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.adaptor.leftSide.set(0.5);
    	Robot.adaptor.rightSide.set(0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() > time;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
