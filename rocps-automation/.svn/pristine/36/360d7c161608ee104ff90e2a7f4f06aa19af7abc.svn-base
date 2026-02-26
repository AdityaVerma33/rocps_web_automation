package com.subex.rocps.sprintTestCase.bklg16;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TestCaseExec extends PSAcceptanceTest {

	
	  
	 	
	@org.testng.annotations.Test(priority = 1)
	public void OOTBReportsDispite() throws Exception {

		try {
			String Path;
			String WorkbookName;
			String sheetName;
			
			String testCaseName;
			Path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
			WorkbookName = "TestData.xlsx";
			sheetName = "OOTBReportsDispute_sheet8";
			testCaseName = "DisputeReport";
			DisputeReport disputeobj = new DisputeReport();
			disputeobj.disputeReport(Path, WorkbookName, sheetName, testCaseName);

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}

	}
	
	
	
	
	
	

}
