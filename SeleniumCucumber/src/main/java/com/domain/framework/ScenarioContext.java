package com.domain.framework;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
	private Map<String, Object> scenarioContext;

	
	public ScenarioContext() {
		scenarioContext= new HashMap<String, Object>();
	}
	public Object getContext(String key) {
		return scenarioContext.get(key);
	}

	public void setContext(String key,Object value) {
		scenarioContext.put(key, value);
	}
	
	public void deleteContext(String key) {
		scenarioContext.remove(key);
	}
	
	public Boolean isContains(String key) {
		return scenarioContext.containsKey(key);
	}
	
	public void clearContext() {
		scenarioContext.clear();
	}
	

}
