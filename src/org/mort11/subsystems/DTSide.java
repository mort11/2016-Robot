package org.mort11.subsystems;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class DTSide extends Subsystem {
    private Talon motors;
    private Encoder enc;
    // private double curVal = 0;
    private boolean motorReverse;

    public DTSide(int motorPort, int encAPort, int encBPort, boolean motorReverse, boolean encReverse) {
        motors = new Talon(motorPort);
        enc = new Encoder(encAPort, encBPort, encReverse, EncodingType.k4X);
        enc.setDistancePerPulse(1); // Placeholder value
        this.motorReverse = motorReverse;
    }

    public void initDefaultCommand() {

    }

    public double getSpeed() {
        return motors.get();
    }

    public void setSpeed(double speed) {
        motors.set(speed);
    }

    public double getDist() {
        return enc.getDistance();
    }

    public double getEncRate() {
        return enc.getRate();
    }

    public void resetEnc() {
        enc.reset();
    }
}

