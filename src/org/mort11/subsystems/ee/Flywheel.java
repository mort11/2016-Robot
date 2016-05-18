package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.constants.Constants;

/**
 * Controls the flywheel
 *
 * @author Sahit Chintalapudi
 * @author Matthew Krzyzanowski
 * @author Matt Turi
 */
public class Flywheel extends Subsystem {
    private boolean readyFireState = false;
    private CANTalon flywheel;

    public Flywheel() {
        this.flywheel = new CANTalon(Constants.FLYWHEEL_TALON_ID);
    }

    public boolean getReadyFireState() {
        return readyFireState;
    }

    public void setReadyFireState(boolean isSpunUp) {
        this.readyFireState = isSpunUp;
    }

    @Override
    public void initDefaultCommand() {
    }

    /**
     * Set speed of flywheel
     *
     * @param speed Flywheel speed
     */
    public void set(double speed) {
        this.flywheel.set(speed);
    }

    /**
     * Get speed flywheel is spinning at
     *
     * @return Flywheel speed
     */
    public double getSpeed() {
        return flywheel.getEncVelocity();
    }
}
