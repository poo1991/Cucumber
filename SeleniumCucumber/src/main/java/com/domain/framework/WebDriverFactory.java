package com.domain.framework;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverFactory {
	private static String testEnvtHeader;
	
	public static RemoteWebDriver getDriver(String browserName, String platform, String version, String username, String accessKey, String jobName,String tunnelIdentifier) throws MalformedURLException {
		testEnvtHeader =System.getProperty("testEnvtHeader")==null? Settings.getProperty("testEnvtHeader") : System.getProperty("testEnvtHeader");
		DesiredCapabilities dc = new DesiredCapabilities();
		switch (browserName.toLowerCase()) {
		case "chrome":
			ChromeOptions options = new ChromeOptions();
			if(testEnvtHeader != null) {
				options.addExtensions(new File("crx path"));
				options.addArguments("--test-type --no-sandbox");
			}
			options.addArguments("user-agent=LDQA");
			dc.setCapability(CapabilityType.BROWSER_NAME, browserName);
			dc.setCapability(CapabilityType.VERSION, version);
			dc.setCapability(CapabilityType.PLATFORM, platform);
			break;

		case "firefox":
	
			dc.setCapability(CapabilityType.BROWSER_NAME, browserName);
			dc.setCapability(CapabilityType.VERSION, version);
			dc.setCapability(CapabilityType.PLATFORM, platform);
			break;

		default:
			break;
		}
		dc.setCapability("name", jobName);
		dc.setCapability("username", username);
		dc.setCapability("accessKey", accessKey);
		dc.setCapability("tunnelIdentifier", tunnelIdentifier);
		return new RemoteWebDriver(new URL("http://"+username+":"+accessKey+"@ondemand.saucelabs.com:80/wd/hub"),dc);
	}
}
