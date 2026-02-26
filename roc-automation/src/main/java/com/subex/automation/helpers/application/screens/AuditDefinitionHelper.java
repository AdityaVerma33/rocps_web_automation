package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CanvasHelper;
import com.subex.automation.helpers.component.EntitySearchHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class AuditDefinitionHelper extends ROCAcceptanceTest {
	
	public void createAuditDefinition(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String auditName = excelData.get("Name").get(i);
				String description = excelData.get("Description").get(i);
				String[] measureName = testData.getStringValue(excelData.get("Measure Name").get(i), firstLevelDelimiter);
				String autoArrange = excelData.get("Auto Arrange").get(i);
				
				createAuditDefinition(partition, auditName, description, measureName, autoArrange);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createAuditDefinition (String partition, String auditName, String description, String[] measureName, String autoArrange)
			throws Exception{
		try {
			NavigationHelper.navigateToScreen("Audits", "Audit Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("AuditDefinition_Name", auditName, "Name");

			if (row >0 ) {
				Log4jHelper.logWarning("Audit '" + auditName + " ' is already present." );
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "AuditDefinition_Name");
				
				updateAuditDefinition(auditName, description, measureName, autoArrange);
				
				saveAuditDefinition(auditName, detailScreenTitle);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateAuditDefinition(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String auditName = excelData.get("Name").get(i);
				String description = excelData.get("Description").get(i);
				String[] measureName = testData.getStringValue(excelData.get("Measure Name").get(i), firstLevelDelimiter);
				String autoArrange = excelData.get("Auto Arrange").get(i);
				
				updateAuditDefinition(auditName, description, measureName, autoArrange);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateAuditDefinition (String auditName, String description, String[] measureName, String autoArrange)
			throws Exception{
		try {
			TextBoxHelper.type("AuditDefinition_Name", auditName);
			TextBoxHelper.type("AuditDefinition_Description", description);
			
			if (ValidationHelper.isNotEmpty(measureName)) {
				EntitySearchHelper entitySearch = new EntitySearchHelper();
				for (int i = 0; i < measureName.length; i++) {
					MouseHelper.click("AuditDefinition_Add");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					
					entitySearch.selectUsingGridFilterTextBox("Measure Search", "Measure_Name", measureName[i], "Name");
				}
			}
			
			if (ValidationHelper.isNotEmpty(autoArrange)) {
				if (autoArrange.equalsIgnoreCase("Horizontal"))
					NavigationHelper.navigateToAction("Auto Arrange", "Horizontal");
				else if (autoArrange.equalsIgnoreCase("Vertical"))
					NavigationHelper.navigateToAction("Auto Arrange", "Horizontal");
				else
					NavigationHelper.navigateToAction("Auto Arrange", "Horizontal");
			}
			else {
				NavigationHelper.navigateToAction("Auto Arrange", "Horizontal");
			}
			
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createAuditRequest(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Audit Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String auditName = excelData.get("Audit Name").get(i);
				String scheduledStart = excelData.get("Scheduled Start").get(i);
				String description = excelData.get("Description").get(i);
				
				String frequency = excelData.get("Frequency").get(i);
				String frequencyDatetime = excelData.get("Frequency Datetime").get(i);
				String start = excelData.get("Start").get(i);
				String length = excelData.get("Length").get(i);
				String prune = excelData.get("Prune").get(i);
				
				String fromDatetime = excelData.get("From Datetime").get(i);
				String toDatetime = excelData.get("To Datetime").get(i);
				boolean generateItems = ValidationHelper.isTrue(excelData.get("Generate Items").get(i));
				
				createAuditRequest(partition, auditName, scheduledStart, description, frequency, frequencyDatetime, start, length, prune,
						fromDatetime, toDatetime, generateItems);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createAuditRequest (String partition, String auditName, String scheduledStart, String description, String frequency,
			String frequencyDatetime, String start, String length, String prune, String fromDatetime, String toDatetime,
			boolean generateItems) throws Exception{
		try {
			NavigationHelper.navigateToScreen("Audits", "Audit Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("AuditDefinition_Name", auditName, "Name");

			if (row >0 ) {
				Log4jHelper.logWarning("Audit '" + auditName + " ' is already present." );
			}
			else {
				GridHelper.clickRow("SearchGrid", auditName, "Name");
				NavigationHelper.navigateToAction("Audit Requests");
				
				if (!NavigationHelper.isActionPresent(auditName)) {
					NavigationHelper.navigateToAction("New Request");
					String detailScreenTitle = NavigationHelper.getScreenTitle();
					
					AuditRequestHelper auditRequest = new AuditRequestHelper();
					auditRequest.updateAuditRequest(auditName, scheduledStart, description, frequency, frequencyDatetime, start, length, prune, fromDatetime, toDatetime, generateItems);
					
					ButtonHelper.click("SaveButton");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle));
					
					ButtonHelper.click("SearchButton");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					GridHelper.clickRow("SearchGrid", auditName, "Name");
					NavigationHelper.navigateToAction("Audit Requests");
					assertTrue(NavigationHelper.isActionPresent(auditName));
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
	
	public void clickMeasure(String measureNameorIndex) throws Exception {
		try {
			String measure = measureNameorIndex;
			
			if (!ValidationHelper.isInteger(measureNameorIndex)) {
				if (measureNameorIndex.length() > 12)
					measure = measureNameorIndex.substring(0, 9) + "...";
				else if (measureNameorIndex.length() == 12) {
					if (CanvasHelper.isTextPresent("AuditDefinition_Canvas", measure))
						measure = measureNameorIndex;
					else
						measure = measureNameorIndex.substring(0, 9) + "...";
				}
			}
			
			CanvasHelper.click("AuditDefinition_Canvas", measure);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void saveAuditDefinition(String name, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);

			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle));
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"));
			Log4jHelper.logInfo("Audit Definition '" + name + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}