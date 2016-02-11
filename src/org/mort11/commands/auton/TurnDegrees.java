package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.mort11.Robot;
import org.mort11.sensors.SensorDealer;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.util.PIDLoop;

/**
 * TurnDegrees - Turn x degrees
 *
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 */
public class TurnDegrees extends Command {
    private PIDLoop pd;
    private DTSide left = Robot.adaptor.leftSide;
    private DTSide right = Robot.adaptor.rightSide;
    private double speed;
    private double desiredAngle; // Angle that the robot will turn by
    private double currentAngle; // Current orientation of robot
    private boolean isReverse; // Allows the robot to turn counterclockwise if set to true

    /**
     * Takes desired angle for turning, must be positive, and boolean for turn direction
     *
     * @param isReverse Should reverse motor?
     * @param angle     Angle to turn by
     */
    public TurnDegrees(boolean isReverse, double angle) {
        this.isReverse = isReverse;
        this.desiredAngle = angle;
        requires(left);
        requires(right);
        pd = new PIDLoop(this.desiredAngle, 0.01, 0.01, 1.5); // Original vals:  (.02, .005, 2)
    }

    /**
     * Resets the yaw so current angle is accurate
     */
    protected void initialize() {
        SensorDealer.getInstance().getAHRS().reset();
    }

    protected void execute() {
        if (!DTSide.getDisabled()) {
            //currentAngle = DTSide.getAngle(); //gets current angle of robot
            currentAngle = Math.abs(SensorDealer.getInstance().getAHRS().getYaw()); //might work better than getAngle(), must test
            System.out.println("current angle" + currentAngle);
            speed = pd.getOutput(currentAngle); //passes current angle through pid loop
            System.out.println("speed" + speed);
            SmartDashboard.putNumber("Current Angle", currentAngle);
            SmartDashboard.putNumber("Speed", speed);
            // Clockwise turning
            if (!isReverse) {
                left.set(speed);
                right.set(-speed);
            }
            // Counterclockwise turning
            else {
                left.set(-speed);
                right.set(speed);
            }
        } else {
            end();
        }
    }

    protected boolean isFinished() {
        return SensorDealer.getInstance().getAHRS().getYaw() > desiredAngle * 0.9;
    }

    protected void end() {
        left.set(0);
        right.set(0);
        left.resetEncoder();
        right.resetEncoder();
        SensorDealer.getInstance().getAHRS().reset();
    }

    protected void interrupted() {
    }

}
