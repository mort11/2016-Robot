package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.Robot;
import org.mort11.commands.ee.JoystickIntake;
import org.mort11.constants.EEConstants;
import org.mort11.constants.PDPConstants;
import org.mort11.sensors.SensorDealer;
import org.mort11.util.powermanager.MORTCANTalon;

/**
 * IntakeArm - Controls the intake arm
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class IntakeArm extends Subsystem {
    private MORTCANTalon intakeArm;
    private IntakeBrake intakeBrake;
    private double initPos;
    double scaling;
    public IntakeArm() {
        intakeArm = new MORTCANTalon(EEConstants.INTAKE_ARM_TALON_ID, PDPConstants.INTAKE_ARM, false);
        intakeBrake = Robot.adaptor.intakeBrake;
        intakeArm.reset();
        initPos = intakeArm.getEncPosition();
        System.out.println("init pos: "  + initPos);
        scaling = 90/1142;
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
        return ((intakeArm.getEncPosition() - initPos) * 90/1142); //:'(
    }

    /**
     * Check if arm is at limit switch
     *
     * @return True if arm at limit switch
     */
    public boolean islimSwitch() {
        return SensorDealer.getInstance().getArmLimitSwitch().get();
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
        // Engage or disengage brake depending on current requested speed
        if (speed > 0) {
            intakeBrake.disengage();
        } else {
            intakeBrake.engage();
        }

        intakeArm.set(speed);
    }
}