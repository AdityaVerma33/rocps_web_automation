package com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoiceimportsearch;

import java.util.ArrayList;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class CarrierInvoiceSearchImpl extends PSAcceptanceTest
{
	public PSGenericHelper genericHelperObj = new PSGenericHelper();
	public long timeLimit;
	public String timeStampString;
	public boolean isCarrierInvoiceRequestPresent(String fileName, String tempName, String invoicePeriod) throws Exception
	{
		SearchGridHelper.gridFilterSearchWithTextBox( "fileTbl$filFilename", fileName, "File Name" );
		SearchGridHelper.gridFilterAdvancedSearch( "invoiceTemplate", tempName, "Template" );
		CalendarHelper.setOnDate( "PS_SearchFilter_BillAction_date_calenderID", invoicePeriod );
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		boolean flag = GridHelper.isValuePresent( "SearchGrid", tempName, "Template" );
		return flag;
	}
	
	public void checkTaskStatus() throws Exception {
		//assertEquals(NavigationHelper.getScreenTitle(), "Carrier Invoice Imp...");
		sortDescendingScheduledDate();
		waitForTaskCompletion("SearchGrid");
	}
	
	
	
	public void waitForTaskCompletion(String gridId) throws Exception {
		ArrayList<String> acceptedStatus = new ArrayList<String>();
		changeToMilliSeconds();
		acceptedStatus.add("Failed");
		acceptedStatus.add("Success");
		acceptedStatus.add("Mapping Missing");
		acceptedStatus.add("Duplicate");
		long startTime = System.currentTimeMillis();
		String actualValue = GridHelper.getCellValue(gridId, 1, "Status").trim();
		while ((!actualValue.equals(acceptedStatus.get(0)) && !actualValue.equals(acceptedStatus.get(1)))
				&& (System.currentTimeMillis() - startTime) < timeLimit) {
			ButtonHelper.click("SearchButton");
			actualValue = GridHelper.getCellValue(gridId, 1, "Status").trim();
		}
		GenericHelper.waitForLoadmask();
		assertTrue(acceptedStatus.contains(actualValue));
		if(actualValue.contains( "Duplicate" ))
			FailureHelper.failTest( "Carrier Invoice Import request failed with Duplicate File error" );
		if(actualValue.contains( "Mapping Missing" ))
			FailureHelper.failTest( "Carrier Invoice Import request failed with Mapping Missing File error" );
		if(actualValue.contains( "Failed" ))
			FailureHelper.failTest( "Carrier Invoice Import request failed with some errors" );
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
		GridHelper.sortGrid( "SearchGrid", "Imported Date" );
		GridHelper.sortGrid( "SearchGrid", "Imported Date" );
	}
}
