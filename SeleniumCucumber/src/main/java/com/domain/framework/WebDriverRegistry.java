package com.domain.framework;

import java.io.File;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class WebDriverRegistry {
	private WebDriver driver;
	private Browser browser;
	private String environmentType;
	private String testEnvtHeader;
	private String testPlatform;
	private String headers;

	private static volatile Map<Browser,WebDriver> registryDesktop = new HashMap<Browser,WebDriver>();
	private static volatile Map<Browser,WebDriver> registryTablet = new HashMap<Browser,WebDriver>();
	public String username = System.getenv("SAUCE_USER_NAME")==null ? Settings.getProperty("SAUCE_USER_NAME") : System.getenv("SAUCE_USER_NAME");
	public String accessKey = System.getenv("SAUCE_API_KEY")==null ? Settings.getProperty("SAUCE_API_KEY") : System.getenv("SAUCE_API_KEY");
	public String tunnelId = System.getenv("TUNNEL_IDENTIFIER")==null ? Settings.getProperty("TUNNEL_IDENTIFIER") : System.getenv("TUNNEL_IDENTIFIER");
	private ConfigFileReader reader = new ConfigFileReader();
	public WebDriverRegistry() {
		browser= reader.getBrowser();
		environmentType = reader.getEnvironment();
		testPlatform = reader.getTestPlatform();
		headers = reader.getHeaders();
	}

	public WebDriver getDriver() throws MalformedURLException {
		if(driver==null) {
			if(testPlatform.equals("Desktop")) {
				driver=createDesktopDriver();
				driver.manage().window().maximize();
			}
			else if (testPlatform.equals("Mobile")) {
//				driver=createMobileDriver();
			}
			else {
				driver=createDesktopDriver();
				driver.manage().window().maximize();
			}
		}
		return driver;
	}

	private WebDriver createDesktopDriver() throws MalformedURLException {
		switch (environmentType) {
		case "local":
			driver= createLocalDriver();
			break;

		case "saucelab":
			driver= createRemoteDriver();
			break;
		default:
			driver= createLocalDriver();
			break;
		}
		return driver;
	}

	private WebDriver createLocalDriver() {
		testEnvtHeader = System.getProperty("testEnvtHeader")==null? Settings.getProperty("testEnvtHeader") : System.getProperty("testEnvtHeader");
		Boolean jenkins = System.getProperty("JenkinsRun")==null?Boolean.parseBoolean( Settings.getProperty("JenkinsRun")) :Boolean.parseBoolean(System.getProperty("JenkinsRun"));
		switch (browser) {
		case CHROME:
			System.setProperty("webdriver.chrome.driver", Settings.getProperty("ChromeDriverPath"));
			Map<String,Object> preferences = new Hashtable<>();
			preferences.put("download.default_directory", System.getProperty("user.dir")+"\\src\\test\\resources");
			
			ChromeOptions options = new ChromeOptions();
			if(testEnvtHeader!=null) {
				options.addExtensions(new File("crx path"));
			}
			options.addArguments("--disable-web-security");
			options.addArguments("--start-maximized");
			options.addArguments("--disable-gpu");
			options.addArguments("--no-sandbox");
			options.addArguments("user-agent=LDQA");
			//ADD BROWSER MOB PROXY LATER
			if(jenkins) {
				options.setHeadless(true);
			}
			driver = new ChromeDriver(options);
			break;
			
		case FIREFOX:
			System.setProperty("webdriver.gecko.driver", Settings.getProperty("FirefoxDriverPath"));
		default:
			throw new IllegalArgumentException("Browser "+browser.name()+"not supported");
			
		}
		return driver;
	}

	private WebDriver createRemoteDriver() throws MalformedURLException {
		List<Map<String,Object>> sauceDetails = sauceBrowserDataProvider();
		Map<String,Object> sauceBrowserInfo = sauceDetails.get(0);
		String platform =sauceBrowserInfo.get("platform").toString();
		String version =sauceBrowserInfo.get("version").toString();
		String browser =sauceBrowserInfo.get("browser").toString();

		SimpleDateFormat sdf = new SimpleDateFormat("MMddhhmmss");
		String dateAsString = sdf.format(new Date());
		String jobName = "Test"+dateAsString+'_'+platform+'_'+browser+'_'+version;
		WebDriver driver = WebDriverFactory.getDriver(browser,platform,version,username,accessKey,jobName,tunnelId);
		return driver;
	}

	public List<Map<String, Object>> sauceBrowserDataProvider() {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		//
		String testPlatform = readPropertyOrEnv("testPlatform", "Desktop");
		Settings.setProperty("testPlatform", testPlatform);
		String json = readPropertyOrEnv("SAUCE_ONDEMAND_BROWSERS", null);
		if(StringUtils.isNotBlank(json)) {
			JSONArray browsers = new JSONArray(json);
			for(Object obj : browsers) {
				JSONObject jo= new JSONObject(obj);
				HashMap<String, Object> sauceBrowserInfo = new HashMap<>();
				sauceBrowserInfo.put("browser", jo.get("browser"));
				sauceBrowserInfo.put("version", jo.get("version"));
				sauceBrowserInfo.put("platform", jo.get("platform"));
				data.add(sauceBrowserInfo);
			}
		}
		else {
			if(System.getenv("saucebrowser")!=null && System.getenv("saucebrowser").equalsIgnoreCase("IE")) {
				String browserVersion = System.getenv("browserVersion");
				String OsVersion = System.getenv("OSVersion");
				HashMap<String, Object> sauceBrowserInfo = new HashMap<>();
				sauceBrowserInfo.put("browser", "iexplore");
				sauceBrowserInfo.put("version", browserVersion);
				sauceBrowserInfo.put("platform", OsVersion);
				data.add(sauceBrowserInfo);
			}
			else if(System.getenv("saucebrowser")!=null && System.getenv("saucebrowser").equalsIgnoreCase("Safari")) {
				String browserVersion = System.getenv("browserVersion");
				String OsVersion = System.getenv("OSVersion");
				HashMap<String, Object> sauceBrowserInfo = new HashMap<>();
				sauceBrowserInfo.put("browser", "safari");
				sauceBrowserInfo.put("version", browserVersion);
				sauceBrowserInfo.put("platform", OsVersion);
				data.add(sauceBrowserInfo);
			}
			else if(System.getenv("saucebrowser")!=null && System.getenv("saucebrowser").equalsIgnoreCase("MicrosoftEdge")) {
				String browserVersion = System.getenv("browserVersion");
				String OsVersion = System.getenv("OSVersion");
				HashMap<String, Object> sauceBrowserInfo = new HashMap<>();
				sauceBrowserInfo.put("browser", "microsoftedge");
				sauceBrowserInfo.put("version", browserVersion);
				sauceBrowserInfo.put("platform", OsVersion);
				data.add(sauceBrowserInfo);
			}
			else {
				String localBrowsers = Settings.getProperty("LocalBrowser","chrome");
				String browserVersion = Settings.getProperty("version","84");
				String platform = Settings.getProperty("platform");
				HashMap<String, Object> sauceBrowserInfo = new HashMap<>();
				sauceBrowserInfo.put("browser", localBrowsers);
				sauceBrowserInfo.put("version", browserVersion);
				sauceBrowserInfo.put("platform", platform);
				data.add(sauceBrowserInfo);
			}
		}
		return null;
	}

	private String readPropertyOrEnv(String key, String defaultValue) {
		String prop = System.getenv(key);
		if(prop==null)
			prop=Settings.getProperty(key);
		if(prop==null)
			prop=defaultValue;
		return prop;
	}
	
	public WebDriver closeDriver() {
			if(testPlatform.equals("Desktop")) {
				driver.quit();
				registryDesktop.clear();
			}
			else if (testPlatform.equals("Mobile")) {
				driver.quit();
				//stopAppiumServer();
				registryDesktop.clear();
			}
			else {
				driver.quit();
				registryDesktop.clear();
			}
		return driver;
	}

}
