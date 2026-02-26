package com.subex.rocps.automation.helpers.application.carrierinvoice.invoicereconciliationrequest;

import java.util.ArrayList;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class InvoiceReconciliationRequestSearchImpl extends PSAcceptanceTest
{
	public PSGenericHelper genericHelperObj = new PSGenericHelper();
	public long timeLimit;
	public String timeStampString;
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
		assertTrue(acceptedStatus.contains(actualValue));
		
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
