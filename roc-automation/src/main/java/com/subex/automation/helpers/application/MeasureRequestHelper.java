package com.subex.automation.helpers.application;
import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.screens.JumpToResultHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.EntitySearchHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.ImageHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class MeasureRequestHelper extends ROCAcceptanceTest {
	
	public void verifyResult(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Measure Name").size(); i++)
			{
				String measureName = excelData.get("Measure Name").get(i);
				boolean navigateToSummary = ValidationHelper.isTrue(excelData.get("Navigate To Summary Result").get(i));
				String resultCount = excelData.get("Result Count").get(i);
				
				String resultExcelPath = excelData.get("Result Excel Path").get(i);
				String resultExcelFilename = excelData.get("Result Excel Filename").get(i);
				String resultExcelSheetname = excelData.get("Result Excel Sheetname").get(i);
				String resultExcelTCName = excelData.get("Result Excel TCName").get(i);
				
				String[] expressionType = testData.getStringValue(excelData.get("Expression Type").get(i), firstLevelDelimiter);
				String[] filterName = testData.getStringValue(excelData.get("Filter Name").get(i), firstLevelDelimiter);
				String[] expClause = testData.getStringValue(excelData.get("Expression Clause").get(i), firstLevelDelimiter);
				String[] expLeftIndent = testData.getStringValue(excelData.get("Expression Left Indent").get(i), firstLevelDelimiter);
				String[] expExpression1 = testData.getStringValue(excelData.get("Expression Expression1").get(i), firstLevelDelimiter);
				String[] expOperator = testData.getStringValue(excelData.get("Expression Operator").get(i), firstLevelDelimiter);
				String[] expExpression2 = testData.getStringValue(excelData.get("Expression Expression2").get(i), firstLevelDelimiter);
				String[] expRightIndent = testData.getStringValue(excelData.get("Expression Right Indent").get(i), firstLevelDelimiter);
				
				verifyResult(measureName, navigateToSummary, resultCount, resultExcelPath, resultExcelFilename, resultExcelSheetname, resultExcelTCName,
						expressionType, filterName, expClause, expLeftIndent, expExpression1, expOperator, expExpression2, expRightIndent);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyResult(String measureName, boolean navigateToSummary, String resultCount, String resultExcelPath, String resultExcelFilename,
			String resultExcelSheetname, String resultExcelTCName, String[] expressionType, String[] filterName, String[] expClause,
			String[] expLeftIndent, String[] expExpression1, String[] expOperator, String[] expExpression2, String[] expRightIndent) throws Exception {
		try {
			int row = navigateToMeasureRequest(measureName);
			
			if (row > 0) {
				GridHelper.clickRow("SearchGrid", row, 1);
				navigateToResults(navigateToSummary);
				
				if(ValidationHelper.isNotEmpty(resultExcelPath) && ValidationHelper.isNotEmpty(resultExcelSheetname)) {
					JumpToResultHelper jumpToResult = new JumpToResultHelper();
					jumpToResult.verifyResult(resultExcelPath, resultExcelFilename, resultExcelSheetname, resultExcelTCName, expressionType, filterName,
							expClause, expLeftIndent, expExpression1, expOperator, expExpression2, expRightIndent);
				}
				
				if (!navigateToSummary) {
					JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
					jumpToSearch.verifyRecordCount(resultCount);
				}
				
				BrowserHelper browser = new BrowserHelper();
				browser.back();
			}
			else {
				FailureHelper.failTest("Measure '" + measureName + "' is not found.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public int navigateToMeasureRequest(String measureName) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Measure Requests", "Measure Request Search");
			int row = SearchGridHelper.gridFilterAdvancedSearch("MR_Measure_Filter", measureName, "Measure");
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String navigateToMeasureRequestDetail(String partition, String measureType, String measureName) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Measure Requests", "Measure Request Search");
			int row = SearchGridHelper.gridFilterAdvancedSearch("MR_Measure_Filter", measureName, "Measure");
			
			if (row > 0 ){
				GridHelper.clickRow("SearchGrid", row, "Measure");
				NavigationHelper.navigateToAction("Common Tasks");
				if (NavigationHelper.isActionPresent("Edit"))
					NavigationHelper.navigateToAction("Edit");
			}
			else {
				NavigationHelper.navigateToAction("Common Tasks", "New", measureType);
				NavigationHelper.selectPartition(partition);
			}
			
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			return detailScreenTitle;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void provideBasicDetails(String detailScreenTitle, String measureName, boolean generateItems, String description) throws Exception {
		try {
			if (detailScreenTitle.startsWith("New"))
				EntityComboHelper.selectUsingGridFilterTextBox("MR_Measure", "Measure Search", "Measure_Name", measureName, "Name");
			
			if(generateItems && CheckBoxHelper.isEnabled("MR_GenerateItems"))
				CheckBoxHelper.check("MR_GenerateItems");
			TextBoxHelper.type("MR_Description", description);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void provideMeasureRequestPeriod(String fromDate, String toDate) throws Exception {
		try {
			if(TextBoxHelper.isEnabled("MR_From")) {
				TextBoxHelper.type("MR_From", fromDate);
			}

			if(TextBoxHelper.isEnabled("MR_To")) {
				TextBoxHelper.type("MR_To", toDate);	
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void selectInputMeasureRequest(String[] inputMeasure, String[] inputMeasureRequest) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(inputMeasure)) {
				int rows = GridHelper.getRowCount("MR_InputMeasures_Grid");
				
				if (rows > 0) {
					EntitySearchHelper entitySearch = new EntitySearchHelper();
					
					for (int i = 0; i < inputMeasure.length; i++) {
						int row = GridHelper.getRowNumber("MR_InputMeasures_Grid", inputMeasure[i], "Input Measure");
						
						if (row > 0) {
							GridHelper.updateGridEntityCombo("MR_InputMeasures_Grid", "MR_InputMeasures_MeasureRequest", row, "Measure Request", inputMeasureRequest[i]);
							entitySearch.select("Measure Request Search", inputMeasureRequest[i], "Measure");
							assertEquals(GridHelper.getCellValue("", row, "Measure Request"), inputMeasureRequest[i]);
						}
						else {
							FailureHelper.failTest("Input Measure '" + inputMeasure[i] + "' is not found in Measure Request screen.");
						}
					}
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
	
	public void selectTableInstance(String[] tableDefinition, String[] tableInstance) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(tableDefinition)) {
				int rows = GridHelper.getRowCount("MR_TableInstance_Grid");
				
				if (rows > 0) {
					for (int i = 0; i < tableDefinition.length; i++) {
						int row = GridHelper.getRowNumber("MR_TableInstance_Grid", tableDefinition[i], "Table Definition");
						
						if (row > 0) {
							GridHelper.updateGridComboBox("MR_TableInstance_Grid", "MR_TableInstance_TableInstance", row, "Table Instance", "Table Definition", tableInstance[i]);
						}
						else {
							FailureHelper.failTest("Input Measure '" + tableDefinition[i] + "' is not found in Measure Request screen.");
						}
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void saveMeasureRequest(String measure, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			if (ButtonHelper.isPresent("YesButton")) {
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle));
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", measure, "Measure"));
			Log4jHelper.logInfo("Measure Request '" + measure + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void scheduleMeasureRequest(String measureName) throws Exception {
		try {
			int row = navigateToMeasureRequest(measureName);
			
			if (row > 0) {
				GridHelper.clickRow("SearchGrid", row, "Measure");
				Thread.sleep(100);
				NavigationHelper.navigateToAction("Measure Actions");
				
				if (NavigationHelper.isActionPresent("Schedule")) {
					NavigationHelper.navigateToAction("Schedule");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					Thread.sleep(3000);
				}
				else if (NavigationHelper.isActionPresent("Reschedule")) {
					NavigationHelper.navigateToAction("Reschedule");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					Thread.sleep(3000);
				}
				
				if (ButtonHelper.isPresent("YesButton")) {
					ButtonHelper.click("YesButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
				}
				
				ButtonHelper.click("SearchButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
			else {
				FailureHelper.failTest("Measure Request '" + measureName + "' is not found.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private int waitForMeasureRequestCompletion(int rows, int waitTimeInSecs) throws Exception {
		try {
			int tryCount = waitTimeInSecs;
			int count = 0;
			
			if (rows == 0) {
				while (count < tryCount) {
					Thread.sleep(1000);
					ButtonHelper.click("SearchButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					
					rows = GridHelper.getRowCount("SearchGrid");
					if (rows > 0)
						break;
					else
						count++;
				}
			}
			
			return rows;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyMeasureRequest(String type, String createdType, String createdFromDate, String createdToDate, String measureName,
			String status, String description, int resultCount, int waitTimeInSecs) throws Exception {
		try {
			navigateToMeasureRequest(type, createdType, createdFromDate, createdToDate, measureName, status, description);
			int rows = GridHelper.getRowCount("SearchGrid");
			rows = waitForMeasureRequestCompletion(rows, waitTimeInSecs);
			
			if (rows >= resultCount) {
				Log4jHelper.logInfo("Expected number of result '" + resultCount + "' found in Measure Request screen.");
			}
			else {
				FailureHelper.failTest("Expected result count of Measure Request '" + resultCount + "' is not found. Only '" + rows + "' requests are found.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyKPIResult(String type, String createdType, String createdFromDate, String createdToDate, String measureName,
			String status, String description, String kpiName, int resultCount) throws Exception {
		try {
			navigateToMeasureRequest(type, createdType, createdFromDate, createdToDate, measureName, status, description);
			int rows = GridHelper.getRowCount("SearchGrid");
			
			if (rows > 0) {
				GridHelper.clickRow("SearchGrid", 1, 1);
				if (NavigationHelper.isActionPresent("KPI Results")) {
					NavigationHelper.navigateToAction("KPI Results");
					
					if (NavigationHelper.isActionPresent(kpiName)) {
						NavigationHelper.navigateToAction(kpiName);
						assertTrue(LabelHelper.isTitlePresent("KPI Result Search"));
						
						rows = GridHelper.getRowCount("SearchGrid");
						assertTrue(rows >= resultCount);
					}
					else {
						FailureHelper.failTest("KPI Results > '" + kpiName + "' action is not found for Measure Request '" + measureName + "'.");
					}
				}
				else {
					FailureHelper.failTest("KPI Results action is not found for Measure Request '" + measureName + "'.");
				}
			}
			else {
				FailureHelper.failTest("Expected result count of Measure Request '" + resultCount + "' is not found. Only '" + rows + "' requests are found.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void navigateToMeasureRequest(String type, String createdType, String createdFromDate, String createdToDate, String measureName,
			String status, String description) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Measure Requests", "Measure Request Search");
			SearchGridHelper.searchWithComboBox("MR_Type_Filter", type, "Type");
			SearchGridHelper.gridFilterSearchWithCalendar("MR_Created_Filter", createdType, createdFromDate, createdToDate, "Created");
			SearchGridHelper.gridFilterAdvancedSearch("MR_Measure_Filter", measureName, "Measure");
			
			SearchGridHelper.gridFilterSearchWithComboBox("MR_Status_Filter", status, "Status");
			SearchGridHelper.gridFilterSearchWithTextBox("MR_Description_Filter", description, "Description");
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void navigateToResults(boolean navigateToSummary) throws Exception {
		try {
			if (navigateToSummary) {
				NavigationHelper.navigateToAction("Measure Results", "Jump to Summary");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
				if (ImageHelper.isPresent("MR_SummaryResult_Expand")) {
					ImageHelper.click("MR_SummaryResult_Expand");
				}
			}
			else {
				NavigationHelper.navigateToAction("Measure Results", "Jump to Results");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}