package org.mort11.subsystems.dt;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.Robot;
import org.mort11.commands.SubsystemStates;
import org.mort11.util.powermanager.MORTCANTalon;

/**
 * DTSide - Base class controlling drivetrain sides
 *
 * @author gridbug
 * @author Matt Turi
 * @author Matthew Krzyzanowski
 * @author Jeffrey Pastilha
 * @author Abi Koutha
 * @author Jakob Shortell
 */
public abstract class DTSide extends Subsystem {
    private MORTCANTalon motor1, motor2, motor3;
    private Encoder encoder;

    public DTSide(
            int motor1Port, int motor2Port, int motor3Port,
            int pdpSlot1, int pdpSlot2, int pdpSlot3,
            boolean motor1Reverse, boolean motor2Reverse, boolean motor3Reverse,
            Encoder encoder) {
        this.motor1 = new MORTCANTalon(motor1Port, pdpSlot1, motor1Reverse);
        this.motor2 = new MORTCANTalon(motor2Port, pdpSlot2, motor2Reverse);
        this.motor3 = new MORTCANTalon(motor3Port, pdpSlot3, motor3Reverse);
        this.encoder = encoder;
    }

    /**
     * Toggle current gear
     *
     * @param gear Gear to shift to
     */
    public static void shift(SubsystemStates.Gear gear) {
//    	System.out.println("shifting gears");
        switch (gear) {
            case LOW:
                Robot.adaptor.shifter.set(DoubleSolenoid.Value.kReverse);
                break;
            case HIGH:
                Robot.adaptor.shifter.set(DoubleSolenoid.Value.kForward);
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
        return motor1.get();
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
}

