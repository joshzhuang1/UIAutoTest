package com.tictoc.testcases;

import java.io.IOException;
import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.common.TestReporting.ExtentLogger;
import com.common.TestReporting.ExtentReport;

import utilities.auto.BrowserFactory;
import utilities.auto.DriverFactory;

	/**
	 * @author JoshZhuang
	 * This test is to verify extent report logger implementation
	 */


	@Listeners(utilities.auto.TestNGListener.class) // Listener!!!!!!!!!!!!!!!!!!!!!!!!
	public class TestExtReportingOutput {
		
		WebDriver localdriver;
		String testname = "TestExtReportingOutput";
		String browsername = "chrome";      		// "edge";
		String url = "https://tictoc.com.au/";		// "https://www.nab.com.au/"
		
		
		@BeforeClass  //init test report
		public void initTestReport() throws IOException{
			//init test report
			ExtentReport.createTestReport(testname);
		}
		
		
		@BeforeClass(dependsOnMethods = { "initTestReport" }) //launch chrome and navigate to tictoc
		public void launchApp() throws InterruptedException {			
			localdriver = BrowserFactory.initBrowser(browsername); //init thread local instance for multi-threading
			localdriver.manage().window().maximize(); //maximise window
			localdriver.get(url); //navigate to *url*
			
			Thread.sleep(2000);	
		}
		
		
		@AfterClass //close chrome each time when a test is completed!
		public void closeApp() {
//			localdriver.quit();  //close browser
			DriverFactory.getInstance().removeDriver(); // to close a particular threadlocal browser instance ---- this is for multi-threading
		}
		
	
//		@AfterClass (dependsOnMethods = { "closeApp" })
//		public void completeTestReport() throws IOException {
//			ExtentReport.flushReports(testname);
//		}
		
		
		@Test
		public void Verifylogger() throws IOException, InterruptedException{
			
			//init test report
//			ExtentReport.createTestReport(testname, testname);
			
			//test title compare
			String ActualTitle = localdriver.getTitle();
			String ExpTitle = "?????Online home loans | Smarter Faster Simpler | Tic:Toc";		
			
			if (Objects.equals(ExpTitle, ActualTitle)){
				ExtentLogger.pass("page title is as expected: "+ExpTitle);
			}else {
				ExtentLogger.failshot("Expected title: "+ExpTitle+". Actual title: "+ActualTitle);    //with screenshot
				//ExtentLogger.fail("Expected title: "+ExpTitle+". Actual title: "+ActualTitle);   //without screenshot
			}
			
			
			ExtentLogger.fail("xin nian hao !");
			ExtentLogger.warningshot("go to next page");
//			ExtentLogger.skip("the step is skipped");
//			ExtentLogger.info("this is just FYI");
			ExtentLogger.pass("step passed");
			
			//finalise test report
			ExtentReport.flushReports();
		}
		
	}

	