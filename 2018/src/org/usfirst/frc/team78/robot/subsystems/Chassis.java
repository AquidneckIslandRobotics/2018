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
	
	public Encoder rightEnc = new Encoder(RobotMap.RIGHT_ENC_A, RobotMap.RIGHT_ENC_B);
	public Encoder leftEnc = new Encoder(RobotMap.LEFT_ENC_A, RobotMap.LEFT_ENC_B);
	
	public AHRS navx = new AHRS(SPI.Port.kMXP);
	
	public SpeedOutput turnSpeed = new SpeedOutput();
	public PIDController turnController = new PIDController(0.025,0.00005,0.05, navx, turnSpeed);
	
	public SpeedOutput rightDistanceSpeed = new SpeedOutput();
	public SpeedOutput leftDistanceSpeed = new SpeedOutput();
	public PIDController rightDistanceController = new PIDController(0.03, 0.002, 0.04, rightEnc, rightDistanceSpeed);
	public PIDController leftDistanceController = new PIDController(0.03, 0.002, 0.04, leftEnc, leftDistanceSpeed);
	
	public Servo servo = new Servo(9);
	public AnalogInput lidar = new AnalogInput(3);
	
//---------------------------------------------	
	
	double wheelDiameterInFeet = RobotMap.WHEEL_DIAMETER / 12;
	double pulsesPerRot = RobotMap.PULSES_PER_ROTATION;
	
	public double pulsesToFeet = ((wheelDiameterInFeet * Math.PI) / pulsesPerRot);
	public double feetToPulses = (pulsesPerRot / (wheelDiameterInFeet * Math.PI));
	
	public boolean shiftIsHigh;
	
//---------------------------------------------
	
	public void chassisInit() {
		resetEnc();
		
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
    
    public void turn(double angle) {
    	turnController.setSetpoint(angle);
    }
    
//    public void drive(double leftDistance, double rightDistance) {
//    	
//    	leftDistance *= feetToPulses;
//    	rightDistance *= feetToPulses;
//     	
//    	leftDistanceController.setSetpoint(leftDistance);
//    	rightDistanceController.setSetpoint(rightDistance);
//    	
//    }

    public void shift(boolean state) {
    	if(state) {
    		shifter.set(Value.kForward);
    	}else{
    		shifter.set(Value.kReverse);
    	}
    }
    
//----------------------------------------------    
   
    public double getRightEnc() {
		return rightEnc.getRaw();
	}
    
    public double getLeftEnc() {
		return leftEnc.getRaw();
	}
   
    public void resetEnc() {
    	rightEnc.reset();
    	leftEnc.reset();
    }
    
    public double getAngle() {
    	return navx.getAngle();
    }

    public void setServo(double val) {
    	servo.set(val);
    }
    
    public double getLidar() {
    	return lidar.getVoltage();
    }
}


