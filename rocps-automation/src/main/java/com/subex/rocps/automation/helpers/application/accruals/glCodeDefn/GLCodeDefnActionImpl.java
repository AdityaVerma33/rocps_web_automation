package com.subex.rocps.automation.helpers.application.accruals.glCodeDefn;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;

public class GLCodeDefnActionImpl extends PSAcceptanceTest
{

	protected PSGenericHelper psgenericHelperobj = new PSGenericHelper();

	/* This method is used to click on 'New' action in GL Code Definition*/
	public void clickNewAction( String clientPartition ) throws Exception
	{
		PSGenericHelper.waitForParentActionElementTOBeclickable( "Common Tasks" );
		psgenericHelperobj.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_glCodeDefn_Details_xpath" ), searchScreenWaitSec );
	}

	/* This method is used to click on  action in  GL Code Definition*/
	public void clickOnAction( String parentActionNm, String childActionNm ) throws Exception
	{
		PSGenericHelper.waitForParentActionElementTOBeclickable( parentActionNm );
		psgenericHelperobj.validateActionText( parentActionNm, childActionNm );
		NavigationHelper.navigateToAction( parentActionNm, childActionNm );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_glCodeDefn_Details_xpath" ), searchScreenWaitSec );
	}

}
