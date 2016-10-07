package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.commands.SubsystemStates;
import org.mort11.subsystems.ee.Indexers;

/**
 * Toggles the Indexers
 *
 * @author Matt Turi
 * @author Jakob Shortell
 */
public class IndexerToggle extends Command {

    public IndexerToggle() {
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        if (Robot.oi.getUpPOV()) {
            Indexers.setIndexer(SubsystemStates.IndexersState.DOWN);
            System.out.println("index down");
        } else if (Robot.oi.getDownPOV()) {
            Indexers.setIndexer(SubsystemStates.IndexersState.UP);
            System.out.println("index up");
        }
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
