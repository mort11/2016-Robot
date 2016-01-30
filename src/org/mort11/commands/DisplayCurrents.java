package org.mort11.commands;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DisplayCurrents extends Command {

    PowerDistributionPanel pdp;

    // current values for each monitored PDP channel (0 - 15) in Amps
    // can comment out any channels that are not in use
    //private double cur0;
    private double cur1;
    private double cur2;
//  private double cur3;
//  private double cur4;
//  private double cur5;
//  private double cur6;
//  private double cur7;
//  private double cur8;
//  private double cur9;
//  private double cur10;
//  private double cur11;
//  private double cur12;
//  private double cur13;
//  private double cur14;
//  private double cur15;

    private double temp; // temperature of PDP in degrees Celsius
    private double totalCur; // current of all monitored PDP channels in Amps
    private double totalEnergy; // total energy draw of all monitored PDP channels, give in Joules
    private double totalPowerDraw; // total power draw of all monitored PDP channels, given in Watts
    private double voltage; // input voltage for PDP in Watts

	public DisplayCurrents() {

	}

	protected void initialize() {
	    pdp = new PowerDistributionPanel();
	}

	protected void execute() {
//	    cur0 = pdp.getCurrent(0);
	    cur1 = pdp.getCurrent(1);
	    cur2 = pdp.getCurrent(2);
//	    cur3 = pdp.getCurrent(3);
//	    cur4 = pdp.getCurrent(4);
//	    cur5 = pdp.getCurrent(5);
//      cur6 = pdp.getCurrent(6);
//      cur7 = pdp.getCurrent(7);
//      cur8 = pdp.getCurrent(8);
//      cur9 = pdp.getCurrent(9);
//      cur10 = pdp.getCurrent(10);
//      cur11 = pdp.getCurrent(11);
//      cur12 = pdp.getCurrent(12);
//      cur13 = pdp.getCurrent(13);
//      cur14 = pdp.getCurrent(14);
//      cur15 = pdp.getCurrent(15);
	    temp = pdp.getTemperature();
	    totalCur = pdp.getTotalCurrent();
	    totalEnergy = pdp.getTotalEnergy();
	    totalPowerDraw = pdp.getTotalPower();
	    voltage = pdp.getTotalCurrent();

//      SmartDashboard.putNumber("PDP Current Channel 0", cur0);
	    SmartDashboard.putNumber("PDP Current Channel 1", cur1);
	    SmartDashboard.putNumber("PDP Current Channel 2", cur2);
//      SmartDashboard.putNumber("PDP Current Channel 3", cur3);
//      SmartDashboard.putNumber("PDP Current Channel 4", cur4);
//      SmartDashboard.putNumber("PDP Current Channel 5", cur5);
//      SmartDashboard.putNumber("PDP Current Channel 6", cur6);
//      SmartDashboard.putNumber("PDP Current Channel 7", cur7);
//      SmartDashboard.putNumber("PDP Current Channel 8", cur8);
//      SmartDashboard.putNumber("PDP Current Channel 9", cur9);
//      SmartDashboard.putNumber("PDP Current Channel 10", cur10);
//      SmartDashboard.putNumber("PDP Current Channel 11", cur11);
//      SmartDashboard.putNumber("PDP Current Channel 12", cur12);
//      SmartDashboard.putNumber("PDP Current Channel 13", cur13);
//      SmartDashboard.putNumber("PDP Current Channel 14", cur14);
//      SmartDashboard.putNumber("PDP Current Channel 15", cur15);
	    SmartDashboard.putNumber("PDP Temperature", temp);
	    SmartDashboard.putNumber("PDP Total Current", totalCur);
	    SmartDashboard.putNumber("PDP Total Energy", totalEnergy);
	    SmartDashboard.putNumber("PDP Total Power Draw", totalPowerDraw);
	    SmartDashboard.putNumber("PDP Voltage", voltage);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
