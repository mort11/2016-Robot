package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.Robot;

/**
 * Hood - Intake hood
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 * @author Matt Turi <mturi@mort11.org>
 */
public class Hood extends Subsystem {
    private DoubleSolenoid solenoid = Robot.adaptor.hood;
    private boolean hoodUp;

    /**
     * Set hood to up position
     */
    public void popHood() {
        setHood(true);
        this.hoodUp = true;
    }

    /**
     * Set hood to stowed position
     */
    public void stowHood() {
        setHood(false);
        this.hoodUp = false;
    }

    /**
     * Toggle hood state between stowed and up
     */
    public void toggleHood() {
        setHood(!hoodUp);
    }

    public void setHood(boolean engage) {
        if (engage) {
            solenoid.set(DoubleSolenoid.Value.kForward);
        } else {
            solenoid.set(DoubleSolenoid.Value.kReverse);
        }
        this.hoodUp = engage;
    }

    @Override
    protected void initDefaultCommand() {
    }
}

