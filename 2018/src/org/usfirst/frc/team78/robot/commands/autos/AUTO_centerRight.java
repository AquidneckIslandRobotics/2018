package org.usfirst.frc.team78.robot.commands.autos;

import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.FollowTrajectory;
import org.usfirst.frc.team78.robot.commands.OpenIntakeGrabber;
import org.usfirst.frc.team78.robot.commands.OuttakeForAUTO;
import org.usfirst.frc.team78.robot.commands.RaiseArmToPreset;

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
    	addParallel(new RaiseArmToPreset(RobotMap.ARM_PARRELLEL_PRESET), 3);
    	addSequential(new FollowTrajectory("centerRight"));
    	addSequential(new OpenIntakeGrabber());
//    	addSequential(new OuttakeForAUTO(RobotMap.OUTTAKE_SPEED-0.05), 1);
    }
}
