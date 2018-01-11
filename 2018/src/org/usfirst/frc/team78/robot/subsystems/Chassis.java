package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.OI;
import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Chassis extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public Victor Left1Motor = new Victor(RobotMap.Left1);
	public Victor Left2Motor = new Victor(RobotMap.Left2);
	public Victor Right1Motor = new Victor(RobotMap.Right1);
	public Victor Right2Motor = new Victor(RobotMap.Right2);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithJoysticks());
    }
    
    public void DriveWithJoysticks() {
    	
    	setSpeed(-OI.DriverStick.getY(), OI.DriverStick.getThrottle());
    	
    }
    
    public void setSpeed(double left, double right) {
    	Left1Motor.set(left);
    	Left2Motor.set(left);
    	Right1Motor.set(right);
    	Right2Motor.set(right);
    }
    
    
}

