package eShopLegacy;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.base.Function;

public class eShopLegacyTests {
	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {

		String projectLocation = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver",projectLocation+"\\driver\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();

		htmlReporter = new ExtentHtmlReporter("extent.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Actions actions = new Actions(driver);
	}

	@Test
	public void testShopACQ() throws Exception {
		driver.get("https://www.vodafone.ro/autentificare");
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Nume utilizator'])[2]/following::input[1]")).click();
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Nume utilizator'])[2]/following::input[1]")).clear();
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Nume utilizator'])[2]/following::input[1]")).sendKeys("tahayari");

		driver.findElement(By.id("PortalDesktop_Authenticationlogin-password")).click();
		driver.findElement(By.id("PortalDesktop_Authenticationlogin-password")).clear();
		driver.findElement(By.id("PortalDesktop_Authenticationlogin-password")).sendKeys("vodafone");

		driver.findElement(By.xpath("//button[@id='PortalDesktop_Authenticationauthenticate']")).sendKeys(Keys.ENTER);
		//----End Login 
		
		//----Begin navigating to eShop
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.urlToBe("https://www.vodafone.ro/vfrointegration/appmanager/vfroportal/myvodafone?_nfxr=false&_pageLabel=contul-meu"));
		System.out.println("dashboard URL loaded. Login complete");
		
		driver.get("https://www.vodafone.ro/shop/telefoane");
		System.out.println("Navigating to All phones page");
				
		driver.findElement(By.xpath("//img[@title='Samsung GALAXY J6 2018 4G Dual SIM Negru']")).click();
		System.out.println("Am ajuns in pagina detaliata a produsului");
		
		
		System.out.println("Urmeaza sa dau scroll jos pana la buton");
		//----scroll down to Button
		WebElement button_adauga = driver.findElement(By.xpath("//span[contains(text(),'Adaugă in cos')]"));
		Actions actions = new Actions(driver);
		actions.moveToElement(button_adauga);
		System.out.println("Am dat scroll cu success");
		
		System.out.println("Urmeaza sa dau click pe butonul de Adauga in cos");
		button_adauga.click();
		System.out.println("Am dat click pe Adauga in cos");
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

