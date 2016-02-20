package org.mort11;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.mort11.commands.SubsystemStates;
import org.mort11.commands.dt.shifting.Shift;
import org.mort11.commands.ee.IntakeRollers;
import org.mort11.commands.ee.SpinUp;
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

    public Button shiftUp = new JoystickButton(rightJoystick, Constants.SHIFT_UP_BUTTON);
    public Button shiftDown = new JoystickButton(rightJoystick, Constants.SHIFT_DOWN_BUTTON);

    // EE Joystick
    public Button spinUp = new JoystickButton(endEffector, Constants.SPIN_UP_BUTTON);
    public Button intakeRoller = new JoystickButton(endEffector, Constants.INTAKE_BUTTON);
    public Button outtakeRoller = new JoystickButton(endEffector, Constants.OUTTAKE_BUTTON);
//    public Button rollerUp = new JoystickButton(endEffector, Constants.ROLLER_UP_BUTTON);

    public OI() {
        intakeRoller.whileHeld(new IntakeRollers(SubsystemStates.RollerRequest.INTAKE));
        intakeRoller.whenReleased(new IntakeRollers(SubsystemStates.RollerRequest.STOP));

        outtakeRoller.whileHeld(new IntakeRollers(SubsystemStates.RollerRequest.EXHAUST));
        outtakeRoller.whenReleased(new IntakeRollers(SubsystemStates.RollerRequest.STOP));

        shiftUp.whenPressed(new Shift(SubsystemStates.Gear.HIGH));
        shiftDown.whenPressed(new Shift(SubsystemStates.Gear.LOW));

        spinUp.toggleWhenPressed(new SpinUp(20, false));
//        rollerUp.toggleWhenPressed(new RollerUp(182)); // Keep roller up at 182 degrees when toggled
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
        return (endEffector.getZ() + 1) / 2;
    }
}

