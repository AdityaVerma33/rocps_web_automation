package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntitySearchHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class CacheStrategyHelper extends ROCAcceptanceTest {
	
	public void editCacheStrategy(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Cache Key").size(); i++)
			{
				String objectClass = excelData.get("Object Class").get(i);
				String cacheClass = excelData.get("Cache Class").get(i);
				String strategy = excelData.get("Strategy").get(i);
				String cacheKey = excelData.get("Cache Key").get(i);
				String newStrategy = excelData.get("New Strategy").get(i);
				String timeOut = excelData.get("Timeout").get(i);
				
				String [] tableInst = testData.getStringValue(excelData.get("Table Instance").get(i), firstLevelDelimiter);
				String [][] joinDetails = testData.getStringValue(excelData.get("Join Details").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String [] expressionType = testData.getStringValue(excelData.get("Expression Type").get(i), firstLevelDelimiter);
				String [] filterName = testData.getStringValue(excelData.get("Filter Name").get(i), firstLevelDelimiter);
				String [] expClause = testData.getStringValue(excelData.get("Expression Clause").get(i), firstLevelDelimiter);
				String [] expLeftIndent = testData.getStringValue(excelData.get("Expression Left Indent").get(i), firstLevelDelimiter);
				String [] expExpression1 = testData.getStringValue(excelData.get("Expression Expression1").get(i), firstLevelDelimiter);
				String [] expOperator = testData.getStringValue(excelData.get("Expression Operator").get(i), firstLevelDelimiter);
				String [] expExpression2 = testData.getStringValue(excelData.get("Expression Expression2").get(i), firstLevelDelimiter);
				String [] expRightIndent = testData.getStringValue(excelData.get("Expression Right Indent").get(i), firstLevelDelimiter);
				
				editCacheStrategy(objectClass, cacheClass, strategy, cacheKey, newStrategy, timeOut, tableInst, joinDetails, expressionType,
						filterName, expClause, expLeftIndent, expExpression1, expOperator, expExpression2, expRightIndent);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void editCacheStrategy(String objectClass, String cacheClass, String strategy, String cacheKey, String newStrategy, String timeOut,
			String[] tableInst, String[][] joinDetails, String[] expressionType, String[] filterName, String[] expClause, String[] expLeftIndent,
			String[] expExpression1, String[] expOperator, String[] expExpression2, String[] expRightIndent) throws Exception {
		try {
			int row = navigateToCacheStrategy(objectClass, cacheClass, newStrategy, cacheKey);
			
			if (row == 0)
				FailureHelper.failTest("Expected Cache Strategy '" + cacheKey + "' is not found in Cache Strategies screen.");
			else {
				String detailScreenTitle = NavigationHelper.navigateToEdit("SearchGrid", row, "CacheStrategy_Panel");
				
				ComboBoxHelper.select("CacheStrategy_Panel", "CacheStrategy_Strategy", newStrategy);
				TextBoxHelper.type("CacheStrategy_Panel", "CacheStrategy_Timeout", timeOut);
				
				if ((ValidationHelper.isNotEmpty(newStrategy) && newStrategy.contains("Prefetch")) || (ValidationHelper.isEmpty(newStrategy) && strategy.contains("Prefetch"))) {
					if (ValidationHelper.isNotEmpty(tableInst)) {
						ButtonHelper.click("CacheStrategy_QueryBuilder");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						assertTrue(LabelHelper.isTitlePresent("Query Builder"));
						
						if (ValidationHelper.isNotEmpty(tableInst)) {
							addTableInstance(tableInst);
							
							if (ValidationHelper.isNotEmpty(joinDetails))
								updateJoin(joinDetails);
						}
						
						if (ValidationHelper.isNotEmpty(expressionType)) {
							TabHelper.gotoTab("CacheStrategy_QueryBuilder_Tab", "Filters");
							QueryFilterHelper queryFilter = new QueryFilterHelper();
							queryFilter.addCondition(expressionType, filterName, expClause, expLeftIndent, expExpression1, expOperator, expExpression2, expRightIndent);
						}
						
						ButtonHelper.click("CacheStrategy_QueryBuilder_Save");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
					}
				}
				
				saveCacheStrategy(cacheKey, detailScreenTitle);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateStrategy(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Cache Key").size(); i++)
			{
				String objectClass = excelData.get("Object Class").get(i);
				String cacheClass = excelData.get("Cache Class").get(i);
				String strategy = excelData.get("Strategy").get(i);
				String cacheKey = excelData.get("Cache Key").get(i);
				String newStrategy = excelData.get("New Strategy").get(i);
				
				updateStrategy(objectClass, cacheClass, strategy, cacheKey, newStrategy);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateStrategy(String objectClass, String cacheClass, String strategy, String cacheKey, String newStrategy) throws Exception {
		try {
			int row = navigateToCacheStrategy(objectClass, cacheClass, strategy, cacheKey);
			
			if (row == 0)
				FailureHelper.failTest("Expected Cache Strategy '" + cacheKey + "' is not found in Cache Strategies screen.");
			else {
				GridHelper.clickRow("SearchGrid", row, 1);
				NavigationHelper.navigateToAction("Update Strategy");
				
				if (NavigationHelper.isActionPresent(newStrategy)) {
					NavigationHelper.navigateToAction(newStrategy);
					
					ButtonHelper.click("YesButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					
					if (ButtonHelper.isPresent("YesButton")) {
						ButtonHelper.click("YesButton");
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
					}
					
					ButtonHelper.click("SearchButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					assertTrue(GridHelper.isValuePresent("SearchGrid", row, newStrategy, "Strategy"));
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
	
	public int navigateToCacheStrategy(String objectClass, String cacheClass, String strategy, String cacheKey) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Cache Strategies", "Cache Strategy Search");
			if (ButtonHelper.isPresent("YesButton")) {
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
			SearchGridHelper.gridFilterSearchWithTextBox("CacheStrategy_ObjectClass", objectClass, "Object Class");
			SearchGridHelper.gridFilterSearchWithTextBox("CacheStrategy_CacheClass", cacheClass, "Cache Class");
			SearchGridHelper.gridFilterSearchWithComboBox("CacheStrategy_Strategy", strategy, "Strategy");
			
			int row = GridHelper.getRowNumber("SearchGrid", cacheKey, "Cache Key");
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addTableInstance(String[] tableInst) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(tableInst)) {
				for (int i = 0; i < tableInst.length; i++ ){
					boolean isPresent = GridHelper.isValuePresent("CacheStrategy_QueryBuilder_Sources_Grid", tableInst[i], "Table Name");
					
					if (!isPresent) {
						ButtonHelper.click("CacheStrategy_QueryBuilder_AddTI");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						
						EntitySearchHelper entitySearch = new EntitySearchHelper();
						entitySearch.selectUsingGridFilterTextBox("Table Instance Search", "TableInst_DisplayName", tableInst[i], "Display Name");
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateJoin(String[][] joinDetails) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(joinDetails)) {
				String gridId = "CacheStrategy_QueryBuilder_Sources_Grid";
				for (int i = 0; i < joinDetails.length; i++){
					if (ValidationHelper.isNotEmpty(joinDetails[i])) {
						int rowNum = i + 1;
						GridHelper.updateGridComboBox(gridId, "CacheStrategy_QueryBuilder_JoinType", rowNum, "Join Type", "Table Name", joinDetails[i][0]);
						
						GridHelper.updateGridTextBox(gridId, "CacheStrategy_QueryBuilder_TableAlias", rowNum, "Table Alias", "Table Name", joinDetails[i][1]);
						if (rowNum > 1) {
							GridHelper.clickRow(gridId, rowNum, "Column 1");
							if (!ComboBoxHelper.isPresent("CacheStrategy_QueryBuilder_Column1"))
								GridHelper.clickRow(gridId, rowNum, "Column 1");
							GridHelper.clickRow(gridId, 1, "Table Name");
							GridHelper.clickRow(gridId, 1, "Table Name");
						}
						
						GridHelper.updateGridTextBox(gridId, "CacheStrategy_QueryBuilder_EditColumn1", rowNum, "Edit Column 1", "Table Name", joinDetails[i][3]);
						GridHelper.updateGridComboBox(gridId, "CacheStrategy_QueryBuilder_Column1", rowNum, "Column 1", "Table Name", joinDetails[i][2]);
						
						if (rowNum > 1) {
							GridHelper.clickRow(gridId, rowNum, "Column 2");
							if (!ComboBoxHelper.isPresent("CacheStrategy_QueryBuilder_Column2"))
								GridHelper.clickRow(gridId, rowNum, "Column 2");
							GridHelper.clickRow(gridId, 1, "Table Name");
							GridHelper.clickRow(gridId, 1, "Table Name");
						}
						
						GridHelper.updateGridTextBox(gridId, "CacheStrategy_QueryBuilder_EditColumn2", rowNum, "Edit Column 2", "Table Name", joinDetails[i][5]);
						GridHelper.updateGridComboBox(gridId, "CacheStrategy_QueryBuilder_Column2", rowNum, "Column 2", "Table Name", joinDetails[i][4]);
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void saveCacheStrategy(String cacheKey, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle));
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", cacheKey, "Cache Key"));
			Log4jHelper.logInfo("Cache Strategy '" + cacheKey + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}