package org.mort11.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.subsystems.ee.Shooter;

public class Shoot extends Command {
    Shooter shooter = Robot.adaptor.shooter;

    @Override
    protected void initialize() {
        requires(shooter);
    }

    @Override
    protected void execute() {
        if (Robot.oi.spinUp.get()) {
            shooter.set(0.8);
        } else {
            shooter.set(0);
        }
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
