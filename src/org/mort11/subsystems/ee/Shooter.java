package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.constants.EndEffectorConstants;
import org.mort11.constants.PDPConstants;
import org.mort11.sensors.SensorDealer;
import org.mort11.util.MORTSubsystem;
import org.mort11.util.powermanager.MORTCANTalon;

/**
 * Shooter - Controls the flywheel
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 * @author Matt Turi <mturi@mort11.org>
 */
public class Shooter extends Subsystem implements MORTSubsystem {
    private boolean disabled = false;
    private MORTCANTalon flywheel;

    public Shooter() {
        this.flywheel = new MORTCANTalon(EndEffectorConstants.FLYWHEEL_TALON_ID, PDPConstants.FLYWHEEL, "Flywheel");
    }

    @Override
    public void initDefaultCommand() {
    }

    /**
     * Set speed of flywheel
     *
     * @param speed Flywheel speed
     */
    // TODO: 2/10/16 Flywheel speed should be regulated by a PID loop
    public void set(double speed) {
        if (!this.disabled) {
            this.flywheel.set(speed);
        }
    }

    /**
     * Get speed flywheel is spinning at
     *
     * @return Flywheel speed
     */
    public double getSpeed() {
        return SensorDealer.getInstance().getRollerEncoder().getRate();
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
    
    public double getAngle() {
    	return 0;
    }
}
