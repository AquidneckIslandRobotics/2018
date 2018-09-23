package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.OI;
import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.SensorInputPID;
import org.usfirst.frc.team78.robot.SpeedOutput;
import org.usfirst.frc.team78.robot.commands.LowerArmManual;
import org.usfirst.frc.team78.robot.commands.LowerElevatorManual;
import org.usfirst.frc.team78.robot.commands.ManualJoystickControls;
import org.usfirst.frc.team78.robot.commands.RaiseArmManual;
import org.usfirst.frc.team78.robot.commands.RaiseElevatorManual;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 *
 */
public class Arm extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public boolean override = false;
	
	//Motors
	public TalonSRX arm = new TalonSRX(RobotMap.ARM);
	
	//Sensors	
	//public AnalogInput elevatorPot = new AnalogInput(RobotMap.ELEVATOR_ENCODER);
	public AnalogInput armPot = new AnalogInput(RobotMap.ARM_POT);

	
	//PID
	public SpeedOutput armSpeedOutput = new SpeedOutput();
	public PIDController armPID = new PIDController(7, 0.0, 0.5, armPot, armSpeedOutput);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ManualJoystickControls());
    }
    
    public void armInit() {
    	arm.setNeutralMode(NeutralMode.Brake);
    	arm.setInverted(true);
    	
    	
//    	arm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0); 
    	
    }
    
    
    public void manualJoystickArmControls() {    	
    	if(OI.ManipulatorStick.getY(Hand.kRight) < -RobotMap.XBOX_DEADZONE) {
    		if((Robot.arm.getArmPot() < RobotMap.ARM_POT_UPPER_LIMIT) && !override)
    			this.setArm(-OI.ManipulatorStick.getY(Hand.kRight) * 0.9);
    		else
    			this.stopArm();
    	} else if(OI.ManipulatorStick.getY(Hand.kRight) > RobotMap.XBOX_DEADZONE) {
    		if((Robot.arm.getArmPot() > RobotMap.ARM_POT_LOWER_LIMIT) && !override)
    			this.setArm(-OI.ManipulatorStick.getY(Hand.kRight) * 0.9);
    		else
    			this.stopArm();
//    	}else if(override) {
//    		if(Math.abs(arm.getOutputCurrent()) > Math.abs(20))
//    		this.setArm(-OI.ManipulatorStick.getY(Hand.kRight) * 0.8);
    		
    	}else {
    		this.stopArm();
    	}
    }
    
    public void setArm(double speed) {
    	if(speed < 0 && getArmPot() > RobotMap.ARM_POT_LOWER_LIMIT) {
    		arm.set(ControlMode.PercentOutput, speed);
    	} else if(speed > 0 && getArmPot() < RobotMap.ARM_POT_UPPER_LIMIT) {
    		arm.set(ControlMode.PercentOutput, speed);
    	} else if(speed == 0) {
    		arm.set(ControlMode.PercentOutput, speed);
    	}
    }
    
    public void stopArm() {
    	setArm(0);
    }
    
    public double getArmPot() {
    	return armPot.getAverageVoltage();
    }
    
    
}