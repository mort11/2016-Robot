package org.mort11;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.mort11.behavior.Commands;
import org.mort11.commands.ee.IntakeRollers;
import org.mort11.commands.ee.RollerUp;
import org.mort11.commands.ee.SpinUp;
import org.mort11.constants.OIConstants;
import org.mort11.util.SpeedController;

/**
 * OI - Joystick mapping to buttons and other math stuff
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
    public Joystick leftJoystick = new Joystick(OIConstants.LEFT_JOYSTICK);
    public Joystick rightJoystick = new Joystick(OIConstants.RIGHT_JOYSTICK);
    public Joystick endEffector = new Joystick(OIConstants.EE_JOYSTICK);

    // Right drive joystick
    public Button fullSpeed = new JoystickButton(rightJoystick, OIConstants.FULL_SPEED_BUTTON);
    public Button shift = new JoystickButton(rightJoystick, OIConstants.SHIFT_BUTTON);

    // EE Joystick
    public Button spinUp = new JoystickButton(endEffector, OIConstants.SPIN_UP_BUTTON);
    public Button spin = new JoystickButton(endEffector, 3);
    public Button intakeRoller = new JoystickButton(endEffector, OIConstants.INTAKE_BUTTON);
    public Button outtakeRoller = new JoystickButton(endEffector, OIConstants.OUTTAKE_BUTTON);
    public Button rollerUp = new JoystickButton(endEffector, OIConstants.ROLLER_UP_BUTTON);

    public OI() {
        intakeRoller.whileHeld(new IntakeRollers(Commands.RollerRequest.INTAKE));
        intakeRoller.whenReleased(new IntakeRollers(Commands.RollerRequest.STOP));

        outtakeRoller.whileHeld(new IntakeRollers(Commands.RollerRequest.EXHAUST));
        outtakeRoller.whenReleased(new IntakeRollers(Commands.RollerRequest.STOP));
        //spin.whenPressed(new FullSpin());
        //shift.whenPressed(new Shift());

        spinUp.toggleWhenPressed(new SpinUp(20, false));
        rollerUp.toggleWhenPressed(new RollerUp(182)); // Keep roller up at 182 degrees when toggled
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
    	return endEffector.getZ();
    }
}

