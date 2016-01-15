package org.mort11;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.mort11.commands.ee.*;
import org.mort11.util.TeleopConstants;

public class OI {
boolean button = false; 
Joystick left = new Joystick(TeleopConstants.LEFT_JOYSTICK);
Joystick right = new Joystick(TeleopConstants.RIGHT_JOYSTICK);
public Joystick ee = new Joystick(TeleopConstants.EE_JOYSTICK);
 JoystickButton pb = new JoystickButton(ee, TeleopConstants.PISTON_BUTTON );
 
 public OI() {
	 pb.whenPressed(new PistonActuation());
 }
}

