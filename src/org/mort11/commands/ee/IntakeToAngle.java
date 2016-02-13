package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.subsystems.ee.IntakeArm;

/**
 * IntakeToAngle - Move motor to angle
 *
 * @author Ryan Thant <ryanthant1@gmail.com>
 * @author Seven Kurt <seven.kurt@motsd.org>
 * @author Ryan O'Toole <ryan.otoole@motsd.org>
 */
public class IntakeToAngle extends Command {

    double speed = 0.5;
    double tarAng = 90;
    private IntakeArm intakeArm = Robot.adaptor.intakeArm;

    public IntakeToAngle() {
        requires(intakeArm);
    }

    protected void initialize() {
    }

    protected void execute() {
        if (intakeArm.getAngle() < tarAng) {
            intakeArm.set(speed);
        }
        if (intakeArm.getAngle() > tarAng) {
            intakeArm.set(-speed);
        }
        if (intakeArm.getAngle() == tarAng) {
            intakeArm.set(0);
        }
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}