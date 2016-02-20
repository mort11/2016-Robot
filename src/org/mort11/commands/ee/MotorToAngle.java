package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.subsystems.ee.IntakeArm;
import org.mort11.util.PIDLoop;

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
    double target = 90, angle, error;
    PIDLoop intake_pid;
    private IntakeArm intake = Robot.adaptor.intakeArm;

    public MotorToAngle(double target) {
        requires(intake);
        this.target = target;
        intake_pid = new PIDLoop(target, 0.01, 0.01, 1.5);
    }

    public MotorToAngle() {
        requires(intake);
    }

    @Override
    protected void initialize() {
        this.setInterruptible(true);
    }

    @Override
    protected void execute() {
//        System.out.println("shooter angle: " + intake.getAngle());
        angle = intake.getAngle();
        double output = intake_pid.getOutput(angle);
        if (output > 0.3) {
            output = 0.3;
        }
        //System.out.println("error " + (target - angle));
        //System.out.println("setting: " + output);

        intake.set(output);
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
