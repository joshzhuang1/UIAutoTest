/**
 * 
 */
package utilities.auto;

import org.openqa.selenium.WebDriver;

/**
 * @author JoshZhuang
 * driver factory is using ThreadLocal to support multi-threading for parallel testing
 */


public class DriverFactory {

	//constructor
	private DriverFactory(){
		//do nothing here. Do NOT allow to init this class from outside.
	}
	
	
	private static DriverFactory instance = new DriverFactory(); // create DriverFactory instance
	
	
	public static DriverFactory getInstance() {
		return instance;
	}
	
	
	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>(); //treadlocal instance for webdriver - this is for multi-threading testing!!!!!
	
	
	public WebDriver getDriver() {   // call this method to get the driver object and launch browser
		return driver.get();        //this get() method gets current webdriver instance. this method is from ThreadLocal!!!		
	}
	
	
	public void setDriver(WebDriver driverParam) {   //call this to set driver object
		driver.set(driverParam);
	}
	
	
	public void removeDriver() {    //quit driver and close browser
		driver.get().quit();
		driver.remove();
	}
}

