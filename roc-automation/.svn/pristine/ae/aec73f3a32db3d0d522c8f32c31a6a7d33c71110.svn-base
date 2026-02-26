package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.MeasureRequestHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.TreeHelper;
import com.subex.automation.helpers.data.DataAssertion;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class DataMatchMeasureRequest extends MeasureRequestHelper {
	
	public void createMeasureRequest(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Measure Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String measureName = excelData.get("Measure Name").get(i);
				boolean generateItems = ValidationHelper.isTrue(excelData.get("Generate Items").get(i));
				String description = excelData.get("Description").get(i);
				
				String fromDate = excelData.get("From Date").get(i);
				String toDate = excelData.get("To Date").get(i);
				
				String [] inputMeasure = testData.getStringValue(excelData.get("Input Measure").get(i), firstLevelDelimiter);
				String [] inputMeasureRequest = testData.getStringValue(excelData.get("Input Measure Request").get(i), firstLevelDelimiter);
				
				String [] sourceName = testData.getStringValue(excelData.get("Source Name").get(i), firstLevelDelimiter);
				String [][] filterExpressionType = testData.getStringValue(excelData.get("Filter Expression Type").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String [][] filterName = testData.getStringValue(excelData.get("Filter Name").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String [][] filterExpClause = testData.getStringValue(excelData.get("Filter Expression Clause").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String [][] filterExpLeftIndent = testData.getStringValue(excelData.get("Filter Expression Left Indent").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String [][] filterExpExpression1 = testData.getStringValue(excelData.get("Filter Expression Expression1").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String [][] filterExpOperator = testData.getStringValue(excelData.get("Filter Expression Operator").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String [][] filterExpExpression2 = testData.getStringValue(excelData.get("Filter Expression Expression2").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String [][] filterExpRightIndent = testData.getStringValue(excelData.get("Filter Expression Right Indent").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				boolean exactMatch = ValidationHelper.isTrue(excelData.get("Exact Match").get(i));
				boolean onlyCompare = ValidationHelper.isTrue(excelData.get("Only Compare").get(i));
				boolean overMatch = ValidationHelper.isTrue(excelData.get("Over Match").get(i));
				boolean underMatch = ValidationHelper.isTrue(excelData.get("Under Match").get(i));
				
				createMeasureRequest(partition, measureName, generateItems, description, fromDate, toDate, inputMeasure, inputMeasureRequest,
						sourceName, filterExpressionType, filterName, filterExpClause, filterExpLeftIndent, filterExpExpression1, filterExpOperator,
						filterExpExpression2, filterExpRightIndent, exactMatch, onlyCompare, overMatch, underMatch);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createMeasureRequest(String partition, String measureName, boolean generateItems, String description, String fromDate,
			String toDate, String[] inputMeasure, String[] inputMeasureRequest, String[] sourceName, String[][] filterExpressionType,
			String[][] filterName, String[][] filterExpClause, String[][] filterExpLeftIndent, String[][] filterExpExpression1,
			String[][] filterExpOperator, String[][] filterExpExpression2, String[][] filterExpRightIndent, boolean exactMatch, boolean onlyCompare,
			boolean overMatch, boolean underMatch) throws Exception {
		try {
			String detailScreenTitle = navigateToMeasureRequestDetail(partition, "Data Match Measure Request", measureName);
			
			if (detailScreenTitle.startsWith("New") || detailScreenTitle.startsWith("Edit")) {
				provideBasicDetails(detailScreenTitle, measureName, generateItems, description);
				
				provideMeasureRequestPeriod(fromDate, toDate);
				
//				Edit Filter
				if (ValidationHelper.isNotEmpty(sourceName)) {
					TreeHelper.expandTree("MR_MatchSourceFilter_Tree", "Sources");
					
					for (int i = 0; i < sourceName.length; i++) {
						TreeHelper.click("MR_MatchSourceFilter_Tree", sourceName[i]);
						
						ButtonHelper.click("MR_MatchSourceFilter_EditFilter");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						assertTrue(LabelHelper.isTitlePresent("Edit Data Match Source Filter"));
						QueryFilterHelper queryFilter = new QueryFilterHelper();
						queryFilter.addCondition(filterExpressionType[i], filterName[i], filterExpClause[i], filterExpLeftIndent[i], filterExpExpression1[i],
								filterExpOperator[i], filterExpExpression2[i], filterExpRightIndent[i]);
					}
				}
				
//				Match Result
				if(exactMatch)
					CheckBoxHelper.check("MR_ExactMatch");
				else
					CheckBoxHelper.uncheck("MR_ExactMatch");
				
				if(onlyCompare)
					CheckBoxHelper.check("MR_OnlyCompare");
				else
					CheckBoxHelper.uncheck("MR_OnlyCompare");
				
				if(overMatch)
					CheckBoxHelper.check("MR_OverMatch");
				else
					CheckBoxHelper.uncheck("MR_OverMatch");
				
				if(underMatch)
					CheckBoxHelper.check("MR_UnderMatch");
				else
					CheckBoxHelper.uncheck("MR_UnderMatch");
				
				selectInputMeasureRequest(inputMeasure, inputMeasureRequest);
					
				saveMeasureRequest(measureName, detailScreenTitle);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void scheduleMeasureRequest(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Measure Name").size(); i++)
			{
				String measureName = excelData.get("Measure Name").get(i);
				
				scheduleMeasureRequest(measureName);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyMeasureRequest(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
			ArrayList<String> waitTime = excelData.get("Wait Time In Seconds");
			
			for(int i = 0; i < excelData.get("Result Count").size(); i++)
			{
				String type = excelData.get("Type").get(i);
				String createdType = excelData.get("Created Type").get(i);
				String createdFromDate = excelData.get("Created From Date").get(i);
				String createdToDate = excelData.get("Created To Date").get(i);
				String measureName = excelData.get("Measure Name").get(i);
				String status = excelData.get("Status").get(i);
				String description = excelData.get("Description").get(i);
				int resultCount = testData.getIntegerValue(excelData.get("Result Count").get(i));
				int waitTimeInSecs = searchScreenWaitSec;
				
				if (waitTime != null && waitTime.size() > i)
					waitTimeInSecs = testData.getIntegerValue(waitTime.get(i));
				
				verifyMeasureRequest(type, createdType, createdFromDate, createdToDate, measureName, status, description, resultCount, waitTimeInSecs);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyResult(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			MeasureRequestHelper measureRequest = new MeasureRequestHelper();
			measureRequest.verifyResult(path, workBookName, workSheetName, testCaseName, occurence);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyZenResult(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Result Count").size(); i++)
			{
				String measureName = excelData.get("Measure Name").get(i);
				String zenName = excelData.get("Zen Name").get(i);
				int resultCount = testData.getIntegerValue(excelData.get("Result Count").get(i));
				
				String resultExcelPath = excelData.get("Result Excel Path").get(i);
				String resultFileName = excelData.get("Result Filename").get(i);
				String resultSheetName = excelData.get("Result Sheet Name").get(i);
				String resultTCName = excelData.get("Result TCName").get(i);
				
				verifyZenResult(measureName, zenName, resultCount, resultExcelPath, resultFileName, resultSheetName, resultTCName);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyZenResult(String measureName, String zenName, int resultCount, String resultExcelPath, String resultFileName,
			String resultSheetName, String resultTCName) throws Exception {
		try {
			MeasureRequestHelper measureRequest = new MeasureRequestHelper();
			int row = measureRequest.navigateToMeasureRequest(measureName);
			
			if (row > 0) {
				GridHelper.clickRow("SearchGrid", row, 1);
				
				if (resultCount == 0) {
					assertFalse(NavigationHelper.isActionPresent("Zen Results"));
				}
				else {
					assertTrue(NavigationHelper.isActionPresent("Zen Results"));
					NavigationHelper.navigateToAction("Zen Results", zenName);
					assertTrue(LabelHelper.isTitlePresent("Zen Result Search"));
					
					ButtonHelper.click("SearchButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					int rows = GridHelper.getRowCount("SearchGrid");
					assertTrue(rows >= resultCount, "Expected '" + resultCount + "' rows. But found '" + rows + "' rows in Zen Results.");
					
					NavigationHelper.navigateToAction("Expand/Collapse", "Expand All");
					DataAssertion dataAssertion = new DataAssertion();
					dataAssertion.testDataAssertion(resultExcelPath, resultFileName, resultSheetName, resultTCName);
				}
			}
			else {
				FailureHelper.failTest("Measure '" + measureName + "' is not found.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyZenUncoveredResult(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Result Count").size(); i++)
			{
				String measureName = excelData.get("Measure Name").get(i);
				String zenName = excelData.get("Zen Name").get(i);
				int resultCount = testData.getIntegerValue(excelData.get("Result Count").get(i));
				
				verifyZenUncoveredResult(measureName, zenName, resultCount);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyZenUncoveredResult(String measureName, String zenName, int resultCount) throws Exception {
		try {
			MeasureRequestHelper measureRequest = new MeasureRequestHelper();
			int row = measureRequest.navigateToMeasureRequest(measureName);
			
			if (row > 0) {
				GridHelper.clickRow("SearchGrid", row, 1);
				NavigationHelper.navigateToAction("Zen Uncovered Results", zenName);
				
				if (resultCount == 0) {
					assertTrue(LabelHelper.isTextPresent("No Uncovered results found."));
					ButtonHelper.click("OKButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
				}
				else {
					
				}
			}
			else {
				FailureHelper.failTest("Measure '" + measureName + "' is not found.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}