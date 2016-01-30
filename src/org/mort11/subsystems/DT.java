package org.mort11.subsystems;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;


public class DT extends Subsystem {
    private final static double kCircumference = 4 * Math.PI;
    Talon leftTal = new Talon(0);
    Talon rightTal = new Talon(2);
    Encoder leftEnc = new Encoder(0, 1, false, EncodingType.k4X);
    Encoder rightEnc = new Encoder(2, 3, false, EncodingType.k4X);

    public DT() {
        leftEnc.setDistancePerPulse(kCircumference / 256);
        leftEnc.reset();
        rightEnc.setDistancePerPulse(kCircumference / 256);
        rightEnc.reset();
    }

    public void initDefaultCommand() {
    }

    public void driveLeft(double speed) {
        leftTal.set(speed);
    }

    public void driveRight(double speed) {
        rightTal.set(-speed);
    }

    public void stop() {
        driveLeft(0);
        driveRight(0);
    }

    public void resetEnc() {
        leftEnc.reset();
    }

    public double getDistLeft() {
        return leftEnc.getDistance();
    }

    public double getDistRight() {
        return rightEnc.getDistance();
    }
}

