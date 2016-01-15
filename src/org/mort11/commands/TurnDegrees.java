package org.mort11.commands;

import org.mort11.Robot;
import org.mort11.subsystems.DTSide;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;


public class TurnDegrees extends Command {
	
	Timer timer;
	double curVal;
	double time;

    public TurnDegrees() {
    	requires(Robot.leftSide);
        requires(Robot.rightSide);
    }

    protected void initialize() {
    	timer = new Timer();
    	timer.start();
    }

    protected void execute() {
    	Robot.leftSide.setSpeed(curVal);
    	Robot.rightSide.setSpeed(-curVal);
    }


    protected boolean isFinished() {
        return timer.get() > time;
    }

    protected void end() {
    	Robot.leftSide.setSpeed(0);
    	Robot.rightSide.setSpeed(0);
    }

       protected void interrupted() {
    }
}
