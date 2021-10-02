package com.domain.steps;

import java.net.MalformedURLException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.domain.framework.PageRegistry;
import com.domain.framework.TestContext;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	TestContext testContext;
	
	public Hooks(TestContext context) {
		testContext=context;
	}
	
	@Before("@UI")
	public void BeforeSteps(Scenario scenario) {
		testContext.pageRegistry= new PageRegistry(testContext);
	}
	
	@After("@UI")
	public void aFTERSteps(Scenario scenario) throws Exception {
		if(scenario.isFailed()) {
			WebDriver driver = testContext.getWebDriverRegistry().getDriver();
			final byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "iamge/jpeg", "");
		}
		testContext.getWebDriverRegistry().closeDriver();
	}
	
	
	
}
