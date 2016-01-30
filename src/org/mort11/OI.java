package org.mort11;

import org.mort11.commands.ee.PistonActuation;
import org.mort11.util.TeleopConstants;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

 	public class OI{
 		
 		Joystick left = new Joystick(TeleopConstants.LEFT_JOYSTICK);
 		Joystick right = new Joystick(TeleopConstants.RIGHT_JOYSTICK);
 		Joystick ee = new Joystick(TeleopConstants.EE_JOYSTICK);
 		
 		public Button piston = new JoystickButton(ee, TeleopConstants.PISTON_BUTTON);
 		Button intakeRoll = new JoystickButton(ee, TeleopConstants.INTAKE_BUTTON);
 		Button outtakeRoll = new JoystickButton(ee, TeleopConstants.OUTTAKE_BUTTON);
 	
 		public OI() {
 			System.out.println("OI print ln fsklafhajdskjfehakjfhkj ewahfjweahkjefhewaihfkjawhke");
 			System.out.println(piston);
 		}
 	
}

