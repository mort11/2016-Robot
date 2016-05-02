package org.mort11;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.mort11.commands.SubsystemStates;
import org.mort11.commands.auton.AdjustToGoal;
import org.mort11.commands.auton.CamAuton;
import org.mort11.commands.auton.TurnDegrees;
import org.mort11.commands.auton.TurnToGoal;
import org.mort11.commands.dt.shifting.Shift;
import org.mort11.commands.ee.*;
import org.mort11.constants.Constants;
import org.mort11.util.SpeedController;

/**
 * OI - Joystick mapping to buttons and other math stuff
 *
 * @author Sahit Chintalapudi
 * @author Matthew Krzyzanowski
 * @author Matt Turi
 * @author Ryan Thant
 * @author Seven Kurt
 * @author Michael Kozak
 * @author Ryan O'Toole
 */
public class OI {

    // Joysticks
    public Joystick leftJoystick = new Joystick(Constants.LEFT_JOYSTICK);
    public Joystick rightJoystick = new Joystick(Constants.RIGHT_JOYSTICK);
    public Joystick endEffector = new Joystick(Constants.EE_JOYSTICK);

    public Button shiftDown = new JoystickButton(leftJoystick, Constants.SHIFT_DOWN_BUTTON);
    public Button shiftUp = new JoystickButton(rightJoystick, Constants.SHIFT_UP_BUTTON);

    // EE Joystick
    public Button spinUp = new JoystickButton(endEffector, Constants.SPIN_UP_BUTTON);
    //public Button spinUpFast = new JoystickButton(endEffector, Constants.SPIN_UP_BUTTON_FAST);
    public Button spinDown = new JoystickButton(endEffector, Constants.STOP_FLYWHEEL);
    public Button intakeRoller = new JoystickButton(endEffector, Constants.INTAKE_BUTTON);
    public Button outtakeRoller = new JoystickButton(endEffector, Constants.OUTTAKE_BUTTON);
    public Button hoodToggle = new JoystickButton(endEffector, Constants.TOGGLE_HOOD);
    public Button armToNinety = new JoystickButton(endEffector, Constants.ARM_TO_90);
    public Button armToZero = new JoystickButton(endEffector, Constants.ARM_TO_0);
    public Button shooterButton = new JoystickButton(endEffector, 1);
    public Button goToGoal = new JoystickButton(endEffector, 6);
    	
    
    public OI() {
        intakeRoller.whileHeld(new IntakeRollers(SubsystemStates.RollerRequest.INTAKE));
        intakeRoller.whenReleased(new IntakeRollers(SubsystemStates.RollerRequest.STOP));

        outtakeRoller.whileHeld(new IntakeRollers(SubsystemStates.RollerRequest.EXHAUST));
        outtakeRoller.whenReleased(new IntakeRollers(SubsystemStates.RollerRequest.STOP));

        shiftUp.whenPressed(new Shift(SubsystemStates.Gear.HIGH));
        shiftDown.whenPressed(new Shift(SubsystemStates.Gear.LOW));

        //77k
        spinUp.whenPressed(new SpinUp(60000, true));
        //spinUpFast.whenPressed(new SpinUp(91000, true));        
        spinDown.whenPressed(new SpinUp(0, true));
        hoodToggle.whenPressed(new HoodToggle());
        armToNinety.whenPressed(new ActuatePistonIntake(SubsystemStates.Intake.UP)); // Moves the intake arm to 90 degrees when pressed
        armToZero.whenPressed(new ActuatePistonIntake(SubsystemStates.Intake.DOWN)); // Moves the intake arm to 90 degrees when pressed
        
        shooterButton.whenPressed(new Shoot());
        goToGoal.whenPressed(new TurnToGoal(false));

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
        return (endEffector.getThrottle() + 1) / 2;
    }
    
    public boolean getUpPOV() {
    	return endEffector.getPOV() == 0;
    }
    
    public boolean getDownPOV() {
    	return endEffector.getPOV() == 180;
    }

}