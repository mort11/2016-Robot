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
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 * @author Jeffrey Pastilha <jpmail967@yahoo.com>
 */
public class DriveStraight extends Command {
    private DTSide left = Robot.adaptor.leftSide;
    private DTSide right = Robot.adaptor.rightSide;
    private PIDLoop pd_left,pd_right;
    private Encoder leftDTEncoder = SensorDealer.getInstance().getLeftDTEncoder();
    private Encoder rightDTEncoder = SensorDealer.getInstance().getRightDTEncoder();

    public DriveStraight(double distance) {
        requires(left);
        requires(right);
        pd_left = new PIDLoop(distance, .01, 0); // Placeholder values, must test
        pd_right = new PIDLoop(distance, .01, 0); // Placeholder values, must test
    }

    protected void initialize() {
        left.resetEncoder();
        right.resetEncoder();
    }

    protected void execute() {
        if (!DTSide.getDisabled()){ // disable method integration
        double currentDistanceLeft = leftDTEncoder.getDistance();
        double currentDistanceRight = rightDTEncoder.getDistance();
        System.out.println("left: " + currentDistanceLeft);
        System.out.println("Right " + currentDistanceRight);
        double speedLeft = pd_left.getOutput(currentDistanceLeft);
        double speedRight = pd_right.getOutput(currentDistanceRight);

            left.set(speedLeft);
            right.set(speedRight);

            SmartDashboard.putNumber("Left Distance", currentDistanceLeft);
            SmartDashboard.putNumber("Right Distance", currentDistanceRight);
            SmartDashboard.putNumber("Left Speed", speedLeft);
            SmartDashboard.putNumber("Right Speed", speedRight);
        } else {
            end();
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        left.stop();
        right.stop();
        left.resetEncoder();
        right.resetEncoder();
    }

    protected void interrupted() {
    }
}
