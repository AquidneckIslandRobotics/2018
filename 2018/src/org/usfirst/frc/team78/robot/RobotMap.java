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
	
	//Drive
	public static final int LEFT_FRONT = 1;
	public static final int LEFT_TOP = 3;
	public static final int LEFT_BOTTOM = 2;
	public static final int RIGHT_FRONT = 15;
	public static final int RIGHT_TOP = 14;
	public static final int RIGHT_BOTTOM = 16;
	
	public static final int SHIFT_HIGH = 3; //dogs inward
	public static final int SHIFT_LOW = 2; //dogs outward
	
	public static final double STICK_DEADZONE = 0.05;
	public static final double XBOX_DEADZONE = 0.20;
	
	//Sensors
	public static final int RIGHT_ENC_A = 0;
	public static final int RIGHT_ENC_B = 1;
	public static final int LEFT_ENC_A = 5;
	public static final int LEFT_ENC_B = 4;
	public static final int ELEVATOR_ENCODER = 0;
	public static final int ARM_POT = 0; 
	public static final int BOTTOM_ELEVATOR_LIMIT = 9;
	public static final int UPPER_ELEVATOR_LIMIT = 8;
	
	//Armavator
	public static final int LEFT_ELEVATOR = 5;
	public static final int RIGHT_ELEVATOR = 11;
	public static final int ARM = 4;
	public static final double ELEVATOR_SPEED = 0.75; 
	public static final double ELEVATOR_HOVER_SPEED = 0.06;
	public static final double ARM_POT_UPPER_LIMIT = 4.54; 
	public static final double ARM_POT_LOWER_LIMIT = 2.82;
	public static final double ARM_SPEED = 0.85; 
	public static final double MAX_CLIMB_SPEED = 0.65;
	public static final double ENGAGE_SPEED = 0.25; 
	public static final double CLIMB_HOVER_SPEED = 0.078;
	
	//Armavator Presets
	public static final double ARM_PARRELLEL_PRESET = 3.36;
	public static final double STOWED_ARM_PRESET = 2.599;
	public static final double STOWED_ELEVATOR_PRESET = 8220;
	public static final double SWITCH_ARM_PRESET = 3.46;
	public static final double SWITCH_ELEVATOR_PRESET = 0.0;
	public static final double INTAKE_ARM_PRESET = 2.90;
	public static final double INTAKE_ELEVATOR_PRESET = 0;
	public static final double NEUTRAL_SCALE_ARM_PRESET = 3.94;
	public static final double NEUTRAL_SCALE_ELEVATOR_PRESET = 15380;
	public static final double HIGH_SCALE_ARM_PRESET = 4.20;
	public static final double HIGH_SCALE_ELEVATOR_PRESET = 17820;
	
	
	public static final double ARM_DEADZONE = 0.025;
	public static final double ELEVATOR_DEADZONE = 100;
	
	//Intake 
	public static final int INTAKE_LEADER = 12;   // 12 is LEFT
	public static final int INTAKE_FOLLOWER = 13; // 13 is RIGHT
	public static final double INTAKE_SPEED = 1;
	public static final double HOLD_CUBE = 0.3;
	public static final double OUTTAKE_SPEED = 1;
	public static final int INTAKE_GRIPPER_OPEN = 1; 
	public static final int INTAKE_GRIPPER_CLOSED = 0;
	
	
	//Constant variables
	public static final double WHEEL_DIAMETER = 6;
	public static final double PULSES_PER_ROTATION = 4096;
	
	//start config
	public static final double ARM_START_CONFIG = 2.573;
	
}
