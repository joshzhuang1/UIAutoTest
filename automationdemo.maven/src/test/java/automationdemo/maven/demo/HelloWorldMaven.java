package automationdemo.maven.demo;

import java.util.Random;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HelloWorldMaven {

	@Parameters({"param1","param2"})
	@Test
	public void testHello(String text1,String text2) {
		
		System.out.println("$$$$$$$$?????$$$$$$$HELLO MAVEN WORLD$$$$$$?????$$$$$$$$"+text1+" ############### "+text2);
		Random rand = new Random(); //instance of random class
	      int upperbound = 999999999;
	        //generate random values from 0-9999999
	      int int_random = rand.nextInt(upperbound); 
	      System.out.println(Integer.toString(int_random)+" random string@@@@@@@@@@@@@@@@@@@@@@");
	}
}
