package com.subex.rocps.automation.helpers.application.reaggregation.reaggregationrequest;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;

public class ReAggregationRequestActionImpl extends PSAcceptanceTest
{
	protected PSGenericHelper genHelperObj = new PSGenericHelper();

	/*
	 * This method is to click new action
	 */
	public void clickNewAction( String clientPartition ) throws Exception
	{
		genHelperObj.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask();
	}
	
	public void clickEditAction( ) throws Exception
	{
		NavigationHelper.navigateToAction( "Common Tasks", "Edit" );
		GenericHelper.waitForLoadmask();
	}
	
	public void addBillProfile() throws Exception
	{
		ButtonHelper.click( "PS_Detail_reaggregation_billProfile_addBtnID" );
		GenericHelper.waitForLoadmask();
		//assertEquals( NavigationHelper.getScreenTitle(), "Bill Profile Search" );
	}
	
	public void addBillPeriod() throws Exception
	{
		ButtonHelper.click( "PS_Detail_reaggregation_billPeriod_addBtnID" );
		GenericHelper.waitForLoadmask();
		//assertEquals( NavigationHelper.getScreenTitle(), "Bill Period Search" );
	}
	

	public void scheduleReAggregation( ) throws Exception
	{
		PSGenericHelper.waitForParentActionElementTOBeclickable("Actions");
		NavigationHelper.navigateToAction( "Actions", "Schedule Request" );
		GenericHelper.waitForLoadmask();
	}
}
