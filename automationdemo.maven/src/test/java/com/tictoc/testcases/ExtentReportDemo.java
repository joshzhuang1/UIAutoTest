/**
 * 
 */
package com.tictoc.testcases;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utilities.auto.BrowserFactory;

/**
 * @author JoshZhuang
 *
 */


public class ExtentReportDemo {
//	
//	ExtentReports extent;
//	WebDriver driver;
//	
//	@BeforeSuite
//	public void setUp() throws IOException {
//		ExtentReports extent = new ExtentReports(); //create new extentreport object
//		ExtentSparkReporter spark = new ExtentSparkReporter("./TestReports/index.html"); //set report file path and name
//		spark.loadXMLConfig(new File("extentconfig.xml"));
//		extent.attachReporter(spark); //Attach a reporter to access all started tests, nodes and logs
//		
//		//test config to be maintained in config file
////		final File CONF = new File("extentconfig.xml"); //create extentconfig file
////		spark.loadXMLConfig(CONF); //import extentconfig file
//		
//		
//	}
//	
//	
//	@AfterSuite
//	public void tearDown() throws IOException {
//		extent.flush(); //unless you call this method, your report will not be writen with logs
//		//Desktop.getDesktop().browse(new File("./TestReports/index.html").toURI()); //relative path not working???????
//		Desktop.getDesktop().browse(new File("C:\\Users\\JoshZhuang\\OneDrive\\SeleniumProjects\\SeleniumTrial\\TestReports\\index.html").toURI()); //open the report right after text execution
//	
//	}
//	
	
	
//	@Test(priority=1,description="login to some page")
//	public void qtesting001() {
//		System.out.println("testing 001 is to log in");
//		Assert.assertEquals("adf","dsfa");
//		Assert.assertEquals("2234","2234");
//	    Reporter.log("Fail:: Login Failed");
//	}
//	
//	@Test(priority=2,description="buy sth")
//	public void atesting012() {
//		System.out.println("testing 012 is to buy lots of things");
//	}
	
	//Extent Report Demo 
	@Test(priority=3,description="check out - using extent report")
	public void ExtentReportDemo() throws IOException, URISyntaxException, InterruptedException {

		ExtentReports extent = new ExtentReports(); //create new extentreport object
		ExtentSparkReporter spark = new ExtentSparkReporter("./testreports/index.html"); //set report file path and name
		extent.attachReporter(spark); //Attach a reporter to access all started tests, nodes and logs
		
		//spark.config().setDocumentTitle("Regression ABC testsuite"); //html file title
		//test config to be maintained in config file
		final File CONF = new File("extentconfig.xml"); //create extentconfig file
		spark.loadXMLConfig(CONF); //import extentconfig file
		

		ExtentTest logger = extent.createTest("Test step 01 loginTesting").assignAuthor("J Zhuang").assignCategory("Regression"); //create a test node
		logger.info("login to some page");
		logger.pass("test step as expected=================================");
		logger.fail("test step failed333333!");
		
		//Launch Chrome and go to tictoc
		String browsername = "chrome";
		String url = "https://tictoc.com.au";
		
		WebDriver localdriver = BrowserFactory.initBrowser(browsername);		
		localdriver.manage().window().maximize(); //maximise window
		localdriver.get(url); //navigate to *url*
		Thread.sleep(2000);		
		
		logger.fail("see scrshot. it's wrong"); // create a test step node with screenshot!!
		
		logger.pass("dfd ye ye ye"); // create a test step node
		
		ExtentTest logger2 = extent.createTest("Test step 02 fill shopping cart"); //create another test node
		logger2.info("buy sth");
		logger2.pass("test step as expectedf;lksaj");
		logger2.pass("dfd ye ye ye"); // create a test step node
		
		extent.flush(); //unless you call this method, your report will not be writen with logs
//		//Desktop.getDesktop().browse(new File("./TestReports/index.html").toURI()); //relative path not working???????
		Desktop.getDesktop().browse(new File("/testreports/index.html").toURI()); //open the report right after text execution
	
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		URL resourcea = contextClassLoader.getResource("/testreports/index.html");
		Desktop.getDesktop().open(new File(resourcea.toURI()));
	}
	
//	public String getScreenshotPath() throws IOException {
//		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		String path = System.getProperty("user.dir")+"/Screenshots/image.png";
//		FileUtils.copyFile(source, new File(path));
//		return path;				
//	}
}






