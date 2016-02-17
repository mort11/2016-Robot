package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.constants.EEConstants;
import org.mort11.constants.PDPConstants;
import org.mort11.util.powermanager.MORTCANTalon;

/**
 * Rollers - Controls the intakeArm roller
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class Rollers extends Subsystem {
    private MORTCANTalon rollers;

    public Rollers() {
        this.rollers = new MORTCANTalon(EEConstants.ROLLER_TALON_ID, PDPConstants.ROLLERS, false);
        rollers.reset();
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

    /**
     * Get distance rollers have gone
     *
     * @return Distance
     */
    public double getDistance() {
        return rollers.getEncPosition();
    }
}