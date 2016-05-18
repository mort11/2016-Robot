package org.mort11.commands.dt;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.subsystems.dt.DTSide;

/**
 * Base command for controlling left and right drivetrain movement
 *
 * @author Will Marshall
 * @author Matthew Krzyzanowski
 * @author Matt Turi
 */
abstract class DriveLinear extends Command {
    private DTSide side;

    DriveLinear(DTSide drivetrain) {
        requires(drivetrain);
        setInterruptible(true);
        this.side = drivetrain;
    }

    @Override
    protected void initialize() {
    }

    protected abstract double getSpeed();

    @Override
    protected void execute() {
        side.set(getSpeed());
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
