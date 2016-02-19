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
    private boolean isFinished = false;
    private DTSide left = Robot.adaptor.leftSide;
    private DTSide right = Robot.adaptor.rightSide;
    private Camera camera = Robot.adaptor.cam;

    public AdjustToGoal() {
        requires(left);
        requires(right);
        requires(camera);
    }

    protected void initialize() {
        Robot.adaptor.ahrs.zeroYaw();
    }

    protected void execute() {
        this.camera.setPicture();
        double x_val = camera.getX();
        if (x_val == -1) {
            System.out.println("Not found");
            this.isFinished = true;
        }
        System.out.println("Centering");
        if (x_val < 135) {
            this.left.set(-0.15);
            this.right.set(0.15);
            System.out.println("Too far right");
        } else if (x_val > 185) {
            this.left.set(0.15);
            this.right.set(-0.15);
            System.out.println("Too far left");
        } else {
            System.out.println("Centered");
            this.isFinished = true;
        }
        this.camera.setPicture();
    }

    protected boolean isFinished() {
        return this.isFinished;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
