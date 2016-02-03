package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.HardwareAdaptor;
import org.mort11.constants.DrivetrainConstants;
import org.mort11.sensors.SensorDealer;
import org.mort11.subsystems.dt.DTLeft;
import org.mort11.subsystems.dt.DTRight;
import org.mort11.util.PIDLoop;

public class DriveArc extends Command {
    double arcLength, turnRadius;
    PIDLoop pidLeft;
    PIDLoop pidRight;
    private DTRight rightSide = HardwareAdaptor.rightSide;
    private DTLeft leftSide = HardwareAdaptor.leftSide;

    public DriveArc(double arclength, double turnRadius) {
        requires(leftSide);
        requires(rightSide);
        this.arcLength = arclength;
        this.turnRadius = turnRadius;
    }

    protected void initialize() {
        double[] distances = arc_calc(arcLength, turnRadius);
        pidRight = new PIDLoop(distances[0], 0.01, 0.00, distances[0] / Math.max(distances[0], distances[1]));
        pidLeft = new PIDLoop(distances[1], 0.01, 0.00, distances[1] / Math.max(distances[0], distances[1]));
    }

    protected void execute() {
        rightSide.setSpeed(pidRight.getOutput(SensorDealer.getInstance().getRightDriveTrain().getDistance()));
        leftSide.setSpeed(pidLeft.getOutput(SensorDealer.getInstance().getLeftDriveTrain().getDistance()));
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }

    private double[] arc_calc(double arc_length, double theta) {
    	double centerRadius = Math.abs(arc_length/theta);
    	double right_radius = centerRadius - 
    			DrivetrainConstants.kRobotRadius * Math.signum(arc_length);
    	double left_radius = centerRadius + 
    			DrivetrainConstants.kRobotRadius * Math.signum(arc_length);
    	System.out.println("Right radius: "  + right_radius * theta);
    	System.out.println("Left radius: "  + left_radius * theta);
    	return (new double[]{right_radius * theta,left_radius*theta});
    }
}
