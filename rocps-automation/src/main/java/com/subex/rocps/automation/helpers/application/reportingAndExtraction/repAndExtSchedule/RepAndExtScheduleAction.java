package com.subex.rocps.automation.helpers.application.reportingAndExtraction.repAndExtSchedule;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;

public class RepAndExtScheduleAction extends PSAcceptanceTest
{
	protected PSGenericHelper psgenericHelperobj = new PSGenericHelper();

	/* This method is used to click on 'New' action in Report and Extract Scheduler*/
	public void clickNewAction( String clientPartition ) throws Exception
	{
		PSGenericHelper.waitForParentActionElementTOBeclickable( "Common Tasks" );
		psgenericHelperobj.validateActionText( "Common Tasks", "New" );
		psgenericHelperobj.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_reportAndExtSch_Details_xpath" ), searchScreenWaitSec );
	}

	/* This method is used to click on  action in Reports and Extracts Scheduler*/
	public void clickOnAction( String parentActionNm, String childActionNm ) throws Exception
	{
		PSGenericHelper.waitForParentActionElementTOBeclickable( parentActionNm );
		psgenericHelperobj.validateActionText( parentActionNm, childActionNm );
		NavigationHelper.navigateToAction( parentActionNm, childActionNm );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_reportAndExtSch_Details_xpath" ), searchScreenWaitSec );
	}
}
