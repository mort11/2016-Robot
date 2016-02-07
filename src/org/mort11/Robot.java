package org.mort11;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.mort11.commands.auton.DriveArc;
import org.mort11.commands.auton.DriveStraight;
import org.mort11.commands.ee.SpinUp;
import org.mort11.commands.auton.WaitTime;

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
    public static OI oi;
    public static HardwareAdaptor adaptor = new HardwareAdaptor();
    Command spinUp;
    Command driveArc;
    Command autonomousCommand;
    SendableChooser autonomousChooser;

    @Override
    public void robotInit() {
        driveArc = new DriveArc(1.33 * Math.PI, 0.5 * Math.PI);

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
