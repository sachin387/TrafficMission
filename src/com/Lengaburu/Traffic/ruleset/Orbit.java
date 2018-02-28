package com.Lengaburu.Traffic.ruleset;

import java.util.HashMap;

public class Orbit {
	
	public static HashMap<String, OrbitRuleSet> ruleSet = new HashMap<>();
	static {
		
		OrbitRuleSet orbit1RuleSet = new OrbitRuleSet();
		orbit1RuleSet.setOrbitDistance(18);
		orbit1RuleSet.setOrbitCraters(20);
		orbit1RuleSet.setOrbitName("ORBIT1");
		orbit1RuleSet.setOrbitConnectedCity1("Silk Dorb");
		orbit1RuleSet.setOrbitConnectedCity2("Hallitharam");
		ruleSet.put("ORBIT1",orbit1RuleSet);
		
		OrbitRuleSet orbit2RuleSet = new OrbitRuleSet();
		orbit2RuleSet.setOrbitDistance(20);
		orbit2RuleSet.setOrbitCraters(10);
		orbit2RuleSet.setOrbitName("ORBIT2");
		orbit2RuleSet.setOrbitConnectedCity1("Silk Dorb");
		orbit2RuleSet.setOrbitConnectedCity2("Hallitharam");
		ruleSet.put("ORBIT2", orbit2RuleSet);
		
		OrbitRuleSet orbit3RuleSet = new OrbitRuleSet();
		orbit3RuleSet.setOrbitDistance(30);
		orbit3RuleSet.setOrbitCraters(15);
		orbit3RuleSet.setOrbitName("ORBIT3");
		orbit3RuleSet.setOrbitConnectedCity1("Silk Dorb");
		orbit3RuleSet.setOrbitConnectedCity2("RK Puram");
		ruleSet.put("ORBIT3", orbit3RuleSet);
		
		OrbitRuleSet orbit4RuleSet = new OrbitRuleSet();
		orbit4RuleSet.setOrbitDistance(15);
		orbit4RuleSet.setOrbitCraters(18);
		orbit4RuleSet.setOrbitName("ORBIT4");
		orbit4RuleSet.setOrbitConnectedCity1("RK Puram");
		orbit4RuleSet.setOrbitConnectedCity2("Hallitharam");
		ruleSet.put("ORBIT4", orbit4RuleSet);
		
	}
	
}
