package com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoice;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.GenericHelper;

public class CarrierInvoiceActionImpl extends PSAcceptanceTest
{
	
	public void clickAuthorizeAction() throws Exception
	{
		NavigationHelper.navigateToAction( "Change Status", "Authorize" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	public void clickJumpToAction() throws Exception
	{
		NavigationHelper.navigateToAction( "Jump to", "Disputes" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "Dispute Search" );
	}
	
	public void clickRejectAction() throws Exception
	{
		NavigationHelper.navigateToAction( "Change Status", "Reject" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}
	
	public void viewCIHistoryAction() throws Exception
	{
		NavigationHelper.navigateToAction( "View", "CI History" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "Carrier Invoice History" );
	}
}
