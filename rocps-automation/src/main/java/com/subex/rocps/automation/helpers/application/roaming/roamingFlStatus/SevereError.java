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

public class SevereError extends RoamingRecordsUtil
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> severeErrorExcelMap = null;
	protected Map<String, String> severeErrorMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int colSize;
	protected int index;
	protected String colmHdrsSearchResult;
	protected String searchScreenColumns;
	protected String mapkeys;
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	/**Constructor
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public SevereError( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		severeErrorExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( severeErrorExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{

		mapkeys = ExcelHolder.getKey( map, "MapRowKeys" );
		colmHdrsSearchResult = ExcelHolder.getKey( map, "ColmnHeadersRecordScreen" );
		searchScreenColumns = ExcelHolder.getKey( map, "SearchScreenColumns" );

	}

	// Method: Validate the search results of 'Severe Error' screen
	public void validateSevereErrorSearchResult( String fileName, String tapFlNm ) throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{

			severeErrorMap = excelHolderObj.dataMap( index );
			initializeVariable( severeErrorMap );
			super.validateFileName( "PS_Detail_RoamingFlStatus_fileNm_txtId", fileName );
			super.validateFileName( "PS_Detail_RoamFlStatus_SeveRec_TapfileNm_txtId", tapFlNm );
			if ( ValidationHelper.isNotEmpty( searchScreenColumns ) )
				psGenericHelper.screenColumnValidation( "Error Code", searchScreenColumns, "'Severe Error' for file name " + fileName );
			psGenericHelper.validateSearchResult( colmHdrsSearchResult, mapkeys, severeErrorMap, "PS_Detail_SearchScreen_ColumnHeaderID", "SearchGrid" );
			Log4jHelper.logInfo( "'Severe Error' results are validated successfully for file name -'" + fileName );
		}

	}
}
