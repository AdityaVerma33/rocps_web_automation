package com.subex.rocps.automation.helpers.application.genericHelpers;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;

public class PSActionImpl extends PSAcceptanceTest
{

	protected PSGenericHelper psgenericHelperobj = new PSGenericHelper();

	/* This method is used to click on 'New' action in */
	public void clickNewAction( String clientPartition, String waitForDetailPageXpath ) throws Exception
	{
		PSGenericHelper.waitForParentActionElementTOBeclickable( "Common Tasks" );
		psgenericHelperobj.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( waitForDetailPageXpath ), searchScreenWaitSec );

	}

	/* This method is used to click on  action with wait for detail page xpath '*/
	public void clickOnAction( String parentActionNm, String childActionNm, String waitForDetailPageXpath ) throws Exception
	{
		PSGenericHelper.waitForParentActionElementTOBeclickable( parentActionNm );
		psgenericHelperobj.validateActionText( parentActionNm, childActionNm );
		NavigationHelper.navigateToAction( parentActionNm, childActionNm );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( waitForDetailPageXpath ), searchScreenWaitSec );
	}

	/* This method is used to click on  action '*/
	public void clickOnAction( String parentActionNm, String childActionNm ) throws Exception
	{
		PSGenericHelper.waitForParentActionElementTOBeclickable( parentActionNm );
		psgenericHelperobj.validateActionText( parentActionNm, childActionNm );
		NavigationHelper.navigateToAction( parentActionNm, childActionNm );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}
	
	/* This method is used to click on  action '*/
	public void clickOnActionWithPartition( String parentActionNm, String childActionNm, String clientPartition ) throws Exception
	{
		PSGenericHelper.waitForParentActionElementTOBeclickable( parentActionNm );
		psgenericHelperobj.validateActionText( parentActionNm, childActionNm );
		NavigationHelper.navigateToAction( parentActionNm, childActionNm );
		if ( configProp.getProperty( "clientPartitionFlag" ) != null && Boolean.valueOf( configProp.getProperty( "clientPartitionFlag" ) ) && !clientPartition.isEmpty() )
			NavigationHelper.selectPartition( clientPartition );
		else if ( configProp.getProperty( "clientPartitionFlag" ) != null && Boolean.valueOf( configProp.getProperty( "clientPartitionFlag" ) ) && !configProp.getProperty( "partition" ).isEmpty() )
			NavigationHelper.selectPartition( configProp.getProperty( "partition" ) );
		
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}
	
	/* This method is used to click on  action '*/
	public void clickOnActionWithPartition( String parentText, String childText, String subChildText, String clientPartition ) throws Exception
	{
		NavigationHelper.navigateToAction(parentText,childText,subChildText);
		if ( configProp.getProperty( "clientPartitionFlag" ) != null && Boolean.valueOf( configProp.getProperty( "clientPartitionFlag" ) ) && !clientPartition.isEmpty() )
			NavigationHelper.selectPartition( clientPartition );
		else if ( configProp.getProperty( "clientPartitionFlag" ) != null && Boolean.valueOf( configProp.getProperty( "clientPartitionFlag" ) ) && !configProp.getProperty( "partition" ).isEmpty() )
			NavigationHelper.selectPartition( configProp.getProperty( "partition" ) );
		
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}
}
