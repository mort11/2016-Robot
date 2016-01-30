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
 		Button piston = new JoystickButton(ee, TeleopConstants.PISTON_BUTTON);
 		Button intakeRoll = new JoystickButton(ee, TeleopConstants.INTAKE_BUTTON);
 		Button outtakeRoll = new JoystickButton(ee, TeleopConstants.OUTTAKE_BUTTON);
 	
 		public OI() {
 			piston.whenPressed(new PistonActuation());
 		}
 	
}

