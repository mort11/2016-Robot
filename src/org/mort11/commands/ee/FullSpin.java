package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.subsystems.ee.Flywheel;

/**
 *@author chsahit
 */
public class FullSpin extends Command {
    private Flywheel flywheel = Robot.adaptor.flywheel;

    public FullSpin() {
        requires(this.flywheel);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        this.flywheel.set(-1);
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
