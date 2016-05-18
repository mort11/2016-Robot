package org.mort11.subsystems.dt;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.Robot;
import org.mort11.commands.SubsystemStates;

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
    private CANTalon motor1, motor2, motor3;
    private boolean motor1Reverse, motor2Reverse, motor3Reverse;
    private Encoder encoder;
    private double distToTurn = 0;

    public DTSide(int motor1Port, int motor2Port, int motor3Port, boolean motor1Reverse, boolean motor2Reverse,
                  boolean motor3Reverse, Encoder encoder) {
        this.motor1 = new CANTalon(motor1Port);
        this.motor2 = new CANTalon(motor2Port);
        this.motor3 = new CANTalon(motor3Port);
        this.motor1Reverse = motor1Reverse;
        this.motor2Reverse = motor2Reverse;
        this.motor3Reverse = motor3Reverse;
        this.encoder = encoder;
    }

    /**
     * Toggle current gear
     *
     * @param gear Gear to shift to
     */
    public static void shift(SubsystemStates.Gear gear) {
        switch (gear) {
            case LOW:
                Robot.adaptor.shifter.set(DoubleSolenoid.Value.kForward);
                break;
            case HIGH:
                Robot.adaptor.shifter.set(DoubleSolenoid.Value.kReverse);
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
     * Set motor to speed
     *
     * @param speed Speed
     */
    public void set(double speed) {
        this.motor1.set(speed * (motor1Reverse ? -1 : 1));
        this.motor2.set(speed * (motor2Reverse ? -1 : 1));
        this.motor3.set(speed * (motor3Reverse ? -1 : 1));
    }

    /**
     * Halt motor
     */
    public void halt() {
        this.motor1.set(0);
        this.motor2.set(0);
        this.motor3.set(0);
    }

    public double getDistToTurn() {
        return distToTurn;
    }

    public void setDistToTurn(double distToTurn) {
        this.distToTurn = distToTurn;
    }
}

