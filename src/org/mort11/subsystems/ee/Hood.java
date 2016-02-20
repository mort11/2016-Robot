package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.commands.SubsystemStates;
import org.mort11.constants.Constants;

/**
 * Hood - Intake hood
 *
 * @author Sahit Chintalapudi
 * @author Matt Turi
 */
public class Hood extends Subsystem {
    private static DoubleSolenoid solenoid = new DoubleSolenoid(Constants.HOOD_SOLENOID_A, Constants.HOOD_SOLENOID_B);

    public static void setHood(SubsystemStates.HoodRequest hoodRequest) {
        switch (hoodRequest) {
            case POP:
                solenoid.set(DoubleSolenoid.Value.kForward);
                break;
            case STOW:
                solenoid.set(DoubleSolenoid.Value.kReverse);
                break;
        }
    }

    @Override
    protected void initDefaultCommand() {
    }
}

