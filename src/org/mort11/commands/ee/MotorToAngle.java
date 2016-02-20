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
        intake_pid = new PIDLoop(target, 0.01, 0.01, 2.5);
    }

    protected void initialize() {
        this.setInterruptible(true);
    }

    protected void execute() {
        //System.out.println("flywheel angle: " + Intake.getAngle());
        System.out.println(intake.getAngle());
        angle = intake.getAngle();
        double output = intake_pid.getOutput(angle);
        if (output > 0.5) {
            output = 0.5;
        }
//    	System.out.println("error " + (target - angle));
//    	System.out.println("setting: " + output);

        intake.set(output);
        //intake.set(0.2);
//        if (flywheel.getAngle() < tarAng) {
//            flywheel.set(speed);
//        }
//        if (flywheel.getAngle() > tarAng) {
//            flywheel.set(-speed);
//        }
//        if (flywheel.getAngle() == tarAng) {
//            flywheel.set(0);
//    }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
