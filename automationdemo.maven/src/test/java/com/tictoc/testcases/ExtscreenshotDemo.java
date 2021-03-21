/**
 * 
 */
package com.tictoc.testcases;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.tictoc.pages.HomePage;
import com.tictoc.pages.RefiCalcPageFactory;

import utilities.auto.BrowserFactory;

/**
 * @author JoshZhuang
 *
 */
public class ExtscreenshotDemo {

	
	//will later have this.extent and this.driver.
	ExtentReports extent;
	WebDriver driver;
	
	@BeforeSuite
	public void setUp() throws IOException {
		extent = new ExtentReports(); //create new extentreport object
		ExtentSparkReporter spark = new ExtentSparkReporter("./testreports/index.htmll"); //set report html file path and name
		spark.loadXMLConfig(new File("XMLfiles/extentconfig.xml")); //Load extentconfig.xml file
		extent.attachReporter(spark); //Attach a reporter to access all started tests, nodes and logs		
	}
	
	
	@AfterSuite
	public void tearDown() throws IOException {
		extent.flush(); //unless you call this method, your report will not be written with logs
		//Desktop.getDesktop().browse(new File("./TestReports/index.html").toURI()); //relative path not working???????
		Desktop.getDesktop().browse(new File("C:\\Users\\JoshZhuang\\OneDrive\\SeleniumProjects\\SeleniumTrial\\TestReports\\index.html").toURI()); //open the report right after text execution	
	}
	

	//Extent Report Demo - with screenshot
	@Test(priority=1,description="check out - using extent report")
	public void ExtentReportDemo() throws IOException, URISyntaxException, InterruptedException {
	       
		ExtentTest logger = extent.createTest("Test step 01 loginTesting").assignAuthor("J Zhuang").assignCategory("Regression"); //create a test node
		
		// create a test step node and pass
		//logger.pass("test step as expected=================================");
		
		//Launch Chrome and go to tictoc
		String browsername = "chrome";
		String url = "https://tictoc.com.au";
		WebDriver localdriver = BrowserFactory.initBrowser(browsername);
		
		localdriver.manage().window().maximize(); //maximise window
		localdriver.get(url); //navigate to *url*
		
		Thread.sleep(1000);	
		
		this.driver = localdriver; //!!!important!!! this.driver is needed in getScreenshotPath, which is still null before this step.
				
		// create a test step node with screenshot!!
		logger.fail("see scrshot. it's wrong", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshotPath(getRandomfileName("png"))).build());
		Thread.sleep(3000);
		
		//Step 2 Navigate to refi calculator
		HomePage homepage = new HomePage(localdriver); 
		homepage.GotoRefiCalc(); 
		Thread.sleep(2000);
		
		// create a test step node with screenshot!!
		logger.pass("yay passed!!!!", MediaEntityBuilder.createScreenCaptureFromPath(getScreenshotPath(getRandomfileName("png"))).build());
	}
	
	
	//To take screenshot, and copy paste to the report.
	public String getScreenshotPath(String imgname) throws IOException {
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/Screenshots/"+imgname; //full path - C:\Users\JoshZhuang\OneDrive\SeleniumProjects\SeleniumTrial\Screenshots
		FileUtils.copyFile(source, new File(path));
		return path;				
	}
	
	
	//need random file name for screenshots
	public String getRandomfileName(String filetype){
		Random rand = new Random(); //instance of random class
	      int upperbound = 999999;
	        //generate random values from 0-24
	      int int_random = rand.nextInt(upperbound); 
	      String imgname = Integer.toString(int_random)+"."+filetype;
	      return imgname;
	}
	


////Creating a method getScreenshot and passing two parameters 
////driver and screenshotName
//	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
//            //below line is just to append the date format with the screenshot name to avoid duplicate names		
//            String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//			TakesScreenshot ts = (TakesScreenshot) driver;
//			File source = ts.getScreenshotAs(OutputType.FILE);
//            
//			//after execution, you could see a folder "FailedTestsScreenshots" under src folder
//			String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
//			File finalDestination = new File(destination);
//			FileUtils.copyFile(source, finalDestination);
//            //Returns the captured file path
//			return destination;
//	}
}



