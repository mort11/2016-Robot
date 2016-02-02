package org.mort11.subsystems.ee;

import org.mort11.commands.ee.PistonActuation;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pneumatics extends Subsystem {
	DoubleSolenoid sol;
	boolean engaged;

    public Pneumatics(int engagedPort, int notEngagedPort) {
        sol = new DoubleSolenoid(30,engagedPort, notEngagedPort);
        engaged = false;
    }

    public void initDefaultCommand() {
    	setDefaultCommand(new PistonActuation());
    }

    public void setSolenoid(boolean engage) {
        if (engage) {
            sol.set(DoubleSolenoid.Value.kForward); 
        } else {
            sol.set(DoubleSolenoid.Value.kReverse); 
        }
        engaged = engage;
        System.out.println("state 2: " + engage);
    }

    public boolean isEngaged() {
        return engaged;
    }
}
