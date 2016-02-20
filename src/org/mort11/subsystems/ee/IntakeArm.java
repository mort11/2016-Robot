package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.commands.ee.JoystickIntake;
import org.mort11.constants.Constants;
import org.mort11.util.powermanager.MORTCANTalon;

/**
 * IntakeArm - Controls the intake arm
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class IntakeArm extends Subsystem {
    private MORTCANTalon intakeArm;
    private double initPos;

    public IntakeArm() {
        this.intakeArm = new MORTCANTalon(Constants.INTAKE_ARM_TALON_ID, Constants.PDP_INTAKE_ARM, false);
        this.initPos = intakeArm.getEncPosition();
        System.out.println("init pos: " + initPos);
    }

    /**
     * Get position of intakeArm arm from encoder reading
     *
     * @return Encoder distance/ticks
     */
    public double getDistance() {
        return intakeArm.getEncPosition();
    }

    /**
     * Get position of intakeArm arm from encoder reading as angle
     *
     * @return Encoder angle
     */
    public double getAngle() {
        return ((intakeArm.getEncPosition() - initPos) * 90 / 1142); //:'(
    }

    // TODO: 2/19/2016 Rewrite limit switch code here when Mr. Thant finishes mounting lim switch

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
}