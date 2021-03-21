/**
 * 
 */
package co.happy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.common.TestReporting.ExtentLogger;

import utilities.auto.ToolBox;

/**
 * @author JoshZhuang
 * this class stores all objects and methods from looka login page
 */
public class PricingPage {

	WebDriver driver;
	//locators
	@FindBy(how=How.XPATH,using="//div[@class='stack s-1a']/a[@href='/pricing/happy-property-pricing']")
	WebElement happypropertyprcing;
	
	
	//Create a class constructor for HomePage Class. this is for cross browser testing. e.g. passing "Chrome".
	public PricingPage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	//login
	public void naviToHPPricing() throws Exception {
		ToolBox.waitFor(happypropertyprcing, 5);
		happypropertyprcing.click();	
		Thread.sleep(2000);	
	}
	
}
