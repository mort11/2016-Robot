package org.mort11.commands;

import org.mort11.Robot;
import org.mort11.subsystems.DTSide;

import util.PIDLoop;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {

	private double driveDistance;
	private DTSide left = Robot.leftSide;
	private DTSide right = Robot.rightSide;
	private Timer timer;
	private PIDLoop pd;
	private double curDist;
	private double speed;
	
    public DriveStraight() {
       requires(left);
       requires(right);
       timer = new Timer();
       pd = new PIDLoop(driveDistance, .01, 0);
       curDist = DTSide.getDist();
       speed = pd.getOutput(curDist);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DTSide.resetEnc();
    	timer.start();
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	DTSide.setSpeed(speed);
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	DTSide.resetEnc();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
