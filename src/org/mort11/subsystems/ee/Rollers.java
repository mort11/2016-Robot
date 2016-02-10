package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.constants.EndEffectorConstants;
import org.mort11.sensors.SensorDealer;

/**
 * Rollers - Controls the intake roller
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class Rollers extends Subsystem {
    private CANTalon roller = new CANTalon(EndEffectorConstants.ARM_TALON_ID);
    private Encoder rollerEnc = SensorDealer.getInstance().getRollerEncoder();

    public Rollers() {
        rollerEnc.setDistancePerPulse(EndEffectorConstants.INCHES_PER_PULSE);
        rollerEnc.reset();
    }

    protected void initDefaultCommand() {
    }

    public void set(double speed) {
        roller.set(speed);
    }

    public double getDistance() {
        return rollerEnc.getDistance();
    }
}