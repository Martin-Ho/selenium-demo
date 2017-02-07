import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class multiBrowser  {
	private static WebDriver chromeDriver;
	private static WebDriver firefoxDriver;
	private static WebDriver safariDriver;
	private final String chromeColor = "rgba(236, 112, 99, 1)";
	private final String firefoxColor = "rgb(236, 112, 99)";
	private final String safariColor = "rgb(236, 112, 99)";
	String errColor = "";
	WebElement email;
	WebElement password;
	WebElement btn;
	WebDriverWait wait;
	
	@BeforeClass
	public static void setupDriver() {
		System.setProperty("webdriver.chrome.driver","/Users/cpenarrieta/selenium/chromedriver");
		System.setProperty("webdriver.gecko.driver","/Users/cpenarrieta/selenium/geckodriver");
		chromeDriver = new ChromeDriver();
		firefoxDriver = new FirefoxDriver();
		safariDriver = new SafariDriver();
	}
	
	public void before(WebDriver driver, String color) {
		driver.navigate().to("http://localhost:3000/");
		
		email = driver.findElement(By.id("emailTextField"));
		password = driver.findElement(By.id("passwordTextField"));
		btn = driver.findElement(By.id("submitBtn"));
		errColor = color;
		
		wait = (new WebDriverWait(driver, 10));
	}
	
	public void testEmptyEmail() {
		password.sendKeys("123456");
		btn.click();

		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("show-error-message")));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("show-error-message"), "Empty email"));
		wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	WebElement errorBox = d.findElement(By.className("show-error-message"));
            	String backgroundColor = errorBox.getCssValue("background-color");
                return backgroundColor.equals(errColor);
            }
        });
	}
	
	@Test
	public void testEmptyEmailChrome() {
		before(chromeDriver, chromeColor);
		testEmptyEmail();
	}
	
	@Test
	public void testEmptyEmailFirefox() {
		before(firefoxDriver, firefoxColor);
		testEmptyEmail();
	}
	
	@Test
	public void testEmptyEmailSafari() {
		before(safariDriver, safariColor);
		testEmptyEmail();
	}
	
	public void testEmptypassword() {
		email.sendKeys("admin@ubc.ca");
		btn.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("show-error-message")));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("show-error-message"), "Empty password"));
		wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	WebElement errorBox = d.findElement(By.className("show-error-message"));
            	String backgroundColor = errorBox.getCssValue("background-color");
                return backgroundColor.equals(errColor);
            }
        });
	}
	
	@Test
	public void testEmptypasswordChrome() {
		before(chromeDriver, chromeColor);
		testEmptypassword();
	}
	
	@Test
	public void testEmptypasswordFirefox() {
		before(firefoxDriver, firefoxColor);
		testEmptypassword();
	}
	
	@Test
	public void testEmptypasswordSafari() {
		before(safariDriver, safariColor);
		testEmptypassword();
	}
	
	public void testIncorrectPassword() {
		email.sendKeys("admin@ubc.ca");
		password.sendKeys("wrong password");
		btn.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("show-error-message")));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("show-error-message"), "Incorrect email/password"));
		wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	WebElement errorBox = d.findElement(By.className("show-error-message"));
            	String backgroundColor = errorBox.getCssValue("background-color");
                return backgroundColor.equals(errColor);
            }
        });
	}
	
	@Test
	public void testIncorrectPasswordChrome() {
		before(chromeDriver, chromeColor);
		testIncorrectPassword();
	}
	
	@Test
	public void testIncorrectPasswordFirefox() {
		before(firefoxDriver, firefoxColor);
		testIncorrectPassword();
	}
	
	@Test
	public void testIncorrectPasswordSafari() {
		before(safariDriver, safariColor);
		testIncorrectPassword();
	}
	
	public void testCorrectUsernamePassword() {
		email.sendKeys("admin@ubc.ca");
		password.sendKeys("123456");
		btn.click();
		
		wait.until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver d) {
	            	String url = d.getCurrentUrl();
	                return url.equals("http://localhost:3000/questions");
	            }
	        });
	}
	
	@Test
	public void testCorrectUsernamePasswordChrome() {
		before(chromeDriver, chromeColor);
		testCorrectUsernamePassword();
	}
	
	@Test
	public void testCorrectUsernamePasswordFirefox() {
		before(firefoxDriver, firefoxColor);
		testCorrectUsernamePassword();
	}
	
	@Test
	public void testCorrectUsernamePasswordSafari() {
		before(safariDriver, safariColor);
		testCorrectUsernamePassword();
	}
	
	@AfterClass
	public static void closeBrowser() {
		chromeDriver.quit();
		firefoxDriver.quit();
		safariDriver.quit();
	}
}