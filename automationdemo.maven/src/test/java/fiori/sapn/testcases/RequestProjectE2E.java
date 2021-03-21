/**
 * 
 */
package fiori.sapn.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.common.TestReporting.ExtentReport;
import com.looka.pages.DashboardPage;
import com.looka.pages.LoginPage;

import fiori.sapn.pages.LaunchPad;
import utilities.auto.BrowserFactory;
import utilities.auto.DriverFactory;
import utilities.auto.ToolBox;

/**
 * @author zhuaj1
 *
 */

@Listeners(utilities.auto.TestNGListener.class)

public class RequestProjectE2E {

	WebDriver ldriver;
	String reportfolder = "FioriRequestProject";
	String testcasename = "FioriRequestProject";
	String browsername = "chrome";
	String url = "https://sapgwqas001.etsa.com.au:2080/fiori/";
	

	@BeforeClass // init extreport
	public void initExtentReport() throws IOException {
		ExtentReport.createTestReport(testcasename); //init extent report
	}
	
	@AfterClass //close chrome and flush extreport
	public void closeApp() throws IOException {

		//		driver.quit();  //close browser
		DriverFactory.getInstance().removeDriver(); //close current threadlocal browser instance --- for multi-threading
		
		//finalise test report
		ExtentReport.flushReports();
	}

	
	
	@Test
	public void requestProject() throws Exception {
			
		
		ldriver = BrowserFactory.initBrowser(browsername); //init threadlocal instance - recommended!
		Thread.sleep(3000);	
		
		
		ldriver.manage().window().maximize(); //maximise window
		ldriver.get(url); //navigate to *url*	
//		Assert.fail("END of testing#$D");
		
		//init LaunchPad
		LaunchPad launchpad = PageFactory.initElements(ldriver, LaunchPad.class);	

		
		//navigate to request project
		launchpad.requestProject();
		
		
		Thread.sleep(1000);
		
	}
}
