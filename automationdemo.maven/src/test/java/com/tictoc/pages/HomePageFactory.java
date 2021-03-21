/**
 * 
 */
package com.tictoc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import utilities.auto.BrowserFactory;
import utilities.auto.ToolBox;

/**
 * 
 * @author JoshZhuang
 * this class stores all objects and methods from TicToc homepage
 * 
 */
public class HomePageFactory {

	
	WebDriver driver;
	//locators
	@FindBy(how=How.XPATH,using="//span[text()='Calculators']")
	WebElement calculators;
	
	@FindBy(how=How.XPATH,using="//span[text()='Refinance calculator']")
	WebElement reficalc;
	
	@FindBy(how=How.XPATH,using="//img[@class='header__logo__main']")
	WebElement tictoclogo;

	@FindBy(how=How.XPATH,using="//span[@class='icon far fa-angle-down']")
	WebElement downarrow;

	//Create a class constructor for HomePage Class. this is for cross browser testing. e.g. passing "Chrome".
	public HomePageFactory(WebDriver driver) {
		this.driver = driver;
	}
	
	
	//Navigate to refinance calculator
	public void GotoRefiCalc() throws InterruptedException {
		//mouse hover over
		Actions action = new Actions(driver); //init Actions class
		WebElement calcdropdown = calculators; //Specify webelement
		action.moveToElement(calcdropdown).perform(); //Find the element and hover over
		Thread.sleep(1000);
		
		try {
			reficalc.click(); //Click the refinance calculator
		} catch (Exception e) {
			calculators.click();  //in firefox hover over doesn't pop up the drop down list, so use click instead
			Thread.sleep(1000);
			reficalc.click(); 
		}
		Thread.sleep(1000);
	}
	
	
	//Navigate to home page
	public void gotoHomePage() throws InterruptedException{
		tictoclogo.click();
		Thread.sleep(4000);
	}
	
//	//testing method
//	public void testingMethod(WebElement webelement, int sec){
//		BrowserFactory.waitforObject(webelement,sec);
//	}
	
}







