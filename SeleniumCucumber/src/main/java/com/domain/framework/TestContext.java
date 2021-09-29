package com.domain.framework;

public class TestContext {
	private WebDriverRegistry webDriverRegistry;
	public PageRegistry pageRegistry;
	public ScenarioContext scenarioContext;
	
	public TestContext() {
		webDriverRegistry = new WebDriverRegistry();
		scenarioContext = new ScenarioContext();
	}

	public WebDriverRegistry getWebDriverRegistry() {
		return webDriverRegistry;
	}

	public PageRegistry getPageRegistry() {
		return pageRegistry;
	}

	public ScenarioContext getScenarioContext() {
		return scenarioContext;
	}
}
