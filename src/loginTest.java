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

public class loginTest  {
	private static WebDriver chromeDriver;
	WebElement email;
	WebElement password;
	WebDriverWait wait;
	
	@BeforeClass
	public static void setupDriver() {
		System.setProperty("webdriver.chrome.driver","/Users/cpenarrieta/selenium/chromedriver");
		chromeDriver = new ChromeDriver();
	}
	
	@Before
	public void openBrowser() {
		chromeDriver.navigate().to("http://localhost:3000/");
		
		email = chromeDriver.findElement(By.id("emailTextField"));
		password = chromeDriver.findElement(By.id("passwordTextField"));
		
		wait = (new WebDriverWait(chromeDriver, 10));
	}
	
	@Test
	public void testEmptyEmail() {
		password.sendKeys("123456");
		password.submit();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("show-error-message")));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("show-error-message"), "Empty email"));
		wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	WebElement errorBox = d.findElement(By.className("show-error-message"));
            	String backgroundColor = errorBox.getCssValue("background-color");
                return backgroundColor.equals("rgba(236, 112, 99, 1)");
            }
        });
	}
	
	@Test
	public void testEmptypassword() {
		email.sendKeys("admin@ubc.ca");
		password.submit();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("show-error-message")));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("show-error-message"), "Empty password"));
		wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	WebElement errorBox = d.findElement(By.className("show-error-message"));
            	String backgroundColor = errorBox.getCssValue("background-color");
                return backgroundColor.equals("rgba(236, 112, 99, 1)");
            }
        });
	}
	
	@Test
	public void testIncorrectPassword() {
		email.sendKeys("admin@ubc.ca");
		password.sendKeys("wrong password");
		password.submit();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("show-error-message")));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("show-error-message"), "Incorrect email/password"));
		wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	WebElement errorBox = d.findElement(By.className("show-error-message"));
            	String backgroundColor = errorBox.getCssValue("background-color");
                return backgroundColor.equals("rgba(236, 112, 99, 1)");
            }
        });
	}
	
	@Test
	public void testCorrectUsernamePassword() {
		email.sendKeys("admin@ubc.ca");
		password.sendKeys("123456");
		password.submit();
		
		wait.until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver d) {
	            	String url = d.getCurrentUrl();
	                return url.equals("http://localhost:3000/questions");
	            }
	        });
	}
	
	@AfterClass
	public static void closeBrowser() {
		chromeDriver.quit();
	}
}