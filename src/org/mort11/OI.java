package org.mort11;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.mort11.commands.ee.*;
import org.mort11.commands.ee.IntakeArm;
import org.mort11.util.TeleopConstants;


	public class OI {
		boolean button = false; 
		Joystick left = new Joystick(TeleopConstants.LEFT_JOYSTICK);
		Joystick right = new Joystick(TeleopConstants.RIGHT_JOYSTICK);
		public Joystick ee = new Joystick(TeleopConstants.EE_JOYSTICK);
 		JoystickButton pistonButton = new JoystickButton(ee, TeleopConstants.PISTON_BUTTON );
 		JoystickButton JoyButton1 = new JoystickButton(ee, TeleopConstants.JOYSTICK_BUTTON1);
 	
 	public OI() {
 		pistonButton.whenPressed(new PistonActuation());
 		JoyButton1.whenPressed(new IntakeArm());
 	}
 	
 

public class Oi{
boolean button = false; 
Joystick left = new Joystick(TeleopConstants.LEFT_JOYSTICK);
Joystick right = new Joystick(TeleopConstants.RIGHT_JOYSTICK);
public Joystick ee = new Joystick(TeleopConstants.EE_JOYSTICK);
 JoystickButton pb = new JoystickButton(ee, TeleopConstants.PISTON_BUTTON );
<<<<<<< HEAD
 
 public OI() {
	 pb.whenPressed(new PistonActuation());
 }
=======
 JoystickButton intakeMotors = new JoystickButton(ee, TeleopConstants.INTAKE_BUTTON);
 JoystickButton outtakeMotors = new JoystickButton(ee, TeleopConstants.OUTTAKE_BUTTON);
}
>>>>>>> 65ca2714650d6af6ec5846bad49400b67cc74cbf
}

