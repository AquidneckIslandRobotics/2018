package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PreMatchPresets extends CommandGroup {

    public PreMatchPresets() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new RaiseArmToPreset(RobotMap.ARM_PARRELLEL_PRESET), 2);
    	addSequential(new LowerElevatorManual(0.2), 5);
    	addSequential(new RaiseElevatorManual(0.3), 1);
    	addSequential(new SetElevatorPID(RobotMap.STOWED_ELEVATOR_PRESET, true, 0.3), 5);
    	addSequential(new SetArmPID(RobotMap.STOWED_ARM_PRESET, true, 0.3), 5);
    	
    }
}
