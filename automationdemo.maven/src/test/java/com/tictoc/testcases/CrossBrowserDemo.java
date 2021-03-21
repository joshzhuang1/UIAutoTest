package com.tictoc.testcases;

import java.io.IOException;
import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.common.TestReporting.ExtentLogger;
import com.common.TestReporting.ExtentReport;
import com.tictoc.pages.HomePage;

import utilities.auto.BrowserFactory;
import utilities.auto.DriverFactory;

public class CrossBrowserDemo {
	WebDriver driver;
//	String reportfolder = "CrossBrowserDemo";
//	String browsername = "chrome";      		// "edge";
	String url = "https://tictoc.com.au/";		// "https://www.nab.com.au/"
	
	@BeforeClass //launch chrome navigate to tictoc
	@Parameters({"selectedbrowser"}) // !!!this parameters will be from CrossBrowserDemo.xml and passed to below launchApp method
	public void launchApp(String browsername) throws InterruptedException {
		driver = BrowserFactory.initBrowser(browsername); //init thread local instance for multi-threading
		Thread.sleep(2000);	
		
		driver.manage().window().maximize(); //maximise window
		driver.get(url); //navigate to *url*	
	}
	
	@BeforeClass (dependsOnMethods={"launchApp"}) 
	@Parameters({"testcasename"})
	public void initTestReport(String testname) throws IOException {
		//init test report
		ExtentReport.createTestReport(testname);
	}
	
	
	@AfterClass //close chrome each time when a test is completed!
	public void closeApp() throws IOException {
//		driver.quit();  //close browser
		DriverFactory.getInstance().removeDriver(); // to close a particular threadlocal browser instance ---- this is for multi-threading
	}
	

	@Test
	//@Parameters({"testcasename"}) // !!!this parameter will be from CrossBrowserDemo.xml and passed to below verifylogger method. this is to differentiate test names in the test report.
	public void verifylogger() throws IOException, InterruptedException{
		
//		//init test report
//		ExtentReport.createTestReport(reportfolder, testname);
		
		//test title compare
		String ActualTitle = driver.getTitle();
		String ExpTitle = "?????Online home loans | Smarter Faster Simpler | Tic:Toc";		
		
		if (Objects.equals(ExpTitle, ActualTitle)){
			ExtentLogger.pass("page title is as expected: "+ExpTitle);
		}else {
			ExtentLogger.failshot("Expected title: "+ExpTitle+". Actual title: "+ActualTitle);    //with screenshot
			//ExtentLogger.fail("Expected title: "+ExpTitle+". Actual title: "+ActualTitle);   //without screenshot
		}
		
		//Navigate to Refi calc page
		HomePage homepage = new HomePage(driver); //init HomePage class with current webdriver instance
		homepage.GotoRefiCalc(); 
		Thread.sleep(2000);
		
		//ExtentLogger.fail("xin nian hao !");
		ExtentLogger.warningshot("go to next page");
		ExtentLogger.pass("step passed");
		
		//finalise test report
		ExtentReport.flushReports();
	}
}

