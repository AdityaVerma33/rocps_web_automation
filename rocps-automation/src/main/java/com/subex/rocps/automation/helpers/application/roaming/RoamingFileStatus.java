package com.subex.rocps.automation.helpers.application.roaming;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportsAndExt.ReportAndExtFileHelper;
import com.subex.rocps.automation.helpers.application.roaming.nrtrdeFlStatus.NRTRDERecordsScreen;
import com.subex.rocps.automation.helpers.application.roaming.roamingFlStatus.FatalError;
import com.subex.rocps.automation.helpers.application.roaming.roamingFlStatus.RoamingFileTaskStatus;
import com.subex.rocps.automation.helpers.application.roaming.roamingFlStatus.RoamingFlStatusActionImpl;
import com.subex.rocps.automation.helpers.application.roaming.roamingFlStatus.RoamingRecordsScreen;
import com.subex.rocps.automation.helpers.application.roaming.roamingFlStatus.SevereError;
import com.subex.rocps.automation.helpers.application.roaming.roamingFlStatus.SevereRecords;
import com.subex.rocps.automation.helpers.application.roaming.roamingFlStatus.TapError;
import com.subex.rocps.automation.helpers.application.roaming.util.RoamingFileHelper;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.dbscript.DBScriptImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class RoamingFileStatus extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> roamingFlStatusExcelMap = null;
	protected Map<String, String> roamingFlStatusMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	protected String fileType;
	protected String fileNm;
	protected String isNotificationFl;
	protected String sender;
	protected String recipient;
	protected String failureReason;
	protected String notificationSent;
	protected String mapkeys;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl psActionImpl = new PSActionImpl();
	DBScriptImpl dbScriptImpl = new DBScriptImpl();
	RoamingFileHelper roamingFileHelper = new RoamingFileHelper();

	/**
	 * Default constructor
	 */
	public RoamingFileStatus()
	{

	}

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public RoamingFileStatus( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		roamingFlStatusExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( roamingFlStatusExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/***Constructor :  Initializing the excel with occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @param occurence
	 * @throws Exception 
	 */
	public RoamingFileStatus( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		roamingFlStatusExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( roamingFlStatusExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		fileType = ExcelHolder.getKey( map, "FileType" );
		sender = ExcelHolder.getKey( map, "Sender" );
		recipient = ExcelHolder.getKey( map, "Recipient" );
		fileNm = ExcelHolder.getKey( map, "FileName" );
		isNotificationFl = ExcelHolder.getKey( map, "IsNotificationFile" );
		failureReason = ExcelHolder.getKey( map, "FailureReason" );
		notificationSent = ExcelHolder.getKey( map, "NotificationSent" );
		mapkeys = ExcelHolder.getKey( roamingFlStatusMap, "MapRowKeys" );
		colmHdrs = ExcelHolder.getKey( roamingFlStatusMap, "ColmnHeaders" );

	}

	/*
	 * This method is for initialize variable
	 */
	private void initialVarRapOut( Map<String, String> map ) throws Exception
	{
		fileType = ExcelHolder.getKey( map, "FileType" );
		sender = ExcelHolder.getKey( map, "Sender" );
		recipient = ExcelHolder.getKey( map, "Recipient" );

	}

	/*
	 * This method is for 'Roaming File Status' screen common method
	 */
	public void navigateToRoamingFlStatusScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Roaming File Status" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "File Name" );
	}

	/*
	 * This method is for 'Roaming File Status' screen column validation
	 */
	public void roamingFlStatusolumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingFlStatusMap = excelHolderObj.dataMap( index );
				navigateToRoamingFlStatusScreen();
				colmHdrs = ExcelHolder.getKey( roamingFlStatusMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "File Name", colmHdrs, "Roaming File Status" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	// Method: Validate the search results of 'Roaming File Status' screen
	public void validateRoamingFlStatusSearchResult() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingFlStatusMap = excelHolderObj.dataMap( index );
				navigateToRoamingFlStatusScreen();
				initializeVariable( roamingFlStatusMap );
				boolean isRoamingFilePresent = isRoamingFilePresent( fileType, fileNm, sender, recipient, isNotificationFl, failureReason, notificationSent );
				if ( isRoamingFilePresent )
				{
					psGenericHelper.validateSearchResult( colmHdrs, mapkeys, roamingFlStatusMap, "PS_Detail_SearchScreen_ColumnHeaderID", "SearchGrid" );
					Log4jHelper.logInfo( "'Roaming File Status' results are validated successfully for:- " + fileType + " and:-  " + fileNm );
				}
				else
					Log4jHelper.logInfo( "'Roaming File Status'' is not avilable with file name:" + fileNm + " for:" + fileType );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is for 'Roaming File Status'  validate search screen record with actions task
	 */
	public void roamFlStatusValidateSrchResWithAction() throws Exception
	{

		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingFlStatusMap = excelHolderObj.dataMap( index );
				navigateToRoamingFlStatusScreen();
				initializeVariable( roamingFlStatusMap );
				boolean isRoamingFilePresent = isRoamingFilePresent( fileType, fileNm, sender, recipient, isNotificationFl, failureReason, notificationSent );
				if ( isRoamingFilePresent )
				{
					psGenericHelper.validateSearchResult( colmHdrs, mapkeys, roamingFlStatusMap, "PS_Detail_SearchScreen_ColumnHeaderID", "SearchGrid" );
					Log4jHelper.logInfo( "'Roaming File Status' results are validated successfully for:- " + fileType + " and:-  " + fileNm );
					performActionTask( fileNm );
				}
				else
					Log4jHelper.logInfo( "'Roaming File Status'' is not avilable with file name:" + fileNm );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Roaming File Status'  validate the tapout file in screen and folder as well as perform the action task
	 */
	public void validateTapOutFile() throws Exception
	{

		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingFlStatusMap = excelHolderObj.dataMap( index );
				initializeVariable( roamingFlStatusMap );
				int pRFS_FILE_SEQ_NUM = getPRFS_FILE_SEQ_NUM( fileType, sender, recipient );
				scheduleVerifyTapOutTask();
				navigateToRoamingFlStatusScreen();
				String fileName = dbScriptImpl.getPrfsFileName( pRFS_FILE_SEQ_NUM, fileType, sender, recipient );
				String tapOutFlPath = roamingFlStatusMap.get( "RoamingTapOutFilePath" );
				boolean isRoamingFilePresent = isRoamingFilePresent( fileType, fileName, sender, recipient, isNotificationFl, failureReason, notificationSent );
				assertTrue( isRoamingFilePresent, "'Roaming File Status'' is not avilable with file name:" + fileName );
				if ( isRoamingFilePresent )
				{
					psGenericHelper.validateSearchResult( colmHdrs, mapkeys, roamingFlStatusMap, "PS_Detail_SearchScreen_ColumnHeaderID", "SearchGrid" );
					Log4jHelper.logInfo( "'Roaming File Status' results are validated successfully for:- " + fileType + " and:-  " + fileName );
					performActionTask( fileNm );
					roamingFileHelper.readFile( tapOutFlPath, fileName );
					Log4jHelper.logInfo( "'Roaming Tap Out' results are validated successfully for:- " + "Tap Out" + " -File Name:-  " + fileName );
				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for Perform Action task
	 */
	private void performActionTask( String fileNm ) throws Exception
	{
		RoamingFlStatusActionImpl roamFlStatusActionImpl = new RoamingFlStatusActionImpl( roamingFlStatusMap );
		List<String> actionNameKeys = roamFlStatusActionImpl.getKeysOfActionName();
		for ( String actionKey : actionNameKeys )
		{
			String actionNameValue = roamingFlStatusMap.get( actionKey );
			if ( ValidationHelper.isNotEmpty( actionNameValue ) )
			{
				GridHelper.clickRow( "SearchGrid", fileNm, "File Name" );
				roamFlStatusActionImpl.switchToActionTask(fileNm, actionKey, actionNameValue, path, workBookName, sheetName );

			}
		}
	}

	// Method: for validate Rap Out File
	public void validateRapOutFile() throws Exception
	{

		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingFlStatusMap = excelHolderObj.dataMap( index );
				navigateToRoamingFlStatusScreen();
				initialVarRapOut( roamingFlStatusMap );
				String linkedTapfileNm = roamingFlStatusMap.get( "LinkedTapFileName" );
				boolean isRoamingFilePresent = isRoamingFilePresent( fileType, linkedTapfileNm, sender, recipient );
				if ( isRoamingFilePresent )
				{
					String rapOutFlPath = roamingFlStatusMap.get( "RoamingRapOutFilePath" );
					String rapFile = getFileNameValue( 1 );
					ButtonHelper.click( "ClearButton" );
					psGenericHelper.waitforHeaderElement( "File Name" );
					boolean isRapFilePresent = rapFile.contains( sender + recipient );
					assertTrue( isRapFilePresent, "'Rap Out' file is not found in ;Roaming File Status' screen" );
					Log4jHelper.logInfo( "'Roaming File Status' results are validated successfully for:- 'Rap Out' " + " -File Name:-  " + rapFile + " Of Linked Tap File " + linkedTapfileNm );
					roamingFileHelper.readFile( rapOutFlPath, rapFile );
					Log4jHelper.logInfo( "'Roaming Rap Out' results are validated successfully for:- " + "Tap In" + " -File Name:-  " + linkedTapfileNm + " generated Rap Out File " + rapFile );
				}
				else
					Log4jHelper.logInfo( "'Roaming File Status'' is not avilable with linked tap file name:" + linkedTapfileNm );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}
	// Method: for validate Rap Out File
	public void validateRapOutFileWithActionPerform() throws Exception
	{

		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingFlStatusMap = excelHolderObj.dataMap( index );
				navigateToRoamingFlStatusScreen();
				initialVarRapOut( roamingFlStatusMap );
				String linkedTapfileNm = roamingFlStatusMap.get( "LinkedTapFileName" );
				boolean isRoamingFilePresent = isRoamingFilePresent( fileType, linkedTapfileNm, sender, recipient );
				if ( isRoamingFilePresent )
				{
					String rapOutFlPath = roamingFlStatusMap.get( "RoamingRapOutFilePath" );
					String rapFile = getFileNameValue( 1 );
					boolean isRapFilePresent = rapFile.contains( sender + recipient );
					assertTrue( isRapFilePresent, "'Rap Out' file is not found in ;Roaming File Status' screen" );
					Log4jHelper.logInfo( "'Roaming File Status' results are validated successfully for:- 'Rap Out' " + " -File Name:-  " + rapFile + " Of Linked Tap File " + linkedTapfileNm );
					performActionTask(rapFile);
					roamingFileHelper.readFile( rapOutFlPath, rapFile );
					Log4jHelper.logInfo( "'Roaming Rap Out' results are validated successfully for:- " + "Tap In" + " -File Name:-  " + linkedTapfileNm + " generated Rap Out File " + rapFile );
				}
				else
					Log4jHelper.logInfo( "'Roaming File Status'' is not avilable with linked tap file name:" + linkedTapfileNm );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Roaming File Status' present or not
	 */
	public boolean isRoamingFilePresent( String fileType, String fileNm, String sender, String recipient, String isNotificationFl, String failureReason, String notificationSent ) throws Exception
	{

		PSSearchGridHelper.gridFilterSearchWithComboBox( "PS_Detail_RoamingFlStatus_fileType_comboId", fileType, "File Type" );
		if ( ValidationHelper.isNotEmpty( sender ) )
			psGenericHelper.isGridTextValuePresent( "PS_Detail_RoamingFlStatus_sender_txtId", sender, "Sender" );
		if ( ValidationHelper.isNotEmpty( recipient ) )
			psGenericHelper.isGridTextValuePresent( "PS_Detail_RoamingFlStatus_recepient_txtId", recipient, "Recipient" );
		if ( ValidationHelper.isNotEmpty( isNotificationFl ) )
			PSSearchGridHelper.gridFilterSearchWithComboBox( "PS_Detail_RoamingFlStatus_isNotifcFl_comboId", isNotificationFl, "Is Notification File" );
		if ( ValidationHelper.isNotEmpty( failureReason ) )
			PSSearchGridHelper.gridFilterSearchWithComboBox( "PS_Detail_RoamingFlStatus_failureReason_comboId", failureReason, "Failure Reason" );
		if ( ValidationHelper.isNotEmpty( notificationSent ) )
			PSSearchGridHelper.gridFilterSearchWithComboBox( "PS_Detail_RoamingFlStatus_NotifcSent_comboId", notificationSent, "Notification Sent" );
		return psGenericHelper.isGridTextValuePresent( "PS_Detail_RoamingFlStatus_fileNm_txtId", fileNm, "File Name" );
	}

	/*
	 * This method is for 'Roaming File Status' present or not to use in Rap out
	 */
	public boolean isRoamingFilePresent( String fileType, String linkedTapfileNm, String sender, String recipient ) throws Exception
	{

		PSSearchGridHelper.gridFilterSearchWithComboBox( "PS_Detail_RoamingFlStatus_fileType_comboId", fileType, "File Type" );
		if ( ValidationHelper.isNotEmpty( sender ) )
			psGenericHelper.isGridTextValuePresent( "PS_Detail_RoamingFlStatus_sender_txtId", sender, "Sender" );
		if ( ValidationHelper.isNotEmpty( recipient ) )
			psGenericHelper.isGridTextValuePresent( "PS_Detail_RoamingFlStatus_recepient_txtId", recipient, "Recipient" );
		return psGenericHelper.isGridTextValuePresent( "PS_Detail_RoamingFlStatus_linkedTapFile_txtId", linkedTapfileNm, "Linked Tap File" );
	}

	// Method: Get the value  of 'Failure Reason'
	public String getFailureReasonValue( int row ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "SearchGrid", row, "Failure Reason" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		return GridHelper.getCellValue( "SearchGrid", row, "Failure Reason" );
	}

	// Method: Get the value  of 'Linked Tap File'
	public String getLinkedTapValue( int row ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "SearchGrid", row, "Linked Tap File" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		return GridHelper.getCellValue( "SearchGrid", row, "Linked Tap File" );
	}

	// Method: Get the value  of 'Linked Tap File'
	public String getLinkedRapValue( int row ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "SearchGrid", row, "Linked Rap File" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		return GridHelper.getCellValue( "SearchGrid", row, "Linked Rap File" );
	}

	// Method: Get the value  of 'Linked Tap File'
	public String getFileNameValue( int row ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "SearchGrid", row, "File Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		return GridHelper.getCellValue( "SearchGrid", row, "File Name" );
	}

	// Method: Get the value  of PRFS_FILE_SEQ_NUM of ROAMING_File_status from backend  with fileter of fileType,sender,recipient
	private int getPRFS_FILE_SEQ_NUM( String fileType, String sender, String recipient ) throws Exception
	{
		int pRFS_FILE_SEQ_NUM = dbScriptImpl.getMaxPRFS_FILE_SEQ_NUM( fileType, sender, recipient );
		Log4jHelper.logInfo( "Old PRFS_FILE_SEQ_NUM-:"+pRFS_FILE_SEQ_NUM  );
		if ( pRFS_FILE_SEQ_NUM == 0 )
			pRFS_FILE_SEQ_NUM = 1;
		else
			pRFS_FILE_SEQ_NUM++;
		Log4jHelper.logInfo( "New PRFS_FILE_SEQ_NUM-:"+pRFS_FILE_SEQ_NUM  );
		return pRFS_FILE_SEQ_NUM;
	}

	// Method: to schedule TapOut Stream task and verify the task status
	private void scheduleVerifyTapOutTask() throws Exception
	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			taskObj.scheduleRecurringTask( path, workBookName, "RoamingTapOutServerCases", "RecurringTapOutStreamTask", 1 );
			psTaskobj.psVerifyTaskStatus( path, workBookName, "RoamingTapOutServerCases", "TapOutStreamTaskStatus", 1 );
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
