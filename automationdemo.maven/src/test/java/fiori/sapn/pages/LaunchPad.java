/**
 * 
 */
package fiori.sapn.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.common.TestReporting.ExtentLogger;

import utilities.auto.ToolBox;

/**
 * @author zhuaj1
 *
 */
public class LaunchPad {
	WebDriver driver;
	
	//locators	
	@FindBy(how=How.XPATH,using="//div[contains(@aria-label, 'Request a Project')]")
	WebElement requestproject;
	
	@FindBy(how=How.XPATH,using="//div[contains(@aria-label, 'Track My Projects')]")
	WebElement trackproject;
	//i[text()='close']
	
//	@FindBy(how=How.XPATH,using="//span[@class='css-25shwt']")
//	WebElement usernamelabel;
	
	
	
	//Create a class constructor for HomePage Class. this is for cross browser testing. e.g. passing "Chrome".
	public LaunchPad(WebDriver driver) {
		this.driver = driver;
	}
	
	
	//navigate to request a project
	public void requestProject() {
		ToolBox.waitFor(requestproject, 12); //the logos take some time to show up
		requestproject.click();
	}
	
	
}
