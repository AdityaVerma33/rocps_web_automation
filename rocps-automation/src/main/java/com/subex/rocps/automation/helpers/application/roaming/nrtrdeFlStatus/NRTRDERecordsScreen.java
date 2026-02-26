package com.subex.rocps.automation.helpers.application.roaming.nrtrdeFlStatus;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.roaming.util.RoamingRecordsUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;

public class NRTRDERecordsScreen extends RoamingRecordsUtil
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> nrtrdeRecordsScreenExcelMap = null;
	protected Map<String, String> nrtrdeRecordsScreenMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int colSize;
	protected int index;
	protected String colmHdrsSearchResult;
	protected String searchScreenColumns;
	protected String context;
	protected String mapkeys;
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	/**Constructor
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public NRTRDERecordsScreen( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		nrtrdeRecordsScreenExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( nrtrdeRecordsScreenExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{

		mapkeys = ExcelHolder.getKey( map, "MapRowKeys" );
		colmHdrsSearchResult = ExcelHolder.getKey( map, "ColmnHeadersRecordScreen" );
		context = ExcelHolder.getKey( map, "Context" );
		searchScreenColumns = ExcelHolder.getKey( map, "SearchScreenColumns" );

	}

	// Method: Validate the search results of 'NRTRDE Record Screen' screen
	public void validateNrtrdeRecordSearchResult( String fileName ) throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{

			nrtrdeRecordsScreenMap = excelHolderObj.dataMap( index );
			initializeVariable( nrtrdeRecordsScreenMap );
			super.validateFileName( "PS_Detail_NrtrdeFlStatus_fileNm_txtId", fileName );
			super.filterSelection( "PS_Detail_NrtrdeFlStatus_Record_context_comboId", context, "PS_Detail_NrtrdeRecordScreen_contextHeader_xpath" );
			if ( ValidationHelper.isNotEmpty( searchScreenColumns ) )
				psGenericHelper.screenColumnValidation( "IMSI", searchScreenColumns, "'NRTRDE Record Screen' for file name " + fileName + " of context " + context );
			psGenericHelper.validateSearchResult( colmHdrsSearchResult, mapkeys, nrtrdeRecordsScreenMap, "PS_Detail_SearchScreen_ColumnHeaderID", "SearchGrid" );
			Log4jHelper.logInfo( "'NRTRDE Record Screen' results are validated successfully for file name -'" + fileName + "' of context " + context );
		}

	}

}
