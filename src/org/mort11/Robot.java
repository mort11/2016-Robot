package org.mort11;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.mort11.commands.auton.DriveArc;
<<<<<<< HEAD
import org.mort11.subsystems.ee.Pneumatics;
import org.mort11.util.Logger;
import org.mort11.subsystems.ee.ShootingMechanism;
import org.mort11.OI;
import org.mort11.commands.DrivePID;
import org.mort11.subsystems.dt.DT;

<<<<<<< HEAD
<<<<<<< HEAD
import java.sql.Timestamp;
import java.util.Date;
=======
import org.mort11.commands.DisplayCurrents;
import org.mort11.commands.auton.DrivePID;
import org.mort11.commands.auton.DriveStraight;
import org.mort11.subsystems.dt.DT;
import org.mort11.subsystems.dt.DTLeft;
import org.mort11.subsystems.dt.DTRight;
import org.mort11.subsystems.ee.Intake;
import org.mort11.subsystems.ee.Rollers;
>>>>>>> 830d2dde43e2d35cf89f028ef516f6ec618b4fa7
=======
import java.sql.Timestamp;
import java.util.Date;
>>>>>>> 419908c65a86f490a546220fe1565cd093d66b4e
=======
import org.mort11.commands.auton.DriveStraight;
import org.mort11.commands.ee.SpinUp;
import org.mort11.commands.auton.WaitTime;
>>>>>>> 71aec0c941fc3f0a86872790a1014caf2b6d8718

/**
 * Robot - Main Robot class
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 * @author Matt Turi <mturi@mort11.org>
 * @author Ryan Thant <ryanthant1@gmail.com>
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 * @author Seven Kurt <seven.kurt@motsd.org>
 * @author Michael Kozak <michael.kozak@motsd.org>
 * @author Jeffrey Pastilha <jpmail967@yahoo.com>
 * @author Ryan O'Toole <ryan.otoole@motsd.org>
 * @author Carl Hausman <carl@hausman.org>
 */
public class Robot extends IterativeRobot {
<<<<<<< HEAD
    public static OI OI;
    public static DT dt;
    public static ShootingMechanism intakeArm;
    public static ShootingMechanism ShootMech;
    public static ShootingMechanism intakeRollers;
    public static ShootingMechanism motorToAngle;
    public static Pneumatics piston;
<<<<<<< HEAD
<<<<<<< HEAD
=======
    public static Rollers roller;
    Command DrivePIDCommand;
    Accelerometer accel;
    public static Intake intake ;
>>>>>>> 830d2dde43e2d35cf89f028ef516f6ec618b4fa7
    public static OI oi;
=======
>>>>>>> 419908c65a86f490a546220fe1565cd093d66b4e
    public static HardwareAdaptor adaptor;

=======
>>>>>>> 71aec0c941fc3f0a86872790a1014caf2b6d8718
    public static OI oi;
    public static HardwareAdaptor adaptor = new HardwareAdaptor();
    Command spinUp;
    Command driveArc;
    Command autonomousCommand;
    SendableChooser autonomousChooser;

    @Override
    public void robotInit() {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        //piston = new Pneumatics(RobotMap.PNE_ENG1, RobotMap.PNE_ENG2);
        //DispCurrent = new DisplayCurrents();
        //DriveStraight = new DriveStraight(200);
        adaptor = HardwareAdaptor.getInstance();
        DriveLinearLeft = new DriveLinearLeft();
        DriveLinearRight = new DriveLinearRight();
=======
        adaptor = HardwareAdaptor.getInstance();
>>>>>>> 419908c65a86f490a546220fe1565cd093d66b4e
=======
>>>>>>> 71aec0c941fc3f0a86872790a1014caf2b6d8718
        driveArc = new DriveArc(1.33 * Math.PI, 0.5 * Math.PI);

<<<<<<< HEAD
        Logger.init("/home/lvuser/test_" + new Timestamp(date.getTime()));
<<<<<<< HEAD
=======
    	
    	piston = new Pneumatics(RobotMap.PNE_ENG1, RobotMap.PNE_ENG2);
        comp = new Compressor(30); 
        accel = new BuiltInAccelerometer();
        //intakeArm = new Shooter();
        ShootMech = new Shooter();
        DrivePIDCommand = new DrivePID(120);
        leftSide = new DTLeft();
        rightSide = new DTRight();
        DispCurrent = new DisplayCurrents();
        DriveStraight = new DriveStraight(200);
        intake = new Intake();
        roller = new Rollers();
>>>>>>> 830d2dde43e2d35cf89f028ef516f6ec618b4fa7
=======
>>>>>>> 419908c65a86f490a546220fe1565cd093d66b4e
=======
>>>>>>> 71aec0c941fc3f0a86872790a1014caf2b6d8718
        oi = new OI();
        spinUp= new SpinUp(20,false);
        
        // Have operator choose autonomous mode
//        autonomousChooser = new SendableChooser();
//        autonomousChooser.addDefault("Do Nothing for 10s", new WaitTime(10));
//        autonomousChooser.addObject("Drive Straight [20in.]", new DriveStraight(20));
//        autonomousChooser.addObject("Drive Arc [Unknown units]", new DriveArc(1.33 * Math.PI, 0.5 * Math.PI));
//        SmartDashboard.putData("Autonomous Mode", autonomousChooser);
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        System.out.println("auton initting");
        spinUp.start();
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    @Override
    public void disabledInit() {

        // None
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        if (Robot.oi.spin.get()) {
            OI.enabled_spin = true;
        }
        if (OI.enabled_spin) {
            spinUp.start();
        }
    }

    @Override
    public void testInit() {
        System.out.println("Starting test mode...");
    }

    @Override
    public void testPeriodic() {
        LiveWindow.run();
    }
}
