package com.domain.steps;

import com.domain.framework.TestContext;
import com.domain.selenium.pages.GoogleSearchPage;
import com.domain.selenium.pages.HomePage;
import com.domain.support.ReusableLibrary;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GoogleSearchSteps extends ReusableLibrary {

	HomePage homePage;
	GoogleSearchPage googleSearchPage;
	public GoogleSearchSteps(TestContext context) throws Exception {
		super(context);
		homePage=testContext.getPageRegistry().getHomePage();
		googleSearchPage=testContext.getPageRegistry().getGoogleSearchPage();
	}
	
	@Given("Google Search Page is displayed")
	public void google_searchPage_displayed() {
		googleSearchPage.openGoogleSearch();
	}
	
	@When("I enter {string} in search text box")
	public void enterValue_inSearch(String searchValue) {
		googleSearchPage.editSearchTextBox(searchValue);
	}
	
	@Then("I click on Goole Search Button in Type Ahead Section")
	public void click_GoogleSearch_Button() {
		googleSearchPage.clickGoogleSearch();
	}

}
