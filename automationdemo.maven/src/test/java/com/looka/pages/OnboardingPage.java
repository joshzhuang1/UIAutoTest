/**
 * 
 */
package com.looka.pages;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.common.TestReporting.ExtentLogger;

import utilities.auto.ToolBox;

/**
 * @author JoshZhuang
 * this class stores all objects and methods from looka onboarding page
 */


public class OnboardingPage {

	WebDriver driver;
	//locators	
	@FindBy(how=How.XPATH,using="//i[text()='menu']")
	WebElement menubutton;
	
	@FindBy(how=How.XPATH,using="//input[@id='industry']")
	WebElement industryfield;	
	
	@FindBy(how=How.XPATH,using="//div[@class='css-of0vry css-1smao10 css-k008qs css-1jkp9i7']")
	WebElement logosyoulikeparent;
	
	@FindBy(how=How.XPATH,using="//button[text()='Continue']")
	WebElement continuebutton;
	
	@FindBy(how=How.XPATH,using="//button[text()='Skip']")
	WebElement skipbutton;
	
	@FindBy(how=How.XPATH,using="//input[@id='name']")
	WebElement companyname;	
	
	@FindBy(how=How.XPATH,using="//input[@name='slogan']")
	WebElement sloganfield;	
	
		
	//Create a class constructor for HomePage Class. this is for cross browser testing. e.g. passing "Chrome".
	public OnboardingPage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public void checkOnboarding() throws IOException {
		if (ToolBox.waitFor(industryfield, 3)) {
			ExtentLogger.pass("Landed on onboarding Page");
		}else {
				ExtentLogger.failshot("Did NOT land on onboarding page!");
		}
	}
	
	//On "Pick some logos you like" section: to wait until the indexed logo to show up, and select it 
	public void selectLikedLogo(String index) {
		WebElement logo = this.driver.findElement(By.xpath("//div[@class='css-of0vry css-1smao10 css-k008qs css-1jkp9i7']/div["+index+"]"));
		ToolBox.waitFor(logo, 5); //the logos take some time to show up
		logo.click();
	}
	
	
	//On "Pick colors" section: select color 
	public void selectColor(String color) {
		WebElement colorlabel = this.driver.findElement(By.xpath("//h6[text()='"+color+"']"));
		ToolBox.waitFor(colorlabel, 5); //the logos take some time to show up
		colorlabel.click();
	}
	
	
	//generate a logo using wizard
	public void generateLogo(String industry, String color, String compayname, String slogan) throws Exception {
		industryfield.sendKeys(industry);
		continuebutton.click();
		Thread.sleep(5000);

		//select #3 and #5 logos
		Random rand = new Random();
		int index1 = rand.nextInt(3) + 1;  // 1 to 3
		int index2 = rand.nextInt(3) + 4;  // 4 to 6
		selectLikedLogo(Integer.toString(index1));
		selectLikedLogo(Integer.toString(index2));
		Thread.sleep(2000);
		continuebutton.click();
		Thread.sleep(1500);
		
		//select color
		selectColor(color);
		Thread.sleep(1500);
		continuebutton.click();
		Thread.sleep(1500);
		
		//enter company name and slogan
		companyname.sendKeys(compayname);
		sloganfield.sendKeys(slogan);
		Thread.sleep(1500);
		continuebutton.click();
		Thread.sleep(1500);
		
		//skip element section
		skipbutton.click();
		Thread.sleep(1500);
		
	}
	
	
}
