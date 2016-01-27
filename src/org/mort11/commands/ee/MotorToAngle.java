package org.mort11.commands.ee;

import org.mort11.Robot;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.subsystems.ee.ShootingMechanism;


public class MotorToAngle extends Command{

	private ShootingMechanism Shooter = new ShootingMechanism();
	double speed = .5;
	
	  public MotorToAngle() {
	        requires(Robot.ShootMech);
	    }

	    protected void initialize() {
	    
	    }

	    protected void execute() {
	    	if(Shooter.getAngle() < 90){
	    		Shooter.setSpeed(speed);
		    }
	    	if(Shooter.getAngle() > 90){
		    	Shooter.setSpeed(-speed);
		    }
	    	if(Shooter.getAngle() == 90){
	    		Shooter.setSpeed(0);
	    	}
	    }

	    protected boolean isFinished() {
	        return true;
	    }

	    protected void end() {
	    }

	    protected void interrupted() {
	    }
}
