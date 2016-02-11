package org.mort11.util.powermanager;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SpeedController;
import org.mort11.Robot;
import org.mort11.util.MORTSubsystem;

/**
 * MORTCANTalon - Implementation of CANTalon with ability to monitor current draw
 *
 * @author Matt Turi <mturi@mort11.org>
 */
public class MORTCANTalon extends CANTalon implements SpeedController, MORTSubsystem {
    public String name;
    private PowerDistributionPanel pdp = Robot.adaptor.pdp;
    private int pdpSlot;
    private CANTalon talon;
    private boolean disabled = false;

    /**
     * Creates a CANTalon that controls a single motor
     *
     * @param deviceNumber CAN ID for talon
     * @param pdpSlot      int of PDP slot motor is connected to
     */
    public MORTCANTalon(int deviceNumber, int pdpSlot, String subsystemName) {
        super(deviceNumber);
        this.talon = new CANTalon(deviceNumber);
        this.pdpSlot = pdpSlot;
        this.name = subsystemName;
        MotorHolder.motors.add(this);
    }

    /**
     * @return Current of given PDP channel
     */
    public double getCurrent() {
        return pdp.getCurrent(pdpSlot);
    }

    @Override
    public double get() {
        return talon.get();
    }

    @Override
    public void set(double speed, byte syncGroup) {
        talon.set(speed);
    }

    /**
     * Set wrapped CANTalon to speed, unless disabled
     *
     * @param speed Speed to set talon to
     */
    @Override
    public void set(double speed) {
        if (!disabled) {
            set(speed, (byte) 0);
        }
    }

    @Override
    public boolean getInverted() {
        return false;
    }

    @Override
    public void setInverted(boolean isInverted) {
        talon.setInverted(isInverted);
    }

    @Override
    public void pidWrite(double output) {
        talon.pidWrite(output);
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
}
