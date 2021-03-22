/**
 * 
 */
package com.common.TestReporting;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.MediaEntityBuilder;

import utilities.auto.DriverFactory;


/**
 * @author JoshZhuang
 * Extentlogger is the interface to external testscripts.
 * test scripts call ExtentLogger.pass("msg"); to log result
 */


public final class ExtentLogger {

	
	private ExtentLogger() {} //constructor
	//private static SoftAssert softAssert = new SoftAssert();
	
	
	//set pass
	public static void pass(String message) {
		ExtentManager.getExtentTest().pass(message);
	}
	
	
	//set fail
	public static void fail(String message) {
		ExtentManager.getExtentTest().fail(message);
		//softAssert.fail(); //fail the test for TestNG
	}
	
	
	//set info
	public static void info(String message) {
		ExtentManager.getExtentTest().info(message);
	}
	
	
	//set warning
	public static void warning(String message) {
		ExtentManager.getExtentTest().warning(message);
	}
	
	
	//set skip
	public static void skip(String message) {
		ExtentManager.getExtentTest().skip(message);
	}
	

	//set pass with screenshot
	public static void passshot(String message){
		try {
			ExtentManager.getExtentTest().pass(message,MediaEntityBuilder.createScreenCaptureFromPath(getScreenshotPath(getRandomfileName("png"))).build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentManager.getExtentTest().pass(message+" --- unable to do screenshot");
		}
	}
	
	
	//set fail with screenshot
	public static void failshot(String message) {
		try {
			ExtentManager.getExtentTest().fail(message,MediaEntityBuilder.createScreenCaptureFromPath(getScreenshotPath(getRandomfileName("png"))).build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentManager.getExtentTest().fail(message+" --- unable to do screenshot");		
		}
	}
	
	
	//set warning with screenshot
	public static void warningshot(String message) {
		try {
			ExtentManager.getExtentTest().warning(message,MediaEntityBuilder.createScreenCaptureFromPath(getScreenshotPath(getRandomfileName("png"))).build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentManager.getExtentTest().warning(message+" --- unable to do screenshot");
		}
	}
	
	
	//set info with screenshot
	public static void infoshot(String message) {
		try {
			ExtentManager.getExtentTest().info(message,MediaEntityBuilder.createScreenCaptureFromPath(getScreenshotPath(getRandomfileName("png"))).build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentManager.getExtentTest().info(message+" --- unable to do screenshot");
		}
	}
	
	
	//set skip with screenshot
	public static void skipshot(String message) {
		try {
			ExtentManager.getExtentTest().skip(message,MediaEntityBuilder.createScreenCaptureFromPath(getScreenshotPath(getRandomfileName("png"))).build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentManager.getExtentTest().skip(message+" --- unable to do screenshot");
		}
	}
	
	
	//fail the test case outright
	public static void failtest(String message) {
		ExtentManager.getExtentTest().fail(message);
		Assert.fail(message); //set testng result to fail
	}
	
	
	//fail the test case outright with screenshot
	public static void failtestshot(String message) {
		try {
			ExtentManager.getExtentTest().fail(message,MediaEntityBuilder.createScreenCaptureFromPath(getScreenshotPath(getRandomfileName("png"))).build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentManager.getExtentTest().fail(message+" --- unable to do screenshot");		
		}
		Assert.fail(message); //set testng result to fail
	}
	
	
	//To take screenshot, and copy paste to the report. - no need to pass webdriver!!!!
	private static String getScreenshotPath(String imgname) throws IOException {
		WebDriver driver = DriverFactory.getInstance().getDriver(); //get current webdriver instance!!! this only works with threadlocal instance	
		if (Objects.isNull(driver)) { //handle remote driver 
			driver = DriverFactory.getInstance().getDriver();
		}
		
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/testreports/_Screenshots/"+imgname;
//		String path = "/job/_TicTocDockerGrid/ws/automationdemo.maven/testreports/_Screenshots/"+imgname;
		FileUtils.copyFile(source, new File(path));
		return path;				
	}
	
	
	//need random file name for screenshots
	private static String getRandomfileName(String filetype) {
		Random rand = new Random(); //instance of random class
	      int upperbound = 9999999;
	        //generate random values from 0-9999999
	      int int_random = rand.nextInt(upperbound); 
	      String imgname = Integer.toString(int_random)+"."+filetype;
	      return imgname;
	}
}
