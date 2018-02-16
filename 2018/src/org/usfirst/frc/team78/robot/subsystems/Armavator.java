package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.OI;
import org.usfirst.frc.team78.robot.RobotMap;
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
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new ManualJoystickControls());
    }
    
    public void armavatorInit() {
    	elevatorFollower.follow(elevatorLeader);
    	elevatorFollower.setInverted(false);
    	elevatorFollower.setNeutralMode(NeutralMode.Brake);
    	elevatorLeader.setNeutralMode(NeutralMode.Brake);
    	elevatorFollower.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
    	arm.setNeutralMode(NeutralMode.Brake);
    	arm.setInverted(true);
    }
    
    public void manualJoystickControls() {
    	if(OI.ManipulatorStick.getRawAxis(1) > RobotMap.STICK_DEADZONE) {
    		new RaiseElevatorManual(OI.ManipulatorStick.getRawAxis(1));
    	} else if(OI.ManipulatorStick.getY() < -RobotMap.STICK_DEADZONE) {
    		new LowerElevatorManual(OI.ManipulatorStick.getY());
    	}
    	
    	if(OI.ManipulatorStick.getThrottle() > RobotMap.STICK_DEADZONE) {
    		new RaiseElevatorManual(OI.ManipulatorStick.getThrottle());
    	} else if(OI.ManipulatorStick.getThrottle() < -RobotMap.STICK_DEADZONE) {
    		new LowerElevatorManual(OI.ManipulatorStick.getThrottle());
    	}    	
    }
    
    //ELEVATOR METHODS
    public void setElevator(double speed) {
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