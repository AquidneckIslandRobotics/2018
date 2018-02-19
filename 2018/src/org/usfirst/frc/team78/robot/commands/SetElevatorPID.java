package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetElevatorPID extends Command {
	
	private double targetValue, outputSpeed;
	private boolean holdPosition = false;
	
	public SetElevatorPID(double target) {
		targetValue = target;
	}
	
    public SetElevatorPID(double target, boolean hold) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	targetValue = target;
    	holdPosition = hold;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.armavator.elePID.setContinuous(false);
    	Robot.armavator.elePID.setInputRange(0, 19000);
    	Robot.armavator.elePID.setOutputRange(-1, 1);
    	Robot.armavator.elePID.setAbsoluteTolerance(100);
    	Robot.armavator.elePID.setSetpoint(targetValue);
    	Robot.armavator.elePID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	outputSpeed = Robot.armavator.eleSpeed.getSpeed();
    	Robot.armavator.setElevator(outputSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(holdPosition) return false;
        else return Robot.armavator.elePID.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
