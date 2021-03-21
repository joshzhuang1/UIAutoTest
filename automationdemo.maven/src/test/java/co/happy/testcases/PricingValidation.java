/**
 * 
 */
package co.happy.testcases;

import static org.testng.Assert.fail;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.common.TestReporting.ExtentLogger;
import com.common.TestReporting.ExtentReport;
import com.looka.pages.DashboardPage;
import com.looka.pages.LoginPage;
import com.looka.pages.OnboardingPage;

import co.happy.pages.PricingPage;
import co.happy.pages.HomePage;
import co.happy.pages.HPPricingPage;
import utilities.auto.BrowserFactory;
import utilities.auto.DriverFactory;
import utilities.auto.ToolBox;

/**
 * @author JoshZhuang
 *
 */

@Listeners(utilities.auto.TestNGListener.class)

public class PricingValidation {
	WebDriver ldriver;
//	String reportfolder = "HappyCoPricing";
//	String testcasename = "HappyCoPricing";
//	String browsername = "chrome";
//	String url = "https://happy.co";
	String datapath = "testdata/pricetounit.xlsx"; //expected unit price table

	@BeforeClass 
	@Parameters({"testcasename"})// init extreport
	public void initExtentReport(String testname) throws IOException, Exception {
		ExtentReport.createTestReport(testname); //init extent report. testname is logger name
	}
	
	@BeforeClass (dependsOnMethods={"initExtentReport"}) //init browser instance and launch app
	@Parameters({"selectedbrowser","startingurl","seleniumhub"}) // !!!this parameters will be from LogoE2E.xml and passed to below launchApp method
	public void launchApp(String browsername, String auturl,String huburl) throws InterruptedException, IOException {
		ldriver = BrowserFactory.initBrowser(browsername,huburl); //init threadlocal instance - recommended!
		Thread.sleep(2000);	
		
		ldriver.manage().window().maximize(); //maximise window
		ldriver.get(auturl); //navigate to *url*	
	}
	
	@AfterClass //close chrome and flush extreport
	public void closeApp() throws IOException {

		//		driver.quit();  //close browser
		DriverFactory.getInstance().removeDriver(); //close current threadlocal browser instance --- for multi-threading
		
		//finalise test report
		ExtentReport.flushReports();
	}

	
	
	@Test
	@Parameters({"datapath"}) 
	public void verifyHPPricing(String datapath) throws Exception {
	
//		ldriver = BrowserFactory.initBrowser(browsername); //init threadlocal instance - recommended!
//		Thread.sleep(2000);	
////		ExtentReport.createTestReport(reportfolder, reportfolder); //init extent report		
//		
//		ldriver.manage().window().maximize(); //maximise window
//		ldriver.get(url); //navigate to *url*	

		ExtentLogger.info("*******TEST DATA******** Input Data: "+ datapath);
		
		//init homepage
		HomePage homepage = PageFactory.initElements(ldriver, HomePage.class);	
		//init pricing page
		HPPricingPage hppricingpage = PageFactory.initElements(ldriver, HPPricingPage.class);	
		//init hppricing page
		PricingPage pricingpage = PageFactory.initElements(ldriver, PricingPage.class);	
		
		
		//navigate to Pricing page
		homepage.naviToPricing();
		
		//navigate to Happy Property Pricing page
		pricingpage.naviToHPPricing();
		
		//validate the unit pricing - unit count is as expected for each level on the slide bar
		hppricingpage.verifyPricing(datapath);
		
		Thread.sleep(1000);
			
	}

}
