package com.Lengaburu.Traffic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LengaburuTraffic {
	
	static String todayWeather = "";
	static int todayTrafficInOrbit1 = 0;
	static int todayTrafficInOrbit2 = 0;
	static int todayTrafficInOrbit3 = 0;
	static int todayTrafficInOrbit4 = 0;
	static int cityVisitDision = 0;
	static String Solution = "";
	
	public static void main(String[] args) throws IOException {
		InputParser input = new InputParser();
		// Enter data using BufferReader
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter 1 if you want to vist Hallitharam or to 2 if you vist Hallitharam and RK Puram :");
		int cityVisitDision = Integer.parseInt(reader.readLine());
		
		System.out.println("Please enter today's weather:");
		// Reading data using about today weather
		String weather = reader.readLine();

		
		// Getting weather from user input data
		todayWeather = input.getTodayWeather(weather);
		
		if(cityVisitDision==1 || cityVisitDision==2) {
			System.out.println("Please enter orbit1 traffic: ");
			// Reading data using about today's traffic in orbit 1
			String orbit1Traffic = reader.readLine();
			System.out.println("Please enter orbit2 traffic: ");
			// Reading data using about today's traffic in orbit 2
			String orbit2Traffic = reader.readLine();
			// Getting orbit 1 traffic from user input data
			todayTrafficInOrbit1 = input.getTodayTraffic(orbit1Traffic);
			// Getting orbit 2 traffic from user input data
			todayTrafficInOrbit2 = input.getTodayTraffic(orbit2Traffic);	
		}
		if(cityVisitDision==2) {
			System.out.println("Please enter orbit3 traffic: ");
			// Reading data using about today's traffic in orbit 3
			String orbit3Traffic = reader.readLine();
			System.out.println("Please enter orbit4 traffic: ");
			// Reading data using about today's traffic in orbit 4
			String orbit4Traffic = reader.readLine();
			// Getting orbit 3 traffic from user input data
			todayTrafficInOrbit3 = input.getTodayTraffic(orbit3Traffic);
			// Getting orbit 4 traffic from user input data
			todayTrafficInOrbit4 = input.getTodayTraffic(orbit4Traffic);
		}
		

		Optimumsolution sol = new Optimumsolution();
		
		if(cityVisitDision==1)
			Solution = sol.getTravellTime(todayWeather,todayTrafficInOrbit1,todayTrafficInOrbit2);
		else if(cityVisitDision==2)
			Solution = sol.getTravellTime(todayWeather,todayTrafficInOrbit1,todayTrafficInOrbit2,todayTrafficInOrbit3,todayTrafficInOrbit4);
		//System.out.println(Solution);
		
	}
	


}