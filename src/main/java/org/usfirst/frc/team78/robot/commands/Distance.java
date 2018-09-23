package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Distance extends Command {

    public Distance() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
//    	Robot.chassis.leftDistanceController.setContinuous(false);
//    	Robot.chassis.leftDistanceController.setOutputRange(-0.5, 0.5);
//    	Robot.chassis.rightDistanceController.setContinuous(false);
//    	Robot.chassis.rightDistanceController.setOutputRange(-0.5, 0.5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	double rSpeed = Robot.chassis.rightDistanceSpeed.getSpeed();
//    	double lSpeed = Robot.chassis.leftDistanceSpeed.getSpeed();
//    	Robot.chassis.setSpeed(lSpeed, -rSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return true; //!(Robot.chassis.rightDistanceController.isEnabled() && Robot.chassis.leftDistanceController.isEnabled());
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
