package org.mort11.commands.ee;

import org.mort11.Robot;
import org.mort11.sensors.SensorDealer;
import org.mort11.subsystems.ee.Intake;
import org.mort11.util.PIDLoop;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 */
public class RollerUp extends Command {
    private Intake intake = Robot.adaptor.intake;
    private Encoder intakeEncoder = SensorDealer.getInstance().getArmEncoder();
    private PIDLoop pd;
    private double desiredAngle,currentAngle, speed;

    public RollerUp(double Angle) {
        requires(intake);
        this.desiredAngle = Angle;
        pd = new PIDLoop(desiredAngle, .01, 0);
    }

    protected void initialize() {
        intakeEncoder.reset();
        
    }

    protected void execute() {
        currentAngle = Intake.getAngle();
        speed = pd.getOutput(currentAngle);
        intake.set(speed);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        intake.set(0);
        intakeEncoder.reset();
    }

    protected void interrupted() {
    }
}
