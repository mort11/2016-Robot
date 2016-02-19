package org.mort11.subsystems.ee;

import org.mort11.commands.ee.JoystickIntake;
import org.mort11.constants.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * IntakeArm - Controls the intake arm
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class IntakeArm extends Subsystem {
    private CANTalon intakeArm;
    private double initPos;
    double scaling;
    public IntakeArm() {
        intakeArm = new CANTalon(Constants.INTAKE_ARM_TALON_ID);
        initPos = intakeArm.getEncPosition();
        System.out.println("init pos: "  + initPos);
        scaling = 90/1142;
    }

    /**
     * Get position of intakeArm arm from encoder reading as angle
     *
     * @return Encoder angle
     */
    public double getAngle() {
    	//System.out.println("ticks: "  + intakeArm.getEncPosition());
        return ((intakeArm.getEncPosition() - initPos) * 0.09); //:'(
    }

    /**
     * Returns the distance gotten from the arm encoder
     *
     * @return Distance arm has traveled
     */
    public double getDistance() {
        System.out.println(intakeArm.get());

        return intakeArm.getEncPosition();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new JoystickIntake());
    }

    /**
     * Adjust position of intakeArm arm
     *
     * @param speed Amount to move arm by
     */
    public void set(double speed) {
        intakeArm.set(speed);
    }

    public void reset() {
        intakeArm.reset();
    }
}