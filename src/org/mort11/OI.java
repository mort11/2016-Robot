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
 * Joystick mapping to buttons and other math stuff
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
    private Joystick leftJoystick = new Joystick(Constants.LEFT_JOYSTICK);
    private Joystick rightJoystick = new Joystick(Constants.RIGHT_JOYSTICK);
    private Joystick endEffector = new Joystick(Constants.EE_JOYSTICK);

    OI() {
        Button intakeRoller = new JoystickButton(endEffector, Constants.INTAKE_BUTTON);
        intakeRoller.whileHeld(new IntakeRollers(SubsystemStates.RollerState.INTAKE));
        intakeRoller.whenReleased(new IntakeRollers(SubsystemStates.RollerState.STOP));

        Button outtakeRoller = new JoystickButton(endEffector, Constants.OUTTAKE_BUTTON);
        outtakeRoller.whileHeld(new IntakeRollers(SubsystemStates.RollerState.EXHAUST));
        outtakeRoller.whenReleased(new IntakeRollers(SubsystemStates.RollerState.STOP));

        Button shiftUp = new JoystickButton(rightJoystick, Constants.SHIFT_UP_BUTTON);
        shiftUp.whenPressed(new Shift(SubsystemStates.Gear.HIGH));
        Button shiftDown = new JoystickButton(leftJoystick, Constants.SHIFT_DOWN_BUTTON);
        shiftDown.whenPressed(new Shift(SubsystemStates.Gear.LOW));

        Button spinUp = new JoystickButton(endEffector, Constants.SPIN_UP_BUTTON);
        spinUp.whenPressed(new SpoolFlywheel(60000, true));
        Button spinDown = new JoystickButton(endEffector, Constants.STOP_FLYWHEEL);
        spinDown.whenPressed(new SpoolFlywheel(0, true));
        Button hoodToggle = new JoystickButton(endEffector, Constants.TOGGLE_HOOD);
        hoodToggle.whenPressed(new HoodToggle());
        Button armToNinety = new JoystickButton(endEffector, Constants.ARM_TO_90);
        armToNinety.whenPressed(new ToggleIntake(SubsystemStates.Intake.UP));
        Button armToZero = new JoystickButton(endEffector, Constants.ARM_TO_0);
        armToZero.whenPressed(new ToggleIntake(SubsystemStates.Intake.DOWN));
        Button shooterButton = new JoystickButton(endEffector, 1);
        shooterButton.whenPressed(new AutoShoot());
    }

    public double getLeftJoy() {
        return SpeedController.threshold(-leftJoystick.getY());
    }

    public double getRightJoy() {
        return SpeedController.threshold(-rightJoystick.getY());
    }

    public double getEEJoy() {
        return SpeedController.threshold(endEffector.getY());
    }

    public double getEE_Z() {
        return (endEffector.getThrottle() + 1) / 2;
    }

    public boolean getUpPOV() {
        return endEffector.getPOV() == 0;
    }

    public boolean getDownPOV() {
        return endEffector.getPOV() == 180;
    }
}