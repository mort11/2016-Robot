package org.mort11.subsystems.ee;

import org.mort11.commands.ee.JoystickIntake;
import org.mort11.constants.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * IntakeArm - Controls the intake arm
 *
 * @author Sahit Chintalapudi
 */
public class IntakeArm extends Subsystem {
    private CANTalon intakeArm;
    private double initPos;
    private DigitalInput limswitch;
    double angle;
    int timesCalled = 0;
    public IntakeArm() {
        intakeArm = new CANTalon(Constants.INTAKE_ARM_TALON_ID);
        //limswitch = new DigitalInput(Constants.ARM_LIM_SWITCH_PORT);
        initPos = intakeArm.getEncPosition();
//        System.out.println("init pos: "  + initPos);
    }

    /**
     * Get position of intakeArm arm from encoder reading as angle
     *
     * @return Encoder angle
     */
    public double getAngle() {
    	//System.out.println("analog out:" + intakeArm.getAnalogInRaw());
        //System.out.println("ticks: "  + intakeArm.getEncPosition() + " times called: " + timesCalled);
    	intakeArm.getEncPosition();
    	//System.out.println("angle in intakearm class: " + angle;
    	angle = ((intakeArm.getEncPosition() - initPos) * 0.092); //:'(
    	timesCalled++;
        return angle; //:'(
    }


    
    /**
     * Tells if the intake has been driven too far back
     * @return whether the limit switch has been struck
     */
    public boolean isLimitSwitch() {
    	return false;
    }
    // TODO: 2/19/2016 Rewrite limit switch code here when Mr. Thant finishes mounting lim switch

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new JoystickIntake());
    }

    /**
     * Adjust position of intakeArm arm
     *
     * @param speed Amount to move arm by
     */
    public void set(double speed) {
        intakeArm.set(speed);
    }
}