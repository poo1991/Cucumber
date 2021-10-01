package com.domain.runner;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;

import com.domain.framework.ReportHelper;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features= {"src/test/resources/features"},
glue = {"com.domain.steps"},
plugin = {"pretty","html:target/cucumber","json:target/cucumber.json"},
tags ="@tag1",
monochrome=true
)
public class TestRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel=false)
	public Object[][] scenarios(){
		return super.scenarios();
	}
	
	@AfterSuite(alwaysRun =true)
	public void generateReports() throws Exception {
		ReportHelper.generateCucumberReport();
	}
}
