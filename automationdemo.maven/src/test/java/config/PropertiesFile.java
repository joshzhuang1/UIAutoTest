/**
 * 
 */
package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * @author hoshi
 *
 */
public class PropertiesFile {

	
	static Properties prop = new Properties();
	static String configfile = "src/test/java/config/config.properties";
	
	
	//get property value from configfile as per propname
	public static String getPropValue(String propname) {		
		InputStream input;
		try {
			input = new FileInputStream(configfile);
			prop.load(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return(prop.getProperty(propname)); //get property values from configfile

	}
	
	
	//set property value in configfile
	public static void setPropValue(String propname, String propvalue) {
		
		try {
			
			OutputStream output = new FileOutputStream(configfile);
			prop.setProperty(propname, propvalue); //set property name and value
			prop.store(output, null); //save file
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
}
