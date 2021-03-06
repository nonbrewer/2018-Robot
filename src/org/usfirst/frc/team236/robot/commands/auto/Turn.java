package org.usfirst.frc.team236.robot.commands.auto;

import org.usfirst.frc.team236.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import lib.pid.PID;
import lib.pid.PIDParameters;

/**
 *
 */
public class Turn extends Command {

	private PID pid;
	private double degrees;
	private double margin;
	private double angleError;

	public static PIDParameters TURN_PARAMS;

	public Turn(double _degrees, double _margin, PIDParameters _pid) {
		requires(Robot.drive);
		this.degrees = _degrees;
		this.margin = _margin;

		pid = new PID(Robot.drive, Robot.drive, _pid);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		pid.setSetpoint(degrees);

		Robot.drive.navx.reset();
		pid.enable();

		pid.update();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		angleError = pid.getError();
		SmartDashboard.putNumber("Angle Error", angleError);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return ((Math.abs(angleError) < margin) && (Math.abs(Robot.drive.navx.getRate()) < 0.25));
	}

	// Called once after isFinished returns true
	protected void end() {
		pid.disable();
		Robot.drive.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
