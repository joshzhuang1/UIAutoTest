/**
 * 
 */
package utilities.auto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

/**
 * @author JoshZhuang
 * use Apache POI to handle external xlsx file as input datatable
 * for Data Driven Testing
 */
public class DataManager {

	XSSFWorkbook inputworkbook; 
	XSSFSheet inputsheet;
	int currentrow; //enable set current row.

	
	//construtor!!! to init datatable
	public DataManager(String excelPath,String sheetName) {
		
		try {
			File src = new File (excelPath);
			
			FileInputStream fis = new FileInputStream(src); //obtains input bytes from a file (reading)		
 
			//XSSFWorkbook - the first object most users will construct reading/writing xlsx. 
			//It is also the top level object for creating new sheets/etc.	
			inputworkbook = new XSSFWorkbook(fis); 
		
			//XSSFSheet inputsheet = wb.getSheetAt(0); //sheet object. index = 0 means first sheet i.e. Sheet1
			inputsheet = inputworkbook.getSheet(sheetName);
			
			currentrow = 1; //by default it is set to row 1.
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	

	//get cell value from excel sheet
	public String getData(int row, int column) {
		
		String data = inputsheet.getRow(row).getCell(column).getStringCellValue();		
		return data;
		
	}
	
	
	//set cell value to excel sheet
	public void setData(int row, int column, String value) {	
		inputsheet.getRow(row).getCell(column).setCellValue(value);
		
	}
	


	/*
	 * below datatable handling methods are UFT-like  
	 * 1. getrowcount 2.setcurrentrow. 3. once row is set, get or set data using column name
	 */
	
	//Note assuming row 0 (first row) in datatable is for headers which is not counted.
	//So data starts from row 1 (second row)
	public int getRowCount() {
		return inputsheet.getLastRowNum();
	}
	
	
	//set current row
	public void setCurrentRow(int i) {
		int maxrow = getRowCount();
		if (i <= maxrow) {
			this.currentrow = i;
		}else {
			close(); //always close workbook before throwing error
			Assert.fail("Input datasheet has "+maxrow+" row(s) of valid data. Nothing found in Row #"+i);
		}	
	}
	
	
	//get cell value from datatable using column name. Need to call setCurrentRow in prior
	public String getData(String colname) {
		int colcount = inputsheet.getRow(0).getLastCellNum(); //get column count
		String data = null;
		for (int i=0; i<=colcount; i++ ) {
			if (colname.equalsIgnoreCase(getData(0,i))) {  //loop headers row to compare column name
//				data = inputsheet.getRow(this.currentrow).getCell(i).getStringCellValue();
				data = getData(this.currentrow,i); //if column name is found, get data using "current row"
				return data;
			}
			if (i == (colcount-1)) {
				close(); //always close workbook before throwing error
				Assert.fail("Unable to GET data. Colomn name = '"+colname+"' is NOT found in input datasheet!");
			}
		}	
		return data;
	}
	
	
	//set cell value in excel datatable using column name. Need to call setCurrentRow in prior
	public void setData(String colname, String val) {
		int colcount = inputsheet.getRow(0).getLastCellNum(); //get column count
		for (int i=0; i<=colcount; i++ ) {
			if (colname.equalsIgnoreCase(getData(0,i))) {  //loop headers row to compare column name
				setData(this.currentrow,i,val);  //if column name is found, set data using "current row"
				return;
			}
			if (i == (colcount-1)) {
				close(); //always close workbook before throwing error
				Assert.fail("Unable to SET data. Colomn name = '"+colname+"' is NOT found in input datasheet!");
			}
		}	
	}
	
	
	
	
	/*
	 * below datatable handling methods need to create a 2-D array in the test
	 * 1. 2d array = getTestData 
	 * 2. set data in 2d array whenever needed
	 * 3. exportData back to excel sheet.
	 */
	
	//get full test data set (excluding 1st row, which is header)
	public Object[][] getTestData() {
		
		int rowindex = inputsheet.getLastRowNum()+1; // get total # of rows with data. getLastRowNum actually starts from 0, so need to +1
		int colindex = inputsheet.getRow(0).getLastCellNum(); //get # of cols 
		Object[][] data = new Object[rowindex][colindex]; // 2-dim array
		
		for (int i=0; i < (rowindex); i++) {
			for (int j=0; j<colindex; j++) {
				try {
					data[i][j] = inputsheet.getRow(i).getCell(j).toString(); // data starts from row 1 hence getRow(i+1). first row is header
				} catch (Exception e) {
					// TODO Auto-generated catch block
					data[i][j] = ""; // if that data cell is null, return ""
//					e.printStackTrace();
				}
			}		
		}
		
		return data;
	}
	
	
	
	//export data to xlsx workbook
	public void exportData(Object[][] data, String excelPath) throws IOException {
 
		int rowcount = data.length;  //row count of input data
		int colcount = data[0].length;   //column count of input data
		
		for (int i=0; i < rowcount; i++) {
			Row row = inputsheet.createRow(i); //create row object for the sheet
			for (int j = 0; j < colcount; j++) {
					Cell cell = row.createCell(j);  //for rows, create cells on each nj
					cell.setCellValue((String)data[i][j]);
				}
			}					
		//save to excel sheet
		FileOutputStream fos = new FileOutputStream(excelPath);
		inputworkbook.write(fos);
		inputworkbook.close();
	}
	
	
	//close workbook
	public void close(){
		try {
			inputworkbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	
}
