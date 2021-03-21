/**
 * 
 */
package com.tictoc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

/**
 * @author JoshZhuang
 * This class stores all locators and methods of refinance calculater page https://tictoc.com.au/calculators/refinance-calculator
 */
public class RefiCalcPage {

	WebDriver driver;
	
	//locators
	By loanamount = By.xpath("//input[@min='50000']");
	By currentrate = By.xpath("//input[@min='0.01']");
	By remainingyears = By.xpath("//input[@min='1']");
	By calcbutton = By.xpath("//span[text()='Calculate my savings']");
	By welldonelabel = By.xpath("//h2[contains(text(), 'Well done you')]");
	By Hurrahlabel = By.xpath("//h2[contains(text(), 'Hurrah!')]");
	
	
	//Create a class constructor for HomePage Class. this is for cross browser testing. e.g. passing "Chrome".
	public RefiCalcPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//enter amount, rate, and years, then click calculate button
	public void CalcRefiSavings(String inputloan, String inputrate, String inputyears, String breakevenrate) throws InterruptedException {
		
		//scroll down a bit
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(0,500)");
	
		//Overwrite value in the fields - need to send control+a first
		driver.findElement(loanamount).click();
		Thread.sleep(1000);
		driver.findElement(loanamount).sendKeys(Keys.CONTROL + "a");
		Thread.sleep(1000);
		driver.findElement(loanamount).sendKeys(inputloan);
		Thread.sleep(1000);
		
		driver.findElement(currentrate).click();
		Thread.sleep(1000);
		driver.findElement(currentrate).sendKeys(Keys.CONTROL + "a");
		Thread.sleep(1000);
		driver.findElement(currentrate).sendKeys(inputrate);
		Thread.sleep(1000);
		
		driver.findElement(remainingyears).click();
		Thread.sleep(1000);
		driver.findElement(remainingyears).sendKeys(Keys.CONTROL + "a");
		Thread.sleep(1000);
		driver.findElement(remainingyears).sendKeys(inputyears);
		Thread.sleep(1000);
		
		driver.findElement(calcbutton).click();
		Thread.sleep(1000);
		
		//depending on current rate and breakevenrate, it should display welldone or hurray label accordingly.
		if (Float.parseFloat(inputrate) <= Float.parseFloat(breakevenrate)){
		    if(!driver.findElements(welldonelabel).isEmpty()){ //Welldone label displays
		    	System.out.println("Passed! welldonelabel displays as expected");
		    }else{
		    	System.out.println("Failed! welldonelabel is NOT displayed!");
		    }
		}else {
			if(!driver.findElements(Hurrahlabel).isEmpty()){
		    	System.out.println("Passed! Hurrahlabel displays as expected");
		    }else{
		    	System.out.println("Failed! Hurrahlabel is NOT displayed!");
		    }
		}
	}
	
}

