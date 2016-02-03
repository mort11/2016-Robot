package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.commands.ee.PistonActuation;

/**
 * Pneumatics - Does pneumatic things
 *
 * @author Ryan Thant <ryanthant1@gmail.com>
 * @author Seven Kurt <seven.kurt@motsd.org>
 */
public class Pneumatics extends Subsystem {
    DoubleSolenoid solenoid;
    boolean engaged;

    public Pneumatics(int engagedPort, int notEngagedPort) {
        solenoid = new DoubleSolenoid(engagedPort, notEngagedPort);
        engaged = false;
    }

    public void initDefaultCommand() {
        setDefaultCommand(new PistonActuation());
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