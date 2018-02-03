/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team78.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	public static final int LEFT_FRONT = 1;
	public static final int LEFT_TOP = 3;
	public static final int LEFT_BOTTOM = 2;
	public static final int RIGHT_FRONT = 15;
	public static final int RIGHT_TOP = 14;
	public static final int RIGHT_BOTTOM = 16;
	
	public static final int RIGHT_ENC_A = 0;
	public static final int RIGHT_ENC_B = 1;
	public static final int LEFT_ENC_A = 5;
	public static final int LEFT_ENC_B = 4;
	
	public static final int SHIFT_HIGH = 1; //dogs inward
	public static final int SHIFT_LOW = 0; //dogs outward
	
	public static final double WHEEL_DIAMETER = 6;
	public static final double PULSES_PER_ROTATION = 4096;
	
}
