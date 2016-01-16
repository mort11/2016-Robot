package org.mort11.commands;

import org.mort11.Robot;
import org.mort11.subsystems.DTSide;

import util.PIDLoop;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraight extends Command {

	private double driveDistance;
	private DTSide left = Robot.leftSide;
	private DTSide right = Robot.rightSide;
	private Timer timer;
	private PIDLoop pd;
	private double curDist;
	private double speed;
	
    public DriveStraight() {
       requires(left);
       requires(right);
       timer = new Timer();
       pd = new PIDLoop(driveDistance, .01, 0);
    }

    protected void initialize() {
    	DTSide.resetEnc();
    	timer.start();

    }

    protected void execute() {
        curDist = DTSide.getDist();
        speed = pd.getOutput(curDist);
    	DTSide.setSpeed(speed);
    	SmartDashboard.putNumber("Distance Traveled", DTSide.getDist()); //gets and displays distance traveled
    	SmartDashboard.putNumber("PWM Value", DTSide.getCurent()); //gets raw PWM value, unsure of what value means
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	DTSide.resetEnc();
    }

    protected void interrupted() {
    }
}
