package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.Robot;
import org.mort11.commands.SubsystemStates;

/**
 * HoodToggle - Intake hood
 *
 * @author Sahit Chintalapudi
 * @author Matt Turi
 */
public class Hood extends Subsystem {
    private static DoubleSolenoid solenoid = Robot.adaptor.hood;
    private static boolean hoodUp = true;

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

    public static void toggleHood() {
    	System.out.println("new state is: " + hoodUp);
        if (hoodUp) {
            solenoid.set(DoubleSolenoid.Value.kForward);
        } else {
            solenoid.set(DoubleSolenoid.Value.kReverse);
        }
        hoodUp = !hoodUp;
    }

    @Override
    protected void initDefaultCommand() {
    }
}

