/**
 * 
 */
package com.common.TestReporting;

import com.aventstack.extentreports.ExtentTest;

/**
 * @author JoshZhuang
 *
 */
public class ExtentManager {

	//constructor
	private ExtentManager(){
		//do nothing here. Do NOT allow to init this class from outside.
	}

	//to solve parrellel testing issue. when 2 tests are running at the same time. Extent Report will log correctly.
	private static ThreadLocal<ExtentTest> extTest = new ThreadLocal<>(); 
	
	
	//Below methods are only accessible within the com.common.TestReporting package, as you don't want general testscripts to call them directly
	//static ---> can be only called within the package. --> private to package
	static ExtentTest getExtentTest() {
		return extTest.get();
	}
	
	
	static void setExtentTest(ExtentTest test) {
		extTest.set(test);
	}
	
	
	static void unload() {
		extTest.remove();
	}
	
}
