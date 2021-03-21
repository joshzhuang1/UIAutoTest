package firstpackage;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utilities.auto.ExcelDataConfig;

public class ReadDataDemo2 { // 2 different ways of loading datatable 
	    
	
		@DataProvider(name = "calcdata1")
		public Object[][] passData1() {  //return 2-dim array 
			ExcelDataConfig inputsheet = new ExcelDataConfig("datatable/CalcInput.xlsx","Sheet1");
			
			//the data table has 1 header row, 3 data rows, 4 columns
			int rowindex = inputsheet.getRowCount(); // row starts from 0, so rowcount = 3
			Object[][] data = new Object[rowindex+1][4]; //need rowcount = rowindex+1 for data[][]!!
			
			for (int i=0; i<=rowindex; i++) { // i=0: read data including header!!! 
				data[i][0] = inputsheet.getData(i, 0);
				data[i][1] = inputsheet.getData(i, 1);
				data[i][2] = inputsheet.getData(i, 2);
				data[i][3] = inputsheet.getData(i, 3);				
			}
			return data;
		}
		
		
		
		@DataProvider(name = "calcdata2")
		public Object[][] passData2() {  //return 2-dim array 
			ExcelDataConfig inputsheet = new ExcelDataConfig("datatable/CalcInput.xlsx","Sheet1");
			Object data[][] = inputsheet.getTestData();
			return data;
		}
		
		
		
		@Test(dataProvider ="calcdata1")
		public void readData(String un, String pw, String df, String er){		
			
			System.out.println(un+" "+pw+" "+df+" "+er);
		}
		

		
	
}
