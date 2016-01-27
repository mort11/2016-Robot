package org.mort11.commands.ee;

import org.mort11.Robot;
import org.mort11.subsystems.ee.Pneumatics;

import edu.wpi.first.wpilibj.command.Command;

public class PistonActuation extends Command {
	Pneumatics piston = Robot.piston; 
	
    public PistonActuation() {
        requires(piston);
    }

    protected void initialize() {
    }

    protected void execute() {
    	System.out.println("piston is pressed" );
    	piston.setSolenoid(!piston.isEngaged());
    }

    protected boolean isFinished() {
    	
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
