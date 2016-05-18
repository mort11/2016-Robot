package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.subsystems.ee.IntakeArm;

/**
 * DEPRECATED: NO LONGER USING THIS MODEL INTAKE
 * <p>
 * Hold the intake arm at an angle
 *
 * @author Matt Turi
 * @author Ryan Thant
 * @author Seven Kurt
 * @author Ryan O'Toole
 * @author Sahit Chintalapudi
 */
@Deprecated
public class IntakeArmToAngle extends Command {
    private double target = 90;
    private IntakeArm intake = Robot.adaptor.intakeArm;

    public IntakeArmToAngle(double target) {
        requires(intake);
        this.target = target;
    }

    @Override
    protected void initialize() {
        this.setInterruptible(true);
    }

    @Override
    protected void execute() {
        double angle = intake.getAngle();

        double output = (target - angle) * 0.01;

        if (output > 0.3) {
            output = 0.3;
        }

        intake.set(output);
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
