package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.util.Logger;
import org.mort11.util.PIDLoop;

public class DrivePID extends Command {
    PIDLoop loopFunction_left;
    PIDLoop loopFunction_right;
    Logger logger;
    double target = 60;
    double velLeft = 0;
    double velRight = 0;

    public DrivePID() {
        requires(Robot.dt);
    }

    public DrivePID(double target) {
        this.target = target;
        requires(Robot.dt);
    }

    protected void initialize() {
    	loopFunction_left = new PIDLoop(target, 0.05, 0);
    	loopFunction_right = new PIDLoop(target, 0.05, 0);
    	logger = new Logger();
    	logger.init("/home/lvuser/output");
    	logger.writeString("Left Dist,SP Left,Left PWM, Right Dist,SP Right, Right PWM");
    }

    protected void execute() {
        velLeft = loopFunction_left.getOutput(Robot.dt.getDistLeft());
        velRight = loopFunction_right.getOutput(Robot.dt.getDistRight());
        logger.writeString(Robot.dt.getDistLeft() + "," + loopFunction_left.getSP()
                + "," + velLeft + "," + Robot.dt.getDistRight() + "," + loopFunction_right.getSP()
                + "," + velRight);
        System.out.println("Left- Distance:  " + Robot.dt.getDistLeft() + " PI: " + velLeft);
        System.out.println("Right- Distance:  " + Robot.dt.getDistRight() + " PI: " + velRight);
        Robot.dt.driveLeft(velLeft);
        Robot.dt.driveRight(velRight);
    }

    protected boolean isFinished() {
        //2 inch threshold and slow
        return Math.abs(Robot.dt.getDistLeft() / target) > 0.98
                && Math.abs(velLeft) < 0.35;
    }

    protected void end() {
    	logger.close();
    	Robot.dt.resetEnc();
    	Robot.dt.stop();
        Robot.dt.resetEnc();
        Robot.dt.stop();
    }

    protected void interrupted() {
    }
}
