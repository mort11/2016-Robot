package org.mort11.subsystems.ee;

import org.mort11.commands.ee.ActuateBrake;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Pneumatics - Does pneumatic things
 *
 * @author Ryan Thant <ryanthant1@gmail.com>
 * @author Seven Kurt <seven.kurt@motsd.org>
 */
public class Brake extends Subsystem {
    DoubleSolenoid solenoid;
    boolean engaged;

    public Brake(int engagedPort, int notEngagedPort) {
        this.solenoid = new DoubleSolenoid(30, engagedPort, notEngagedPort);
        this.engaged = false;
    }

    public void initDefaultCommand() {
        setDefaultCommand(new ActuateBrake());
    }

    /**
     * Set piston state
     *
     * @param engage Piston state
     */
    public void set(boolean engage) {
        if (engage) {
            this.solenoid.set(DoubleSolenoid.Value.kForward);
        } else {
            this.solenoid.set(DoubleSolenoid.Value.kReverse);
        }
        this.engaged = engage;
        System.out.println("Piston State: " + engage);
    }

    public boolean isEngaged() {
        return engaged;
    }
}
