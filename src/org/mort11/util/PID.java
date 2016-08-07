package org.mort11.util;

/**
 * PID Stuff
 *
 * @author Jakob Shortell
 * @author Matt Turi
 */
public class PID {
    private double setpoint, kP, kI, kD, accumError = 0;
    private double prevTime = -1;

    /**
     * Create instance of PID controller
     *
     * @param setpoint Setpoint
     * @param kP       Proportionality multiplier
     * @param kI       Integral multiplier
     * @param kD       Derivative multiplier
     */
    public PID(double setpoint, double kP, double kI, double kD) {
        this.setpoint = setpoint;
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }

    /**
     * Calculate the output of the PID controller
     *
     * @param measuredValue Value that the PID controlled subsystem returns as the distance moved
     * @return PID output
     */
    public double getOutput(double measuredValue) {
        double error = setpoint - measuredValue;
        double currTime = System.currentTimeMillis();

        // Setup integral and derivative terms if the getOutput() method has executed more than once
        if (prevTime > 0) {
            // TODO: 8/4/16
        }

        double deltaTime = currTime - prevTime;
        prevTime = currTime;
        accumError += error;

        return kP * error;
    }
}
