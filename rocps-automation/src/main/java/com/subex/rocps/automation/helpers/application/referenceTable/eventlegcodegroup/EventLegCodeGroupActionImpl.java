package com.subex.rocps.automation.helpers.application.referenceTable.eventlegcodegroup;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;

public class EventLegCodeGroupActionImpl extends PSAcceptanceTest
{
protected PSGenericHelper genHelperObj = new PSGenericHelper();
	
	/*
	 * This method is to click new action
	 */
	public void clickNewAction(String clientPartition) throws Exception {
		genHelperObj.clickNewAction(clientPartition);
		GenericHelper.waitForLoadmask();
	}	
	

	public void clickEditAction() throws Exception
	{
		NavigationHelper.navigateToAction( "Common Tasks", "Edit" );
		GenericHelper.waitForLoadmask();
	}
	/*
	 * This method is to  delete event Leg code group
	 */
	public void clickDeleteAction(String name) throws Exception {
		genHelperObj.clickDeleteOrUnDeleteAction(name, "Name", "Delete");
		GenericHelper.waitForLoadmask();

	}
	
	/*
	 * This method is to undelete event Leg code group
	 */
	public void clickUnDeleteACtion(String name) throws Exception
	{
		genHelperObj.clickDeleteOrUnDeleteAction(name, "Name", "Undelete");
		GenericHelper.waitForLoadmask();
	}
}
