package org.usfirst.frc.team78.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class SensorInputPID implements PIDSource {

	private PIDSourceType sourceType;
	private TalonSRX sourceTalon;
	private int sensorNumber;
	
	public SensorInputPID(TalonSRX talon, PIDSourceType typeSource) {
		sourceType = typeSource;
		sourceTalon = talon;
		sensorNumber = 0;
	}
	
	public SensorInputPID(TalonSRX talon, PIDSourceType typeSource, int sensorNum) {
		sourceType = typeSource;
		sourceTalon = talon;
		sensorNumber = sensorNum;
	}
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		sourceType = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return sourceType;
	}

	@Override
	public double pidGet() {
		return sourceTalon.getSelectedSensorPosition(sensorNumber);
	}

}
