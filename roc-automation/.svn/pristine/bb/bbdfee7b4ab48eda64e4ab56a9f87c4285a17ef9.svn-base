package com.subex.automation.testcases.regressiontesting.masking;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.MaskingConfigurationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class testSearch extends testMasking {
	
	final String sheetName = "Search";
	
	public testSearch() throws Exception {
		super();
	}
	
	@Test(priority=3, description="Search filter's starts with search")
	public void testStartsWithSearch() throws Exception
	{
		try {
			// Search based on all filters should be 'starts with search' by default
			MaskingConfigurationHelper masking = new MaskingConfigurationHelper();
			masking.createMaskingConfiguration(path, fileName, sheetName, "Masking", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Masking", 1 );
			ArrayList<String> name = excelData.get("Name");
			ArrayList<String> tableDefinition = excelData.get("Table Definition");
			
			SearchGridHelper.gridFilterSearchWithTextBox("Masking_Name", "audit", "Name");
//			masking.searchByName("audit");
			for(int i = 0; i < name.size(); i++) {
				assertTrue(GridHelper.isValuePresent("SearchGrid", name.get(i), "Name"));
			}
			
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			int rows = GridHelper.getRowCount("SearchGrid");
			assertTrue(rows == 0);
			
			SearchGridHelper.gridFilterSearchWithTextBox("Masking_TableDefinition_Filter", "audit", "Table Name");
//			masking.searchByTableDefinition("audit");
			for(int i = 0; i < tableDefinition.size(); i++) {
				assertTrue(GridHelper.isValuePresent("SearchGrid", tableDefinition.get(i), "Table Name"));
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Search filter's like search")
	public void testLikeSearch() throws Exception
	{
		try {
			// Like Search should work for all filters
//			MaskingConfigurationHelper masking = new MaskingConfigurationHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Masking", 1 );
			ArrayList<String> name = excelData.get("Name");
			ArrayList<String> tableDefinition = excelData.get("Table Definition");
			
			SearchGridHelper.gridFilterSearchWithTextBox("Masking_Name", "%trial%", "Name");
//			masking.searchByName("%trial%");
			for(int i = 0; i < name.size(); i++) {
				String eName = name.get(i);
				if (eName.contains("trial"))
					assertTrue(GridHelper.isValuePresent("SearchGrid", eName, "Name"));
				else
					assertFalse(GridHelper.isValuePresent("SearchGrid", eName, "Name"));
			}
			
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			SearchGridHelper.gridFilterSearchWithTextBox("Masking_TableDefinition_Filter", "%trial%", "Table Name");
//			masking.searchByTableDefinition("%trial%");
			for(int i = 0; i < tableDefinition.size(); i++) {
				String eName = tableDefinition.get(i);
				if (eName.contains("trial"))
					assertTrue(GridHelper.isValuePresent("SearchGrid", eName, "Table Name"));
				else
					assertFalse(GridHelper.isValuePresent("SearchGrid", eName, "Table Name"));
			}
			
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			SearchGridHelper.gridFilterSearchWithTextBox("Masking_Name", "%masking%", "Name");
//			masking.searchByName("%masking%");
			for(int i = 0; i < name.size(); i++) {
				String eName = name.get(i);
				if (eName.contains("masking") || eName.contains("Masking"))
					assertTrue(GridHelper.isValuePresent("SearchGrid", eName, "Name"));
				else
					assertFalse(GridHelper.isValuePresent("SearchGrid", eName, "Name"));
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}