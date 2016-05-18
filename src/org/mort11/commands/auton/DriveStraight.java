package org.mort11.commands.auton;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.util.PIDLoop;

/**
 * Drive in a (mostly) straight line
 *
 * @author Matthew Krzyzanowski
 * @author Sahit Chintalapudi
 * @author Jeffrey Pastilha
 */
public class DriveStraight extends Command {
    private DTSide left = Robot.adaptor.leftSide, right = Robot.adaptor.rightSide;
    private PIDLoop leftDTController, rightDTController;
    private Encoder leftDTEncoder = Robot.adaptor.leftDTEncoder, rightDTEncoder = Robot.adaptor.rightDTEncoder;
    private AHRS ahrs = Robot.adaptor.ahrs;
    private Timer timer;
    private double distance, currentDistanceRight;

    public DriveStraight(double distance) {
        this.ahrs.zeroYaw();
        requires(left);
        requires(right);
        leftDTController = new PIDLoop(distance, .02, 0.01, 23);
        rightDTController = new PIDLoop(distance, .02, 0.01, 23);
        this.distance = distance;
        leftDTEncoder.reset();
        rightDTEncoder.reset();
        timer = new Timer();
    }

    public DriveStraight(double distance, double velocity) {
        this(distance);
        leftDTController = new PIDLoop(distance, .02, 0.01, velocity);
        rightDTController = new PIDLoop(distance, .02, 0.01, velocity);
    }

    @Override
    protected void initialize() {
        timer.start();
    }

    @Override
    protected void execute() {
        currentDistanceRight = rightDTEncoder.getDistance();
        double speedLeft = leftDTController.getOutput(leftDTEncoder.getDistance());
        double speedRight = rightDTController.getOutput(currentDistanceRight);

        double angleError = this.ahrs.getYaw() % 360;
        if (angleError > 180) {
            angleError = Math.abs(360 - angleError);
        }

        left.set(speedLeft - 0.03 * angleError);
        right.set(speedRight + 0.03 * angleError);
    }

    @Override
    protected boolean isFinished() {
        return (Math.abs(distance - currentDistanceRight) < 8 || leftDTController.timeElapsed(1.2) || rightDTController.timeElapsed(1.2));
    }

    @Override
    protected void end() {
        left.halt();
        right.halt();
        left.resetEncoder();
        right.resetEncoder();
    }

    @Override
    protected void interrupted() {
    }
}
