package com.subex.rocps.automation.helpers.application.roaming.roamingFlStatus.callEventDetails.chargeInformation;

import java.util.ArrayList;
import java.util.List;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class ChargeInformationActImpl extends PSAcceptanceTest
{
	protected String path;
	protected String workBookName;
	protected String sheetName;
	PSActionImpl psActionImpl = new PSActionImpl();
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	/*
	 * This method is for initialize workbook, sheetname  and path
	 */
	private void initializeWorkbookSheetNm( String path, String workbooknm, String sheetNm )
	{
		this.path = path;
		workBookName = workbooknm;
		sheetName = sheetNm;
	}
	
	/*
	 * This method is for switch to Action task
	 */
	public void switchToActionTask( String fileNm,String chargedItem,String actionName, String actionValue, String path, String workbooknm, String sheetNm ) throws Exception
	{
		initializeWorkbookSheetNm( path, workbooknm, sheetNm );
		switch( actionName )
		{
		case "ChargeAndTaxDetail_PopupAct_OfChargeInfo":
			chargeAndTaxDetailPopupActionTask( fileNm, chargedItem, actionValue );
			break;
		default:
			Log4jHelper.logInfo( "The given action name is not found '" + actionName+"' in the SwitchToActionTask()" );
			break;
		}
	}
	
	
	/*
	 * This method is for switch to 'Charge and Tax Detail' Action task
	 */
	private void chargeAndTaxDetailPopupActionTask( String fileNm,String chargedItem, String actionValue ) throws Exception
	{
		String testCaseNm=actionValue;
		psActionImpl.clickOnAction( "View", "Charge and Tax Detail", "PS_Detail_RoamFlStatus_chargeAndTaxDetail_xpath" );
		ChargeAndTaxDetail chargeAndTaxDetail=new ChargeAndTaxDetail( path, workBookName, sheetName, testCaseNm );
		chargeAndTaxDetail.validateChargedAndTaxDetailSearchResult( fileNm, chargedItem );
		ButtonHelper.click( "PS_Detail_RoamFlStatus_chargeAndTaxDetail_closeBtnId" );
		ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "PS_Detail_RoamFlStatus_chargeAndTaxDetail_xpath" ), searchScreenWaitSec );
	}

	/*
	 * This method is for get keys of Actions
	 */
	public List<String> getKeysOfActionName()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "ChargeAndTaxDetail_PopupAct_OfChargeInfo" );
		return listColumn;
	}
}
