package org.mort11;

import org.mort11.commands.DrivePID;
import org.mort11.subsystems.DT;
import org.mort11.subsystems.ee.Pneumatics;
import org.mort11.subsystems.ee.ShootingMechanism;
import org.mort11.OI;
import org.mort11.RobotMap;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

//install mDNS client (check if already installed): http://wpilib.screenstepslive.com/s/4485/m/13503/l/242608-roborio-networking

public class Robot extends IterativeRobot {
	

	public static final ShootingMechanism ShootMech = new ShootingMechanism();
	public static OI oi;
    public static final DT dt = new DT();
    public static final Pneumatics piston = new Pneumatics(RobotMap.Pne_Eng1, RobotMap.Pne_Eng2);
    public static final ShootingMechanism intakeArm = new ShootingMechanism();
    public static final ShootingMechanism limMotor = new ShootingMechanism();
    public static final ShootingMechanism intakeRollers = new ShootingMechanism();
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
