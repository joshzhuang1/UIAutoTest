/**
 * 
 */
package com.looka.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.common.TestReporting.ExtentLogger;
import com.common.TestReporting.ExtentReport;
import com.looka.pages.CommonLocators;
import com.looka.pages.DashboardPage;
import com.looka.pages.EditorPage;
import com.looka.pages.ExplorePage;
import com.looka.pages.LoginPage;
import com.looka.pages.OnboardingPage;

import utilities.auto.BrowserFactory;
import utilities.auto.DriverFactory;
import utilities.auto.ExcelDataConfig;

/**
 * @author JoshZhuang
 *
 */

@Listeners(utilities.auto.TestNGListener.class)

public class LogoManagement {
	WebDriver ldriver;
	
	//data input
	String wbname = "testdata/manageLogo.xlsx";
//	String stname1 = "logindetails";
	String stname2 = "logodetails";
	String stname3 = "logoindex";

	
//	@DataProvider(name = "logindata")
//	public Object[][] passData1() {  //return 2-dim array 
//		ExcelDataConfig inputsheet = new ExcelDataConfig(wbname,stname1);
//		Object data[][] = inputsheet.getTestData();
//		return data;
//	}
	
	@DataProvider(name = "logodata")
	public Object[][] passData2() {  //return 2-dim array 
		ExcelDataConfig inputsheet = new ExcelDataConfig(wbname,stname2);
		Object data[][] = inputsheet.getTestData();
		return data;
	}
	
	@DataProvider(name = "deletelogo")
	public Object[][] passData3() {  //return 2-dim array 
		ExcelDataConfig inputsheet = new ExcelDataConfig(wbname,stname3);
		Object data[][] = inputsheet.getTestData();
		return data;
	}
	
	
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

	
	@Test (priority=1)
	@Parameters({"username","password"}) 
	public void login(String uname, String pword) throws Exception {
				//init pages
		LoginPage loginpage = PageFactory.initElements(ldriver, LoginPage.class);	
		CommonLocators commonlocators = PageFactory.initElements(ldriver, CommonLocators.class);	
		DashboardPage dashboardpage = PageFactory.initElements(ldriver, DashboardPage.class);
		//login
		loginpage.login(uname,pword);
		
		//check if login successful
		commonlocators.checkUserLogin();
		
		//wait for all logos to be loaded
		dashboardpage.waitlogoloading(30);
	}
	
	
	// (dependsOnMethods={"login"}) 
	@Test (priority=2, dataProvider ="logodata")
	//generate some logos and save one of them to fav
	public void addLogoToSaved(String industry, String color, String compayname, String slogan) throws Exception {
		
		//print test data info in report
		ExtentLogger.info("***TEST DATA*** industry "+industry+", color "+color+", compayname "+compayname+", slogan "+slogan);
		
		//init pages
		CommonLocators commonlocators = PageFactory.initElements(ldriver, CommonLocators.class);	
		OnboardingPage onboardingpage = PageFactory.initElements(ldriver, OnboardingPage.class);
		ExplorePage explorepage = PageFactory.initElements(ldriver, ExplorePage.class);
		EditorPage editorpage = PageFactory.initElements(ldriver, EditorPage.class);
		DashboardPage dashboardpage = PageFactory.initElements(ldriver, DashboardPage.class);
				
		//navigate to logo generator
		commonlocators.navigateToGenerator();
		
		//check if user landed to onboarding page successfully
		onboardingpage.checkOnboarding();
		
		//generate a logo
		onboardingpage.generateLogo(industry,color,compayname,slogan);
		
		//check if logos are being generated within x seconds
		explorepage.checkLogoGenerating(3);
		
		//check if logos are generated within x seconds
		explorepage.checkPickLogo(20);

		//wait for all generated logos to be loaded
		explorepage.waitgeneratedlogo(30);

		//select a photo to save
		explorepage.selectSavedLogo(2);
		
		//get logo id from editor page url
		String logoid = editorpage.getCurrentLogoID();
		Thread.sleep(5000);
		
		//navigate to dashboard page
		commonlocators.navigateToSavedlogos();
		
		//wait for all logos to be loaded
		dashboardpage.waitlogoloading(30);
		
		//Check if logo with specific ID is displayed
		dashboardpage.checkSavedLogo(logoid);
		
		Thread.sleep(2000);
	}
	
	@Test (priority=3, dataProvider ="deletelogo")
	// To delete a saved logo
	public void deleteLogo(String interation) throws Exception {
		//print test data info in report
		ExtentLogger.info("***TEST DATA*** to delete logo - iteration "+interation);
		//init pages
		DashboardPage dashboardpage = PageFactory.initElements(ldriver, DashboardPage.class);
		
		ldriver.navigate().refresh(); // there is a bug here. if don't refresh page confirm delete button wouldn't work
		
		//wait for all logos to be loaded
		int logocount = dashboardpage.waitlogoloading(30);
		
		//delete the last logo  on the page
		dashboardpage.deleteLogoByIndex(Integer.toString(logocount));
	}
	
	@Test  (priority=4)
	// log off
	public void logoff() throws Exception {
		
		//init pages
		CommonLocators commonlocators = PageFactory.initElements(ldriver, CommonLocators.class);	
		LoginPage loginpage = PageFactory.initElements(ldriver, LoginPage.class);
		
		//log off and navigate to homepage
		commonlocators.logOffToHome();
		
		//check if login button exists
		loginpage.checkLoginLabel(5);
	}
	

}
