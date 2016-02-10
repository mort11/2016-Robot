package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.commands.ee.PistonActuation;
import org.mort11.util.MORTSubsystem;

/**
 * Pneumatics - Does pneumatic things
 *
 * @author Ryan Thant <ryanthant1@gmail.com>
 * @author Seven Kurt <seven.kurt@motsd.org>
 */
public class Pneumatics extends Subsystem implements MORTSubsystem {
    private DoubleSolenoid solenoid;
    private boolean disabled, engaged;

    public Pneumatics(int engagedPort, int notEngagedPort) {
        this.solenoid = new DoubleSolenoid(30, engagedPort, notEngagedPort);
        this.engaged = false;
    }

    public void initDefaultCommand() {
        setDefaultCommand(new PistonActuation());
    }

    /**
     * Set piston state
     *
     * @param engage Piston state
     */
    public void set(boolean engage) {
        if (!disabled) {
            if (engage) {
                this.solenoid.set(DoubleSolenoid.Value.kForward);
            } else {
                this.solenoid.set(DoubleSolenoid.Value.kReverse);
            }
            this.engaged = engage;
            System.out.println("Piston State: " + engage);
        }
    }

    public boolean isEngaged() {
        return engaged;
    }

    /**
     * Disable the subsystem
     */
    @Override
    public void disable() {
        this.disabled = true;
    }

    /**
     * Check if subsystem is disabled
     *
     * @return Subsystem state
     */
    @Override
    public boolean isDisabled() {
        return this.disabled;
    }

    /**
     * Re-enable subsystem that is in a disabled state
     */
    @Override
    public void enable() {
        this.disabled = false;
    }
}
