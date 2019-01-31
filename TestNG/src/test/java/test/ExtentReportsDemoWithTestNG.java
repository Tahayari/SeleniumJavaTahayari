package test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import pageObjects.AuthentificationPageObjects;
import pageObjects.PostpaidDashboardPageObjects;
import utils.excelUtils;

public class ExtentReportsDemoWithTestNG {

	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	String projectLocation = System.getProperty("user.dir");
	
	private static WebDriver driver = null ;

	@BeforeSuite
	public void setUp() {
		htmlReporter = new ExtentHtmlReporter("extent.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}
	
	@BeforeTest
	public void beforeTest() {
		
		System.setProperty("webdriver.chrome.driver",projectLocation+"\\driver\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();	
	}

	//Login
	@Test
	public void test1() throws IOException {
						
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
	

	@AfterTest
	public void tearDownDriver() {
		driver.close();
		driver.quit();
		System.out.println("Test Completed Successfully");
	}
	
	@AfterSuite
	public void tearDown() {
		extent.flush();
	}

}
