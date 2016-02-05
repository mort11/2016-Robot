package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Hood - ToDo description
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class Hood extends Subsystem {
    DoubleSolenoid solenoid;
    boolean engaged;

    public void initDefaultCommand() {
    }

    public void popHood() {
        solenoid.set(DoubleSolenoid.Value.kForward);
        engaged = true;
    }

    public void stowHood() {
        solenoid.set(DoubleSolenoid.Value.kReverse);
        engaged = false;
    }

    public void toggleHood() {
        setSolenoid(!engaged);
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

