package seleniumgrid;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SetupRunTimeDocker {

	@BeforeTest
	void startDockerGrid() throws Exception {
		String cmdline = System.getProperty("user.dir")+"\\docker\\start_dockergrid.bat"; //get the batch file path
		Runtime.getRuntime().exec("cmd /c start "+cmdline);		
		Thread.sleep(15000);
	}
	
	
	@AfterTest //test
	void stopDockerGrid() throws Exception {
		String cmdline = System.getProperty("user.dir")+"\\docker\\stop_dockergrid.bat"; //get the batch file path
		Runtime.getRuntime().exec("cmd /c start "+cmdline);	
		Thread.sleep(5000);
		
		Runtime.getRuntime().exec("taskkill /f /im cmd.exe"); //close command prompt
	}
}
