package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

import org.mort11.RobotMap;
import org.mort11.util.EEConstants;

public class Rollers extends Subsystem{

	private Talon RollerMotor;
	private Encoder RollerEnc;
	
	public Rollers(){
		RollerMotor = new Talon(RobotMap.Roll_Tal1);
		RollerEnc = new Encoder(RobotMap.Roll_Enc1, RobotMap.Roll_Enc2, true, EncodingType.k4X);
		RollerEnc.setDistancePerPulse(EEConstants.INCHES_PER_PULSE);
		RollerEnc.reset();
	}
	
	protected void initDefaultCommand() {
		
	}
	
	public void set(double speed){
		RollerMotor.set(speed);
	}
	
	public double getDistance(){
		return RollerEnc.getDistance();
	}

}
