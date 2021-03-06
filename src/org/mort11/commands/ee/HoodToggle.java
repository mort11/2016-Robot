package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.commands.SubsystemStates;
import org.mort11.subsystems.ee.Hood;

/**
 * Toggles the hood
 *
 * @author Matt Turi
 */
public class HoodToggle extends Command {
    private SubsystemStates.HoodState state = null;

    public HoodToggle() {
    }

    /**
     * Sets the hood to a specified state
     *
     * @param state Hood state
     */
    HoodToggle(SubsystemStates.HoodState state) {
        this.state = state;
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        if (state == null) {
            Hood.toggleHood();
        } else {
            Hood.setHood(state);
        }
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
