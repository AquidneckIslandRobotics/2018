package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Armavator extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//Motors
	public TalonSRX leftElevator = new TalonSRX(RobotMap.LEFT_ELEVATOR);
	public TalonSRX rightElevator = new TalonSRX(RobotMap.RIGHT_ELEVATOR);
	public TalonSRX arm = new TalonSRX(RobotMap.ARM);
	
	//Sensors	
	public AnalogInput elevatorPot = new AnalogInput(RobotMap.ELEVATOR_POT);
	public AnalogInput armPot = new AnalogInput(RobotMap.ARM_POT);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setElevator(double speed) {
    	leftElevator.set(ControlMode.PercentOutput, speed);
    	//rightElevator.set(ControlMode.PercentOutput, -speed); CHECK INVIDIDUALLY BEFORE RUNNING
    }
    
    public void setArm(double speed) {
    	arm.set(ControlMode.PercentOutput, speed);
    }
    
}

