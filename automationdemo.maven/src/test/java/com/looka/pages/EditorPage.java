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
public class EditorPage {
	WebDriver driver;
	//locators	
	@FindBy(how=How.XPATH,using="//p[text()='Suggested']")
	WebElement suggested;
	
	@FindBy(how=How.XPATH,using="//button[text()='Download']")
	WebElement download;
	
	
	
	
	//button[text()='Download']
	
	//Create a class constructor for HomePage Class. this is for cross browser testing. e.g. passing "Chrome".
	public EditorPage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	//to get the id of the logo currently being editted
	public String getCurrentLogoID() {
		ToolBox.waitFor(download, 6); //wait until page is loaded
		
		String url = driver.getCurrentUrl(); //ID is in url string
		String logoid = url.replaceAll("\\D+",""); // this is to get rid of all non-digits. so "https://looka.com/editor/62208209" becomes "62208209"
		ExtentLogger.infoshot("Logo saved. ID = "+logoid); //log the ID
		return logoid;
	}
}
