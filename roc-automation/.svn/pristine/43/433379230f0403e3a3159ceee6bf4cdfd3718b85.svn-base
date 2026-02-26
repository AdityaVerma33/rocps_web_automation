package com.subex.automation.testcases.regressiontesting.duplicatecheck;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.DuplicateXDRHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testSearch extends ROCAcceptanceTest {
	
	static String path = null;
	final String fileName = "DuplicateCheck_TestData.xlsx";
	final String sheetName = "Search";
	
	public testSearch() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Parse with error data file", groups = { "search" })
	public void testLikeSearch() throws Exception
	{
		try {
			// Validate that like search is working fine in Duplicate XDR Check screen
			DuplicateXDRHelper duplicateCheck = new DuplicateXDRHelper();
			duplicateCheck.createDuplicateXDR(path, fileName, sheetName, "DuplicateXDRCheck", 1);
			
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			SearchGridHelper.gridFilterSearchWithTextBox("DuplicateXDR_Name", "%1%", "Name");
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "DuplicateXDRCheck", 1 );
			
			for ( int i = 0; i < excelData.get( "Name" ).size(); i++ ) {
				String name = excelData.get( "Name" ).get(i);
				
				if (name.contains("1"))
					assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"));
				else
					assertFalse(GridHelper.isValuePresent("SearchGrid", name, "Name"));
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Parse with error data file", dependsOnMethods = { "testLikeSearch" }, groups = { "search" })
	public void testExactSearch() throws Exception
	{
		try {
			// Validate that exact search is working fine in Duplicate XDR Check screen
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			SearchGridHelper.gridFilterSearchWithTextBox("DuplicateXDR_Name", "1Duplicate Check", "Name");
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "DuplicateXDRCheck", 1 );
			
			for ( int i = 0; i < excelData.get( "Name" ).size(); i++ ) {
				String name = excelData.get( "Name" ).get(i);
				
				if (name.equals("1Duplicate Check"))
					assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"));
				else
					assertFalse(GridHelper.isValuePresent("SearchGrid", name, "Name"));
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Parse with error data file", dependsOnMethods = { "testExactSearch" }, groups = { "search" })
	public void testStartsWithSearch() throws Exception
	{
		try {
			// Validate that starts with search is working fine in Duplicate XDR Check screen
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			SearchGridHelper.gridFilterSearchWithTextBox("DuplicateXDR_Name", "Duplicate", "Name");
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "DuplicateXDRCheck", 1 );
			
			for ( int i = 0; i < excelData.get( "Name" ).size(); i++ ) {
				String name = excelData.get( "Name" ).get(i);
				
				if (name.startsWith("Duplicate"))
					assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"));
				else
					assertFalse(GridHelper.isValuePresent("SearchGrid", name, "Name"));
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