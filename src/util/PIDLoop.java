package util;

import edu.wpi.first.wpilibj.Timer;

public class PIDLoop {
	double target;
	double kP,kI,kAlly; //PID control constants
	double netError = 0;
	boolean isNear = false; //whether we can shift from P to I, will tell us
							//when we can start the timer
	double currTime = 0,oldTime = 0;
	Timer timer = new Timer();
	public PIDLoop(double target,double kP, double kI) {
		this.target = target;
		this.kP = kP;
		this.kI = kI;
	}
	
	public double getOutput(double  pos){
		double error = target - pos;
		//shift to I
		if (Math.abs(error/pos) < 0.2){
			System.out.println("I loop");
			System.out.println("Error: " + error);
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

}
