package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PostpaidDashboardPageObjects {
	
	WebDriver driver = null ;

	By text_CostControl = By.xpath("//span[contains(text(),'Cost control')]");

	public PostpaidDashboardPageObjects (WebDriver driver) {
		this.driver=driver;
	}

	public boolean checkDashboardLogin(){

		String dashbURL = "https://www.vodafone.ro/vfrointegration/appmanager/vfroportal/myvodafone?_nfxr=false&_pageLabel=contul-meu";
		WebDriverWait wait=new WebDriverWait(driver, 20);
		WebElement dan = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/h3[1]/span[1]")));
		return dashbURL.equals(driver.getCurrentUrl().toString());

	}
}
