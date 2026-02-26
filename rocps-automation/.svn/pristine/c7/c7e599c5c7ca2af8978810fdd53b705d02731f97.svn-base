package com.subex.rocps.automation.helpers.application.roaming.roamingFlStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.roaming.RoamingFileStatus;
import com.subex.rocps.automation.helpers.application.roaming.util.RoamingRecordsUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;

public class RoamingFlStatusActionImpl extends RoamingRecordsUtil
{
	protected Map<String, String> roamingFlStatusActionMap = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;;
	protected String fileType;
	protected String isNotificationFl;
	protected String sender;
	protected String recepient;
	protected String failureReason;
	protected String notificationSent;

	PSActionImpl psActionImpl = new PSActionImpl();
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	RoamingFileStatus roamingFileStatus = new RoamingFileStatus();

	/**Constructor
	 * @param roamingFlStatusActionMap
	 */
	public RoamingFlStatusActionImpl( Map<String, String> roamingFlStatusActionMap )
	{
		this.roamingFlStatusActionMap = roamingFlStatusActionMap;
	}

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
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		fileType = map.get( "FileType" );
		sender = map.get( "Sender" );
		recepient = map.get( "Recipient" );
		isNotificationFl = map.get( "IsNotificationFile" );
		failureReason = map.get( "FailureReason" );
		notificationSent = map.get( "NotificationSent" );
	}

	/*
	 * This method is for switch to Action task
	 */
	public void switchToActionTask( String fileNm,String actionName, String actionValue, String path, String workbooknm, String sheetNm ) throws Exception
	{
		initializeWorkbookSheetNm( path, workbooknm, sheetNm );
		initializeVariable( roamingFlStatusActionMap );
		switch( actionName )
		{
		case "CallEventDetails_Action":
			callEventDetailsActionTask(  fileNm, actionValue );
			roamingFileStatus.navigateToRoamingFlStatusScreen();
			roamingFileFilter(fileNm);
			break;
		case "TapIn_TapErrors_Action":
			tapInTapErrorActionTask(  fileNm, actionValue );
			roamingFileStatus.navigateToRoamingFlStatusScreen();
			roamingFileFilter(fileNm);
			break;
		case "SendTAPNotification_Action":
			sendTapNotificationActionTask(  fileNm, actionValue );
			break;
		case "RapIn_ViewRapFile_Action":
			rapInViewRapFileActionTask(  fileNm, actionValue );
			break;
		case "RapInViewServeRapError_Action":
			rapInViewServeRapErrorActionTask(  fileNm, actionValue );
			roamingFileStatus.navigateToRoamingFlStatusScreen();
			roamingFileFilter(fileNm);
			break;
		case "RapIn_RoamingFileTaskStatus_Action":
			rapInRoamingFlTaskStatusActionTask(  fileNm, actionValue );
			roamingFileStatus.navigateToRoamingFlStatusScreen();
			roamingFileFilter(fileNm);
			break;
		case "RapIn_RecycleErrors_Action":
			rapInRecycleErrorsActionTask(  fileNm, actionValue );
			break;
		case "RapIn_ResendTap_Action":
			rapInResendTapActionTask(  fileNm, actionValue );
			break;
		case "RapOut_ErrorDetail_Action":
			rapOutErrorDetailActionTask(fileNm,actionValue);
			roamingFileStatus.navigateToRoamingFlStatusScreen();
			roamingFileFilter(fileNm);
			break;
		default:
			Log4jHelper.logInfo( "The given action name is not found '" + actionName+"' in the SwitchToActionTask()" );
			break;
		}
	}
	/*
	 * This method is for 'Error Detail' action
	 */
	private void rapOutErrorDetailActionTask( String fileNm, String actionValue ) throws Exception
	{
		String testCasenm = actionValue;
		psActionImpl.clickOnAction( "View", "View Error Detail", "PS_Detail_RoamingRecordScreen_fileNmHeader_xpath" );
		ViewErrorDetail viewErrorDetail=new ViewErrorDetail( path, workBookName, sheetName, testCasenm );
		viewErrorDetail.validateErrorDetailSearchResult( fileNm );
		
	}

	/*
	 * This method is for 'Resend TAP' action
	 */
	private void rapInResendTapActionTask(  String fileNm, String actionValue ) throws Exception
	{
		String actionFlg = actionValue;
		if ( ValidationHelper.isTrue( actionFlg ) )
		{
			psActionImpl.clickOnAction( "Resend TAP", "Resend TAP", "PS_Detail_RoamingFlStatus_popupXpath" );
			String popupText = ElementHelper.getText( GenericHelper.getORProperty( "PS_Detail_RoamingFlStatus_popupXpath" ) );
			if ( popupText.contains( "As records are already recycled and TAPOUT being sent" ) )
				Log4jHelper.logInfo( popupText );
			else
				Log4jHelper.logInfo( popupText );
			ButtonHelper.click( "OKButton" );
			psGenericHelper.scrollforHeaderElement( "File Name" );
			psGenericHelper.waitforHeaderElement( "File Name" );
			Log4jHelper.logInfo( "'Roaming File Status'' is  succesffuly validated for 'Resend TAP' action with file name:" + fileNm );

		}

	}

	/*
	 * This method is for 'Recycle Errors' action
	 */
	private void rapInRecycleErrorsActionTask(  String fileNm, String actionValue ) throws Exception
	{
		String actionFlg = actionValue;
		if ( ValidationHelper.isTrue( actionFlg ) )
		{
			psActionImpl.clickOnAction( "Recycle Errors", "Recycle Errors", "PS_Detail_RoamingFlStatus_popupXpath" );
			String popupText = ElementHelper.getText( GenericHelper.getORProperty( "PS_Detail_RoamingFlStatus_popupXpath" ) );
			if ( popupText.contains( "Schedule Rerate task" ) )
				Log4jHelper.logInfo( popupText );
			else
				Log4jHelper.logInfo( popupText );
			ButtonHelper.click( "OKButton" );
			psGenericHelper.scrollforHeaderElement( "File Name" );
			psGenericHelper.waitforHeaderElement( "File Name" );
			Log4jHelper.logInfo( "'Roaming File Status'' is  succesffuly validated for 'Recycle Errors' action with file name:" + fileNm );
		}

	}

	/*
	 * This method is for 'Roaming File Task Status' action
	 */
	private void rapInRoamingFlTaskStatusActionTask(  String fileNm, String actionValue ) throws Exception
	{
		String testCasenm = actionValue;
		psActionImpl.clickOnAction( "Jump To", "Roaming File Task Status", "PS_Detail_RoamingRecordScreen_fileNmHeader_xpath" );
		RoamingFileTaskStatus roamingFileTaskStatus = new RoamingFileTaskStatus( path, workBookName, sheetName, testCasenm );
		roamingFileTaskStatus.validateRoamFlTaskStatusSearchResult( fileNm );

	}

	/*
	 * This method is for 'View Severe Rap Error' action
	 */
	private void rapInViewServeRapErrorActionTask(  String fileNm, String actionValue ) throws Exception
	{
		String testCasenm = actionValue;
		String likedTapFlNm = roamingFileStatus.getLinkedTapValue( 1 );
		psActionImpl.clickOnAction( "View", "View Severe Rap Error", "PS_Detail_RoamingRecordScreen_fileNmHeader_xpath" );
		SevereError severeError = new SevereError( path, workBookName, sheetName, testCasenm );
		severeError.validateSevereErrorSearchResult( fileNm, likedTapFlNm );

	}

	/*
	 * This method is for 'View Rap File' action
	 */
	private void rapInViewRapFileActionTask(  String fileNm, String actionValue ) throws Exception
	{
		String testCasenm = actionValue;

		String getFailureReasonValue = roamingFileStatus.getFailureReasonValue( 1 );
		switch( getFailureReasonValue )
		{
		case "FATAL RETURN":
			psActionImpl.clickOnAction( "View", "View RAP File", "PS_Detail_RoamingRecordScreen_fileNmHeader_xpath" );
			FatalError fatalError = new FatalError( path, workBookName, sheetName, testCasenm );
			fatalError.validateFatalErrorsSearchResult( fileNm );
			roamingFileStatus.navigateToRoamingFlStatusScreen();
			roamingFileFilter(fileNm);

			break;
		case "SEVERE RETURN":
			String likedTapFlNm = roamingFileStatus.getLinkedTapValue( 1 );
			psActionImpl.clickOnAction( "View", "View Rap File", "PS_Detail_RoamingRecordScreen_fileNmHeader_xpath" );
			SevereRecords severeRecords = new SevereRecords( path, workBookName, sheetName, testCasenm );
			severeRecords.validateSevereRecordsSearchResult( fileNm, likedTapFlNm );
			roamingFileStatus.navigateToRoamingFlStatusScreen();
			roamingFileFilter(fileNm);
			break;
		case "MISSING RETURN":
			psActionImpl.clickOnAction( "View", "View Rap File", "PS_Detail_MissingStop_fileNmHeader_xpath" );
			ButtonHelper.click( "CancelButton" );
			psGenericHelper.scrollforHeaderElement( "File Name" );
			psGenericHelper.waitforHeaderElement( "File Name" );
			break;
		case "STOP RETURN":
			psActionImpl.clickOnAction( "View", "View Rap File", "PS_Detail_MissingStop_fileNmHeader_xpath" );
			super.validateFileName( "PS_Detail_RoamingFlStatus_fileNm_txtId", fileNm );
			ButtonHelper.click( "CancelButton" );
			psGenericHelper.scrollforHeaderElement( "File Name" );
			psGenericHelper.waitforHeaderElement( "File Name" );
			break;
		default:
			break;
		}

	}

	/*
	 * This method is for 'Send TAP Notification' action
	 */
	private void sendTapNotificationActionTask(  String fileNm, String actionValue ) throws Exception
	{
		String actionFlg = actionValue;
		if ( ValidationHelper.isTrue( actionFlg ) )
		{
			psActionImpl.clickOnAction( "Send TAP Notification", "Send TAP Notification", "PS_Detail_RoamingFlStatus_popupXpath" );
			String popupText = ElementHelper.getText( GenericHelper.getORProperty( "PS_Detail_RoamingFlStatus_popupXpath" ) );
			if ( popupText.contains( "Do you want to generate a TAP Notification against sequence number" ) )
			{
				Log4jHelper.logInfo( popupText );
				ButtonHelper.click( "OKButton" );
				ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_RoamingFlStatus_popupXpath" ), searchScreenWaitSec );
				popupText = ElementHelper.getText( GenericHelper.getORProperty( "PS_Detail_RoamingFlStatus_popupXpath" ) );
				Log4jHelper.logInfo( popupText );
				ButtonHelper.click( "OKButton" );
			}
			else
			{
				Log4jHelper.logInfo( "PopUp message-" + popupText );
				ButtonHelper.click( "OKButton" );
			}
			psGenericHelper.scrollforHeaderElement( "File Name" );
			psGenericHelper.waitforHeaderElement( "File Name" );
			Log4jHelper.logInfo( "'Roaming File Status'' is  succesffuly validated for 'Send TAP Notification' action with file name:" + fileNm );

		}

	}

	/*
	 * This method is for 'Tap Errors' action
	 */
	private void tapInTapErrorActionTask(  String fileNm, String actionValue ) throws Exception
	{
		String testCasenm = actionValue;
		psActionImpl.clickOnAction( "View", "Tap Errors", "PS_Detail_TapError_ContextHeader_xpath" );
		TapError tapError = new TapError( path, workBookName, sheetName, testCasenm );
		tapError.validateTapErrorSearchResult( fileNm );

	}

	/*
	 * This method is for 'Call Event Details ' action
	 */
	private void callEventDetailsActionTask(  String fileNm, String actionValue ) throws Exception
	{
		String testCasenm = actionValue;
		psActionImpl.clickOnAction( "View", "Call Event Details", "PS_Detail_RoamingRecordScreen_fileNmHeader_xpath" );
		RoamingRecordsScreen roamingRecordsScreen = new RoamingRecordsScreen( path, workBookName, sheetName, testCasenm );
		roamingRecordsScreen.validateCallEventDetailsSearchResult( fileNm );
	}

	/*
	 * This method is for get keys of Actions
	 */
	public List<String> getKeysOfActionName()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "CallEventDetails_Action" );
		listColumn.add( "TapIn_TapErrors_Action" );
		listColumn.add( "SendTAPNotification_Action" );
		listColumn.add( "RapIn_ViewRapFile_Action" );
		listColumn.add( "RapInViewServeRapError_Action" );
		listColumn.add( "RapIn_RoamingFileTaskStatus_Action" );
		listColumn.add( "RapIn_RecycleErrors_Action" );
		listColumn.add( "RapIn_ResendTap_Action" );
		listColumn.add( "RapOut_ErrorDetail_Action" );
		return listColumn;
	}

	/*
	 * This method is for Roaming filter
	 */
	private void roamingFileFilter(String fileNm) throws Exception
	{
		roamingFileStatus.isRoamingFilePresent( fileType, fileNm, sender, recepient, isNotificationFl, failureReason, notificationSent );
	}
}
