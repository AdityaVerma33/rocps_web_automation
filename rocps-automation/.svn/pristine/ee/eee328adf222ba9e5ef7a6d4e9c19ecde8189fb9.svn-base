package com.subex.rocps.automation.helpers.application.roaming.roamingFlStatus.callEventDetails.chargeInformation;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class ChargeAndTaxDetail extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> chargeAndTaxDetailExcelMap = null;
	protected Map<String, String> chargeAndTaxDetailMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int colSize;
	protected int index;
	protected String colmHdrsSearchResult_chargeDetail;
	protected String mapkeys_chargeDetail;
	protected String colmHdrsSearchResult_taxInfo;
	protected String mapkeys_taxInfo;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	DataVerificationHelper dataVerHelOb = new DataVerificationHelper();

	/**
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public ChargeAndTaxDetail( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		chargeAndTaxDetailExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( chargeAndTaxDetailExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{

		mapkeys_chargeDetail = ExcelHolder.getKey( map, "MapRowKeys_ChargeDetail" );
		colmHdrsSearchResult_chargeDetail = ExcelHolder.getKey( map, "ColmnHdrs_ChargeDetail" );
		mapkeys_taxInfo = ExcelHolder.getKey( map, "MapRowKeys_TaxInformation" );
		colmHdrsSearchResult_taxInfo = ExcelHolder.getKey( map, "ColmnHdrs_TaxInformation" );

	}

	// Method: Validate the search results of 'Charged And Tax Detail' 
	public void validateChargedAndTaxDetailSearchResult( String fileName ,String chargedItem) throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{

			chargeAndTaxDetailMap = excelHolderObj.dataMap( index );
			initializeVariable( chargeAndTaxDetailMap );
			GenericHelper.waitTime( 10, "Waiting for Roaming Records" );
			dataVerHelOb.validateDataWithoutSorting( "PS_Detail_RoamFlStatus_chargeAndTaxDetail_chgDet_gridId", "grid_column_header_undefined_", chargeAndTaxDetailMap, colmHdrsSearchResult_chargeDetail, mapkeys_chargeDetail, false );
			if ( ValidationHelper.isNotEmpty( colmHdrsSearchResult_taxInfo ) )
				dataVerHelOb.validateDataWithoutSorting( "PS_Detail_RoamFlStatus_chargeAndTaxDetail_taxInfo_gridId", "grid_column_header_undefined_", chargeAndTaxDetailMap, colmHdrsSearchResult_taxInfo, mapkeys_taxInfo, false );
			Log4jHelper.logInfo( "\n'Charge And Tax Detail popup' results are validated successfully for file name -'" + fileName + "' of chargedItem " + chargedItem );

		}

	}
}
