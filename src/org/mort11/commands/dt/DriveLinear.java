package org.mort11.commands.dt;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.mort11.HardwareAdaptor;
import org.mort11.subsystems.dt.DTSide;

public abstract class DriveLinear extends Command {

    protected DTSide side;
    private DTSide left = HardwareAdaptor.leftSide;
    private DTSide right = HardwareAdaptor.rightSide;

    public DriveLinear(DTSide drivetrain) {
        requires(drivetrain);
        setInterruptible(true);
        side = drivetrain;
    }

    protected void initialize() {
    }

    protected abstract double getSpeed();

    protected void execute() {
        side.setSpeed(getSpeed());
        SmartDashboard.putNumber("Left PWM Value", left.getSpeed()); //gets most recently set PWM value, between -1.0 and 1.0
        SmartDashboard.putNumber("Right PWM Value", right.getSpeed()); //gets most recently set PWM value, between -1.0 and 1.0
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
