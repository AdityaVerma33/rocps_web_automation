package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.component.TreeHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class CollectedFilesHelper extends ROCAcceptanceTest {
	
	public int navigateToCollectedFiles(String fileName) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Collected Files", "Collected File Search");
			CalendarHelper.setToday("CollectedFiles_PolledDate");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("CollectedFiles_FileName", fileName, "File Name");
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void processCollectedFile(String fileName, int waitSeconds) throws Exception {
		try {
			navigateToCollectedFiles(fileName);
			int row = SearchGridHelper.gridFilterSearchWithComboBox("CollectedFiles_Status", "Pending", "Status");
			
			if (row == 0)
				FailureHelper.failTest("Expected file '" + fileName + "' did not appear in Collected Files screen");
			else {
				GridHelper.clickRow("SearchGrid", row, 1);
				NavigationHelper.navigateToAction("File Actions", "Process File");
			}
			
			SearchGridHelper.gridFilterSearchWithComboBox("CollectedFiles_Status", "(All)", "Status");
			row = GridHelper.getRowNumber("SearchGrid", fileName, "File Name");
			int tryCount = 0;
			String status = null;
			
			while(true)
			{
				status = GridHelper.getCellValue("SearchGrid", row, "Status");
				if (status.equals("Completed") || tryCount == waitSeconds)
					break;
				Thread.sleep(1000);
				ButtonHelper.click("SearchButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				tryCount++;
			}
			
			if (status == null)
				FailureHelper.failTest("Expected file '" + fileName + "' did not appear in Collected Files screen");
			else if (!status.equals("Completed"))
				FailureHelper.failTest("The file '" + fileName + "' is expected to be in 'Completed' status. But status is '" + status + "' in Collected Files screen.");
			else
				Log4jHelper.logInfo("The Process File action was selected for the file '" + fileName + "' in Collected Files screen.");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyCollectedFiles(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Result Count").size(); i++)
			{
				String fileSource = excelData.get("File Source").get(i);
				String fileCollection = excelData.get("File Collection").get(i);
				String token = excelData.get("Token").get(i);
				String polledDateType = excelData.get("Polled Date Type").get(i);
				String fromDate = excelData.get("From Date").get(i);
				String toDate = excelData.get("To Date").get(i);
				String status = excelData.get("Status").get(i);
				String fileName = excelData.get("File Name").get(i);
				String duplicate = excelData.get("Duplicate").get(i);
				int resultCount = testData.getIntegerValue(excelData.get("Result Count").get(i));
				
				verifyCollectedFiles(fileSource, fileCollection, token, polledDateType, fromDate, toDate, status, fileName, duplicate, resultCount);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyCollectedFiles(String fileSource, String fileCollection, String token, String polledDateType, String fromDate, String toDate,
			String status, String fileName, String duplicate, int resultCount) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Collected Files", "Collected File Search");
			CalendarHelper.setDate("CollectedFiles_PolledDate", polledDateType, fromDate, toDate);
			
			ComboBoxHelper.select("CollectedFiles_FileSource", fileSource);
			ComboBoxHelper.select("CollectedFiles_Token", token);
			SearchGridHelper.gridFilterSearchWithComboBox("CollectedFiles_FileCollection", fileCollection, "File Collection");
			SearchGridHelper.gridFilterSearchWithComboBox("CollectedFiles_Status", status, "Status");
			SearchGridHelper.gridFilterSearchWithTextBox("CollectedFiles_FileName", fileName, "File Name");
			SearchGridHelper.gridFilterSearchWithComboBox("CollectedFiles_Duplicate", duplicate, "Duplicate");
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			int rows = 0;
			
			int tryCount = 0;
			int maxRetry = configProp.getIntegerProperty("collectedFilesTryCount");
			
			while(true)
			{
				rows = GridHelper.getRowCount("SearchGrid");
				
				if (rows == resultCount || tryCount == maxRetry)
					break;
				Thread.sleep(1000);
				ButtonHelper.click("SearchButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				tryCount++;
			}
			
			if (rows >= resultCount)
				Log4jHelper.logInfo("Expected number of files '" + resultCount + "' are found in Collected Files screen.");
			else
				FailureHelper.failTest("Expected result count '" + resultCount + "' did not appear in Collected Files screen");
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
	
			for(int i = 0; i < excelData.get("File Name").size(); i++)
			{
				String fileSource = excelData.get("File Source").get(i);
				String polledDateType = excelData.get("Polled Date Type").get(i);
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
				
				viewParseStatistics(fileSource, polledDateType, fromDate, toDate, fileName, inputRecords, outputRecords, inputUsage, outputUsage,
						firstRecordDate, lastRecordDate, earliestRecordDate, latestRecordDate, firstRecordSeqNo, lastRecordSeqNo, firstBlockSeqNo,
						lastBlockSeqNo, recordTypes);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void viewParseStatistics(String fileSource, String polledDateType, String fromDate, String toDate, String fileName, String inputRecords,
			String outputRecords, String inputUsage, String outputUsage, String firstRecordDate, String lastRecordDate, String earliestRecordDate,
			String latestRecordDate, String firstRecordSeqNo, String lastRecordSeqNo, String firstBlockSeqNo, String lastBlockSeqNo,
			String[][] recordTypes) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Collected Files", "Collected File Search");
			
			CalendarHelper.setDate("CollectedFiles_PolledDate", polledDateType, fromDate, toDate);
			ComboBoxHelper.select("CollectedFiles_FileSource", fileSource);
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			int row = GridHelper.getRowNumber("SearchGrid", fileName, "File Name");
			
			if (row > 0) {
				GridHelper.clickRow("SearchGrid", row, "File Name");
				NavigationHelper.navigateToAction("File Actions", "Open Parse Statistics");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
				viewParseStatistics(fileName, inputRecords, outputRecords, inputUsage, outputUsage, firstRecordDate, lastRecordDate,
						earliestRecordDate, latestRecordDate, firstRecordSeqNo, lastRecordSeqNo, firstBlockSeqNo, lastBlockSeqNo, recordTypes);
				
				Log4jHelper.logInfo("Parse Statistics for filename '" + fileName + "' is verified in Collected Files screen.");
			}
			else {
				FailureHelper.failTest("Expected file '" + fileName + "' is not found in Collected Files screen");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void viewParseStatistics(String fileName, String inputRecords, String outputRecords, String inputUsage, String outputUsage,
			String firstRecordDate, String lastRecordDate, String earliestRecordDate, String latestRecordDate, String firstRecordSeqNo,
			String lastRecordSeqNo, String firstBlockSeqNo, String lastBlockSeqNo, String[][] recordTypes) throws Exception {
		try {
			if (LabelHelper.isTitlePresent("Information")) {
				assertTrue(PopupHelper.isTextPresent("No File Parse Statistic Available for the selected row"));
				ButtonHelper.click("OKButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
			else {
				assertTrue(LabelHelper.isTitlePresent("View File Parse Statistics"));
				String actualFileName = TextBoxHelper.getValue("CF_ParseStatistics_Filename");
				if (!actualFileName.endsWith(fileName))
					FailureHelper.failTest("Expected Filename '" + fileName + "' but found '" + actualFileName + "' in Parse Statistics popup.");
				
				if (ValidationHelper.isNotEmpty(inputRecords))
					assertEquals(TextBoxHelper.getValue("CF_ParseStatistics_InputRecords"), inputRecords);
				
				if (ValidationHelper.isNotEmpty(outputRecords))
					assertEquals(TextBoxHelper.getValue("CF_ParseStatistics_OutputRecords"), outputRecords);
				
				if (ValidationHelper.isNotEmpty(inputUsage))
					assertEquals(TextBoxHelper.getValue("CF_ParseStatistics_InputUsage"), inputUsage);
				
				if (ValidationHelper.isNotEmpty(outputUsage))
					assertEquals(TextBoxHelper.getValue("CF_ParseStatistics_OutputUsage"), outputUsage);
				
				if (ValidationHelper.isNotEmpty(firstRecordDate))
					assertEquals(TextBoxHelper.getValue("CF_ParseStatistics_FirstRecordDate"), firstRecordDate);
				
				if (ValidationHelper.isNotEmpty(lastRecordDate))
					assertEquals(TextBoxHelper.getValue("CF_ParseStatistics_LastRecordDate"), lastRecordDate);
				
				if (ValidationHelper.isNotEmpty(earliestRecordDate))
					assertEquals(TextBoxHelper.getValue("CF_ParseStatistics_EarliestRecordDate"), earliestRecordDate);
				
				if (ValidationHelper.isNotEmpty(latestRecordDate))
					assertEquals(TextBoxHelper.getValue("CF_ParseStatistics_LatestRecordDate"), latestRecordDate);
				
				if (ValidationHelper.isNotEmpty(firstRecordSeqNo))
					assertEquals(TextBoxHelper.getValue("CF_ParseStatistics_FirstRecordSeqNo"), firstRecordSeqNo);
				
				if (ValidationHelper.isNotEmpty(lastRecordSeqNo))
					assertEquals(TextBoxHelper.getValue("CF_ParseStatistics_LastRecordSeqNo"), lastRecordSeqNo);
				
				if (ValidationHelper.isNotEmpty(firstBlockSeqNo))
					assertEquals(TextBoxHelper.getValue("CF_ParseStatistics_FirstBlockSeqNo"), firstBlockSeqNo);
				
				if (ValidationHelper.isNotEmpty(lastBlockSeqNo))
					assertEquals(TextBoxHelper.getValue("CF_ParseStatistics_LastBlockSeqNo"), lastBlockSeqNo);
				
				if (ValidationHelper.isNotEmpty(recordTypes)) {
					List<WebElement> parents = ElementHelper.getElements("CF_ParseStatistics_RecordsTypes");
					
					for (int i = 0; i < recordTypes.length; i++) {
						String parent = ElementHelper.getText(parents.get(i+1));
						if (!parent.trim().equals(recordTypes[i][0]))
							FailureHelper.failTest("Expected Record Type '" + recordTypes[i][0] + "' is not found in Parse Statistics popup");
						
						for (int j = 1; j < recordTypes[i].length; j++) {
							if (!TreeHelper.isValuePresent("CF_ParseStatistics_Records_Tree", recordTypes[i][j]))
								FailureHelper.failTest("Expected Record Type '" + recordTypes[i][j] + "' is not found in Parse Statistics popup");
						}
					}
				}
				
				ButtonHelper.click("CF_ParseStatistics_Close");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void viewCompressedEntries(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Compress File Name").size(); i++)
			{
				String fileSource = excelData.get("File Source").get(i);
				String polledDateType = excelData.get("Polled Date Type").get(i);
				String fromDate = excelData.get("From Date").get(i);
				String toDate = excelData.get("To Date").get(i);
				
				String compressFileName = excelData.get("Compress File Name").get(i);
				String[] fileNames = testData.getStringValue(excelData.get("File Names").get(i), firstLevelDelimiter);
				
				viewCompressedEntries(fileSource, polledDateType, fromDate, toDate, compressFileName, fileNames);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void viewCompressedEntries(String fileSource, String polledDateType, String fromDate, String toDate, String compressFileName,
			String[] fileNames) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Collected Files", "Collected File Search");
			ComboBoxHelper.select("CollectedFiles_FileSource", fileSource);
			CalendarHelper.setDate("CollectedFiles_PolledDate", polledDateType, fromDate, toDate);
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			int row = GridHelper.getRowNumber("SearchGrid", compressFileName, "File Name");
			
			if (row > 0) {
				GridHelper.clickRow("SearchGrid", row, "File Name");
				NavigationHelper.navigateToAction("File Actions", "View Compressed Entries");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
				viewCompressedEntries(fileNames);
				
				Log4jHelper.logInfo("Parse Statistics for filename '" + compressFileName + "' is verified in Collected Files screen.");
			}
			else {
				FailureHelper.failTest("Expected file '" + compressFileName + "' is not found in Collected Files screen");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void viewCompressedEntries(String[] fileNames) throws Exception {
		try {
			assertTrue(LabelHelper.isTitlePresent("Compressed Entries"));
			for (int i = 0; i < fileNames.length; i++) {
				assertTrue(GridHelper.isValuePresent("CF_CompressedEntries_Grid", fileNames[i], "File  Name"));
			}
			
			ButtonHelper.click("CF_CompressedEntries_Close");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}