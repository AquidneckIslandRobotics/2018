package org.usfirst.frc.team78.robot;

import edu.wpi.first.wpilibj.PIDOutput;

public class SpeedOutput implements PIDOutput{

	double speed;
	
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		speed = output;
	}

	public double getSpeed() {
		return speed;
	}
	
}
