package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.subsystems.ee.Flywheel;

/**
 * Set the flywheel speed based off of the operator's throttle
 *
 * @author Sahit Chintalapudi
 */
public class JoystickShooter extends Command {
    private Flywheel flywheel = Robot.adaptor.flywheel;

    public JoystickShooter() {
        requires(flywheel);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        flywheel.set(Robot.oi.getEE_Z());
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
