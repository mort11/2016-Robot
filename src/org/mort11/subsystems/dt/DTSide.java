package org.mort11.subsystems.dt;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.Robot;
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
    public static Gear currentGear = Gear.LOW_GEAR;
    private MORTCANTalon motor1, motor2, motor3;
    private boolean motor1Reverse, motor2Reverse, motor3Reverse;
    private Encoder encoder;

    public DTSide(int motor1Port, int motor2Port, int motor3Port, int motor1PDPSlot, int motor2PDPSlot, int motor3PDPSlot,
                  String motor1Name, String motor2Name, String motor3Name, boolean motor1Reverse, boolean motor2Reverse,
                  boolean motor3Reverse, Encoder encoder) {
        this.motor1 = new MORTCANTalon(motor1Port, motor1PDPSlot, motor1Name);
        this.motor2 = new MORTCANTalon(motor2Port, motor2PDPSlot, motor2Name);
        this.motor3 = new MORTCANTalon(motor3Port, motor3PDPSlot, motor3Name);
        this.motor1Reverse = motor1Reverse;
        this.motor2Reverse = motor2Reverse;
        this.motor3Reverse = motor3Reverse;
        this.encoder = encoder;
    }

    /**
     * Toggle between high and low gear
     */
    public static void shift() {
        if (currentGear == Gear.LOW_GEAR) {
            shift(Gear.HIGH_GEAR);
        } else {
            shift(Gear.LOW_GEAR);
        }
    }

    /**
     * Toggle current gear
     *
     * @param gear Gear to shift to
     */
    public static void shift(Gear gear) {
        currentGear = gear;
        // Low gear
        if (gear == Gear.LOW_GEAR) {
            Robot.adaptor.shifter.set(DoubleSolenoid.Value.kReverse); // TODO: 2/10/16 Check that low gear is solenoid kForward
        }

        // High gear
        else {
            Robot.adaptor.shifter.set(DoubleSolenoid.Value.kForward); // TODO: 2/10/16 Check that high gear is solenoid kReverse
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
        double avgSpeed = (motor1.get() + motor2.get() + motor3.get()) / 3; // TODO: 2/10/16 Check if we want to use just 1 motor or average of all three
        return motor1.get();
    }

    /**
     * Set motor to speed
     *
     * @param speed Speed
     */
    public void set(double speed) {
        this.motor1.set(speed * (this.motor1Reverse ? -1 : 1));
        this.motor2.set(speed * (this.motor2Reverse ? -1 : 1));
        this.motor3.set(speed * (this.motor3Reverse ? -1 : 1));
    }

    /**
     * Halt motor
     */
    public void stop() {
        this.motor1.set(0);
        this.motor2.set(0);
        this.motor3.set(0);
    }

    @Override
    public void initDefaultCommand() {
    }

    public static final class Gear {
        private static final Gear HIGH_GEAR = new Gear();
        private static final Gear LOW_GEAR = new Gear();
    }
}

