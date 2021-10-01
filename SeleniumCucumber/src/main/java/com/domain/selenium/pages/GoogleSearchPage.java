package com.domain.selenium.pages;

import java.net.MalformedURLException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.domain.framework.Settings;
import com.domain.framework.TestContext;
import com.domain.support.ReusableLibrary;

public class GoogleSearchPage extends ReusableLibrary {

	public GoogleSearchPage(TestContext context) throws Exception {
		super(context);
		this.driver = testContext.getWebDriverRegistry().getDriver();
		this.wait =new WebDriverWait(driver, 30)		;
		PageFactory.initElements(driver, this);
	}
	String applicationUrl = System.getProperty("ApplicationUrl")==null? Settings.getProperty("ApplicationUrl"):  System.getProperty("ApplicationUrl");

	@FindBy(xpath="//input[@title='Search']")
	private WebElement searchTextBox;
	
	@FindBy(xpath="(//input[@value='Google Search'])[1]")
	private WebElement googleSearchBtnInTypeAhead;
	
	@FindBy(xpath="(//input[@value='Google Search'])[2]")
	private WebElement googleSearchBtn;
	
	public void openGoogleSearch() {
		openUrl(applicationUrl);
	}
	
	public void editSearchTextBox(String searchValue) {
		setTextBoxValue(searchTextBox,searchValue);
	}	
	
	public void clickGoogleSearch() {
		clickCustomize(googleSearchBtnInTypeAhead,"Google Search in Type Ahead");
		
	}
}
