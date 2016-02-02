package org.mort11.subsystems.ee;

import org.mort11.PortMap;
import org.mort11.util.EEConstants;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

public class Shooter extends Subsystem{

	private Talon armMotor;
	private DigitalInput limSwitch;
	private Encoder armEnc;
	private AnalogPotentiometer armPot;
	
	public Shooter(){
		armMotor = new Talon(PortMap.ARM_TALL);
		limSwitch = new DigitalInput(PortMap.ARM_LIM);
		armEnc = new Encoder(PortMap.ARM_ENC1, PortMap.ARM_ENC2, true, EncodingType.k4X);
		//armPot = new AnalogPotentiometer(RobotMap.ARM_POT);
		armEnc.setDistancePerPulse(EEConstants.INCHES_PER_PULSE);
		armEnc.reset();
	}
	
	public void initDefaultCommand() {
	}
	
	public void setSpeed(double speed){
		armMotor.set(speed);
	}
	
	public boolean islimSwitch(){
		return limSwitch.get();
	}
	
	public double getDistance(){
		return armEnc.getDistance();
	}
	
	public double getAngle(){
		return armPot.get();
	}	
}
