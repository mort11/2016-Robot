package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.subsystems.ee.*;


public class IntakeRollers extends Command{
	
	double speed;
	private Rollers Rollers = Robot.Rollers;

	public IntakeRollers(){
		this.speed = speed;
		requires(Rollers);
	}
	
	protected void initialize() {
		
	}

	protected void execute() {
		if(false){
			Rollers.set(0);
		}else{
			Rollers.set(speed);
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
