package org.mort11.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.subsystems.dt.DTSide;

/**
 * Shift - ToDo class description
 *
 * @author Matt Turi <mturi@mort11.org>
 */
public class Shift extends Command {

    int cntr = 0;

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        DTSide.shift();
        cntr++;
    }

    @Override
    protected boolean isFinished() {
        return cntr > 0;
    }

    @Override
    protected void end() {
        cntr = 0;

    }

    @Override
    protected void interrupted() {

    }
}
