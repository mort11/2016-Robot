package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.subsystems.Camera;
import org.mort11.subsystems.dt.DTSide;

/**
 * AdjustToGoal - Adjust robot to goal
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class AdjustToGoal extends Command {
    boolean finished = false;
    private DTSide left = Robot.adaptor.leftSide;
    private DTSide right = Robot.adaptor.rightSide;
    private Camera camera = Robot.adaptor.cam;

    public AdjustToGoal() {
        requires(left);
        requires(right);
        requires(camera);
    }

    protected void initialize() {
        //SensorDealer.getInstance().getAHRS().zeroYaw();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.adaptor.cam.setPicture();
        System.out.println("looping");
        double x_val = Robot.adaptor.cam.getX();
        if (x_val == -1) {
            System.out.println("not found");
            finished = true;
        }
        System.out.println("Centering");
        if (x_val < 135) {
            Robot.adaptor.leftSide.set(-0.15);
            Robot.adaptor.rightSide.set(0.15);
            System.out.println("too far right");
        } else if (x_val > 185) {
            Robot.adaptor.leftSide.set(0.15);
            Robot.adaptor.rightSide.set(-0.15);
            System.out.println("too far left");
        } else {
            System.out.println("centered");
            finished = true;
        }
        Robot.adaptor.cam.setPicture();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
