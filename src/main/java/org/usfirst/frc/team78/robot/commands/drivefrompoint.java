package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class drivefrompoint extends Command {

	double rightdist;
	double leftdist;
	
	
    public drivefrompoint() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	rightdist = Robot.motionProfile.getDistanceFromPoints(0, 0, 0, 1);
//    	rightdist *= Robot.chassis.feetToPulses;
//    	Robot.chassis.rightDistanceController.setContinuous(false);
//    	Robot.chassis.rightDistanceController.setOutputRange(-0.5, 0.5);
//    	Robot.chassis.rightDistanceController.setSetpoint(rightdist);
//    	
//    	leftdist = Robot.motionProfile.getDistanceFromPoints(0, 0, 0, 1);
//    	leftdist *= Robot.chassis.feetToPulses;
//    	Robot.chassis.leftDistanceController.setContinuous(false);
//    	Robot.chassis.leftDistanceController.setOutputRange(-0.5, 0.5);
//    	Robot.chassis.leftDistanceController.setSetpoint(leftdist);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	double rspeed = Robot.chassis.rightDistanceSpeed.getSpeed();
//    	double lspeed = Robot.chassis.leftDistanceSpeed.getSpeed();
//    	Robot.chassis.setSpeed(lspeed, -rspeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
