package test;

import java.util.concurrent.TimeUnit;

import org.testng.TestNG;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.base.Function;

public class KatalonDemo {

	public class LoginPostpaid {
		private WebDriver driver;
		private String baseUrl=null;
		private boolean acceptNextAlert = true;
		private StringBuffer verificationErrors = new StringBuffer();
		ExtentHtmlReporter htmlReporter;
		ExtentReports extent;



		@BeforeClass(alwaysRun = true)
		public void setUp() throws Exception {

			String projectLocation = System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver",projectLocation+"\\driver\\chromedriver\\chromedriver.exe");
			driver = new ChromeDriver();
			//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			htmlReporter = new ExtentHtmlReporter("extent.html");
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);


		}

		@Test(groups="regression")
		public void testUntitledTestCase() throws Exception {

			ExtentTest test = extent.createTest("Authentification Login", "Login flow performed from Authentification page");

			driver.get("https://www.vodafone.ro/autentificare");
			test.pass("Navigate to authentification page");
			test.fail("Authentification page error");

			driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Nume utilizator'])[2]/following::input[1]")).click();
			driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Nume utilizator'])[2]/following::input[1]")).clear();
			driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Nume utilizator'])[2]/following::input[1]")).sendKeys("tahayari");
			test.pass("Enter username");
			test.fail("Enter username failed");

			driver.findElement(By.id("PortalDesktop_Authenticationlogin-password")).clear();
			driver.findElement(By.id("PortalDesktop_Authenticationlogin-password")).sendKeys("vodafone");
			test.pass("Enter password");
			test.fail("Enter password failed");

			System.out.println("Urmeaza sa dau click pe submit");
			driver.findElement(By.xpath("//button[@id='PortalDesktop_Authenticationauthenticate']")).sendKeys(Keys.ENTER);
			test.pass("Clicked on Authenticate");

			//Wait until Vezi mai mult button from Asistenta footer is visible/clickable;
			//Explicitwait
			//WebDriverWait wait = new WebDriverWait(driver, 20);
			//WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Bar/Unbar')]")));			

			//FluentWait
			Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
					.withTimeout(20,  TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);

			WebElement foo = fluentWait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					WebElement linkElement = driver.findElement(By.xpath("//a[contains(text(),'Bar/Unbar')]"));

					if(linkElement.isEnabled()){
						System.out.println("Element Found!");
					}
					return linkElement;
				}
			});
		}


		@AfterClass(alwaysRun = true)
		public void tearDown() throws Exception {
			driver.quit();
			String verificationErrorString = verificationErrors.toString();
			if (!"".equals(verificationErrorString)) {
				fail(verificationErrorString);
			}
		}

		private boolean isElementPresent(By by) {
			try {
				driver.findElement(by);
				return true;
			} catch (NoSuchElementException e) {
				return false;
			}
		}

		private boolean isAlertPresent() {
			try {
				driver.switchTo().alert();
				return true;
			} catch (NoAlertPresentException e) {
				return false;
			}
		}

		private String closeAlertAndGetItsText() {
			try {
				Alert alert = driver.switchTo().alert();
				String alertText = alert.getText();
				if (acceptNextAlert) {
					alert.accept();
				} else {
					alert.dismiss();
				}
				return alertText;
			} finally {
				acceptNextAlert = true;
			}
		}
	}
}
