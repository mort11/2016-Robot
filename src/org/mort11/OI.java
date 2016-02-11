package org.mort11;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.mort11.commands.Shift;
import org.mort11.commands.ee.IntakeRollers;
import org.mort11.commands.ee.RollerUp;
import org.mort11.commands.ee.SpinUp;
import org.mort11.constants.OperatorInterfaceConstants;
import org.mort11.util.SpeedController;

/**
 * OI - Joystick mapping to buttons and other control systems
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 * @author Matt Turi <mturi@mort11.org>
 * @author Ryan Thant <ryanthant1@gmail.com>
 * @author Seven Kurt <seven.kurt@motsd.org>
 * @author Michael Kozak <michael.kozak@motsd.org>
 * @author Ryan O'Toole <ryan.otoole@motsd.org>
 */
public class OI {
    // Joysticks
    public Joystick leftJoystick = new Joystick(OperatorInterfaceConstants.LEFT_JOYSTICK);
    public Joystick rightJoystick = new Joystick(OperatorInterfaceConstants.RIGHT_JOYSTICK);
    public Joystick endEffector = new Joystick(OperatorInterfaceConstants.EE_JOYSTICK);

    // Right drive joystick
    public Button fullSpeed = new JoystickButton(rightJoystick, OperatorInterfaceConstants.FULL_SPEED_BUTTON);
    public Button shift = new JoystickButton(rightJoystick, 4);

    // EE Joystick
    public Button piston = new JoystickButton(endEffector, OperatorInterfaceConstants.PISTON_BUTTON);
    public Button spinUp = new JoystickButton(endEffector, OperatorInterfaceConstants.SPIN_UP_BUTTON);
    public Button intakeRoller = new JoystickButton(endEffector, OperatorInterfaceConstants.INTAKE_BUTTON);
    public Button outtakeRoller = new JoystickButton(endEffector, OperatorInterfaceConstants.OUTTAKE_BUTTON);
    public Button rollerUp = new JoystickButton(endEffector, OperatorInterfaceConstants.ROLLER_UP_BUTTON);

    public OI() {
        shift.whenPressed(new Shift());
        spinUp.toggleWhenPressed(new SpinUp(20, false));
        intakeRoller.toggleWhenPressed(new IntakeRollers(true, false));
        outtakeRoller.toggleWhenPressed(new IntakeRollers(false, true));
        rollerUp.toggleWhenPressed(new RollerUp(182)); // Keep roller up at 182 degrees when toggled
    }


    public double getLeftJoy() {
        return SpeedController.threshold(-leftJoystick.getY());
    }

    public double getRightJoy() {
        return SpeedController.threshold(rightJoystick.getY());
    }

    public double getEEJoy() {
        return SpeedController.threshold(endEffector.getY());
    }
}

