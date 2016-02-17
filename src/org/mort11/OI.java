package org.mort11;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 419908c65a86f490a546220fe1565cd093d66b4e
=======
import org.mort11.commands.ee.PistonActuation;
import org.mort11.commands.ee.MotorToAngle;
import org.mort11.util.TeleopConstants;
import org.mort11.subsystems.dt.DT;
 		
>>>>>>> ff2541f2f511a88973d3aad641851c8cd4226270
=======
>>>>>>> 71aec0c941fc3f0a86872790a1014caf2b6d8718
=======

import org.mort11.behavior.Commands;
import org.mort11.commands.dt.Shift;
import org.mort11.commands.ee.FullSpin;
import org.mort11.commands.ee.IntakeRollers;
import org.mort11.commands.ee.RollerUp;
import org.mort11.commands.ee.SpinUp;
>>>>>>> 60dd0b09bbbcdeb91e63404ddf2dfc76cc2a939f
import org.mort11.constants.OperatorInterfaceConstants;
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
    private static boolean enabled;
    private static int count = 0;
    public Joystick ee = new Joystick(OperatorInterfaceConstants.EE_JOYSTICK);
    public Joystick left = new Joystick(OperatorInterfaceConstants.LEFT_JOYSTICK);
    public Joystick right = new Joystick(OperatorInterfaceConstants.RIGHT_JOYSTICK);
    public Button piston = new JoystickButton(ee, OperatorInterfaceConstants.PISTON_BUTTON);

    // Joysticks
    public Joystick leftJoystick = new Joystick(OperatorInterfaceConstants.LEFT_JOYSTICK);
    public Joystick rightJoystick = new Joystick(OperatorInterfaceConstants.RIGHT_JOYSTICK);
    public Joystick endEffector = new Joystick(OperatorInterfaceConstants.EE_JOYSTICK);

    // Right drive joystick
    public Button fullSpeed = new JoystickButton(rightJoystick, OperatorInterfaceConstants.FULL_SPEED_BUTTON);
    public Button shift = new JoystickButton(rightJoystick, OperatorInterfaceConstants.SHIFT_BUTTON);

    // EE Joystick
    public Button piston = new JoystickButton(endEffector, OperatorInterfaceConstants.PISTON_BUTTON);
    public Button spinUp = new JoystickButton(endEffector, OperatorInterfaceConstants.SPIN_UP_BUTTON);
    public Button spin = new JoystickButton(endEffector, 3);
    public Button intakeRoller = new JoystickButton(endEffector, OperatorInterfaceConstants.INTAKE_BUTTON);
    public Button outtakeRoller = new JoystickButton(endEffector, OperatorInterfaceConstants.OUTTAKE_BUTTON);
    public Button rollerUp = new JoystickButton(endEffector, OperatorInterfaceConstants.ROLLER_UP_BUTTON);

    public OI() {
        intakeRoller.whileHeld(new IntakeRollers(Commands.RollerRequest.INTAKE));
        intakeRoller.whenReleased(new IntakeRollers(Commands.RollerRequest.STOP));

<<<<<<< HEAD
    public static double threshold(double input) {
<<<<<<< HEAD
=======
import org.mort11.commands.ee.PistonActuation;
import org.mort11.util.TeleopConstants;

public class OI{
	public Joystick ee = new Joystick(TeleopConstants.EE_JOYSTICK);
	Joystick left = new Joystick(TeleopConstants.LEFT_JOYSTICK);
	Joystick right = new Joystick(TeleopConstants.RIGHT_JOYSTICK);	
	public Button piston = new JoystickButton(ee, TeleopConstants.PISTON_BUTTON);
	Button intakeRoll = new JoystickButton(ee, TeleopConstants.INTAKE_BUTTON);
	Button outtakeRoll = new JoystickButton(ee, TeleopConstants.OUTTAKE_BUTTON);

	public OI() {
		
	}
 	

    public static double doThreshold(double input) {
>>>>>>> 830d2dde43e2d35cf89f028ef516f6ec618b4fa7
=======
>>>>>>> 419908c65a86f490a546220fe1565cd093d66b4e
        if (Math.abs(input) <= 0.05) {
            return 0;
        }
        return input / Math.abs(input) * (Math.abs(input) - 0.05) / (1 - 0.05);
=======
        outtakeRoller.whileHeld(new IntakeRollers(Commands.RollerRequest.EXHAUST));
        outtakeRoller.whenReleased(new IntakeRollers(Commands.RollerRequest.STOP));
        //spin.whenPressed(new FullSpin());
        //shift.whenPressed(new Shift());

        spinUp.toggleWhenPressed(new SpinUp(20, false));
        rollerUp.toggleWhenPressed(new RollerUp(182)); // Keep roller up at 182 degrees when toggled
>>>>>>> 60dd0b09bbbcdeb91e63404ddf2dfc76cc2a939f
    }

    /**
     * Limits top speed of robot to avoid brownouts
     *
     * @param speed Current speed received from Joystick or other control module
     * @return Speed limited value
     */
    public static double speedLimit(double speed) {
//        System.out.println(Robot.oi.timer.get());
//        if (Robot.oi.fullSpeed.get()) {
//            enabled = true;
//            count++;
//        }
//        if (enabled) {
//            Robot.oi.timer.start();
//            enabled = false;
//        }
//        if (Robot.oi.timer.get() < 10 && Robot.oi.timer.get() > 0 && count <= 20) {
//            return speed;
//        }
//        if (Robot.oi.timer.get() >= 10) {
//            //count = 2;
//            Robot.oi.timer.stop();
//            Robot.oi.timer.reset();
//            System.out.println("timer: " + Robot.oi.timer.get());
//        }
//        if (!enabled) {
//            if (speed >= .75) {
//                speed = .75;
//            }
//            if (speed <= -.75) {
//                speed = -.75;
//            }
//        }
//        return speed;
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

