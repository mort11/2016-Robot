package org.mort11;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.mort11.constants.OperatorInterfaceConstants;
import org.mort11.commands.FullSpeed;

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
//    protected static boolean enabled_fullSpeed;
    public static int count_fullSpeed = 0;
    
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
        fullSpeed.whenPressed(new FullSpeed());
    }


    public static double threshold(double input) {
        if (Math.abs(input) <= 0.05) {
            return 0;
        }
        return input / Math.abs(input) * (Math.abs(input) - 0.05) / (1 - 0.05);
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
//            enabled_fullSpeed = true;
//            count_fullSpeed++;
//        }
//        if (enabled_fullSpeed) {
//            Robot.oi.timer.start();
//            enabled_fullSpeed = false;
//        }
//        if (Robot.oi.timer.get() < 10 && Robot.oi.timer.get() > 0 && count_fullSpeed <= 20) {
//            return speed;
//        }
//        if (Robot.oi.timer.get() >= 10) {
//            //count = 2;
//            Robot.oi.timer.stop();
//            Robot.oi.timer.reset();
//            System.out.println("timer: " + Robot.oi.timer.get());
//        }
//        if (!enabled_fullSpeed) {
//            if (speed >= .75) {
//                speed = .75;
//            }
//            if (speed <= -.75) {
//                speed = -.75;
//            }
//        }
//        return speed;
          if (speed >= .75) {
              speed = 0.75;
          }
          if (speed <= -.75) {
              speed = -0.75;
          }
          return speed;
    }

    public double getLeftJoy_limit() {
        return speedLimit(-left.getY());
    }

    public double getRightJoy_limit() {
        return speedLimit(right.getY());
    }
    
    public double getLeftJoy_full() {
        return threshold(-left.getY());
    }

    public double getRightJoy_full() {
        return threshold(right.getY());
    }
}

