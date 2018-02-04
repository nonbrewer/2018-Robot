/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team236.robot;

import org.usfirst.frc.team236.robot.commands.LaunchCycle;
import org.usfirst.frc.team236.robot.commands.SpitCycle;
import org.usfirst.frc.team236.robot.commands.intake.Eject;
import org.usfirst.frc.team236.robot.commands.intake.Feed;
import org.usfirst.frc.team236.robot.commands.intake.Intake;
import org.usfirst.frc.team236.robot.commands.intake.Lower;
import org.usfirst.frc.team236.robot.commands.intake.Raise;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import lib.oi.LogitechF310;
import lib.oi.Thrustmaster;

public class OI {
	//Sam's classes (Thrustmaser and Logitech310), otherwise need Joystick Class
	public Thrustmaster leftStick;
	public Thrustmaster rightStick;
	public LogitechF310 controller;

	public JoystickButton raise;
	public JoystickButton lower;
	public JoystickButton intake;
	public JoystickButton eject;
	public JoystickButton feed;
	
	public JoystickButton launch;
	public JoystickButton spit;

	public OI() {
		/*
		 * //Only need code below if using Joystick Class (not Sam's classes)
		 * leftStick = new Joystick(RobotMap.JoystickMap.USB_LEFT); rightStick =
		 * new Joystick(RobotMap.JoystickMap.USB_RIGHT); controller = new
		 * Joystick(RobotMap.JoystickMap.USB_CONTROLLER);
		 * 
		 * raise = new JoystickButton(controller,
		 * RobotMap.ControllerMap.RAISE_INTAKE); lower = new
		 * JoystickButton(controller, RobotMap.ControllerMap.LOWER_INTAKE);
		 * intake = new JoystickButton(controller,
		 * RobotMap.ControllerMap.INTAKE_CUBE); eject = new
		 * JoystickButton(controller, RobotMap.ControllerMap.EJECT_CUBE); feed =
		 * new JoystickButton(controller, RobotMap.ControllerMap.FEED_CUBE);
		 * 
		 * 
		 * raise.whenPressed(new RaiseIntake()); lower.whenPressed(new
		 * LowerIntake()); intake.whileHeld(new IntakeCube());
		 * eject.whileHeld(new EjectCube()); feed.whileHeld(new FeedCube());
		 */

		leftStick = new Thrustmaster(RobotMap.JoystickMap.USB_LEFT);
		rightStick = new Thrustmaster(RobotMap.JoystickMap.USB_RIGHT);
		controller = new LogitechF310(RobotMap.JoystickMap.USB_CONTROLLER);

		controller.y.whenPressed(new Raise());
		controller.a.whenPressed(new Lower());
		controller.x.whileHeld(new Intake());
		controller.b.whileHeld(new Eject());
		controller.rb.whileHeld(new Feed());
		
		controller.back.whenPressed(new SpitCycle());
		controller.start.whenPressed(new LaunchCycle());

	}

}
