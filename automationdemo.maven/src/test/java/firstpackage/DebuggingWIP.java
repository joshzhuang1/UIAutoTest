/**
 * 
 */
package firstpackage;

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
import org.testng.annotations.Test;

import com.common.TestReporting.ExtentReport;
import com.looka.pages.DashboardPage;
import com.looka.pages.LoginPage;
import com.looka.pages.OnboardingPage;

import utilities.auto.BrowserFactory;
import utilities.auto.DriverFactory;
import utilities.auto.ToolBox;

/**
 * @author JoshZhuang
 *
 */

@Listeners(utilities.auto.TestNGListener.class)

public class DebuggingWIP {
	WebDriver ldriver;
	String reportfolder = "Lookatests";
	String testcasename = "Lookatests";
	String browsername = "chrome";
	String url = "https://www.looka.com/";
	

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
	public void addLogoToFaviourite() throws Exception {
			
//	    int i = 34534;
//	    
//	    System.out.println("sdafj "+i+ " fsdfsd");
//	    Thread.sleep(2000);	
		String input = "hello.com.au@Gsdrt1234";
		String output = input.substring(0, input.indexOf('@'));
		
//		String[] val = new String[20];
//		for(int i=1; i<10; i++) {
//			val[i] = this.toString(); 
//	    }
//		Assert.fail("END of testing#$D");
		ldriver = BrowserFactory.initBrowser(browsername); //init threadlocal instance - recommended!
		Thread.sleep(2000);	
//		ExtentReport.createTestReport(reportfolder, reportfolder); //init extent report
		
		
		ldriver.manage().window().maximize(); //maximise window
		ldriver.get(url); //navigate to *url*	
	//	Assert.fail("END of testing#$D");
		//init LoginPage
		LoginPage loginpage = PageFactory.initElements(ldriver, LoginPage.class);	
		//init dashboard page
		DashboardPage dashboardpage = PageFactory.initElements(ldriver, DashboardPage.class);
		
		//login
		loginpage.login("joshzhuangdemo@gmail.com","K!e9R#cj4KRXQ7w");
		Thread.sleep(1000);
		dashboardpage.waitlogoloading(5);
		dashboardpage.deleteLogoByIndex("1");
		
//		Assert.fail("END of testing#$D");
//		Thread.sleep(15000);
		
		
		


		dashboardpage.deleteLogoByIndex("16");
		Thread.sleep(1000);	
//		Assert.assertTrue(true);
		
		
		
		WebElement logo = this.ldriver.findElement(By.xpath("//span[@class='css-1f3l2gp']/div[3]"));
		ToolBox.waitFor(logo, 8); //the logos take some time to show up
		logo.click();
		
		Thread.sleep(1000);
		
	}

}
