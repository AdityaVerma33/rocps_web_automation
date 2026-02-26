package com.subex.rocps.sprintTestCase.bklg296;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TestCaseExec extends PSAcceptanceTest{
	@org.testng.annotations.Test

	public void verifyPopupGrid() throws Exception{
		
		try {
			String Path;
			String WorkbookName;
			String sheetName;
			String testCaseName;
			Path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
			WorkbookName = "PSSprintFunctional_ExcelData.xlsx";
			sheetName = "BKLG296_ImfImportScreen";
			testCaseName = "verifyGrid";
			
			ImfExchangeRateImportScreen verifypopupData= new ImfExchangeRateImportScreen(Path, WorkbookName, sheetName, testCaseName);		
			verifypopupData.verifyPopupGrid();
			}	
		catch(Exception e) {
			throw e;
			}
	}
}