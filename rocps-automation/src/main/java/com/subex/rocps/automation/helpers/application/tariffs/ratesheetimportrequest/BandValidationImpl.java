package com.subex.rocps.automation.helpers.application.tariffs.ratesheetimportrequest;

import org.jsoup.select.Evaluator.IsEmpty;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class BandValidationImpl extends PSAcceptanceTest
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

	public BandValidationImpl( Map<String, String> map ) throws Exception
	{
		this.map = map;
		initializeVariablesTariffBand( map );
	}

	public void openBandAction() throws Exception
	{
		NavigationHelper.navigateToAction( "Band Actions", "Open Band" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "Edit Band" );
	}

	public void tariffBandValidation() throws Exception
	{
		TextBoxHelper.type( "FastEntry_BandName", bands );
		ButtonHelper.click( "FastEntry_Search" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		int row = GridHelper.getRowNumber( "FastEntry_Grid", bands, "Band Name" );
		if ( row > 0 )
		{
			dataVerifyObj.validateData( "grid_column_header_undefined_", tariffcolHeaders, "FastEntry_Grid", tariffresults );
			Log4jHelper.logInfo( "Tariff Bands are validated sucessfully for  :" + tariffName );
		}
		else
			FailureHelper.failTest( "Specified band is not available for this tariff" + tariffName );
	}
	public void bandValidation() throws Exception
	{

		GridHelper.clickRow( "fastEntryGrid", bands, "Band Name" );
		openBandAction();
		assertEquals( TextBoxHelper.getValue( "Bands_Name" ), bands, "Band Names are not matching" );
		if ( ValidationHelper.isNotEmpty( type ) )
			assertEquals( ComboBoxHelper.getValue( "Bands_BandType" ), type, "Band Type is not matching" );
		if ( ValidationHelper.isNotEmpty( elementSets ) )
			assertTrue( GridHelper.isValuePresent( "Bands_ElementSet_Grid", elementSets, "Element Set" ), "Element set value is not matching" );
		if ( ValidationHelper.isNotEmpty( elementSetTypes ) )
			assertTrue( GridHelper.isValuePresent( "Bands_ElementSet_Grid", elementSetTypes, "Type" ), "Element set value is not matchging" );

		elementsValidation();
		closeBand();
		Log4jHelper.logInfo( "Band elements are validated successfully" );
	}

	private void elementsValidation() throws Exception
	{
		if ( ValidationHelper.isNotEmpty( toElements ) )
		{			
			boolean isPresent = GridHelper.isValuePresent( "Bands_ElementConnection_Grid", toElements, "To Element Name" );
			/*GridHelper.sortGrid( "elementConnectionGridModel", "To  Element Digits" );
			GridHelper.sortGrid( "elementConnectionGridModel", "To  Element Digits" );*/
			if ( isPresent )
			{
				int row = GridHelper.getRowCount( "elementConnectionGridModel" );
				for (int i =0; i < row ;i++)
				{
				ArrayList<String> val = GridHelper.getRowValues( "elementConnectionGridModel", i+1 );
				String actualVal = psStringObj.stringformation( val );
				System.out.println( "Element grid Actual Values " +actualVal);
				}

				//dataVerifyObj.validateData( "elementConnectionGridModel", "grid_column_header_undefined_", map, "elementConnectionGridModel", bandcolHeaders, bandresults );
			
			}else
				FailureHelper.failTest( "Elements are not presnet for this band" );
		}
	}

	public void closeBand() throws Exception
	{
		ButtonHelper.click( "bandDetail.cancel" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "Edit Fast Entry" );

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
		
		ButtonHelper.click( "CancelButton" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
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
		Log4jHelper.logInfo( "Band Rate Details Actual Row Headers: " + rowActualHeaders );
		Log4jHelper.logInfo( "Band Rate Details Excel Row Headers: " + rowExcelHeaders );
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
			rateDenExcelVal.clear();
			rateDefnVal.remove( 0 );
			
			rateDenExcelVal.add( rateDefinitionsArr[i] );	
			
			
			for(int j=0;j<=rateDefnVal.size();j++)
			{
			int index = rateDefnVal.size()-1;		
			String lastindex = rateDefnVal.get( index );
			if(lastindex.contains( "" ))
				rateDefnVal.remove( index );
			}
			
			String rateDefnActual = psStringObj.stringformation( rateDefnVal );
			String rateDefnExcel = psStringObj.stringformation( rateDenExcelVal );					
			Log4jHelper.logInfo( "Band Rate Definitions Actual Value: " + rateDefnActual );
			Log4jHelper.logInfo( "Band Rate Definitions Excel Value: " + rateDefnExcel );
			assertEquals( rateDefnActual, rateDefnExcel, "Rate Definition values are not matching" );
		}
	}
	


	public void initializeVariablesTariffBand( Map<String, String> map ) throws Exception
	{
		tariffName = ExcelHolder.getKey( map, "TariffName" );
		tariffClassName = ExcelHolder.getKey( map, "TariffClassName" );
		bands = ExcelHolder.getKey( map, "Bands" );
		tariffcolHeaders = ExcelHolder.getKey( map, "TariffColHeaders" );
		tariffresults = ExcelHolder.getKey( map, "TariffResults" );
		//fromElements = ExcelHolder.getKey( map, "From Element" );
		toElements = ExcelHolder.getKey( map, "To Element" );
		bandcolHeaders = ExcelHolder.getKey( map, "BandcolHeaders" );
		bandresults = ExcelHolder.getKey( map, "Bandresults" );
		type = ExcelHolder.getKey( map, "Type" );
		elementSets = ExcelHolder.getKey( map, "Element Set" );
		elementSetTypes = ExcelHolder.getKey( map, "Element Set Type" );

		bands = ExcelHolder.getKey( map, "Bands" );
		currency = ExcelHolder.getKey( map, "Currency" );
		rateColHeaders = ExcelHolder.getKey( map, "RateColHeaders" );
		rateResults = ExcelHolder.getKey( map, "RateResults" );
		rateDefinitions = ExcelHolder.getKey( map, "RateDefinitions" );
		trn = ExcelHolder.getKey( map, "TRN" );
		
	}
	
	
	
}
