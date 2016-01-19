package org.mort11.commands.dt;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.mort11.Robot;
import org.mort11.subsystems.dt.DTSide;
import util.PIDLoop;

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
       pd = new PIDLoop(driveDistance, .01, 0); //placeholder values, must test
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
        SmartDashboard.putNumber("PWM Value", DTSide.getSpeed()); //get most recently set PWM value, between -1.0 and 1.0
    	
    }

    protected boolean isFinished() {
        return this.inTresh();
    }

    protected void end() {
    	DTSide.resetEnc();
    }

    protected void interrupted() {
    }
    
    protected boolean inTresh(){
        //placeholder values, must test
        return speed < .01 && speed > -.01;

    }
}
