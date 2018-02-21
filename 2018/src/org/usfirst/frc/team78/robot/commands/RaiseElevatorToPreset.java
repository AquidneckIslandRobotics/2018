package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RaiseElevatorToPreset extends Command {
	
	double preset;
	double offset = 0.0;
	
    public RaiseElevatorToPreset(double Preset) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    	preset = Preset;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.elevator.getElevatorMagPosition() > (preset+offset+RobotMap.ELEVATOR_DEADZONE)) {
    		Robot.elevator.setElevator(-0.4);
    	}else if(Robot.elevator.getElevatorMagPosition() < (preset-offset-RobotMap.ELEVATOR_DEADZONE)) {
    		Robot.elevator.setElevator(0.4);
    	} else {
    		Robot.elevator.stopElevator();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ((Robot.elevator.getElevatorMagPosition() == preset) || !Robot.elevator.getBottomElevatorLimit() || !Robot.elevator.getUpperElevatorLimit());
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.stopElevator();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
