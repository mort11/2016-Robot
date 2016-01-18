package org.mort11.subsystems.dt;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import util.DTConstants;

public abstract class DTSide extends Subsystem {

    private static CANTalon motors;
    private static Encoder enc;
    private static boolean motorReverse;
    private AHRS navx = new AHRS(SerialPort.Port.kMXP);

    public DTSide(int motorPort, int encAPort, int encBPort, boolean motorReverse, boolean encReverse) {
        motors = new CANTalon(motorPort);
        enc = new Encoder(encAPort, encBPort, encReverse, EncodingType.k4X);
        enc.setDistancePerPulse(DTConstants.INCHES_PER_PULSE); // Placeholder value
        DTSide.motorReverse = motorReverse;
    }

    public static double getSpeed() {
        return motors.get();
    }

    public static void setSpeed(double speed) {
        motors.set(speed * (motorReverse ? -1 : 1));
    }

    public static double getCurrent() { //returns raw value, unsure of what it means
        return motors.getOutputCurrent();
    }

    public static double getDist() {
        return enc.getDistance();
    }

    public static double getEncRate() {
        return enc.getRate();
    }

    public static void resetEnc() {
        enc.reset();
    }

    public void initDefaultCommand() {

    }

    public double getAngle() {
        return navx.getAngle();
    }

}

