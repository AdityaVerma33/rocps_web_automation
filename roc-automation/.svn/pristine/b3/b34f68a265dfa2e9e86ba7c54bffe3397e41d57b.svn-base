package com.subex.automation.testcases.regressiontesting.etl.etl_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class test137221 extends testETLIssues {
	
	final String sheetName = "test137221";
	
	public test137221() throws Exception {
		super();
	}
	
	@Test(priority=101, description="Test Case 1 for Bug 137221")
	public void testCase1() throws Exception {
		try {
			// In Query Measure detail screen, click Add Table Instance and check if Clear functionality is working in Table Instance entity search screen.
			String testCaseName = "TableInstance_137221";
			createTDTI(sheetName, "ImportFromDiamond_137221", 1, testCaseName, 1);
			
			NavigationHelper.navigateToScreen("Measures", "Measure Search");
			NavigationHelper.navigateToAction("Common Tasks", "New", "Query Measure");
			NavigationHelper.selectPartition("Common");
			assertTrue(LabelHelper.isTitlePresent("New Query Measure"));
			
			ButtonHelper.click("QM_AddTableInstance");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Table Instance Search"));
			
			assertEquals(ComboBoxHelper.getValue("TableInst_IncludeSystem_Filter"), "User Tables");
			int rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows > 0);
			
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertEquals(ComboBoxHelper.getValue("TableInst_IncludeSystem_Filter"), "(All)");
			rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows == 0);
			
			ButtonHelper.click("CancelButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=102, description="Test Case 2 for Bug 137221")
	public void testCase2() throws Exception {
		try {
			// In Query Measure detail screen, click Add Table Instance and check if Search functionality is working in Table Instance entity search screen.
			String testCaseName = "TableInstance_137221";
			createTDTI(sheetName, "ImportFromDiamond_137221", 1, testCaseName, 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, testCaseName, 1 );
			String tableName = excelData.get("Table Name").get(0);
			
			NavigationHelper.navigateToScreen("Measures", "Measure Search");
			NavigationHelper.navigateToAction("Common Tasks", "New", "Query Measure");
			NavigationHelper.selectPartition("Common");
			assertTrue(LabelHelper.isTitlePresent("New Query Measure"));
			
			ButtonHelper.click("QM_AddTableInstance");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Table Instance Search"));
			
			assertEquals(ComboBoxHelper.getValue("TableInst_IncludeSystem_Filter"), "User Tables");
			int rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows > 0);
			
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertEquals(ComboBoxHelper.getValue("TableInst_IncludeSystem_Filter"), "(All)");
			rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows == 0);
			
			int row = SearchGridHelper.gridFilterSearchWithTextBox("TableInst_TableName", tableName, "Table Name");
			assertTrue(row >= 1);
			
			ButtonHelper.click("CancelButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=103, description="Test Case 3 for Bug 137221")
	public void testCase3() throws Exception {
		try {
			// In Table Instance Group detail screen, click Add Table Instance and check if Clear functionality is working in Table Instance entity search screen.
			String testCaseName = "TableInstance_137221";
			createTDTI(sheetName, "ImportFromDiamond_137221", 1, testCaseName, 1);
			
			NavigationHelper.navigateToScreen("Table Instance Groups", "Table Instance Group Search");
			NavigationHelper.navigateToNew("Common", "TIG_AddTableInstance");
			assertTrue(LabelHelper.isTitlePresent("New Table Instance Group"));
			
			ButtonHelper.click("TIG_AddTableInstance");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Table Instance Search"));
			
			assertEquals(ComboBoxHelper.getValue("TableInst_IncludeSystem_Filter"), "User Tables");
			int rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows > 0);
			
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertEquals(ComboBoxHelper.getValue("TableInst_IncludeSystem_Filter"), "User Tables");
			rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows == 0);
			
			ButtonHelper.click("CancelButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=104, description="Test Case 4 for Bug 137221")
	public void testCase4() throws Exception {
		try {
			// In Table Instance Group screen, click Add Table Instance and check if Search functionality is working in Table Instance entity search screen.
			String testCaseName = "TableInstance_137221";
			createTDTI(sheetName, "ImportFromDiamond_137221", 1, testCaseName, 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, testCaseName, 1 );
			String tableName = excelData.get("Table Name").get(0);
			
			NavigationHelper.navigateToScreen("Table Instance Groups", "Table Instance Group Search");
			NavigationHelper.navigateToNew("Common", "TIG_AddTableInstance");
			assertTrue(LabelHelper.isTitlePresent("New Table Instance Group"));
			
			ButtonHelper.click("TIG_AddTableInstance");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Table Instance Search"));
			
			assertEquals(ComboBoxHelper.getValue("TableInst_IncludeSystem_Filter"), "User Tables");
			int rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows > 0);
			
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertEquals(ComboBoxHelper.getValue("TableInst_IncludeSystem_Filter"), "User Tables");
			rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows == 0);
			
			int row = SearchGridHelper.gridFilterSearchWithTextBox("TableInst_TableName", tableName, "Table Name");
			assertTrue(row >= 1);
			
			ButtonHelper.click("CancelButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void navigateToParseOutputMap() throws Exception {
		try {
			NavigationHelper.navigateToNew("Common", "Stream_StreamStage_Add");
			assertTrue(LabelHelper.isTitlePresent("New Stream"));
			
			ButtonHelper.click("Stream_StreamStage_Add");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("New Stream Stage"));
			
			ComboBoxHelper.select("Stream_StreamStage_Type", "Parse");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			PropertyGridHelper.typeInDataDir("Output Directory *", "DataDir!ParseOutput", "!");
			PropertyGridHelper.typeInDataDir("Parser Definition File *", "DataDir!Diamond_XMLs!MSC.xml", "!");
			
			ButtonHelper.click( "OKButton" );
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			ButtonHelper.click( "YesButton" );
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue( LabelHelper.isTitlePresent( "Parse Output Maps" ), "Parse Output Map popup did not appear." );
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=105, description="Test Case 5 for Bug 137221")
	public void testCase5() throws Exception {
		try {
			// In Table Instance Group screen, click Add Table Instance and check if Search functionality is working in Table Instance entity search screen.
			String testCaseName = "TableInstance_137221";
			createTDTI(sheetName, "ImportFromDiamond_137221", 1, testCaseName, 1);
			
			NavigationHelper.navigateToScreen("Streams", "Stream Search");
			if (NavigationHelper.isActionPresent("Common Tasks")) {
				navigateToParseOutputMap();
				
				ButtonHelper.click("Stream_ParseOutput_Add");
				GridHelper.clickRow("Stream_ParseOutput_Grid", 1, "Table Instance");
				if (!EntityComboHelper.isPresent("Stream_ParseOutput_TableInstance"))
					GridHelper.clickRow("Stream_ParseOutput_Grid", 1, "Table Instance");
				EntityComboHelper.clickEntityIcon("Stream_ParseOutput_TableInstance");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Table Instance Search"));
				
				assertEquals(ComboBoxHelper.getValue("TableInst_IncludeSystem_Filter"), "User Tables");
				int rows = GridHelper.getRowCount("SearchGrid");
				assertTrue(rows > 0);
				
				ButtonHelper.click("ClearButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				assertEquals(ComboBoxHelper.getValue("TableInst_IncludeSystem_Filter"), "(All)");
				rows = GridHelper.getRowCount("SearchGrid");
				assertTrue(rows == 0);
				
				ButtonHelper.click("CancelButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
				ButtonHelper.click("Stream_ParseOutput_Cancel");
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
	
	@Test(priority=106, description="Test Case 6 for Bug 137221")
	public void testCase6() throws Exception {
		try {
			// In Table Instance Group screen, click Add Table Instance and check if Search functionality is working in Table Instance entity search screen.
			String testCaseName = "TableInstance_137221";
			createTDTI(sheetName, "ImportFromDiamond_137221", 1, testCaseName, 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, testCaseName, 1 );
			String tableName = excelData.get("Table Name").get(0);
			
			NavigationHelper.navigateToScreen("Streams", "Stream Search");
			if (NavigationHelper.isActionPresent("Common Tasks")) {
				navigateToParseOutputMap();
				
				ButtonHelper.click("Stream_ParseOutput_Add");
				GridHelper.clickRow("Stream_ParseOutput_Grid", 1, "Table Instance");
				if (!EntityComboHelper.isPresent("Stream_ParseOutput_TableInstance"))
					GridHelper.clickRow("Stream_ParseOutput_Grid", 1, "Table Instance");
				EntityComboHelper.clickEntityIcon("Stream_ParseOutput_TableInstance");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Table Instance Search"));
				
				assertEquals(ComboBoxHelper.getValue("TableInst_IncludeSystem_Filter"), "User Tables");
				int rows = GridHelper.getRowCount("SearchGrid");
				assertTrue(rows > 0);
				
				ButtonHelper.click("ClearButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				assertEquals(ComboBoxHelper.getValue("TableInst_IncludeSystem_Filter"), "(All)");
				rows = GridHelper.getRowCount("SearchGrid");
				assertTrue(rows == 0);
				
				int row = SearchGridHelper.gridFilterSearchWithTextBox("TableInst_TableName", tableName, "Table Name");
				assertTrue(row >= 1);
				
				ButtonHelper.click("CancelButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);

				ButtonHelper.click("Stream_ParseOutput_Cancel");
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
}