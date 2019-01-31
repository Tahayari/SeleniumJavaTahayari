package test;


import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestFirefox {

	public static void main(String[] args) {
		String projectLocation = System.getProperty("user.dir");
		//System.setProperty("webdriver.firefox.driver","C:\\Users\\dan.hosman\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        capabilities.setCapability("ignoreZoomSetting", true);
        File fil = new File(projectLocation+"/driver/IEDriver/IEDriverServer.exe");
        System.setProperty("webdriver.ie.driver", fil.getAbsolutePath());
        WebDriver driver = new InternetExplorerDriver(capabilities);   
				
        driver.get("http://www.google.com");
		

	}

}
