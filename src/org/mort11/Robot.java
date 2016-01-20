package org.mort11;

import org.mort11.commands.DrivePID;
import org.mort11.subsystems.DT;
import org.mort11.subsystems.ee.ShootingMechanism;
import org.mort11.OI;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot {

	public static final ShootingMechanism ShootMech = new ShootingMechanism();
	public static OI oi;
    public static final DT dt = new DT();
    public static final ShootingMechanism intakeArm = new ShootingMechanism();
    Command autonomousCommand;
    Command DrivePIDCommand;

    public void robotInit() {
        oi = new OI();
        DrivePIDCommand = new DrivePID(120); 
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        if (autonomousCommand != null) DrivePIDCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if (autonomousCommand != null) DrivePIDCommand.cancel();
    }

    public void disabledInit() {

    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    public void testPeriodic() {
        LiveWindow.run();
    }
}
