package org.mort11.commands.auton;

import org.mort11.Robot;
import org.mort11.subsystems.dt.DTSide;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForwardToGoal extends Command {

	private boolean isFinished = false;
    private DTSide left = Robot.adaptor.leftSide;
    private DTSide right = Robot.adaptor.rightSide;
       
    public DriveForwardToGoal() {
    	requires(left);
        requires(right);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (Robot.table.getNumberArray("area", new double[]{}).length == 0) {
            System.out.println("Not found");
            this.isFinished = true;
            return;
        }
        
        if(Robot.table.getNumberArray("area", new double[]{})[0] < 6000){
        	System.out.println("going forward");
        	left.set(.25);
        	right.set(.25);
        }else if(Robot.table.getNumberArray("area", new double[]{})[0] > 6500){
        	System.out.println("going back");
        	left.set(-.25);
        	right.set(-.25);
        } else {
            System.out.println("Centered");
            this.isFinished = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
