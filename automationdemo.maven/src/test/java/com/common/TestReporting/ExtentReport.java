package com.common.TestReporting;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utilities.auto.ToolBox;

import java.util.Objects;

import org.testng.Assert;


public class ExtentReport {

	private ExtentReport() {} //constructor
	
	private static ExtentReports extent;
	//public static ExtentTest test;
	
	//Specify the path where report is generated. Name of the Test Class that calls this methods
	static String foldername = ToolBox.getCallerCallerClassName(); 
	static String reportpath = "testreports/"+foldername+"/index.html";
	
	//init report, specify report folder path (using test class name), load config file
	public static void initReports() throws IOException {
		if(Objects.isNull(extent)) { // don't run this if extent object already exists
			extent = new ExtentReports(); //create new extentreport object
			
			ExtentSparkReporter spark = new ExtentSparkReporter(reportpath); //set report html file path and name
			extent.attachReporter(spark); //Attach a reporter to access all started tests, nodes and logs			
			spark.loadXMLConfig(new File("XMLfiles/extentconfig.xml")); //Load extentconfig.xml file	
		}

	}
	
	
	//	create test: can pass testcasename as loggername for common practice. 
	//	with different logger names, extent report will create different tabs in a html report.
	public static void createTest(String loggername) {
		ExtentTest test = extent.createTest(loggername);
		ExtentManager.setExtentTest(test);
	}
	
	
	//initReports + createTest ----- mostly used
	public static void createTestReport(String testcasename) throws IOException {
			initReports();
			createTest(testcasename);
	}

	
	//tear down report and sync status
	public static void flushReportsAlt() throws IOException {
		extent.flush(); //write all the test logs to the report file			
//		syncReportResult(); //to fail TestNG report, if Extentreport is a fail.
	}
	
	
	//tear down report, launch report.
	public static void flushReports() throws IOException {
		extent.flush(); //write all the test logs to the report file
		Desktop.getDesktop().browse(new File(reportpath).toURI()); //open html report at the end of the test
//		syncReportResult(); //if any failed steps in Extentreport, also fail TestNG report.
	}
	
	
	//Get status of extent report result. if fails, return true, otherwise return false.
	public static boolean isExtReportFail() {
		Status currentstatus = ExtentManager.getExtentTest().getStatus(); //get pass/fail status of current test
		if (Objects.equals(currentstatus.name(), "FAIL")) { //if extentport status is fail, also fail the TestNG test. Otherwise TestNG report is still pass.
//			Assert.fail("Set TestNG result to fail according to extent report results");
			return true;
		}	else {
			return false;
		}
	}
	
}
	

