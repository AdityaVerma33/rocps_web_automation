package com.subex.rocps.automation.helpers.application.roaming.roamingFlStatus;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.roaming.util.RoamingRecordsUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;

public class SevereRecords extends RoamingRecordsUtil
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> severeRecordExcelMap = null;
	protected Map<String, String> severeRecordMap = null;
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

	/**Constructor
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public SevereRecords( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		severeRecordExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( severeRecordExcelMap );
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

	// Method: Validate the search results of 'Severe Records Screen' screen
	public void validateSevereRecordsSearchResult( String fileName, String tapFileNm ) throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{

			severeRecordMap = excelHolderObj.dataMap( index );
			initializeVariable( severeRecordMap );
			super.validateFileName( "PS_Detail_RoamingFlStatus_fileNm_txtId", fileName );
			super.validateFileName( "PS_Detail_RoamFlStatus_SeveRec_TapfileNm_txtId", tapFileNm );
			super.filterSelection( "PS_Detail_RoamFlStatus_RoamRec_callType_comboId", callType, "PS_Detail_RoamingRecordScreen_fileNmHeader_xpath" );
			if ( ValidationHelper.isNotEmpty( searchScreenColumns ) )
				psGenericHelper.screenColumnValidation( "Stream", searchScreenColumns, "'Severe Records Screen' for file name " + fileName + " of callType " + callType );
			psGenericHelper.validateSearchResult( colmHdrsSearchResult, mapkeys, severeRecordMap, "PS_Detail_SearchScreen_ColumnHeaderID", "SearchGrid" );
			Log4jHelper.logInfo( "'Severe Records Screen' results are validated successfully for file name -'" + fileName + "' of callType " + callType );
		}

	}
}
