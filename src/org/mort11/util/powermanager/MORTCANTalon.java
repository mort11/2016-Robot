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
    public String name;
    private PowerDistributionPanel pdp = new PowerDistributionPanel();
    private int pdpSlot;
    private boolean disabled = false;

    /**
     * Creates a CANTalon that controls a single motor
     *
     * @param deviceNumber CAN ID for talon
     * @param pdpSlot      int of PDP slot motor is connected to
     */
    public MORTCANTalon(int deviceNumber, int pdpSlot, String subsystemName) {
        super(deviceNumber);
        this.pdpSlot = pdpSlot;
        this.name = subsystemName;
        System.out.println(String.format("Created new MORTCANTalon [%s] [ID-%s]", name, String.valueOf(super.getDeviceID())));
    }

    /**
     * Wraps talon set method and sets speed to 0 if subsystem is disabled
     *
     * @param speed Motor speed
     */
    @Override
    public void set(double speed) {
        super.set(speed);
    }

    /**
     * @return Current of given PDP channel
     */
    public double getCurrent() {
        return pdp.getCurrent(pdpSlot);
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
