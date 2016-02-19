package org.mort11.subsystems.dt;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.Robot;
import org.mort11.constants.Constants;
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
    private static Encoder encLeft = new Encoder(Constants.DT_ENCODER_LEFT_A, Constants.DT_ENCODER_LEFT_B, true, CounterBase.EncodingType.k4X);
    private static Encoder encRight = new Encoder(Constants.DT_ENCODER_RIGHT_A, Constants.DT_ENCODER_RIGHT_B, true, CounterBase.EncodingType.k4X);
    private MORTCANTalon motor1, motor2, motor3;

    public DTSide(int motor1Port, int motor2Port, int motor3Port, int pdpSlot1, int pdpSlot2, int pdpSlot3,
                  boolean motor1Reverse, boolean motor2Reverse, boolean motor3Reverse) {
        this.motor1 = new MORTCANTalon(motor1Port, pdpSlot1, motor1Reverse);
        this.motor2 = new MORTCANTalon(motor2Port, pdpSlot2, motor2Reverse);
        this.motor3 = new MORTCANTalon(motor3Port, pdpSlot3, motor3Reverse);
    }

    /**
     * Shifts the current gear
     *
     * @param gear Gear to shift to [High, Low]
     */
    public static void shift(Gear gear) {
        switch (gear) {
            case LOW:
                Robot.adaptor.shifter.set(DoubleSolenoid.Value.kReverse);
                break;
            case HIGH:
                Robot.adaptor.shifter.set(DoubleSolenoid.Value.kForward);
                break;
        }
    }

    public static Encoder getEncLeft() {
        return encLeft;
    }

    public static Encoder getEncRight() {
        return encRight;
    }

    /**
     * Reset encoder ticks
     */
    public void resetEncoder() {
        encLeft.setDistancePerPulse(Constants.INCHES_PER_PULSE_LEFT);
        encRight.setDistancePerPulse(Constants.INCHES_PER_PULSE_RIGHT);
        encLeft.reset();
        encRight.reset();
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

