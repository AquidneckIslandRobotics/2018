/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team78.robot;

import org.usfirst.frc.team78.robot.commands.Shift;
import org.usfirst.frc.team78.robot.commands.StopArm;
import org.usfirst.frc.team78.robot.commands.StopElevator;
import org.usfirst.frc.team78.robot.commands.StopIntake;
import org.usfirst.frc.team78.robot.commands.SwitchFront;
import org.usfirst.frc.team78.robot.commands.Turn;
import org.usfirst.frc.team78.robot.commands.autos.AUTO_rightSwitchLeft;
import org.usfirst.frc.team78.robot.commands.autos.AUTO_rightSwitchRight;
import org.usfirst.frc.team78.robot.commands.CloseIntakeGrabber;
import org.usfirst.frc.team78.robot.commands.HowToTestAutoPath;
import org.usfirst.frc.team78.robot.commands.IntakeCube;
import org.usfirst.frc.team78.robot.commands.LowerArmManual;
import org.usfirst.frc.team78.robot.commands.LowerElevatorManual;
import org.usfirst.frc.team78.robot.commands.ManualJoystickControls;
import org.usfirst.frc.team78.robot.commands.OpenIntakeGrabber;
import org.usfirst.frc.team78.robot.commands.OuttakeCube;
import org.usfirst.frc.team78.robot.commands.RaiseArmManual;
import org.usfirst.frc.team78.robot.commands.RaiseArmToPreset;
import org.usfirst.frc.team78.robot.commands.RaiseElevatorManual;
import org.usfirst.frc.team78.robot.commands.SetArmavatorPID;
import org.usfirst.frc.team78.robot.commands.SetArmavatorPreset;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public static Joystick DriverStick = new Joystick(0);
	public static XboxController ManipulatorStick = new XboxController(1);
	
	public Button driverA;
	public Button driverB;
	public Button driverX;
	public Button driverY;
	public Button driverRB;
	public Button driverRT;
	public Button driverLB;
	public Button driverLT;
	public Button driverDpadUp;
	public Button driverDpadDown;
	public Button driverDpadRight;
	public Button driverDpadLeft;
	public Button driverStart;
	
	public Button manipulatorA;
	public Button manipulatorB;
	public Button manipulatorX;
	public Button manipulatorY;
	public Button manipulatorLB;
	public Button manipulatorRB;
	public XboxTriggerButton manipulatorLT;
	public XboxTriggerButton manipulatorRT;
	public Button manipulatorBack;
	
	public OI() {
		//DRIVER BUTTONS
		driverA = new JoystickButton(DriverStick, 2);
		driverB = new JoystickButton(DriverStick, 3);
		driverX = new JoystickButton(DriverStick, 1);
		driverY = new JoystickButton(DriverStick, 4);
		driverRB = new JoystickButton(DriverStick, 6); 
		driverRT = new JoystickButton(DriverStick, 8);
		driverLB = new JoystickButton(DriverStick, 5);
		driverLT = new JoystickButton(DriverStick, 7);
		driverDpadUp = new JoystickButton(DriverStick, 1);
		driverDpadDown= new JoystickButton(DriverStick, 1);
		driverDpadRight= new JoystickButton(DriverStick, 1);
		driverDpadLeft= new JoystickButton(DriverStick, 1);
		driverStart = new JoystickButton(DriverStick, 10);
		
		driverRB.whenPressed(new Shift());
		driverRB.whenReleased(new Shift());
		driverLT.whenPressed(new OpenIntakeGrabber());
		driverLT.whenReleased(new CloseIntakeGrabber());
		driverLB.whileHeld(new OuttakeCube(RobotMap.OUTTAKE_SPEED));
		driverLB.whenReleased(new StopIntake());
		driverStart.whenReleased(new SwitchFront());
		
//		driverA.whenPressed(new HowToTestAutoPath());
//		driverX.whenPressed(new rightSwitchRight());
//		driverB.whenPressed(new rightSwitchLeft());
		
		//MANIPULATOR BUTTONS
		manipulatorA = new JoystickButton(ManipulatorStick, 1);
		manipulatorX = new JoystickButton(ManipulatorStick, 3);
		manipulatorB = new JoystickButton(ManipulatorStick, 2);
		manipulatorY = new JoystickButton(ManipulatorStick, 4);
		manipulatorLB = new JoystickButton(ManipulatorStick, 5);
		manipulatorRB = new JoystickButton(ManipulatorStick, 6);
		manipulatorLT = new XboxTriggerButton(ManipulatorStick, 2);
		manipulatorRT = new XboxTriggerButton(ManipulatorStick, 3);
		manipulatorBack = new JoystickButton(ManipulatorStick, 7);
		
//		manipulatorY.whileHeld(new LowerElevatorManual(RobotMap.ELEVATOR_HOVER_SPEED));
//		manipulatorY.whenReleased(new StopElevator());
//		manipulatorA.whileHeld(new IntakeCube(RobotMap.INTAKE_SPEED));
//		manipulatorA.whenReleased(new StopIntake());
//		manipulatorB.whileHeld(new IntakeCube(RobotMap.HOLD_CUBE));
//		manipulatorB.whenReleased(new StopIntake());
		manipulatorX.whileHeld(new OuttakeCube(RobotMap.OUTTAKE_SPEED));
		manipulatorX.whenReleased(new StopIntake());
		manipulatorA.whileHeld(new IntakeCube(RobotMap.INTAKE_SPEED));
		manipulatorA.whenReleased(new StopIntake());
		manipulatorB.whileHeld(new IntakeCube(RobotMap.HOLD_CUBE));
		manipulatorB.whenReleased(new StopIntake());
		manipulatorY.whileHeld(new SetArmavatorPID(RobotMap.ARM_PARRELLEL_PRESET, RobotMap.SWITCH_ELEVATOR_PRESET));
		manipulatorY.whenReleased(new ManualJoystickControls());
		//manipulatorY.whenReleased(new ManualJoystickControls());
//		manipulatorLB.whileHeld(new RaiseElevatorManual(RobotMap.ELEVATOR_SPEED));// COMMENTED FOR MANUAL CONTROL ON MANIPULATOR JOYSTICKS
//		manipulatorLB.whenReleased(new StopElevator());
//		manipulatorLT.whileHeld(new LowerElevatorManual(RobotMap.ELEVATOR_SPEED));
		manipulatorLT.whileHeld(new SetArmavatorPreset(RobotMap.ARM_SCALE_PRESET, RobotMap.ELEVATOR_SCALE_PRESET));
		manipulatorLT.whenReleased(new StopElevator());
//		manipulatorRB.whileHeld(new RaiseArmManual(RobotMap.ARM_SPEED));
//		manipulatorRB.whenReleased(new StopArm());
//		manipulatorRT.whileHeld(new LowerArmManual(RobotMap.ARM_SPEED));
//		manipulatorRT.whenReleased(new StopArm());
	}
	
}
