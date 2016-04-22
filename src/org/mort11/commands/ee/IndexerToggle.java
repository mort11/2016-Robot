package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;

import org.mort11.commands.SubsystemStates;
import org.mort11.commands.auton.DriveStraight;
import org.mort11.subsystems.ee.Indexers;

/**
 * IndexersToggle - Toggles the Indexers
 *
 * @author Matt Turi
 * @author Jakob Shortell
 * 
 */
public class IndexerToggle extends Command {
	SubsystemStates.IndexerRequest state = null;
	public IndexerToggle() {
		
	}
	
	public IndexerToggle(SubsystemStates.IndexerRequest state) {
		this.state = state;
	}
    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
    	System.out.println("toggling");
    	if(state == null) {
    		Indexers.toggleIndexer();
    	}
    	else {
    		Indexers.setIndexer(state);
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
