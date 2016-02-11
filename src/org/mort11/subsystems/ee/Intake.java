package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.mort11.commands.ee.JoystickIntake;
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
// returns the distance gotten from the arm encoder
    public static double getDistance() {
        System.out.println(SensorDealer.getInstance().getArmEncoder().get());
        return SensorDealer.getInstance().getArmEncoder().get();
    }
// returns the angle of the intake
    public static double getAngle() {
        return getDistance() * EndEffectorConstants.ROLLER_DEGREE_PER_TICK;
    }

    protected void initDefaultCommand() {
    	setDefaultCommand(new JoystickIntake());
    }
    // sets the speed of the intake arm 
    public void set(double speed) {
        if (!isDisabled) {
            intakeArm.set(speed);
        }
    }

    public void disable() {
        isDisabled = true;
    }

}
