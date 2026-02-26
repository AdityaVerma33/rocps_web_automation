package com.subex.rocps.automation.helpers.application.roaming.roamingFlStatus;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.roaming.util.RoamingRecordsUtil;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;

public class RoamingFileTaskStatus extends RoamingRecordsUtil
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> roamFlTaskStatusExcelMap = null;
	protected Map<String, String> roamFlTaskStatusMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int colSize;
	protected int index;
	protected String colmHdrsSearchResult;
	protected String searchScreenColumns;
	protected String streamStage;
	protected String mapkeys;
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	/**Constructor
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public RoamingFileTaskStatus( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		roamFlTaskStatusExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( roamFlTaskStatusExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{

		mapkeys = ExcelHolder.getKey( map, "MapRowKeys" );
		colmHdrsSearchResult = ExcelHolder.getKey( map, "ColmnHeadersRecordScreen" );
		streamStage = ExcelHolder.getKey( map, "StreamStage" );
		searchScreenColumns = ExcelHolder.getKey( map, "SearchScreenColumns" );

	}

	// Method: Validate the search results of 'Roaming File Task Status' screen
	public void validateRoamFlTaskStatusSearchResult( String fileName ) throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{

			roamFlTaskStatusMap = excelHolderObj.dataMap( index );
			initializeVariable( roamFlTaskStatusMap );
			super.validateFileName( "PS_Detail_RoamFlStatus_RoamFlTaskStat_flNm_textId", fileName );
			super.filterSelection( "PS_Detail_RoamFlStatus_RoamFlTaskStat_strmStg_comboId", streamStage, "PS_Detail_RoamingRecordScreen_fileNmHeader_xpath" );
			if ( ValidationHelper.isNotEmpty( searchScreenColumns ) )
				psGenericHelper.screenColumnValidation( "File Name", searchScreenColumns, "'Roaming File Task Status' for file name " + fileName + " of streamStage " + streamStage );
			psGenericHelper.validateSearchResult( colmHdrsSearchResult, mapkeys, roamFlTaskStatusMap, "PS_Detail_SearchScreen_ColumnHeaderID", "SearchGrid" );
			Log4jHelper.logInfo( "'Roaming File Task Status' results are validated successfully for file name -'" + fileName + "' of streamStage " + streamStage );
		}

	}

}
