package com.Lengaburu.Traffic.ruleset;

import java.util.HashMap;
import java.util.LinkedList;

public class TrafficForProblem1 {

	public static HashMap<String, TrafficRuleSet> ruleSet = new HashMap<>();
	static {

		TrafficRuleSet sunnyTrafficRuleset = new TrafficRuleSet();
		TrafficRuleSet windyTrafficRuleset = new TrafficRuleSet();
		TrafficRuleSet rainyTrafficRuleset = new TrafficRuleSet();

		LinkedList<VehicleRuleSet> vehicleCanTravellInSunny = new LinkedList<>();
		LinkedList<VehicleRuleSet> vehicleCanTravellInWindy = new LinkedList<>();
		LinkedList<VehicleRuleSet> vehicleCanTravellInRainy = new LinkedList<>();
		LinkedList<OrbitRuleSet> orbitRuleSet = new LinkedList<>();

		sunnyTrafficRuleset.setWeatherRuleSet(Weather.ruleSet.get("SUNNY"));
		windyTrafficRuleset.setWeatherRuleSet(Weather.ruleSet.get("WINDY"));
		rainyTrafficRuleset.setWeatherRuleSet(Weather.ruleSet.get("RAINY"));

		vehicleCanTravellInSunny.add(Vehicles.ruleSet.get("BIKE"));
		vehicleCanTravellInSunny.add(Vehicles.ruleSet.get("SUPERCAR"));
		vehicleCanTravellInSunny.add(Vehicles.ruleSet.get("TUKTUK"));
		sunnyTrafficRuleset.setVehicleRuleSet(vehicleCanTravellInSunny);

		vehicleCanTravellInRainy.add(Vehicles.ruleSet.get("TUKTUK"));
		vehicleCanTravellInRainy.add(Vehicles.ruleSet.get("SUPERCAR"));
		rainyTrafficRuleset.setVehicleRuleSet(vehicleCanTravellInRainy);

		vehicleCanTravellInWindy.add(Vehicles.ruleSet.get("BIKE"));
		vehicleCanTravellInWindy.add(Vehicles.ruleSet.get("SUPERCAR"));
		windyTrafficRuleset.setVehicleRuleSet(vehicleCanTravellInWindy);

		orbitRuleSet.add(Orbit.ruleSet.get("ORBIT1"));
		orbitRuleSet.add(Orbit.ruleSet.get("ORBIT2"));

		sunnyTrafficRuleset.setOrbitRuleSet(orbitRuleSet);
		windyTrafficRuleset.setOrbitRuleSet(orbitRuleSet);
		rainyTrafficRuleset.setOrbitRuleSet(orbitRuleSet);

		ruleSet.put("SUNNY", sunnyTrafficRuleset);
		ruleSet.put("WINDY", windyTrafficRuleset);
		ruleSet.put("RAINY", rainyTrafficRuleset);
	}
}
