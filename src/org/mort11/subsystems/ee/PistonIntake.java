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
public class PistonIntake extends Subsystem {
    private static DoubleSolenoid solenoid = Robot.adaptor.pistonIntake;

    public static void setPistonIntake(SubsystemStates.Intake pistonRequest) {
        switch (pistonRequest) {
            case UP:
                solenoid.set(DoubleSolenoid.Value.kForward);
                break;
            case DOWN:
                solenoid.set(DoubleSolenoid.Value.kReverse);
                break;
        }
    }

    public static void hoodUp() {
        setPistonIntake(SubsystemStates.Intake.UP);
    }

    public static void hoodDown() {
    	setPistonIntake(SubsystemStates.Intake.DOWN);
    }

    public static void toggleIntake() {
    	//System.out.println("new state is: " + intakeUp);
        if (solenoid.get() == DoubleSolenoid.Value.kForward) {
            solenoid.set(DoubleSolenoid.Value.kReverse);
        } else {
            solenoid.set(DoubleSolenoid.Value.kForward);
        }
    }

    @Override
    protected void initDefaultCommand() {
    }
}

