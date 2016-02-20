package org.mort11.commands;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.util.Logger;
import org.mort11.util.PIDLoop;

/**
 * DrivePID - Drive linear within PID confines
 *
 * @author Sahit Chintalapudi
 * @author Matt Turi
 * @author Ryan Thant
 * @author Matthew Krzyzanowski
 */
public class DrivePID extends Command {
    PIDLoop loopFunction_left;
    PIDLoop loopFunction_right;
    Logger logger;
    double target = 60;
    double velLeft = 0;
    double velRight = 0;

    private DTSide left = Robot.adaptor.leftSide;
    private DTSide right = Robot.adaptor.rightSide;
    private Encoder leftEncoder = Robot.adaptor.leftDTEncoder;
    private Encoder rightEncoder = Robot.adaptor.rightDTEncoder;

    public DrivePID() {
        requires(left);
        requires(right);
    }

    public DrivePID(double target) {
        this.target = target;
        requires(left);
        requires(right);
    }

    protected void initialize() {
        loopFunction_left = new PIDLoop(target, 0.05, 0);
        loopFunction_right = new PIDLoop(target, 0.05, 0);
        //Logger.init("/home/lvuser/output");
        //Logger.writeString("Left Dist,SP Left,Left PWM, Right Dist,SP Right, Right PWM");
    }

    protected void execute() {
        velLeft = loopFunction_left.getOutput(leftEncoder.getDistance());
        velRight = loopFunction_right.getOutput(rightEncoder.getDistance());
        //Logger.writeString(leftEncoder.getDistance() + "," + loopFunction_left.getSP()
        //        + "," + velLeft + "," + rightEncoder.getDistance() + "," + loopFunction_right.getSP()
        //        + "," + velRight);
        System.out.println("Left- Distance:  " + leftEncoder.getDistance() + " PI: " + velLeft);
        System.out.println("Right- Distance:  " + rightEncoder.getDistance() + " PI: " + velRight);
        left.set(velLeft);
        right.set(velRight);
    }

    protected boolean isFinished() {
        //2 inch threshold and slow
        return Math.abs(leftEncoder.getDistance() / target) > 0.98
                && Math.abs(velLeft) < 0.35;
    }

    protected void end() {
        Logger.close();
        right.halt();
        left.halt();
        rightEncoder.reset();
        leftEncoder.reset();
    }

    protected void interrupted() {
    }
}
