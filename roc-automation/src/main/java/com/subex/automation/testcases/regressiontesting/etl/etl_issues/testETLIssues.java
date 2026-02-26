package com.subex.automation.testcases.regressiontesting.etl.etl_issues;

import com.subex.automation.helpers.application.screens.TableDefinitionHelper;
import com.subex.automation.helpers.application.screens.TableInstanceHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TreeHelper;
import com.subex.automation.helpers.data.StringHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testETLIssues extends ROCAcceptanceTest {
	
	String path = null;
	final String fileName = "ETLIssues_TestData.xlsx";
	
	static String[] streams = null;
	static String[][] streamStages = new String[100][100];
	
	public testETLIssues() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Issues\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createTDTI(String sheetName, String tdTestCase, int tdOccurance, String tiTestCase, int tiOccurance) throws Exception {
		try {
			// Create a Standard Table Instance with Table name length of 25 characters
			TableDefinitionHelper tableDefinition = new TableDefinitionHelper();
			tableDefinition.importFromDiamond(path, fileName, sheetName, tdTestCase, tdOccurance);
			
			TableInstanceHelper tableInstance = new TableInstanceHelper();
			tableInstance.createTableInstance(path, fileName, sheetName, tiTestCase, tiOccurance);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void getStreamStageDetails() throws Exception {
		try {
			int rows = GridHelper.getRowCount("SearchGrid");
			streams = new String[rows];
			streamStages = new String[rows][100];
			for (int i = 0; i < rows; i++) {
				streams[i] = GridHelper.getCellValue("SearchGrid", (i+1), "Stream Stage");
				TreeHelper.expandTreeGrid("SearchGrid", streams[i]);
				int newCount = GridHelper.getRowCount("SearchGrid");
				for (int j = 0, row = (i+2); j < (newCount-rows); j++, row++) {
					streamStages[i][j] = GridHelper.getCellValue("SearchGrid", row, "Stream Stage");
				}
				TreeHelper.collapseTreeGrid("SearchGrid", streams[i]);
				streamStages = StringHelper.resizeStringArray(streamStages, rows, (newCount-rows));
			}
			
			streamStages = StringHelper.resizeStringArray(streamStages);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyStreamStage(int expectedStreamsCount) throws Exception {
		try {
			int rows = GridHelper.getRowCount("SearchGrid");
			assertEquals(rows, expectedStreamsCount);
			for (int i = 0; i < expectedStreamsCount; i++) {
				String stream = GridHelper.getCellValue("SearchGrid", (i+1), "Stream Stage");
				assertEquals(stream, streams[i]);
				TreeHelper.expandTreeGrid("SearchGrid", stream);
				int newCount = GridHelper.getRowCount("SearchGrid");
				
				for (int j = 0, row = (i+2); j < (newCount-rows); j++, row++) {
					String streamStage = GridHelper.getCellValue("SearchGrid", row, "Stream Stage");
					assertEquals(streamStage, streamStages[i][j]);
				}
				TreeHelper.collapseTreeGrid("SearchGrid", streams[i]);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void doubleClickSummary(String stream, String streamStage) throws Exception {
		try {
			assertTrue(GridHelper.isValuePresent("SearchGrid", stream, "Stream Stage"));
			TreeHelper.expandTreeGrid("SearchGrid", stream);
			assertTrue(GridHelper.isValuePresent("SearchGrid", streamStage, "Stream Stage"));
			
			int row = GridHelper.getRowNumber("SearchGrid", streamStage, "Stream Stage");
			String count = GridHelper.getCellValue("SearchGrid", row, "Completed");
			int expectedRows = Integer.parseInt(count);
			if (expectedRows == 0) {
				count = GridHelper.getCellValue("SearchGrid", row, "Waiting");
				expectedRows = Integer.parseInt(count);
			}
			
			if (expectedRows > 0) {
				GridHelper.doubleClick("SearchGrid", row, "Completed");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				assertTrue(TabHelper.isSelected("SearchTab"));
				int rows = GridHelper.getRowCount("SearchGrid");
				assertEquals(rows, expectedRows);
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