package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.commands.ee.JoystickShooter;
import org.mort11.constants.Constants;
import org.mort11.util.powermanager.MORTCANTalon;

/**
 * Flywheel - Controls the flywheel
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 * @author Matt Turi <mturi@mort11.org>
 */
public class Flywheel extends Subsystem {
    double initEncoderValue;
    private MORTCANTalon flywheel;

    public Flywheel() {
        this.flywheel = new MORTCANTalon(Constants.FLYWHEEL_TALON_ID, Constants.PDP_FLYWHEEL, false);
        initEncoderValue = flywheel.getEncPosition();
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
        //System.out.println("outputting: " + flywheel.get() );
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
        return flywheel.getEncPosition();
    }
}
