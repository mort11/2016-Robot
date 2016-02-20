package org.mort11.commands.auton;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.mort11.Robot;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.util.PIDLoop;

/**
 * DriveStraight - Drive in a (mostly) straight line
 *
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 * @author Jeffrey Pastilha <jpmail967@yahoo.com>
 */
public class DriveStraight extends Command {
    double currentDistanceLeft, currentDistanceRight;
    double distance;
    private DTSide left = Robot.adaptor.leftSide;
    private DTSide right = Robot.adaptor.rightSide;
    private PIDLoop pd_left, pd_right;
    private Encoder leftDTEncoder = Robot.adaptor.leftDTEncoder;
    private Encoder rightDTEncoder = Robot.adaptor.rightDTEncoder;
    private AHRS ahrs = Robot.adaptor.ahrs;

    public DriveStraight(double distance) {
        requires(left);
        requires(right);
        pd_left = new PIDLoop(distance, .01, 0); // Placeholder values, must test
        pd_right = new PIDLoop(distance, .01, 0); // Placeholder values, must test
        this.distance = distance;
    }

    protected void initialize() {
        leftDTEncoder.reset();
        rightDTEncoder.reset();
        this.ahrs.zeroYaw();
    }

    protected void execute() {
        currentDistanceLeft = leftDTEncoder.getDistance();
        currentDistanceRight = rightDTEncoder.getDistance();
        System.out.println("left: " + currentDistanceLeft);
        System.out.println("Right " + currentDistanceRight);
        double speedLeft = pd_left.getOutput(currentDistanceLeft);
        double speedRight = pd_right.getOutput(currentDistanceRight);

        double angleError = this.ahrs.getYaw() % 360;
        if (angleError > 180) {
            angleError = Math.abs(360 - angleError);
        }
        System.out.println("angle error: " + angleError);

        left.set(speedLeft - 0.05 * angleError);
        right.set(speedRight + 0.05 * angleError);


        SmartDashboard.putNumber("Left Distancse", currentDistanceLeft);
        SmartDashboard.putNumber("Right Distance", currentDistanceRight);
        SmartDashboard.putNumber("Left Speed", speedLeft);
        SmartDashboard.putNumber("Right Speed", speedRight);
        SmartDashboard.putNumber("Angle Disp", angleError);
    }

    protected boolean isFinished() {
        return (Math.abs(distance - currentDistanceLeft) < 2 &&
                Math.abs(distance - currentDistanceRight) < 2) ||
                (pd_left.timeElapsed(1.1));
    }

    protected void end() {
        left.halt();
        right.halt();
        left.resetEncoder();
        right.resetEncoder();
    }

    protected void interrupted() {
    }
}
