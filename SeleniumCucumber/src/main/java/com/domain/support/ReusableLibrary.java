package com.domain.support;

import java.sql.Timestamp;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.domain.framework.ConfigFileReader;
import com.domain.framework.TestContext;

public class ReusableLibrary extends BaseClass{
//	public WebDriver driver;
//	public WebDriverWait wait;
//	protected TestContext testContext;
	
	public ReusableLibrary(TestContext context) {
		super(context);
	}
	
	ConfigFileReader reader = new ConfigFileReader();
	
	
	public WebElement returnElementIfPresent(WebElement element, String varElementDesc) {
		WebElement wbEle = null;
		
		scrollToView(element);
		wbEle=wait.until(ExpectedConditions.visibilityOf(element));
		
		return wbEle;
		
	}


	public void scrollToView(WebElement element) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true)", element);
		je.executeScript("window.scrollBy(0,-100)", "");
		
	}
	
	public boolean waitForPageLoad() {
		wait.until(new Function<WebDriver, Boolean>() {
			
			public Boolean apply(WebDriver driver) {
				System.out.println("Current Window state :"+ String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
				return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")).equals("complete");
			}
		});
		return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")).equals("complete");
	}
	
	public String generateRandomText() {
		String text ="";
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		text="auto"+timestamp.getTime();
		return text;
	}
	
	public void setTextBoxValue(WebElement element,String elementValue) {
		scrollToView(element);
		element.clear();
		element.sendKeys(elementValue);
	}
	
	public void clickCustomize(WebElement element,String elementDesc) {
		scrollToView(element);
		returnElementIfPresent(element, elementDesc).click();
	}
	
	public void openUrl(String url) {
		driver.get(url);
	}
}
