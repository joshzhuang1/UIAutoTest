/**
 * 
 */
package utilities.auto;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author JoshZhuang
 *
 * This class provides 2 ways to init browser instance
 *  1. Local webdriver:  launch browser as ThreadLocal instance locally. Parallel testing is supported.
 *  2. Remote webdriver: launch browser as ThreadLocal instance remotely, i.e. through selenium hub. Parallel testing is supported.
 * 
 */
public class BrowserFactory {

	static WebDriver driver;

	/**
	 * init browser locally
	 */
	//init a threadlocal browser instance locally ---- this caters for multi-threading. MAGIC.
	public static WebDriver initBrowser(String browserName) {
		driver = createBrowserInstance(browserName);      // create browser instance based on browser name
		
		DriverFactory.getInstance().setDriver(driver);    //to set browser instance as threadlocal for multi-threading
		driver = DriverFactory.getInstance().getDriver();  //now driver becomes "threadlocal instance", so that multi-threading is possible		
		return driver;										// return driver
	}
	
	//create webdriver instance locally depending on browser
	public static WebDriver createBrowserInstance(String browserName) {
		if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver(); //launch firefox
		}
		
		else if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			driver = new ChromeDriver(); //launch chrome
		}
		
		else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");
			driver = new EdgeDriver(); //launch edge
		}
			
		return driver;
	}
	

	
	/**
	 * init browser remotely
	 */
	//when you pass huburlstring to this method, Browser is then initialised remotely i.e. through selenium hub = huburlstring 
	public static WebDriver initBrowser(String browserName,String huburlstring) throws IOException {
		driver = createBrowserInstance(browserName,huburlstring);      // create browser instance based on browser name
		
		DriverFactory.getInstance().setDriver(driver);    //to set browser instance as threadlocal for multi-threading
		driver = DriverFactory.getInstance().getDriver();  //now driver becomes "threadlocal instance", so that multi-threading is possible		
		return driver;										// return driver
	}
	
	
	//create webdriver instance remotely i.e. through selenium hub = huburlstring 
	public static WebDriver createBrowserInstance(String browserName,String huburlstring) throws IOException {
		DesiredCapabilities cap =  new DesiredCapabilities();
		
		if (browserName.equalsIgnoreCase("firefox")) {	
			cap.setBrowserName("firefox");		
		}
		
		else if (browserName.equalsIgnoreCase("chrome")) {			
			cap.setBrowserName("chrome"); 		
		}
		
		else if (browserName.equalsIgnoreCase("edge")) {
			cap.setBrowserName("MicrosoftEdge");	
		}
		
//		else if (browserName.equalsIgnoreCase("opera")) {
//			cap.setBrowserName("opera");
//		}
		
//		cap.setPlatform(Platform.WINDOWS); //specify platform 
		URL huburl = new URL(huburlstring); //selenium hub address
		
		//start browser instance on remotewebdriver
		driver = new RemoteWebDriver(huburl,cap); 
		
		return driver;
	}
	
	

	/**
	 * navigate to url
	 */
	public static void launchURL(String url) {
		driver.manage().window().maximize(); //maximise window
		driver.get(url); //navigate to *url*
	}
	
}
	
	
	
	
	
	



