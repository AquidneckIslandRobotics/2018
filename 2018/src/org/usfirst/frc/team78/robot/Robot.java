/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team78.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team78.robot.commands.Turn;
import org.usfirst.frc.team78.robot.subsystems.Chassis;
import org.usfirst.frc.team78.robot.subsystems.MotionProfile;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	
	public static OI m_oi;
	public static Chassis chassis = new Chassis();
	public static MotionProfile motionProfile = new MotionProfile();

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
//----for game data----------	
	char R = 'R';
	char L = 'L';
	boolean alliance_R_SwitchState, R_scaleState, opposite_R_SwitchState;
	boolean alliance_L_SwitchState, L_scaleState, opposite_L_SwitchState;
//---------------------------	
	double servo = 0.5;
	
	
	
	public char getGameSpecificData(String s) {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		if(s == "alliance") {
			return gameData.charAt(0);
		}else if(s == "scale") {
			return gameData.charAt(1);
		}else if(s == "opposing") {
			return gameData.charAt(2);
		}else {
			return gameData.charAt(0);
		}
		
	}
	public Alliance getAlliance() {
		return DriverStation.getInstance().getAlliance();
	}
	
	public void getSwitchColor() {
		if(getAlliance() == Alliance.Red) {
			if(getGameSpecificData("alliance") == R) {
				alliance_R_SwitchState = false;
				alliance_L_SwitchState = true;
			}else if(getGameSpecificData("alliance") == L) {
				alliance_R_SwitchState = true;
				alliance_L_SwitchState = false;
			}
			if(getGameSpecificData("scale") == R) {
				R_scaleState = false;
				L_scaleState = true;
			}else if(getGameSpecificData("scale") == L) {
				R_scaleState = true;
				L_scaleState = false;
			}
			if(getGameSpecificData("opposing") == R) {
				opposite_R_SwitchState = false;
				opposite_L_SwitchState = true;
			}else if(getGameSpecificData("opposing") == L) {
				opposite_R_SwitchState = true;
				opposite_L_SwitchState = false;
			}  
		}else if(getAlliance() == Alliance.Blue) {
			if(getGameSpecificData("alliance") == R) {
				alliance_R_SwitchState = true;
				alliance_L_SwitchState = false;
			}else if(getGameSpecificData("alliance") == L) {
				alliance_R_SwitchState = false;
				alliance_L_SwitchState = true;
			}
			if(getGameSpecificData("scale") == R) {
				R_scaleState = true;
				L_scaleState = false;
			}else if(getGameSpecificData("scale") == L) {
				R_scaleState = false;
				L_scaleState = true;
			}
			if(getGameSpecificData("opposing") == R) {
				opposite_R_SwitchState = true;
				opposite_L_SwitchState = false;
			}else if(getGameSpecificData("opposing") == L) {
				opposite_R_SwitchState = true;
				opposite_L_SwitchState = false;
			}  
		}	
		
	}
	
	
	@Override
	public void robotInit() {
		m_oi = new OI();
		//m_chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		
		
		SmartDashboard.putData("Auto mode", m_chooser);
		chassis.chassisInit();
	}
	
	
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		

	}

	@Override
	public void disabledPeriodic() {	
		
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		m_autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
		
		getSwitchColor();
		SmartDashboard.putBoolean("Alliance_R", alliance_R_SwitchState);
		SmartDashboard.putBoolean("Alliance_L", alliance_L_SwitchState);
		SmartDashboard.putBoolean("Scale_R", R_scaleState);
		SmartDashboard.putBoolean("Scale_L", L_scaleState);
		SmartDashboard.putBoolean("oppisite_R", opposite_R_SwitchState);
		SmartDashboard.putBoolean("opposite_L", opposite_L_SwitchState);
		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		
		getSwitchColor();
		
		SmartDashboard.putNumber("servo val", servo);
	
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		
		
		
		SmartDashboard.putBoolean("Alliance_R", alliance_R_SwitchState);
		SmartDashboard.putBoolean("Alliance_L", alliance_L_SwitchState);
		SmartDashboard.putBoolean("Scale_R", R_scaleState);
		SmartDashboard.putBoolean("Scale_L", L_scaleState);
		SmartDashboard.putBoolean("oppisite_R", opposite_R_SwitchState);
		SmartDashboard.putBoolean("opposite_L", opposite_L_SwitchState);
		
		SmartDashboard.putData("right enc", chassis.rightEnc);
		SmartDashboard.putData("left enc", chassis.leftEnc);
		SmartDashboard.putData("gyro", chassis.navx);
		
		SmartDashboard.putNumber("TurnPID", chassis.turnSpeed.getSpeed());
		
		SmartDashboard.putData("TurnController", chassis.turnController);
		SmartDashboard.putData("Turn",new Turn());
		SmartDashboard.putData("Chassis",chassis);
		
		
		SmartDashboard.putNumber("lidar", chassis.getLidar());
		

		
		
		chassis.setServo(SmartDashboard.getNumber("servo val", servo));
		
		Scheduler.getInstance().run();
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	
	}
}
