/**
 * 
 */
package com.looka.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.common.TestReporting.ExtentLogger;

import utilities.auto.ToolBox;

/**
 * @author JoshZhuang
 *
 */
public class CommonLocators {

	WebDriver driver;
	
	//locators	
	@FindBy(how=How.XPATH,using="//i[text()='menu']")
	WebElement menubutton;
	
	@FindBy(how=How.XPATH,using="//i[text()='close']")
	WebElement closemenu;
	//i[text()='close']
	
	@FindBy(how=How.XPATH,using="//span[@class='css-25shwt']")
	WebElement usernamelabel;
	
	@FindBy(how=How.XPATH,using="//li[text()='Log Out']")
	WebElement logout;
	
	@FindBy(how=How.XPATH,using="//p[text()='Logo Generator']")
	WebElement logogenerator;
	
	@FindBy(how=How.XPATH,using="//p[text()='Saved logos']")
	WebElement savedlogos;
	
	@FindBy(how=How.XPATH,using="//div[@aria-label='Open Intercom Messenger']")
	WebElement chaticon;
	
	
	
	
	//Create a class constructor for HomePage Class. this is for cross browser testing. e.g. passing "Chrome".
	public CommonLocators(WebDriver driver) {
		this.driver = driver;
	}
	
	//check if user is logged in. return true or false
	public boolean checkUserLogin() throws Exception {	
		if (ToolBox.waitFor(menubutton, 8)) {
			menubutton.click();
			Thread.sleep(2400); //need to wait otherwise click closemenue may fail!			
			if (ToolBox.waitFor(logout, 3)) {
				ExtentLogger.pass("user login successful!");
//				ToolBox.waitFor(closemenu, 3);
				closemenu.click();
				return true;
			} else {
				ExtentLogger.failtestshot("user is NOT logged in!");
				return false;
			}
		}else {
				ExtentLogger.failtestshot("user is NOT logged in!");
				return false;
		}
				
	}
	
	
	//navigate to logogenerator
	public void navigateToGenerator() throws Exception {
		menubutton.click();
		Thread.sleep(1000);
		logogenerator.click();
	}
	
	
	//navigate to savedlogos
	public void navigateToSavedlogos() throws Exception {
		menubutton.click();
		Thread.sleep(2000);
		savedlogos.click();
	}
	
	
	//wait until chaticon shows
	public void waitForChatIcon(int sec) {
		ToolBox.waitFor(chaticon, sec);
	}
	
	//log off current user
	public void logOffToHome() throws Exception {
		
		//log off user
		menubutton.click();
		Thread.sleep(2000);
		logout.click();
		Thread.sleep(2000);
		
		//check if user already logged off
		menubutton.click();
		if (!ToolBox.waitFor(logout, 3)) {
			ExtentLogger.pass("Successfully logged off!");
		}else {
			ExtentLogger.failshot("User is NOT logged off!");
		}
		closemenu.click();
		Thread.sleep(1000);
		
		//navigate to home page
		driver.navigate().to("https://www.looka.com/");
		Thread.sleep(2000);
		if (ToolBox.isAlertPresent()) {
			driver.switchTo().alert().accept();
		}
		
		Thread.sleep(1000);
	}
	
}
