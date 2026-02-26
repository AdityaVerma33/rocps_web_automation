package com.subex.rocps.automation.helpers.application.xdrextraction.XdrExtraTemplate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class XdrExtrTempActionImpl extends PSAcceptanceTest
{

	protected PSGenericHelper psgenericHelperobj = new PSGenericHelper();

	/* This method is used to click on 'New' action in XDR Extraction Template*/
	public void clickNewAction( String clientPartition, String currscreentitle ) throws Exception
	{
		psgenericHelperobj.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "New " + currscreentitle );
	}

	/* Method: This method is used to click on 'Save As' action in XDR Extraction Template*/
	public void clickSaveAsAction( String clientPartition, String currscreentitle, String currTempNm ) throws Exception
	{

		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		NavigationHelper.navigateToAction( "Common Tasks", "Save As" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_ValidFromDt_textID" ), searchScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "New " + currscreentitle );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_xdrExtTemp_name_textID" ), currTempNm );
	}

	/* Method: This method is used to click on 'View' action in XDR Extraction Template*/
	public void clickViewAction( String clientPartition, String currscreentitle ) throws Exception
	{

		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		NavigationHelper.navigateToAction( "Common Tasks", "View" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "View " + currscreentitle );
	}

	/* Method: This method is used to click on 'Template Actions' action in XDR
	 Extraction Template*/
	public void changeStatusToAccepted( String popupTitle ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		NavigationHelper.navigateToAction( "Template Actions", "Accept Template" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
		assertTrue( PopupHelper.isPresent() );
		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
		assertTrue( PopupHelper.isTextPresent( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), "Template Status Changed to Accepted Successfully" ) );
		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	/* Method: This method is used to click on 'Update XDR Extract Option' action in
	 XDR Extraction Template*/
	public void updateXdrExtOpAction( String updateActionNm ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		NavigationHelper.navigateToAction( "Update XDR Extract Option", updateActionNm );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
		assertTrue( PopupHelper.isPresent() );
		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );

	}
}
