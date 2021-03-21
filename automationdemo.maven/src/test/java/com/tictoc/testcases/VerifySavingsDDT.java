/**
 * 
 */
package com.tictoc.testcases;

import java.io.IOException;
import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.common.TestReporting.ExtentLogger;
import com.common.TestReporting.ExtentReport;
import com.tictoc.pages.HomePageFactory;
import com.tictoc.pages.RefiCalcPageFactory;

import utilities.auto.BrowserFactory;
import utilities.auto.DriverFactory;
import utilities.auto.ExcelDataConfig;

/**
 * @author JoshZhuang
 * Demo Testing refinance calculator from tictoc
 * using /SeleniumTrial/datatable/CalcInput.xlsx as data input
 * using VerifySavingsDDT.xml for test run config - cross browser testing
 * using extent report to capture test result
 * 
 */

public class VerifySavingsDDT {
	
	WebDriver ldriver;
//	String reportfolder = "VerifySavingsDTT";
	
	//specify testdata input
	String wbname = "testdata/CalcInput.xlsx";
//	String wbname = "testdata/CalcInputShort.xlsx"; ////
	String stname = "Sheet1";

		
	@DataProvider(name = "calcdata")
	public Object[][] passData() {  //return 2-dim array 
		ExcelDataConfig inputsheet = new ExcelDataConfig(wbname,stname);
		Object data[][] = inputsheet.getTestData();
		return data;
	}
	
	
	@BeforeClass //launch chrome and navigate to tictoc
	@Parameters({"selectedbrowser","startingurl"}) // !!!this parameters will be from VerifySavingsDDT.xml and passed to below launchApp method
	public void launchApp(String browsername, String url) throws InterruptedException {
		ldriver = BrowserFactory.initBrowser(browsername); //init threadlocal instance - recommended!
		Thread.sleep(2000);	
		
		ldriver.manage().window().maximize(); //maximise window
		ldriver.get(url); //navigate to *url*	
	}
	
	
	@BeforeClass (dependsOnMethods={"launchApp"}) //init testreport. this is called after launchAPP()
	@Parameters({"testcasename"}) // !!!this parameters will be from VerifySavingsDDT.xml and passed to below initTestReport method
	public void initTestReport(String testname) throws IOException {
		//init test report. reportfolder is the html report location, testname specifies testnode in extreport.
		ExtentReport.createTestReport(testname);
	}
	
	
	@AfterClass //close chrome and flush extreport
	public void closeApp() throws IOException {

		//		driver.quit();  //close browser
		DriverFactory.getInstance().removeDriver(); //close current threadlocal browser instance --- for multi-threading
		
		//finalise test report
		ExtentReport.flushReports();
	}
	
	
	@Test(dataProvider ="calcdata") //below method needs 4 args - same structure as imported datatable
	public void VerifyRefiSavings(String loanamount,String currentrate,String remyears,String breakevenrate) throws InterruptedException, IOException {
		
		
		//print test data info in report
		ExtentLogger.info("***TEST DATA*** loanamount "+loanamount+", currentrate "+currentrate+", remyears "+remyears+", breakevenrate "+breakevenrate);

		//init HomePageFactory class (page factory)
		HomePageFactory homepage = PageFactory.initElements(ldriver, HomePageFactory.class);
		
		//Step 1 title verification for homepage
		String ActualTitle = ldriver.getTitle();
		String ExpTitle = "Online home loans | Smarter Faster Simpler | Tic:Toc";		
		
		if (Objects.equals(ExpTitle, ActualTitle)){
			ExtentLogger.pass("page title is as expected: "+ExpTitle);
		}else {
			ExtentLogger.failshot("Expected title: "+ExpTitle+". Actual title: "+ActualTitle);    //with screenshotW
		}
		
		//Step 2 Navigate to refi calculator
		homepage.GotoRefiCalc(); 
		Thread.sleep(2000);
		
		
		//init RefiCalcPageFactory class (page factory)
		RefiCalcPageFactory RefiCalcPage = PageFactory.initElements(ldriver, RefiCalcPageFactory.class);
		
		//Step 3 enter values and calculate, verify webelement is displayed as expected
		RefiCalcPage.CalcRefiSavings(loanamount, currentrate, remyears, breakevenrate);

		Thread.sleep(1000);
		
		homepage.gotoHomePage(); //navigate to home page so that next iteration can happen
	
	}
	
}
