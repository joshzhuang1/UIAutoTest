/**
 * 
 */
package co.happy.pages;

import java.util.Objects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.common.TestReporting.ExtentLogger;

import utilities.auto.DataManager;
import utilities.auto.ToolBox;

/**
 * @author JoshZhuang
 * this class stores all objects and methods from looka login page
 */
public class HPPricingPage {

	WebDriver driver;	
//	String datapath = "testdata/pricetounit.xlsx"; //expected unit price table
	
	//locators
	@FindBy(how=How.XPATH,using="//label[@class='form-slider']")
	WebElement pricingslider;
	
	@FindBy(how=How.XPATH,using="//span[@class='pricing-item-price-amount']")
	WebElement unitprice;
	
	@FindBy(how=How.XPATH,using="//span[@class='pricing-slider-value']")
	WebElement unitcount;
	
	
	
	
	
	//Create a class constructor for HomePage Class. this is for cross browser testing. e.g. passing "Chrome".
	public HPPricingPage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	//slide to right twice
	public void verifyPricing(String datapath) throws Exception {	
		Actions action= new Actions(driver);
		//intial position 5000 units, -> 3 times, then <- 6 times, total 9 times
		for (int i=0; i<=9; i++) {
			
			if (i == 0) { 
				// i = 0, do nothing
			} else if (i == 1) { // i == 1, don't click anything 
				action.moveToElement(pricingslider).click(pricingslider).sendKeys(Keys.ARROW_UP).perform();
			} else if (i<=3) {  // while i <= 3, slide to right
				action.sendKeys(Keys.ARROW_UP).perform();
			} else {    	// while i <= 9, slide to left
				action.sendKeys(Keys.ARROW_DOWN).perform();
			}
						
			Thread.sleep(1500);
			
			//get actuall unit count and unit price
			String unit = getUnitCount();
			String actprice = getUnitPrice();
			
			//call method to get expected unit price
			String expprice = getPrice(unit,datapath);
			
			//validate if unit price is as expected.
			if (Objects.equals(expprice,actprice)) {
				ExtentLogger.pass("Unit count = "+unit+". Expected price = "+expprice+". Actual price = "+actprice);
			} else {
				ExtentLogger.failshot("Unit count = "+unit+". Expected price = "+expprice+". Actual price = "+actprice);
			}	

		}

	}

	
	//get displayed unit count value
	public String getUnitCount() {
		return unitcount.getText();
	}
	
	
	//get displayed unit price value
	public String getUnitPrice() {
		return unitprice.getText();
	}
	
	
	//get expected unit price from "source of truth" ---- "testdata/pricetounit.xlsx"
	public String getPrice(String unitcount, String datapath) {
		//init return value
		String price = "value not defined! check "+datapath;
		
		//init a new DataManager Object using excel datasheet.
		DataManager inputsheet = new DataManager(datapath,"Sheet1");
		
		//get row count of input data
		int rcount = inputsheet.getRowCount();	
		
		for (int i=1; i<=rcount; i++) {   
			inputsheet.setCurrentRow(i); // set current row
			String unit = inputsheet.getData("unit");  // read data using col name
			if (Objects.equals(unit, unitcount)) {
				return inputsheet.getData("price");  //get price value
			}
		}
		
		return price;
	}
}
