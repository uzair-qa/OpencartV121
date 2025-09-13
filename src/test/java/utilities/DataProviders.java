package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "LoginData")
	public String [][] getData() throws IOException {
		String path = ".\\testData\\Opencart_LoginData.xlsx";	//taking xl file from testData
		
		ExcelUtility xlUtil = new ExcelUtility(path); 	//creating 
		
		int totalrows = xlUtil.getRowCount("Sheet1");
		int totalcols = xlUtil.getCellCount("Sheet1",1);
		
		String loginData[][] = new String[totalrows][totalcols];
		
		for(int i=1; i<=totalrows; i++) {		//1	//read data from xl storing i (ignoring header)
			for(int j=0; j<totalcols; j++) {	//0	//i--> rows 	j--> col
				loginData[i-1][j] = xlUtil.getCellData("Sheet1", i, j);		//1,0 (storing from 1-1=0; i=0) so that i=0 is not wasted
			}
		}
		return loginData;	//returning 2D array
	}
	
	//DataProvider 2
	
	//DataProvider 3

	//DataProvider 4
}