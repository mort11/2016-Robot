package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.command.Subsystem;
<<<<<<< HEAD

/**
 * Intake - Intake
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class Intake extends Subsystem {
    public void initDefaultCommand() {
=======
import org.mort11.RobotMap;
import org.mort11.util.EEConstants;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
public class Intake extends Subsystem {
		private Talon intakeArm;

		private Encoder intakeEnc;
    

    public Intake() {
    	intakeArm = new Talon(RobotMap.INTAKE_TALON);
    	intakeEnc = new Encoder(RobotMap.INTAKE_ENCODER1,RobotMap.INTAKE_ENCODER2 );
    	intakeEnc.reset();
        intakeEnc.setDistancePerPulse(EEConstants.INCHES_PER_PULSE);
>>>>>>> 830d2dde43e2d35cf89f028ef516f6ec618b4fa7
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