package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.util.MORTSubsystem;

/**
 * Hood - ToDo description
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class Hood extends Subsystem implements MORTSubsystem {
    DoubleSolenoid solenoid;
    boolean engaged;
    public void initDefaultCommand() {

    }

    public void popHood() {
        if (!isDisabled) {
            solenoid.set(DoubleSolenoid.Value.kForward);
            engaged = true;
        }
    }
    public void stowHood() {
        engaged = false;
        if (!isDisabled) {
            solenoid.set(DoubleSolenoid.Value.kReverse);
            engaged = false;
        }
    }
    public void toggleHood() {
        if (!isDisabled) {
            setSolenoid(!engaged);
        }
    }
    
    public void setSolenoid(boolean engage) {
        if (engage) {
            solenoid.set(DoubleSolenoid.Value.kForward);
        } else {
            solenoid.set(DoubleSolenoid.Value.kReverse);
        }
        engaged = engage;
        System.out.println("state 2: " + engage);
    }

    public boolean isEngaged() {
        return engaged;
    }
}

