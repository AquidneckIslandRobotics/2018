package org.usfirst.frc.team78.robot.subsystems;

import org.opencv.core.Mat;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class MotionProfile extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public int unitSize = 1; // 1 unit is the same as 1 foot/meter/inch (we havent figured out what to use yet)

	public double getDistanceFromPoints(double x1, double y1, double x2, double y2) {
		double dist = 0;
		double xRadius = 0;
		double yRadius = 0;
		
		if(x1 == x2) {
			dist = y2 - y1;
		}else if(y1 == y2) {
			dist = x2 - x1;
		}else {
			if(!(x1 == x2)) {
				xRadius = x2 - x1;
			}
			
			if(!(y1 == y2)) {
				yRadius = y2 - y1;
			}
			
			xRadius = Math.abs(xRadius);
			yRadius = Math.abs(yRadius);
			
			double arcLength = ((2 * Math.PI * xRadius) / 4);
			dist = arcLength;
		}
		
		return dist;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

