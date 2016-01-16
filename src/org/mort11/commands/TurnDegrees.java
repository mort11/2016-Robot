package org.mort11.commands;

import org.mort11.Robot;
import org.mort11.subsystems.DTSide;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
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
    	speed = pd.getOutput(angle);
    	Robot.leftSide.setSpeed(speed);
    	Robot.rightSide.setSpeed(-speed);
    }


    protected boolean isFinished() {
        return curAngle == angle;
    }

    protected void end() {
    	Robot.leftSide.setSpeed(0);
    	Robot.rightSide.setSpeed(0);
    }

       protected void interrupted() {
    }
}
