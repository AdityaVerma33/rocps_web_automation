package com.subex.rocps.automation.helpers.application.prepayments.prepaidbalance;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.GenericHelper;

public class PrepaidBalanceActionImpl extends PSAcceptanceTest
{

	public void clickBalanceHistoryAction() throws Exception
	{
	NavigationHelper.navigateToAction( "View", "Balance History" );
	GenericHelper.waitForLoadmask();
	//assertEquals( NavigationHelper.getScreenTitle(), "Pre-Payment Balance History Search" );
	}
	
	public void clickPaymentsAction() throws Exception
	{
		NavigationHelper.navigateToAction( "View", "Prepayments" );
		GenericHelper.waitForLoadmask();
	//	assertEquals( NavigationHelper.getScreenTitle(), "Pre-Payments Search" );
	}
	
}
