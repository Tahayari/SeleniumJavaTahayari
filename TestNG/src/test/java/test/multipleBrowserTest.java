package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import pageObjects.AuthentificationPageObjects;
import pageObjects.PostpaidDashboardPageObjects;
import utils.excelUtils;

import java.io.File;
import java.io.IOException;

public class multipleBrowserTest {


	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	WebDriver driver = null;
	String projectLocation=System.getProperty("user.dir");

	@BeforeSuite
	public void setUp() {
		htmlReporter = new ExtentHtmlReporter("extent.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	@Parameters("browserName")
	@BeforeTest
	public void setup(String browserName){
		System.out.println("Browser name is : "+browserName);

		if(browserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver",projectLocation+"/driver/chromedriver/chromedriver.exe");
			driver = new ChromeDriver();
			System.out.println("Initializing Chrome driver \n");
		}
		else if(browserName.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver","C:\\Users\\dan.hosman\\workspace\\TestNG\\driver\\geckodriver\\geckodriver.exe");
			driver = new FirefoxDriver();
			System.out.println("Initializing Firefox driver \n");
		}
		else if(browserName.equalsIgnoreCase("ie")){
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
	        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	        capabilities.setCapability("ignoreZoomSetting", true);
	        File fil = new File(projectLocation+"/driver/IEDriver/IEDriverServer.exe");
	        System.setProperty("webdriver.ie.driver", fil.getAbsolutePath());
	        WebDriver driver = new InternetExplorerDriver(capabilities);   
			System.out.println("Initializing IE driver \n");
		}
	}

	@Test
	public void login() throws IOException {

		ExtentTest test = extent.createTest("Authentification Login", "Login flow performed from Authentification page");
		AuthentificationPageObjects authPageObj = new AuthentificationPageObjects(driver);
		excelUtils excel = new excelUtils(projectLocation+"/excel/Data.xlsx", "Sheet1");

		driver.get("https://vodafone.ro/autentificare");
		test.pass("Mergi pe pagina de autentificare");

		authPageObj.setUsername(String.valueOf(excelUtils.getCellData(1, 0)));
		test.pass("Enter username");

		authPageObj.setPassword(excelUtils.getCellData(1, 1));
		test.pass("Enter password");

		authPageObj.clickAutentificareButton();
		test.pass("Click the login button");


		PostpaidDashboardPageObjects postDashbObj = new PostpaidDashboardPageObjects(driver);
		//postDashbObj.checkDashboardLogin();

		if(postDashbObj.checkDashboardLogin()){
			test.pass("Login succesuful");
			System.out.println("Dashboardul a fost incarcat cu succes!");
		}
		else test.fail("Login Failed");

		test.log(Status.INFO, "This step shows usage of log(status, details)");
		test.info("This step shows usage of info(details)");
	}

	/*@AfterTest
	public void tearDown(){
		driver.close();
		System.out.println("Test completed successfully");

	} */



}
