package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.command.Subsystem;
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 419908c65a86f490a546220fe1565cd093d66b4e

/**
 * Intake - Intake
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
<<<<<<< HEAD
public class Intake extends Subsystem {
    public void initDefaultCommand() {
=======
import org.mort11.RobotMap;
import org.mort11.util.EEConstants;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
=======
>>>>>>> 419908c65a86f490a546220fe1565cd093d66b4e
public class Intake extends Subsystem {
		private Talon intakeArm;
    
		private Encoder intakeEnc;
    

    public Intake() {
    	intakeArm = new Talon(RobotMap.INTAKE_TALON);
    	intakeEnc = new Encoder(RobotMap.INTAKE_ENCODER1,RobotMap.INTAKE_ENCODER2 );
    	intakeEnc.reset();
        intakeEnc.setDistancePerPulse(EEConstants.INCHES_PER_PULSE);
<<<<<<< HEAD
>>>>>>> 830d2dde43e2d35cf89f028ef516f6ec618b4fa7
=======
>>>>>>> 419908c65a86f490a546220fe1565cd093d66b4e
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

