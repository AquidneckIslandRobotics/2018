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
	
//	public SpeedOutput turnSpeed = new SpeedOutput();
//	public PIDController turnController = new PIDController(0.025,0.00005,0.05, navx, turnSpeed);
//	
//	public SpeedOutput rightDistanceSpeed = new SpeedOutput();
//	public SpeedOutput leftDistanceSpeed = new SpeedOutput();
//	public PIDController rightDistanceController = new PIDController(0.03, 0.002, 0.04, rightEnc, rightDistanceSpeed);
//	public PIDController leftDistanceController = new PIDController(0.03, 0.002, 0.04, leftEnc, leftDistanceSpeed);

	
//---------------------------------------------	
	
	double wheelDiameterInFeet = RobotMap.WHEEL_DIAMETER / 12;
	double pulsesPerRot = RobotMap.PULSES_PER_ROTATION;
	
	public double pulsesToFeet = ((wheelDiameterInFeet * Math.PI) / pulsesPerRot);
	public double feetToPulses = (pulsesPerRot / (wheelDiameterInFeet * Math.PI));
	
	public boolean shiftIsHigh;
	
	int timeoutMs = 5000;
	
	double pdpVoltage;
//---------------------------------------------
	
	public void chassisInit() {
		resetEnc();
		
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
		
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new DriveWithJoysticks());
    }
    
    public void driveWithJoysticks() {
    	setSpeed(-OI.DriverStick.getY(), OI.DriverStick.getThrottle());
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
}


