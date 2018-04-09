package org.usfirst.frc.team78.robot.commands.autos;

import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.FollowTrajectory;
import org.usfirst.frc.team78.robot.commands.HowToTestAutoPath;
import org.usfirst.frc.team78.robot.commands.IntakeCube;
import org.usfirst.frc.team78.robot.commands.OuttakeCube;
import org.usfirst.frc.team78.robot.commands.ResetGyro;
import org.usfirst.frc.team78.robot.commands.SetArmavatorPID;
import org.usfirst.frc.team78.robot.commands.SetSideSpeed;
import org.usfirst.frc.team78.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AUTO_leftScaleLeft extends CommandGroup {

    public AUTO_leftScaleLeft() {
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
//    	addSequential(new ResetGyro());
//    	addSequential(new SetSideSpeed( 0.75, -0.75),3); //3
//    	addParallel(new IntakeCube(RobotMap.HOLD_CUBE), 0.3);
//    	addSequential(new Turn(30), 2);
//    	addSequential(new SetArmavatorPID(RobotMap.HIGH_SCALE_ARM_PRESET, RobotMap.HIGH_SCALE_ELEVATOR_PRESET, true), 3);
//    	addSequential(new SetSideSpeed(0.25, -0.25), 0.6);
//    	addSequential(new OuttakeCube(0.5), 2);
//    	addSequential(new SetSideSpeed(-0.4, 0.4), 0.6);
//    	addSequential(new SetArmavatorPID(RobotMap.INTAKE_ARM_PRESET, RobotMap.INTAKE_ELEVATOR_PRESET, true), 3);
    	addSequential(new ResetGyro());
//    	addParallel(new SetArmavatorPID(RobotMap.HIGH_SCALE_ARM_PRESET, RobotMap.HIGH_SCALE_ELEVATOR_PRESET, true, 0.6, 2)); //2 sec or ms delay - not timeout
    	addSequential(new FollowTrajectory("LScL_Forward"));
    	addParallel(new SetArmavatorPID(RobotMap.SCALE_AUTO_ARM_PRESET, RobotMap.HIGH_SCALE_ELEVATOR_PRESET, true, 0.6));
    	addSequential(new FollowTrajectory("leftScaleLeft"));
    	addSequential(new Turn(38), 2);
    	addSequential(new OuttakeCube(0.4), 2);
	

    }
}
