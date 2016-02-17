package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.subsystems.ee.Shooter;

/**
 * FullSpin - Spin the flywheel at full speed
 *
 * @author chsahit
 */
public class FullSpin extends Command {
    private Shooter shooter = Robot.adaptor.shooter;

    public FullSpin() {
        requires(shooter);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        shooter.set(-1);
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
