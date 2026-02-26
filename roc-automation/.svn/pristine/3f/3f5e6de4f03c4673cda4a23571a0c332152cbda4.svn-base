package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class AlertsHelper extends ROCAcceptanceTest {
	
	public void verifyAlertInstance(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Alert Group").size(); i++)
			{
				String alertGroup = excelData.get("Alert Group").get(i);
				String raisedType = excelData.get("Raised Type").get(i);
				String raisedFrom = excelData.get("Raised From Date").get(i);
				String raisedTo = excelData.get("Raised To Date").get(i);
				String alertNo = excelData.get("Alert No").get(i);
				String alertSource = excelData.get("Alert Source").get(i);
				String alertText = excelData.get("Alert Text").get(i);
				int resultCount = testData.getIntegerValue(excelData.get("Result Count").get(i));
				
				verifyAlertInstance(alertGroup, raisedType, raisedFrom, raisedTo, alertNo, alertSource, alertText, resultCount);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private int verifyResult(int row, String value, String columnHeader) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				boolean isPresent = false;
				
				if (row > 0) {
					String actualValue = GridHelper.getCellValue("SearchGrid", row, columnHeader);
					if (actualValue.contains(value))
						isPresent = true;
				}
				
				if (row == 0 || !isPresent) {
					int rows = GridHelper.getRowCount("SearchGrid");
					
					if (rows > 0) {
						for (int i = 0; i < rows; i++) {
							String actualValue = GridHelper.getCellValue("SearchGrid", (i+1), columnHeader);
							
							if (actualValue.contains(value)) {
								isPresent = true;
								break;
							}
						}
					}
					else
						FailureHelper.failTest("Alert Instance not found for " + columnHeader + " '" + value + "'");
				}
				
				if (isPresent)
					Log4jHelper.logInfo("Alert Instance found for " + columnHeader + " '" + value + "'");
				else
					FailureHelper.failTest("Alert Instance not found for " + columnHeader + " '" + value + "'");
			}
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyAlertInstance(String alertGroup, String raisedType, String raisedFrom, String raisedTo, String alertNo, String alertSource,
			String alertText, int resultCount) throws Exception {
		try {
			int row = navigateToAlerts(alertGroup, raisedType, raisedFrom, raisedTo, alertNo);
			
			row = verifyResult(row, alertSource, "Alert Source");
			
			verifyResult(row, alertText, "Alert Text");
			
			int rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows >= resultCount);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public int navigateToAlerts(String alertGroup, String raisedType, String raisedFrom, String raisedTo, String alertNo) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Alerts", "Alert Instance Search");
			
			if (ValidationHelper.isNotEmpty(raisedType)) 
				CalendarHelper.setDate("Alerts_Raised", raisedType, raisedFrom, raisedTo);
			else
				CalendarHelper.setToday("Alerts_Raised");
			
			if (ValidationHelper.isNotEmpty(alertGroup))
				SearchGridHelper.searchWithComboBox("Alerts_AlertGroup", alertGroup);
			else {
				ButtonHelper.click("Search");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
			
			int row = 0;
			if (ValidationHelper.isNotEmpty(alertNo))
				row = GridHelper.getRowNumber("SearchGrid", alertNo, "Alert No");
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}