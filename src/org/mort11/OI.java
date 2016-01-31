package org.mort11;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.mort11.commands.ee.PistonActuation;
import org.mort11.util.TeleopConstants;

public class OI{
	private static boolean enabled;
	private static int count = 0; 
    public Joystick ee = new Joystick(TeleopConstants.EE_JOYSTICK);
	public Joystick left = new Joystick(TeleopConstants.LEFT_JOYSTICK);
	public Joystick right = new Joystick(TeleopConstants.RIGHT_JOYSTICK);	
	public Button piston = new JoystickButton(ee, TeleopConstants.PISTON_BUTTON);
	public Button intakeRoll = new JoystickButton(ee, TeleopConstants.INTAKE_BUTTON);
	public Button outtakeRoll = new JoystickButton(ee, TeleopConstants.OUTTAKE_BUTTON);
	public Button fullSpeed = new JoystickButton(right,TeleopConstants.FULL_SPEED_BUTTON);
	private Timer timer;
	
	public OI() {
		System.out.println("OI print ln fsklafhajdskjfehakjfhkj ewahfjweahkjefhewaihfkjawhke");
		System.out.println(piston);
		System.out.println(left.getY());
		timer = new Timer();
	}
 	

    public static double doThreshold(double input) {
        if (Math.abs(input) <= 0.05) {
            return 0;
        }
        return input / Math.abs(input) * (Math.abs(input) - 0.05) / (1 - 0.05);
    }
    
    public static double setSpeed(double speed) {
        System.out.println(Robot.oi.timer.get());
        if (Robot.oi.fullSpeed.get()) {
            enabled = true;
            count ++;
        }
        if (enabled) {
            Robot.oi.timer.start();
            enabled = false;
        }
        if (Robot.oi.timer.get() < 10 && Robot.oi.timer.get() > 0 && count < 5 ) {
            return speed;
        }
        if (Robot.oi.timer.get() >= 10){
            System.out.println("stop");
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
        //return doThreshold(-left.getY());
        //return limitSpeed(-left.getY());
        return setSpeed(-left.getY());
    }

    public double getRightJoy() {
        //return doThreshold(right.getY());
        //return limitSpeed(right.getY());
        return setSpeed(right.getY());
    }
}

