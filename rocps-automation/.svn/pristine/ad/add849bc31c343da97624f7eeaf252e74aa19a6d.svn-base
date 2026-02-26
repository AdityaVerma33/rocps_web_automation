package com.subex.rocps.sprintTestCase.bklg114;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
public class HurExecution extends PSAcceptanceTest {
		@org.testng.annotations.Test(priority = 6)
		public void hurColumnHeaderValidation() throws Exception {

			try {
				String Path;
				String WorkbookName;
				String sheetName;
				String testCaseName;
				Path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
				WorkbookName = "TestData.xlsx";
				sheetName = "BKLG_114HURFileStatus";
				testCaseName = "HURFileStatusColumnsValidation";
				System.out.println(Path);
				HurFileStatus hurobj = new HurFileStatus();
				hurobj.HurFileColumnsVerification(Path, WorkbookName, sheetName, testCaseName);
				hurobj.searchScreenColumnsValidation();

			} catch (Exception e) {
				FailureHelper.reportFailure(e);
				throw e;
			}

		}
		@org.testng.annotations.Test(priority = 7)
		public void hurEmailVerificationAndFilterStatus() throws Exception {
			try{
				String Path;
				String WorkbookName;
				String sheetName;
				String testCaseName;
				Path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
				WorkbookName = "TestData.xlsx";
				sheetName = "BKLG_114HURFileStatus";
				testCaseName = "hurEmailVerificationAndFilterStatus";
				System.out.println(Path);
				HurFileStatus hurobj = new HurFileStatus();
				hurobj.HurFileColumnsVerification(Path, WorkbookName, sheetName, testCaseName);
				hurobj.filterCheckHurFileStatus();
				hurobj.validateDataInHURSearch();
				hurobj.emailActionClick();
				hurobj.popUpDataVerfication();
			}
			catch (Exception e) {
				FailureHelper.reportFailure(e);
				throw e;
			}
}
}
	

