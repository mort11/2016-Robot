package org.mort11.util.powermanager;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import org.mort11.util.MORTSubsystem;

/**
 * MORTCANTalon - Implementation of CANTalon with ability to monitor current draw
 *
 * @author Matt Turi <mturi@mort11.org>
 */
public class MORTCANTalon extends CANTalon implements MORTSubsystem {
    private PowerDistributionPanel pdp = new PowerDistributionPanel();
    private boolean disabled = false;
    private boolean reverse;

    /**
     * Creates a CANTalon that controls a single motor
     *
     * @param deviceNumber CAN ID for talon
     * @param reverse      Reverse motor
     */
    public MORTCANTalon(int deviceNumber, boolean reverse) {
        super(deviceNumber);
        this.reverse = reverse;
        MotorHolder.motors.add(this);
    }

    /**
     * Wraps talon set method and sets speed to 0 if subsystem is disabled
     *
     * @param speed Motor speed
     */
    @Override
    public void set(double speed) {
        if (this.disabled) {
            super.set(0);
        } else {
            super.set(this.reverse ? speed * -1 : speed);
        }
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
     * Disable the subsystem
     */
    @Override
    public void disable() {
        this.disabled = true;
    }

    /**
     * Re-enable subsystem that is in a disabled state
     */
    @Override
    public void enable() {
        this.disabled = false;
    }

    /**
     * Get voltage
     */
    public double getVoltage() {
        return super.getOutputVoltage();
    }
}
