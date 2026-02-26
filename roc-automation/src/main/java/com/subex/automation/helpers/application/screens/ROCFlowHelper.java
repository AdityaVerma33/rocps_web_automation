package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.EntitySearchHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class ROCFlowHelper extends ROCAcceptanceTest {
	
	public void createROCFlow(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				
				String[] toolbars = testData.getStringValue(excelData.get("Toolbars").get(i), firstLevelDelimiter);
				String[][] screens = testData.getStringValue(excelData.get("Screens").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] referenceTables = testData.getStringValue(excelData.get("Reference Tables").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				createROCFlow(partition, name, toolbars, screens, referenceTables);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createROCFlow(String partition, String name, String[] toolbars, String[][] screens, String[][] referenceTables) throws Exception {
		try {
			NavigationHelper.navigateToScreen("ROC Flows", "ROC Flows Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("ROCFlows_Name", name, "Name");

			if (row > 0) {
				Log4jHelper.logWarning("ROC Flow '" + name + "' is already present.");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "ROCFlows_Name");
				
				updateROCFlow(name, toolbars, screens, referenceTables);
				
				saveROCFlows(name, detailScreenTitle);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateROCFlow(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String name = excelData.get("Name").get(i);
				
				String[] toolbars = testData.getStringValue(excelData.get("Toolbars").get(i), firstLevelDelimiter);
				String[][] screens = testData.getStringValue(excelData.get("Screens").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] referenceTables = testData.getStringValue(excelData.get("Reference Tables").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				updateROCFlow(name, toolbars, screens, referenceTables);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateROCFlow(String name, String[] toolbars, String[][] screens, String[][] referenceTables) throws Exception {
		try {
			TextBoxHelper.type("ROCFlows_Name", name);
			
			addToolbar(toolbars, screens, referenceTables);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void editROCFlow(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String name = excelData.get("Name").get(i);
				
				String[] toolbars = testData.getStringValue(excelData.get("Toolbars").get(i), firstLevelDelimiter);
				String[][] addScreens = testData.getStringValue(excelData.get("Add Screens").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] addReferenceTables = testData.getStringValue(excelData.get("Add Reference Tables").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[] deleteToolbars = testData.getStringValue(excelData.get("Delete Toolbars").get(i), firstLevelDelimiter);
				String[][] deleteToolbarItems = testData.getStringValue(excelData.get("Delete Toolbar Items").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				editROCFlow(name, toolbars, addScreens, addReferenceTables, deleteToolbars, deleteToolbarItems);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void editROCFlow(String name, String[] toolbars, String[][] addScreens, String[][] addReferenceTables,String[] deleteToolbars,
			String[][] deleteToolbarItems) throws Exception {
		try {
			NavigationHelper.navigateToScreen("ROC Flows", "ROC Flows Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("ROCFlows_Name", name, "Name");

			if (row > 0) {
				String detailScreenTitle = NavigationHelper.navigateToEdit("SearchGrid", row, "ROCFlows_Name");
				
				addToolbar(toolbars, addScreens, addReferenceTables);
				
				deleteToolbar(deleteToolbars);
				
				deleteToolbarItems(toolbars, deleteToolbarItems);
				
				saveROCFlows(name, detailScreenTitle);
				Log4jHelper.logWarning("ROC Flow '" + name + "' is updated.");
			}
			else {
				FailureHelper.failTest("ROC Flow '" + name + "' is not found.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void validateROCFlow(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String name = excelData.get("Name").get(i);
				
				String[] toolbars = testData.getStringValue(excelData.get("Toolbars").get(i), firstLevelDelimiter);
				String[][] toolbarItems = testData.getStringValue(excelData.get("Toolbar Items").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] screenTitles = testData.getStringValue(excelData.get("Screen Titles").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				validateROCFlow(name, toolbars, toolbarItems, screenTitles);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void validateROCFlow(String name, String[] toolbars, String[][] toolbarItems, String[][] screenTitles) throws Exception {
		try {
			String flowLocator = or.getProperty("ROCFlowsPanel_Flow").replace("flowName", name);
			if (ButtonHelper.isPresent(flowLocator)) {
				ButtonHelper.click(flowLocator);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
				if (ValidationHelper.isNotEmpty(toolbars)) {
					for (int i = 0; i < toolbars.length; i++) {
						String toolbarLocator = or.getProperty("ROCFlowsPanel_Toolbar").replace("toolbarName", toolbars[i]);
						
						if (ButtonHelper.isPresent(toolbarLocator)) {
							ButtonHelper.click(toolbarLocator);
							GenericHelper.waitForLoadmask(searchScreenWaitSec);
							
							if (ValidationHelper.isNotEmpty(toolbarItems) && ValidationHelper.isNotEmpty(toolbarItems[i])) {
								for (int j = 0; j < toolbarItems.length; j++) {
									String toolbarItemLocator = or.getProperty("ROCFlowsPanel_ToolbarItem").replace("toolbarItemName", toolbarItems[i][j]);
									
									if (ButtonHelper.isPresent(toolbarItemLocator)) {
										ButtonHelper.click(toolbarItemLocator);
										GenericHelper.waitForLoadmask(searchScreenWaitSec);
										
										if (ValidationHelper.isNotEmpty(screenTitles) && ValidationHelper.isNotEmpty(screenTitles[i])
												 && ValidationHelper.isNotEmpty(screenTitles[i][j])) {
											String actualScreenTitle = NavigationHelper.getScreenTitle();
											assertEquals(actualScreenTitle, screenTitles[i][j], 
													"Expected Screen Title '" + screenTitles[i][j] + "'. But found '" + actualScreenTitle + "'. ");
										}
									}
									else {
										FailureHelper.failTest("Toolbar Item '" + toolbarItems[i][j] + "' is not found under Toolbar '" + toolbars[i] + "' under ROC Flow '" + name + "' in top panel.");
									}
								}
							}
						}
						else {
							FailureHelper.failTest("Toolbar '" + toolbars[i] + "' is not found under ROC Flow '" + name + "' in top panel.");
						}
					}
				}
			}
			else {
				FailureHelper.failTest("ROC Flow '" + name + "' is not found in top panel.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void deleteROCFlow(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String name = excelData.get("Name").get(i);
				
				deleteROCFlow(name);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void deleteROCFlow(String name) throws Exception {
		try {
			NavigationHelper.navigateToScreen("ROC Flows", "ROC Flows Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("ROCFlows_Name", name, "Name");

			if (row > 0) {
				NavigationHelper.delete("SearchGrid", name, "Name");
				Log4jHelper.logWarning("ROC Flow '" + name + "' is deleted.");
			}
			else {
				FailureHelper.failTest("ROC Flow '" + name + "' is not found.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addToolbar(String[] toolbars, String[][] screens, String[][] referenceTables) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(toolbars)) {
				for (int i = 0; i < toolbars.length; i++) {
					int rowNum = GridHelper.getRowNumber("ROCFlows_Toolbar_Grid", toolbars[i], "ROC  Flow  Toolbar  Name");
					boolean screensAdded = false;
					
					if (rowNum == 0) {
						ButtonHelper.click("ROCFlows_Toolbar_Add");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						rowNum = GridHelper.getRowCount("ROCFlows_Toolbar_Grid");
						GridHelper.updateGridTextBox("ROCFlows_Toolbar_Grid", "ROCFlows_Toolbar_Name", rowNum, "ROC  Flow  Toolbar  Name", toolbars[i]);
					}
					
					GridHelper.clickRow("ROCFlows_Toolbar_Grid", rowNum, 1);
					
					if (ValidationHelper.isNotEmpty(screens) && screens.length > i && ValidationHelper.isNotEmpty(screens[i])) {
						addScreens(screens[i]);
						screensAdded = true;
					}
					
					if (ValidationHelper.isNotEmpty(referenceTables) && referenceTables.length > i && ValidationHelper.isNotEmpty(referenceTables[i])) {
						addReferenceTables(referenceTables[i]);
						screensAdded = true;
					}
					
					if (screensAdded)
						GridHelper.click("ROCFlows_Toolbar_Grid");
					else
						GridHelper.clickRow("ROCFlows_Toolbar_Grid", 1, 1);
					
//					rowNum = GridHelper.getRowNumber("ROCFlows_Toolbar_Grid", toolbars[i], "ROC  Flow  Toolbar  Name");
//					if (rowNum != 0 && rowNum != (i+1)) {
//						moveToolbar(rowNum, (i+1));
//						
//						if (screenAdded)
//							GridHelper.click("ROCFlows_Toolbar_Grid");
//						else {
//							GridHelper.click("ROCFlows_ToolbarItems_Grid");
//							GridHelper.click("ROCFlows_Toolbar_Grid");
//						}
//					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void moveToolbar(int currentRowNum, int expectedRowNum) throws Exception {
		try {
			if (currentRowNum > expectedRowNum) {
				int totalMoves = currentRowNum - expectedRowNum;
				
				for (int i = 0; i < totalMoves; i++) {
					GridHelper.clickRow("ROCFlows_Toolbar_Grid", currentRowNum, 1);
					ButtonHelper.click("ROCFlows_Toolbar_MoveUp");
					
					GridHelper.click("ROCFlows_Toolbar_Grid");
					currentRowNum--;
				}
			}
			else if (currentRowNum < expectedRowNum) {
				int totalMoves = expectedRowNum - currentRowNum;
				
				for (int i = 0; i < totalMoves; i++) {
					GridHelper.clickRow("ROCFlows_Toolbar_Grid", currentRowNum, 1);
					ButtonHelper.click("ROCFlows_Toolbar_MoveDown");
					
					GridHelper.click("ROCFlows_Toolbar_Grid");
					currentRowNum++;
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void deleteToolbar(String[] toolbars) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(toolbars)) {
				for (int i = 0; i < toolbars.length; i++) {
					int rowNum = GridHelper.getRowNumber("ROCFlows_Toolbar_Grid", toolbars[i], "ROC  Flow  Toolbar  Name");
					
					if (rowNum > 0) {
						GridHelper.clickRow("ROCFlows_Toolbar_Grid", rowNum, 1);
						ButtonHelper.click("ROCFlows_Toolbar_Delete");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void deleteToolbarItems(String[] toolbars, String[][] toolbarItems) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(toolbars)) {
				for (int i = 0; i < toolbars.length; i++) {
					int row = GridHelper.getRowNumber("ROCFlows_Toolbar_Grid", toolbars[i], "ROC  Flow  Toolbar  Name");
					
					if (row > 0) {
						GridHelper.clickRow("ROCFlows_Toolbar_Grid", row, 1);
						
						if (ValidationHelper.isNotEmpty(toolbarItems) && ValidationHelper.isNotEmpty(toolbarItems[i])) {
							for (int j = 0; j < toolbarItems[i].length; j++) {
								int rowNum = GridHelper.getRowNumber("ROCFlows_ToolbarItems_Grid", toolbarItems[i][j], "ROC  Flow  Toolbar  Item  Name");
								
								if (rowNum > 0) {
									GridHelper.clickRow("ROCFlows_ToolbarItems_Grid", rowNum, 1);
									ButtonHelper.click("ROCFlows_ToolbarItems_Delete");
									GenericHelper.waitForLoadmask(detailScreenWaitSec);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addScreens(String[] screens) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(screens)) {
				for (int i = 0; i < screens.length; i++) {
					String screenName = screens[i] + "s";
					if (screens[i].endsWith("y"))
						screenName = screens[i].substring(0, screens[i].length()-1) + "ies";
					else if (screens[i].equals("Task"))
						screenName = "Task Search";
					
					int rowNum = GridHelper.getRowNumber("ROCFlows_ToolbarItems_Grid", screens[i], "ROC  Flow  Toolbar  Item  Name");
					
					if (rowNum == 0) {
						rowNum = GridHelper.getRowNumber("ROCFlows_ToolbarItems_Grid", screenName, "ROC  Flow  Toolbar  Item  Name");
						
						if (rowNum == 0) {
							ButtonHelper.click("ROCFlows_ToolbarItems_AddScreens");
							GenericHelper.waitForLoadmask(detailScreenWaitSec);
							
							EntitySearchHelper entitySearch = new EntitySearchHelper();
							entitySearch.selectUsingGridFilterTextBox("Screen Search", "Screens_Name", screens[i], "Screen Name");
						}
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addReferenceTables(String[] referenceTables) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(referenceTables)) {
				for (int i = 0; i < referenceTables.length; i++) {
					int rowNum = GridHelper.getRowNumber("ROCFlows_ToolbarItems_Grid", referenceTables[i], "ROC  Flow  Toolbar  Item  Name");
					
					if (rowNum == 0) {
						ButtonHelper.click("ROCFlows_ToolbarItems_AddReferenceTables");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						Thread.sleep(1000);
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						
						EntitySearchHelper entitySearch = new EntitySearchHelper("window-scroll-panel", "refTableGrid");
						entitySearch.select("Reference Table Search", referenceTables[i], "Reference  Table  Name");
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void moveToolbarItem(int currentRowNum, int expectedRowNum) throws Exception {
		try {
			if (currentRowNum > expectedRowNum) {
				int totalMoves = currentRowNum - expectedRowNum;
				
				for (int i = 0; i < totalMoves; i++) {
					GridHelper.clickRow("ROCFlows_ToolbarItems_Grid", currentRowNum, 1);
					ButtonHelper.click("ROCFlows_ToolbarItems_MoveUp");
					
					GridHelper.click("ROCFlows_ToolbarItems_Grid");
					currentRowNum--;
				}
			}
			else if (currentRowNum < expectedRowNum) {
				int totalMoves = expectedRowNum - currentRowNum;
				
				for (int i = 0; i < totalMoves; i++) {
					GridHelper.clickRow("ROCFlows_ToolbarItems_Grid", currentRowNum, 1);
					ButtonHelper.click("ROCFlows_ToolbarItems_MoveDown");
					
					GridHelper.click("ROCFlows_ToolbarItems_Grid");
					currentRowNum++;
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void saveROCFlows(String name, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "ROC Flow save did not happen");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), "Value '" + name + "' is not found in grid.");
			Log4jHelper.logInfo("ROC Flow '" + name + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}