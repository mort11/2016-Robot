package org.mort11.commands.ee;

import org.mort11.Robot;
import util.PIDLoop;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeArm extends Command {
	PIDLoop loopFunction;
	double target;
	double curAngle;
	double vel;
	
    public IntakeArm() {
        requires(Robot.intakeArm);
    }
    
    public IntakeArm(double target) {
    	this.target = target;
        requires(Robot.intakeArm);
    }

    protected void initialize() {
    	loopFunction = new PIDLoop(target, .1, .1);
    }

    protected void execute() {
    	curAngle = Robot.intakeArm.getAngle();
    	vel = loopFunction.getOutput(curAngle);
    	Robot.intakeArm.setSpeed(vel);
    }

    protected boolean isFinished() {
        return inThresh();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
    protected boolean inThresh(){
    	return vel < .1 && vel > -.1;
    }
}
