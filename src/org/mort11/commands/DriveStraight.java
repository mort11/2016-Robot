package org.mort11.commands;

import org.mort11.Robot;
import org.mort11.subsystems.DTSide;

import util.PIDLoop;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveStraight extends Command {

<<<<<<< HEAD
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
       curDist = DTSide.getDist();
       speed = pd.getOutput(curDist);
=======
    private double driveDistance;
    private DTSide left = Robot.leftSide;
    private DTSide right = Robot.rightSide;
    private Timer timer;

    public DriveStraight() {
        requires(left);
        requires(right);
        timer = new Timer();
>>>>>>> 9310cbde147d90fc109c008c4e69157523d37b0a
    }

    protected void initialize() {
<<<<<<< HEAD
    	DTSide.resetEnc();
    	timer.start();
    	
    	
=======
        timer.start();
>>>>>>> 9310cbde147d90fc109c008c4e69157523d37b0a
    }

    protected void execute() {
    	DTSide.setSpeed(speed);
    	
    	
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
