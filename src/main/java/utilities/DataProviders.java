package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	String path="./src/test/resources/mobilestoreTestData.xlsx";
	ExcelUtil xl=new ExcelUtil(path);

	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException
	{
		
	
		int rownum=xl.getRowCount("Sheet1");	
		int colcount=xl.getCellCount("Sheet1",1);
		
		String testdata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{		
			for(int j=0;j<colcount;j++)
			{
				testdata[i-1][j]= xl.getCellData("Sheet1",i, j);  
			}
		}
	
		return testdata;
	}
	
	@DataProvider(name="Names")
	public String[] getUserNames() throws IOException
	{
	
		int rownum=xl.getRowCount("Sheet1");	
			
		String testdata[]=new String[rownum];
		
		for(int i=1;i<=rownum;i++)
		{		
			testdata[i-1]= xl.getCellData("Sheet1",i, 0);  
			
		}
	
		return testdata;
	}
	
}
