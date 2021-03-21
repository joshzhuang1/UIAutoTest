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
public class HomePage {

	WebDriver driver;
	//locators
	@FindBy(how=How.XPATH,using="//DIV[@class='nav_item-text' and text()='Pricing']")
	WebElement pricing;
	
	@FindBy(how=How.XPATH,using="//figure[@class='home_intro']")
	WebElement homeintro;
	
	//Create a class constructor for HomePage Class. this is for cross browser testing. e.g. passing "Chrome".
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	//login
	public void naviToPricing() throws Exception {
		ToolBox.waitFor(homeintro, 5);
		pricing.click();	
		Thread.sleep(2000);	
	}
	
}
