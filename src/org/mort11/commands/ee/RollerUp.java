package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.subsystems.ee.IntakeArm;
import org.mort11.util.PIDLoop;

/**
 * RollerUp - Keeps the roller up at a constant angle
 *
 * @author Matthew Krzyzanowski
 */
public class RollerUp extends Command {
    private IntakeArm intakeArm = Robot.adaptor.intakeArm;
    private PIDLoop pd;
    private double desiredAngle, currentAngle, speed;

    public RollerUp(double Angle) {
        requires(intakeArm);
        this.desiredAngle = Angle;
        pd = new PIDLoop(desiredAngle, .01, 0);
    }

    protected void initialize() {
    }

    protected void execute() {
        currentAngle = intakeArm.getAngle();
        speed = pd.getOutput(currentAngle);
        intakeArm.set(speed);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        intakeArm.set(0);
    }

    protected void interrupted() {
    }
}
