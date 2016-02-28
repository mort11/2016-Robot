package org.mort11;

import org.mort11.commands.auton.AdjustToGoal;
import org.mort11.commands.auton.CamAuton;
import org.mort11.commands.auton.DriveArc;
import org.mort11.commands.auton.DriveForwardToGoal;
import org.mort11.commands.auton.DriveStraight;
import org.mort11.commands.auton.WaitTime;
import org.mort11.commands.ee.SpinUp;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Robot - Main Robot class
 *
 * @author Sahit Chintalapudi
 * @author Matt Turi
 * @author Ryan Thant
 * @author Matthew Krzyzanowski
 * @author Seven Kurt
 * @author Michael Kozak
 * @author Jeffrey Pastilha
 * @author Ryan O'Toole
 * @author Carl Hausman
 * @author Jakob Shortell
 */
public class Robot extends IterativeRobot {
    public static OI oi;
    //NetworkTable table = NetworkTable.getTable("GRIP/myContoursReport"); 
    public static HardwareAdaptor adaptor = new HardwareAdaptor();
    public static NetworkTable table;
    Command autonomousCommand;
    SendableChooser autoModes;
    SendableChooser portcullis;
    //public static final NetworkTable table = NetworkTable.getTable("GRIP/myContoursReport"); 
    
    //NetworkTable table_location = NetworkTable.getTable("locations");

    
    @Override
    public void robotInit() {
        oi = new OI();

       // new Rainbow();
        // Have operator choose autonomous mode
        autoModes = new SendableChooser();
        autoModes.addDefault("Do Nothing for 10s", new WaitTime(10));
        autoModes.addObject("Drive Straight [20in.]", new DriveStraight(20));
        autoModes.addObject("Drive Arc [Unknown units]", new DriveArc(1.33 * Math.PI, 0.5 * Math.PI));

        portcullis = new SendableChooser();
        // TODO: 2/21/2016 Write auto commands for these
        portcullis.addDefault("Portcullis", new WaitTime(0));
        portcullis.addObject("No Portcullis", new WaitTime(0));

        SmartDashboard.putData("Auto Mode", autoModes);
        SmartDashboard.putData("Portcullis", portcullis);
        SmartDashboard.putString("RPM", "too slow!");
        
        table = NetworkTable.getTable("GRIP/myContoursReport");
//        this.areas = table.getNumberArray("area", defaultValue);
//        this.centerX = table.getNumberArray("centerX", defaultValue);
//        this.centerY = table.getNumberArray("centerY", defaultValue);
//        this.width = table.getNumberArray("width", defaultValue);
//        this.height = table.getNumberArray("height", defaultValue);
//        this.solidity = table.getNumberArray("solidity", defaultValue);
      
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
//        System.out.println("STARTING AUTONOMOUS");
//        Command[] autoCommands = new Command[]{(Command) autoModes.getSelected(), (Command) portcullis.getSelected()};
//        autonomousCommand = new AutoCommand(autoCommands);
//
//        System.out.println("Running auto commands:");
//        for (Command autoCommand : autoCommands) {
//            System.out.println(autoCommand);
//        }
//    	Logger.init("/home/lvuser/auton_test1");
//    	autonomousCommand = new LowBarAuton();
//    	new AdjustToGoal().start();
//    	new DriveForwardToGoal().start();
    	new CamAuton().start();
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
       //new MotorToAngle(90).start();
    	//new SpinUp(98000, true).start();
    	//new HoodToggle().start();
//    	autonomousCommand.start();
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    @Override
    public void disabledInit() {
    	new SpinUp(0,true).start();
//        System.out.println("Disabled. Code halted!");
    }

    @Override
    public void teleopPeriodic() {

        Scheduler.getInstance().run();
    }

    @Override
    public void testInit() {
//        System.out.println("Starting test mode...");
    }

    @Override
    public void testPeriodic() {
        LiveWindow.run();
//       System.out.println(adaptor.centerX[0]);
    }
}
