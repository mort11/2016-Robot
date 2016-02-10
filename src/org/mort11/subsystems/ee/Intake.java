package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.commands.ee.JoystickIntake;
import org.mort11.constants.EndEffectorConstants;
import org.mort11.sensors.SensorDealer;
import org.mort11.util.MORTSubsystem;

/**
 * Intake - Controls the intake arm
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class Intake extends Subsystem implements MORTSubsystem {
    private static Encoder intakeEnc;
    private boolean disabled = false;
    private CANTalon intakeArm;

    public Intake() {
        intakeArm = new CANTalon(EndEffectorConstants.ARM_TALON_ID);
        intakeEnc = SensorDealer.getInstance().getIntakeArmEncoder();
        intakeEnc.reset();
        intakeEnc.setDistancePerPulse(EndEffectorConstants.INCHES_PER_PULSE);
    }

    public static double getDistance() {
        System.out.println(intakeEnc.get());
        return intakeEnc.get();
    }

    public static double getAngle() {
        return getDistance() * EndEffectorConstants.INTAKE_DEGREE_PER_TICK;
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new JoystickIntake());
    }

    public void set(double speed) {
        if (!disabled) {
            intakeArm.set(speed);
        }
    }

    @Override
    public void disable() {
        this.disabled = true;
    }

    @Override
    public void enable() {
        this.disabled = false;
    }
}