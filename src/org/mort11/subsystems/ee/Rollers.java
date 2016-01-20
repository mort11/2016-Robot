package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import org.mort11.util.EEConstants;

public class Rollers extends Subsystem{

	private Talon RollerMotor;
	private Encoder RollerEnc;
	
	protected void initDefaultCommand() {
		
	}

}
