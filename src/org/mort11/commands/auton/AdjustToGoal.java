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
    private double output;
    public AdjustToGoal() {
        requires(left);
        requires(right);
        pid_turn = new PIDLoop(160, 0.006, 0.004,35);
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
        double x_val = Robot.table.getNumberArray("centerX", new double[]{})[target_index];
        double y_val = Robot.table.getNumberArray("centerY", new double[]{})[target_index];
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
//       System.out.println("x " + (/*180 -*/ x_val));
//       System.out.println("y " + y_val);
//       System.out.println("area " + area);
//       output = pid_turn.getOutput(x_val);
//       if (output > 0.4) {
//    	   output = 0.4;
//       } else if (output < -0.4) {
//    	   
//    	   output = -0.4;
//       }
//       //left.set(output);
//       //right.set(-output);
//       System.out.println("output: " + output);
    }
    protected boolean isFinished() {
//    	try {
//    		return Math.abs(pid_turn.getOutput(
//    			Robot.table.getNumberArray("centerX", new double[]{})[target_index])) < 0.05;
//    	} catch (Exception e) {
//    		return this.isFinished;
//    	}
    	return false;
    }
    protected void end() {
    	this.isFinished = false;
    	this.left.set(0);
    	this.right.set(0);
    }
    protected void interrupted() {
    }
}