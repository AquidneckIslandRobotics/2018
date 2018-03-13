package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCube extends Command {
	double speed;
	int i = 0;
	
    public IntakeCube(double intakeSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	speed = intakeSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.setIntake(speed);
//    	i++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//    	if((i > 20) && Robot.intake.intakeFollower.getOutputCurrent() > 15) {
//    		return true;
//    	}else{
//    		return false;
//    	}
    	
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.stopIntake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intake.stopIntake();
    }
}
