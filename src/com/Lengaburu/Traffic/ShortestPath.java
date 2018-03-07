package com.Lengaburu.Traffic;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import com.Lengaburu.Traffic.DTO.TrafficDTO;

public class ShortestPath {
	static LinkedList<String> shortestPath = new LinkedList<>();
	static String bestVehicle = "";
	static String result = "";
	static float bestTime = Float.MAX_VALUE;
	static LinkedList<LinkedList<String>> allPosiblePaths = new LinkedList<>();
	static LinkedList<LinkedList<String>> allPosibleOrbits = new LinkedList<>();
	static LinkedList<String> currentPermutation = new LinkedList<>();
	static HashMap<String, HashMap<LinkedList<String>, Float>> totalTravellTime = new HashMap<>();
	static Float totalTime, shortestTime;

	static String shortestPath(LinkedList<TrafficDTO> vehileTravellTimeData) {
		// We are assuming that Silk Dorb city will be always the source city;
		removeMultipleOrbitForSameVehicleBetweenTwoCity(vehileTravellTimeData);
		LinkedList<String> vehicle = new LinkedList<>();
		LinkedList<String> city = new LinkedList<>();
		for (int i = 0; i < vehileTravellTimeData.size(); i++) {
			if (!vehicle.contains(vehileTravellTimeData.get(i).getVehicle())) {
				vehicle.add(vehileTravellTimeData.get(i).getVehicle());
			}
			if (!city.contains(vehileTravellTimeData.get(i).getConnectedCity1())) {
				city.add(vehileTravellTimeData.get(i).getConnectedCity1());
			} else if (!city.contains(vehileTravellTimeData.get(i).getConnectedCity2())) {
				city.add(vehileTravellTimeData.get(i).getConnectedCity2());
			}
		}

		String[] allCities = new String[city.size()];
		for (int j = 0; j < city.size(); j++) {
			allCities[j] = city.get(j);
		}
		// this method will give all possible path between the cities. Keeping an
		// assumption that King Shan Visit all cities.
		allPosiblePaths = permute(allCities, 0);
		// removing all the path whose starting city is not Silk Dorb and invalid paths.
		allPosiblePaths = removeInvalidPaths(allPosiblePaths, vehileTravellTimeData);
		// allPosibleOrbits = allPossibleOrbit(allPosiblePaths, vehileTravellTimeData);
		totalTravellTime = getTotalTime(vehicle, allPosiblePaths, vehileTravellTimeData);
		shortestTime = getShortestTime(totalTravellTime);
		result = validateShortestTime(shortestTime, totalTravellTime, vehileTravellTimeData);
		return result;
	}

	static HashMap<String, HashMap<LinkedList<String>, Float>> getTotalTime(LinkedList<String> vehicles,
			LinkedList<LinkedList<String>> allPosiblePaths, LinkedList<TrafficDTO> vehileTravellTimeData) {
		vehicles.forEach(vehicle -> {
			HashMap<LinkedList<String>, Float> totalTravellTimeForAParticularVehicle = new HashMap<>();
			allPosiblePaths.forEach(path -> {
				totalTime = (float) 0;
				for (int i = 0; i < path.size() - 1; i++) {
					for (int j = 0; j < vehileTravellTimeData.size(); j++) {
						if ((path.get(i) == vehileTravellTimeData.get(j).getConnectedCity1()
								&& path.get(i + 1) == vehileTravellTimeData.get(j).getConnectedCity2()
								&& vehicle == vehileTravellTimeData.get(j).getVehicle())
								|| (path.get(i + 1) == vehileTravellTimeData.get(j).getConnectedCity1()
										&& path.get(i) == vehileTravellTimeData.get(j).getConnectedCity2()
										&& vehicle == vehileTravellTimeData.get(j).getVehicle())) {
							totalTime += vehileTravellTimeData.get(j).getTravellTime();
						}

					}
				}
				totalTravellTimeForAParticularVehicle.put(path, totalTime);
			});
			totalTravellTime.put(vehicle, totalTravellTimeForAParticularVehicle);
		});
		return totalTravellTime;
	}

	static float getShortestTime(HashMap<String, HashMap<LinkedList<String>, Float>> totalTravellTime) {

		shortestTime = Float.MAX_VALUE;

		totalTravellTime.forEach((vehicle, val) -> {
			val.forEach((path, time) -> {
				if (shortestTime > time) {
					shortestTime = time;
				}
			});
		});
		return shortestTime;
	}

	static String validateShortestTime(float shortestTime,
			HashMap<String, HashMap<LinkedList<String>, Float>> totalTravellTime,
			LinkedList<TrafficDTO> vehileTravellTimeData) {
		totalTravellTime.forEach((vehicle, pathSetData) -> {
			if (vehicle == "BIKE" && pathSetData.containsValue(shortestTime)) {
				result += "Vehicle Bike to ";
				bestVehicle = "BIKE";
				shortestPath = getPath(pathSetData, shortestTime);
			} else if (vehicle == "TUKTUK" && pathSetData.containsValue(shortestTime)) {
				result += "Vehicle TukTuk to ";
				bestVehicle = "TUKTUK";
				shortestPath = getPath(pathSetData, shortestTime);
			} else if (vehicle == "SUPERCAR" && pathSetData.containsValue(shortestTime)) {
				result += "Vehicle SuperCar to ";
				bestVehicle = "SUPERCAR";
				shortestPath = getPath(pathSetData, shortestTime);
			}
		});
		LinkedList<String> city = new LinkedList<>();
		LinkedList<String> orbit = new LinkedList<>();
		city.add("Silk Dorb");
		for (int i = 0; i < shortestPath.size() - 1; i++) {
			for (int j = 0; j < vehileTravellTimeData.size(); j++) {
				if ((shortestPath.get(i) == vehileTravellTimeData.get(j).getConnectedCity1()
						&& shortestPath.get(i + 1) == vehileTravellTimeData.get(j).getConnectedCity2()
						&& bestVehicle == vehileTravellTimeData.get(j).getVehicle())
						|| (shortestPath.get(i + 1) == vehileTravellTimeData.get(j).getConnectedCity1()
								&& shortestPath.get(i) == vehileTravellTimeData.get(j).getConnectedCity2()
								&& bestVehicle == vehileTravellTimeData.get(j).getVehicle())) {
					if (!city.contains(vehileTravellTimeData.get(j).getConnectedCity1())) {
						city.add(vehileTravellTimeData.get(j).getConnectedCity1());
					} else if (!city.contains(vehileTravellTimeData.get(j).getConnectedCity2())) {
						city.add(vehileTravellTimeData.get(j).getConnectedCity2());
					}
					orbit.add(vehileTravellTimeData.get(j).getOrbit());
				}
			}
		}
		if (orbit.size() == 1) {
			result += city.get(1) + " via " + orbit.get(0);
		} else {
			for (int i = 0; i < orbit.size(); i++) {
				result += " " + city.get(i + 1) + " via " + orbit.get(i) + " and";
			}
			result = result.substring(0, result.lastIndexOf(" "));
		}

		return result;
	}

	static LinkedList<String> getPath(HashMap<LinkedList<String>, Float> vehicleTravellTime, float shortestTime) {
		vehicleTravellTime.forEach((path, time) -> {
			if (time == shortestTime) {
				shortestPath = path;
			}
		});
		return shortestPath;
	}

	static LinkedList<LinkedList<String>> removeInvalidPaths(LinkedList<LinkedList<String>> allPosiblePath,
			LinkedList<TrafficDTO> vehileTravellTimeData) {
		for (int i = 0; i < allPosiblePath.size(); i++) {
			if (allPosiblePath.get(i).get(0) != "Silk Dorb") {
				allPosiblePath.remove(i);
				i--;
			}
		}
		for (int i = 0; i < allPosiblePath.size(); i++) {
			for (int j = 0; j < allPosiblePath.get(i).size() - 1; j++) {
				String city1 = allPosiblePath.get(i).get(j);
				String city2 = allPosiblePath.get(i).get(j + 1);
				int counter = 0;
				for (int k = 0; k < vehileTravellTimeData.size(); k++) {
					if (city1 == vehileTravellTimeData.get(k).getConnectedCity1()
							&& city2 == vehileTravellTimeData.get(k).getConnectedCity2()) {
						counter++;
						break;
					} else if (city1 == vehileTravellTimeData.get(k).getConnectedCity2()
							&& city2 == vehileTravellTimeData.get(k).getConnectedCity1()) {
						counter++;
						break;
					}
				}
				if (counter == 0) {
					allPosiblePath.remove(i);
					i--;
					break;
				}
			}

		}
		return allPosiblePath;
	}

	static LinkedList<LinkedList<String>> permute(String[] a, int k) {
		if (k == a.length) {
			for (int i = 0; i < a.length; i++) {
				currentPermutation.add(a[i]);
			}
			allPosiblePaths.add(currentPermutation);
			currentPermutation = new LinkedList<>();
		} else {
			for (int i = k; i < a.length; i++) {
				String temp = a[k];
				a[k] = a[i];
				a[i] = temp;
				permute(a, k + 1);
				temp = a[k];
				a[k] = a[i];
				a[i] = temp;
			}
		}
		return allPosiblePaths;
	}

	public static void removeMultipleOrbitForSameVehicleBetweenTwoCity(LinkedList<TrafficDTO> vehileTravellTimeData) {
		Collections.sort(vehileTravellTimeData, new TravellTimeComparator());
		for (int i = 0; i < vehileTravellTimeData.size(); i++) {
			for (int j = i + 1; j < vehileTravellTimeData.size(); j++) {
				if (vehileTravellTimeData.get(i).getConnectedCity1() == vehileTravellTimeData.get(j).getConnectedCity1()
						&& vehileTravellTimeData.get(i).getConnectedCity2() == vehileTravellTimeData.get(j)
								.getConnectedCity2()
						&& vehileTravellTimeData.get(i).getVehicle() == vehileTravellTimeData.get(j).getVehicle()) {
					if (vehileTravellTimeData.get(i).getTravellTime() <= vehileTravellTimeData.get(j)
							.getTravellTime()) {
						vehileTravellTimeData.remove(j);
						j--;
					}

				} else if (vehileTravellTimeData.get(i).getConnectedCity1() == vehileTravellTimeData.get(j)
						.getConnectedCity2()
						&& vehileTravellTimeData.get(i).getConnectedCity2() == vehileTravellTimeData.get(j)
								.getConnectedCity1()
						&& vehileTravellTimeData.get(i).getVehicle() == vehileTravellTimeData.get(j).getVehicle()) {
					if (vehileTravellTimeData.get(i).getTravellTime() <= vehileTravellTimeData.get(j)
							.getTravellTime()) {
						vehileTravellTimeData.remove(j);
						j--;
					}
				}
			}
		}
	}
}