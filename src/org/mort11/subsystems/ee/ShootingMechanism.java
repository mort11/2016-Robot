package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.constants.EndEffectorConstants;
import org.mort11.sensors.SensorDealer;

/**
 * ShootingMechanism - Subset of Shooter for controlling flywheel mechanism
 *
 * @author Matt Turi <mturi@mort11.org>
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 */
public class ShootingMechanism extends Subsystem {

    private Talon armMotor;

    public ShootingMechanism() {
        armMotor = new Talon(EndEffectorConstants.ARM_TALON_PORT);
        SensorDealer.getInstance().getArmEncoder().reset();
        SensorDealer.getInstance().getArmEncoder().setDistancePerPulse(EndEffectorConstants.INCHES_PER_PULSE);
    }

    public void initDefaultCommand() {
    }

    public void setSpeed(double speed) {
        armMotor.set(speed);
    }

    public boolean isLimSwitch() {
        return SensorDealer.getInstance().getArmLimitSwitch().get();
    }

    public double getDistance() {
        return SensorDealer.getInstance().getArmEncoder().getDistance();
    }

    public double getAngle() {
        return SensorDealer.getInstance().getArmPot().get();
    }
}
