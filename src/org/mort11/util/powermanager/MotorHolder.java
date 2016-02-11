package org.mort11.util.powermanager;

import org.mort11.util.Loopable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * MotorHolder - Holds the MORTCANTalon objects
 *
 * @author Matt Turi <mturi@mort11.org>
 */
public class MotorHolder implements Loopable {
    public static ArrayList<MORTCANTalon> motors = new ArrayList<>();

    @Override
    public void update() {
        Collections.sort(motors, new Comparator<MORTCANTalon>() {
            @Override
            public int compare(MORTCANTalon talon1, MORTCANTalon talon2) {
                return (int) (talon1.getVoltage() - talon2.getVoltage());
            }
        });
    }
}
