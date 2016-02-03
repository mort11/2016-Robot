package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.HardwareAdaptor;
import org.mort11.subsystems.ee.Shooter;

public class MotorToAngle extends Command {

    double speed = .5;
    private Shooter shooter = HardwareAdaptor.shooter;

    public MotorToAngle() {
        requires(shooter);
    }

    protected void initialize() {
    }

    protected void execute() {
        if (shooter.getAngle() < 90) {
            shooter.setSpeed(speed);
        }
        if (shooter.getAngle() > 90) {
            shooter.setSpeed(-speed);
        }
        if (shooter.getAngle() == 90) {
            shooter.setSpeed(0);
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
