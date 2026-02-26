package com.subex.rocps.automation.helpers.application.roaming.roamingFlStatus.callEventDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.roaming.util.RoamingRecordsUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class CallEventDetailsActImpl extends PSAcceptanceTest
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
	public void switchToActionTask( String fileNm, String actionName, String actionValue, String path, String workbooknm, String sheetNm ) throws Exception
	{
		initializeWorkbookSheetNm( path, workbooknm, sheetNm );
		switch( actionName )
		{
		case "ChargeInformation_SubAct_OfCallEveDetails":
			chargeInformationSubActionTask( fileNm, actionValue );
			navigateToCallEventDetailsScreen();
			break;
		case "RecordEntities_SubAct_OfCallEveDetails":
			recordEntitiesSubActionTask( fileNm, actionValue );
			break;
		default:
			Log4jHelper.logInfo( "The given action name is not found '" + actionName + "' in the SwitchToActionTask()" );
			break;
		}
	}

	/*
	 * This method is for switch to  Recording entities Action task
	 */
	private void recordEntitiesSubActionTask( String fileNm, String actionValue ) throws Exception
	{
		String testCasenm = actionValue;
		psActionImpl.clickOnAction( "View", "View Recording Entities", "PS_Detail_RoamFlStatus_recordingEntities_xpath" );
		RecordEntitiesDetail recordEntitiesDetail = new RecordEntitiesDetail( path, workBookName, sheetName, testCasenm );
		recordEntitiesDetail.validateRecordEntitiesSearchResult( fileNm );
		ButtonHelper.click( "PS_Detail_RoamFlStatus_recordEntities_closeBtnId" );
		ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "PS_Detail_RoamFlStatus_recordingEntities_xpath" ), searchScreenWaitSec );
	}

	/*
	 * This method is for back to call event details screen
	 */
	private void navigateToCallEventDetailsScreen() throws Exception
	{
		driver.navigate().back();
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	/*
	 * This method is for switch to Charge Information  Action task
	 */
	private void chargeInformationSubActionTask( String fileNm, String actionValue ) throws Exception
	{
		String testCasenm = actionValue;
		psActionImpl.clickOnAction( "View", "Charge Information", "PS_Detail_RoamingRecordScreen_fileNmHeader_xpath" );
		ChargeInformationDetail chargeInformationDetail = new ChargeInformationDetail( path, workBookName, sheetName, testCasenm );
		chargeInformationDetail.validateChargedInformationSearchResult( fileNm );
	}

	/*
	 * This method is for get keys of Actions
	 */
	public List<String> getKeysOfActionName()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "ChargeInformation_SubAct_OfCallEveDetails" );
		listColumn.add( "RecordEntities_SubAct_OfCallEveDetails" );
		return listColumn;
	}
}
