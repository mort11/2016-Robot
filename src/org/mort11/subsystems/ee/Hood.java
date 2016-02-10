package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.constants.EndEffectorConstants;
import org.mort11.util.MORTSubsystem;

/**
 * Hood - ToDo description
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 * @author Matt Turi <mturi@mort11.org>
 */
public class Hood extends Subsystem implements MORTSubsystem {
    private DoubleSolenoid solenoid = new DoubleSolenoid(EndEffectorConstants.HOOD_SOLENOID_A, EndEffectorConstants.HOOD_SOLENOID_B);
    private boolean hoodUp, disabled;

    public void popHood() {
        if (!disabled) {
            setHood(true);
            this.hoodUp = true;
        }
    }

    public void stowHood() {
        if (!disabled) {
            setHood(false);
            this.hoodUp = false;
        }
    }

    public void toggleHood() {
        if (!disabled) {
            setHood(!hoodUp);
        }
    }

    public void setHood(boolean engage) {
        if (engage) {
            solenoid.set(DoubleSolenoid.Value.kForward);
        } else {
            solenoid.set(DoubleSolenoid.Value.kReverse);
        }
        this.hoodUp = engage;
    }

    public boolean isHoodUp() {
        return this.hoodUp;
    }

    @Override
    public void disable() {
        this.disabled = true;
    }

    @Override
    public void enable() {
        this.disabled = false;
    }

    @Override
    protected void initDefaultCommand() {

    }
}

