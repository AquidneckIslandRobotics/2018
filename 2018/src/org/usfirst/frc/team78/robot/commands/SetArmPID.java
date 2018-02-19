package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetArmPID extends Command {
	
	private double targetValue, outputSpeed;
	private boolean holdPosition = false;
	
    public SetArmPID(double target) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	targetValue = target;
    }
    
    public SetArmPID(double target, boolean hold) {
    	targetValue = target;
    	holdPosition = hold;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.armavator.armPID.setContinuous(false);
    	Robot.armavator.armPID.setInputRange(0, 12);
    	Robot.armavator.armPID.setOutputRange(-1, 1);
    	Robot.armavator.armPID.setAbsoluteTolerance(0.05);
    	Robot.armavator.armPID.setSetpoint(targetValue);
    	Robot.armavator.armPID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	outputSpeed = Robot.armavator.armSpeedOutput.getSpeed();
    	Robot.armavator.setArm(outputSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(holdPosition) return false;
        else return Robot.armavator.armPID.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
