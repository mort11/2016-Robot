package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.constants.EndEffectorConstants;
import org.mort11.sensors.SensorDealer;
import org.mort11.util.MORTSubsystem;

/**
 * Shooter - Controls the flywheel
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 */
public class Shooter extends Subsystem implements MORTSubsystem {
    private boolean disabled = false;
    private CANTalon armMotor;

    public Shooter() {
        armMotor = new CANTalon(EndEffectorConstants.ARM_TALON_ID);
    }

    public void initDefaultCommand() {
    }

    public void set(double speed) {
        if (!disabled) {
            armMotor.set(speed);
        }
    }

    public boolean islimSwitch() {
        return SensorDealer.getInstance().getArmLimitSwitch().get();
    }

    public double getDistance() {
        return SensorDealer.getInstance().getIntakeArmEncoder().getDistance();
    }

    public double getAngle() {
        return SensorDealer.getInstance().getArmPot().get();
    }

    public double getRate() {
        return SensorDealer.getInstance().getRollerEncoder().getRate();
    }

    @Override
    public void disable() {
        this.disabled = true;
    }

    @Override
    public void enable() {
        this.disabled = false;
    }
}
