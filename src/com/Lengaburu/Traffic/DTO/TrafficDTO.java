package com.Lengaburu.Traffic.DTO;

public class TrafficDTO {

	private String vehicle;
	private String orbit;
	private float travellTime;
	private String connectedCity1;
	private String connectedCity2;

	public String getConnectedCity1() {
		return connectedCity1;
	}

	public void setConnectedCity1(String connectedCity1) {
		this.connectedCity1 = connectedCity1;
	}

	public String getConnectedCity2() {
		return connectedCity2;
	}

	public void setConnectedCity2(String connectedCity2) {
		this.connectedCity2 = connectedCity2;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public String getOrbit() {
		return orbit;
	}

	public void setOrbit(String orbit) {
		this.orbit = orbit;
	}

	public float getTravellTime() {
		return travellTime;
	}

	public void setTravellTime(float travellTime) {
		this.travellTime = travellTime;
	}

}
