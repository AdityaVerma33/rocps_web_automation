package com.subex.rocps.automation.helpers.application.dispute.dispute;

import org.openqa.selenium.By;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class DisputeActionImpl extends PSAcceptanceTest
{

	protected PSGenericHelper psgenericHelperobj = new PSGenericHelper();

	//This method is used to click on new action in Dispute
	public void clickNewAction( String clientPartition, String currscreentitle ) throws Exception
	{
		psgenericHelperobj.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "New " + currscreentitle );
	}

	//This method is used to click on 'Reject Dispute' action in Dispute
	public void clickRejectDisputeAction( String popupscreentitle, String rejectComment ) throws Exception
	{
		GenericHelper.waitForLoadmask();
		NavigationHelper.navigateToAction( "Dispute Actions", "Reject Dispute" );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), popupscreentitle );

		TextAreaHelper.type( "PS_Detail_dispute_rejectResn_texAreatId", rejectComment );
		ButtonHelper.click( "PS_Detail_dispute_rejectDisp_btnID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
		ElementHelper.isElementPresent( GenericHelper.getORProperty( "PS_searchPanelId" ) );

	}

	//This method is used to click on 'Validate Dispute' action in Dispute

	public void clickValidateDisputeAction( String currscreentitle ) throws Exception
	{
		GenericHelper.waitForLoadmask();
		NavigationHelper.navigateToAction( "Dispute Actions", "Validate Dispute" );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), currscreentitle );
		String actualInfo = driver.findElement( By.id( GenericHelper.getORProperty( "PS_Detail_dispute_Validate_msz_textId" ) ) ).getText();
		assertTrue( actualInfo.contains( GenericHelper.getORProperty( "PS_Detail_dispute_Validate_msz" ) ), "The confirmation message is not as expected" );
		ButtonHelper.click( "PS_Detail_dispute_Validate_ok_BtnId" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
		ElementHelper.isElementPresent( GenericHelper.getORProperty( "PS_searchPanelId" ) );
		GenericHelper.waitForLoadmask();

	}

	//This method is used to click on 'View Dispute History' action in Dispute
	public void clickViewUpdtHistoryDispAction( String currscreentitle ) throws Exception
	{
		GenericHelper.waitForLoadmask();
		NavigationHelper.navigateToAction( "Dispute Actions", "View Dispute History" );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), currscreentitle );
		ButtonHelper.click( "PS_Detail_disputeHistory_close_BtnId" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
		ElementHelper.isElementPresent( GenericHelper.getORProperty( "PS_searchPanelId" ) );

	}

	//This method is used to click on 'Move To Dispute Resolve' action in Dispute

	public void moveToDisputeResolveAction( String currscreentitle, String dispRootCause, String favHomOpAmnt, String closureCmnt ) throws Exception
	{
		GenericHelper.waitForLoadmask();
		NavigationHelper.navigateToAction( "Dispute Actions", "Move To Dispute Resolve" );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), currscreentitle );
		ComboBoxHelper.select( "PS_Detail_dispute_Resolve_GridWrapper", "PS_Detail_dispute_resolveRootCaus_comboId", dispRootCause );
		TextBoxHelper.type( "PS_Detail_dispute_Resolve_GridWrapper", "PS_Detail_disp_resolve_amt-FavHomeOper_amt_textID", favHomOpAmnt );
		TextAreaHelper.type( "PS_Detail_dispute_Resolve_ClosCmnt_textID", closureCmnt );
		ButtonHelper.click( "PS_Detail_dispute_Resolve_BtnID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.isElementPresent( GenericHelper.getORProperty( "PS_searchPanelId" ) );

	}

	//This method is used to click on 'Close Dispute' action in Dispute
	public void closeDisputeAction() throws Exception
	{
		GenericHelper.waitForLoadmask();
		NavigationHelper.navigateToAction( "Dispute Actions", "Close Dispute" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_popUP_Xpath" ), searchScreenWaitSec );
		ElementHelper.isElementPresent( GenericHelper.getORProperty( "PS_searchPanelId" ) );

	}

}