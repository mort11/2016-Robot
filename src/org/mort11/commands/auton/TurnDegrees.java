package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.mort11.Robot;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.util.PIDLoop;


public class TurnDegrees extends Command {
	
	//	Timer timer;
	private PIDLoop pd;
	private DTSide left = Robot.leftSide;
	private DTSide right = Robot.rightSide;
	private double speed;
	private double angle; //angle that the robot will turn by
	private double curAngle; //current orientation of robot

    public TurnDegrees(double angle) { //takes desired angle for turning (between -180 and 180)
    	this.angle = angle;
    	requires(left);
        requires(right);
        pd = new PIDLoop(this.angle, .01, 0); //placeholder values, must test
    }

    protected void initialize() {

    }

    protected void execute() {
    	//curAngle = DTSide.getAngle(); //gets current angle of robot
    	speed = pd.getOutput(curAngle); //passes current angle through pid loop
        left.setSpeed(speed); //sets speed
        right.setSpeed(-speed); //sets negative speed so robot can turn
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
        return speed < .1 && speed > -.1;

    }
}
