package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.RobotMap;
import org.mort11.constants.EndEffectorConstants;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

/**
 * Intake - Intake
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class Intake extends Subsystem {
		private Talon intakeArm;

		private Encoder intakeEnc;
    

    public Intake() {
    	intakeArm = new Talon(RobotMap.INTAKE_TALON);
    	intakeEnc = new Encoder(RobotMap.INTAKE_ENCODER1,RobotMap.INTAKE_ENCODER2 );
    	intakeEnc.reset();
        intakeEnc.setDistancePerPulse(EndEffectorConstants.INCHES_PER_PULSE);
    }
	protected void initDefaultCommand() {
		
	}
	public double getDistance(){
		System.out.println(intakeEnc.get());
		return intakeEnc.get();
	}
	public void setSpeed(double speed) {
		intakeArm.set(speed);	
		}
	
}