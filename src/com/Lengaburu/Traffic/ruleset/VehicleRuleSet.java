package com.Lengaburu.Traffic.ruleset;

public class VehicleRuleSet {

	private int speed;
	private int carterCrossingTime;
	private String vehileName;

	public String getVehileName() {
		return vehileName;
	}

	public void setVehileName(String vehileName) {
		this.vehileName = vehileName;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getCarterCrossingTime() {
		return carterCrossingTime;
	}

	public void setCarterCrossingTime(int carterCrossingTime) {
		this.carterCrossingTime = carterCrossingTime;
	}

	@Override
	public String toString() {
		return "VehicleRuleSet [speed=" + speed + ", carterCrossingTime=" + carterCrossingTime + "]";
	}

}
