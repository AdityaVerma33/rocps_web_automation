package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.ExportHelper;
import com.subex.automation.helpers.application.MeasureHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.component.TreeHelper;
import com.subex.automation.helpers.data.DataAssertion;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.automation.helpers.util.UnzipHelper;

public class JumpToSearchHelper extends ROCAcceptanceTest {
	
	public void verifyJumpToSearchResult(String path, String workBookName, String workSheetName, String testCaseName, int occurance) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurance );
	
			for(int i = 0; i < excelData.get("Display Name").size(); i++) {
				String tableType = excelData.get("Table Type").get(i);
				String tableDisplayName = excelData.get("Display Name").get(i);
				String fromDate = excelData.get("From Date").get(i);
				String toDate = excelData.get("To Date").get(i);
				String recordCount = excelData.get("Record Count").get(i);
				String sortColumn = excelData.get("Sort Column").get(i);
				
				String [] expressionType = testData.getStringValue(excelData.get("Expression Type").get(i), firstLevelDelimiter);
				String [] filterName = testData.getStringValue(excelData.get("Filter Name").get(i), firstLevelDelimiter);
				String [] expClause = testData.getStringValue(excelData.get("Expression Clause").get(i), firstLevelDelimiter);
				String [] expLeftIndent = testData.getStringValue(excelData.get("Expression Left Indent").get(i), firstLevelDelimiter);
				String [] expExpression1 = testData.getStringValue(excelData.get("Expression Expression1").get(i), firstLevelDelimiter);
				String [] expOperator = testData.getStringValue(excelData.get("Expression Operator").get(i), firstLevelDelimiter);
				String [] expExpression2 = testData.getStringValue(excelData.get("Expression Expression2").get(i), firstLevelDelimiter);
				String [] expRightIndent = testData.getStringValue(excelData.get("Expression Right Indent").get(i), firstLevelDelimiter);
				
				String resultExcelPath = excelData.get("Result Excel Path").get(i);
				String resultFileName = excelData.get("Result Filename").get(i);
				String resultSheetName = excelData.get("Result Sheet Name").get(i);
				String resultTCName = excelData.get("Result TCName").get(i);
				
				if (ValidationHelper.isNotEmpty(excelData.get("Assertion Columns")) && ValidationHelper.isNotEmpty(excelData.get("Assertion Columns").get(i))) {
					String[] assertionColumns = testData.getStringValue(excelData.get("Assertion Columns").get(i), firstLevelDelimiter);
					String[][] assertionValues = testData.getStringValue(excelData.get("Assertion Values").get(i), firstLevelDelimiter, secondLevelDelimiter);
					
					verifyJumpToSearchResult(tableType, tableDisplayName, fromDate, toDate, recordCount, sortColumn, assertionColumns,
							assertionValues, expressionType, filterName, expClause, expLeftIndent, expExpression1, expOperator, expExpression2,
							expRightIndent);
				}
				else {
					verifyJumpToSearchResult(tableType, tableDisplayName, fromDate, toDate, recordCount, sortColumn, resultExcelPath, resultFileName,
							resultSheetName, resultTCName, expressionType, filterName, expClause, expLeftIndent, expExpression1, expOperator,
							expExpression2, expRightIndent, occurance);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyJumpToSearchResult(String tableType, String tableDisplayName, String fromDate, String toDate, String recordCount,
			String sortColumn, String[] assertionColumns, String[][] assertionValues, String[] expressionType, String[] filterName, String[] expClause,
			String[] expLeftIndent, String[] expExpression1, String[] expOperator, String[] expExpression2, String[] expRightIndent) throws Exception {
		try {
			navigateToJ2S(tableDisplayName);
			
			setDates(fromDate, toDate);
	
			if (ValidationHelper.isNotEmpty(expressionType)) {
				GenericHelper.expandSearchFilterPanel();
				QueryFilterHelper queryFilter = new QueryFilterHelper();
				queryFilter.addCondition(expressionType, filterName, expClause, expLeftIndent, expExpression1, expOperator, expExpression2, expRightIndent);
			}
			
			if (ValidationHelper.isNotEmpty(recordCount))
				verifyRecordCount(tableType, fromDate, toDate, recordCount);
	
			if (ValidationHelper.isNotEmpty(assertionColumns)) {
				validateResult(sortColumn, assertionColumns, assertionValues);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyJumpToSearchResult(String tableType, String tableDisplayName, String fromDate, String toDate, String recordCount,
			String sortColumn, String resultExcelPath, String resultFileName, String resultSheetName, String resultTCName, String[] expressionType,
			String[] filterName, String[] expClause, String[] expLeftIndent, String[] expExpression1, String[] expOperator, String[] expExpression2,
			String[] expRightIndent, int occurance) throws Exception {
		try {
			navigateToJ2S(tableDisplayName);
			
			setDates(fromDate, toDate);
	
			if (ValidationHelper.isNotEmpty(expressionType)) {
				GenericHelper.expandSearchFilterPanel();
				QueryFilterHelper queryFilter = new QueryFilterHelper();
				queryFilter.addCondition(expressionType, filterName, expClause, expLeftIndent, expExpression1, expOperator, expExpression2, expRightIndent);
			}
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
			
			if (ValidationHelper.isNotEmpty(recordCount))
				verifyRecordCount(tableType, fromDate, toDate, recordCount);
			
			if (ValidationHelper.isNotEmpty(sortColumn)) {
				GridHelper.sortGrid("SearchGrid", sortColumn);
				GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
			}
			
			if (ValidationHelper.isNotEmpty(resultFileName)) {
				DataAssertion dataAssertion = new DataAssertion();
				dataAssertion.testDataAssertion(resultExcelPath, resultFileName, resultSheetName, resultTCName);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyRecordCount(String recordCount) throws Exception {
		try {
			ButtonHelper.click("J2S_RecordCount");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			GenericHelper.waitForElementToDisappear("J2S_RecordCount_PleaseWait", configProp.getCustomScreenWaitSec());
			GenericHelper.waitForElement("OKButton", searchScreenWaitSec);
			
			String actualCount = ButtonHelper.getText("J2S_RecordCount").replace(" Records Found", "");
			assertTrue(PopupHelper.isTextPresent("Search query returned " + recordCount + " row(s)."),
					"Expected record count '" + recordCount + "' not found. But found '" + actualCount + "'");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			if (ButtonHelper.isPresent("OKButton")) {
				ButtonHelper.click("OKButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
		}
	}
	
	private void verifyRecordCount(String tableType, String fromDate, String toDate, String recordCount) throws Exception {
		try {
			if (tableType.equals("Standard") || tableType.equals("Reference")) {
				verifyRecordCount(recordCount);
			}
			else {
				String tempFrom = null;
				String tempTo = null;
				
				if (TextBoxHelper.isPresent("J2S_FromDate")) {
					if (ValidationHelper.isEmpty(fromDate)) {
						fromDate = TextBoxHelper.getValue("J2S_FromDate");
					}
					
					if (fromDate.contains(":"))
						fromDate = fromDate.substring(0, fromDate.length()-9);
					
					tempFrom = DateHelper.convertDate("MM/dd/yyyy", "MMM-dd-yyyy", fromDate);
				}
				
				if (TextBoxHelper.isPresent("J2S_ToDate")) {
					if (ValidationHelper.isEmpty(toDate)) {
						toDate = TextBoxHelper.getValue("J2S_ToDate");
					}
					
					if (toDate.contains(":"))
						toDate = toDate.substring(0, toDate.length()-9);
					
					tempTo = DateHelper.convertDate("MM/dd/yyyy", "MMM-dd-yyyy", toDate);
				}
				
				ButtonHelper.click("J2S_RecordCount");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				GenericHelper.waitForElementToDisappear("J2S_RecordCount_PleaseWait", configProp.getCustomScreenWaitSec());
				Thread.sleep(500);
				assertTrue(ButtonHelper.isTextPresent("J2S_RecordCount", recordCount + " Records Found"), "Expected Record Count '" + recordCount + "' not found.");
				GenericHelper.waitForElement("OKButton", searchScreenWaitSec);
				
				if (ValidationHelper.isNotEmpty(fromDate) && ValidationHelper.isNotEmpty(fromDate)) {
					assertTrue(PopupHelper.isTextPresent("Search query returned the data from " + tempFrom + " to " + tempTo + " having total COUNT of " + recordCount + " row(s)."),
						"Record Count information is not proper.");
				}
				else {
					assertTrue(PopupHelper.isTextPresent("total COUNT of " + recordCount + " row(s)."), "Record Count information is not proper.");
				}
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			if (ButtonHelper.isPresent("OKButton")) {
				ButtonHelper.click("OKButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
		}
	}
	
	public void validateResult(String sortColumn, String[] assertionColumns, String[][] assertionValues) throws Exception {
		try {
			ButtonHelper.click("Search");
			GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
			
			if (sortColumn != null && !sortColumn.equals(""))
				GridHelper.sortGrid("SearchGrid", sortColumn);
			
			for (int i = 0; i < assertionValues.length; i++) {
				for (int j = 0; j < assertionColumns.length; j++) {
					if (ValidationHelper.isBoolean(assertionValues[i][j])) {
						boolean actualValue = GridHelper.getBooleanValue("SearchGrid", i+1, assertionColumns[j]);
						if (ValidationHelper.isTrue(assertionValues[i][j]))
							assertEquals(actualValue, true, "Expected 'true' but found '" + actualValue + "' in column '" + assertionColumns[j] + "'.");
						else
							assertEquals(actualValue, false, "Expected 'false' but found '" + actualValue + "' in column '" + assertionColumns[j] + "'.");
					}
					else {
						String actualValue = GridHelper.getCellValue("SearchGrid", i+1, assertionColumns[j]);
						assertEquals(actualValue, assertionValues[i][j], "Expected '" + assertionValues[i][j] + "' but found '" + actualValue + "' in column '" + assertionColumns[j] + "'.");
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
	
	public void manualDataLoad(String path, String WorkbookName, String workSheetName, String testCaseName, int occurance) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurance );
			
			for(int i = 0; i < excelData.get("Display Name").size(); i++) {
				String tableType = excelData.get("Table Type").get(i);
				String tableDisplayName = excelData.get("Display Name").get(i);
				String fileName = excelData.get("File Name").get(i);
				String fileNameWithPath = GenericHelper.getUploadFilePath(fileName);
				
				String delimiter = excelData.get("Delimiter").get(i);
				String dateFormat = excelData.get("Date Format").get(i);
				
				boolean truncateAndLoad = ValidationHelper.isTrue(excelData.get("Truncate & Load").get(i));
				boolean trimQuotes = ValidationHelper.isTrue(excelData.get("Trim Quotes").get(i));
				boolean convertDecimal = ValidationHelper.isTrue(excelData.get("Convert Decimal").get(i));
				boolean skipHeader = ValidationHelper.isTrue(excelData.get("Skip Header").get(i));
				String fromDate = excelData.get("From Date").get(i);
				String toDate = excelData.get("To Date").get(i);
				String recordCount = excelData.get("Record count").get(i);
				
				if (delimiter.contains("\\\\"))
					delimiter = delimiter.replace("\\\\", "\\");
				manualDataLoad(tableType, tableDisplayName, fileNameWithPath, delimiter, dateFormat, truncateAndLoad, trimQuotes, convertDecimal, skipHeader, fromDate, toDate, recordCount);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void manualDataLoad(String tableType, String tableDisplayName, String fileNameWithPath, String delimiter, String dateFormat,
			boolean truncateAndLoad, boolean trimQuotes, boolean convertDecimal, boolean skipHeader, String fromDate, String toDate,
			String recordCount) throws Exception {
		try {
			navigateToJ2S(tableDisplayName);
			
			NavigationHelper.navigateToAction("Manual Import", "Data Load");
		    GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Import Data File"));

			GenericHelper.fileUpload("J2S_ManualDataLoad_FileName", fileNameWithPath);
			
			boolean isValuePresent = ComboBoxHelper.containsValue("J2S_ManualDataLoad_FieldDelimiter", delimiter);
			if (isValuePresent)
				ComboBoxHelper.select("J2S_ManualDataLoad_FieldDelimiter", delimiter);
			else {
				ComboBoxHelper.select("J2S_ManualDataLoad_FieldDelimiter", "Custom");
				assertTrue(TextBoxHelper.isEnabled("J2S_ManualDataLoad_CustomDelimiter"));
				TextBoxHelper.type("J2S_ManualDataLoad_CustomDelimiter", delimiter);
			}
			
			if(ValidationHelper.isNotEmpty(dateFormat)) {
				isValuePresent = ComboBoxHelper.isValuePresent("J2S_ManualDataLoad_DateFormat", dateFormat);
				if (isValuePresent)
					ComboBoxHelper.select("J2S_ManualDataLoad_DateFormat", dateFormat);
				else {
					ComboBoxHelper.select("J2S_ManualDataLoad_DateFormat", "Custom");
					assertTrue(TextBoxHelper.isEnabled("J2S_ManualDataLoad_CustomDateFormat"));
					dateFormat = dateFormat.replace("D", "d");
					TextBoxHelper.type("J2S_ManualDataLoad_CustomDateFormat", dateFormat);
				}
			}
			
			if(truncateAndLoad)
				CheckBoxHelper.check("J2S_ManualDataLoad_TruncatAndLoad");
			if(trimQuotes)
				CheckBoxHelper.check("J2S_ManualDataLoad_TrimQuotes");
			if(convertDecimal)
				CheckBoxHelper.check("J2S_ManualDataLoad_ConvertDecimal");
			if(skipHeader)
				CheckBoxHelper.check("J2S_ManualDataLoad_SkipHeader");

			ButtonHelper.click("J2S_ManualDataLoad_Import");
			GenericHelper.waitForElementToDisappear("J2S_ManualDataLoad_Import", searchScreenWaitSec);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			GenericHelper.waitForElement("OKButton", configProp.getCustomScreenWaitSec());
			assertTrue(PopupHelper.isTextPresent("Import successfully completed"),
					"Manual Data Load for table '" + tableDisplayName + "' did not happen.");
			ButtonHelper.click("OKButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);

			if (ValidationHelper.isNotEmpty(recordCount))
				verifyRecordCount(tableType, fromDate, toDate, recordCount);
			
			Log4jHelper.logInfo("Manual Data Load successfully completed for table '" + tableDisplayName + "'");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void exportAllRows(String path, String WorkbookName, String workSheetName, String testCaseName, int occurance) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurance );
			
			for(int i = 0; i < excelData.get("Display Name").size(); i++) {
				String tableDisplayName = excelData.get("Display Name").get(i);
				String fromDate = excelData.get("From Date").get(i);
				String toDate = excelData.get("To Date").get(i);
				
				exportAllRows(tableDisplayName, fromDate, toDate);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void exportAllRows(String tableDisplayName, String fromDate, String toDate) throws Exception {
		try {
			navigateToJ2S(tableDisplayName);
			
			setDates(fromDate, toDate);
			
			ExportHelper export = new ExportHelper();
			export.exportAllRows();
			Log4jHelper.logInfo("Export All Rows scheduled for table '" + tableDisplayName + "'");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyExportAllRows(String path, String WorkbookName, String workSheetName, String testCaseName, int occurance) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurance );
			
			for(int i = 0; i < excelData.get("Comparison File").size(); i++) {
				String tableName = excelData.get("Table Name").get(i);
				String exportDirectory = excelData.get("Export Directory").get(i);
				String downloadDirectory = excelData.get("Download Directory").get(i);
				String comparisonFile = excelData.get("Comparison File").get(i);
				String[] linesValuesToIgnore = testData.getStringValue(excelData.get("Lines Values To Ignore").get(i), firstLevelDelimiter);
				
				verifyExportAllRows(tableName, exportDirectory, downloadDirectory, comparisonFile, linesValuesToIgnore);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyExportAllRows(String tableName, String exportDirectory, String downloadDirectory, String comparisonFile, String[] linesValuesToIgnore) throws Exception {
		try {
			if (!downloadDirectory.endsWith("\\") && !downloadDirectory.endsWith("/"))
				downloadDirectory = downloadDirectory + "\\";
			String fileName = FileHelper.getLastModifiedFile(applicationOS, exportDirectory, tableName);
			
			if (ValidationHelper.isNotEmpty(fileName)) {
				FileHelper.copyFile(applicationOS, exportDirectory, downloadDirectory, fileName, fileName, true);
				UnzipHelper unzip = new UnzipHelper();
				String[] fileNames = unzip.unzip(downloadDirectory + fileName, downloadDirectory);
				FileHelper.compareFiles(comparisonFile, downloadDirectory + fileNames[0], linesValuesToIgnore);
			}
			else {
				FailureHelper.failTest("Export file for table '" + tableName + "' is not found in Export directory '" + exportDirectory + "'.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createCaseInstance(String path, String WorkbookName, String workSheetName, String testCaseName, int occurance) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurance );
			
			for(int i = 0; i < excelData.get("Display Name").size(); i++) {
				String tableDisplayName = excelData.get("Display Name").get(i);
				String fromDate = excelData.get("From Date").get(i);
				String toDate = excelData.get("To Date").get(i);
				String cellValue = excelData.get("Cell Value").get(i);
				String valueColumnHeader = excelData.get("Value Column Header").get(i);
				int startRowNo = testData.getIntegerValue(excelData.get("Start Row Number").get(i));
				int noOfRows = testData.getIntegerValue(excelData.get("Number of Rows").get(i));
				
				String caseTemplateName = excelData.get("Case Template Name").get(i);
				boolean createSingleCase = ValidationHelper.isTrue(excelData.get("Create Single Case").get(i));
				boolean notify = ValidationHelper.isTrue(excelData.get("Notify").get(i));
				
				String[][] casePropertyMapping = testData.getStringValue(excelData.get("Case Property Mapping").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String expiresIn = excelData.get("Case Expires In").get(i);
				String cost = excelData.get("Case Cost").get(i);
				String severity = excelData.get("Case Severity").get(i);
				
				createCaseInstance(tableDisplayName, fromDate, toDate, cellValue, valueColumnHeader, startRowNo, noOfRows, caseTemplateName,
						createSingleCase, notify, casePropertyMapping, expiresIn, cost, severity);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createCaseInstance(String tableDisplayName, String fromDate, String toDate, String cellValue, String valueColumnHeader,
			int startRowNo, int noOfRows, String caseTemplateName, boolean createSingleCase, boolean notify, String[][] casePropertyMapping,
			String expiresIn, String cost, String severity) throws Exception {
		try {
			navigateToJ2S(tableDisplayName);
			setDates(fromDate, toDate);
			int row = 0;
			
			if (ValidationHelper.isNotEmpty(cellValue)) {
				row = GridHelper.getRowNumber("SearchGrid", cellValue, valueColumnHeader);
			
				if (row > 0) {
					GridHelper.clickRow("SearchGrid", row, valueColumnHeader);
				}
				else {
					FailureHelper.failTest("Value '" + cellValue + "' not found in column '" + valueColumnHeader + "'.");
				}
			}
			else {
				row = startRowNo;
				GridHelper.clickMultipleCells("SearchGrid", startRowNo, noOfRows);
			}
			
			NavigationHelper.navigateToAction("Case", "Create Case Instance");
		    GenericHelper.waitForLoadmask(searchScreenWaitSec);
		    GenericHelper.waitForElement("LinkCaseTemplate_Save", searchScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Create Case Instance"), "Create Case Instance popup did not appear.");
			
			MeasureHelper measure = new MeasureHelper();
			measure.linkCaseTemplate(caseTemplateName, createSingleCase, notify, casePropertyMapping, expiresIn, cost, severity);
			
			Log4jHelper.logInfo("Case Instance created successfully from Jump to Search screen.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void viewRawRecord(String path, String WorkbookName, String workSheetName, String testCaseName, int occurance) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurance );
			
			for(int i = 0; i < excelData.get("Display Name").size(); i++) {
				String tableDisplayName = excelData.get("Display Name").get(i);
				String fromDate = excelData.get("From Date").get(i);
				String toDate = excelData.get("To Date").get(i);
				String cellValue = excelData.get("Cell Value").get(i);
				String valueColumnHeader = excelData.get("Value Column Header").get(i);
				
				String[][] recordTypes = testData.getStringValue(excelData.get("Expected Record Types").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String recordType = excelData.get("Record Type").get(i);
				String[][] expectedValue = testData.getStringValue(excelData.get("Expected Values").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				viewRawRecord(tableDisplayName, fromDate, toDate, cellValue, valueColumnHeader, recordTypes, recordType, expectedValue);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void viewRawRecord(String tableDisplayName, String fromDate, String toDate, String cellValue, String valueColumnHeader,
			String[][] recordTypes, String recordType, String[][] expectedValue) throws Exception {
		try {
			navigateToJ2S(tableDisplayName);
			setDates(fromDate, toDate);
			int row = GridHelper.getRowNumber("SearchGrid", cellValue, valueColumnHeader);
			
			if (row > 0) {
				GridHelper.clickRow("SearchGrid", row, valueColumnHeader);
				NavigationHelper.navigateToAction("Drill Down", "View Raw Record");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				GenericHelper.waitForAJAXReady(searchScreenWaitSec);
				Thread.sleep(500);
				
				if (LabelHelper.isTitlePresent("View Raw Record")) {
					FailureHelper.failTest("View Raw Record poup did not appear. May be Server Service is not running.");
				}
				else {
					LabelHelper.isTitlePresent("Decoded  Record:");
					
					if (ValidationHelper.isNotEmpty(recordTypes)) {
						for (int i = 0; i < recordTypes.length; i++) {
							assertTrue(TreeHelper.isValuePresent("J2S_ViewRawRecord_RecordTree", recordTypes[i][0]),
									"Value '" + recordTypes[i][0] + "' is not found in View Raw Record popup.");
							
							if (recordTypes.length > 1) {
								for (int j = 1; j < recordTypes[i].length; j++) {
									assertTrue(TreeHelper.isValuePresent("J2S_ViewRawRecord_RecordTree", recordTypes[i][0], recordTypes[i][j]),
											"Value '" + recordTypes[i][j] + "' is not found under Record Type '" + recordTypes[i][0] + "' in View Raw Record popup.");
								}
							}
						}
					}
					
					if (ValidationHelper.isNotEmpty(expectedValue)) {
						TreeHelper.click("J2S_ViewRawRecord_RecordTree", recordType);
						
						for (int i = 0; i < expectedValue.length; i++) {
							if (ValidationHelper.isNotEmpty(expectedValue[i])) {
								int rowNum = GridHelper.getRowNumber("J2S_ViewRawRecord_Field_Grid", expectedValue[i][0], "Field");
								
								if (rowNum > 0) {
									assertTrue(GridHelper.isValuePresent("J2S_ViewRawRecord_Field_Grid", expectedValue[i][1], "Hex  Value"),
											"Value '" + expectedValue[i][1] + "' is not found in View Raw Record popup Hex Value.");
									assertTrue(GridHelper.isValuePresent("J2S_ViewRawRecord_Field_Grid", expectedValue[i][2], "Parsed  Value"),
											"Value '" + expectedValue[i][2] + "' is not found in View Raw Record popup Parsed Value.");
								}
								else {
									FailureHelper.failTest("Field '" + expectedValue[i][0] + "' is not found in View Raw Record popup for Record Type '" + recordType + "'");
								}
							}
						}
					}
					
					ButtonHelper.click("J2S_ViewRawRecord_Close");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					Log4jHelper.logInfo("View Raw Record is verified in Jump to Search screen for '" + tableDisplayName + "'");
				}
			}
			else {
				FailureHelper.failTest("Value '" + cellValue + "' not found in column '" + valueColumnHeader + "'.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void viewParseStatistics(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Table Display Name").size(); i++)
			{
				String tableDisplayName = excelData.get("Table Display Name").get(i);
				String fromDate = excelData.get("From Date").get(i);
				String toDate = excelData.get("To Date").get(i);
				String fileName = excelData.get("File Name").get(i);
				
				String inputRecords = excelData.get("Input Records").get(i);
				String outputRecords = excelData.get("Output Records").get(i);
				String inputUsage = excelData.get("Input Usage").get(i);
				String outputUsage = excelData.get("Output Usage").get(i);
				String firstRecordDate = excelData.get("First Record Date").get(i);
				String lastRecordDate = excelData.get("Last Record Date").get(i);
				String earliestRecordDate = excelData.get("Earliest Record Date").get(i);
				String latestRecordDate = excelData.get("Latest Record Date").get(i);
				String firstRecordSeqNo = excelData.get("First Record Seq No").get(i);
				String lastRecordSeqNo = excelData.get("Last Record Seq No").get(i);
				String firstBlockSeqNo = excelData.get("First Block Seq No").get(i);
				String lastBlockSeqNo = excelData.get("Last Block Seq No").get(i);
				String[][] recordTypes = testData.getStringValue(excelData.get("Record Types").get(i), secondLevelDelimiter, configProp.getThirdLevelDelimiter());
				
				viewParseStatistics(tableDisplayName, fromDate, toDate, fileName, inputRecords, outputRecords, inputUsage, outputUsage,
						firstRecordDate, lastRecordDate, earliestRecordDate, latestRecordDate, firstRecordSeqNo, lastRecordSeqNo, firstBlockSeqNo,
						lastBlockSeqNo, recordTypes);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void viewParseStatistics(String tableDisplayName, String fromDate, String toDate, String fileName, String inputRecords,
			String outputRecords, String inputUsage, String outputUsage, String firstRecordDate, String lastRecordDate, String earliestRecordDate,
			String latestRecordDate, String firstRecordSeqNo, String lastRecordSeqNo, String firstBlockSeqNo, String lastBlockSeqNo,
			String[][] recordTypes) throws Exception {
		try {
			navigateToJ2S(tableDisplayName);
			setDates(fromDate, toDate);
			
			int rows = GridHelper.getRowCount("SearchGrid");
			
			if (rows > 0) {
				GridHelper.clickRow("SearchGrid", 1, 1);
				NavigationHelper.navigateToAction("Parse Statistics", "View Parse Statistics");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
				CollectedFilesHelper collectedFiles = new CollectedFilesHelper();
				collectedFiles.viewParseStatistics(fileName, inputRecords, outputRecords, inputUsage, outputUsage, firstRecordDate, lastRecordDate,
						earliestRecordDate, latestRecordDate, firstRecordSeqNo, lastRecordSeqNo, firstBlockSeqNo, lastBlockSeqNo, recordTypes);
				
				Log4jHelper.logInfo("Parse Statistics is verified in Jump to Search screen for '" + tableDisplayName + "'");
			}
			else {
				FailureHelper.failTest("No rows found in Jump to Search screen of '" + tableDisplayName + "'");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void setDates(String fromDate, String toDate) throws Exception {
		try {
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			GenericHelper.waitForAJAXReady(searchScreenWaitSec);
			Thread.sleep(1000);
			
			if (ValidationHelper.isNotEmpty(fromDate))
				TextBoxHelper.type("J2S_FromDate", fromDate);
			
			if (ValidationHelper.isNotEmpty(toDate))
				TextBoxHelper.type("J2S_ToDate", toDate);
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void navigateToJ2S(String j2sScreenTitle) throws Exception {
		try {
			if (LabelHelper.isTitleNotPresent(j2sScreenTitle)) {
				int row = navigateToTableInstance(j2sScreenTitle);
				
				if (row > 0) {
					GridHelper.clickRow("SearchGrid", row, 1);
					NavigationHelper.navigateToAction("Table Actions", "Jump To Search");
					assertTrue(LabelHelper.isTitlePresent(j2sScreenTitle), "Jump to Search screen did not appear.");
				}
				else {
					FailureHelper.failTest("Table '" + j2sScreenTitle + "' is not found.");
				}
			}
			else {
				ButtonHelper.click("ClearButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				GenericHelper.collapseSearchFilterPanel();
				
				ButtonHelper.click("SearchButton");
				GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
			}
			
			Thread.sleep(500);
			GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public int navigateToTableInstance(String tableDisplayName) throws Exception {
		try {
			NavigationHelper.navigateToScreen( "Table Instances", "Table Instance Search" );
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			SearchGridHelper.gridFilterSearchWithTextBox("Grid_Filter_Panel", "TableInst_DisplayName", tableDisplayName, "Display Name");
			int row = GridHelper.getRowNumberContains("SearchGrid", tableDisplayName, "Display Name");
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}