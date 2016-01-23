package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.mort11.Robot;
import org.mort11.subsystems.dt.DTLeft;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.util.PIDLoop;

public class DriveStraight extends Command {

	private double driveDistance; //distance that you want robot to drive
	private DTSide left = Robot.leftSide;
	private DTSide right = Robot.rightSide;
	private PIDLoop pd_left;
	private PIDLoop pd_right;
	private double curDist; 
	private double speed_left;
	private double speed_right;
	
    public DriveStraight(double distance) {
       this.driveDistance = distance;
       requires(left);
       requires(right);
       pd_left = new PIDLoop(driveDistance, .01, 0); //placeholder values, must test
       pd_right = new PIDLoop(driveDistance, .01, 0); //placeholder values, must test
    }

    protected void initialize() {
    	DTSide.resetEnc();
    }
    
    protected void execute() {
        curDist = DTSide.getDist(); //get distance traveled since last encoder reset
        speed_left = pd_left.getOutput(curDist); //gets speed using pid loop
        speed_right = pd_right.getOutput(curDist); //gets speed using pid loop
    	left.setSpeed(speed_left); //sets speed of robot
    	right.setSpeed(speed_right); //sets speed of robot
    	SmartDashboard.putNumber("Distance Traveled", DTSide.getDist()); //gets and displays distance traveled
        SmartDashboard.putNumber("PWM Value", DTSide.getSpeed()); //get most recently set PWM value, between -1.0 and 1.0
    	
    }

    protected boolean isFinished() {
        return this.inTresh();
    }

	protected void end() {
    	left.setSpeed(0);
    	right.setSpeed(0);
    	DTSide.resetEnc();
    }

    protected void interrupted() {
    }
    
    //used to determine if robot is close enough to target to stop
    protected boolean inTresh(){
        //placeholder values, must test
        return (speed_left < .01 && speed_left > -.01) && (speed_right < .01 && speed_right > -.01);

    }
}
