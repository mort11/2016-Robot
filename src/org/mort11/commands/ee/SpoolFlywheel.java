package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.mort11.Robot;
import org.mort11.subsystems.ee.Flywheel;

/**
 * Spools the flywheel to a specified RPM
 *
 * @author Matt Turi
 * @author Seven Kurt
 * @author Matthew Krzyzanowski
 * @author Sahit Chintalapudi
 */
public class SpoolFlywheel extends Command {
    private Flywheel flywheel = Robot.adaptor.flywheel;
    private boolean shouldUsePID;
    private double unregulatedSpeed, velocity, flywheelSpeed;

    public SpoolFlywheel(double velocity, boolean shouldUsePID) {
        requires(flywheel);
        setInterruptible(true);
        this.velocity = velocity;
        this.shouldUsePID = shouldUsePID;
    }

    @Override
    protected void initialize() {
        setInterruptible(true);
    }

    @Override
    protected void execute() {
        if (this.velocity == 0) {
            flywheel.set(0);
            return;
        }
        if (shouldUsePID) {
            // Uses a PID controller to maintain given RPM
            double currentVelocity = flywheel.getSpeed();

            if ((currentVelocity - velocity) < 1000 && (currentVelocity - velocity) > 0) {
                currentVelocity = velocity;
            }

            double deltaRPM = (velocity - currentVelocity) * 0.0000002;

            flywheelSpeed = flywheelSpeed + deltaRPM;
            if (flywheelSpeed > 1) {
                flywheelSpeed = 1;
            } else if (flywheelSpeed < 0) {
                flywheelSpeed = 0;
            }
            flywheel.set(flywheelSpeed);

            double percentError = (velocity - currentVelocity) / velocity;
            if (percentError > -0.02 && percentError < 0.05) {
                flywheel.setReadyFireState(true);
                SmartDashboard.putString("RPM", "FIRE!");
            } else {
                flywheel.setReadyFireState(false);
                SmartDashboard.putString("RPM", "STANDBY");
            }
        } else {
            // Spool flywheel without it being regulated by a PID controller. Instead uses a Bang-Bang controller
            double currentVelocity = flywheel.getSpeed();

            if (currentVelocity < velocity) {
                unregulatedSpeed += .03;
                flywheel.set(unregulatedSpeed);
            } else {
                unregulatedSpeed -= .03;
                flywheel.set(unregulatedSpeed);
            }
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        flywheel.set(0);
    }

    @Override
    protected void interrupted() {
        flywheel.set(0);
    }
}
