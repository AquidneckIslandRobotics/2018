package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SwitchWrist extends Command {

    public SwitchWrist() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.intake.switchWrist) {
    		Robot.intake.flexWrist();
    		Robot.intake.switchWrist = false;
    	}else if(!Robot.intake.switchWrist) {
    		Robot.intake.straightenWrist();
    		Robot.intake.switchWrist = true;
    	}else {
    		Robot.intake.straightenWrist();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
