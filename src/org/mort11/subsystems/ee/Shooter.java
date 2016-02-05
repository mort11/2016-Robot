package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.constants.EndEffectorConstants;
import org.mort11.sensors.SensorDealer;

/**
 * Shooter - Controls the flywheel
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 */
public class Shooter extends Subsystem {
    private CANTalon armMotor;

    public Shooter() {
        armMotor = new CANTalon(EndEffectorConstants.ARM_TALON_PORT);
    }

    public void initDefaultCommand() {
    }

    public void set(double speed) {
        armMotor.set(speed);
    }

    public boolean islimSwitch() {
        return SensorDealer.getInstance().getArmLimitSwitch().get();
    }

    public double getDistance() {
        return SensorDealer.getInstance().getArmEncoder().getDistance();
    }

    public double getAngle() {
        return SensorDealer.getInstance().getArmPot().get();
    }
}
