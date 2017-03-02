import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;

public class notFoundTest {
	private static WebDriver chromeDriver;
	WebDriverWait wait;
	
	@BeforeClass
	public static void setupDriver() {
		System.setProperty("webdriver.chrome.driver","/Users/cpenarrieta/selenium/chromedriver");
		chromeDriver = new ChromeDriver();
		chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Before
	public void openBrowser() {
		wait = (new WebDriverWait(chromeDriver, 10));
	}
	
	@Test
	public void tesInvalidUrl() {
		chromeDriver.navigate().to("http://localhost:3000/invalid_url");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("invalid-url-content")));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("invalid-url-content"), "404 - Invalid url"));
		wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	WebElement errorBox = d.findElement(By.className("invalid-url-content"));
            	String backgroundColor = errorBox.getCssValue("background-color");
                return backgroundColor.equals("rgba(231, 76, 60, 1)");
            }
        });
		
		getscreenshot("not-found");
	}

	public void getscreenshot(String filename) {
		try {
			File scrFile = ((TakesScreenshot)chromeDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("/Users/cpenarrieta/github/selenium-demo/" + filename + ".png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
	
	@AfterClass
	public static void closeBrowser() {
		chromeDriver.quit();
	}
}
