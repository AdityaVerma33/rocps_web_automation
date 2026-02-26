package com.subex.rocps.automation.helpers.application.matchandrate.eventmodellinginstance;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class EventModInstActionImpl extends PSAcceptanceTest
{

	protected PSGenericHelper psgenericHelperobj = new PSGenericHelper();
	PSActionImpl psActionImpl = new PSActionImpl();

	//This method is used to click on new action in Event modelling Instance
	public void clickNewAction( String clientPartition, String currscreentitle ) throws Exception
	{
		psgenericHelperobj.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "New " + currscreentitle );
	}
	//This method is used to click on  action in Event modelling Instance
	public void clickOnAction( String parentAction, String childAction, String waitForPageLoadXpath ) throws Exception
	{
		psActionImpl.clickOnAction( parentAction, childAction,waitForPageLoadXpath );
	}
}
