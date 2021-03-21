package com.tictoc.pages;

import java.io.IOException;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.common.TestReporting.ExtentLogger;

import utilities.auto.BrowserFactory;
import utilities.auto.ToolBox;

/**
 * @author JoshZhuang
 * This class stores all locators and methods of refinance calculater page, using page factory. https://tictoc.com.au/calculators/refinance-calculator
 */

public class RefiCalcPageFactory {

	WebDriver driver;
	//locators
	@FindBy(how=How.XPATH,using="//input[@min='50000']")
	WebElement loanamount;
	
	@FindBy(how=How.XPATH,using="//input[@min='0.01']")
	WebElement currentrate;
	
	@FindBy(how=How.XPATH,using="//input[@min='1']")
	WebElement remainingyears;
	
	@FindBy(how=How.XPATH,using="//span[text()='Calculate my savings']")
	WebElement calcbutton;
	
	@FindBy(how=How.XPATH,using="//h2[contains(text(), 'Well done you')]")
	WebElement welldonelabel;
	
	@FindBy(how=How.XPATH,using="//h2[contains(text(), 'Hurrah!')]")
	WebElement Hurrahlabel;
	
	
	
	//Create a class constructor for HomePage Class. this is for cross browser testing. e.g. passing "Chrome".
	public RefiCalcPageFactory(WebDriver driver) {
		this.driver = driver;
	}
	
	
	
	//enter amount, rate, and years, then click calculate button, compare with break even rate
	public void CalcRefiSavings(String inputloan, String inputrate, String inputyears, String breakevenrate) throws InterruptedException, IOException {
			
		//Overwrite value in the fields - need to send control+a first
		loanamount.click();
		Thread.sleep(700);
		loanamount.sendKeys(Keys.CONTROL + "a");
		Thread.sleep(1000);
		loanamount.sendKeys(inputloan);
		Thread.sleep(700);
		
		currentrate.click();
		Thread.sleep(700);
		currentrate.sendKeys(Keys.CONTROL + "a");
		Thread.sleep(1000);
		currentrate.sendKeys(inputrate);
		Thread.sleep(700);
		
		remainingyears.click();
		Thread.sleep(700);
		remainingyears.sendKeys(Keys.CONTROL + "a");
		Thread.sleep(1000);
		remainingyears.sendKeys(inputyears);
		Thread.sleep(700);
		
		calcbutton.click();
		Thread.sleep(1000);

		
		//depending on current rate and breakevenrate, it should display welldone or hurray label accordingly.
		if (Float.parseFloat(inputrate) <= Float.parseFloat(breakevenrate)){
			
			ToolBox.waitFor(welldonelabel,5);//explicit wait 3 seconds for welldone label
			
			if(ToolBox.objectExists(welldonelabel)){ //Welldone label should display, when current rate is lower than breakeven
		    	System.out.println("Passed! welldone label displays as expected");
		    	ExtentLogger.pass("welldone label webelement is displayed!");
		    }else{
		    	System.out.println("Failed! welldone label is NOT displayed!");
		    	ExtentLogger.failshot("welldone label webelement is NOT displayed!");
		    }
		
		}else {
			
			ToolBox.waitFor(Hurrahlabel,5); //explicit wait 3 seconds for hurrah label
			
			if(ToolBox.objectExists(Hurrahlabel)){ //Hurrah label should display, when current rate is higher than breakeven
		    	System.out.println("Passed! Hurrah label displays as expected");
		    	ExtentLogger.pass("Hurrah label webelement is displayed!");
		    }else{
		    	System.out.println("Failed! Hurrah label is NOT displayed!");
		    	ExtentLogger.failshot("Hurrah label webelement is NOT displayed!");
		    }
		}
	}
}
