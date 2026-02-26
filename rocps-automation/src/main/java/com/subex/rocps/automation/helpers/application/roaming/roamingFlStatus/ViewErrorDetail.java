package com.subex.rocps.automation.helpers.application.roaming.roamingFlStatus;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.roaming.util.RoamingRecordsUtil;
import com.subex.rocps.automation.utils.ExcelHolder;

public class ViewErrorDetail extends RoamingRecordsUtil
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>>viewErrorDetailExcelMap = null;
	protected Map<String, String>viewErrorDetailMap = null;
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
	DataVerificationHelper dataVerHelOb = new DataVerificationHelper();

	/**
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public ViewErrorDetail( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		viewErrorDetailExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder(viewErrorDetailExcelMap );
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

	// Method: Validate the search results of 'Error Detail Search' screen
	public void validateErrorDetailSearchResult( String fileName ) throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{

			viewErrorDetailMap = excelHolderObj.dataMap( index );
			initializeVariable( viewErrorDetailMap );
			//super.validateFileName( "PS_Detail_RoamingFlStatus_fileNm_txtId", fileName );
			super.clickOnSearchButton( "PS_Detail_RoamingRecordScreen_fileNmHeader_xpath" );
			GenericHelper.waitTime( 10, "Waiting for Error Detail Action" );
			if ( ValidationHelper.isNotEmpty( searchScreenColumns ) )
				psGenericHelper.screenColumnValidation( "Error Code", searchScreenColumns, "'Error Detail Action Screen' for file name " + fileName );
			dataVerHelOb.validateDataWithoutSorting( GenericHelper.getORProperty( "PS_Detail_SearchScreen_ColumnHeaderID" ), viewErrorDetailMap, colmHdrsSearchResult, mapkeys, false );
			Log4jHelper.logInfo( "'\nError Detail Action Screen' results are validated successfully for file name -'" + fileName  );
		}

	}
}
