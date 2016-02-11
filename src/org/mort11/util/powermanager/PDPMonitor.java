package org.mort11.util.powermanager;

import edu.wpi.first.wpilibj.Timer;
import org.mort11.constants.PDPConstants;

import java.util.ArrayList;

/**
 * PDPMonitor - Monitors PDP currents and disabled overdrawing subsystems
 *
 * @author Matt Turi <mturi@mort11.org>
 */
public class PDPMonitor {
    private Timer timer;

    public static void manage() {
        checkOverdraw(MotorHolder.motors);
        enableMotors(MotorHolder.motors);
    }

    private static void checkOverdraw(ArrayList<MORTCANTalon> motors) {
        motors.stream().filter(motor -> motor.getCurrent() > PDPConstants.MOTOR_MAX_CURRENT).forEach(motor -> {
            System.out.println(String.format("Disabled [%s]", motor.name));
            motor.disable();
        });
    }

    private static void enableMotors(ArrayList<MORTCANTalon> motors) {

    }
}
