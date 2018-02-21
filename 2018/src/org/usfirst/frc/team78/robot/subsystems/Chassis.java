package org.usfirst.frc.team78.robot.subsystems;

import java.io.PipedOutputStream;

import javax.swing.text.AbstractDocument.LeafElement;

import org.usfirst.frc.team78.robot.OI;
import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.SpeedOutput;
import org.usfirst.frc.team78.robot.commands.DriveWithJoysticks;
import org.usfirst.frc.team78.robot.commands.Turn;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.kauailabs.navx.frc.AHRS;

/**
 *
 */
public class Chassis extends Subsystem {

	public TalonSRX leftFront = new TalonSRX(RobotMap.LEFT_FRONT);
	public TalonSRX leftTop = new TalonSRX(RobotMap.LEFT_TOP);
	public TalonSRX leftBottom = new TalonSRX(RobotMap.LEFT_BOTTOM);	
;	public TalonSRX rightFront = new TalonSRX(RobotMap.RIGHT_FRONT);
	public TalonSRX rightTop = new TalonSRX(RobotMap.RIGHT_TOP);
	public TalonSRX rightBottom = new TalonSRX(RobotMap.RIGHT_BOTTOM);
	
	public DoubleSolenoid shifter = new DoubleSolenoid(RobotMap.SHIFT_HIGH, RobotMap.SHIFT_LOW);
	
	public AHRS navx = new AHRS(SPI.Port.kMXP);
	
	public SpeedOutput turnSpeed = new SpeedOutput();
	public PIDController turnController = new PIDController(0.015,0.0,0.03, navx, turnSpeed);
	
//	public SpeedOutput rightDistanceSpeed = new SpeedOutput();
//	public SpeedOutput leftDistanceSpeed = new SpeedOutput();
//	public PIDController rightDistanceController = new PIDController(0.03, 0.002, 0.04, rightEnc, rightDistanceSpeed);
//	public PIDController leftDistanceController = new PIDController(0.03, 0.002, 0.04, leftEnc, leftDistanceSpeed);

	
//---------------------------------------------	
	
	double wheelDiameterInFeet = RobotMap.WHEEL_DIAMETER / 12;
	double pulsesPerRot = RobotMap.PULSES_PER_ROTATION;
	
	public double pulsesToFeet = ((wheelDiameterInFeet * Math.PI) / pulsesPerRot);
	public double feetToPulses = (pulsesPerRot / (wheelDiameterInFeet * Math.PI));
	
	double leftSpeed, rightSpeed;
	
	public boolean shiftIsHigh;
	public boolean switchFront = true;
	
	int timeoutMs = 5000;
	
	double pdpVoltage;
//---------------------------------------------
	
	public void chassisInit() {
		resetEnc();
		resetGyro();
		
		pdpVoltage = Robot.pdp.getVoltage();
		
		leftTop.follow(leftFront);
		leftBottom.follow(leftFront);
		
		rightTop.follow(rightFront);
		rightBottom.follow(rightFront);
		
		leftFront.setInverted(true);
		leftTop.setInverted(true);
		leftBottom.setInverted(true);
		rightFront.setInverted(true);
		rightTop.setInverted(true);
		rightBottom.setInverted(true);
		
		double secondsFromNeutralToFull = 0.25;
		leftFront.configOpenloopRamp( secondsFromNeutralToFull, timeoutMs);
		leftTop.configOpenloopRamp(secondsFromNeutralToFull, timeoutMs);
		leftBottom.configOpenloopRamp(secondsFromNeutralToFull, timeoutMs);
		rightFront.configOpenloopRamp(secondsFromNeutralToFull, timeoutMs);
		rightTop.configOpenloopRamp(secondsFromNeutralToFull, timeoutMs);
		rightBottom.configOpenloopRamp(secondsFromNeutralToFull, timeoutMs);
		
		leftFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		rightFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		rightFront.setSensorPhase(true);
		
		rightFront.setIntegralAccumulator(20, 0, 10);
		rightFront.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 10);
		rightFront.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 10);
		rightFront.configNominalOutputForward(0, 10);
		rightFront.configNominalOutputReverse(0, 10);
		rightFront.configPeakOutputForward(1, 10);
		rightFront.configPeakOutputReverse(-1, 10);
		rightFront.selectProfileSlot(1, 1);
		rightFront.config_kF(0, 0.20, 10); //.21 - .19
		rightFront.config_kP(0, 0.505, 10); //.57 - .455
		rightFront.config_kI(0, 0.0, 10);//.005 - .001
		rightFront.config_kD(0, 0, 10);   //.42 - 20
		rightFront.configMotionCruiseVelocity(3653, 10);
		rightFront.configMotionAcceleration(3653, 10);
		rightFront.setSelectedSensorPosition(0, 0, 10);
		
		leftFront.setIntegralAccumulator(19, 0, 10);
		leftFront.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 10);
		leftFront.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 10);
		leftFront.configNominalOutputForward(0, 10);
		leftFront.configNominalOutputReverse(0, 10);
		leftFront.configPeakOutputForward(1, 10);
		leftFront.configPeakOutputReverse(-1, 10);
		leftFront.selectProfileSlot(0, 0);
		leftFront.config_kF(0, 0.20, 10); //.22 - .2199
		leftFront.config_kP(0, 0.505, 10);  //.6 - .515
		leftFront.config_kI(0, 0.0, 10); //.02 - .003
		leftFront.config_kD(0, 0, 10);   //40 - 19
		leftFront.configMotionCruiseVelocity(3653, 10);
		leftFront.configMotionAcceleration(3653, 10);
		leftFront.setSelectedSensorPosition(0, 0, 10);
		
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new DriveWithJoysticks());
    }
    
    public void driveWithJoysticks() {
    	if(Math.abs(OI.DriverStick.getY()) < RobotMap.STICK_DEADZONE) leftSpeed = 0;
    	else leftSpeed = -OI.DriverStick.getY();
    	if(Math.abs(OI.DriverStick.getThrottle()) < RobotMap.STICK_DEADZONE) rightSpeed = 0;
    	else rightSpeed = OI.DriverStick.getThrottle();
    	setSpeed(leftSpeed, rightSpeed);
	}
    
    public void reverseDriveWithJoysticks() {
    	if(Math.abs(OI.DriverStick.getY()) < RobotMap.STICK_DEADZONE) leftSpeed = 0;
    	else leftSpeed = -OI.DriverStick.getY();
    	if(Math.abs(OI.DriverStick.getThrottle()) < RobotMap.STICK_DEADZONE) rightSpeed = 0;
    	else rightSpeed = OI.DriverStick.getThrottle();
    	setSpeed(rightSpeed, leftSpeed);
	}
    
    public void setSpeed(double left, double right) {
    	leftFront.set(ControlMode.PercentOutput, left);
    	rightFront.set(ControlMode.PercentOutput, right);
    }
    
    public void setSideSpeed(String side, double speed) {
    	if(side == "left") {
    		leftFront.set(ControlMode.PercentOutput, speed);
    	}else if(side == "right") {
    		rightFront.set(ControlMode.PercentOutput, speed);
    	}    	
    }
    
//    public void turn(double angle) {
//    	turnController.setSetpoint(angle); 
//    }
    

    public void shift(boolean state) {
    	if(state) {
    		shifter.set(Value.kForward);
    	}else{
    		shifter.set(Value.kReverse);
    	}
    }
    
//----------------------------------------------    
   
    public double rightMagVelocity() {
    	double vel = pulsesToFeet * rightFront.getSelectedSensorVelocity(0);
    	return vel;
    }
    
    public double leftMagVelocity() {
    	double vel = pulsesToFeet * leftFront.getSelectedSensorVelocity(0);
    	return vel;
    }
    
    public double rightMagPosition() {
    	double pos = pulsesToFeet * rightFront.getSelectedSensorPosition(0);
    	return pos;
    }
    
    public double leftMagPosition() {
    	double pos = pulsesToFeet * leftFront.getSelectedSensorPosition(0);
    	return pos;
    }
    
    public void resetEnc() {
    	rightFront.setSelectedSensorPosition(0, 0, 0);
    	leftFront.setSelectedSensorPosition(0, 0, 0);
    }
    
    public double getAngle() {
    	return navx.getAngle();
    }
    
    public void resetGyro() {
    	navx.reset();
    }
}


