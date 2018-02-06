package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//Motors
	public TalonSRX leftIntake = new TalonSRX(RobotMap.LEFT_INTAKE);
	public TalonSRX rightIntake = new TalonSRX(RobotMap.RIGHT_INTAKE);
	
	//Sensors
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setIntake(double speed) {
    	leftIntake.set(ControlMode.PercentOutput, speed);
    	rightIntake.set(ControlMode.PercentOutput, -speed); //CHECK DIRECTION ON ACTUAL ROBOT
    }
    
}

