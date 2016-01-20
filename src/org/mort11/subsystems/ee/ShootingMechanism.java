package org.mort11.subsystems.ee;
import org.mort11.RobotMap;
import util.EEConstants;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

public class ShootingMechanism extends Subsystem{

	private Talon ArmMotor;
	private DigitalInput LimSwitch;
	private Encoder ArmEnc;
	private AnalogPotentiometer ArmPot;
	
	public ShootingMechanism(){
		ArmMotor = new Talon(RobotMap.Arm_Tal1);
		LimSwitch = new DigitalInput(RobotMap.Arm_Lim);
		ArmEnc = new Encoder(RobotMap.Arm_Enc1, RobotMap.Arm_Enc2, true, EncodingType.k4X);
		ArmPot = new AnalogPotentiometer(RobotMap.Arm_Pot);
		ArmEnc.setDistancePerPulse(EEConstants.INCHES_PER_PULSE);
		ArmEnc.reset();
	}
	
	public void initDefaultCommand() {
	}
	
	public void setSpeed(double speed){
		ArmMotor.set(speed);
	}
	
	public boolean isLimSwitch(){
		return LimSwitch.get();
	}
	
	public double getDistance(){
		return ArmEnc.getDistance();
	}
	
	public double getAngle(){
		return ArmPot.get();
	}	
}
