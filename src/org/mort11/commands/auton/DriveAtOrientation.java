package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.util.PIDLoop;

/**
 * DriveAtOrientation - Drives at orientation
 *
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 */
public class DriveAtOrientation extends Command {
    private DTSide left = Robot.adaptor.leftSide;
    private DTSide right = Robot.adaptor.rightSide;
    private PIDLoop pd_left;
    private PIDLoop pd_right;

    // Speed values for left and right sides of robot
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
        left.set(speed_left);
        right.set(speed_right);
    }

    protected boolean isFinished() {
        return this.inThresh();
    }

    protected void end() {
        left.halt();
        right.halt();
    }

    protected void interrupted() {
    }

    protected boolean inThresh() {
        return false;
    }
}
