package com.subex.automation.testcases.regressiontesting.etl.etl_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.application.screens.TableDefinitionHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test143012 extends testETLIssues {
	
	final String sheetName = "test143012";
	
	public test143012() throws Exception {
		super();
	}
	
	@Test(priority=61, description="Test Case 1 for Bug 143012")
	public void testCase1() throws Exception {
		try {
			// In existing Reference Table Definition, which does not have Table Instance, rename a table column and save
			String testCaseName = "ImportFromDiamond_TC1";
			TableDefinitionHelper tableDefinition = new TableDefinitionHelper();
			tableDefinition.importFromDiamond(path, fileName, sheetName, testCaseName, 1);
			
			tableDefinition.editTableDefinition(path, fileName, sheetName, "EditTD_TC1", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=62, description="Test Case 2 for Bug 143012")
	public void testCase2() throws Exception {
		try {
			// In existing Usage Table Definition, which does not have Table Instance, rename a table column and save
			String testCaseName = "ImportFromDiamond_TC2";
			TableDefinitionHelper tableDefinition = new TableDefinitionHelper();
			tableDefinition.importFromDiamond(path, fileName, sheetName, testCaseName, 2);
			
			tableDefinition.editTableDefinition(path, fileName, sheetName, "EditTD_TC2", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=63, description="Test Case 3 for Bug 143012")
	public void testCase3() throws Exception {
		try {
			// In existing Reference Table Definition, which has one Table Instance, rename a table column and save
			String testCaseName = "ImportFromDiamond_TC3";
			createTDTI(sheetName, testCaseName, 1, "TableInstance_TC3", 1);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.manualDataLoad(path, fileName, sheetName, "ManualDataLoad_TC3", 1);
			
			TableDefinitionHelper tableDefinition = new TableDefinitionHelper();
			tableDefinition.editTableDefinition(path, fileName, sheetName, "EditTD_TC3", 1);
			tableDefinition.synchTableSchema(path, fileName, sheetName, "EditTD_TC3", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=64, description="Test Case 4 for Bug 143012")
	public void testCase4() throws Exception {
		try {
			// In existing Usage Table Definition, which has one Table Instance, rename a table column and save
			TableDefinitionHelper tableDefinition = new TableDefinitionHelper();
			tableDefinition.editTableDefinition(path, fileName, sheetName, "EditTD_TC4", 1);
			tableDefinition.synchTableSchema(path, fileName, sheetName, "EditTD_TC4", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=65, description="Test Case 5 for Bug 143012")
	public void testCase5() throws Exception {
		try {
			// In existing Reference Table Definition, which has one Table Instance, add a table column and save
			TableDefinitionHelper tableDefinition = new TableDefinitionHelper();
			tableDefinition.editTableDefinition(path, fileName, sheetName, "EditTD_TC5", 1);
			tableDefinition.synchTableSchema(path, fileName, sheetName, "EditTD_TC5", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=66, description="Test Case 6 for Bug 143012")
	public void testCase6() throws Exception {
		try {
			// In existing Reference Table Definition, which has one Table Instance, add a table column and save
			TableDefinitionHelper tableDefinition = new TableDefinitionHelper();
			tableDefinition.editTableDefinition(path, fileName, sheetName, "EditTD_TC6", 1);
			tableDefinition.synchTableSchema(path, fileName, sheetName, "EditTD_TC6", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=67, description="Test Case 7 for Bug 143012")
	public void testCase7() throws Exception {
		try {
			// In existing Reference Table Definition, which has one Table Instance, delete a table column and save
			TableDefinitionHelper tableDefinition = new TableDefinitionHelper();
			tableDefinition.editTableDefinition(path, fileName, sheetName, "EditTD_TC7", 1);
			tableDefinition.synchTableSchema(path, fileName, sheetName, "EditTD_TC7", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=68, description="Test Case 8 for Bug 143012")
	public void testCase8() throws Exception {
		try {
			// In existing Usage Table Definition, which has one Table Instance, delete a table column and save
			TableDefinitionHelper tableDefinition = new TableDefinitionHelper();
			tableDefinition.editTableDefinition(path, fileName, sheetName, "EditTD_TC8", 1);
			tableDefinition.synchTableSchema(path, fileName, sheetName, "EditTD_TC8", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=69, description="Test Case 9 for Bug 143012")
	public void testCase9() throws Exception {
		try {
			// In existing Reference Table Definition, which has more than one Table Instance, rename a table column and save
			String testCaseName = "ImportFromDiamond_TC9";
			createTDTI(sheetName, testCaseName, 1, "TableInstance_TC9", 1);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.manualDataLoad(path, fileName, sheetName, "ManualDataLoad_TC9", 1);
			
			TableDefinitionHelper tableDefinition = new TableDefinitionHelper();
			tableDefinition.editTableDefinition(path, fileName, sheetName, "EditTD_TC9", 1);
			tableDefinition.synchTableSchema(path, fileName, sheetName, "EditTD_TC9", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=70, description="Test Case 10 for Bug 143012")
	public void testCase10() throws Exception {
		try {
			// In existing Usage Table Definition, which has more than one Table Instance, rename a table column and save
			TableDefinitionHelper tableDefinition = new TableDefinitionHelper();
			tableDefinition.editTableDefinition(path, fileName, sheetName, "EditTD_TC10", 1);
			tableDefinition.synchTableSchema(path, fileName, sheetName, "EditTD_TC10", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}