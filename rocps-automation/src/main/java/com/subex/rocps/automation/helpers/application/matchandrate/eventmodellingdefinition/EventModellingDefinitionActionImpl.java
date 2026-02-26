package com.subex.rocps.automation.helpers.application.matchandrate.eventmodellingdefinition;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class EventModellingDefinitionActionImpl extends PSAcceptanceTest
{
	protected PSGenericHelper psgenericHelperobj = new PSGenericHelper();

	//This method is used to click on new action in Event modelling defn
	public void clickNewAction( String clientPartition, String currscreentitle ) throws Exception
	{
		psgenericHelperobj.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "New " + currscreentitle );
	}

	/* This method is used to click on Configure Search Grid action
	 *  in Event Modelling defn
	 */

	public void clickConfgSearchGridAction( String clientPartition, String currscreentitle ) throws Exception
	{
		NavigationHelper.navigateToAction( "Map Event Tables", "Configure Search Grids" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), currscreentitle );
	}

}
