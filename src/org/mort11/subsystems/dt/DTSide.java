package org.mort11.subsystems.dt;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
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
 * @author Jakob Shortell <jshortell@mort11.org>
 */
public abstract class DTSide extends Subsystem implements MORTSubsystem {
    private static boolean disabled = false;
    private static Gear currentGear = Gear.LOW_GEAR;
    private static Solenoid lowShifter, highShifter;
    private CANTalon motor1, motor2, motor3;
    private boolean motor1Reverse, motor2Reverse, motor3Reverse;
    private Encoder encoder;

    public DTSide(int motorPort1, int motorPort2, int motorPort3, boolean motor1Reverse,
                  boolean motor2Reverse, boolean motor3Reverse, Encoder encoder) {
        motor1 = new CANTalon(motorPort1);
        motor2 = new CANTalon(motorPort2);
        motor3 = new CANTalon(motorPort3);
        lowShifter = new Solenoid(DrivetrainConstants.DT_LOW_SHIFTER_PORT);
        highShifter = new Solenoid(DrivetrainConstants.DT_HIGH_SHIFTER_PORT);
        this.motor1Reverse = motor1Reverse;
        this.motor2Reverse = motor2Reverse;
        this.motor3Reverse = motor3Reverse;
        this.encoder = encoder;
    }

    public static boolean getDisabled() {
        return disabled;
    }

    public static void setDisabledState(boolean isDisabled) {
        DTSide.disabled = isDisabled;
    }

    public static void shift() {
        if (currentGear == Gear.LOW_GEAR) {
            shift(Gear.HIGH_GEAR);
        } else {
            shift(Gear.LOW_GEAR);
        }
    }

    public static void shift(Gear gear) {
        currentGear = gear;
        if (gear == Gear.LOW_GEAR) {
            lowShifter.set(true);
            highShifter.set(false);
        } else {
            lowShifter.set(false);
            highShifter.set(true);
        }
    }

    public void resetEncoder() {
        this.encoder.reset();
    }

    public double getSpeed() {
        return motor1.get();
    }

    public void set(double speed) {
        motor1.set(speed * (motor1Reverse ? -1 : 1)); 
        motor2.set(speed * (motor2Reverse ? -1 : 1)); 
        motor3.set(speed * (motor3Reverse ? -1 : 1)); 
    }

    public void stop() {
        motor1.set(0);
        motor2.set(0);
        motor3.set(0);
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
        return motor1.getOutputCurrent();
    }

    /**
     * @return Voltage of talon in Volts
     */
    public double getTalonVoltage() {
        return motor1.getOutputVoltage();
    }

    @Override
    public void disable() {
        DTSide.disabled = true;
    }

    public void initDefaultCommand() {
    }

    public static final class Gear {
        public static final Gear HIGH_GEAR = new Gear();
        public static final Gear LOW_GEAR = new Gear();
    }
}

