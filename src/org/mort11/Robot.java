package org.mort11;

import org.mort11.commands.auton.DriveArc;
import org.mort11.commands.auton.DriveStraight;
import org.mort11.commands.auton.LowBarAuton;
import org.mort11.commands.auton.LowBarLowGoal;
import org.mort11.commands.auton.ObstacleAuton;
import org.mort11.commands.auton.WaitTime;
import org.mort11.commands.auton.positional.Pos4Auton;
import org.mort11.commands.ee.HoodToggle;
import org.mort11.commands.ee.SpinUp;
import org.mort11.util.Logger;

import edu.wpi.first.wpilibj.CameraServer;
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
 * @author Jakob Shortell
 * @author Seven Kurt
 * @author Michael Kozak
 * @author Jeffrey Pastilha
 * @author Ryan O'Toole
 * @author Carl Hausman */
public class Robot extends IterativeRobot {
    public static OI oi;
    public static HardwareAdaptor adaptor = new HardwareAdaptor();
    public static NetworkTable table;
    
    Command autonomousCommand = new WaitTime(0);
    SendableChooser autoModes;
    SendableChooser portcullis;
    
    NetworkTable table_location;
	public static CameraServer cam;
    
    @Override
    public void robotInit() {
        oi = new OI();        
       // new Rainbow();
        // Have operator choose autonomous mode
//        cam = CameraServer.getInstance();
//        cam.setQuality(25);
//        cam.setSize(100);
//        cam.startAutomaticCapture("cam0");
        autoModes = new SendableChooser();
        autoModes.addDefault("Do Nothing for 10s", new WaitTime(10));
        autoModes.addObject("Drive Straight over obstacle", new ObstacleAuton());
        autoModes.addObject("Take Shot", new LowBarAuton());

//        portcullis = new SendableChooser();
//        // TODO: 2/21/2016 Write auto commands for these
//        portcullis.addDefault("Portcullis", new WaitTime(0));
//        portcullis.addObject("No Portcullis", new WaitTime(0));
//
        SmartDashboard.putData("Auto Mode", autoModes);
//        SmartDashboard.putData("Portcullis", portcullis);
        SmartDashboard.putString("RPM", "too slow!");
        
        table = NetworkTable.getTable("GRIP/myContoursReport");
        table_location = NetworkTable.getTable("locations");

      
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
//    	autonomousCommand = new DriveStraight(40);
//        autonomousCommand.start();
    	Logger.init("/home/lvuser/MAR_CMP");
    	adaptor.leftDTEncoder.reset();
    	adaptor.rightDTEncoder.reset();
    	adaptor.ahrs.zeroYaw();
    	autonomousCommand = (Command) new Pos4Auton();
    	//autonomousCommand = new LowBarAuton();
        autonomousCommand.start();
//    	new AdjustToGoal().start();
//    	new DriveForwardToGoal().start();
//    	new CamAuton().start();
    }

    @Override
    public void autonomousPeriodic() {
    	System.out.println(adaptor.leftDTEncoder.getDistance() + 
    			"left " + adaptor.rightDTEncoder.getDistance() + " right ");
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
       //new MotorToAngle(90).start();
    	//new SpinUp(98000, true).start();
    	//new HoodToggle().start();
    	adaptor.ahrs.zeroYaw();
    	//System.out.println("normal output: " + adaptor.intakeArm.getAngle());    	
        autonomousCommand.cancel();
       
    }

    @Override
    public void disabledInit() {
    	//new SpinUp(0,true).start();
        System.out.println("Disabled. Code halted!");
    	new SpinUp(0,true).start();
//        System.out.println("Disabled. Code halted!");
    }

    
    @Override
    public void teleopPeriodic() {
    	//System.out.println("left: " + adaptor.leftDTEncoder.get());
    	table_location.putNumber("mag", adaptor.leftDTEncoder.get());
    	table_location.putNumber("theta", adaptor.ahrs.getYaw());
        Scheduler.getInstance().run();
//        System.out.println("left output: " + adaptor.leftSide.getSpeed());
//        System.out.println("left comm: " + adaptor.leftSide.getCurrentCommand());
//        System.out.println("right output: " + adaptor.rightSide.getSpeed());
//        System.out.println("right comm: " + adaptor.rightSide.getCurrentCommand());
        System.out.println("left dist: " + adaptor.leftDTEncoder.getDistance() + 
        		" right dist: " + adaptor.rightDTEncoder.getDistance() + 
        		" intake ang: " + adaptor.intakeArm.getAngle() + 
        		" shooter RPM" + adaptor.flywheel.getSpeed());
    	//System.out.println("right dist: " + adaptor.rightDTEncoder.getDistance());
        //System.out.println(adaptor.leftSide.getCurrentCommand() + " left command");
        //System.out.println(adaptor.rightSide.getCurrentCommand() + " right command");
        //System.out.println(adaptor.intakeArm.getAngle() + " norm input");
        //System.out.println("yaw: " + adaptor.ahrs.getYaw());
        adaptor.intakeArm.getAngle();
        //System.out.println();
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
