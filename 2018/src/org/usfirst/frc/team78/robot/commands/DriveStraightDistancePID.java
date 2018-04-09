package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightDistancePID extends Command {
	
	double target, leftOutputSpeed, rightOutputSpeed;
	double speedMax = 1;
	
    public DriveStraightDistancePID(double encoderTarget) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	target = encoderTarget;
    }
    
    public DriveStraightDistancePID(double encoderTarget, double maxSpeed) {
    	target = encoderTarget;
    	speedMax = maxSpeed;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	//LEFT DRIVE PID
    	Robot.chassis.leftDriveController.setContinuous(false);
    	Robot.chassis.leftDriveController.setInputRange(-0.75, 0.75);
    	Robot.chassis.leftDriveController.setOutputRange(-speedMax, speedMax);
    	Robot.chassis.leftDriveController.setAbsoluteTolerance(100);
    	Robot.chassis.leftDriveController.setSetpoint(target);
    	Robot.chassis.leftDriveController.enable();
    	
    	//RIGHT DRIVE PID
    	Robot.chassis.rightDriveController.setContinuous(false);
    	Robot.chassis.rightDriveController.setInputRange(-0.75, 0.75);
    	Robot.chassis.rightDriveController.setOutputRange(-speedMax, speedMax);
    	Robot.chassis.rightDriveController.setAbsoluteTolerance(100);
    	Robot.chassis.rightDriveController.setSetpoint(target);
    	Robot.chassis.rightDriveController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	leftOutputSpeed = Robot.chassis.leftDriveSpeed.getSpeed();
    	rightOutputSpeed = Robot.chassis.rightDriveSpeed.getSpeed();
    	
    	Robot.chassis.setSpeed(leftOutputSpeed, rightOutputSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.chassis.leftDriveController.onTarget() && Robot.chassis.rightDriveController.onTarget());
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.setSpeed(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
