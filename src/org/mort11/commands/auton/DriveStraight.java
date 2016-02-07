package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.mort11.Robot;
import org.mort11.sensors.SensorDealer;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.util.PIDLoop;

/**
 * DriveStraight - Drive in a (mostly) straight line
 *
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 */
public class DriveStraight extends Command {
    private DTSide left = Robot.adaptor.leftSide;
    private DTSide right = Robot.adaptor.rightSide;
    private PIDLoop pd_left;
    private PIDLoop pd_right;
    private Encoder leftDTEncoder = SensorDealer.getInstance().getLeftDTEncoder();
    private Encoder rightDTEncoder = SensorDealer.getInstance().getRightDTEncoder();

    public DriveStraight(double distance) {
        requires(left);
        requires(right);
        pd_left = new PIDLoop(distance, .01, 0); // Placeholder values, must test
        pd_right = new PIDLoop(distance, .01, 0); // Placeholder values, must test
    }

    protected void initialize() {
        DTSide.resetEncoders();
    }

    protected void execute() {
        if (!DTSide.getIsDisabled()){ // disable method integration
        double currentDistanceLeft = leftDTEncoder.getDistance();
        double currentDistanceRight = rightDTEncoder.getDistance();

        double speedLeft = pd_left.getP(currentDistanceLeft);
        double speedRight = pd_right.getP(currentDistanceRight);

        left.set(speedLeft);
        right.set(speedRight);

        SmartDashboard.putNumber("Left Distance", currentDistanceLeft);
        SmartDashboard.putNumber("Right Distance", currentDistanceRight);
        SmartDashboard.putNumber("Left Speed", speedLeft);
        SmartDashboard.putNumber("Right Speed", speedRight);
        }
        else {
            end();
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        left.stop();
        right.stop();
        DTSide.resetEncoders();
    }

    protected void interrupted() {
    }
}
