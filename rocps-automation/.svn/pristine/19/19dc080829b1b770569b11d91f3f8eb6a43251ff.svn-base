package com.subex.rocps.automation.helpers.application.settlements;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;

public class SettlementsActionImpl extends PSAcceptanceTest
{

	protected PSGenericHelper psgenericHelperobj = new PSGenericHelper();

	/* This method is used to click on 'New' action in Settlements*/
	public void clickNewAction( String clientPartition, String currscreentitle ) throws Exception
	{
		psgenericHelperobj.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		String titleXpath = GenericHelper.getORProperty( "ps_Detail_newScreen_FormtitleXpath" );
		titleXpath = titleXpath.replace( "screenName", currscreentitle );
		ElementHelper.waitForElement( titleXpath, searchScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "New " + currscreentitle );
	}

	/* This method is used to click on 'Change Status' action in Settlements*/
	public void changeStatusAction( String statusName ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		NavigationHelper.navigateToAction( "Change Status", statusName );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	/* This method is used to click on 'Jump to' action in Settlements*/
	public void jumpToSettHistoryAction() throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		NavigationHelper.navigateToAction( "Jump to", "Settlement History" );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	/* This method is used to click on 'Settlements Action' action in Settlements*/
	public void settlementsAction( String settlementsChildAction ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		NavigationHelper.navigateToAction( "Settlements Action", settlementsChildAction );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

}
