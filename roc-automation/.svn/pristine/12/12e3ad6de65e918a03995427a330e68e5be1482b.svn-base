package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.MeasureRequestHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class QueryMeasureRequestHelper extends MeasureRequestHelper {
	
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
				String [] tableDefinition = testData.getStringValue(excelData.get("Table Definition").get(i), firstLevelDelimiter);
				String [] tableInstance = testData.getStringValue(excelData.get("Table Instance").get(i), firstLevelDelimiter);
				
				String [] filterExpressionType = testData.getStringValue(excelData.get("Filter Expression Type").get(i), firstLevelDelimiter);
				String [] filterName = testData.getStringValue(excelData.get("Filter Name").get(i), firstLevelDelimiter);
				String [] filterExpClause = testData.getStringValue(excelData.get("Filter Expression Clause").get(i), firstLevelDelimiter);
				String [] filterExpLeftIndent = testData.getStringValue(excelData.get("Filter Expression Left Indent").get(i), firstLevelDelimiter);
				String [] filterExpExpression1 = testData.getStringValue(excelData.get("Filter Expression Expression1").get(i), firstLevelDelimiter);
				String [] filterExpOperator = testData.getStringValue(excelData.get("Filter Expression Operator").get(i), firstLevelDelimiter);
				String [] filterExpExpression2 = testData.getStringValue(excelData.get("Filter Expression Expression2").get(i), firstLevelDelimiter);
				String [] filterExpRightIndent = testData.getStringValue(excelData.get("Filter Expression Right Indent").get(i), firstLevelDelimiter);
				
				createMeasureRequest(partition, measureName, generateItems, description, fromDate, toDate, inputMeasure, inputMeasureRequest,
						tableDefinition, tableInstance, filterExpressionType, filterName, filterExpClause, filterExpLeftIndent, filterExpExpression1,
						filterExpOperator, filterExpExpression2, filterExpRightIndent);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createMeasureRequest(String partition, String measureName, boolean generateItems, String description, String fromDate,
			String toDate, String[] inputMeasure, String[] inputMeasureRequest, String[] tableDefinition, String[] tableInstance,
			String[] filterExpressionType, String[] filterName, String[] filterExpClause, String[] filterExpLeftIndent, String[] filterExpExpression1,
			String[] filterExpOperator, String[] filterExpExpression2, String[] filterExpRightIndent) throws Exception {
		try {
			String detailScreenTitle = navigateToMeasureRequestDetail(partition, "Query Measure Request", measureName);
			
			if (detailScreenTitle.startsWith("New") || detailScreenTitle.startsWith("Edit")) {
				provideBasicDetails(detailScreenTitle, measureName, generateItems, description);
				
				provideMeasureRequestPeriod(fromDate, toDate);
				
				selectInputMeasureRequest(inputMeasure, inputMeasureRequest);
				
				selectTableInstance(tableDefinition, tableInstance);
				
				if (ValidationHelper.isNotEmpty(filterExpressionType)) {
					ButtonHelper.click("MeasureRequest_Filter");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					assertTrue(LabelHelper.isTitlePresent("Query Measure Filter"));
					QueryFilterHelper queryFilter = new QueryFilterHelper();
					queryFilter.addCondition(filterExpressionType, filterName, filterExpClause, filterExpLeftIndent, filterExpExpression1, filterExpOperator,
							filterExpExpression2, filterExpRightIndent);
				}
				
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
	
	public void verifyKPIResult(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Result Count").size(); i++)
			{
				String type = excelData.get("Type").get(i);
				String createdType = excelData.get("Created Type").get(i);
				String createdFromDate = excelData.get("Created From Date").get(i);
				String createdToDate = excelData.get("Created To Date").get(i);
				String measureName = excelData.get("Measure Name").get(i);
				String status = excelData.get("Status").get(i);
				String description = excelData.get("Description").get(i);
				String kpiName = excelData.get("KPI Name").get(i);
				int resultCount = testData.getIntegerValue(excelData.get("Result Count").get(i));
				
				verifyKPIResult(type, createdType, createdFromDate, createdToDate, measureName, status, description, kpiName, resultCount);
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
}