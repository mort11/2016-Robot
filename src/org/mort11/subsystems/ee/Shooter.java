package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.commands.ee.JoystickShooter;
import org.mort11.constants.EndEffectorConstants;
import org.mort11.constants.PDPConstants;
import org.mort11.util.powermanager.MORTCANTalon;

/**
 * Shooter - Controls the flywheel
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 * @author Matt Turi <mturi@mort11.org>
 */
public class Shooter extends Subsystem {
    private MORTCANTalon flywheel;

    public Shooter() {
        this.flywheel = new MORTCANTalon(EndEffectorConstants.FLYWHEEL_TALON_ID, PDPConstants.FLYWHEEL, "Flywheel");
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new JoystickShooter());
    }

    /**
     * Set speed of flywheel
     *
     * @param speed Flywheel speed
     */
    // TODO: 2/10/16 Flywheel speed should be regulated by a PID loop
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
    
    public double getAngle() {
    	return 0;
    }
}
