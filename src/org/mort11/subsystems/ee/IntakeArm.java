package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.commands.ee.JoystickIntake;
import org.mort11.constants.Constants;

/**
 * Controls the intake arm
 *
 * @author Sahit Chintalapudi
 */
@Deprecated
public class IntakeArm extends Subsystem {
    private CANTalon intakeArm;
    private double initPos;

    public IntakeArm() {
        intakeArm = new CANTalon(Constants.INTAKE_ARM_TALON_ID);
        initPos = intakeArm.getEncPosition();
    }

    /**
     * Get position of intakeArm arm from encoder reading as angle
     *
     * @return Encoder angle
     */
    public double getAngle() {
        return ((intakeArm.getEncPosition() - initPos) * 0.093); //:'(
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
}