package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class UserLoginHelper extends ROCAcceptanceTest {
	
	public void applyFilter(String path, String fileName, String sheetName, String testCaseName, int occurance) throws Exception
	{
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, testCaseName, occurance );
	
			for(int i = 0; i < excelData.get("Username").size(); i++) 
			{
				String dateTimeType = excelData.get("Datetime Type").get(i);
				String fromDate = excelData.get("From Date").get(i);
				String toDate = excelData.get("To Date").get(i);
				String sourceAddress = excelData.get("Source Address").get(i);
				String sourceHostname = excelData.get("Source Hostname").get(i);
				String userName = excelData.get("Username").get(i);
				String message = excelData.get("Message").get(i);
				String userDisplayName = excelData.get("User Display Name").get(i);
				
				applyFilter(dateTimeType, fromDate, toDate, sourceAddress, sourceHostname, userName, message, userDisplayName);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public int applyFilter(String dateTimeType, String fromDate, String toDate, String sourceAddress, String sourceHostname, String userName, String message,
			String userDisplayName) throws Exception
	{
		try {
			NavigationHelper.navigateToScreen( "User Logins", "User Login Search" );
			ButtonHelper.click("ClearButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			SearchGridHelper.gridFilterSearchWithCalendar("UserLogin_Datetime", dateTimeType, fromDate, toDate, "Datetime");
			SearchGridHelper.gridFilterSearchWithTextBox("UserLogin_SourceAddress", sourceAddress, "Source Address");
			SearchGridHelper.gridFilterSearchWithTextBox("UserLogin_SourceHostname", sourceHostname, "Source Hostname");
			
			SearchGridHelper.gridFilterAdvancedSearch("UserLogin_Username", userName, "User");
			SearchGridHelper.gridFilterSearchWithTextBox("UserLogin_Message", message, "Message");
			SearchGridHelper.gridFilterSearchWithTextBox("UserLogin_UserDisplayName", userDisplayName, "User Display Name");
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			int rows = GridHelper.getRowCount("SearchGrid");
			return rows;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}