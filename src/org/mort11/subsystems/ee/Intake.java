package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.commands.ee.JoystickIntake;
import org.mort11.constants.EEConstants;
import org.mort11.constants.PDPConstants;
import org.mort11.util.powermanager.MORTCANTalon;

/**
 * Intake - Intake
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class Intake extends Subsystem {
    private MORTCANTalon intakeArm;

    public Intake() {
        intakeArm = new MORTCANTalon(EEConstants.INTAKE_ARM_TALON_ID, PDPConstants.INTAKE_ARM, false);
        intakeArm.reset();
    }

    /**
     * Returns the distance gotten from the arm encoder
     * @return Distance arm has traveled
     */
    public double getDistance() {
        System.out.println(intakeArm.get());
        return intakeArm.getEncPosition();
    }

    /**
     * Returns the angle of the intake
     * @return Angle of intake
     */
    public double getAngle() {
        return getDistance() * EEConstants.INTAKE_DEGREE_PER_TICK;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new JoystickIntake());
    }

    /**
     * Set the speed of the intake arm
     *
     * @param speed Speed
     */
    public void set(double speed) {
        intakeArm.set(speed);
    }
}
