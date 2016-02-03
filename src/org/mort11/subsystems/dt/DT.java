package org.mort11.subsystems.dt;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.sensors.SensorDealer;

/**
 * DT - Controls both sides of the drivetrain at once. [PROBABLY NOT FOR PRODUCTION USE]
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 * @author Matt Turi <mturi@mort11.org>
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 */
public class DT extends Subsystem {
    private final static double kCircumference = 4 * Math.PI;
    Talon leftTal = new Talon(0);
    Talon rightTal = new Talon(2);
    Encoder leftEnc = SensorDealer.getInstance().getLeftDriveTrain();
    Encoder rightEnc = SensorDealer.getInstance().getRightDriveTrain();

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

