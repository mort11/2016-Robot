package org.mort11;


import edu.wpi.first.wpilibj.Joystick;

public class OI {
    Joystick left = new Joystick(0);
    Joystick right = new Joystick(1);

    public static double doThreshold(double input) {
        if (Math.abs(input) <= 0.05) {
            return 0;
        }
        return input / Math.abs(input) * (Math.abs(input) - 0.05) / (1 - 0.05);
    }

    public double getLeftJoy() {
        return doThreshold(-left.getY());
    }

    public double getRightJoy() {
        return doThreshold(-right.getY());
    }
}

