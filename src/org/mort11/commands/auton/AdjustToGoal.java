package org.mort11.commands.auton;
import edu.wpi.first.wpilibj.command.Command;
import org.mort11.HardwareAdaptor;
import org.mort11.Robot;
import org.mort11.subsystems.Camera;
import org.mort11.subsystems.dt.DTSide;

/**
 * AdjustToGoal - Adjust robot to goal using the camera
 *
 * @author Sahit Chintalapudi
 * @author Jakob Shortell
 * @author Matt Krzyzanowski
 */
public class AdjustToGoal extends Command {
    private boolean isFinished = false;
    private DTSide left = Robot.adaptor.leftSide;
    private DTSide right = Robot.adaptor.rightSide;
    private double area = 0;
    private int curr_index = 0,target_index = 0;
    public AdjustToGoal() {
        requires(left);
        requires(right);
    }
    protected void initialize() {
        
    }
    protected void execute() {    	
        if (Robot.table.getNumberArray("centerX", new double[]{}).length == 0) {
            System.out.println("Not found in ATG");
            this.isFinished = true;
            return;
        }
        area = 0; target_index = 0; curr_index = 0;
        for (double i: Robot.table.getNumberArray("area",new double[]{})) {
        	if(i > area) {
        		area = i;
        		target_index = curr_index;
        	}
        	curr_index++;
        }        
        System.out.println("Centering"); 
        if (Robot.table.getNumberArray("centerX", new double[]{})[target_index] < 158) {
            this.left.set(-0.25);
            this.right.set(0.25);
            System.out.println("Too far right");
        } else if (Robot.table.getNumberArray("centerX", new double[]{})[target_index] > 162) {
            this.left.set(0.25);
            this.right.set(-0.25);
            System.out.println("Too far left");
        } else {
            System.out.println("Centered");
            this.isFinished = true;
        }
    }
    protected boolean isFinished() {
        return this.isFinished;
    }
    protected void end() {
    	this.isFinished = false;
    }
    protected void interrupted() {
    }
}