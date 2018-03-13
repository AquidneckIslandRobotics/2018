package org.usfirst.frc.team78.robot.commands.autos;

import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.FollowTrajectory;
import org.usfirst.frc.team78.robot.commands.IntakeCube;
import org.usfirst.frc.team78.robot.commands.OpenIntakeGrabber;
import org.usfirst.frc.team78.robot.commands.OuttakeCube;
import org.usfirst.frc.team78.robot.commands.OuttakeForAUTO;
import org.usfirst.frc.team78.robot.commands.RaiseArmToPreset;
import org.usfirst.frc.team78.robot.commands.ResetGyro;
import org.usfirst.frc.team78.robot.commands.SetArmavatorPID;
import org.usfirst.frc.team78.robot.commands.StopIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AUTO_centerRight extends CommandGroup {

    public AUTO_centerRight() {
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
    	//addParallel(new RaiseArmToPreset(RobotMap.ARM_PARRELLEL_PRESET), 3);
    	addSequential(new ResetGyro());
    	addParallel(new FollowTrajectory("centerRight"));
    	addParallel(new SetArmavatorPID(RobotMap.SWITCH_ARM_PRESET, RobotMap.STOWED_ELEVATOR_PRESET, true));
    	addSequential(new IntakeCube(0.0), 3);
    	addSequential(new OuttakeCube(0.35), 2);
//    	addSequential(new OuttakeForAUTO(RobotMap.OUTTAKE_SPEED-0.05), 1);
    	addSequential(new FollowTrajectory("reverseCenterRight"));
    	addSequential(new SetArmavatorPID(RobotMap.INTAKE_ARM_PRESET, RobotMap.INTAKE_ELEVATOR_PRESET, true, 0.8) );
    }
}
