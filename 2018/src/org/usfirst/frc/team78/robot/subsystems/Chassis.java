package org.usfirst.frc.team78.robot.subsystems;


import org.usfirst.frc.team78.robot.OI;
import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;


/**
 *
 */
public class Chassis extends Subsystem {

	public TalonSRX LeftMotor_1 = new TalonSRX(RobotMap.LEFT_1);
	public TalonSRX LeftMotor_2 = new TalonSRX(RobotMap.LEFT_2);
	public TalonSRX RightMotor_1 = new TalonSRX(RobotMap.RIGHT_1);
	public TalonSRX RightMotor_2 = new TalonSRX(RobotMap.RIGHT_2);
	
//  Alex's PID constants
//	0.03, 0.002, 0.04

	
//---------------------------------------------	
	
	double wheelDiameterInFeet = RobotMap.WHEEL_DIAMETER / 12;
	double pulsesPerRot = RobotMap.PULSES_PER_ROTATION;
	
	public double pulsesToFeet = ((wheelDiameterInFeet * Math.PI) / pulsesPerRot);
	public double feetToPulses = (pulsesPerRot / (wheelDiameterInFeet * Math.PI));
	
//---------------------------------------------
	
	public void chassisInit() {
//		resetEnc();
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new DriveWithJoysticks());
    }
    
    public void driveWithJoysticks() {
    	setSpeed(-OI.DriverStick.getY(), OI.DriverStick.getThrottle());
	}
    
    public void setSpeed(double left, double right) {
    	LeftMotor_1.set(ControlMode.PercentOutput, left);
    	LeftMotor_2.set(ControlMode.PercentOutput, left);
    	RightMotor_1.set(ControlMode.PercentOutput, right);
    	RightMotor_2.set(ControlMode.PercentOutput, right);
    }
    
    public void setSideSpeed(String side, double speed) {
    	if(side == "left") {
    		LeftMotor_1.set(ControlMode.PercentOutput,speed);
    		LeftMotor_2.set(ControlMode.PercentOutput,speed);
    	}else if(side == "right") {
    		RightMotor_1.set(ControlMode.PercentOutput,speed);
    		RightMotor_2.set(ControlMode.PercentOutput,speed);
    	}    	
    }
    
//    public void turn(double angle) {
//    	turnController.setSetpoint(angle);
//    }
    
//    public void drive(double leftDistance, double rightDistance) {
//    	
//    	leftDistance *= feetToPulses;
//    	rightDistance *= feetToPulses;
//     	
//    	leftDistanceController.setSetpoint(leftDistance);
//    	rightDistanceController.setSetpoint(rightDistance);
//    	
//    }




}

