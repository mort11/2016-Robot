package org.mort11.commands.auton;
import edu.wpi.first.wpilibj.command.Command;

import org.mort11.HardwareAdaptor;
import org.mort11.Robot;
import org.mort11.subsystems.Camera;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.util.PIDLoop;

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
    PIDLoop pid_turn;
    private double angle,range,pxPer6,x_val,theta;
    public AdjustToGoal() {
        requires(left);
        requires(right);
        pid_turn = new PIDLoop(180, 0.01, 0,15);
    }
    protected void initialize() {
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
        x_val =Robot.table.getNumberArray("centerX",new double[]{})[target_index];
        double y_val =Robot.table.getNumberArray("centerY",new double[]{})[target_index];
        range = (y_val + 68.05)/1.475;
        pxPer6 = -0.7083*range+ 104.75;
        theta = Math.atan2((((153 - x_val)/pxPer6) * 6),range) * 180/Math.PI;
        System.out.println("will turn: " + theta);
    }
    protected void execute() {    	        
        System.out.println("Centering"); 
//        if (Robot.table.getNumberArray("centerX", new double[]{})[target_index] < 158) {
//            this.left.set(-0.25);
//            this.right.set(0.25);
//            System.out.println("Too far right");
//        } else if (Robot.table.getNumberArray("centerX", new double[]{})[target_index] > 162) {
//            this.left.set(0.25);
//            this.right.set(-0.25);
//            System.out.println("Too far left");
//        } else {
//            System.out.println("Centered");
//            this.isFinished = true;
//        }
//        left.set(-pid_turn.getOutput(
//        		Robot.table.getNumberArray("centerX", new double[]{})[target_index]));
//        right.set(pid_turn.getOutput(
//        		Robot.table.getNumberArray("centerX", new double[]{})[target_index]));
        
        System.out.println("current angle");
    }
    protected boolean isFinished() {
        return Math.abs(pid_turn.getOutput(
        		Robot.table.getNumberArray("centerX", new double[]{})[target_index])) < 0.01;
    }
    protected void end() {
    	this.isFinished = false;
    	this.left.set(0);
    	this.right.set(0);
    }
    protected void interrupted() {
    }
}