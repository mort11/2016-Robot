package org.mort11.commands.dt.shifting;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.subsystems.dt.DTSide;

/**
 * ShiftDown - ToDo class description
 *
 * @author Matt Turi <mturi@mort11.org>
 */
public class ShiftDown extends Command {
    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        DTSide.shift(DTSide.Gear.LOW);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {

    }
}
