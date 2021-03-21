/**
 * 
 */
package com.tictoc.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author JoshZhuang
 *
 */
public class TestDependencyDemo {

	
	@Test
	public void startApp() {
		System.out.println("starting up app");
		
	}
	
	
	@Test(dependsOnMethods="startApp") //if startApp() fails, this test is skipped
	public void loginApp() {
		System.out.println("login to app");
		Assert.assertEquals(12,13); //fail test case
	}
	
	
	@Test(dependsOnMethods="loginApp") //if loginApp() fails, this test is skipped
	public void logoutApp() {
		System.out.println("log off");
	}
	
}
