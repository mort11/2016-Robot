package org.mort11.subsystems.dt;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.mort11.Robot;
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
    private CANTalon motor;
    private boolean motorReverse;
    private DoubleSolenoid shifter;
    private Encoder encoder;

    public DTSide(int motorPort, boolean motorReverse, Encoder encoder) {
        this.motor = new CANTalon(motorPort);
        this.shifter = new DoubleSolenoid(DrivetrainConstants.DT_LOW_SHIFTER_PORT, DrivetrainConstants.DT_HIGH_SHIFTER_PORT);
        this.motorReverse = motorReverse;
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
        return motor.get();
    }

    /**
     * Set motor to speed
     *
     * @param speed Speed
     */
    public void set(double speed) {
        motor.set(speed * (motorReverse ? -1 : 1));
    }

    /**
     * Halt motor
     */
    public void stop() {
        motor.set(0);
    }

    /**
     * @return Current of channel in Amps
     */
    public double getCurrentLeft() {
        SmartDashboard.putNumber("Left Motor Current", Robot.adaptor.pdp.getCurrent(0));
        return Robot.adaptor.pdp.getCurrent(0);
    }

    /**
     * @return Current of channel in Amps
     */
    public double getCurrentRight() {
        SmartDashboard.putNumber("Right Motor Current", Robot.adaptor.pdp.getCurrent(1));
        return Robot.adaptor.pdp.getCurrent(1);
    }

    /**
     * @return Current of talon in Amps
     */
    public double getTalonCurrent() {
        return motor.getOutputCurrent();
    }

    /**
     * @return Voltage of talon in Volts
     */
    public double getTalonVoltage() {
        return motor.getOutputVoltage();
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

    @Override
    public abstract double getCurrent();

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

