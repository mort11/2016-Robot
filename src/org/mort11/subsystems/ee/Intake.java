package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.constants.EndEffectorConstants;
import org.mort11.sensors.SensorDealer;
import org.mort11.util.MORTSubsystem;

/**
 * Intake - Intake
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class Intake extends Subsystem implements MORTSubsystem {
    boolean isDisabled = false;
    private CANTalon intakeArm;

    public Intake() {
        intakeArm = new CANTalon(EndEffectorConstants.ARM_TALON_PORT);
        SensorDealer.getInstance().getArmEncoder().reset();
        SensorDealer.getInstance().getArmEncoder().setDistancePerPulse(EndEffectorConstants.INCHES_PER_PULSE);
    }

    public static double getDistance() {
        System.out.println(SensorDealer.getInstance().getArmEncoder().get());
        return SensorDealer.getInstance().getArmEncoder().get();
    }

    public static double getAngle() {
        return getDistance() * EndEffectorConstants.ROLLER_DEGREE_PER_TICK;
    }

    protected void initDefaultCommand() {
    	
    }
    
    public void set(double speed) {
        if (!isDisabled) {
            intakeArm.set(speed);
        }
    }

    public void disable() {
        isDisabled = true;
    }

}
