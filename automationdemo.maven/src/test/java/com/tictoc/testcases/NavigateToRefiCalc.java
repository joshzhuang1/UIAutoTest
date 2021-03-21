/**
 * 
 */
package com.tictoc.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.tictoc.pages.HomePage;
import com.tictoc.pages.RefiCalcPage;

/**
 * @author JoshZhuang
 *
 */
public class NavigateToRefiCalc {

	@Test
//	public void VerifyValidLogin() throws InterruptedException {
//	System.setProperty("webdriver.chrome.driver", "C:\\Users\\JoshZhuang\\Desktop\\workdocs\\selenium\\Selenium\\WebDrivers\\chromedriver.exe");
//	WebDriver driver = new ChromeDriver();
//	driver.manage().window().maximize();
//	driver.get("https://tictoc.com.au"); //navigate the url
//	Thread.sleep(2000);
//
//	
//	HomePage homepage = new HomePage(driver); //init Homepage class
//	
//	homepage.GotoRefiCalc(); //Navigate to finance
//	
//	
//	Thread.sleep(1000);
//	
//	driver.quit();
//	}
	
	//main
	public static void main(String[] args) throws InterruptedException{
			
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\JoshZhuang\\Desktop\\workdocs\\selenium\\Selenium\\WebDrivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(); //launch chrome
		driver.manage().window().maximize();
		driver.get("https://tictoc.com.au"); //navigate the url
		Thread.sleep(2000);

		
		HomePage homepage = new HomePage(driver); //init Homepage class, passing chromedriver
		RefiCalcPage reficalcpage = new RefiCalcPage(driver); //init reficalcpage class, passing chromedriver
		
		//Step 1 Navigate to refi calculator
		homepage.GotoRefiCalc(); 
		Thread.sleep(2000);
		
		String laonamount = "376600";
		String currentrate = "2.55";
		String remyears = "12";
		String breakevenrate = "2.19";
		
		//Step 2 enter values and calculate
		reficalcpage.CalcRefiSavings(laonamount, currentrate, remyears, breakevenrate);
		
		driver.quit();
	}
}
