/**
 * 
 */
package firstpackage;

import java.io.IOException;
import java.util.Random;

import org.testng.annotations.DataProvider;

import utilities.auto.DataManager;
import utilities.auto.ToolBox;


/**
 * @author JoshZhuang
 *
 */


public class ReadDataManager {

	
	static String inputdata = "testdata/dataEXP.xlsx";
	
	public static void main(String[] args) throws IOException {

		//init a new DataManager Object using excel datasheet.
		DataManager inputsheet = new DataManager(inputdata,"Sheet1");
		
		// uft-like data operation. 
		int rcount = inputsheet.getRowCount();	// 1. get datatable rowcount. 
		for (int i=1; i<=rcount; i++) {   
			inputsheet.setCurrentRow(i); // 2. set current row
			String val1 = inputsheet.getData("companyname");  //3. read data using col name
			String val2 = inputsheet.getData("slogan");
			String val3 = inputsheet.getData("colour");
			String val4 = inputsheet.getData("logoid");
			String val5 = inputsheet.getData("price");
			
			System.out.println(val1+" "+val2+" "+val3+" "+val4+" "+val5);
			
			//this directly writes random number string into excel "price" column!
			inputsheet.setData("price", ToolBox.getRndNumStr(3)); 
		}

		System.out.println("==========================================");
		
		//below is demo to import and export data from/to excel
		//2D array to accept data from excel
		Object data[][] = inputsheet.getTestData();	
		
	    data[2][3] = ToolBox.getRndNumStr(8);
	    data[1][3] = ToolBox.getRndNumStr(5);
	    
		
		int rowNum = inputsheet.getRowCount();
		
		for (int i=1; i <= rowNum; i++) {
			String companyname = (String) data[i][0];
			String slogan = (String) data[i][1];
			String colour = (String) data[i][2];
			String logoid = (String) data[i][3];
			String price = (String) data[i][4];
			
			System.out.println(companyname+" "+slogan+" "+colour+" "+logoid+" "+price);
		}
		
		 System.out.println(inputsheet.getData(2, 3));
		//export data to exel
		inputsheet.exportData(data,inputdata);
		
//		System.out.println("sthdddd");
	}
}
