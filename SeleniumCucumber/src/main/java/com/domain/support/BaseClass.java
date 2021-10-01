package com.domain.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.domain.framework.TestContext;

public class BaseClass {
	public WebDriver driver;
	public WebDriverWait wait;
	protected TestContext testContext;
	
	public BaseClass(TestContext context) {
		this.testContext=context;
	}
}
