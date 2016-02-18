package org.mort11.subsystems.dt;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.util.powermanager.MORTCANTalon;

/**
 * DTSide - Base class controlling drivetrain sides
 *
 * @author gridbug <wmarshall@mort11.org>
 * @author Matt Turi <mturi@mort11.org>
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 * @author Jeffrey Pastilha <jpmail967@yahoo.com>
 * @author Abi Koutha <akoutha7@gmail.com>
 * @author Jakob Shortell <jshortell@mort11.org>
 */
public abstract class DTSide extends Subsystem {
    private MORTCANTalon motor1, motor2, motor3;
    private Encoder encoder;

    public DTSide(int motor1Port, int motor2Port, int motor3Port, boolean motor1Reverse, boolean motor2Reverse,
                  boolean motor3Reverse, Encoder encoder) {
        this.motor1 = new MORTCANTalon(motor1Port, motor1Reverse);
        this.motor2 = new MORTCANTalon(motor2Port, motor2Reverse);
        this.motor3 = new MORTCANTalon(motor3Port, motor3Reverse);
        this.encoder = encoder;
    }

    /**
     * Shifts the current gear
     *
     * @param gear Gear to shift to [High, Low]
     */
    public static void shift(Gear gear) {
        switch (gear) {
            case LOW:
                break;
            case HIGH:
                break;
        }
    }

    /**
     * Reset encoder ticks
     */
    public void resetEncoder() {
        this.encoder.reset();
    }

    /**
     * Get current speed of motor from TalonSRX
     *
     * @return Motor speed [Units?]
     */
    public double getSpeed() {
        return this.encoder.get();
    }

    /**
     * Set motor to speed
     *
     * @param speed Speed
     */
    public void set(double speed) {
        this.motor1.set(speed);
        this.motor2.set(speed);
        this.motor3.set(speed);
    }

    /**
     * Halt motor
     */
    public void halt() {
        this.motor1.set(0);
        this.motor2.set(0);
        this.motor3.set(0);
    }

    @Override
    public void initDefaultCommand() {
    }

    public enum Gear {
        LOW, HIGH
    }
}

