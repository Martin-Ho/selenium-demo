package com.gui.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
	WebElement emailLocator;
	WebElement passwordLocator;
	WebElement loginBtnLocator;
	WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;

        if (!"http://localhost:3000/".equals(driver.getCurrentUrl())) {
            throw new IllegalStateException("This is not the login page");
        } else {
        	emailLocator = driver.findElement(By.id("emailTextField"));
        	passwordLocator = driver.findElement(By.id("passwordTextField"));
        	loginBtnLocator = driver.findElement(By.id("submitBtn"));
        	
        	wait = (new WebDriverWait(driver, 10));
        }
    }

    public LoginPage typeEmail(String email) {
    	emailLocator.sendKeys(email);
        return this;    
    }

    public LoginPage typePassword(String password) {
    	passwordLocator.sendKeys(password);
        return this;    
    }

    public QuestionsPage submitLogin() { 
    	loginBtnLocator.click();
        return new QuestionsPage(driver);    
    }

    public LoginPage submitLoginExpectingFailure() {
    	loginBtnLocator.click();
        return this;   
    }

    public QuestionsPage loginAs(String email, String password) {
        typeEmail(email);
        typePassword(password);
        return submitLogin();
    }
    
    public QuestionsPage login() {
        typeEmail("admin@ubc.ca");
        typePassword("123456");
        return submitLogin();
    }
    
    public WebElement getErrorBox() {
    	return wait.until(ExpectedConditions.presenceOfElementLocated(By.className("show-error-message")));
    }
    
    public String getErrorBoxText() {
    	WebElement errorBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("show-error-message")));
    	return errorBox.getText();
    }
    
    public String getErrorBoxColor() {
    	WebElement errorBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("show-error-message")));
    	return errorBox.getCssValue("background-color");
    }
    
    public String getUrl() {
    	return driver.getCurrentUrl();
    }
}