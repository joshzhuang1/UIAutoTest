/**
 * 
 */
package utilities.auto;

import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.common.TestReporting.ExtentLogger;
import com.common.TestReporting.ExtentManager;
import com.common.TestReporting.ExtentReport;

/**
 * @author JoshZhuang
 *
 */
public class TestNGListener implements ITestListener {
   
	
	@Override
	public void onTestStart(ITestResult result) {	
		ExtentLogger.info("*** Test execution started *** --- "+result.getName());
		System.out.println("*** Test execution started *** --- "+result.getName());
	}
	
	
	@Override
	public void onStart(ITestContext result) {	
		
		try {
			ExtentLogger.info("*** RUN START *** --- "+result.getName());     // ----- this happens before init Extentreport. won't work	
		} catch (Exception e) {
			System.out.println("*** RUN START *** --- "+result.getName());
		} 		
	}
	
	
	@Override
	public void onFinish(ITestContext result) {	
		ExtentLogger.info("*** RUN FINISH *** --- "+result.getName());      // ---- this happens after Extentreport teardown. won't work
		System.out.println("*** RUN FINISH *** --- "+result.getName());  
	}


	@Override
	public void onTestSuccess(ITestResult result) {
		// if extent report status is failed, also fail TestNG status. Otherwise it's a pass.
		if (ExtentReport.isExtReportFail()) {
			Assert.fail("Set test result to FAIL due to failed steps"); //if this is executed, it will be directed to onTestFailure. how convenient.
		} else {  //otherwise print PASSED in the report.
			ExtentLogger.pass("##### Test Case PASSED :) ##### --- "+result.getName());  
			System.out.println("##### Test Case PASSED :) ##### --- "+result.getName()); 
		}
	}

	
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		//get any exception messages that caused fail. this is GOLD!!!
	   if (null != result.getThrowable()) {       
		      String msg = result.getThrowable().getMessage(); 
		      if (!msg.equals("null")) {  //only print to ext report when msg is not "null" string
		    	  ExtentLogger.fail("*** OnFail Listener Message *** --- "+msg);
		      }
		    }
		
		ExtentLogger.fail("##### Test Case FAILED :( ##### --- "+result.getName());  
		System.out.println("##### Test Case FAILED :( ##### --- "+result.getName()); 
	}

	
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	
}
