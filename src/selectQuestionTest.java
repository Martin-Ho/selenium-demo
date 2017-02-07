import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class selectQuestionTest {
	private static WebDriver chromeDriver;
	WebElement title;
	WebElement description;
	WebElement saveButton;
	WebDriverWait wait;
	
	@BeforeClass
	public static void setupDriver() {
		System.setProperty("webdriver.chrome.driver","/Users/cpenarrieta/selenium/chromedriver");
		chromeDriver = new ChromeDriver();
		chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Before
	public void openBrowser() {
		chromeDriver.navigate().to("http://localhost:3000/questions");
		wait = (new WebDriverWait(chromeDriver, 10));
		
		title = chromeDriver.findElement(By.id("titleTextField"));
		description = chromeDriver.findElement(By.id("descriptionTextField"));
		saveButton = chromeDriver.findElement(By.id("saveButton"));
		
		title.sendKeys("question title 1");
		description.sendKeys("description title 1");
		saveButton.click();
		title.sendKeys("question title 2");
		description.sendKeys("description title 2");
		saveButton.click();
		title.sendKeys("question title 3");
		description.sendKeys("description title 3");
		saveButton.click();		
	}
	
	@Test
	public void testSelectQuestion() {
		List<WebElement> questions = chromeDriver.findElements(By.className("question-list-item"));
		assertEquals(3, questions.size());
		
		questions.get(1).click();
		
		wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	WebElement selectedQuestion = d.findElement(By.cssSelector("div[class='question-list-item selected']"));
            	String selectedQuestionColor = "";
            	if (selectedQuestion != null) {
            		selectedQuestionColor = selectedQuestion.getCssValue("background-color");
            	}
            	return selectedQuestion != null && selectedQuestionColor.equals("rgba(255, 195, 0, 1)");
            }
        });
		
		wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	WebElement selectedQuestion = d.findElement(By.cssSelector("div[class='question-detail selected']"));
            	String selectedQuestionColor = "";
            	if (selectedQuestion != null) {
            		selectedQuestionColor = selectedQuestion.getCssValue("background-color");
            	}
            	return selectedQuestion != null && selectedQuestionColor.equals("rgba(255, 195, 0, 1)");
            }
		});
	}

	@AfterClass
	public static void closeBrowser() {
		chromeDriver.quit();
	}
}
