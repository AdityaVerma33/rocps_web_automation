package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class TeamHelper extends ROCAcceptanceTest {

	public void createTeam(String path, String fileName, String sheetName, String testCaseName, int occurance) throws Exception
	{
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, testCaseName, occurance );
	
			for(int i = 0; i < excelData.get("Name").size(); i++) 
			{
				String partition = excelData.get("Partition").get(i);
				String teamName = excelData.get("Name").get(i);
				String[] users = testData.getStringValue(excelData.get("Users").get(i), firstLevelDelimiter);
				String supervisor = excelData.get("Supervisor").get(i);
				
				createTeam(partition, teamName, users, supervisor);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createTeam(String partition, String teamName, String[] users, String supervisor) throws Exception
	{
		try {
			int row = navigateToTeams(teamName);
			NavigationHelper.navigateToNewOrEdit(row, partition, "Team", "Popup_Panel");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			TextBoxHelper.type("Popup_Panel", "Teams_Name", teamName);
			
			assignUsers(users, supervisor);
			
			saveTeams(teamName, detailScreenTitle);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void assignUsers(String[] users, String supervisor) throws Exception 
	{
		try {
			int rows = GridHelper.getRowCount("Teams_Users_Grid");
			rows++;
			
			for(int i = 0; i < users.length; i++) {
				int row = GridHelper.getRowNumber("Teams_Users_Grid", users[i], "User");
				
				if (row == 0) {
					row = rows;
					ButtonHelper.click("Teams_Users_Add");
					rows++;
					
					GridHelper.updateGridComboBox("Teams_Users_Grid", "Teams_Users_ComboBox", row, "User", "Supervisor", users[i]);
					row = GridHelper.getRowNumber("Teams_Users_Grid", users[i], "User");
				}
				
				if (supervisor.equals(users[i])) {
					boolean isPresent = GridHelper.isImagePresent("Teams_Users_Grid", row, "Supervisor");
					
					if (!isPresent) {
						GridHelper.clickRow("Teams_Users_Grid", row, "Supervisor");
						ButtonHelper.click("Teams_Users_SetAsSupervisor");
						Thread.sleep(500);
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private int navigateToTeams(String teamName) throws Exception {
		try {
			NavigationHelper.navigateToScreen( "Teams", "Team Search" );
			int row = SearchGridHelper.searchWithTextBox("Teams_Name", teamName, "Name");
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void saveTeams(String teamName, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("Teams_Save");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Team save did not happen.");
			
			if (LabelHelper.isTitlePresent("Information"))
				ButtonHelper.click("OKButton");
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", teamName, "Name"), "Value '" + teamName + "' is not found in grid.");
			Log4jHelper.logInfo("Team '" + teamName + "' created");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}