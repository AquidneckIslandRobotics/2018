package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//Motors
	public TalonSRX intakeLeader = new TalonSRX(RobotMap.INTAKE_LEADER);
	public TalonSRX intakeFollower = new TalonSRX(RobotMap.INTAKE_FOLLOWER);
	
	public DoubleSolenoid grabber = new DoubleSolenoid(RobotMap.INTAKE_GRIPPER_OPEN, RobotMap.INTAKE_GRIPPER_CLOSED);
	
	//Sensors
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void intakeInit() {
    	closeGrabber();
    	intakeFollower.follow(intakeLeader);
    	intakeLeader.setNeutralMode(NeutralMode.Brake);
    	intakeLeader.setInverted(false);
    	intakeFollower.setNeutralMode(NeutralMode.Brake);
    	intakeFollower.setInverted(false);
    }
    
    public void closeGrabber() {
    	grabber.set(Value.kReverse); //TODO Check intake gripper pneumatic positions
    }
    
    public void openGrabber() {
    	grabber.set(Value.kForward);
    }
    
    public void setIntake(double speed) {
    	intakeLeader.set(ControlMode.PercentOutput, speed); //CHECK DIRECTION ON ACTUAL ROBOT
    }
    
    public void stopIntake() {
    	setIntake(0);
    }
    
}

