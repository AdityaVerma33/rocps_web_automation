package com.subex.rocps.automation.helpers.application.partnerConfiguration.eventUsgRequest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class EventUsgReqActionImpl extends PSAcceptanceTest
{
	protected PSGenericHelper psgenericHelperobj = new PSGenericHelper();
	PSActionImpl psActionImpl = new PSActionImpl();

	// This method is used to click on 'New' action in Event Usage Request
	public void clickNewAction( String clientPartition, String currscreentitle ) throws Exception
	{
		psgenericHelperobj.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "New " + currscreentitle );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	// Method: This method is used to click on 'Save As' action in Event Usage
	// Request
	public void clickSaveAsAction( String clientPartition, String currscreentitle, String currdDescription ) throws Exception
	{

		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		NavigationHelper.navigateToAction( "Common Tasks", "Save As" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "New " + currscreentitle );
		assertEquals( TextAreaHelper.getValue( "PS_Detail_eventUsgReq_description_textID" ), currdDescription );
	}

	// Method: This method is used to click on action
	public void clickOnAction( String parentActionName, String childActionNm ) throws Exception
	{
		psActionImpl.clickOnAction( parentActionName, childActionNm );
	}
}
