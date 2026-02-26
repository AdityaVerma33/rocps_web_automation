package com.subex.automation.testcases.regressiontesting.etl.etl_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.application.screens.TableDefinitionHelper;
import com.subex.automation.helpers.application.screens.TableInstanceHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test137530 extends testETLIssues {
	
	final String sheetName = "test137530";
	
	public test137530() throws Exception {
		super();
	}
	
	@Test(priority=141, description="Test Case 1 for Bug 137530")
	public void testCase1() throws Exception {
		try {
			// "Get Record and Page Count" button should show the row count of the records for System table.
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "SystemJ2S", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=142, description="Test Case 2 for Bug 137530")
	public void testCase2() throws Exception {
		try {
			// "Get Record and Page Count" button should show the row count of the records for Reference table.
			TableDefinitionHelper tableDefinition = new TableDefinitionHelper();
			tableDefinition.importFromDiamond(path, fileName, sheetName, "ImportFromDiamond", 1);
			
			TableInstanceHelper tableInstance = new TableInstanceHelper();
			tableInstance.createTableInstance(path, fileName, sheetName, "TableInstance", 1);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.manualDataLoad(path, fileName, sheetName, "ManualDataLoad", 1);
			
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "ReferenceJ2S", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=143, description="Test Case 3 for Bug 137530")
	public void testCase3() throws Exception {
		try {
			// "Get Record and Page Count" button should show the row count of the records for Usage table.
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "UsageJ2S", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}