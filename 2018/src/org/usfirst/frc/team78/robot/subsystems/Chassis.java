package org.usfirst.frc.team78.robot.subsystems;

import javax.swing.text.AbstractDocument.LeafElement;

import org.usfirst.frc.team78.robot.OI;
import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Victor;
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
	
//---------------------------------------------
	
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

//----------------------------------------------    
    
    public Encoder getRightEnc() {
		return rightEnc;
	}
    
    public Encoder getLeftEnc() {
		return leftEnc;
	}
    
    public AHRS getYaw() {
		return getYaw();
	}
    public AHRS getAngle() {
    	return getAngle();
    }
    public AHRS getRoll() {
    	return getRoll();
    }
    
    
    
}

