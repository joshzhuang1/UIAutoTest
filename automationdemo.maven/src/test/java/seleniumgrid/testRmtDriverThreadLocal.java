/**
 * 
 */
package seleniumgrid;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import utilities.auto.BrowserFactory;
import utilities.auto.DriverFactory;

/**
 * @author JoshZhuang
 *
 */
public class testRmtDriverThreadLocal {

	WebDriver ldriver;
	String browsername = "chrome";
	String url = "https://www.google.com.au";
	String huburl = "http://localhost:4546/wd/hub";
	
	@Test
	public void test() throws Exception {
		
		ldriver = BrowserFactory.initBrowser(browsername,huburl); //init threadlocal instance - recommended!
		Thread.sleep(2000);	
		
		ldriver.manage().window().maximize(); //maximise window
		ldriver.get(url); //navigate to *url*	
		ldriver.get("https://www.google.com/");
		System.out.println("Title is "+ldriver.getTitle());
		Thread.sleep(2000);
		DriverFactory.getInstance().removeDriver();
	}
	
	
}
