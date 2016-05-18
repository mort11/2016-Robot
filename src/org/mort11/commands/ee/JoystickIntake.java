package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.subsystems.ee.IntakeArm;

/**
 * DEPRECATED: NO LONGER USING THIS MODEL INTAKE
 * <p>
 * Controls the angle that the intake is at relative to the angle the operator's joystick is at
 *
 * @author Sahit Chintalapudi
 * @author Matt Turi
 */
@Deprecated
public class JoystickIntake extends Command {
    private double netError, lastTime;
    private Timer timer;
    private IntakeArm intakeArm = Robot.adaptor.intakeArm;

    public JoystickIntake() {
        requires(intakeArm);
        timer = new Timer();
    }

    @Override
    protected void initialize() {
        this.setInterruptible(true);
        timer.start();
    }

    @Override
    protected void execute() {
        double maxDown = 180;
        double desiredLocation = ((-maxDown / 2) * Robot.oi.getEEJoy() + maxDown / 2) - 5;

        // Set upper and lower limits for how far the intake can move
        if (desiredLocation > 110) {
            desiredLocation = 110;
        } else if (desiredLocation < 0) {
            desiredLocation = 0;
        }

        // Use a PID controller to maintain arm angle
        double error = desiredLocation - intakeArm.getAngle();
        double output = error * 0.010;
        double currentTime = timer.get();
        netError += (currentTime - lastTime) * error;
        if (netError > 30) {
            netError = 30;
        } else if (netError < -30) {
            netError = -30;
        }
        output += (netError * 0.005);
        if (output > 0.2) {
            output = 0.2;
        } else if (output < -1) {
            output = -1;
        }
        intakeArm.set(output);
        lastTime = currentTime;
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}
