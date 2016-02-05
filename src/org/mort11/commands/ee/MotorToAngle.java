package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.subsystems.ee.Shooter;

/**
 * MotorToAngle - Move motor to angle
 *
 * @author Ryan Thant <ryanthant1@gmail.com>
 * @author Seven Kurt <seven.kurt@motsd.org>
 * @author Ryan O'Toole <ryan.otoole@motsd.org>
 */
public class MotorToAngle extends Command {
    double speed = .5;
    double tarAng = 90;
    private Shooter shooter = Robot.adaptor.shooter;

    public MotorToAngle() {
        requires(shooter);
    }

    protected void initialize() {
    }

    protected void execute() {
        if (shooter.getAngle() < tarAng) {
            shooter.set(speed);
        }
        if (shooter.getAngle() > tarAng) {
            shooter.set(-speed);
        }
        if (shooter.getAngle() == tarAng) {
            shooter.set(0);
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}