package org.mort11;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.mort11.commands.SubsystemStates;
import org.mort11.commands.auton.CamAuton;
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
    public Button fullSpeed = new JoystickButton(rightJoystick, Constants.FULL_SPEED_BUTTON);

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
    public Button shooterButton = new JoystickButton(endEffector, 1);
    public Button goToGoal = new JoystickButton(endEffector, 6);

    public OI() {
        intakeRoller.whileHeld(new IntakeRollers(SubsystemStates.RollerRequest.INTAKE));
        intakeRoller.whenReleased(new IntakeRollers(SubsystemStates.RollerRequest.STOP));

        outtakeRoller.whileHeld(new IntakeRollers(SubsystemStates.RollerRequest.EXHAUST));
        outtakeRoller.whenReleased(new IntakeRollers(SubsystemStates.RollerRequest.STOP));

        shiftUp.whenPressed(new Shift(SubsystemStates.Gear.HIGH));
        shiftDown.whenPressed(new Shift(SubsystemStates.Gear.LOW));


        spinUp.toggleWhenPressed(new SpinUp(98000, true));        
        hoodToggle.whenPressed(new HoodToggle());
        armInterrupt.whenPressed(new JoystickIntake(true)); // Allows for manual movement of the intake arm when pressed
        armToNinety.whenPressed(new MotorToAngle(90)); // Moves the intake arm to 90 degrees when pressed
        armToZero.whenPressed(new MotorToAngle(0)); // Moves the intake arm to 0 degrees when pressed
        shooterButton.whenPressed(new Shoot());
        goToGoal.whenPressed(new CamAuton());

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
}

