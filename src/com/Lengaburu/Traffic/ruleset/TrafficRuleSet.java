package com.Lengaburu.Traffic.ruleset;

import java.util.LinkedList;

public class TrafficRuleSet {


	private WeatherRuleSet weatherRuleSet = new WeatherRuleSet();
	private LinkedList<OrbitRuleSet> orbitRuleSet = new LinkedList<>();
	private LinkedList<VehicleRuleSet> vehicleRuleSet = new LinkedList<>();
	
	public LinkedList<VehicleRuleSet> getVehicleRuleSet() {
		return vehicleRuleSet;
	}
	public void setVehicleRuleSet(LinkedList<VehicleRuleSet> vehicleRuleSet) {
		this.vehicleRuleSet = vehicleRuleSet;
	}
	public LinkedList<OrbitRuleSet> getOrbitRuleSet() {
		return orbitRuleSet;
	}
	public void setOrbitRuleSet(LinkedList<OrbitRuleSet> orbitRuleSet) {
		this.orbitRuleSet = orbitRuleSet;
	}
	public WeatherRuleSet getWeatherRuleSet() {
		return weatherRuleSet;
	}
	public void setWeatherRuleSet(WeatherRuleSet weatherRuleSet) {
		this.weatherRuleSet = weatherRuleSet;
	}
}
