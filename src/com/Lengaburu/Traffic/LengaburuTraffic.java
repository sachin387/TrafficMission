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
		boolean userInputFormat = true;
		InputParser input = new InputParser();
		// Enter data using BufferReader
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(
				"Please enter 1 if you want to vist Hallitharam or to 2 if you vist Hallitharam and RK Puram :");
		String cityVisitUserInput = reader.readLine();
		// Validating user input
		while (!(cityVisitUserInput.matches("-?\\d+")
				&& (Integer.parseInt(cityVisitUserInput) == 1 || Integer.parseInt(cityVisitUserInput) == 2))) {
			System.out.println("Invalid Entry!!");
			System.out.println(
					"Please enter 1 if you want to vist Hallitharam or to 2 if you vist Hallitharam and RK Puram :");
			cityVisitUserInput = reader.readLine();
		}
		cityVisitDision = Integer.parseInt(cityVisitUserInput);
		System.out.println("Please enter today's weather in format \"Weather is <Sunny/Windy/Rainy>\":");
		// Reading data using about today weather
		String weatherUserInput = reader.readLine();
		// Validating user input
		userInputFormat = input.validateWeatherFormat(weatherUserInput);
		while (userInputFormat) {
			System.out.println("Invalid Entry!!");
			System.out.println("Please enter today's Weather in format \"Weather is <Sunny/Windy/Rainy>\":");
			weatherUserInput = reader.readLine();
			userInputFormat = input.validateWeatherFormat(weatherUserInput);
		}
		// Getting weather from user input data
		todayWeather = input.getTodayWeather(weatherUserInput);

		if (cityVisitDision == 1 || cityVisitDision == 2) {
			System.out.println(
					"Please enter orbit1 traffic speed in format \"Orbit1 traffic speed is <TrafficSpeed> megamiles/hour\": ");
			// Reading data using about today's traffic in orbit 1
			String orbit1Traffic = reader.readLine();
			// Validating user input
			userInputFormat = input.validateOrbitSpeedFormat(orbit1Traffic, "Orbit1");
			while (userInputFormat) {
				System.out.println("Invalid Entry!!");
				System.out.println(
						"Please enter orbit1 traffic speed in format \"Orbit1 traffic speed is <TrafficSpeed> megamiles/hour\" ");
				orbit1Traffic = reader.readLine();
				userInputFormat = input.validateOrbitSpeedFormat(orbit1Traffic, "Orbit1");
			}

			System.out.println(
					"Please enter orbit2 traffic speed in format \"Orbit2 traffic speed is <TrafficSpeed> megamiles/hour\":");
			// Reading data using about today's traffic in orbit 2
			String orbit2Traffic = reader.readLine();
			// Validating user input
			userInputFormat = input.validateOrbitSpeedFormat(orbit2Traffic, "Orbit2");
			while (userInputFormat) {
				System.out.println("Invalid Entry!!");
				System.out.println(
						"Please enter orbit2 traffic speed in format \"Orbit2 traffic speed is <TrafficSpeed> megamiles/hour\":");
				orbit2Traffic = reader.readLine();
				userInputFormat = input.validateOrbitSpeedFormat(orbit2Traffic, "Orbit2");
			}
			// Getting orbit 1 traffic from user input data
			todayTrafficInOrbit1 = input.getTodayTraffic(orbit1Traffic);
			// Getting orbit 2 traffic from user input data
			todayTrafficInOrbit2 = input.getTodayTraffic(orbit2Traffic);
		}

		if (cityVisitDision == 2) {

			System.out.println(
					"Please enter orbit3 traffic speed in format \"Orbit3 traffic speed is <TrafficSpeed> megamiles/hour\": ");
			// Reading data using about today's traffic in orbit 3
			String orbit3Traffic = reader.readLine();
			// Validating user input
			userInputFormat = input.validateOrbitSpeedFormat(orbit3Traffic, "Orbit3");
			while (userInputFormat) {
				System.out.println("Invalid Entry!!");
				System.out.println(
						"Please enter orbit3 traffic speed in format \"Orbit3 traffic speed is <TrafficSpeed> megamiles/hour\" ");
				orbit3Traffic = reader.readLine();
				userInputFormat = input.validateOrbitSpeedFormat(orbit3Traffic, "Orbit3");
			}

			System.out.println(
					"Please enter orbit4 traffic speed in format \"Orbit4 traffic speed is <TrafficSpeed> megamiles/hour\":");
			// Reading data using about today's traffic in orbit 4
			String orbit4Traffic = reader.readLine();
			// Validating user input
			userInputFormat = input.validateOrbitSpeedFormat(orbit4Traffic, "Orbit4");
			while (userInputFormat) {
				System.out.println("Invalid Entry!!");
				System.out.println(
						"Please enter orbit4 traffic speed in format \"Orbit4 traffic speed is <TrafficSpeed> megamiles/hour\":");
				orbit4Traffic = reader.readLine();
				userInputFormat = input.validateOrbitSpeedFormat(orbit4Traffic, "Orbit4");
			}
			// Getting orbit 3 traffic from user input data
			todayTrafficInOrbit3 = input.getTodayTraffic(orbit3Traffic);
			// Getting orbit 4 traffic from user input data
			todayTrafficInOrbit4 = input.getTodayTraffic(orbit4Traffic);

		}

		OptimumSolution sol = new OptimumSolution();
		if (cityVisitDision == 1)
			// this method will return the end result on which path is better for problem 1.
			Solution = sol.getTravellTime(todayWeather, todayTrafficInOrbit1, todayTrafficInOrbit2);
		else if (cityVisitDision == 2)
			// this method will return the end result on which path is better for problem 2.
			Solution = sol.getTravellTime(todayWeather, todayTrafficInOrbit1, todayTrafficInOrbit2,
					todayTrafficInOrbit3, todayTrafficInOrbit4);
		Solution = "Result : " + Solution;
		System.out.println(Solution);
	}
}