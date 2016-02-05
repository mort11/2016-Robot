package org.mort11.subsystems.ee;

import org.mort11.sensors.SensorDealer;
import org.mort11.util.MORTSubsystem;
import org.mort11.constants.EndEffectorConstants;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.HardwareAdaptor;

/**
 * Intake - Intake
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class Intake extends Subsystem implements MORTSubsystem{
		private Talon intakeArm;
		boolean isDisabled = false;

    public Intake() {
    	intakeArm = new Talon(EndEffectorConstants.ARM_TALON_PORT);
    	SensorDealer.getInstance().getArmEncoder().reset();
    	SensorDealer.getInstance().getArmEncoder().setDistancePerPulse(EndEffectorConstants.INCHES_PER_PULSE);
        
    }
	protected void initDefaultCommand() {
		
	}
	public double getDistance(){
		System.out.println(SensorDealer.getInstance().getArmEncoder().get());
		return SensorDealer.getInstance().getArmEncoder().get();
	}
	public void setSpeed(double speed) {
		if(isDisabled == false){
			intakeArm.set(speed);	
		}
	}
	public void disable(){
		isDisabled = true;
	}

}
