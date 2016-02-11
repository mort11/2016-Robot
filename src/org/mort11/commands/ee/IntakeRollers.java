package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.constants.EndEffectorConstants;
import org.mort11.subsystems.ee.Rollers;

/**
 * IntakeRollers - Spins the intake rollers at a 
 *
 * @author Ryan O'Toole <ryan.otoole@motsd.org>
 * @author Ryan Thant <ryanthant1@gmail.com>
 */
public class IntakeRollers extends Command {
	public enum Move{
		FOREWARD, BACKWARD, STOP, 
	}
    boolean in, out;
    Rollers roller = Robot.adaptor.rollers;
    Move move;

    public IntakeRollers(Move move) {
    	this.move = move;
    }

    protected void initialize() {
    }

    protected void execute() {
       switch (move){
       		case FOREWARD:
       			roller.set(EndEffectorConstants.ROLLER_SPEED);
       			break;
   
       		case BACKWARD:
       			roller.set(EndEffectorConstants.ROLLER_SPEED);
       			break;
       		case STOP:
       			roller.set(EndEffectorConstants.ROLLER_SPEED);
       }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    }

}
