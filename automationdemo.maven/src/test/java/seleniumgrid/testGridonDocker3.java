/**
 * 
 */
package seleniumgrid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

/**
 * @author JoshZhuang
 *
 * the selenium hub in docker is "http://localhost:4546/wd/hub"
 * this is HEADLESS BROWSER testing!!!!!!!!!!!! NO GUI!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 
 */
public class testGridonDocker3 {
	@Test
	public void test() throws MalformedURLException, InterruptedException {
		DesiredCapabilities cap =  new DesiredCapabilities();
		cap.setBrowserName("firefox"); //specify browser. need to make sure chromedriver.exe is phisically on the node
//		cap.setPlatform(Platform.WINDOWS); //specify platform 
		URL huburl = new URL("http://localhost:4546/wd/hub"); //Docker hub address
		
		//Hub will see which node has chrome browser and launch there
		WebDriver driver = new RemoteWebDriver(huburl,cap); //start browser instance on remote driver
		driver.get("https://www.youtube.com/");
		System.out.println ("#######################this is f'king HEADLESS#############################");
		Thread.sleep(3000);
		System.out.println("Title is "+driver.getTitle());
		driver.quit();
	}
}
