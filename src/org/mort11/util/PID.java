package org.mort11.util;

public class PID{
	
	private double setpoint, kP, kI, kD, accumError = 0;
	private double prevTime = -1;
	private double deltaTime = 0;

    public PID( double setpoint, double kP, double kI, double kD ) {
    	this.setpoint = setpoint;
    	this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }
    
    public double getOut(double measuredValue){
    	double error = setpoint - measuredValue;
    	double currTime = System.currentTimeMillis();
    	
    	accumError += error;
    	
		return kP * error;
    	
    }

}
