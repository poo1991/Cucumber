package com.domain.framework;

import java.util.Properties;

public class ConfigFileReader {
	private Properties properties;

	public ConfigFileReader() {
		properties=Settings.loadFromPropertiesFile();
	}

	public String getDriverPath() {
		String driverPath = properties.getProperty("ChromeDriverPath");
		if(driverPath!=null)
			return driverPath;
		else 
			throw new RuntimeException("cHROME dRIVER Path is not present in propsfile");
	}
	
	public Browser getBrowser() {
		String browserName = readPropertyOrEnv("LocalBrowser", "chrome");
		switch (browserName) {
		case "chrome":
			return Browser.CHROME;
		case "safari":
			return Browser.SAFARI;
		case "firefox":
			return Browser.FIREFOX;
		default:
			return Browser.CHROME;
		}
	}
	
	public String getEnvironment() {
		String envName = readPropertyOrEnv("testbed",null);
		if(envName==null || envName.equalsIgnoreCase("LocalBrowser"))
			return "local";
		else if (envName.equals("saucelab")) 
			return "saucelab";
		else
			return "local";
	}
	
	public String getTestPlatform() {
		String testPlatform = readPropertyOrEnv("testPlatform",null);
		if(testPlatform==null || testPlatform.equalsIgnoreCase("Desktop"))
			return "Desktop";
		else if (testPlatform.equals("Mobile")) 
			return "Mobile";
		else
			return "Desktop";
	}
	
	private String readPropertyOrEnv(String key, String defaultValue) {
		String prop = System.getenv(key);
		if(prop==null)
			prop=Settings.getProperty(key);
		if(prop==null)
			prop=defaultValue;
		return prop;
	}

	public String getApplicationUrl() {
		String url = System.getProperty("ApplicationUrl")==null? Settings.getProperty("ApplicationUrl"):System.getProperty("ApplicationUrl");
		return url;
		
	}
	
	public String getHeaders() {
		String headers = System.getProperty("headers")==null? Settings.getProperty("headers"):System.getProperty("headers");
		return headers;
		
	}
	
	
}
