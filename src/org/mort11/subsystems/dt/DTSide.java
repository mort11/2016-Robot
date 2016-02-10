package org.mort11.subsystems.dt;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.constants.DrivetrainConstants;
import org.mort11.util.MORTSubsystem;

/**
 * DTSide - Base class controlling drivetrain sides
 *
 * @author gridbug <wmarshall@mort11.org>
 * @author Matt Turi <mturi@mort11.org>
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 * @author Jeffrey Pastilha <jpmail967@yahoo.com>
 * @author Abi Koutha <akoutha7@gmail.com>
 */
public abstract class DTSide extends Subsystem implements MORTSubsystem {
    private static boolean disabled = false;
    private Gear currentGear = Gear.LOW_GEAR;
    private CANTalon motor1, motor2, motor3;
    private boolean motor1Reverse, motor2Reverse, motor3Reverse;
    private DoubleSolenoid shifter;
    private Encoder encoder;

    public DTSide(int motor1Port, int motor2Port, int motor3Port, boolean motor1Reverse, boolean motor2Reverse,
                  boolean motor3Reverse, Encoder encoder) {
        this.motor1 = new CANTalon(motor1Port);
        this.motor2 = new CANTalon(motor2Port);
        this.motor3 = new CANTalon(motor3Port);
        this.shifter = new DoubleSolenoid(DrivetrainConstants.DT_LOW_SHIFTER_PORT, DrivetrainConstants.DT_HIGH_SHIFTER_PORT);
        this.motor1Reverse = motor1Reverse;
        this.motor2Reverse = motor2Reverse;
        this.motor3Reverse = motor3Reverse;
        this.encoder = encoder;
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

    /**
     * Disable the subsystem
     */
    @Override
    public void disable() {
        disabled = true;
    }

    /**
     * Check if subsystem is disabled
     *
     * @return Subsystem state
     */
    @Override
    public boolean isDisabled() {
        return disabled;
    }

    /**
     * Re-enable subsystem that is in a disabled state
     */
    @Override
    public void enable() {
        disabled = false;
    }

    /**
     * Get current from PDP for DT side. Implemented in child classes
     *
     * @return Current being used by side [Cumulative total]
     */
    public abstract double getCurrent();

    /**
     * Get current being output by talon
     *
     * @return Output current [Not avergaged]
     */
    public double getTalonCurrent() {
        double avgCurrent = (motor1.getOutputCurrent() + motor2.getOutputCurrent() + motor3.getOutputCurrent()) / 3; // TODO: 2/10/16 Check if we want to use just 1 motor or average of all three
        return motor1.getOutputCurrent();
    }

    /**
     * Get voltage being output by talon
     *
     * @return Output voltage [Not averaged]
     */
    public double getTalonVoltage() {
        double avgVoltage = (motor1.getOutputVoltage() + motor2.getOutputVoltage() + motor3.getOutputVoltage()) / 3; // TODO: 2/10/16 Check if we want to use just 1 motor or average of all three
        return motor1.getOutputVoltage();
    }

    @Override
    public void initDefaultCommand() {
    }

    /**
     * Toggle between high and low gear
     */
    public void shift() {
        if (this.currentGear == Gear.LOW_GEAR) {
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
    public void shift(Gear gear) {
        this.currentGear = gear;
        // Low gear
        if (gear == Gear.LOW_GEAR) {
            shifter.set(DoubleSolenoid.Value.kForward); // TODO: 2/10/16 Check that low gear is solenoid kForward
        }
        // High gear
        else {
            shifter.set(DoubleSolenoid.Value.kReverse); // TODO: 2/10/16 Check that high gear is solenoid kReverse
        }
    }

    public static final class Gear {
        private static final Gear HIGH_GEAR = new Gear();
        private static final Gear LOW_GEAR = new Gear();
    }
}

