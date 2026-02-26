package com.subex.rocps.sprintTestCase.bklg136;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TestCaseExec extends PSAcceptanceTest{
	@org.testng.annotations.Test

	public void newCurrency() throws Exception{
		
		try {
			String Path;
			String WorkbookName;
			String sheetName;
			String testCaseName;
			Path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
			WorkbookName = "PSSprintFunctional_ExcelData.xlsx";
			sheetName = "BKLG136_ImfCurrency";
			testCaseName = "newImfCurrency";
			
			ImfCurrency newcur= new ImfCurrency(Path, WorkbookName, sheetName, testCaseName);		
			newcur.newImfCurrency();
			}	
		catch(Exception e) {
			throw e;
			}
	}
	public void editCurrency() throws Exception{
		
		try {
			String Path;
			String WorkbookName;
			String sheetName;
			String testCaseName;
			Path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
			WorkbookName = "PSSprintFunctional_ExcelData.xlsx";
			sheetName = "BKLG136_ImfCurrency";
			testCaseName = "editImfCurrency";
			
			ImfCurrency newcur= new ImfCurrency(Path, WorkbookName, sheetName, testCaseName);		
			newcur.editImfCurrency();
			}
		catch(Exception e) {
			throw e;
			}
	}
}