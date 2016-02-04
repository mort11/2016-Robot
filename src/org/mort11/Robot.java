package org.mort11;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.mort11.commands.auton.DriveArc;
import org.mort11.commands.dt.DriveLinearLeft;
import org.mort11.commands.dt.DriveLinearRight;
import org.mort11.subsystems.ee.Pneumatics;
import org.mort11.util.Logger;

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
 */
public class Robot extends IterativeRobot {
    public static Pneumatics piston;
<<<<<<< HEAD
=======
    public static Rollers roller;
    Command DrivePIDCommand;
    Accelerometer accel;
    public static Intake intake ;
>>>>>>> 830d2dde43e2d35cf89f028ef516f6ec618b4fa7
    public static OI oi;
    public static HardwareAdaptor adaptor;

    Command DriveStraight;
    Command DispCurrent;
    Command DriveLinearLeft;
    Command DriveLinearRight;
    Command driveArc;
    Date date;

    public void robotInit() {
<<<<<<< HEAD
        //piston = new Pneumatics(RobotMap.PNE_ENG1, RobotMap.PNE_ENG2);
        //DispCurrent = new DisplayCurrents();
        //DriveStraight = new DriveStraight(200);
        adaptor = HardwareAdaptor.getInstance();
        DriveLinearLeft = new DriveLinearLeft();
        DriveLinearRight = new DriveLinearRight();
        driveArc = new DriveArc(1.33 * Math.PI, 0.5 * Math.PI);
        date = new Date();

        Logger.init("/home/lvuser/test_" + new Timestamp(date.getTime()));
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
        oi = new OI();
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        System.out.println("auton initting");
        driveArc.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if (DriveStraight != null) {
            DriveStraight.cancel();
            DispCurrent.cancel();
        }
        DriveLinearLeft.start();
        DriveLinearRight.start();
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
