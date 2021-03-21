/**
 * 
 */
package seleniumgrid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

/**
 * @author JoshZhuang
 * To run this test on Selenium Grid
 * Hub will auto assign the execution to a node
 */

//Start the selenium hub and selenium node for this test to work

public class testOnGrid {

	@Test
	public void test() throws MalformedURLException, Exception {
		DesiredCapabilities cap =  new DesiredCapabilities();
		cap.setBrowserName("chrome"); //specify browser. need to make sure chromedriver.exe is phisically on the node
//		cap.setPlatform(Platform.WINDOWS); //specify platform 
		URL huburl = new URL("http://localhost:4546/wd/hub"); //local hub address
		
		//Hub will see which node has chrome browser and launch there
		WebDriver driver = new RemoteWebDriver(huburl,cap); //start browser instance on remote driver
		driver.get("https://www.google.com/");
		System.out.println("Title is "+driver.getTitle());
		Thread.sleep(3000);
		driver.quit();
	}
}
