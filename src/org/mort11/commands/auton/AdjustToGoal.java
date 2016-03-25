package org.mort11.commands.auton;
import org.mort11.Robot;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.util.PIDLoop;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * AdjustToGoal - Adjust robot to goal using the camera
 *
 * @author Sahit Chintalapudi
 * @author Jakob Shortell
 * @author Matt Krzyzanowski
 */
public class AdjustToGoal extends Command {
    private boolean isFinished = false;
    boolean loopStarted = false;
    private DTSide left = Robot.adaptor.leftSide;	
    private DTSide right = Robot.adaptor.rightSide;
    private double area = 0;
    private int curr_index = 0,target_index = 0;
    PIDLoop pid_turn;
    double netError;
    Timer timer;
    double thistime = 0,lasttime = 0;
    private double angle,range,pxPer6,x_val,theta;
    private double output,curr_angle,output_speed = 0.5;
    double error;
    private boolean debug = false;
    public AdjustToGoal() {
        requires(left);
        requires(right);
        pid_turn = new PIDLoop(160, 0.006, 0.004,35);
        timer = new Timer();
        setInterruptible(true);
    }
    public AdjustToGoal(boolean debug) {
    	this();
    	this.debug = debug;
    }
    protected void initialize() {
    	this.left.set(0);
    	this.right.set(0);
    	netError = 0;
    	System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
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
        theta *= -2;
//        if(theta > 0) {
//        	theta += 3;
//        } else if (theta < 0) {
//        	theta -=3;
//        }
        System.out.println("will turn: " + theta);
        left.setDistToTurn(theta);
        Robot.adaptor.ahrs.zeroYaw();
        if(Math.abs(theta) < 15) {
        	output_speed = 0.3;
        }
        timer.start();
    }
    protected void execute() {  
    	//TODO: take advantage of PID class
        //double x_val = Robot.table.getNumberArray("centerX", new double[]{})[target_index];
        //double y_val = Robot.table.getNumberArray("centerY", new double[]{})[target_index];
    	curr_angle = Robot.adaptor.ahrs.getYaw();
    	if(curr_angle > 200) {
    		curr_angle = curr_angle - 360;
    	}
    	if(debug) {
    		if(curr_angle > theta) {
    			System.out.println("turning left");
    			right.set(output_speed);
    			left.set(-output_speed);
    		} else if(curr_angle < theta) {
    			System.out.println("turning right");
    			right.set(-output_speed);
    			left.set(output_speed);
    		}
    		if(curr_angle > theta * 0.95 && curr_angle < theta * 1.05){
    			System.out.println("finished");
    			this.isFinished = true;
    		}
    		return;
    	}
    	if(!loopStarted && curr_angle != 0) {
    		System.out.println("Still Zero!");
    		return;
    	}
    	loopStarted = true;    	
    	thistime = timer.get();
    	error = theta - curr_angle; 
    	netError += (error * (thistime - lasttime));
    	lasttime = thistime;
    	//output = (error* 0.16) + (netError * 0.1);
    	output = (error* 0.09) + (netError * 0.13);
    	if (output > 0.5) {
    		output = 0.5;
    	} else if (output < -0.5) {
    		output = -0.5;
    	}
    	if(!debug) {
    		left.set(output);
    		right.set(-output);
    	} else {
    		this.isFinished = true;
    	}
        //System.out.println("Centering"); 
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
        
        //System.out.println("error " + error);
        //System.out.println("output: " + output);
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
    	//left.set(output);
    	//right.set(-output);
       System.out.println("pos: " + curr_angle + " error: " + error + 
    		   " P output " + (error * 0.09) + " I output " + (netError * 0.1) 
    		    + " netErr " + netError +" output: " + output + " ");
       System.out.println("ATG output: " + output);
    }
    protected boolean isFinished() {
//    	return (Math.abs(distance - currentDistanceLeft) < 8 &&
    	System.out.println("time: " + timer.get());
    	return timer.get() > 1.5;
    	//return ((Math.abs(error) < 0.1 && loopStarted)) || (debug && this.isFinished);
    	//return false;
    }
    protected void end() {
    	this.isFinished = false;
    	netError = 0;
    	this.left.set(0);
    	this.right.set(0);
    	
    }
    protected void interrupted() {
    }
}