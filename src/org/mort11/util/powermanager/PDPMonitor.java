package org.mort11.util.powermanager;

import org.mort11.constants.PDPConstants;

import java.util.ArrayList;

/**
 * PDPMonitor - Monitors PDP currents and disabled overdrawing subsystems
 *
 * @author Matt Turi <mturi@mort11.org>
 */
public class PDPMonitor {
    public static void manage() {
        checkOverdraw(MotorHolder.motors);
        enableMotors(MotorHolder.motors);
    }

    private static void checkOverdraw(ArrayList<MORTCANTalon> motors) {
        motors.stream().filter(motor -> motor.getVoltage() > PDPConstants.MOTOR_MAX_VOLTAGE).forEach(motor -> {
            System.out.println(String.format("Voltage at [%s]", Double.toString(motor.getVoltage())));
            //motor.disable();
        });
    }

    private static void enableMotors(ArrayList<MORTCANTalon> motors) {
        motors.stream().filter(motor -> motor.getVoltage() < PDPConstants.MOTOR_MIN_REENABLE_VOLTAGE).forEach(MORTCANTalon::enable);
    }
}
