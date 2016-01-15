package org.mort11.commands;

import org.mort11.Robot;
import org.mort11.subsystems.DTSide;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;


public class TurnDegrees extends Command {
	
//	Timer timer;
	double error;
	double angle;
	double currentAngle; //need way to get angle of robot

    public TurnDegrees(double angle) { //takes desired angle for turning (between -180 and 180)
    	this.angle = angle;
    	requires(Robot.leftSide);
        requires(Robot.rightSide);
    }

    protected void initialize() {
//    	timer = new Timer();
//    	timer.start();
    }

    protected void execute() {
    	error = angle - currentAngle; //gets angle that robot has to change
    	Robot.leftSide.setSpeed(error);
    	Robot.rightSide.setSpeed(-error);
    }


    protected boolean isFinished() {
        return currentAngle == angle;
    }

    protected void end() {
    	Robot.leftSide.setSpeed(0);
    	Robot.rightSide.setSpeed(0);
    }

       protected void interrupted() {
    }
}
