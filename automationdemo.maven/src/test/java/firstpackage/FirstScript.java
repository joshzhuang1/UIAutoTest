package firstpackage;
	import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.FluentWait;
	import org.openqa.selenium.support.ui.Wait;
	import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
	
	
	public class FirstScript {
		static WebDriver driver;
		
		//Launch Chrome browser Method
		public void LaunchChrome(String url) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\JoshZhuang\\Desktop\\workdocs\\selenium\\Selenium\\WebDrivers\\chromedriver.exe");
			driver = new ChromeDriver(); //launch chrome browser 
			driver.get(url); //navigate the url
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
		}
		
		//do some operations Method
		public void UserOperations(String username1, String username2, String searchtext) throws InterruptedException {
			int myNum = 2000;
		
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			//get page title and assert
			String ActualTitle = driver.getTitle();
			String ExpTitle = "Trade Australian & International Shares Online - nabtrade";
			Assert.assertEquals(ActualTitle, ExpTitle); 
			
			
			driver.findElement(By.xpath("//a[@id='toggle-login']")).click();
			Thread.sleep(myNum);
			driver.findElement(By.xpath("//input[@id='usernameField']")).sendKeys(username1); //find by xpath
			Thread.sleep(myNum);
			driver.findElement(By.id("usernameField")).clear(); //clear the field
			driver.findElement(By.id("usernameField")).sendKeys(username2); //find same thing by id
			Thread.sleep(myNum);
			driver.findElement(By.xpath("//li[@id='btn-search']")).click();
			Thread.sleep(myNum);
			 
			WebElement searchbox = driver.findElement(By.xpath("//input[@id='input-search']"));
			searchbox.sendKeys(searchtext);
			
			//get some attributes of the element
			String Buttontext = driver.findElement(By.xpath("//div[@id='btn-search-submit']")).getText();
			String buttonID = driver.findElement(By.xpath("//div[@id='btn-search-submit']")).getAttribute("id");
			driver.findElement(By.xpath("//div[@id='btn-search-submit']")).click();
			Thread.sleep(3000);
			
			//print
			System.out.println("The page title is: " + driver.getTitle());			
			System.out.println("text shown on the button: " + Buttontext + " " + buttonID);
		}
		
		//navigate to url
		public void navigateurl(String url) throws InterruptedException {
			Thread.sleep(2000);
			driver.navigate().to(url);
			Thread.sleep(2000);
		}
		
		//close browser method
		public void closebrowser() {
			driver.quit();
		}
		
		//scroll screen -- this is not support by element locator, so need to use javascript
		public void scrollscreen() {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\JoshZhuang\\Desktop\\workdocs\\selenium\\Selenium\\WebDrivers\\chromedriver.exe");
			driver = new ChromeDriver(); //launch chrome browser 
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			driver.get("https://www.amazon.com.au");
			js.executeScript("window.scrollBy(0,1000)");	
		}
		
		@Test
		//main
		public static void main(String[] args) throws InterruptedException{
		
			//create new object for FirstScript class
			FirstScript obj = new FirstScript(); 
			
			//set variables
			String username1 = "ElonMusk";
			String username2 = "FakeTears";
			String searchtext = "Margin Lending";
			String MyUrl1 = "https://www.nabtrade.com.au/investor/home";
			String MyUrl2 = "https://tictoc.com.au";
				 
		   // obj.scrollscreen();	
			obj.LaunchChrome(MyUrl1); //Call method to launch Chrome
			obj.UserOperations(username1,username2,searchtext); // do some operations
			obj.navigateurl(MyUrl2); //navigate to another website
			
			//mouse hover over
			Actions action = new Actions(driver); //init Actions class
			WebElement CalcDropDown = driver.findElement(By.xpath("//span[text()='Calculators']")); //Specify webelement
			action.moveToElement(CalcDropDown).perform(); //Find the element and hover over
			Thread.sleep(1000);
			
			//////////////////////////explicit wait//////////////////////////////////////////////
			WebDriverWait wait = new WebDriverWait(driver, 5); // create new WebDriverWait object. maximum time 5s 
			WebElement ApplyButton; // create new webelement.
			
			//below Wait max. 5s for the webelement to show (condition = visibilityOfElementLocated). If not throw error.
			ApplyButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='button button--primary']/span[contains(text(), 'Apply')]")));
			
			//get font size of the element
			String fontfamily = ApplyButton.getCssValue("font-family");
			System.out.println(fontfamily);	
			
			ApplyButton.click();
			Thread.sleep(3000);
			//////////////////////////explicit wait////////////////////////////////	//////////////	
			
			obj.closebrowser(); //close browser
		}
}
