package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.constants.Constants;

/**
 * Controls the intakeArm roller
 *
 * @author Sahit Chintalapudi
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
    }
}