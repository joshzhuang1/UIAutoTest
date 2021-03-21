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
 * this class stores all objects and methods from looka login page
 */
public class LoginPage {

	WebDriver driver;
	//locators
	@FindBy(how=How.XPATH,using="//a[text()='Log in']")
	WebElement loginlabel;
	
	@FindBy(how=How.ID,using="login-email")
	WebElement usernamefield;
	
	@FindBy(how=How.ID,using="login-password")
	WebElement passwordfield;
	
	@FindBy(how=How.XPATH,using="//button[contains(text(),'Sign In')]")
	WebElement signinbutton;
	
	@FindBy(how=How.XPATH,using="//p[text()='Incorrect password']")
	WebElement wrongpwlabel;
	
	@FindBy(how=How.XPATH,using="//i[text()='menu']")
	WebElement menubutton;
	
	
	//Create a class constructor for HomePage Class. this is for cross browser testing. e.g. passing "Chrome".
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	//login
	public void login(String username,String password) throws Exception {
		ExtentLogger.info("Credential - "+username+" : ***************");
		loginlabel.click();
		Thread.sleep(1000);
		usernamefield.sendKeys(username);
		passwordfield.sendKeys(password);
		Thread.sleep(1000);
		signinbutton.click();		
	}

	//check if login button exists
	public boolean checkLoginLabel(int sec) {
//		String url = driver.getCurrentUrl(); 	
		if (ToolBox.waitFor(loginlabel, sec)) {
			ExtentLogger.pass("Login button displays!");
			return true;
		}else {
				ExtentLogger.failshot("Login button does NOT display!");
				return false;
		}
	}
	
}
