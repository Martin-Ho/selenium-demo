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

public class NewQuestionTest {
	private static WebDriver chromeDriver;
	LoginPage loginPage;
	QuestionsPage questionsPage;
	
	@BeforeClass
	public static void setupDriver() {
		System.setProperty("webdriver.chrome.driver","/Users/cpenarrieta/selenium/chromedriver");
		chromeDriver = new ChromeDriver();
	}
	
	@Before
	public void openBrowser() {
		chromeDriver.navigate().to("http://localhost:3000/");
		loginPage = new LoginPage(chromeDriver);
		questionsPage = loginPage.login();
	}
	
	@Test
	public void testEmptyTitle() {
		questionsPage.typeDescription("question description");
		questionsPage.submitQuestion();
		
		assertNotNull(questionsPage.getErrorBox());
		assertEquals("Empty Title", questionsPage.getErrorBoxText());
		assertEquals("rgba(236, 112, 99, 1)", questionsPage.getErrorBoxColor());
	}
	
	@Test
	public void testEmptyDescription() {
		questionsPage.typeTitle("question title");
		questionsPage.submitQuestion();
		
		assertNotNull(questionsPage.getErrorBox());
		assertEquals("Empty Description", questionsPage.getErrorBoxText());
		assertEquals("rgba(236, 112, 99, 1)", questionsPage.getErrorBoxColor());
	}
	
	@Test
	public void testSaveQuestion() {
		questionsPage.typeTitle("question title");
		questionsPage.typeDescription("question description");
		questionsPage.submitQuestion();
		
		assertNotNull(questionsPage.getQuestionItem());
		assertEquals("question title", questionsPage.getQuestionItemText());
	}
	
	@Test
	public void testMultipleQuestion() {
		questionsPage.typeTitle("question title 1");
		questionsPage.typeDescription("question description 1");
		questionsPage.submitQuestion();
		
		questionsPage.typeTitle("question title 2");
		questionsPage.typeDescription("question description 2");
		questionsPage.submitQuestion();
		
		questionsPage.typeTitle("question title 3");
		questionsPage.typeDescription("question description 3");
		questionsPage.submitQuestion();
		
		assertNotNull(questionsPage.getQuestionItems());
		assertEquals(3, questionsPage.getQuestionItemsLength());
	}
	
	@AfterClass
	public static void closeBrowser() {
		chromeDriver.quit();
	}
}
