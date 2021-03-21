package com.tictoc.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.tictoc.pages.HomePage;
import com.tictoc.pages.RefiCalcPageFactory;

import utilities.auto.BrowserFactory;
public class VerifySavingsTestNG {

	
	@Test
	public void VerifyRefiSavings() throws InterruptedException, IOException {
		
		String browsername = "chrome";
		String url = "https://tictoc.com.au";
		
		//Step 1 Launch Chrome and go to tictoc
		WebDriver ldriver = BrowserFactory.initBrowser(browsername);
		ldriver.manage().window().maximize(); //maximise window
		ldriver.get(url); //navigate to *url*
		Thread.sleep(2000);
		
		//init Homepage class (Normal POM)
		HomePage homepage = new HomePage(ldriver); 
		
		//init RefiCalcPageFactory class (page factory)
		RefiCalcPageFactory RefiCalcPage = PageFactory.initElements(ldriver, RefiCalcPageFactory.class);
		
		//Step 2 Navigate to refi calculator
		homepage.GotoRefiCalc(); 
		Thread.sleep(2000);
		
		String laonamount = "276622";
		String currentrate = "2.191";
		String remyears = "11";
		String breakevenrate = "2.19";
		
		//Step 3 enter values and calculate
		RefiCalcPage.CalcRefiSavings(laonamount, currentrate, remyears, breakevenrate);

		Thread.sleep(2000);
		
		//Step 4 close browser
		ldriver.quit();		
		
	}
	
	
}
