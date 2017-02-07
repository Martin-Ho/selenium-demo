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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class newQuestionTest {
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
		
		title = chromeDriver.findElement(By.id("titleTextField"));
		description = chromeDriver.findElement(By.id("descriptionTextField"));
		saveButton = chromeDriver.findElement(By.id("saveButton"));
		
		wait = (new WebDriverWait(chromeDriver, 10));
	}
	
	@Test
	public void testEmptyTitle() {
		description.sendKeys("question description");
		saveButton.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("show-error-message")));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("show-error-message"), "Empty Title"));
		wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	WebElement errorBox = d.findElement(By.className("show-error-message"));
            	String backgroundColor = errorBox.getCssValue("background-color");
                return backgroundColor.equals("rgba(236, 112, 99, 1)");
            }
        });
	}
	
	@Test
	public void testEmptyDescription() {
		title.sendKeys("question title");
		saveButton.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("show-error-message")));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("show-error-message"), "Empty Description"));
		wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	WebElement errorBox = d.findElement(By.className("show-error-message"));
            	String backgroundColor = errorBox.getCssValue("background-color");
                return backgroundColor.equals("rgba(236, 112, 99, 1)");
            }
        });
	}
	
	@Test
	public void testSaveQuestion() {
		title.sendKeys("question title");
		description.sendKeys("description title");
		saveButton.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("question-list-item")));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("question-list-item"), "question title"));
	}
	
	@Test
	public void testMultipleQuestion() {
		title.sendKeys("question title 1");
		description.sendKeys("description title 1");
		saveButton.click();
		
		title.sendKeys("question title 2");
		description.sendKeys("description title 2");
		saveButton.click();
		
		title.sendKeys("question title 3");
		description.sendKeys("description title 3");
		saveButton.click();
		
		List<WebElement> questions = chromeDriver.findElements(By.className("question-list-item"));
		assertEquals(3, questions.size());
	}
	
	@AfterClass
	public static void closeBrowser() {
		chromeDriver.quit();
	}
}
