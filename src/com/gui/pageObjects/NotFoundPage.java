package com.gui.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotFoundPage {
	private final WebDriver driver;
	WebDriverWait wait;

    public NotFoundPage(WebDriver driver) {
        this.driver = driver;

        wait = (new WebDriverWait(driver, 10));
    }
    
    public WebElement getErrorBox() {
    	return wait.until(ExpectedConditions.presenceOfElementLocated(By.className("invalid-url-content")));
    }
    
    public String getErrorBoxText() {
    	WebElement errorBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("invalid-url-content")));
    	return errorBox.getText();
    }
    
    public String getErrorBoxColor() {
    	WebElement errorBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("invalid-url-content")));
    	return errorBox.getCssValue("background-color");
    }

	public String getUrl() {
    	return driver.getCurrentUrl();
    }
	
	public String getTitle() {
    	return driver.getTitle();
    }
}
