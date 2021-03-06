package org.usfirst.frc.team236.robot.commands.launcher;

import org.usfirst.frc.team236.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootUp extends Command {

	public ShootUp() {
		// did not use requires because the wheels need to spin at the same time
		// requires(Robot.launcher);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.launcher.extend();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
