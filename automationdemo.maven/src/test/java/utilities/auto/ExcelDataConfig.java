/**
 * 
 */
package utilities.auto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

/**
 * @author JoshZhuang
 * use Apache POI to handle external xlsx file as input datatable
 * for Data Driven Testing
 */
public class ExcelDataConfig {

	XSSFWorkbook inputworkbook; 
	XSSFSheet inputsheet;
	
	//construtor!!! to init datatable
	public ExcelDataConfig(String excelPath,String sheetName) {
		
		try {
			File src = new File (excelPath);
			
			FileInputStream fis = new FileInputStream(src); //obtains input bytes from a file (reading)		
 
			//XSSFWorkbook - the first object most users will construct reading/writing xlsx. 
			//It is also the top level object for creating new sheets/etc.	
			inputworkbook = new XSSFWorkbook(fis); 
		
			//XSSFSheet inputsheet = wb.getSheetAt(0); //sheet object. index = 0 means first sheet i.e. Sheet1
			inputsheet = inputworkbook.getSheet(sheetName);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	
	//get cell value from datatable
	public String getData(int row, int column) {
		
		String data = inputsheet.getRow(row).getCell(column).getStringCellValue();
		
		return data;
		
	}
	
	
	//Note assuming row 0 (first row) in datatable is always header that is not counted! 
	//So data starts from row 1 (second row)
	public int getRowCount() {
		return inputsheet.getLastRowNum();
	}
	
	
	
	//get full test data set (excluding 1st row, which is header)
	public Object[][] getTestData() {
		
		int rowindex = inputsheet.getLastRowNum(); // get rowindex = # of rows with data (excluding header)
		int colindex = inputsheet.getRow(0).getLastCellNum(); //get # of cols 
		Object[][] data = new Object[rowindex][colindex]; // 2-dim array
		
		if (rowindex < 1) {
			Assert.fail("############## No data found from input sheet. please check source file! #################");
		}
		
		for (int i=0; i<rowindex; i++) {
			for (int j=0; j<colindex; j++) {
				data[i][j] = inputsheet.getRow(i+1).getCell(j).toString(); // data starts from row 1 hence getRow(i+1). first row is header
			}		
		}
		
		return data;
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
