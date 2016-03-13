package org.mort11.commands.auton;

import org.mort11.Robot;
import org.mort11.subsystems.dt.DTSide;

import edu.wpi.first.wpilibj.command.Command;

/**
 * DriveForwardToGoal - Drive up to goal using camera
 *
 * @author Jakob Shortell
 * @author Matt Krzyzanowski
 */
public class DriveForwardToGoal extends Command {

	private boolean isFinished = false;
    private DTSide left = Robot.adaptor.leftSide;
    private DTSide right = Robot.adaptor.rightSide;
       
    public DriveForwardToGoal() {
    	requires(left);
        requires(right);
    }

    protected void initialize() {
    }

    protected void execute() {
        if (Robot.table.getNumberArray("area", new double[]{}).length == 0) {
            System.out.println("Not found");
            this.isFinished = true;
            return;
        }
        
        if(Robot.table.getNumberArray("area", new double[]{})[0] < 1400){
        	System.out.println("going forward");
        	left.set(.25);
        	right.set(.25);
        }else if(Robot.table.getNumberArray("area", new double[]{})[0] > 1800){
        	System.out.println("going back");
        	left.set(-.25);
        	right.set(-.25);
        } else {
            System.out.println("Centered");
            this.isFinished = true;
        }
    }

    protected boolean isFinished() {
    	return isFinished;
    }

    protected void end() {
    	left.set(0);
    	right.set(0);
    }

    protected void interrupted() {
    }
}
