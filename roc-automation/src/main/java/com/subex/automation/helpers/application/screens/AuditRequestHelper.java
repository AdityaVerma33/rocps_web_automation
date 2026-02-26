package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.BrowserHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CanvasHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.ImageHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.DataAssertion;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class AuditRequestHelper extends ROCAcceptanceTest {
	
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
	
	public void createAuditRequest(String partition, String auditName, String scheduledStart, String description, String frequency, String frequencyDatetime,
			String start, String length, String prune, String fromDatetime, String toDatetime, boolean generateItems) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Audit Requests", "Audit Request Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("AuditRequest_Audit_Filter", auditName, "Audit");

			if (row >0 ) {
				Log4jHelper.logWarning("Audit Requests '" + auditName + " ' is already present.");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "AuditRequest_Description");
				
				updateAuditRequest(auditName, scheduledStart, description, frequency, frequencyDatetime, start, length, prune, fromDatetime,
						toDatetime, generateItems);
				
				saveAuditRequest(auditName, detailScreenTitle);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void scheduleAuditRequest(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Audit Name").size(); i++)
			{
				String auditName = excelData.get("Audit Name").get(i);
				scheduleAuditRequest(auditName);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void scheduleAuditRequest(String auditName) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Audit Requests", "Audit Request Search");
			int row = SearchGridHelper.gridFilterAdvancedSearch("AuditRequest_Audit_Filter", auditName, "Audit");
			
			if (row == 0) {
				FailureHelper.failTest("Audit Request '" + auditName + "' is not found.");
			}
			else {
				GridHelper.clickRow("SearchGrid", row, "Audit");
				NavigationHelper.navigateToAction("Task Actions");
				
				if (NavigationHelper.isActionPresent("Schedule")) {
					NavigationHelper.navigateToAction("Schedule");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
				}
				else {
					NavigationHelper.navigateToAction("Reschedule");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					
					if (ButtonHelper.isPresent("OKButton")) {
						ButtonHelper.click("OKButton");
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
					}
				}
				
				if (ButtonHelper.isPresent("YesButton")) {
					ButtonHelper.click("YesButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
				}
				
				if (ButtonHelper.isPresent("YesButton")) {
					ButtonHelper.click("YesButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
				}
				
				Log4jHelper.logInfo("Audit Request of Audit '" + auditName + "' is scheduled.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void restartAuditRequest(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Audit Name").size(); i++)
			{
				String auditName = excelData.get("Audit Name").get(i);
				restartAuditRequest(auditName);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void restartAuditRequest(String auditName) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Audit Requests", "Audit Request Search");
			int row = SearchGridHelper.gridFilterAdvancedSearch("AuditRequest_Audit_Filter", auditName, "Audit");
			
			if (row == 0) {
				FailureHelper.failTest("Audit Request '" + auditName + "' is not found.");
			}
			else {
				GridHelper.clickRow("SearchGrid", row, "Audit");
				NavigationHelper.navigateToAction("Task Actions");
				if (NavigationHelper.isActionPresent("Restart")) {
					NavigationHelper.navigateToAction("Restart");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					
					if (ButtonHelper.isPresent("OKButton")) {
						ButtonHelper.click("OKButton");
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
					}
				}
				
				Log4jHelper.logInfo("Audit Request of Audit '" + auditName + "' is scheduled.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyAuditRequest(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
			ArrayList<String> waitTime = excelData.get("Wait Time In Seconds");
			
			for(int i = 0; i < excelData.get("Result Count").size(); i++)
			{
				String[] createdType = testData.getStringValue(excelData.get("Created Type").get(i), firstLevelDelimiter);
				String[] createdFromDate = testData.getStringValue(excelData.get("Created From Date").get(i), firstLevelDelimiter);
				String[] createdToDate = testData.getStringValue(excelData.get("Created To Date").get(i), firstLevelDelimiter);
				String[] auditName = testData.getStringValue(excelData.get("Audit Name").get(i), firstLevelDelimiter);
				String[] auditSchedule = testData.getStringValue(excelData.get("Audit Schedule").get(i), firstLevelDelimiter);
				String[] status = testData.getStringValue(excelData.get("Status").get(i), firstLevelDelimiter);
				String[] description = testData.getStringValue(excelData.get("Description").get(i), firstLevelDelimiter);
				int[] resultCount = testData.getIntegerValue(excelData.get("Result Count").get(i), firstLevelDelimiter);
				String[][] measures = testData.getStringValue(excelData.get("Measures").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] measureStatus = testData.getStringValue(excelData.get("Measures Status").get(i), firstLevelDelimiter, secondLevelDelimiter);
				int[] waitTimeInSecs = null;
				
				if (waitTime != null && waitTime.size() > i)
					waitTimeInSecs = testData.getIntegerValue(waitTime.get(i), firstLevelDelimiter);
				
				verifyAuditRequest(createdType, createdFromDate, createdToDate, auditName, auditSchedule, status, description, resultCount,
						measures, measureStatus, waitTimeInSecs);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private int waitForAuditRequestCompletion(int rows, int waitTimeInSecs) throws Exception {
		try {
			int tryCount = waitTimeInSecs;
			if (rows == 0) {
				while (rows > 0 || tryCount < 60) {
					Thread.sleep(1000);
					ButtonHelper.click("SearchButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					
					rows = GridHelper.getRowCount("SearchGrid");
				}
			}
			
			return rows;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyAuditRequest(String[] createdType, String[] createdFromDate, String[] createdToDate, String[] auditName, String[] auditSchedule,
			String[] status, String[] description, int[] resultCount, String[][] measures, String[][] measureStatus, int[] waitTimeInSecs) throws Exception {
		try {
			for (int i = 0; i < resultCount.length; i++) {
				NavigationHelper.navigateToScreen("Audit Requests", "Audit Request Search");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				GenericHelper.waitForAJAXReady(searchScreenWaitSec);
				
				SearchGridHelper.gridFilterSearchWithCalendar("AuditRequest_Created_Filter", createdType[i], createdFromDate[i], createdToDate[i], "Created");
				SearchGridHelper.gridFilterAdvancedSearch("AuditRequest_Audit_Filter", auditName[i], "Audit");
				
				SearchGridHelper.gridFilterAdvancedSearch("AuditRequest_AuditSchedule_Filter", auditSchedule[i], "Schedule");
				SearchGridHelper.gridFilterSearchWithComboBox("AuditRequest_Status_Filter", status[i], "Status");
				SearchGridHelper.gridFilterSearchWithTextBox("AuditRequest_Description", description[i], "Description");
				
				ButtonHelper.click("SearchButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				int rows = GridHelper.getRowCount("SearchGrid");
				
				int waitTime = searchScreenWaitSec;
				if (waitTimeInSecs != null && waitTimeInSecs.length > i)
					waitTime = waitTimeInSecs[i];
				rows = waitForAuditRequestCompletion(rows, waitTime);
				
				if (rows >= resultCount[i]) {
					if (ValidationHelper.isNotEmpty(measures) && ValidationHelper.isNotEmpty(measures[i])) {
						GridHelper.clickRow("SearchGrid", auditName[i], "Audit");
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
						
						for (int j = 0; j < measures[i].length; j++) {
							int row = GridHelper.getRowNumber("AuditRequest_Measures_Grid", measures[i][j], "Measure");
							
							if (row == 0) {
								FailureHelper.failTest("Measure '" + measures[i][j] + "' is not found for Audit Request '" + auditName[i] + "'");
							}
							else {
								if (ValidationHelper.isNotEmpty(measureStatus) && ValidationHelper.isNotEmpty(measureStatus[i])) {
									String actualStatus = GridHelper.getCellValue("AuditRequest_Measures_Grid", row, "Status");
									
									if (!actualStatus.equals(measureStatus[i][j]))
										FailureHelper.failTest("Measure is expected to be in '" + measureStatus[i][j] + "' status. But is found in '" + actualStatus + "' status.");
								}
							}
						}
					}
				}
				else {
					FailureHelper.failTest("Expected result count of Audit Request '" + resultCount[i] + "' is not found. Only '" + rows + "' requests are found.");
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyResult(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Audit Name").size(); i++)
			{
				String auditName = excelData.get("Audit Name").get(i);
				String measureName = excelData.get("Measure Name").get(i);
				String measureIndex = excelData.get("Measure Index").get(i);
				String canvasCount = excelData.get("Canvas Count").get(i);
				String resultCount = excelData.get("Result Count").get(i);
				boolean navigateToSummary = ValidationHelper.isTrue(excelData.get("Navigate To Summary Result").get(i));
				
				String resultExcelPath = excelData.get("Result Excel Path").get(i);
				String resultExcelFilename = excelData.get("Result Excel Filename").get(i);
				String[] resultExcelSheetname = testData.getStringValue(excelData.get("Result Excel Sheetname").get(i), firstLevelDelimiter);
				String[] resultExcelTCName = testData.getStringValue(excelData.get("Result Excel TCName").get(i), firstLevelDelimiter);
				
				String[][] expressionType = testData.getStringValue(excelData.get("Expression Type").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] filterName = testData.getStringValue(excelData.get("Filter Name").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] expClause = testData.getStringValue(excelData.get("Expression Clause").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] expLeftIndent = testData.getStringValue(excelData.get("Expression Left Indent").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] expExpression1 = testData.getStringValue(excelData.get("Expression Expression1").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] expOperator = testData.getStringValue(excelData.get("Expression Operator").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] expExpression2 = testData.getStringValue(excelData.get("Expression Expression2").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] expRightIndent = testData.getStringValue(excelData.get("Expression Right Indent").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				verifyResult(auditName, measureName, measureIndex, canvasCount, resultCount, navigateToSummary, resultExcelPath, resultExcelFilename,
						resultExcelSheetname, resultExcelTCName, expressionType, filterName, expClause, expLeftIndent, expExpression1, expOperator,
						expExpression2, expRightIndent);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyResult(String auditName, String measureName, String measureIndex, String canvasCount, String resultCount, boolean navigateToSummary,
			String resultExcelPath, String resultExcelFilename, String[] resultExcelSheetname, String[] resultExcelTCName, String[][] expressionType,
			String[][] filterName, String[][] expClause, String[][] expLeftIndent, String[][] expExpression1, String[][] expOperator,
			String[][] expExpression2, String[][] expRightIndent) throws Exception {
		try {
			navigateToEditAuditRequest(auditName);
			
			if (ValidationHelper.isNotEmpty(measureIndex)) {
				BrowserHelper browser = new BrowserHelper();
				
				int index = Integer.parseInt(measureIndex) + 1;
				String labelLocator = or.getProperty("Canvas_Label").replace("NUM", index+"");
				String value = LabelHelper.getText(labelLocator);
				String result = value.replace(" Rows", "");
				
				if (!value.equals(canvasCount) && !result.equals(canvasCount)) {
					FailureHelper.failTest("For Measure '" + measureName + "' expected result count is '" + canvasCount + "', but found '" + value + "'");
				}
				else {
					navigateToResults(measureName, navigateToSummary, null, "Results");
					
					if (canvasCount.equals("0 Rows")) {
						assertTrue(LabelHelper.isTextPresent("No data to fetch from the result table."));
						ButtonHelper.click("OKButton");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
					}
					else {
						if (navigateToSummary) {
							GenericHelper.waitForElement("JumpToSummary_SelectView", searchScreenWaitSec);
							
							if(ValidationHelper.isNotEmpty(resultExcelPath) && ValidationHelper.isNotEmpty(resultExcelSheetname)) {
								JumpToResultHelper jumpToResult = new JumpToResultHelper();
								jumpToResult.verifyResult(measureName, resultExcelPath, resultExcelFilename, resultExcelSheetname, resultExcelTCName, expressionType, filterName,
										expClause, expLeftIndent, expExpression1, expOperator, expExpression2, expRightIndent);
							}
						}
						else {
							GenericHelper.waitForElement("JumpToResult_From", searchScreenWaitSec);
							
							if(ValidationHelper.isNotEmpty(resultExcelPath) && ValidationHelper.isNotEmpty(resultExcelSheetname)) {
								JumpToResultHelper jumpToResult = new JumpToResultHelper();
								jumpToResult.verifyResult(resultExcelPath, resultExcelFilename, resultExcelSheetname, resultExcelTCName, expressionType, filterName,
										expClause, expLeftIndent, expExpression1, expOperator, expExpression2, expRightIndent);
							}
							
							if (resultCount.contains(" Rows"))
								resultCount = resultCount.replace(" Rows", "");
							JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
							jumpToSearch.verifyRecordCount(resultCount);
						}
						
						browser.back();
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyResultDrillDown(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Audit Name").size(); i++)
			{
				String auditName = excelData.get("Audit Name").get(i);
				String measureName = excelData.get("Measure Name").get(i);
				String measureIndex = excelData.get("Measure Index").get(i);
				boolean navigateToSummary = ValidationHelper.isTrue(excelData.get("Navigate To Summary Result").get(i));
				String resultValue = excelData.get("Result Value").get(i);
				String valueColumn = excelData.get("Value Column").get(i);
				String drillDownTable = excelData.get("Drill Down Table").get(i);
				
				String resultExcelPath = excelData.get("Result Excel Path").get(i);
				String resultExcelFilename = excelData.get("Result Excel Filename").get(i);
				String[] resultExcelSheetname = testData.getStringValue(excelData.get("Result Excel Sheetname").get(i), firstLevelDelimiter);
				String[] resultExcelTCName = testData.getStringValue(excelData.get("Result Excel TCName").get(i), firstLevelDelimiter);
				
				String[][] expressionType = testData.getStringValue(excelData.get("Expression Type").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] filterName = testData.getStringValue(excelData.get("Filter Name").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] expClause = testData.getStringValue(excelData.get("Expression Clause").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] expLeftIndent = testData.getStringValue(excelData.get("Expression Left Indent").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] expExpression1 = testData.getStringValue(excelData.get("Expression Expression1").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] expOperator = testData.getStringValue(excelData.get("Expression Operator").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] expExpression2 = testData.getStringValue(excelData.get("Expression Expression2").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] expRightIndent = testData.getStringValue(excelData.get("Expression Right Indent").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				verifyResultDrillDown(auditName, measureName, measureIndex, navigateToSummary, resultValue, valueColumn, drillDownTable, resultExcelPath,
						resultExcelFilename, resultExcelSheetname, resultExcelTCName, expressionType, filterName, expClause, expLeftIndent, expExpression1,
						expOperator, expExpression2, expRightIndent);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyResultDrillDown(String auditName, String measureName, String measureIndex, boolean navigateToSummary, String resultValue,
			String valueColumn, String drillDownTable, String resultExcelPath, String resultExcelFilename, String[] resultExcelSheetname,
			String[] resultExcelTCName, String[][] expressionType, String[][] filterName, String[][] expClause, String[][] expLeftIndent,
			String[][] expExpression1, String[][] expOperator, String[][] expExpression2, String[][] expRightIndent) throws Exception {
		try {
			navigateToEditAuditRequest(auditName);
			
			if (ValidationHelper.isNotEmpty(measureIndex)) {
				if(ValidationHelper.isNotEmpty(resultExcelPath) && ValidationHelper.isNotEmpty(resultExcelSheetname)) {
					BrowserHelper browser = new BrowserHelper();
					
					for (int i = 0; i < resultExcelSheetname.length; i++) {
						if (ValidationHelper.isNotEmpty(resultExcelSheetname[i])) {
							navigateToResults(measureName, navigateToSummary, null, "Results");
							if (navigateToSummary) {
								GenericHelper.waitForElement("JumpToSummary_SelectView", searchScreenWaitSec);
							}
							else {
								GenericHelper.waitForElement("JumpToResult_From", searchScreenWaitSec);
							}
							
							String gridId = "SearchGrid";
							String drillDownActionGroup = "Drill Down";
							String drillDownAction = drillDownTable;
							
							if (navigateToSummary) {
								gridId = "AuditRequest_SummaryResult_Grid";
								drillDownActionGroup = "Measure Results Summary";
								drillDownAction = "Drill Down";
							}
							
							JumpToResultHelper jumpToResult = new JumpToResultHelper();
							jumpToResult.verifyDrillDown(gridId, resultValue, valueColumn, drillDownActionGroup, drillDownAction, resultExcelPath,
									resultExcelFilename, resultExcelSheetname, resultExcelTCName,expressionType, filterName, expClause, expLeftIndent,
									expExpression1, expOperator, expExpression2, expRightIndent);
							
							browser.back();
						}
					}
				}
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
	
			for(int i = 0; i < excelData.get("Audit Name").size(); i++)
			{
				String auditName = excelData.get("Audit Name").get(i);
				String measureName = excelData.get("Measure Name").get(i);
				String measureIndex = excelData.get("Measure Index").get(i);
				
				String resultExcelPath = excelData.get("Result Excel Path").get(i);
				String resultExcelFilename = excelData.get("Result Excel Filename").get(i);
				String resultExcelSheetname = excelData.get("Result Excel Sheetname").get(i);
				String resultExcelTCName = excelData.get("Result Excel TCName").get(i);
				
				String resultValue = excelData.get("Result Value").get(i);
				String valueColumn = excelData.get("Value Column").get(i);
				String drilldownExcelSheetname = excelData.get("Drilldown Excel Sheetname").get(i);
				String drilldownExcelTCName = excelData.get("Drilldown Excel TCName").get(i);
				
				String[] expressionType = testData.getStringValue(excelData.get("Expression Type").get(i), firstLevelDelimiter);
				String[] filterName = testData.getStringValue(excelData.get("Filter Name").get(i), firstLevelDelimiter);
				String[] expClause = testData.getStringValue(excelData.get("Expression Clause").get(i), firstLevelDelimiter);
				String[] expLeftIndent = testData.getStringValue(excelData.get("Expression Left Indent").get(i), firstLevelDelimiter);
				String[] expExpression1 = testData.getStringValue(excelData.get("Expression Expression1").get(i), firstLevelDelimiter);
				String[] expOperator = testData.getStringValue(excelData.get("Expression Operator").get(i), firstLevelDelimiter);
				String[] expExpression2 = testData.getStringValue(excelData.get("Expression Expression2").get(i), firstLevelDelimiter);
				String[] expRightIndent = testData.getStringValue(excelData.get("Expression Right Indent").get(i), firstLevelDelimiter);
				
				verifyKPIResult(auditName, measureName, measureIndex, resultExcelPath, resultExcelFilename, resultExcelSheetname, resultExcelTCName,
						resultValue, valueColumn, drilldownExcelSheetname, drilldownExcelTCName, expressionType, filterName, expClause,
						expLeftIndent, expExpression1, expOperator, expExpression2, expRightIndent);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyKPIResult(String auditName, String measureName, String measureIndex, String resultExcelPath, String resultExcelFilename,
			String resultExcelSheetname, String resultExcelTCName, String resultValue, String valueColumn, String drilldownExcelSheetname,
			String drilldownExcelTCName, String[] expressionType, String[] filterName, String[] expClause, String[] expLeftIndent,
			String[] expExpression1, String[] expOperator, String[] expExpression2, String[] expRightIndent) throws Exception {
		try {
			navigateToEditAuditRequest(auditName);
			
			if (ValidationHelper.isNotEmpty(measureIndex)) {
				DataAssertion dataAssertion = new DataAssertion();
				BrowserHelper browser = new BrowserHelper();
				
				if(ValidationHelper.isNotEmpty(resultExcelPath) && ValidationHelper.isNotEmpty(resultExcelSheetname)) {
					navigateToResults(measureName, false, "View", "View KPI Results");
							
					dataAssertion.testDataAssertion(resultExcelPath, resultExcelFilename, resultExcelSheetname, resultExcelTCName);
					
					JumpToResultHelper jumpToResult = new JumpToResultHelper();
					jumpToResult.verifyDrillDown("SearchGrid", resultValue, valueColumn, "Drill Down", "Drill Down", resultExcelPath, resultExcelFilename, drilldownExcelSheetname, drilldownExcelTCName,
							expressionType, filterName, expClause, expLeftIndent, expExpression1, expOperator, expExpression2, expRightIndent);
					browser.back();
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private int navigateToAuditRequest(String auditName) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Audit Requests", "Audit Request Search");
			int row = SearchGridHelper.gridFilterAdvancedSearch("AuditRequest_Audit_Filter", auditName, "Audit");
			
			if (row == 0) {
				FailureHelper.failTest("Audit Request for Audit '" + auditName + "' is not found.");
			}
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void navigateToEditAuditRequest(String auditName) throws Exception {
		try {
			if (!LabelHelper.isTitlePresent("Edit Audit Request") || !EntityComboHelper.isPresent("AuditRequest_Audit") || !EntityComboHelper.isValuePresent("AuditRequest_Audit", auditName)) {
				int row = navigateToAuditRequest(auditName);
				
				NavigationHelper.navigateToEdit("SearchGrid", row, "AuditRequest_Description");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
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
					if (CanvasHelper.isTextPresent("AuditRequest_Canvas", measure))
						measure = measureNameorIndex;
					else
						measure = measureNameorIndex.substring(0, 9) + "...";
				}
			}
			
			CanvasHelper.click("AuditRequest_Canvas", measure);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateAuditRequest(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Audit Name").size(); i++)
			{
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
				
				updateAuditRequest(auditName, scheduledStart, description, frequency, frequencyDatetime, start, length, prune, fromDatetime,
						toDatetime, generateItems);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateAuditRequest(String auditName, String scheduledStart, String description, String frequency, String frequencyDatetime,
			String start, String length, String prune, String fromDatetime, String toDatetime, boolean generateItems) throws Exception {
		try {
			EntityComboHelper.selectUsingGridFilterTextBox("AuditRequest_Audit", "Audit Search", "AuditDefinition_Name", auditName, "Name");
			
			TextBoxHelper.type("AuditRequest_ScheduledStart", scheduledStart);
			TextBoxHelper.type("AuditRequest_Description", description);
			
			if (ValidationHelper.isNotEmpty(frequency)) {
				ComboBoxHelper.select("AuditRequest_Frequency", frequency);
				TextBoxHelper.type("AuditRequest_FrequencyStartDate", frequencyDatetime);
				TextBoxHelper.type("AuditRequest_LookbackStart", start);
				TextBoxHelper.type("AuditRequest_LookbackLength", length);
				TextBoxHelper.type("AuditRequest_LookbackPrune", prune);
			}
			
			TextBoxHelper.type("AuditRequest_AuditFrom", fromDatetime);

			TextBoxHelper.type("AuditRequest_AuditTo", toDatetime);	
			
			if (generateItems)
				NavigationHelper.navigateToAction("Generate Items", "Set On All");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void navigateToResults(String measureName, boolean navigateToSummary, String actionGroup, String action) throws Exception {
		try {
			clickMeasure(measureName);
			
			if (navigateToSummary) {
				NavigationHelper.navigateToAction("View", "Summary Results");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
				for (int i = 0; i < 2; i++) {
					if (ImageHelper.isPresent("AuditRequest_SummaryResult_Expand")) {
						ImageHelper.click("AuditRequest_SummaryResult_Expand");
						Thread.sleep(1000);
					}
				}
			}
			else {
				if (ValidationHelper.isNotEmpty(actionGroup))
					NavigationHelper.navigateToAction(actionGroup, action);
				else
					NavigationHelper.navigateToAction(action);
				
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void saveAuditRequest(String name, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle));
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Audit"));
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