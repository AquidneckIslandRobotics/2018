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
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.kauailabs.navx.frc.AHRS;

/**
 *
 */
public class Chassis extends Subsystem {

	public Victor Left1Motor = new Victor(RobotMap.LEFT_1);
	public Victor Left2Motor = new Victor(RobotMap.LEFT_2);
	public Victor Right1Motor = new Victor(RobotMap.RIGHT_1);
	public Victor Right2Motor = new Victor(RobotMap.RIGHT_2);
	
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
	
	double pulsesToFeet = ((wheelDiameterInFeet * Math.PI) / pulsesPerRot);
	double feetToPulses = (pulsesPerRot / (wheelDiameterInFeet * Math.PI));
	
//---------------------------------------------
	
	public void chassisInit() {
		resetEnc();
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new DriveWithJoysticks());
    }
    
    public void driveWithJoysticks() {
    	setSpeed(-OI.DriverStick.getY(), OI.DriverStick.getThrottle());
	}
    
    public void setSpeed(double left, double right) {
    	Left1Motor.set(left);
    	Left2Motor.set(left);
    	Right1Motor.set(right);
    	Right2Motor.set(right);
    }
    
    public void setSideSpeed(String side, double speed) {
    	if(side == "left") {
    		Left1Motor.set(speed);
    		Left2Motor.set(speed);
    	}else if(side == "right") {
    		Right1Motor.set(speed);
    		Right2Motor.set(speed);
    	}    	
    }
    
    public void turn(double angle) {
    	turnController.setSetpoint(angle);
    }
    
    public void drive(double leftDistance, double rightDistance) {
    	
    	leftDistance *= feetToPulses;
    	rightDistance *= feetToPulses;
     	
    	leftDistanceController.setSetpoint(leftDistance);
    	rightDistanceController.setSetpoint(rightDistance);
    	
    	leftDistanceController.enable();
    	rightDistanceController.enable();
    	leftDistanceController.setContinuous(false);
    	leftDistanceController.setOutputRange(-0.5, 0.5);
    	rightDistanceController.setContinuous(false);
    	rightDistanceController.setOutputRange(-0.5, 0.5);
    	

    	double rSpeed = Robot.chassis.rightDistanceSpeed.getSpeed();
    	double lSpeed = Robot.chassis.leftDistanceSpeed.getSpeed();
    	Robot.chassis.setSpeed(lSpeed, -rSpeed);
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


