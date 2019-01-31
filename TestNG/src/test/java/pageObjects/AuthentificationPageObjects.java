package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class AuthentificationPageObjects {

	WebDriver driver = null ;

	By textbox_username = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/form[1]/div[2]/div[1]/input[1]");
	By textbox_password = By.xpath("//input[@id='PortalDesktop_Authenticationlogin-password']");
	By button_autentificare = By.xpath("//button[@id='PortalDesktop_Authenticationauthenticate']");

	public AuthentificationPageObjects (WebDriver driver) {
		this.driver=driver;
	}

	public void setUsername (String username) {
		driver.findElement(textbox_username).sendKeys(username);
	}

	public void setPassword (String pass) {
		driver.findElement(textbox_password).sendKeys(pass);
	}

	public void clickAutentificareButton () {
		driver.findElement(button_autentificare).sendKeys(Keys.ENTER);;
	}
}