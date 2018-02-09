package org.usfirst.frc.team236.robot.subsystems;

import org.usfirst.frc.team236.robot.RobotMap;
import org.usfirst.frc.team236.robot.commands.DriveWithJoysticks;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import lib.pid.PIDSource;
import lib.pid.PIDOutput;

/**
 *
 */
public class Drive extends Subsystem implements PIDSource, PIDOutput {

	public TalonSRX leftFrontMaster, leftRearSlave;
	public TalonSRX rightFrontMaster, rightRearSlave;
	// public TalonSRX leftMiddleSlave, rightMiddleSlave;

	public AHRS navx;

	public Drive() {
		navx = new AHRS(SPI.Port.kMXP);

		leftFrontMaster = new TalonSRX(RobotMap.DriveMap.ID_LEFT_FRONT);
		rightFrontMaster = new TalonSRX(RobotMap.DriveMap.ID_RIGHT_FRONT);
		leftRearSlave = new TalonSRX(RobotMap.DriveMap.ID_LEFT_REAR);
		rightRearSlave = new TalonSRX(RobotMap.DriveMap.ID_RIGHT_REAR);
		// leftMiddleSlave = new TalonSRX(RobotMap.DriveMap.ID_LEFT_MIDDLE);
		// rightMiddleSlave = new TalonSRX(RobotMap.DriveMap.ID_RIGHT_MIDDLE);

		leftRearSlave.set(ControlMode.Follower, leftFrontMaster.getDeviceID());
		rightRearSlave.set(ControlMode.Follower, rightFrontMaster.getDeviceID());
		// leftMiddleSlave.set(ControlMode.Follower, leftFrontMaster.getDeviceID());
		// rightMiddleSlave.set(ControlMode.Follower, rightFrontMaster.getDeviceID());

		// set "true" if needed to make encoder reading positive when TalonSRX blinks
		// green
		leftFrontMaster.setSensorPhase(true);
		rightFrontMaster.setSensorPhase(false);
	}

	public void setLeftSpeed(double speed) {
		leftFrontMaster.set(ControlMode.PercentOutput, speed);
	}

	public void setRightSpeed(double speed) {
		rightFrontMaster.set(ControlMode.PercentOutput, speed);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoysticks());
	}

	public void pidSet(double speed) {
		setLeftSpeed(-speed);
		setRightSpeed(speed);
	}

	// autonomous
	public void resetEncoders() {
		leftFrontMaster.setSelectedSensorPosition(0, 0, 0);
		rightFrontMaster.setSelectedSensorPosition(0, 0, 0);
	}

	public void setMotnCV(int vcruise) {
		rightFrontMaster.configMotionCruiseVelocity(vcruise, 0);
		leftFrontMaster.configMotionCruiseVelocity(vcruise, 0);
	}

	public void setMotnAccel(int accel) {
		rightFrontMaster.configMotionAcceleration(accel, 0);
		leftFrontMaster.configMotionAcceleration(accel, 0);
	}

	public void setRightDistMotion(double distance) {
		rightFrontMaster.set(ControlMode.MotionMagic, distance);
	}

	public void setLeftDistMotion(double distance) {
		leftFrontMaster.set(ControlMode.MotionMagic, distance);
	}

	public int getRightDistance() {
		return rightFrontMaster.getSensorCollection().getQuadraturePosition();
	}

	public double getRightSpeed() {
		return rightFrontMaster.getSelectedSensorVelocity(0);
	}

	public void stop() {
		leftFrontMaster.set(ControlMode.PercentOutput, 0);
		rightFrontMaster.set(ControlMode.PercentOutput, 0);
	}

	public int getLeftEncoder() {
		return leftFrontMaster.getSelectedSensorPosition(0);
	}

	public int getRightEncoder() {
		return rightFrontMaster.getSelectedSensorPosition(0);
	}

	@Override
	public double pidGet() {
		return navx.getAngle();
	}
}