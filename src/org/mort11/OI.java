package org.mort11;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
<<<<<<< HEAD
import org.mort11.constants.OperatorInterfaceConstants;

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

    public Button intakeRoller = new JoystickButton(ee, OperatorInterfaceConstants.INTAKE_BUTTON);
    public Button outtakeRoller = new JoystickButton(ee, OperatorInterfaceConstants.OUTTAKE_BUTTON);

    public Button fullSpeed = new JoystickButton(right, OperatorInterfaceConstants.FULL_SPEED_BUTTON);
    private Timer timer;

    public OI() {
        timer = new Timer();
    }

    public static double threshold(double input) {
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
        if (Math.abs(input) <= 0.05) {
            return 0;
        }
        return input / Math.abs(input) * (Math.abs(input) - 0.05) / (1 - 0.05);
    }

    public static double setSpeed(double speed) {
        System.out.println(Robot.oi.timer.get());
        if (Robot.oi.fullSpeed.get()) {
            enabled = true;
            count++;
        }
        if (enabled) {
            Robot.oi.timer.start();
            enabled = false;
        }
        if (Robot.oi.timer.get() < 10 && Robot.oi.timer.get() > 0 && count <= 20) {
            return speed;
        }
        if (Robot.oi.timer.get() >= 10) {
            //count = 2;
            Robot.oi.timer.stop();
            Robot.oi.timer.reset();
            System.out.println("timer: " + Robot.oi.timer.get());
        }
        if (!enabled) {
            if (speed >= .75) {
                speed = .75;
            }
            if (speed <= -.75) {
                speed = -.75;
            }
        }
        return speed;
    }

    public double getLeftJoy() {
        return setSpeed(-left.getY());
    }

    public double getRightJoy() {
        return setSpeed(right.getY());
    }
}

