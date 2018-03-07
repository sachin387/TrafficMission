package com.Lengaburu.Traffic;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import com.Lengaburu.Traffic.ruleset.*;
import com.Lengaburu.Traffic.DTO.*;

public class Optimumsolution {
	
	float totalTravelTime = 0;
	String optimumSolution="";
	LinkedList<TrafficDTO> vehileTravellTimeData;
	
	String getTravellTime(String todayWeather, int todayTrafficInOrbit1, int todayTrafficInOrbit2) {
		
		HashMap<String, Integer> todayTrafficSpeedInOrbit = new HashMap<>();
		todayTrafficSpeedInOrbit.put("ORBIT1", todayTrafficInOrbit1);
		todayTrafficSpeedInOrbit.put("ORBIT2", todayTrafficInOrbit2);
		
		TrafficRuleSet trafficRuleSet = TrafficForProblem1.ruleSet.get(todayWeather);
		WeatherRuleSet weatherRuleset = trafficRuleSet.getWeatherRuleSet();
		LinkedList<VehicleRuleSet> vehileRuleSet = trafficRuleSet.getVehicleRuleSet();
		LinkedList<OrbitRuleSet> orbitRuleSet = trafficRuleSet.getOrbitRuleSet();
		vehileTravellTimeData= new LinkedList<>();
		
		vehileRuleSet.forEach(vehicle->{
			orbitRuleSet.forEach(orbit->{
				TrafficDTO trafficDTO = new TrafficDTO();
				totalTravelTime = getCraterTravellTime(vehicle, orbit, weatherRuleset) + getOrbitTravellTime(vehicle, orbit, todayTrafficSpeedInOrbit);;
				trafficDTO.setVehicle(vehicle.getVehileName());
				trafficDTO.setOrbit(orbit.getOrbitName());
				trafficDTO.setTravellTime(totalTravelTime);
				trafficDTO.setConnectedCity1(orbit.getOrbitConnectedCity1());
				trafficDTO.setConnectedCity2(orbit.getOrbitConnectedCity2());
				vehileTravellTimeData.add(trafficDTO);
			});
		});
		//optimumSolution = getBestVehicle(vehileTravellTimeData);
		optimumSolution = ShortestPath.shortestPath(vehileTravellTimeData);
		return optimumSolution;
	}
	
	String getTravellTime(String todayWeather, int todayTrafficInOrbit1, int todayTrafficInOrbit2, int todayTrafficInOrbit3, int todayTrafficInOrbit4) {
		
		HashMap<String, Integer> todayTrafficSpeedInOrbit = new HashMap<>();
		todayTrafficSpeedInOrbit.put("ORBIT1" ,todayTrafficInOrbit1);
		todayTrafficSpeedInOrbit.put("ORBIT2", todayTrafficInOrbit2);
		todayTrafficSpeedInOrbit.put("ORBIT3" ,todayTrafficInOrbit3);
		todayTrafficSpeedInOrbit.put("ORBIT4", todayTrafficInOrbit4);
		
		TrafficRuleSet trafficRuleSet = TrafficForProblem2.ruleSet.get(todayWeather);
		WeatherRuleSet weatherRuleset = trafficRuleSet.getWeatherRuleSet();
		LinkedList<VehicleRuleSet> vehileRuleSet = trafficRuleSet.getVehicleRuleSet();
		LinkedList<OrbitRuleSet> orbitRuleSet = trafficRuleSet.getOrbitRuleSet();
		vehileTravellTimeData= new LinkedList<>();
		
		vehileRuleSet.forEach(vehicle->{
			orbitRuleSet.forEach(orbit->{
				TrafficDTO trafficDTO = new TrafficDTO();
				totalTravelTime = getCraterTravellTime(vehicle, orbit, weatherRuleset) + getOrbitTravellTime(vehicle, orbit, todayTrafficSpeedInOrbit);;
				trafficDTO.setVehicle(vehicle.getVehileName());
				trafficDTO.setOrbit(orbit.getOrbitName());
				trafficDTO.setTravellTime(totalTravelTime);
				trafficDTO.setConnectedCity1(orbit.getOrbitConnectedCity1());
				trafficDTO.setConnectedCity2(orbit.getOrbitConnectedCity2());
				vehileTravellTimeData.add(trafficDTO);
			});
		});
		//optimumSolution = getBestVehicle(vehileTravellTimeData);
		optimumSolution = ShortestPath.shortestPath(vehileTravellTimeData);
		return optimumSolution;
	}
	
	private Float getCraterTravellTime(VehicleRuleSet vehicleRuleSet, OrbitRuleSet orbitRuleSet, WeatherRuleSet weatherRuleSet) {
		float carterCrossingTime = ((orbitRuleSet.getOrbitCraters()+(orbitRuleSet.getOrbitCraters()*weatherRuleSet.getCarterPercentageChange())/100))*vehicleRuleSet.getCarterCrossingTime();		
		return carterCrossingTime;	
	}
	
	private Float getOrbitTravellTime(VehicleRuleSet vehileRuleSet, OrbitRuleSet orbitRuleSet, HashMap<String, Integer> todayTrafficSpeedInOrbit) {
		int vehileSpeed = 0;
		Float travellTime = (float)0;
		
		if(todayTrafficSpeedInOrbit.get(orbitRuleSet.getOrbitName())<vehileRuleSet.getSpeed()) {
			vehileSpeed = todayTrafficSpeedInOrbit.get(orbitRuleSet.getOrbitName());
		}
		else{
			vehileSpeed = vehileRuleSet.getSpeed();
		}
		
		travellTime = ( (float)orbitRuleSet.getOrbitDistance()/(float)vehileSpeed)*(float)60;
		return travellTime;
	}
	
	private String getBestVehicle(LinkedList<TrafficDTO> vehileTravellTimeData) {
		LinkedList<String> bestVehicle = new LinkedList<>();
		LinkedList<String> bestOrbit = new LinkedList<>();
		Collections.sort(vehileTravellTimeData, new TravellTimeComparator());		
		if(vehileTravellTimeData.get(0).getTravellTime()== vehileTravellTimeData.get(1).getTravellTime()) {
			bestVehicle.add(vehileTravellTimeData.get(0).getVehicle());
			bestVehicle.add(vehileTravellTimeData.get(1).getVehicle());
			bestOrbit.add(vehileTravellTimeData.get(0).getOrbit());
			bestOrbit.add(vehileTravellTimeData.get(1).getOrbit());
		}
		else if(vehileTravellTimeData.get(0).getTravellTime()== vehileTravellTimeData.get(2).getTravellTime()) {
			bestVehicle.add(vehileTravellTimeData.get(2).getVehicle());
			bestOrbit.add(vehileTravellTimeData.get(2).getOrbit());
		}
		else {
			bestVehicle.add(vehileTravellTimeData.get(0).getVehicle());
			bestOrbit.add(vehileTravellTimeData.get(0).getOrbit());
		}
		
		if(bestVehicle.contains("BIKE") && bestVehicle.contains("SUPERCAR")&&bestVehicle.contains("TUKTUK")) {
			return "BIKE on"+bestOrbit.get(bestVehicle.indexOf("BIKE"));
		}else if (bestVehicle.contains("BIKE") && bestVehicle.contains("SUPERCAR")) {
			return "BIKE on"+bestOrbit.get(bestVehicle.indexOf("BIKE"));
		}else if (bestVehicle.contains("BIKE") && bestVehicle.contains("TUKTUK")) {
			return "BIKE on"+bestOrbit.get(bestVehicle.indexOf("BIKE"));
		}else if (bestVehicle.contains("SUPERCAR") && bestVehicle.contains("TUKTUK")) {
			return "TUKTUK on"+bestOrbit.get(bestVehicle.indexOf("TUKTUK"));
		}else {
			return "Vehicle "+bestVehicle.get(0)+" on "+bestOrbit.get(0);
		}
	}	
		
}
