/**
 * 
 */
package com.tictoc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * @author JoshZhuang
 * This class stores all locators and methods of TicToc homepage
 * Add another line of comment
 */
public class HomePage {

	WebDriver driver;
	
	//locators
	By calculators = By.xpath("//span[text()='Calculators']");
	By reficalc = By.xpath("//span[text()='Refinance calculator']");
	
	//Create a class constructor for HomePage Class. to init the class with webdriver instance (e.g. chrome)
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	//Navigate to refinance calculator
	public void GotoRefiCalc() throws InterruptedException {
		//mouse hover over
		Actions action = new Actions(driver); //init Actions class
		WebElement calcdropdown = driver.findElement(calculators); //Specify webelement
		action.moveToElement(calcdropdown).perform(); //Find the element and hover over
		Thread.sleep(1000);
		
		//Click the refinance calculator
		driver.findElement(reficalc).click();
		Thread.sleep(1000);
	}
	
}
