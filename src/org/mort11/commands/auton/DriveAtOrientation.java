package org.mort11.commands.auton;

import org.mort11.Robot;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.util.PIDLoop;
import edu.wpi.first.wpilibj.command.Command;


public class DriveAtOrientation extends Command {
	
	private DTSide left = Robot.leftSide;
	private DTSide right = Robot.rightSide;
	private PIDLoop pd_left;
	private PIDLoop pd_right;
	//speed values for left and right sides of robot
	private double speed_left;
	private double speed_right;
	private double targetOrientation;
	private double currentOrientation = 5;
	private boolean isUrgent; //false == low urgency, true == high urgency	

    public DriveAtOrientation(double target, double speed, boolean urgency) {
    	  requires(left);
          requires(right);
          this.targetOrientation = target;
          this.speed_left = speed;
          this.speed_right = speed;
          this.isUrgent = urgency;
          pd_left = new PIDLoop(target, .01, .1);
          pd_right = new PIDLoop(target, .01, .1);
            
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	//currentOrientation = navx.getAngle();
    	if(isUrgent) {
      		//use high urgency pid loop
    		//speed_left = pd_left.getOutputHigh(currentOrientation);
    		//speed_right = pd_right.getOutputHigh(currentOrientation);
      	} else {
      		//use low urgency pid loop
      	    //speed_left = pd_left.getOutputHigh(currentOrientation);
    		//speed_right = pd_right.getOutputHigh(currentOrientation);
      	}  
    	left.setSpeed(speed_left);
    	right.setSpeed(speed_right);
    }

    protected boolean isFinished() {
        return this.inThresh();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
    protected boolean inThresh(){
        //placeholder values, must test
    	//placeholder values, must test
        //return (speed_left < .01 && speed_left > -.01) && (speed_right < .01 && speed_right > -.01);
    	return false;

    }
}
