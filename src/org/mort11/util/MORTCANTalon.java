package org.mort11.util;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import org.mort11.Robot;

/**
 * MORTCANTalon - Implementation of CANTalon with ability to monitor current draw
 *
 * @author Matt Turi <mturi@mort11.org>
 */
public class MORTCANTalon {
    private PowerDistributionPanel pdp = Robot.adaptor.pdp;
    private int[] slots;
    private CANTalon talon;

    /**
     * Creates a CANTalon that controls multiple motors
     *
     * @param deviceNumber CAN ID for talon
     * @param slots        int[] of PDP slots each motor is connected to
     */
    public MORTCANTalon(int deviceNumber, int[] slots) {
        this.talon = new CANTalon(deviceNumber);
        this.slots = slots;
    }

    /**
     * Creates a CANTalon that controls a single motor
     *
     * @param deviceNumber CAN ID for talon
     * @param pdpSlot      int of PDP slot motor is connected to
     */
    public MORTCANTalon(int deviceNumber, int pdpSlot) {
        this.talon = new CANTalon(deviceNumber);
        this.slots = new int[]{pdpSlot};
    }

    /**
     * @return Double[] of currents. If only 1 slot found, returns array of size 1 with current. Else returns
     * array of size of slots containing currents for corresponding PDP slots
     */
    public double[] getCurrents() {
        if (slots.length == 1) {
            return new double[]{getCurrent(slots[0])};
        }
        double[] currents = new double[slots.length];
        for (int i = 0; i < currents.length; i++) {
            currents[i] = getCurrent(slots[i]);
        }
        return currents;
    }

    /**
     * @param channel PDP channel to get current for
     * @return Current of given PDP channel
     */
    public double getCurrent(int channel) {
        return pdp.getCurrent(slots[channel]);
    }
}
