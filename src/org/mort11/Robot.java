package org.mort11;

import org.mort11.commands.DrivePID;
import org.mort11.subsystems.DT;
import org.mort11.subsystems.ee.Shooter;
import org.mort11.subsystems.ee.Pneumatics;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.mort11.commands.DisplayCurrents;
import org.mort11.commands.auton.DriveArc;
import org.mort11.commands.auton.DriveStraight;
import org.mort11.subsystems.dt.DTLeft;
import org.mort11.subsystems.dt.DTRight;
import org.mort11.commands.dt.*;
import org.mort11.commands.dt.DriveLinearLeft;
import org.mort11.commands.dt.DriveLinearRight;
import org.mort11.subsystems.dt.DT;
import org.mort11.subsystems.ee.Pneumatics;

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
    public static OI oi;
    public static DT dt;

    Command DriveStraight;
    Command DispCurrent;
    Command DriveLinearLeft;
    Command DriveLinearRight;
    Command driveArc;

    public void robotInit() {
        //piston = new Pneumatics(RobotMap.PNE_ENG1, RobotMap.PNE_ENG2);
        //dt = new DT();
        //DispCurrent = new DisplayCurrents();
        //DriveStraight = new DriveStraight(200);
        DriveLinearLeft = new DriveLinearLeft();
        DriveLinearRight = new DriveLinearRight();
        driveArc = new DriveArc(1.33*Math.PI , 0.5*Math.PI);
        oi = new OI();
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        System.out.println("auton initting");
        //if (DriveStraight != null) {
        	driveArc.start();
        	//DispCurrent.start();
        //}

    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        /**System.out.print("accel output x " + accel.getX()  + "   ");
        System.out.print("accel output y " + accel.getY()  + "   ");
        System.out.println("accel output z " + accel.getZ());
        Timer.delay(0.5);**/
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
