package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.subsystems.ee.IntakeArm;

/**
 * MotorToAngle - Move motor to angle
 *
 * @author Ryan Thant <ryanthant1@gmail.com>
 * @author Seven Kurt <seven.kurt@motsd.org>
 * @author Ryan O'Toole <ryan.otoole@motsd.org>
 * @author chsahit
 */
public class MotorToAngle extends Command {
    double speed = 0.5;
    double tarAng = 90;
    private IntakeArm intake = Robot.adaptor.intakeArm;

    public MotorToAngle() {
        requires(intake);
    }

    @Override
    protected void initialize() {
        this.setInterruptible(true);
    }

    @Override
    protected void execute() {
        System.out.println("Intake Arm Angle: " + intake.getAngle());
        if (intake.getAngle() < tarAng) {
            intake.set(speed);
        }
        if (intake.getAngle() > tarAng) {
            intake.set(-speed);
        }
        if (intake.getAngle() == tarAng) {
            intake.set(0);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}
