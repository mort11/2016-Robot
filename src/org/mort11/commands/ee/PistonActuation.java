package org.mort11.commands.ee;

import org.mort11.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PistonActuation extends Command {
	
    public PistonActuation() {
        requires(Robot.piston);
    }

    protected void initialize() {
    }

    protected void execute() {
    	System.out.println("piston is pressed" );
    	Robot.piston.setSolenoid(!Robot.piston.isEngaged());
    }

    protected boolean isFinished() {
    	
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
