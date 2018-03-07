/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team78.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team78.robot.commands.Distance;
import org.usfirst.frc.team78.robot.commands.HowToTestAutoPath;
import org.usfirst.frc.team78.robot.commands.LowerElevatorManual;
import org.usfirst.frc.team78.robot.commands.PreMatchPresets;
import org.usfirst.frc.team78.robot.commands.SetElevatorPID;
import org.usfirst.frc.team78.robot.commands.ShuttleHigh;
import org.usfirst.frc.team78.robot.commands.Turn;
import org.usfirst.frc.team78.robot.commands.drivefrompoint;
import org.usfirst.frc.team78.robot.commands.autos.AUTO_centerLeft;
import org.usfirst.frc.team78.robot.commands.autos.AUTO_centerRight;
import org.usfirst.frc.team78.robot.commands.autos.AUTO_leftScaleLeft;
import org.usfirst.frc.team78.robot.commands.autos.AUTO_leftScaleRight;
import org.usfirst.frc.team78.robot.commands.autos.AUTO_leftSwitchLeft;
import org.usfirst.frc.team78.robot.commands.autos.AUTO_leftSwitchRight;
import org.usfirst.frc.team78.robot.commands.autos.AUTO_rightScaleRight;
import org.usfirst.frc.team78.robot.commands.autos.AUTO_rightSwitchLeft;
import org.usfirst.frc.team78.robot.commands.autos.AUTO_rightSwitchRight;
import org.usfirst.frc.team78.robot.subsystems.Arm;
import org.usfirst.frc.team78.robot.subsystems.Chassis;
import org.usfirst.frc.team78.robot.subsystems.Elevator;
import org.usfirst.frc.team78.robot.subsystems.Intake;
import org.usfirst.frc.team78.robot.subsystems.MotionProfile;
import org.json.simple.JSONArray;
import org.opencv.features2d.MSER;

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
	public static Arm arm = new Arm();
	public static Elevator elevator = new Elevator();
	public static Intake intake = new Intake();
	public static PowerDistributionPanel pdp = new PowerDistributionPanel();
	public static Compressor compressor = new Compressor();
	public static JSONArray test = new JSONArray();
	

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	SendableChooser<String> m_startPosition = new SendableChooser<>();
	SendableChooser<String> m_gameElement = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
//----for game data----------	
	char R = 'R';
	char L = 'L';
	char C = 'C';
	boolean alliance_R_SwitchState, R_scaleState, opposite_R_SwitchState;
	boolean alliance_L_SwitchState, L_scaleState, opposite_L_SwitchState;
	public static String startPosition;
	boolean goRight;
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
		arm.armInit();
		elevator.elevatorInit();
		intake.intakeInit();
		
		new Thread(() -> {
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			camera.setVideoMode(VideoMode.PixelFormat.kMJPEG, 200, 160, 10);
		}).start();
		
		SmartDashboard.putData("Start Position", m_startPosition);
		m_startPosition.addDefault("Center", "Center");
		m_startPosition.addObject("Right", "Right");
		m_startPosition.addObject("Left", "Left");
		
		SmartDashboard.putData("Game Element", m_gameElement);
		m_gameElement.addDefault("Switch", "switch");
		m_gameElement.addObject("Scale", "scale");
		
		
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
//		m_autonomousCommand = m_chooser.getSelected();
		
		if(m_startPosition.getSelected().equals("Center")) {
			if(getGameSpecificData("alliance") == R) {
				m_autonomousCommand = new AUTO_centerRight();
			}else if(getGameSpecificData("alliance") == L) {
				m_autonomousCommand = new AUTO_centerLeft();
			}else {
				m_autonomousCommand  = null;
			}
			
		}else if(m_startPosition.getSelected().equals("Right")) {
			if(m_gameElement.getSelected().equals("switch")) {
				if(getGameSpecificData("alliance") == R) {
					m_autonomousCommand = new AUTO_rightSwitchRight();
				}else if(getGameSpecificData("alliance") == L) {
					m_autonomousCommand = new AUTO_rightSwitchLeft();
				}
				
			}else if(m_gameElement.getSelected().equals("scale")) {
				if(getGameSpecificData("alliance") == R) {
					m_autonomousCommand = new AUTO_rightScaleRight();
				}else if(getGameSpecificData("alliance") == L) {
					m_autonomousCommand = new HowToTestAutoPath();
				}
			}else {
				m_autonomousCommand = null;
			}
			
		}else if(m_startPosition.getSelected().equals("Left")) {
			if(m_gameElement.getSelected().equals("switch")) {
				if(getGameSpecificData("alliance") == R) {
					m_autonomousCommand = new HowToTestAutoPath();//AUTO_leftSwitchRight();
				}else if(getGameSpecificData("alliance") == L) {
					m_autonomousCommand = new AUTO_leftSwitchLeft();
				}
				
			}else if(m_gameElement.getSelected().equals("scale")) {
				if(getGameSpecificData("alliance") == R) {
					m_autonomousCommand = new AUTO_leftScaleRight();
				}else if(getGameSpecificData("alliance") == L) {
					m_autonomousCommand = new AUTO_leftScaleLeft();
				}
			}else {
				m_autonomousCommand = null;
			}
		}
		
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		SmartDashboard.putData("auto command",m_autonomousCommand);
		
		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
		
		getSwitchColor();
//		SmartDashboard.putBoolean("Alliance_R", alliance_R_SwitchState);
//		SmartDashboard.putBoolean("Alliance_L", alliance_L_SwitchState);
//		SmartDashboard.putBoolean("Scale_R", R_scaleState);
//		SmartDashboard.putBoolean("Scale_L", L_scaleState);
//		SmartDashboard.putBoolean("oppisite_R", opposite_R_SwitchState);
//		SmartDashboard.putBoolean("opposite_L", opposite_L_SwitchState);
		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		SmartDashboard.putNumber("Arm Pot Value", Robot.arm.getArmPot());

		Scheduler.getInstance().run();
		
		if(!elevator.getBottomElevatorLimit()) {
			elevator.resetElevatorMag();
		}
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
		
		SmartDashboard.putData("Stow Robot", new PreMatchPresets());
		
		getSwitchColor();
		chassis.chassisInit();
		intake.intakeInit();
		arm.armInit();
		elevator.elevatorInit();
		SmartDashboard.putNumber("Arm Pot Value", Robot.arm.getArmPot());

//		SmartDashboard.putData("high", new ShuttleHigh());
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		
		
		
//		SmartDashboard.putBoolean("Alliance_R", alliance_R_SwitchState);
//		SmartDashboard.putBoolean("Alliance_L", alliance_L_SwitchState);
//		SmartDashboard.putBoolean("Scale_R", R_scaleState);
//		SmartDashboard.putBoolean("Scale_L", L_scaleState);
//		SmartDashboard.putBoolean("oppisite_R", opposite_R_SwitchState);
//		SmartDashboard.putBoolean("opposite_L", opposite_L_SwitchState);
//		
//		SmartDashboard.putNumber("right Vel", chassis.rightMagVelocity());
//		SmartDashboard.putNumber("left Vel", chassis.leftMagVelocity());
//		SmartDashboard.putNumber("right Pos", chassis.rightMagPosition());
//		SmartDashboard.putNumber("left Pos", chassis.leftMagPosition());
		SmartDashboard.putData("gyro", chassis.navx);
		SmartDashboard.putData("Chassis",chassis);
		SmartDashboard.putBoolean("shift is high", chassis.shiftIsHigh);
		SmartDashboard.putNumber("Arm Pot Value", Robot.arm.getArmPot());
		SmartDashboard.putNumber("Elevator Encoder", elevator.getElevatorMagPosition());
		
//		SmartDashboard.putNumber("TurnPID", chassis.turnSpeed.getSpeed());
//		SmartDashboard.putData("TurnController", chassis.turnController);
//		SmartDashboard.putData("Turn",new Turn());		
//		SmartDashboard.putNumber("leftDistPID", chassis.leftDistanceSpeed.getSpeed());
//		SmartDashboard.putNumber("rightDistPID", chassis.rightDistanceSpeed.getSpeed());
//		SmartDashboard.putData("right drive controller", chassis.rightDistanceController);
//		SmartDashboard.putData("left drive controller", chassis.leftDistanceController);			
//		SmartDashboard.putData("test",new drivefrompoint());
		SmartDashboard.putData("Arm PID", arm.armPID);
		SmartDashboard.putData("Elevator PID", elevator.elePID);
		SmartDashboard.putData("Elevator Subsystem", elevator);
		SmartDashboard.putData("Hold Climb Command", new LowerElevatorManual(0.1));
		
		SmartDashboard.putNumber("intake lead current", Robot.intake.intakeLeader.getOutputCurrent());
		SmartDashboard.putNumber("intake follow current", Robot.intake.intakeFollower.getOutputCurrent());
		
		SmartDashboard.putNumber("arm current", arm.arm.getOutputCurrent());
		//SmartDashboard.putBoolean("Upper Elevator Limit Switch", elevato);
		
		Scheduler.getInstance().run();
		
		
		if(!elevator.getBottomElevatorLimit()) {
			elevator.resetElevatorMag();
		}
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	
	}
}
