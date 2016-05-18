package org.mort11.commands.auton;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.util.PIDLoop;

/**
 * Turn x degrees from current orientation
 *
 * @author Matthew Krzyzanowski
 */
public class TurnDegrees extends Command {
    private PIDLoop turnController;
    private DTSide left = Robot.adaptor.leftSide;
    private DTSide right = Robot.adaptor.rightSide;
    private AHRS ahrs = Robot.adaptor.ahrs;
    private double desiredAngle;

    /**
     * Takes desired angle for turning, must be positive, and boolean for turn direction
     *
     * @param angle Angle to turn by
     */
    public TurnDegrees(double angle) {
        requires(left);
        requires(right);
        setInterruptible(true);
        this.desiredAngle = angle;
        turnController = new PIDLoop(this.desiredAngle, 0.02, 0.01, 25);
    }

    /**
     * Resets the yaw so current angle is accurate
     */
    @Override
    protected void initialize() {
        this.ahrs.reset();
    }

    @Override
    protected void execute() {
        double currentAngle = Math.abs(this.ahrs.getYaw());
        double speed = turnController.getOutput(currentAngle);

        if (desiredAngle < 0) {
            // Counter-clockwise turning
            left.set(speed);
            right.set(-speed);
        } else {
            // Clockwise turning
            left.set(-speed);
            right.set(speed);
        }
    }

    @Override
    protected boolean isFinished() {
        return this.ahrs.getYaw() > desiredAngle * 0.98;
    }

    @Override
    protected void end() {
        left.set(0);
        right.set(0);
        left.resetEncoder();
        right.resetEncoder();
        this.ahrs.zeroYaw();
    }

    @Override
    protected void interrupted() {
        this.ahrs.zeroYaw();
    }
}
