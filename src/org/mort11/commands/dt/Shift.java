package org.mort11.commands.dt;

import edu.wpi.first.wpilibj.command.Command;

import org.mort11.Robot;
import org.mort11.subsystems.dt.DTSide;

/**
 * Shift - Toggles gear shift
 *
 * @author Matt Turi <mturi@mort11.org>
 */
public class Shift extends Command {

    @Override
    protected void initialize() {
    	System.out.println("calling command");
    }

    @Override
    protected void execute() {
        DTSide.shift();
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