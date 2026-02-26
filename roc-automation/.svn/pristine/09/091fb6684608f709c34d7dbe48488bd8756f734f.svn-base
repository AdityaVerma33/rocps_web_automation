package com.subex.automation.testcases.regressiontesting.etl.etl_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test162872 extends testETLIssues {
	
	final String sheetName = "test162872";
	
	public test162872() throws Exception {
		super();
	}
	
	@Test(priority=161, description="Test Case 1 for Bug 162872")
	public void testCase1() throws Exception {
		try {
			// In Jump to Search screen, give From and To for available data and Search. Search should fetch the results
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "UsageJ2S_162872_1", 1);
			
			String expectedCount = "50 of 516";
			String actualCount = SearchGridHelper.getTotalRecordsFetched();
			assertEquals(actualCount, expectedCount);
			int rows = GridHelper.getRowCount("SearchGrid");
			assertEquals(rows, 50);
			
			SearchGridHelper.setPagination("500 per page");
			
			expectedCount = "500";
			actualCount = SearchGridHelper.getTotalRecordsFetched();
			assertEquals(actualCount, expectedCount);
			rows = GridHelper.getRowCount("SearchGrid");
			assertEquals(rows, 500);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=162, description="Test Case 2 for Bug 162872")
	public void testCase2() throws Exception {
		try {
			// In Jump to Search screen, give From and To for available data and Search. Search should fetch the results.
			// Click Clear button. Data should get cleared along with filters
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "UsageJ2S_162872_1", 1);
			SearchGridHelper.setPagination("50 per page");
			
			String expectedCount = "50";
			String actualCount = SearchGridHelper.getTotalRecordsFetched();
			assertEquals(actualCount, expectedCount);
			int rows = GridHelper.getRowCount("SearchGrid");
			assertEquals(rows, 50);
			
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			rows = GridHelper.getRowCount("SearchGrid");
			assertEquals(rows, 0);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=163, description="Test Case 3 for Bug 162872")
	public void testCase3() throws Exception {
		try {
			// In Jump to Search screen, Search based on Query Filter. Search should fetch the results
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "UsageJ2S_162872_2", 1);
			
			String expectedCount = "50 of 110";
			String actualCount = SearchGridHelper.getTotalRecordsFetched();
			assertEquals(actualCount, expectedCount);
			int rows = GridHelper.getRowCount("SearchGrid");
			assertEquals(rows, 50);
			
			SearchGridHelper.setPagination("100 per page");
			
			expectedCount = "100";
			actualCount = SearchGridHelper.getTotalRecordsFetched();
			assertEquals(actualCount, expectedCount);
			rows = GridHelper.getRowCount("SearchGrid");
			assertEquals(rows, 100);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=164, description="Test Case 4 for Bug 162872")
	public void testCase4() throws Exception {
		try {
			// In Jump to Search screen, Search based on Query Filter. Search should fetch the results.
			// Click Clear button. Data should get cleared along with filters
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "UsageJ2S_162872_2", 1);
			SearchGridHelper.setPagination("50 per page");
			
			String expectedCount = "50";
			String actualCount = SearchGridHelper.getTotalRecordsFetched();
			assertEquals(actualCount, expectedCount);
			int rows = GridHelper.getRowCount("SearchGrid");
			assertEquals(rows, 50);
			
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			rows = GridHelper.getRowCount("SearchGrid");
			assertEquals(rows, 0);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}