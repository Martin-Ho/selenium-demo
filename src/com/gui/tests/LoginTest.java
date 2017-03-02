package com.gui.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.gui.pageObjects.LoginPage;
import com.gui.pageObjects.QuestionsPage;

public class LoginTest  {
	private static WebDriver chromeDriver;
	LoginPage loginPage;
	
	@BeforeClass
	public static void setupDriver() {
		System.setProperty("webdriver.chrome.driver","/Users/cpenarrieta/selenium/chromedriver");
		chromeDriver = new ChromeDriver();
	}
	
	@Before
	public void openBrowser() {
		chromeDriver.navigate().to("http://localhost:3000/");
		loginPage = new LoginPage(chromeDriver);
	}
	
	@Test
	public void testEmptyEmail() {
		loginPage.typePassword("12345");
		loginPage.submitLoginExpectingFailure();
		
		assertNotNull(loginPage.getErrorBox());
		assertEquals("Empty email", loginPage.getErrorBoxText());
		assertEquals("rgba(236, 112, 99, 1)", loginPage.getErrorBoxColor());

		ScreenshotFactory.getScreenshot("empty-email", chromeDriver);
	}
	
	@Test
	public void testEmptyPassword() {
		loginPage.typeEmail("admin@ubc.ca");
		loginPage.submitLoginExpectingFailure();
		
		assertNotNull(loginPage.getErrorBox());
		assertEquals("Empty password", loginPage.getErrorBoxText());
		assertEquals("rgba(236, 112, 99, 1)", loginPage.getErrorBoxColor());

		ScreenshotFactory.getScreenshot("empty-password", chromeDriver);
	}
	
	@Test
	public void testIncorrectPassword() {
		loginPage.typeEmail("admin@ubc.ca");
		loginPage.typePassword("wrong password");
		loginPage.submitLoginExpectingFailure();

		assertNotNull(loginPage.getErrorBox());
		assertEquals("Incorrect email/password", loginPage.getErrorBoxText());
		assertEquals("rgba(236, 112, 99, 1)", loginPage.getErrorBoxColor());

		ScreenshotFactory.getScreenshot("incorrect-password", chromeDriver);
	}

	@Test
	public void testCorrectUsernamePassword() {
		loginPage.typeEmail("admin@ubc.ca");
		loginPage.typePassword("123456");

		QuestionsPage questionPage = loginPage.submitLogin();
		assertEquals("http://localhost:3000/questions", questionPage.getUrl());

		ScreenshotFactory.getScreenshot("correct-password", chromeDriver);
	}
	
	@AfterClass
	public static void closeBrowser() {
		chromeDriver.quit();
	}
}