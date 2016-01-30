package org.mort11.util;

import edu.wpi.first.wpilibj.Timer;

public class PIDLoop {
	double desired_target;
	double kP,kI,kAlly; //PID control constants
	double netError = 0;
	boolean isNear = false; //whether we can shift from P to I, will tell us
							//when we can start the timer
	double currTime = 0,oldTime = 0;
	Timer timer = new Timer();
	double vel_max = 24;
	double curr_location = 0;
	public PIDLoop(double target,double kP, double kI) {
		this.desired_target = target;
		this.kP = kP;
		this.kI = kI;
	}
	
	
	public double getOutput_notStaggered(double  pos){
		double error = desired_target - pos;
		//shift to I
		if (Math.abs(error/pos) < 0.2){
//			System.out.println("I loop");
//			System.out.println("Error: " + error);
			if (!isNear) {
				timer.start();
				isNear = true;
			}
			currTime = timer.get();
			double deltaT = currTime - oldTime;
			netError += error * deltaT;
			oldTime = currTime;
			return netError * kI;
		}
		//P loop
		else {
			return error * kP;
		}
	}
	
	public double getOutput(double curr_location) {
		if(!isNear) {
			timer.start();
			isNear = true;
		}
		this.curr_location = curr_location;
		currTime = timer.get();
		double error = getLocation(currTime,curr_location) - curr_location;
		double deltaT = currTime - oldTime;
		netError += error * deltaT;
		oldTime = currTime;
		double output = (error * kP + netError * kI);
//		System.out.println("PI: " + output);
		System.out.println("SP: " + getLocation(currTime,curr_location));
		//System.out.println("Time: " + currTime);
		return output;
	}
	
	public double getSP() {
		return getLocation(currTime,curr_location);
	}
	
	public double getLocation(double time,double pos) {
		if(time > desired_target/vel_max) {
			return desired_target;
		}
		
		return vel_max * time;
	}
	
	

}
