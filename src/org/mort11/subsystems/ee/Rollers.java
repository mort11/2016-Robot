package org.mort11.subsystems.ee;

import org.mort11.constants.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Rollers - Controls the intakeArm roller
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class Rollers extends Subsystem {
    private CANTalon rollers;

    public Rollers() {
        this.rollers = new CANTalon(Constants.ROLLER_TALON_ID);
}

    @Override
    protected void initDefaultCommand() {
    }

    /**
     * Set speed of intakeArm rollers
     *
     * @param speed Speed of rollers
     */
    public void set(double speed) {
        rollers.set(speed);
        System.out.println("getting roller speed: " + rollers.get());
    }

    /**
     * Get distance rollers have gone
     *
     * @return Distance
     */
    public double getDistance() {
        return rollers.getEncPosition();
    }
}