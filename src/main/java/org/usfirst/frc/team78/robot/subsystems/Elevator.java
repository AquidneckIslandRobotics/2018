package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.OI;
import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.SensorInputPID;
import org.usfirst.frc.team78.robot.SpeedOutput;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//Motors
	public TalonSRX elevatorLeader = new TalonSRX(RobotMap.LEFT_ELEVATOR);
	public TalonSRX elevatorFollower = new TalonSRX(RobotMap.RIGHT_ELEVATOR);
	
	//Sensors
//	public DigitalInput bottomElevatorLimit = new DigitalInput(RobotMap.BOTTOM_ELEVATOR_LIMIT);
//	public DigitalInput upperElevatorLimit = new DigitalInput(RobotMap.UPPER_ELEVATOR_LIMIT);
	
	//PID
	public SpeedOutput eleSpeed = new SpeedOutput(); //"skraaa pop pop pop pop pop" - Nate Janssen
	public SensorInputPID eleInput = new SensorInputPID(elevatorFollower, PIDSourceType.kDisplacement, 0);
	public PIDController elePID = new PIDController(0.0009, 0.0, 0.0009, eleInput, eleSpeed);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void elevatorInit() {
    	elevatorFollower.follow(elevatorLeader);
    	elevatorFollower.setInverted(false);
    	elevatorFollower.setNeutralMode(NeutralMode.Brake);
    	elevatorLeader.setNeutralMode(NeutralMode.Brake);
    	elevatorFollower.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
    	elevatorLeader.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
    	elevatorLeader.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
    }
    
    public void manualJoystickElevatorControls() {
    	if(OI.ManipulatorStick.getY(Hand.kLeft) > RobotMap.XBOX_DEADZONE && this.getBottomElevatorLimit()) {
			this.setElevator(-OI.ManipulatorStick.getY(Hand.kLeft) * 0.9);
    	} else if(OI.ManipulatorStick.getY(Hand.kLeft) < -RobotMap.XBOX_DEADZONE && this.getUpperElevatorLimit()) {
			this.setElevator(-OI.ManipulatorStick.getY(Hand.kLeft) * 0.9);
    	} else {
    		this.stopElevator();
    	}
    }
    
    public void setElevator(double speed) {
    	if(getElevatorMagPosition() < 1800) {
    		speed *= 0.5;
    	}else if(getElevatorMagPosition() > 16800) {
    		speed *= 0.5;
    	}
    	
    	if(speed < 0 && getBottomElevatorLimit()) {
    		elevatorLeader.set(ControlMode.PercentOutput, speed);
    	} else if(speed > 0 && getUpperElevatorLimit()) {
    		elevatorLeader.set(ControlMode.PercentOutput, speed);
    	} else if(speed == 0) {
    		elevatorLeader.set(ControlMode.PercentOutput, speed);
    	} else {
    		stopElevator();
    	}
    }

    public void stopElevator() {
    	setElevator(0);
    }

    public boolean getBottomElevatorLimit() {
    	return !elevatorLeader.getSensorCollection().isRevLimitSwitchClosed();
    	//Code is based on not hitting = true, but this normally returns false for not hitting
    }
    
    public boolean getUpperElevatorLimit() {
    	return !elevatorLeader.getSensorCollection().isFwdLimitSwitchClosed();
    }
    
    public double getElevatorMagPosition() {
    	return elevatorFollower.getSelectedSensorPosition(0);
    }
    
    public void resetElevatorMag() {
    	elevatorFollower.setSelectedSensorPosition(0, 0, 0);
    }
}

