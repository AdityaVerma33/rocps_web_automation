package com.subex.rocps.automation.helpers.application.matchandrate.eventextraratingfield;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;

public class EventExtraFieldActionImpl extends PSAcceptanceTest
{

protected PSGenericHelper genHelperObj = new PSGenericHelper();
	
	/*
	 * This method is to click new action
	 */
	public void clickNewAction(String clientPartition) throws Exception {
		genHelperObj.clickNewAction(clientPartition);
		GenericHelper.waitForLoadmask();
	}	
	

	/*
	 * This method is to  delete event extra rating field
	 */
	public void clickDeleteAction(String name) throws Exception {
		genHelperObj.clickDeleteOrUnDeleteAction(name, "Name", "Delete");
		GenericHelper.waitForLoadmask();

	}
	
	/*
	 * This method is to undelete event extra rating field
	 */
	public void clickUnDeleteACtion(String name) throws Exception
	{
		genHelperObj.clickDeleteOrUnDeleteAction(name, "Name", "Undelete");
		GenericHelper.waitForLoadmask();
	}
}
