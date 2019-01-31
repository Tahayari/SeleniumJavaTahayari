package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.AuthentificationPageObjects;

public class Iteratie3 {
	
	WebDriver driver = null;
	
	@BeforeTest
	public void setUp() {
		String projectLocation = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver",projectLocation+"/driver/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Test
	public void authTest() {
		
		AuthentificationPageObjects authPageObj = new AuthentificationPageObjects(driver);

		driver.get("https://portal-pet.vodafone.ro/autentificare");
		
		authPageObj.setUsername("tahayari");
		authPageObj.setPassword("vodafone1");
		authPageObj.clickAutentificareButton();
		
	}
	
	@AfterTest
	public void tearDown () {
		driver.close();
		driver.quit();
		System.out.println("Test Completed Successfully");
	}
}
