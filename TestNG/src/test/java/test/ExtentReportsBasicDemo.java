package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportsBasicDemo {
	
	private static WebDriver driver = null ;
	
	public static void main(String[] args) {
		
		//start reporters
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReports.html");
		
		//create ExtentReports and attach reporter(s)
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		//creates a toggle for the given test, adds all log events under it
		ExtentTest test1 = extent.createTest("Login Flow", "Login flow from the authentification page");
		
		String projectLocation = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver",projectLocation+"\\driver\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		
		test1.log(Status.INFO,"Starting Test Case");
		driver.get("https://vodafone.ro/autentificare");
		test1.pass("Navigated to auth page");
				
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/form[1]/div[2]/div[1]/input[1]")).sendKeys("tahayari");
		test1.pass("Enter username");
				
		driver.findElement(By.xpath(("//input[@id='PortalDesktop_Authenticationlogin-password']"))).sendKeys("vodafone");
		test1.pass("Enter password");
		
		driver.findElement(By.xpath("//button[@id='PortalDesktop_Authenticationauthenticate']")).click();
		test1.pass("Click the login button");
		
		driver.close();
		driver.quit();
		test1.pass("Close the browser");
		test1.info("Test completed");
		
		//calling flush() will write everything in the log
		extent.flush();
	}

}
