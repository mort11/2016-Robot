package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.constants.EndEffectorConstants;
import org.mort11.constants.PDPConstants;
import org.mort11.sensors.SensorDealer;
import org.mort11.util.MORTSubsystem;
import org.mort11.util.powermanager.MORTCANTalon;

/**
 * Rollers - Controls the intake roller
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class Rollers extends Subsystem implements MORTSubsystem {
    private MORTCANTalon rollers;
    private Encoder rollerEnc = SensorDealer.getInstance().getRollerEncoder();
    private boolean disabled;

    public Rollers() {
        this.rollers = new MORTCANTalon(EndEffectorConstants.ROLLER_TALON_ID, PDPConstants.ROLLERS, "Rollers");
        rollerEnc.setDistancePerPulse(EndEffectorConstants.INCHES_PER_PULSE);
        rollerEnc.reset();
    }

    @Override
    protected void initDefaultCommand() {
    }

    /**
     * Set speed of intake rollers
     *
     * @param speed Speed of rollers
     */
    public void set(double speed) {
        if (!disabled) {
            rollers.set(speed);
        }
    }

    /**
     * Get distance rollers have gone
     *
     * @return Distance
     */
    public double getDistance() {
        return rollerEnc.getDistance();
    }

    /**
     * Disable the subsystem
     */
    @Override
    public void disable() {
        this.disabled = true;
    }

    /**
     * Check if subsystem is disabled
     *
     * @return Subsystem state
     */
    @Override
    public boolean isDisabled() {
        return this.disabled;
    }

    /**
     * Re-enable subsystem that is in a disabled state
     */
    @Override
    public void enable() {
        this.disabled = false;
    }
}