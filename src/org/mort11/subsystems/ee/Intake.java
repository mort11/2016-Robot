package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Intake - Intake
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class Intake extends Subsystem {
    private CANTalon intakeArm;
    private Encoder intakeEnc;

    public Intake() {
//    	intakeArm = Robot.adaptor.intake;
//    	intakeEnc = new Encoder(RobotMap.INTAKE_ENCODER1,RobotMap.INTAKE_ENCODER2 );
//    	intakeEnc.reset();
//        intakeEnc.setDistancePerPulse(EEConstants.INCHES_PER_PULSE);
    }

    protected void initDefaultCommand() {

    }

    public double getDistance() {
        System.out.println(intakeEnc.get());
        return intakeEnc.get();
    }

    public void setSpeed(double speed) {
        intakeArm.set(speed);
    }
}

