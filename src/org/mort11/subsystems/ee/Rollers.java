package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.RobotMap;
import edu.wpi.first.wpilibj.Talon;

public class Rollers extends Subsystem {
    	private Talon rollerMotor;
    
	public Rollers() {
		rollerMotor = new Talon(RobotMap.ROLL_TALL);
	}
	public void setSpeed(double speed) {
		rollerMotor.set(speed);
	}
    public void initDefaultCommand() {

    }
}

