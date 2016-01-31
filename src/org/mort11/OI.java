package org.mort11;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
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
		System.out.println("OI print ln fsklafhajdskjfehakjfhkj ewahfjweahkjefhewaihfkjawhke");
		System.out.println(piston);
	}
 	

    public static double doThreshold(double input) {
        if (Math.abs(input) <= 0.05) {
            return 0;
        }
        return input / Math.abs(input) * (Math.abs(input) - 0.05) / (1 - 0.05);
    }

    public double getLeftJoy() {
        return doThreshold(-left.getY());
    }

    public double getRightJoy() {
        return doThreshold(-right.getY());
    }
}

