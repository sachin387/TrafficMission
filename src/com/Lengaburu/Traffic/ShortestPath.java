package com.Lengaburu.Traffic;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import com.Lengaburu.Traffic.DTO.TrafficDTO;

public class ShortestPath {
	static LinkedList<String> shortestPath = new LinkedList<>();
	static String bestVehicle="";
	static float bestTime = Float.MAX_VALUE;
	static LinkedList<LinkedList<String>> allPosiblePaths= new LinkedList<>();
	static LinkedList<String> currentPermutation = new LinkedList<>();
	static void shortestPath(LinkedList<TrafficDTO> vehileTravellTimeData) {
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
		
		String [] allCities = new String[city.size()];
		for(int j=0;j<city.size();j++) {
			allCities[j]= city.get(j);
		}
		//this method will give all possible path between the cities. Keeping an assumption that King Shan Visit all cities.
		permute(allCities, 0);
		//removing all the path whose starting city is not Silk Dorb and invalid paths.
		allPosiblePaths = removeInvalidPaths(allPosiblePaths, vehileTravellTimeData);
		getTotalTime(vehicle, allPosiblePaths, vehileTravellTimeData);
		
	}
	
	static HashMap<String,HashMap<LinkedList<String>, Float>>totalTravellTime = new HashMap<>();
	static void getTotalTime(LinkedList<String> vehicle, LinkedList<LinkedList<String>> allPosiblePath, LinkedList<TrafficDTO> vehileTravellTimeData) {	
		for(int i=0;i<vehicle.size();i++) {
			HashMap<LinkedList<String>, Float> totalTravellTimeForAParticularVehicle =new HashMap<>();
			for(int j=0;j<allPosiblePath.size();j++) {
				Float totalTime = (float)0;
				for(int k=0;k<allPosiblePath.get(j).size()-1;k++) {
					String city1 = allPosiblePath.get(j).get(k);
					String city2 = allPosiblePath.get(j).get(k+1);
					
					for(int l =0;l<vehileTravellTimeData.size();l++) {
						if(city1 == vehileTravellTimeData.get(l).getConnectedCity1() && city2== vehileTravellTimeData.get(l).getConnectedCity2() && vehicle.get(i)==vehileTravellTimeData.get(l).getVehicle()) {
							totalTime += vehileTravellTimeData.get(l).getTravellTime();
							break;
						}
						else if(city1 == vehileTravellTimeData.get(l).getConnectedCity2() && city2== vehileTravellTimeData.get(l).getConnectedCity1() && vehicle.get(i)==vehileTravellTimeData.get(l).getVehicle()) {
							totalTime += vehileTravellTimeData.get(l).getTravellTime();
							break;
						}
					}
				}
				totalTravellTimeForAParticularVehicle.put(allPosiblePath.get(j), totalTime);
			}
			totalTravellTime.put(vehicle.get(i), totalTravellTimeForAParticularVehicle);
		}
		
		totalTravellTime.forEach((k,v)->{
			v.forEach((k1,v1)->{
				if(bestTime>v1) {
					shortestPath = k1;
					bestVehicle = k;
					bestTime=v1;
				}
			});
		});
	}
	
	
	static LinkedList<LinkedList<String>> removeInvalidPaths(LinkedList<LinkedList<String>> allPosiblePath, LinkedList<TrafficDTO> vehileTravellTimeData) {
		for(int i = 0;i<allPosiblePath.size();i++) {
			if(allPosiblePath.get(i).get(0)!= "Silk Dorb") {
				allPosiblePath.remove(i);
				i--;
			}
		}
		for(int i= 0;i<allPosiblePath.size();i++) {
			for(int j=0;j<allPosiblePath.get(i).size()-1;j++) {
				String city1 = allPosiblePath.get(i).get(j);
				String city2 = allPosiblePath.get(i).get(j+1);
				int counter=0;
				for(int k =0;k<vehileTravellTimeData.size();k++) {
					if(city1 == vehileTravellTimeData.get(k).getConnectedCity1() && city2==vehileTravellTimeData.get(k).getConnectedCity2()) {
						counter++;
						break;
					}else if(city1 == vehileTravellTimeData.get(k).getConnectedCity2() && city2==vehileTravellTimeData.get(k).getConnectedCity1()) {
						counter++;
						break;
					}
				}
				if(counter==0) {
					allPosiblePath.remove(i);
					i--;
					break;
				}
			}
			
		}
		return allPosiblePath;
	}

	static void permute(String[] a, int k) 
    {
        if (k == a.length) 
        {
            for (int i = 0; i < a.length; i++) 
            {
                currentPermutation.add(a[i]);
            }
            allPosiblePaths.add(currentPermutation);
            currentPermutation = new LinkedList<>();
        } 
        else 
        {
            for (int i = k; i < a.length; i++) 
            {
                String temp = a[k];
                a[k] = a[i];
                a[i] = temp;
                permute(a, k + 1);
                temp = a[k];
                a[k] = a[i];
                a[i] = temp;
            }
        }
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