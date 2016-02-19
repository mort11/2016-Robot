package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.subsystems.ee.Shooter;

/**
 * JoystickShooter - Run flywheel at joystick value
 * 
 * @author chsahit
 */
public class JoystickShooter extends Command {
    private Shooter shooter = Robot.adaptor.shooter;

    public JoystickShooter() {
        requires(shooter);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        shooter.set(Robot.oi.getEE_Z());
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
