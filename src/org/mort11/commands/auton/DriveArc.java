package org.mort11.commands.auton;

import org.mort11.Robot;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.util.DTConstants;
import org.mort11.util.PIDLoop;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveArc extends Command {	
	private DTSide left = Robot.leftSide;
	private DTSide right = Robot.rightSide;
	double arcLength,turnRadius;
	PIDLoop pidLeft;
	PIDLoop pidRight;
    public DriveArc(double arclength, double turnRadius) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.leftSide);
    	requires(Robot.rightSide);
    	this.arcLength = arclength;
    	this.turnRadius = turnRadius;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	double[] dists = arc_calc(arcLength, turnRadius);
    	pidRight = new PIDLoop(dists[0], 0.01, 0.00, dists[0]/Math.max(dists[0], dists[1]));
    	pidLeft = new PIDLoop(dists[1], 0.01, 0.00, dists[1]/Math.max(dists[0], dists[1]));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	right.setSpeed(pidRight.getOutput(right.getDist()));
    	left.setSpeed(pidLeft.getOutput(left.getDist()));
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
    
    private double[] arc_calc(double arc_length, double theta) {
    	double centerRadius = Math.abs(arc_length/theta);
    	double right_radius = centerRadius - 
    			DTConstants.kRobotRadius * Math.signum(arc_length);
    	double left_radius = centerRadius + 
    			DTConstants.kRobotRadius * Math.signum(arc_length);
    	return (new double[]{right_radius * theta,left_radius*theta});
    }
}
