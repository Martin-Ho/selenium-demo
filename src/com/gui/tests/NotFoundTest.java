package com.gui.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.gui.pageObjects.NotFoundPage;

public class NotFoundTest {
	private static WebDriver chromeDriver;
	NotFoundPage notFoundPage;
	
	@BeforeClass
	public static void setupDriver() {
		System.setProperty("webdriver.chrome.driver","/Users/cpenarrieta/selenium/chromedriver");
		chromeDriver = new ChromeDriver();
	}
	
	@Before
	public void openBrowser() {
		chromeDriver.navigate().to("http://localhost:3000/");
		notFoundPage = new NotFoundPage(chromeDriver);
	}
	
	@Test
	public void tesInvalidUrl() {
		chromeDriver.navigate().to("http://localhost:3000/invalid_url");
		
		assertEquals("Piazza - 404", notFoundPage.getTitle());
		assertNotNull(notFoundPage.getErrorBox());
		assertEquals("404 - Invalid url", notFoundPage.getErrorBoxText());
		assertEquals("rgba(231, 76, 60, 1)", notFoundPage.getErrorBoxColor());
		
		ScreenshotFactory.getScreenshot("not-found", chromeDriver);
	}
	
	@AfterClass
	public static void closeBrowser() {
		chromeDriver.quit();
	}
}
