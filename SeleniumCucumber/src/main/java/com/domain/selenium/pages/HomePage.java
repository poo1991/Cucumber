package com.domain.selenium.pages;

import java.net.MalformedURLException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.domain.framework.TestContext;
import com.domain.support.ReusableLibrary;

public class HomePage extends ReusableLibrary {

	public HomePage(TestContext context) throws Exception {
		super(context);
		this.driver = testContext.getWebDriverRegistry().getDriver();
		this.wait =new WebDriverWait(driver, 30)		;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[@title='Search']")
	private WebElement googleSearchTextBox;
}
