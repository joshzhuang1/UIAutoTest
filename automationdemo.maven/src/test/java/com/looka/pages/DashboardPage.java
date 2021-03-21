/**
 * 
 */
package com.looka.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.common.TestReporting.ExtentLogger;

import utilities.auto.ToolBox;

/**
 * @author JoshZhuang
 * this class stores all objects and methods from looka dashboard page
 */
public class DashboardPage {

	WebDriver driver;
	//locators	
	@FindBy(how=How.XPATH,using="//h2[text()='Saved Logos']")
	WebElement savedlogosheading;
	
	@FindBy(how=How.XPATH,using="//button[@class='css-emh0r7']")
	WebElement loadingicon;

	@FindBy(how=How.XPATH,using="//button[text()='Confirm']")
	WebElement confirmdelete;
	
	@FindBy(how=How.XPATH,using="//button[text()='Cancel']")
	WebElement canceldelete;
	
	@FindBy(how=How.XPATH,using="//span[@class='css-1f3l2gp']/div[1]")
	WebElement firstlogo;
	
	
	//Create a class constructor for HomePage Class. this is for cross browser testing. e.g. passing "Chrome".
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}

	
	public void checkSavedLogo(String logoid) {
		ToolBox.waitFor(savedlogosheading, 5);
		try {
			WebElement logo = this.driver.findElement(By.xpath("//a[@href='/editor/"+logoid+"']")); //if this webelement doesn't exist, exception will be thrown.
			ExtentLogger.passshot("Saved logo is found. ID = "+logoid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentLogger.failshot("Unable to find saved logo! ID = "+logoid);
		}
	}
	
	
	//delete logo by id
	public void deleteLogoByID(String logoid) throws Exception {
		
		ExtentLogger.info("Try to find and delete logo ID = "+logoid);
		WebElement idlabel;
		
		try {
			idlabel = this.driver.findElement(By.xpath("//a[@href='/editor/"+logoid+"']"));
		} catch (Exception e) {
			ExtentLogger.failshot ("Logo ID = "+logoid+" is NOT found! Unable to delete!");
			e.printStackTrace();
			return;
		}
		
		//mouse hover over
		Actions action = new Actions(driver); //init Actions class
		action.moveToElement(idlabel).perform(); //Find the element and hover over
		Thread.sleep(2000);
		WebElement deletebutton = this.driver.findElement(By.xpath("//a[@href='/editor/"+logoid+"']/../button[@class='css-1psw9c1']"));
		deletebutton.click();
		Thread.sleep(1500);
		
//		confirmdelete.click();
//		action.click(confirmdelete);
		confirmdelete.sendKeys(Keys.ENTER); //only this click works
		Thread.sleep(2000);
		if(ToolBox.waitFor(confirmdelete, 2)) {
			ExtentLogger.failshot("### debug info ### Failed to click confirm delete button!!");
		}else if (ToolBox.waitFor(idlabel, 2)) {
			ExtentLogger.failshot("Did NOT delete! Logo still displays! ID = "+logoid);			
		}else {
			ExtentLogger.pass("Logo is successfully deleted! ID = "+logoid);
		}
			
	}
	
	
	//delete logo by index. first find id by index, and then call deleteLogoByID
	public void deleteLogoByIndex(String index) throws Exception {
		
		ExtentLogger.info("Try to find and delete logo by Index = "+index);
//		WebElement indexeddelete;
		WebElement indexededit;
		
		try {
//			indexeddelete = this.driver.findElement(By.xpath("//span[@class='css-1f3l2gp']/div["+index+"]/div[@class='css-hua7oi css-k008qs']/button[@class='css-1psw9c1']"));
			indexededit = this.driver.findElement(By.xpath("//span[@class='css-1f3l2gp']/div["+index+"]/div[@class='css-hua7oi css-k008qs']/a[text()='Edit']"));
		} catch (Exception e) {
			ExtentLogger.failshot ("Logo index = "+index+" does NOT exist! Unable to delete!");
			e.printStackTrace();
			return;
		}
		
		//get href value by xpath of the indexededit(edit button), which contains logo id.
		String href = indexededit.getAttribute("href");
		String logoid = href.replaceAll("\\D+",""); //remove all non-digits value
		ExtentLogger.info("Logo ID = "+logoid+" is found for Index = "+index);
		
		//call deleteLogoByID
		deleteLogoByID(logoid);
		
	}
	
	
	//wait for all logos to be loaded on dashboard page - max number applied
	//for first logo, waiting 12s as it might take longer, for subsequent ones waiting 4s max each
	//if no more logo is loaded, return
	public int waitlogoloading(int maxnumber) {

		boolean isloaded; //flag to check if next logo is loaded
		int logocount = 0;
		for (int i = 1; i <= maxnumber; i++) {
			if (i==1) {
				isloaded = ToolBox.waitFor(By.xpath("//span[@class='css-1f3l2gp']/div["+i+"]"), 12);
			}else {
				isloaded = ToolBox.waitFor(By.xpath("//span[@class='css-1f3l2gp']/div["+i+"]"), 4);
			}
			
			//if image is not loaded, exit loop, return how many logos are loaded
			if (!isloaded) {
				ExtentLogger.info((i-1)+" logos have been loaded on DashBoard page!");
				logocount = i-1;
				return logocount;
			}
			
			if (i == maxnumber) {
				ExtentLogger.info(i+" or more logos have been loaded on DashBoard page!");
				logocount = i;
			}
		}
		return logocount;		
		
	}

	
}
