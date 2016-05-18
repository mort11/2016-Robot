package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.subsystems.dt.DTSide;

/**
 * Adjust robot to goal using the camera
 *
 * @author Sahit Chintalapudi
 * @author Jakob Shortell
 * @author Matt Krzyzanowski
 */
class AdjustToGoal extends Command {
    private Timer timer;
    private DTSide left = Robot.adaptor.leftSide;
    private DTSide right = Robot.adaptor.rightSide;
    private double netError, lastTime, theta, outputSpeed;
    private boolean debug = false, loopStarted;

    AdjustToGoal() {
        requires(left);
        requires(right);
        timer = new Timer();
        setInterruptible(true);
    }

    protected void initialize() {
        this.left.set(0);
        this.right.set(0);
        netError = 0;
        if (Robot.table.getNumberArray("centerX", new double[]{}).length == 0) {
            System.out.println("Not found in ATG");
            return;
        }
        double area = 0;
        int target_index = 0;
        int curr_index = 0;
        for (double i : Robot.table.getNumberArray("area", new double[]{})) {
            if (i > area) {
                area = i;
                target_index = curr_index;
            }
            curr_index++;
        }
        double x_val = Robot.table.getNumberArray("centerX", new double[]{})[target_index];
        double y_val = Robot.table.getNumberArray("centerY", new double[]{})[target_index];
        double range = (y_val + 68.05) / 1.475;
        double pxPer6 = -0.7083 * range + 104.75;
        theta = Math.atan2((((153 - x_val) / pxPer6) * 6), range) * 180 / Math.PI;
        theta *= -2;
        left.setDistToTurn(theta);
        Robot.adaptor.ahrs.zeroYaw();
        if (Math.abs(theta) < 15) {
            outputSpeed = 0.3;
        }
        timer.start();
    }

    @Override
    protected void execute() {
        double curr_angle = Robot.adaptor.ahrs.getYaw();
        if (curr_angle > 200) {
            curr_angle = curr_angle - 360;
        }
        if (debug) {
            if (curr_angle > theta) {
                right.set(outputSpeed);
                left.set(-outputSpeed);
            } else if (curr_angle < theta) {
                right.set(-outputSpeed);
                left.set(outputSpeed);
            }
            if (curr_angle > theta * 0.95 && curr_angle < theta * 1.05) {
            }
            return;
        }
        if (!loopStarted && curr_angle != 0) {
            return;
        }
        loopStarted = true;
        double thistime = timer.get();
        double error = theta - curr_angle;
        netError += (error * (thistime - lastTime));
        lastTime = thistime;
        double output = (error * 0.09) + (netError * 0.13);
        if (output > 0.5) {
            output = 0.5;
        } else if (output < -0.5) {
            output = -0.5;
        }
        if (!debug) {
            left.set(output);
            right.set(-output);
        } else {
        }
    }

    @Override
    protected boolean isFinished() {
        return timer.get() > 1.5;
    }

    @Override
    protected void end() {
        netError = 0;
        this.left.set(0);
        this.right.set(0);
    }

    @Override
    protected void interrupted() {
    }
}