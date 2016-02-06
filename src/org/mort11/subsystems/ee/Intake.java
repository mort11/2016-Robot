package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.constants.EndEffectorConstants;

/**
 * Intake - Intake
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class Intake extends Subsystem {
    private CANTalon intakeArm;
    private Encoder intakeEncoder;

    public Intake(String name, CANTalon intakeArm, Encoder intakeEncoder) {
        super(name);
        this.intakeArm = intakeArm;
        this.intakeEncoder = intakeEncoder;
        intakeEncoder.reset();
        intakeEncoder.setDistancePerPulse(EndEffectorConstants.INCHES_PER_PULSE);
    }

    protected void initDefaultCommand() {
    }

    public double getDistance() {
        System.out.println(intakeEncoder.get());
        return intakeEncoder.get();
    }

    public void setSpeed(double speed) {
        intakeArm.set(speed);
    }
}

