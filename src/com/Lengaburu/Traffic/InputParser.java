package com.Lengaburu.Traffic;

import java.util.ArrayList;

public class InputParser {
	ArrayList<String> weatherString = new ArrayList<>();
	ArrayList<String> trafficString = new ArrayList<>();
	String todayWeather = "";
	int todayTraffic = 0;
	boolean userFormat;

	// getTodayWeather method will return weather as string : RAINY/SUNNY/WINDY
	String getTodayWeather(String weather) {

		weatherString = string2ArrayList(weather);
		weatherString.forEach(word -> {
			switch (word.toUpperCase()) {
			case "SUNNY":
				todayWeather = "SUNNY";
				break;
			case "WINDY":
				todayWeather = "WINDY";
				break;
			case "RAINY":
				todayWeather = "RAINY";
				break;
			}
		});
		if (todayWeather == "") {
			todayWeather = "Invalid Entry";
		}
		return todayWeather;
	}

	// getTodayTraffic will return orbit traffic in integer.
	int getTodayTraffic(String orbitTraffic) {
		trafficString = string2ArrayList(orbitTraffic);
		trafficString.forEach(word -> {
			if (word.matches("-?\\d+")) {
				todayTraffic = Integer.parseInt(word);
			}
		});
		return todayTraffic;
	}

	// validateWeatherFormat method will return boolean value based of user Input if
	// its valid or not.
	Boolean validateWeatherFormat(String weatherUserInput) {
		if (weatherUserInput.equals("Weather is Sunny") || weatherUserInput.equals("Weather is Windy")
				|| weatherUserInput.equals("Weather is Rainy")) {
			userFormat = false;
		} else {
			userFormat = true;
		}
		return userFormat;
	}

	// validateOrbitSpeedFormat method will return boolean value based of user Input
	// if its valid or not.
	Boolean validateOrbitSpeedFormat(String orbitTrafficSpeed, String orbit) {
		trafficString = string2ArrayList(orbitTrafficSpeed);
		if (trafficString.get(0).equals(orbit)
				&& (trafficString.get(1) + trafficString.get(2) + trafficString.get(3)).equals("trafficspeedis")
				&& trafficString.get(4).matches("-?\\d+") && trafficString.get(5).equals("megamiles/hour")) {
			userFormat = false;
		} else {
			userFormat = true;
		}
		return userFormat;
	}

	// string2ArrayList will take user input in string and return ArrayList word
	ArrayList<String> string2ArrayList(String userInput) {
		ArrayList<String> wordArrayList = new ArrayList<>();
		for (String word : userInput.split(" ")) {
			wordArrayList.add(word);
		}
		return wordArrayList;
	}
}
