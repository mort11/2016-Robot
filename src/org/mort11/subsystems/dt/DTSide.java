package org.mort11.subsystems.dt;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.mort11.Robot;
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
    private Solenoid lowShifter;
    private Solenoid highShifter;
    private Encoder encoder;

    public DTSide(int motorPort, int lowShifterPort, int highSifterPort, boolean motorReverse, Encoder encoder) {
        motor = new CANTalon(motorPort);
        lowShifter = new Solenoid(lowShifterPort);
        highShifter = new Solenoid(highSifterPort);
        this.motorReverse = motorReverse;
        this.encoder = encoder;
    }

    public static boolean getDisabled() {
        return disabled;
    }

    public static void setDisabledState(boolean isDisabled) {
        DTSide.disabled = isDisabled;
    }

    public void resetEncoder() {
        this.encoder.reset();
    }

    public double getSpeed() {
        return motor.get();
    }

    public void set(double speed) {
        motor.set(speed * (motorReverse ? -1 : 1));
    }

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

    @Override
    public void disable() {
        DTSide.disabled = true;
    }

    public void initDefaultCommand() {
    }

    public void shift() {
        if (this.currentGear == Gear.LOW_GEAR) {
            shift(Gear.HIGH_GEAR);
        } else {
            shift(Gear.LOW_GEAR);
        }
    }

    public void shift(Gear gear) {
        this.currentGear = gear;
        if (gear == Gear.LOW_GEAR) {
            lowShifter.set(true);
            highShifter.set(false);
        } else {
            lowShifter.set(false);
            highShifter.set(true);
        }
    }

    public static final class Gear {
        public static final Gear HIGH_GEAR = new Gear();
        public static final Gear LOW_GEAR = new Gear();
    }
}

