package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.data.DataAssertion;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class KPIResultsHelper extends ROCAcceptanceTest{
	
	public void verifyKPIResult(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("KPI Name").size(); i++)
			{
				String kpiName = excelData.get("KPI Name").get(i);
				String measureRequest = excelData.get("Measure Request").get(i);
				
				String resultPeriodType = excelData.get("Result Period Type").get(i);
				String fromPeriod = excelData.get("From Period").get(i);
				String toPeriod = excelData.get("To Period").get(i);
				int resultCount = testData.getIntegerValue(excelData.get("Result Count").get(i));
				
				String resultExcelPath = excelData.get("Result Excel Path").get(i);
				String resultExcelFilename = excelData.get("Result Excel Filename").get(i);
				String resultExcelSheetname = excelData.get("Result Excel Sheetname").get(i);
				String resultExcelTCName = excelData.get("Result Excel TCName").get(i);
				
				String valueToCheckKPIValue = excelData.get("Value To Check KPI Value").get(i);
				String valueColumnHeader = excelData.get("Value Column Header").get(i);
				int valueResultCount = testData.getIntegerValue(excelData.get("Value Result Count").get(i));
				String kpiValueExcelPath = excelData.get("KPI Value Excel Path").get(i);
				String kpiValueExcelFilename = excelData.get("KPI Value Excel Filename").get(i);
				String kpiValueExcelSheetname = excelData.get("KPI Value Excel Sheetname").get(i);
				String kpiValueExcelTCName = excelData.get("KPI Value Excel TCName").get(i);
				
				verifyKPIResult(kpiName, measureRequest, resultPeriodType, fromPeriod, toPeriod, resultCount, resultExcelPath, resultExcelFilename,
						resultExcelSheetname, resultExcelTCName, valueToCheckKPIValue, valueColumnHeader, valueResultCount, kpiValueExcelPath,
						kpiValueExcelFilename, kpiValueExcelSheetname, kpiValueExcelTCName);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyKPIResult(String kpiName, String measureRequest, String resultPeriodType, String fromPeriod, String toPeriod,
			int resultCount, String resultExcelPath, String resultExcelFilename, String resultExcelSheetname, String resultExcelTCName,
			String valueToCheckKPIValue, String valueColumnHeader, int valueResultCount, String kpiValueExcelPath, String kpiValueExcelFilename,
			String kpiValueExcelSheetname, String kpiValueExcelTCName) throws Exception {
		try {
			NavigationHelper.navigateToScreen("KPI Results", "KPI Result Search");
			SearchGridHelper.searchWithAdvancedSearch("KPIResults_KPI", kpiName, "KPI Name");
			SearchGridHelper.searchWithAdvancedSearch("KPIResults_MeasureRequest", measureRequest, "Measure Request");
			CalendarHelper.setDate("KPIResults_ResultPeriod", resultPeriodType, fromPeriod, toPeriod);
			
			checkKPIResult(resultCount, resultExcelPath, resultExcelFilename, resultExcelSheetname, resultExcelTCName, valueToCheckKPIValue,
					valueColumnHeader, valueResultCount, kpiValueExcelPath, kpiValueExcelFilename, kpiValueExcelSheetname, kpiValueExcelTCName);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void checkKPIResult(int resultCount, String resultExcelPath, String resultExcelFilename, String resultExcelSheetname, String resultExcelTCName,
			String valueToCheckKPIValue, String valueColumnHeader, int valueResultCount, String kpiValueExcelPath, String kpiValueExcelFilename,
			String kpiValueExcelSheetname, String kpiValueExcelTCName) throws Exception {
		try {
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			int rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows >= resultCount, "Expected number of rows '" + resultCount + "' is not found in KPI Result screen.");
			
			if(ValidationHelper.isNotEmpty(resultExcelPath)) {
				DataAssertion dataAssertion = new DataAssertion();
				dataAssertion.testDataAssertion(resultExcelPath, resultExcelFilename, resultExcelSheetname, resultExcelTCName);
			}
			
			if (ValidationHelper.isNotEmpty(valueToCheckKPIValue)) {
				GridHelper.clickRow("SearchGrid", valueToCheckKPIValue, valueColumnHeader);
				NavigationHelper.navigateToAction("Kpi Result Actions", "View KPI Values");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
				rows = GridHelper.getRowCount("SearchGrid");
				assertTrue(rows == valueResultCount, "Expected '" + valueResultCount + "' rows. But found '" + rows + "' rows.");
				
				if(ValidationHelper.isNotEmpty(kpiValueExcelPath)) {
					DataAssertion dataAssertion = new DataAssertion();
					dataAssertion.testDataAssertion(kpiValueExcelPath, kpiValueExcelFilename, kpiValueExcelSheetname, kpiValueExcelTCName);
				}
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}