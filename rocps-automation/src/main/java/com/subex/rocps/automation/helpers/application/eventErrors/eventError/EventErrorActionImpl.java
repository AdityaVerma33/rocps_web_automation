package com.subex.rocps.automation.helpers.application.eventErrors.eventError;

import org.openqa.selenium.By;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class EventErrorActionImpl extends PSAcceptanceTest
{
	PSGenericHelper psGenericHelper=new PSGenericHelper();

	// Method: reprocess suspense error action
	public void clickReprocessSuspErrAction( String currscreentitle ) throws Exception
	{
		GenericHelper.waitForLoadmask();
		NavigationHelper.navigateToAction( "Error Actions", "Reprocess Suspense Errors" );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		//assertEquals( NavigationHelper.getScreenTitle(), currscreentitle );
	}

	// Method: Reprocess suspense error in diagnostic mode action
	public void clickReprocessSuspErrInDiagMdAction( String currscreentitle ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		NavigationHelper.navigateToAction( "Error Actions", "Reprocess Suspense Error In Diagnostic Mode" );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		String foundTitle = NavigationHelper.getScreenTitle();
		String foundText = driver.findElement( By.id( GenericHelper.getORProperty( "PS_Detail_EveErr_expecInfo_Msz" ) ) ).getText();
	//	assertEquals( foundTitle, currscreentitle, foundTitle + " with the given message found " + foundText );
	}

	// Method: Change the status action
	public void changeStatus( String currscreentitle, String statusChange ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		NavigationHelper.navigateToAction( "Change Status To", "Change Status" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		
		ElementHelper.waitForElement( GenericHelper.getORProperty( "ps_Detail_entityPopupS_Xpath" ), searchScreenWaitSec );
		//assertEquals( NavigationHelper.getScreenTitle(), currscreentitle );
		GridHelper.clickRow( "popupWindow", "searchGrid", statusChange, "Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "popupWindow", "OKButton" );
		ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "ps_Detail_entityPopupS_Xpath" ), searchScreenWaitSec );

	}

	// Method: Assign to action
	public void assignToAction( String currscreentitle, String assignTo ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		NavigationHelper.navigateToAction( "Error Actions", "Assign To" );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "ps_Detail_entityPopupS_Xpath" ), searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		//assertEquals( NavigationHelper.getScreenTitle(), currscreentitle );
		GridHelper.clickRow( "popupWindow", "searchGrid", assignTo, "Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "popupWindow", "OKButton" );
		ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "ps_Detail_entityPopupS_Xpath" ), searchScreenWaitSec );

	}
	
	// Method: reprocess Rate error action
		public void clickReprocessRateErrAction( String currscreentitle ) throws Exception
		{
			GenericHelper.waitForLoadmask();
			NavigationHelper.navigateToAction( "Error Actions", "Rerate" );
			ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			//assertEquals( NavigationHelper.getScreenTitle(), currscreentitle );
		}
}
