package com.domain.framework;

import com.domain.selenium.pages.GoogleSearchPage;
import com.domain.selenium.pages.HomePage;

/**
 * The purpose ofthis class is to call a Page Method only once and user it across the step definition files.
 * This will avoid instantiation of Pages in case of Multiple Step Definition files
 * @author ME
 *
 */
public class PageRegistry {
	private ScenarioContext scenarioContext;
	private HomePage homePage;
	private GoogleSearchPage googleSearchPage;
	
	private TestContext testContext;
	
	public PageRegistry() {
		System.out.println("===========");
	}
	public PageRegistry(TestContext context) {
		this.testContext = context;
	}
	
	public ScenarioContext getScenarioContext() {
		return (scenarioContext==null)? scenarioContext = new ScenarioContext() : scenarioContext;
	}
	
	public HomePage getHomePage() throws Exception {
		return (homePage==null)? homePage = new HomePage(testContext) : homePage;
	}
	
	public GoogleSearchPage getGoogleSearchPage() throws Exception {
		return (googleSearchPage==null)? googleSearchPage = new GoogleSearchPage(testContext) : googleSearchPage;
	}
	
}
