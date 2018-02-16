package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.subsystems.Armavator;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RaiseArmToPreset extends Command {

	double preset;
	double offset = 0.0;
	
    public RaiseArmToPreset(double Preset) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    		requires(Robot.armavator);
    		preset = Preset;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.armavator.getArmPot() > (preset+offset)) {
    		Robot.armavator.setArm(-0.55);
    	}else if(Robot.armavator.getArmPot() < (preset-offset)) {
    		Robot.armavator.setArm(0.55);
    	}else {
    		Robot.armavator.stopArm();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.armavator.getArmPot() == preset);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.armavator.stopArm();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
