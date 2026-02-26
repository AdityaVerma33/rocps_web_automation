package com.subex.rocps.automation.helpers.application.roaming.roamingFlStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.roaming.roamingFlStatus.callEventDetails.CallEventDetailsActImpl;
import com.subex.rocps.automation.helpers.application.roaming.util.RoamingRecordsUtil;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;

/*Roaming Records screen name change to 'Call Event Details*/
public class RoamingRecordsScreen extends RoamingRecordsUtil
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> roamingRecordsScreenExcelMap = null;
	protected Map<String, String> roamingRecordsScreenMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int colSize;
	protected int index;
	protected String colmHdrsSearchResult;
	protected String searchScreenColumns;
	protected String callType;
	protected String mapkeys;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	DataVerificationHelper dataVerHelOb = new DataVerificationHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();

	/**Constructor
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public RoamingRecordsScreen( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		roamingRecordsScreenExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( roamingRecordsScreenExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{

		mapkeys = ExcelHolder.getKey( map, "MapRowKeys" );
		colmHdrsSearchResult = ExcelHolder.getKey( map, "ColmnHeadersRecordScreen" );
		callType = ExcelHolder.getKey( map, "CallType" );
		searchScreenColumns = ExcelHolder.getKey( map, "SearchScreenColumns" );

	}

	// Method: Validate the search results of 'Calll Event Details Search' screen
	public void validateCallEventDetailsSearchResult( String fileName ) throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{

			roamingRecordsScreenMap = excelHolderObj.dataMap( index );
			initializeVariable( roamingRecordsScreenMap );
			super.validateFileName( "PS_Detail_RoamingFlStatus_fileNm_txtId", fileName );
			super.filterSelection( "PS_Detail_RoamFlStatus_RoamRec_callType_comboId", callType, "PS_Detail_RoamingRecordScreen_fileNmHeader_xpath" );
			GenericHelper.waitTime( 10, "Waiting for Roaming Records" );
			if ( ValidationHelper.isNotEmpty( searchScreenColumns ) )
				psGenericHelper.screenColumnValidation( "Stream", searchScreenColumns, "'Roaming Records Screen' for file name " + fileName + " of callType " + callType );
			dataVerHelOb.validateDataWithoutSorting( GenericHelper.getORProperty( "PS_Detail_SearchScreen_ColumnHeaderID" ), roamingRecordsScreenMap, colmHdrsSearchResult, mapkeys, false );
			Log4jHelper.logInfo( "\n'Call Event Details Screen' results are validated successfully for file name -'" + fileName + "' of callType " + callType );
			String[] rows = psStringUtils.stringSplitFirstLevel( mapkeys );
			String rowValue = roamingRecordsScreenMap.get( rows[0] );
			int rowNum = psDataComponentHelper.getRowNumOfGrid( "SearchGrid", rowValue );
			performActionTask( fileName,rowNum );
		}

	}

	/*
	 * This method is for Perform Action task
	 */
	private void performActionTask( String fileNm,int rowNum ) throws Exception
	{
		CallEventDetailsActImpl callEventDetailsActImpl = new CallEventDetailsActImpl();
		List<String> actionNameKeys = callEventDetailsActImpl.getKeysOfActionName();
		for ( String actionKey : actionNameKeys )
		{
			String actionNameValue = roamingRecordsScreenMap.get( actionKey );
			if ( ValidationHelper.isNotEmpty( actionNameValue ) )
			{
				GridHelper.clickRow( "SearchGrid", rowNum, "IMSI" );
				callEventDetailsActImpl.switchToActionTask( fileNm, actionKey, actionNameValue, path, workBookName, sheetName );

			}
		}
	}
}
