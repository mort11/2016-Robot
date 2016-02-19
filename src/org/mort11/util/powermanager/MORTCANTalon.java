package org.mort11.util.powermanager;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

/**
 * MORTCANTalon - Implementation of CANTalon with ability to monitor current draw
 *
 * @author Matt Turi <mturi@mort11.org>
 */
public class MORTCANTalon extends CANTalon {
    private PowerDistributionPanel pdp = new PowerDistributionPanel();
    private int pdpSlot;
    private boolean disabled = false;
    private boolean reverse;
    private CANTalon talon;

    /**
     * Creates a CANTalon that controls a single motor
     *
     * @param deviceNumber CAN ID for talon
     * @param reverse      Reverse motor
     */
    public MORTCANTalon(int deviceNumber, int pdpSlot, boolean reverse) {
        super(deviceNumber);
        this.pdpSlot = pdpSlot;
        this.reverse = reverse;
    }

    /**
     * Wraps talon set method and sets speed to 0 if subsystem is disabled
     *
     * @param speed Motor speed
     */
    public void set(double speed) {
        if (this.disabled) {
            this.talon.set(0);
        } else {
            this.talon.set(this.reverse ? speed * -1 : speed);
        }
    }

    public double getCurrent() {
        return this.pdp.getCurrent(pdpSlot);
    }

//    /**
//     * Check if subsystem is disabled
//     *
//     * @return Subsystem state
//     */
//    public boolean isDisabled() {
//        return this.disabled;
//    }
//
//    /**
//     * Disable the subsystem
//     */
//    public void disable() {
//        this.disabled = true;
//    }
//
//    /**
//     * Re-enable subsystem that is in a disabled state
//     */
//    public void enable() {
//        this.disabled = false;
//    }

    /**
     * Get voltage
     */
    public double getVoltage() {
        return this.talon.getOutputVoltage();
    }
}
