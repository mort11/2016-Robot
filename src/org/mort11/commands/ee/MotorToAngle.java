package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;

import org.mort11.Robot;
import org.mort11.subsystems.ee.IntakeArm;
import org.mort11.subsystems.ee.Shooter;

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
    private IntakeArm Intake = Robot.adaptor.intakeArm;

    public MotorToAngle() {
        requires(Intake);
    }

    protected void initialize() {
    	this.setInterruptible(true);
    }

    protected void execute() {
    	System.out.println("shooter angle: " + Intake.getAngle());
//        if (shooter.getAngle() < tarAng) {
//            shooter.set(speed);
//        }
//        if (shooter.getAngle() > tarAng) {
//            shooter.set(-speed);
//        }
//        if (shooter.getAngle() == tarAng) {
//            shooter.set(0);
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
