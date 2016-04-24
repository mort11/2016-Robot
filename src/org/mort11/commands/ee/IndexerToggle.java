package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;

import org.mort11.Robot;
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
	boolean isFinished = false;
	
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
    	if(state == null && Robot.oi.getUpPOV()) {
    		System.out.println("IF");
    		Indexers.setIndexer(SubsystemStates.IndexerRequest.DOWN);
    	} else if(state == null && Robot.oi.getDownPOV()) {
    		System.out.println("ELSE");
    		Indexers.setIndexer(SubsystemStates.IndexerRequest.UP
    				); 
    	}
    	else if(state != null){
    		System.out.println("ELSE IF");
    		Indexers.setIndexer(state);
    		isFinished = true;
    	}
    }
    

    @Override
    protected boolean isFinished() {
    	System.out.println("IS FINISHED");
        return isFinished;
    }

    @Override
    protected void end() {
    	System.out.println("END");
    	isFinished = false;
    }

    @Override
    protected void interrupted() {
    }
}
