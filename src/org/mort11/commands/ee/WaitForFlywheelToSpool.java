package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;

/**
 * Waits for Flywheel subsystem to report that it is spinning at the appropriate speed to fire
 *
 * @author Matt Turi
 * @author Sahit Chintalapudi
 */
public class WaitForFlywheelToSpool extends Command {
    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
        return Robot.adaptor.flywheel.getReadyFireState();
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}
