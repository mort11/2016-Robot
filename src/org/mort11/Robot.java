package org.mort11;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.mort11.commands.auton.DriveArc;
import org.mort11.commands.auton.DriveStraight;
import org.mort11.commands.auton.TurnDegrees;
import org.mort11.commands.auton.WaitTime;
import org.mort11.commands.auton.DriveStraighter;
import org.mort11.util.Logger;

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
 * @author Jakob Shortell <jshortell@mort11.org>
 */
public class Robot extends IterativeRobot {
    public static OI oi;
    public static HardwareAdaptor adaptor = new HardwareAdaptor();

    //Command driveArc;
    Command autonomousCommand;
    Command turnDegrees;
    Command DriveStraighter;
    //SendableChooser autonomousChooser;

    @Override
    public void robotInit() {

        //driveArc = new DriveArc(18 * Math.PI, 0.5 * Math.PI);
        //driveArc = new DriveStraight(200);
        turnDegrees = new TurnDegrees (90);
        DriveStraighter = new DriveStraighter();
        
        //Logger.init("/home/lvuser/test");

        oi = new OI();

        // Have operator choose autonomous mode
//        autonomousChooser = new SendableChooser();
//        autonomousChooser.addDefault("Do Nothing for 10s", new WaitTime(10));
//        autonomousChooser.addObject("Drive Straight [20in.]", new DriveStraight(20));
//        autonomousChooser.addObject("Drive Arc [Unknown units]", new DriveArc(12 * Math.PI, 0.5 * Math.PI));
//        SmartDashboard.putData("Autonomous Mode", autonomousChooser);
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        System.out.println("STARTING AUTONOMOUS");
        //DriveStraighter.start();
        turnDegrees.start();
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
