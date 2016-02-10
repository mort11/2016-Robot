package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.constants.EndEffectorConstants;
import org.mort11.sensors.SensorDealer;

/**
 * IntakeArm - Intake arm
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class Rollers extends Subsystem {

    private CANTalon rollerMotor = new CANTalon(EndEffectorConstants.ARM_TALON_PORT);
    private Encoder rollerEnc = SensorDealer.getInstance().getShooterEncoder();

    public Rollers() {
        rollerEnc.setDistancePerPulse(EndEffectorConstants.INCHES_PER_PULSE);
        rollerEnc.reset();
}

    protected void initDefaultCommand() {

    }

    public void set(double speed) {
        rollerMotor.set(speed);
    }

    public double getDistance() {
        return rollerEnc.getDistance();
    }
}