package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SetArmPID extends Command {
	
	private double targetValue, outputSpeed;
	private boolean holdPosition = false;
	private double maxSpeed = 1;
	
    public SetArmPID(double target) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.arm);
    	targetValue = target;
    }
    
    public SetArmPID(double target, boolean hold) {
    	requires(Robot.arm);
    	targetValue = target;
    	holdPosition = hold;
    }
    
    public SetArmPID(double target, boolean hold, double speedMax) {
    	requires(Robot.arm);
    	targetValue = target;
    	holdPosition = hold;
    	maxSpeed = speedMax;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.arm.armPID.setContinuous(false);
    	Robot.arm.armPID.setInputRange(0, 12);
    	Robot.arm.armPID.setOutputRange(-maxSpeed, maxSpeed);
    	Robot.arm.armPID.setAbsoluteTolerance(0.01);
    	Robot.arm.armPID.setSetpoint(targetValue);
    	Robot.arm.armPID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	outputSpeed = Robot.arm.armSpeedOutput.getSpeed();
    	Robot.arm.setArm(outputSpeed);
    	SmartDashboard.putNumber("Arm PID Output Speed", outputSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(holdPosition) return false;
        else return Robot.arm.armPID.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	new StopArm();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
