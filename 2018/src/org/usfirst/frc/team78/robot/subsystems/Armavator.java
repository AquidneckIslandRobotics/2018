package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.OI;
import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.RobotMap;
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
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 *
 */
public class Armavator extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//Motors
	public TalonSRX elevatorLeader = new TalonSRX(RobotMap.LEFT_ELEVATOR);
	public TalonSRX elevatorFollower = new TalonSRX(RobotMap.RIGHT_ELEVATOR);
	public TalonSRX arm = new TalonSRX(RobotMap.ARM);
	
	//Sensors	
	//public AnalogInput elevatorPot = new AnalogInput(RobotMap.ELEVATOR_ENCODER);
	public AnalogInput armPot = new AnalogInput(RobotMap.ARM_POT);
	public DigitalInput bottomElevatorLimit = new DigitalInput(RobotMap.BOTTOM_ELEVATOR_LIMIT);
	public DigitalInput upperElevatorLimit = new DigitalInput(RobotMap.UPPER_ELEVATOR_LIMIT);
	
//	public SpeedOutput eleSpeed = new SpeedOutput();
	//public PIDController elePID = new PIDController(0.0,0.0,0.0,elevatorFollower.getSelectedSensorPosition(0),eleSpeed);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ManualJoystickControls());
    }
    
    public void armavatorInit() {
    	elevatorFollower.follow(elevatorLeader);
    	elevatorFollower.setInverted(false);
    	elevatorFollower.setNeutralMode(NeutralMode.Brake);
    	elevatorLeader.setNeutralMode(NeutralMode.Brake);
    	elevatorFollower.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
    	arm.setNeutralMode(NeutralMode.Brake);
    	arm.setInverted(true);
    }
    
    public void manualJoystickControls() {
    	
    	if(OI.ManipulatorStick.getY(Hand.kLeft) > RobotMap.XBOX_DEADZONE && this.getBottomElevatorLimit()) {
			this.setElevator(-OI.ManipulatorStick.getY(Hand.kLeft) * 0.9);
    	} else if(OI.ManipulatorStick.getY(Hand.kLeft) < -RobotMap.XBOX_DEADZONE && this.getUpperElevatorLimit()) {
			this.setElevator(-OI.ManipulatorStick.getY(Hand.kLeft) * 0.9);
    	} else {
    		this.stopElevator();
    	}
    	
    	
    	if(OI.ManipulatorStick.getY(Hand.kRight) < RobotMap.XBOX_DEADZONE) {
    		if((Robot.armavator.getArmPot() < RobotMap.ARM_POT_UPPER_LIMIT))
    			this.setArm(-OI.ManipulatorStick.getY(Hand.kRight) * 0.9);
    		else
    			this.stopArm();
    	} else if(OI.ManipulatorStick.getY(Hand.kRight) > -RobotMap.XBOX_DEADZONE) {
    		if(Robot.armavator.getArmPot() > RobotMap.ARM_POT_LOWER_LIMIT)
    			this.setArm(-OI.ManipulatorStick.getY(Hand.kRight) * 0.9);
    		else
    			this.stopArm();
    	} else {
    		this.stopArm();
    	}
    }
    
    //ELEVATOR METHODS
    public void setElevator(double speed) {
    	if(getElevatorMagPosition() < 2000) {
    		speed *= 0.5;
    	}else if(getElevatorMagPosition() > 16000) {
    		speed *= 0.5;
    	}
    	 
    	elevatorLeader.set(ControlMode.PercentOutput, speed);
    }

    public void stopElevator() {
    	setElevator(0);
    }

    public boolean getBottomElevatorLimit() {
    	return bottomElevatorLimit.get();
    }
    
    public boolean getUpperElevatorLimit() {
    	return upperElevatorLimit.get();
    }
    
    public double getElevatorMagPosition() {
    	return elevatorFollower.getSelectedSensorPosition(0);
    }
    
    public void resetElevatorMag() {
    	elevatorFollower.setSelectedSensorPosition(0, 0, 0);
    }
    
    //ARM METHODS
    public void setArm(double speed) {    	
    	arm.set(ControlMode.PercentOutput, speed);
    }
    
    public void stopArm() {
    	setArm(0);
    }
    
    public double getArmPot() {
    	return armPot.getAverageVoltage();
    }
    
    
}