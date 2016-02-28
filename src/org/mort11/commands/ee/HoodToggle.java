package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.subsystems.ee.Hood;

/**
 * HoodToggle - Toggles the hood
 *
 * @author Matt Turi
 */
public class HoodToggle extends Command {
    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
//    	System.out.println("toggling");
        Hood.toggleHood();
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
