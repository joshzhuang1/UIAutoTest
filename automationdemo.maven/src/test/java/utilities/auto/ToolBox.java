/**
 * 
 */
package utilities.auto;

import java.util.Objects;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.common.TestReporting.ExtentLogger;

/**
 * @author JoshZhuang
 *
 */
public class ToolBox {
	
	WebElement webelemnt;
	
		//constructor
		public ToolBox(){
			//do nothing here. Do NOT allow to init this class from outside.
		}
		
		
		
		//check if object exists. return true or false
		public static boolean objectExists(WebElement webelement) {
			    try {
					webelement.isDisplayed();
						System.out.println("object does exist.");
						return true;
				} catch (Exception e) {
					System.out.println("object does NOT exist.");
					return false;
				}
		        	
		    }	
		
		
		
		//explicit wait for a webelement to show up within x seconds,if not shown, print info, return false. otherwise return true
		public static boolean waitFor(WebElement webelement, int sec) {
			WebDriver driver;
			
			//init driver by getting current driver details
			driver = DriverFactory.getInstance().getDriver();
				
			WebDriverWait wait = new WebDriverWait(driver, sec); // create new WebDriverWait object. maximum time 5s 
			//below Wait max. sec seconds for the webelement to show (condition = visibilityOf webelement). If not throw error.
			try {
				wait.until(ExpectedConditions.visibilityOf(webelement));
//				ExtentLogger.info("Webelement is displayed within "+sec+" seconds."); 
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				ExtentLogger.info("Webelement is NOT displayed. Waiting time "+sec+" seconds."); //if not show, print to ext report
			    return false;
			}
		}
		
		
		
		//save methods as above but passing By parameter
		public static boolean waitFor(By locator, int sec) {
			WebDriver driver;
			
			//init driver by getting current driver details
			driver = DriverFactory.getInstance().getDriver();
				
			WebDriverWait wait = new WebDriverWait(driver, sec); // create new WebDriverWait object. maximum time 5s 
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//				ExtentLogger.info("Webelement is displayed within "+sec+" seconds."); 
				return true;
			} catch (Exception e) {
//				ExtentLogger.info("Webelement is NOT displayed. Waiting time "+sec+" seconds."); //if not show, print to ext report
			    return false;
			}
		}
		
		
		
		//explicit wait for a webelement to disappear within x seconds,if still shows, print info, return false. otherwise return true
		public static boolean waitforDisappear(WebElement webelement, int sec) {
			WebDriver driver;
			
			//can be either local driver or remote driver
			driver = DriverFactory.getInstance().getDriver();
				
			WebDriverWait wait = new WebDriverWait(driver, sec); // create new WebDriverWait object. maximum time 5s 
			
			//below Wait max. sec seconds for the webelement to disappear
			try {
				wait.until(ExpectedConditions.invisibilityOf(webelement));
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				ExtentLogger.warning("Webelement not disappeared! Waiting time "+sec+" seconds."); //if not show, print to ext report
			    return false;
			}
		}
		
		
		//check if alert exists
		public static boolean isAlertPresent() {
			WebDriver driver = DriverFactory.getInstance().getDriver(); //get current webdriver instance!!! this only works with threadlocal instance
		    try 
		    { 
		        driver.switchTo().alert(); 
		        return true; 
		    }   // try 
		    catch (NoAlertPresentException Ex) 
		    { 
		        return false; 
		    }   // catch 
		}   // isAlertPresent()
		
		
		
		//get random number string
		public static String getRndNumStr(int digCount) {
			Random rnd = new Random();
			StringBuilder sb = new StringBuilder(digCount);
		    for(int i=0; i < digCount; i++)
		        sb.append((char)('0' + rnd.nextInt(10)));
		    return sb.toString();
		}
		
		
		//get caller's class name
	    public static String getCallerClassName() { 
	        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
	        for (int i=1; i<stElements.length; i++) {
	            StackTraceElement ste = stElements[i];
	            if (!ste.getClassName().equals(ToolBox.class.getName()) && ste.getClassName().indexOf("java.lang.Thread")!=0) {
	                return ste.getClassName();
	            }
	        }
	        return null;
	     }
		
	  //get caller's class name
	    public static String getCallerCallerClassName() { 
	        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
	        String callerClassName = null;
	        for (int i=1; i<stElements.length; i++) {
	            StackTraceElement ste = stElements[i];
	            if (!ste.getClassName().equals(ToolBox.class.getName())&& ste.getClassName().indexOf("java.lang.Thread")!=0) {
	                if (callerClassName==null) {
	                    callerClassName = ste.getClassName();
	                } else if (!callerClassName.equals(ste.getClassName())) {
	                    return ste.getClassName();
	                }
	            }
	        }
	        return null;
	     }
}


