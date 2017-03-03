package com.gui.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.gui.pageObjects.LoginPage;
import com.gui.pageObjects.QuestionsPage;
import com.utils.Consts;

public class MultiBrowserTest {
	private static WebDriver chromeDriver;
	private static WebDriver firefoxDriver;
	private static WebDriver safariDriver;
	LoginPage loginPage;
	
	@BeforeClass
	public static void setupDriver() {
		System.setProperty("webdriver.chrome.driver", Consts.CHROME_DRIVER_PATH);
		System.setProperty("webdriver.gecko.driver", Consts.FIREFOX_DRIVER_PATH);
		chromeDriver = new ChromeDriver();
		firefoxDriver = new FirefoxDriver();
		safariDriver = new SafariDriver();
	}
	
	public void before(WebDriver driver) {
		driver.navigate().to(Consts.APP_URL);
		loginPage = new LoginPage(driver);
	}
	
	public void testEmptyEmail() {
		loginPage.typePassword("12345");
		loginPage.submitLoginExpectingFailure();
		
		assertNotNull(loginPage.getErrorBox());
		assertEquals("Empty email", loginPage.getErrorBoxText());
	}
	
	public void testEmptyPassword() {
		loginPage.typeEmail("12345");
		loginPage.submitLoginExpectingFailure();
		
		assertNotNull(loginPage.getErrorBox());
		assertEquals("Empty password", loginPage.getErrorBoxText());
	}
	
	public void testCorrectUsernamePassword() {
		loginPage.typeEmail("admin@ubc.ca");
		loginPage.typePassword("123456");

		QuestionsPage questionPage = loginPage.submitLogin();
		assertEquals(Consts.APP_URL + "questions", questionPage.getUrl());
	}
	
	@Test
	public void testEmptyEmailSafari() {
		before(safariDriver);
		testEmptyEmail();
	}
	
	@Test
	public void testEmptyPasswordSafari() {
		before(safariDriver);
		testEmptyPassword();
	}
	
	@Test
	public void testCorrectUsernamePasswordSafari() {
		before(safariDriver);
		testCorrectUsernamePassword();
	}
	
	@Test
	public void testEmptyEmailFirefox() {
		before(firefoxDriver);
		testEmptyEmail();
	}
	
	@Test
	public void testEmptyPasswordFirefox() {
		before(firefoxDriver);
		testEmptyPassword();
	}
	
	@Test
	public void testCorrectUsernamePasswordFirefox() {
		before(firefoxDriver);
		testCorrectUsernamePassword();
	}
	
	@Test
	public void testEmptyEmailChrome() {
		before(chromeDriver);
		testEmptyEmail();
	}
	
	@Test
	public void testEmptyPasswordChrome() {
		before(chromeDriver);
		testEmptyPassword();
	}
	
	@Test
	public void testCorrectUsernamePasswordChrome() {
		before(chromeDriver);
		testCorrectUsernamePassword();
	}
	
	@AfterClass
	public static void closeBrowser() {
		chromeDriver.quit();
		firefoxDriver.quit();
		safariDriver.quit();
	}
}
