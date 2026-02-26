package com.subex.rocps.automation.helpers.application.tariffs.ratesheetimportrequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;

public class RateValidationImpl extends PSAcceptanceTest
{
	Map<String, String> map = null;
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();
	PSGenericHelper genericHelperObj = new PSGenericHelper();
	PSStringUtils psStringObj = new PSStringUtils();
	String tariffName;
	String tariffClassName;
	String bands;
	String tariffcolHeaders;
	String tariffresults;
	String fromElements;
	String toElements;
	String bandcolHeaders;
	String bandresults;
	String type;
	String elementSets;
	String elementSetTypes;
	String currency;
	String rateColHeaders;
	String rateResults;
	String rateDefinitions;
	String trn;

	public RateValidationImpl( Map<String, String> map ) throws Exception
	{
		this.map = map;
		initializeVariablesRate( map );

	}

	private void OpenRateDetailsAction() throws Exception
	{
		NavigationHelper.navigateToAction( "Open Rate Details" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "Rate Details" );
	}

	public void rateDetails() throws Exception
	{

		GridHelper.clickRow( "fastEntryGrid", bands, "Band Name" );
		OpenRateDetailsAction();

		assertEquals( TextBoxHelper.getValue( "band.bndName" ), bands );
		assertEquals( ComboBoxHelper.getValue( "dummyCurrency_gwt_uid_" ), currency );

		String[] rateResultsArr = psStringObj.stringSplitFirstLevel( rateResults );
		String[] trnArr = psStringObj.stringSplitFirstLevel( trn );
		for ( int i = 0; i < rateResultsArr.length; i++ )
		{
			String colHeader = trnArr[i];
			rateValidation( rateResultsArr[i], colHeader );

		}
		if ( ValidationHelper.isNotEmpty( rateDefinitions ) )
			rateDefinitions();
	}

	private void rateValidation( String rateResultsArr, String col ) throws Exception
	{
		String[] ratecolHeadersArr = psStringObj.stringSplitFirstLevel( rateColHeaders );

		ArrayList<String> value = new ArrayList<String>();
		ArrayList<String> excelVal = new ArrayList<String>();
		ArrayList<String> rowHeadersVal = new ArrayList<String>();
		for ( int row = 0; row < ratecolHeadersArr.length; row++ )
		{
			String cellValue = GridHelper.getCellValue( "FastEntry_RateDetails_Properties_Grid", row + 1, col );
			value.add( cellValue );
			rowHeadersVal.add( ratecolHeadersArr[row] );
		}
		ArrayList<String> rowHeaderActualVal = GridHelper.getColumnValues( "FastEntry_RateDetails_Properties_Grid", 1 );
		String rowActualHeaders = psStringObj.stringformation( rowHeaderActualVal );
		String rowExcelHeaders = psStringObj.stringformation( rowHeadersVal );
		excelVal.add( rateResultsArr );
		String actualValue = psStringObj.stringformation( value );
		String excelValue = psStringObj.stringformation( excelVal );
		Log4jHelper.logInfo( "Band Rate Details Row Headers: " + rowActualHeaders );
		Log4jHelper.logInfo( "Band Rate Details Row Headers: " + rowExcelHeaders );
		assertEquals( rowActualHeaders, rowExcelHeaders, "Row header values are not matching" );
		Log4jHelper.logInfo( "Band Rate Details Actual Value: " + actualValue );
		Log4jHelper.logInfo( "Band Rate Details Excel Value: " + excelValue );
		assertEquals( actualValue, excelValue, "Values are not matching " );
	}

	private void rateDefinitions() throws Exception
	{
		ArrayList<String> rateDenExcelVal = new ArrayList<String>();
		String[] rateDefinitionsArr = psStringObj.stringSplitFirstLevel( rateDefinitions );
		for ( int i = 0; i < rateDefinitionsArr.length; i++ )
		{
			ArrayList<String> rateDefnVal = GridHelper.getRowValues( "FastEntry_RateDetails_RateDefinitions_Grid", i + 1 );
			
			rateDefnVal.remove( 0 );
			
			rateDenExcelVal.add( rateDefinitionsArr[i] );
			
			
			String rateDefnActual = psStringObj.stringformation( rateDefnVal );
			String rateDefnExcel = psStringObj.stringformation( rateDenExcelVal );
			rateDefnActual.substring( 0, 49 );
			rateDefnExcel.substring( 0, 49 );
			Log4jHelper.logInfo( "Band Rate Definitions Actual Value: " + rateDefnVal );
			Log4jHelper.logInfo( "Band Rate Definitions Excel Value: " + rateDenExcelVal );
			
			Log4jHelper.logInfo( "Band Rate Definitions Actual Value: " + rateDefnActual );
			Log4jHelper.logInfo( "Band Rate Definitions Excel Value: " + rateDefnActual );
			assertEquals( rateDefnActual, rateDefnExcel, "Rate Definition values are not matching" );
		}
	}

	public void initializeVariablesRate( Map<String, String> map ) throws Exception
	{
		bands = ExcelHolder.getKey( map, "Bands" );
		currency = ExcelHolder.getKey( map, "Currency" );
		rateColHeaders = ExcelHolder.getKey( map, "RateColHeaders" );
		rateResults = ExcelHolder.getKey( map, "RateResults" );
		rateDefinitions = ExcelHolder.getKey( map, "RateDefinitions" );
		trn = ExcelHolder.getKey( map, "TRN" );

	}
}
