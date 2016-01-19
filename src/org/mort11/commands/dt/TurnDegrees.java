package org.mort11.commands.dt;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.mort11.Robot;
import org.mort11.subsystems.dt.DTSide;
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
        pd = new PIDLoop(angle, .01, 0); //placeholder values, must test
    }

    protected void initialize() {

    }

    protected void execute() {
    	speed = pd.getOutput(Robot.leftSide.getAngle());
        DTSide.setSpeed(speed);
        DTSide.setSpeed(-speed);
        SmartDashboard.putNumber("PWM Value", DTSide.getSpeed()); //get most recently set PWM value, between -1.0 and 1.0
    }

    protected boolean isFinished() {
        return this.inTresh();
    }

    protected void end(){
        DTSide.setSpeed(0);
        DTSide.setSpeed(0);
    }

       protected void interrupted() {
    }
       
    protected boolean inTresh(){
        //placeholder values, must test
        return speed < .1 && speed > -.1;

    }
}
