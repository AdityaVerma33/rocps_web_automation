package com.subex.rocps.automation.helpers.application.bills.reraterequest;

import java.util.ArrayList;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.componentHelpers.SearchHelper;

public class RerateRequestSearchImpl extends PSAcceptanceTest
{
	public PSGenericHelper genericHelperObj = new PSGenericHelper();
	public long timeLimit;
	public String timeStampString;

	public boolean isRerateRequestPresnet(String name, String billProfile, String trafficPeriodFrom) throws Exception
	{
		SearchGridHelper.gridFilterSearchWithTextBox( "prrqName", "RerateRateOnly", "Name" );
		ButtonHelper.click( "//*[text()='Advanced Search']" );
		SearchGridHelper.gridFilterSearchWithTextBox( "popupWindow","pbipName", billProfile, "Bill Profile Name" );
		calender( trafficPeriodFrom );
		return GridHelper.isValuePresent( "SearchGrid", name, "Name" );
		
	}
	
	public void calender(String trafficPeriodFrom) throws Exception {
		SearchHelper searchHelper = new SearchHelper();
		searchHelper.clickFilterIcon("SearchGrid", "From");
		CalendarHelper.setOnDate( "ptblCreateDttm", trafficPeriodFrom );
		ButtonHelper.click("SearchButton");
				
	}
	
	
	public void checkTaskStatus() throws Exception {
		//assertEquals(NavigationHelper.getScreenTitle(), "Invoice Reconciliat...");
		sortDescendingScheduledDate();
		waitForTaskCompletion("SearchGrid");
	}
	
	
	
	public void waitForTaskCompletion(String gridId) throws Exception {
		ArrayList<String> acceptedStatus = new ArrayList<String>();
		changeToMilliSeconds();
		acceptedStatus.add("Completed");
		acceptedStatus.add("Failed");
		
		long startTime = System.currentTimeMillis();
		String actualValue = GridHelper.getCellValue(gridId, 1, "Status").trim();
		while ((!actualValue.equals(acceptedStatus.get(0)) && !actualValue.equals(acceptedStatus.get(1)))
				&& (System.currentTimeMillis() - startTime) < timeLimit) {
			ButtonHelper.click("SearchButton");
			actualValue = GridHelper.getCellValue(gridId, 1, "Status").trim();
		}
		GenericHelper.waitForLoadmask();
		assertTrue(acceptedStatus.contains(actualValue), "RErate task is not completed");
		
	}
	
	/*
	 * This Method is used to set a time limit for the checking task Status.
	 */

	public void changeToMilliSeconds() throws Exception {
		long seconds = Long.parseLong(configProp.getProperty("SearchLoopTimeLimitInSecs"));
		timeLimit = seconds * 1000;
	}
	
	/*
	 * This Method is used to sort the column in descending order
	 */

	public void sortDescendingScheduledDate() throws Exception {
		GridHelper.sortGrid( "SearchGrid", "Created Date" );
		GridHelper.sortGrid( "SearchGrid", "Created Date" );
	}
}
