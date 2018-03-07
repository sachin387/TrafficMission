package com.Lengaburu.Traffic.ruleset;

import java.util.HashMap;

public class Weather {

	public static HashMap<String, WeatherRuleSet> ruleSet = new HashMap<>();
	static {

		WeatherRuleSet sunnyWeatherRuleset = new WeatherRuleSet();
		sunnyWeatherRuleset.setCarterPercentageChange(-10);
		sunnyWeatherRuleset.setWeatherName("SUNNY");

		WeatherRuleSet rainyWeatherRuleset = new WeatherRuleSet();
		rainyWeatherRuleset.setCarterPercentageChange(20);
		rainyWeatherRuleset.setWeatherName("RAINY");

		WeatherRuleSet windyWeatherRuleset = new WeatherRuleSet();
		windyWeatherRuleset.setCarterPercentageChange(0);
		windyWeatherRuleset.setWeatherName("WINDY");

		ruleSet.put("SUNNY", sunnyWeatherRuleset);
		ruleSet.put("RAINY", rainyWeatherRuleset);
		ruleSet.put("WINDY", windyWeatherRuleset);

	}

}
