package org.mort11.commands;

import org.mort11.Robot;
import org.mort11.subsystems.DTLeft;
import org.mort11.subsystems.DTSide;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import util.PIDLoop;


public class TurnDegrees extends Command {
	
	//	Timer timer;
	private PIDLoop pd;
	private double speed;
	private double angle;
	private double curAngle; //need way to get angle of robot

    public TurnDegrees(double angle) { //takes desired angle for turning (between -180 and 180)
    	this.angle = angle;
    	requires(Robot.leftSide);
        requires(Robot.rightSide);
        pd = new PIDLoop(angle, .01, 0);
    }

    protected void initialize() {

    }

    protected void execute() {
    	speed = pd.getOutput(Robot.leftSide.getAngle());
    	Robot.leftSide.setSpeed(speed);
    	Robot.rightSide.setSpeed(-speed);
    	SmartDashboard.putNumber("Raw PWM Value", DTSide.getCurent()); //gets raw PWM value from hardware,between 0-255, unsure of what value means
    	SmartDashboard.putNumber("PWM Value", DTSide.getSpeed()); //get most recently set PWM value, between -1.0 and 1.0
    }

    protected boolean isFinished() {
        return this.inTresh();
    }

    protected void end(){
    	Robot.leftSide.setSpeed(0);
    	Robot.rightSide.setSpeed(0);
    }

       protected void interrupted() {
    }
       
    protected boolean inTresh(){
    	if (speed < .1 && speed > -.1){
    		return true;
    	}
    	else{
    		return false;
    	}
    	 
     }
}
