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
import com.utils.Consts;

public class SelectQuestionTest {
	private static WebDriver chromeDriver;
	LoginPage loginPage;
	QuestionsPage questionsPage;
	
	@BeforeClass
	public static void setupDriver() {
		System.setProperty("webdriver.chrome.driver", Consts.CHROME_DRIVER_PATH);
		chromeDriver = new ChromeDriver();
	}
	
	@Before
	public void setup() {
		chromeDriver.navigate().to(Consts.APP_URL);
		loginPage = new LoginPage(chromeDriver);
		questionsPage = loginPage.login();
		questionsPage.submitMultipleQuestion();
	}
	
	@Test
	public void testSelectQuestion() {
		assertNotNull(questionsPage.getQuestionItems());
		assertEquals(3, questionsPage.getQuestionItemsLength());
		
		questionsPage.selectQuestionNum(1);
		
		assertNotNull(questionsPage.getSelectedQuestion());
		assertEquals("question title 2", questionsPage.getSelectedQuestionText());
		assertEquals("rgba(255, 195, 0, 1)", questionsPage.getSelectedQuestionColor());
	}
	
	@Test
	public void testSelectedQuestionDetail() {
		assertNotNull(questionsPage.getQuestionItems());
		assertEquals(3, questionsPage.getQuestionItemsLength());
		
		questionsPage.selectQuestionNum(2);
		
		assertNotNull(questionsPage.getSelectedQuestionDetail());
		assertEquals("rgba(255, 195, 0, 1)", questionsPage.getSelectedQuestionDetailColor());
		assertEquals("question title 3", questionsPage.getQuestionDetailTitle());
		assertEquals("question description 3", questionsPage.getQuestionDetailDescription());
	}
	
	@AfterClass
	public static void closeBrowser() {
		chromeDriver.quit();
	}
}
