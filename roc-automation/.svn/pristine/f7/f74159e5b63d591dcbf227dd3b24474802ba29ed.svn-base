package com.subex.automation.testcases.regressiontesting.datafederation;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.FederatedDataSourceHelper;
import com.subex.automation.helpers.application.screens.FederatedViewHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.application.screens.TableDefinitionHelper;
import com.subex.automation.helpers.application.screens.TableInstanceHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.scripts.ExecuteScript;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testEditView extends ROCAcceptanceTest {
	
	static String path = null;
	final String fileName = "DataFederation_TestData.xlsx";
	final String sheetName = "EditView";
	
	public testEditView() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void verifyTDTI(String tableDfnName, String tableInstName, String j2sTestCaseName) throws Exception {
		try {
			TableDefinitionHelper tableDfn = new TableDefinitionHelper();
			int row = tableDfn.navigateToTableDefinition(tableDfnName);
			assertTrue(row > 0);
			
			TableInstanceHelper tableInstance = new TableInstanceHelper();
			row = tableInstance.navigateToTableInstance(tableInstName);
			assertTrue(row > 0);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, j2sTestCaseName, 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Edit of Federated View", groups = { "edit" })
	public void testEdit() throws Exception
	{
		try {
			// The Remote Data Source View should get updated. Corresponding table definition and table instance should also get updated.
			FederatedDataSourceHelper fedDataSource = new FederatedDataSourceHelper();
			fedDataSource.createFederatedDataSource(path, fileName, sheetName, "FederatedDataSource", 1);
			
			String scriptFileName = automationPath + "\\src\\main\\resources\\System_Test_Flows\\FederatedDataSource_Script.sql";
			ExecuteScript.exeScript(scriptFileName, "FederatedDataSource");
			
			FederatedViewHelper fedView = new FederatedViewHelper();
			fedView.createFederatedView(path, fileName, sheetName, "FederatedView", 1);
			
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "FederatedView", 1 );
			String name = excelData.get("Name").get(0);
			String[] removeColumns = testData.getStringValue(excelData.get("Remove Columns").get(0), firstLevelDelimiter);
			String[] targetName = testData.getStringValue(excelData.get("Target Name").get(0), firstLevelDelimiter);
			
			verifyTDTI(name, name, "J2SEtopup");
			
			testCreateView createView = new testCreateView();
			String detailScreenTitle = createView.navigateToEdit(name);
			fedView.updateFederatedView(path, fileName, sheetName, "EditFederatedView", 1);
			fedView.saveFederatedView(name, detailScreenTitle);
			
			createView.verifyTDColumns(name, targetName, removeColumns);
			
			verifyTDTI(name, name, "J2SEtopup");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}