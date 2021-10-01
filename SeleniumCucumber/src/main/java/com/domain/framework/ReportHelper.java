package com.domain.framework;

import java.io.File;
import java.util.ArrayList;

import com.github.mkolisnyk.cucumber.reporting.CucumberResultsOverview;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class ReportHelper {

	public static void generateCucumberReport() throws Exception {
		File reportOutputDirectory = new File("target");
		ArrayList<String> jsonFiles = new ArrayList<>();
		ConfigFileReader reader = new ConfigFileReader();
		jsonFiles.add("target/cucumber.json");
		
		String projectName ="domainAutomation";
		
		Configuration config = new Configuration(reportOutputDirectory, projectName);
		config.addClassifications("Platform", System.getProperty("os.name"));
		config.addClassifications("ApplicationUrl", reader.getApplicationUrl());
		config.addClassifications("Browser", reader.getBrowser().toString());
		config.addClassifications("TestPlatform", reader.getTestPlatform());
		
		//This will generate a detailed report. If there are failures it shows the step which failed
		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, config);
		reportBuilder.generateReports();
		
		//This gives an overview of the features, scenarios at high level
		CucumberResultsOverview results = new CucumberResultsOverview();
		results.setOutputDirectory("target");
		results.setOutputName("cucumber-results");
		results.setSourceFile("target/cucumber.json");
		results.execute();
		
	}

}
