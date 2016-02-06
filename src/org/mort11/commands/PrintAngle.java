package org.mort11.commands;

import org.mort11.sensors.SensorDealer;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PrintAngle extends Command {

    public PrintAngle() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SensorDealer.getInstance().getAHRS().zeroYaw();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(SensorDealer.getInstance().getAHRS().getYaw());
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
