package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.commands.SubsystemStates;

/**
 * Hood - ToDo class description
 *
 * @author Matt Turi
 */
public class Hood extends Command {
    private SubsystemStates.HoodRequest hood;

    public Hood(SubsystemStates.HoodRequest direction) {
        this.hood = direction;
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {

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
