package org.mort11;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.mort11.commands.SubsystemStates;
import org.mort11.commands.dt.shifting.Shift;
import org.mort11.commands.ee.*;
import org.mort11.constants.Constants;
import org.mort11.util.SpeedController;

/**
 * OI - Joystick mapping to buttons and other math stuff
 *
 * @author Sahit Chintalapudi
 * @author Matthew Krzyzanowski
 * @author Matt Turi
 * @author Ryan Thant
 * @author Seven Kurt
 * @author Michael Kozak
 * @author Ryan O'Toole
 */
public class OI {
    // Joysticks
    public Joystick leftJoystick = new Joystick(Constants.LEFT_JOYSTICK);
    public Joystick rightJoystick = new Joystick(Constants.RIGHT_JOYSTICK);
    public Joystick endEffector = new Joystick(Constants.EE_JOYSTICK);

    // Right drive joystick
    public Button shiftDown = new JoystickButton(leftJoystick, Constants.SHIFT_DOWN_BUTTON);
    public Button shiftUp = new JoystickButton(rightJoystick, Constants.SHIFT_UP_BUTTON);

    // EE Joystick
    public Button spinUp = new JoystickButton(endEffector, Constants.SPIN_UP_BUTTON);
    public Button intakeRoller = new JoystickButton(endEffector, Constants.INTAKE_BUTTON);
    public Button outtakeRoller = new JoystickButton(endEffector, Constants.OUTTAKE_BUTTON);
    public Button hoodToggle = new JoystickButton(endEffector, Constants.TOGGLE_HOOD);
    public Button armInterrupt = new JoystickButton(endEffector, Constants.ARM_INTERRUPT);
    public Button armToNinety = new JoystickButton(endEffector, Constants.ARM_TO_90);
    public Button armToZero = new JoystickButton(endEffector, Constants.ARM_TO_0);
    public Button shooterButton = new JoystickButton(endEffector, Constants.AUTO_SHOOT);

    public OI() {
        // Intake arm rollers
        intakeRoller.whileHeld(new IntakeRollers(SubsystemStates.RollerRequest.INTAKE));
        intakeRoller.whenReleased(new IntakeRollers(SubsystemStates.RollerRequest.STOP));
        outtakeRoller.whileHeld(new IntakeRollers(SubsystemStates.RollerRequest.EXHAUST));
        outtakeRoller.whenReleased(new IntakeRollers(SubsystemStates.RollerRequest.STOP));

        // Transmission
        shiftUp.whenPressed(new Shift(SubsystemStates.Gear.HIGH));
        shiftDown.whenPressed(new Shift(SubsystemStates.Gear.LOW));

        // Flywheel
        spinUp.toggleWhenPressed(new SpinUp(Constants.FLYWHEEL_RPM, true));
        shooterButton.whenPressed(new Shoot());

        // Hood
        hoodToggle.whenPressed(new HoodToggle());

        // Intake arm presets
        armInterrupt.whenPressed(new JoystickIntake(true)); // Operator switches to positional manual intake arm mode
        armToNinety.whenPressed(new MotorToAngle(90)); // Intake arm to 90 degrees
        armToZero.whenPressed(new MotorToAngle(0)); // Intake arm to 0 degrees
    }

    /**
     * Get left drive stick Y axis
     *
     * @return Deadbanded left drive stick Y axis
     */
    public double getLeftJoy() {
        return SpeedController.threshold(-leftJoystick.getY());
    }

    /**
     * Get right driver stick Y axis
     *
     * @return Deadbanded right driver stick Y axis
     */
    public double getRightJoy() {
        return SpeedController.threshold(-rightJoystick.getY());
    }

    /**
     * Get EE Y axis
     *
     * @return Deadbanded EE Y axis
     */
    public double getEEJoy() {
        return SpeedController.threshold(endEffector.getY());
    }

    /**
     * Get EE throttle on 0-1 scale
     *
     * @return Throttle from 0-1, instead of -1-1
     */
    public double getEE_Z() {
        return (endEffector.getThrottle() + 1) / 2;
    }
}

