/**
 * 
 */
package com.looka.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.common.TestReporting.ExtentLogger;

import utilities.auto.ToolBox;

/**
 * @author JoshZhuang
 *
 */
public class ExplorePage {

	WebDriver driver;
	//locators	
	@FindBy(how=How.XPATH,using="//h4[contains(text(),'generating some logos')]")
	WebElement generatinglogo;
	
	@FindBy(how=How.XPATH,using="//h4[contains(text(),'Pick a logo')]")
	WebElement picklogo;

		
	//Create a class constructor for HomePage Class. this is for cross browser testing. e.g. passing "Chrome".
	public ExplorePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void checkLogoGenerating(int sec) {
		if (ToolBox.waitFor(generatinglogo, sec)) {
			ExtentLogger.info("Generating logos in progress");
		}else if(ToolBox.waitFor(picklogo, 2)){
			ExtentLogger.info("logos are already generated!");
		}else {
			ExtentLogger.failshot("Not generating logos! Check screenshot!");
		}
	}
	
	public void checkPickLogo(int sec) {
		if (ToolBox.waitFor(picklogo, sec)) {
			ExtentLogger.pass("Logos are generated as expected!");
		}else {
				ExtentLogger.failshot("Logos are not generated yet! Check screenshot!");
		}
	}
	
	public void selectSavedLogo(int index) {
		WebElement logo = this.driver.findElement(By.xpath("//span[@class='css-j5zjwc']/div["+index+"]"));
//		WebElement logo = this.driver.findElement(By.xpath("//span[@class='css-j5zjwc']/div["+index+"]/div[@class='css-of0vry css-1rkhyuy css-k008qs']"));
		ToolBox.waitFor(logo, 10); //the logos take some time to show up
		
		//try javascript!
		Actions actions = new Actions(driver);
		actions.moveToElement(logo).click().perform();
		
		
//		logo.click();
	}
	
	
	//wait for all logos to be loaded on dashboard page - max number applied
	//for first logo, waiting 12s as it might take longer, for subsequent ones waiting 4s max each
	//if no more logo is loaded, return
	public int waitgeneratedlogo(int maxnumber) {

		boolean isloaded; //flag to check if next logo is loaded
		int logocount = 0;
		for (int i = 1; i <= maxnumber; i++) {
			if (i==1) {
				isloaded = ToolBox.waitFor(By.xpath("//span[@class='css-j5zjwc']/div["+i+"]"), 10);
			}else {
				isloaded = ToolBox.waitFor(By.xpath("//span[@class='css-j5zjwc']/div["+i+"]"), 6);
			}
			
			//if image is not loaded, exit loop, return how many logos are loaded
			if (!isloaded) {
				ExtentLogger.info((i-1)+" logos have been loaded on Explore page!");
				logocount = i-1;
				return logocount;
			}
			
			if (i == maxnumber) {
				ExtentLogger.info(i+" or more logos have been loaded on Explore page!");
				logocount = i;
			}
		}
		return logocount;		
		
	}
	
}
