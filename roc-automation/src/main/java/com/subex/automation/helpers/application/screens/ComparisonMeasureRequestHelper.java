package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.MeasureRequestHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class ComparisonMeasureRequestHelper extends MeasureRequestHelper {
	
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
				
				createMeasureRequest(partition, measureName, generateItems, description, fromDate, toDate, inputMeasure, inputMeasureRequest);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createMeasureRequest(String partition, String measureName, boolean generateItems, String description, String fromDate,
			String toDate, String[] inputMeasure, String[] inputMeasureRequest) throws Exception {
		try {
			String detailScreenTitle = navigateToMeasureRequestDetail(partition, "Comparison Measure Request", measureName);
			
			if (detailScreenTitle.startsWith("New") || detailScreenTitle.startsWith("Edit")) {
				provideBasicDetails(detailScreenTitle, measureName, generateItems, description);
				
				provideMeasureRequestPeriod(fromDate, toDate);
				
				selectInputMeasureRequest(inputMeasure, inputMeasureRequest);
				
				saveMeasureRequest(measureName, detailScreenTitle);
			}
		}  catch (Exception e) {
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
}