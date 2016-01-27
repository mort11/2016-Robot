package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.util.EEConstants;

public class IntakeRollers extends Command {

	boolean intakeButton = false;
	boolean outtakeButton = false;

	public IntakeRollers(boolean in, boolean out) {
		this.intakeButton = in;
		this.outtakeButton = out;
		requires(Robot.intakeRollers);
	}

	protected void initialize() {

	}

	protected void execute() {
		if (intakeButton != outtakeButton) {
			if (intakeButton == true) {
				Robot.intakeRollers.setSpeed(EEConstants.IN_ROLLER_SPEED);
			} else if (outtakeButton == true) {
				Robot.intakeRollers.setSpeed(EEConstants.OUT_ROLLER_SPEED);
			}
		}else{
			Robot.intakeRollers.setSpeed(0);
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {

	}

	protected void interrupted() {

	}

}
