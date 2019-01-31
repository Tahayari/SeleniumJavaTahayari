package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class WaitDemo {

	public static void main(String[] args) {
		seleniumWaits();
	}			

	public static void seleniumWaits(){

		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver",projectPath + "\\driver\\chromedriver\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();	


		driver.get("https://portal-pet.vodafone.ro/autentificare");


		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Nume utilizator'])[2]/following::input[1]")).click();
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Nume utilizator'])[2]/following::input[1]")).clear();
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Nume utilizator'])[2]/following::input[1]")).sendKeys("tahayari");

		driver.findElement(By.id("PortalDesktop_Authenticationlogin-password")).click();
		driver.findElement(By.id("PortalDesktop_Authenticationlogin-password")).clear();
		driver.findElement(By.id("PortalDesktop_Authenticationlogin-password")).sendKeys("vodafone1");

		//implicit WAIT. Default is 250 ms. Used for the ENTIRE session
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


		driver.findElement(By.xpath("//label[@for='loginCheckbox_PortalDesktop_Authentication']")).click();
		driver.findElement(By.xpath("//button[@id='PortalDesktop_Authenticationauthenticate']")).sendKeys(Keys.ENTER);
		//----End Login 

		//Begin to count dashboard load time
		long start = System.currentTimeMillis();

		//Explicit WAIT. It waits only for CERTAIN elements or until the specified condition is met
		//WebDriverWait wait = new WebDriverWait(driver, 30);
		//WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/a[1]")));

		// FLUENT WAIT. Waiting 30 seconds for an element to be present on the page, checking
		// for its presence once every 5 seconds.
		@SuppressWarnings("deprecation")
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		.withTimeout(30, TimeUnit.SECONDS)
		.pollingEvery(5, TimeUnit.SECONDS)
		.ignoring(NoSuchElementException.class);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() 
				{
			public WebElement apply(WebDriver driver) {
				WebElement linkElement = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/a[1]"));

				if(linkElement.isEnabled()){
					System.out.println("Element found");

				}
				else System.out.println("Element not found");
				return linkElement;
			}

				});

		//Stop counting Dashboard loading time 
		long finish = System.currentTimeMillis();

		//WebElement element1 = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/a[1]"));
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		float totalTime = (finish - start)/3600; 
		if(element.isDisplayed()) System.out.println("Dashboardul s-a incarcat cu succes in "+totalTime+" secunde");
		else {
			System.out.println("Dashboardul NU s-a incarcat cu succes!");
		}

		driver.close();
		driver.quit();
	}




}
