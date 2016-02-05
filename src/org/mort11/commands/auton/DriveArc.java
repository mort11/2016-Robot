package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.constants.DrivetrainConstants;
import org.mort11.sensors.SensorDealer;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.util.Logger;
import org.mort11.util.PIDLoop;

/**
 * DriveArc - Drives in an arc
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class DriveArc extends Command {
    double arcLength, turnRadius;
    PIDLoop pidLeft;
    PIDLoop pidRight;
    Timer timer = new Timer();
    double rightTarget, leftTarget;
    double rightDist, leftDist;
    private DTSide rightSide = Robot.adaptor.rightSide;
    private DTSide leftSide = Robot.adaptor.leftSide;

    public DriveArc(double arclength, double turnRadius) {
        requires(leftSide);
        requires(rightSide);
        this.arcLength = arclength;
        this.turnRadius = turnRadius;
    }

    protected void initialize() {
        double[] distances = arc_calc(arcLength, turnRadius);
        Logger.writeString("Right target," + distances[0]);
        Logger.writeString("Left target," + distances[1]);
        rightTarget = distances[0];
        leftTarget = distances[1];
        pidRight = new PIDLoop(distances[0], 0.01, 0.00, distances[0] / Math.max(distances[0], distances[1]));
        pidLeft = new PIDLoop(distances[1], 0.01, 0.00, distances[1] / Math.max(distances[0], distances[1]));
        Logger.writeString("Time, Distance Left, SP Left, Output Left, "
                + "Distance Right, SP Right, Output Right");
        timer.start();
    }

    protected void execute() {
        if (!DTSide.getIsDisabled()){ // disable method integration
        rightDist = SensorDealer.getInstance().getRightDTEncoder().getDistance();
        double rightVel = pidRight.getOutput(rightDist);
        leftDist = SensorDealer.getInstance().getLeftDTEncoder().getDistance();
        double leftVel = pidLeft.getOutput(leftDist);
        rightSide.set(rightVel);
        leftSide.set(leftVel);
        Logger.writeString(timer.get() + "," + leftDist + "," + pidLeft.getSP() + "," + leftVel
                + "," + pidRight.getSP() + "," + rightVel);
        } else {
            leftSide.stop();
            rightSide.stop();
            end();
        }
    }

    protected boolean isFinished() {
        return rightDist > rightTarget * 1.3 && leftDist > leftTarget * 1.3;
    }

    protected void end() {
        Logger.close();
    }

    protected void interrupted() {
    }

    private double[] arc_calc(double arc_length, double theta) {
        double centerRadius = Math.abs(arc_length / theta);
        double right_radius = centerRadius -
                DrivetrainConstants.kRobotRadius * Math.signum(arc_length);
        double left_radius = centerRadius +
                DrivetrainConstants.kRobotRadius * Math.signum(arc_length);
        System.out.println("Right radius: " + right_radius * theta);
        System.out.println("Left radius: " + left_radius * theta);
        return (new double[]{right_radius * theta, left_radius * theta});
    }
}
