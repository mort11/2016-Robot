package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.commands.SubsystemStates;
import org.mort11.subsystems.ee.PistonIntake;

/**
 * Sets the intake to a specified state, or toggles if no state is set
 *
 * @author Matt Turi
 * @author Sahit Chintalapudi
 */
public class ToggleIntake extends Command {
    private SubsystemStates.Intake state = null;

    /**
     * Set intake state request
     *
     * @param state Intake state
     */
    public ToggleIntake(SubsystemStates.Intake state) {
        this.state = state;
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        if (state == null) {
            PistonIntake.toggleIntake();
        } else {
            PistonIntake.setPistonIntake(state);
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
