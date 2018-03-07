package com.Lengaburu.Traffic.ruleset;

import java.util.HashMap;

public class Vehicles {

	public static HashMap<String, VehicleRuleSet> ruleSet = new HashMap<>();

	static {
		VehicleRuleSet bikeRuleSet = new VehicleRuleSet();
		bikeRuleSet.setSpeed(10);
		bikeRuleSet.setCarterCrossingTime(2);
		bikeRuleSet.setVehileName("BIKE");

		VehicleRuleSet tuktukRuleSet = new VehicleRuleSet();
		tuktukRuleSet.setSpeed(12);
		tuktukRuleSet.setCarterCrossingTime(1);
		tuktukRuleSet.setVehileName("TUKTUK");

		VehicleRuleSet supercarRuleSet = new VehicleRuleSet();
		supercarRuleSet.setSpeed(20);
		supercarRuleSet.setCarterCrossingTime(3);
		supercarRuleSet.setVehileName("SUPERCAR");

		ruleSet.put("BIKE", bikeRuleSet);
		ruleSet.put("TUKTUK", tuktukRuleSet);
		ruleSet.put("SUPERCAR", supercarRuleSet);
	}

	HashMap<String, VehicleRuleSet> getVehicles() {
		return ruleSet;
	}
}