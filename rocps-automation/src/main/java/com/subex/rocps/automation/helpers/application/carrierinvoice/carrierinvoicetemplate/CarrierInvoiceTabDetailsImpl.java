package com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoicetemplate;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.componentHelpers.LabelElementHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;

public class CarrierInvoiceTabDetailsImpl extends PSAcceptanceTest
{

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> usgExcel = null;
	protected Map<String, String> usgMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String usage;
	protected String serviceName;
	protected String tariffRateName;;
	protected String band;
	protected String currency;
	protected String billedUsage;
	protected String rate;
	protected String txnAmt;
	protected String setupAmt;
	protected String fromdate;
	protected String date;
	protected String toDate;
	protected String usgColHeaders;
	protected String gridColumnsUsg;
	protected String searchableColHeaders;
	protected String searchableColumns;
	protected String count;
	protected String load;
	protected String loadColValues;
	protected String loadColHeaders;
	protected String[] countArr;
	protected String[] mapGridColHeaders;
	protected String[] bandArr;
	protected String[] serviceArr;
	protected String[] currencyArr;
	protected String[] usageArr;
	protected String[] rateArr;
	protected String[] txnAmtArr;
	protected String[] setupAmtArr;
	protected String[] fromDateArr;
	protected String[] toDateArr;
	protected String[] dateArr;
	protected String[] trnArr;
	protected String[] searchableColArr;
	protected String[] billedUsageArr;
	protected String[] searchableColHeadersArr;

	PSStringUtils strObj = new PSStringUtils();
	protected Map<String, String> map;
	CarrierInvoiceTemplateActionImpl ciActionObj = new CarrierInvoiceTemplateActionImpl();
	PSGenericHelper genericObj = new PSGenericHelper();

	public CarrierInvoiceTabDetailsImpl( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		excelData = new ExcelReader();
		usgExcel = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( usgExcel );
		colSize = excelHolderObj.totalColumns();
	}

	public void usagTabConfiguration() throws Exception
	{
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			usgMap = excelHolderObj.dataMap( paramVal );
			initialiseVariables( usgMap );
			initalizeArray();
			String addBtnID = usage + "Toolbar.Add";
			String gridID = usage + "grid";
			tabConfig( addBtnID, gridID );
		}
	}

	public void tabConfig( String addBtnID, String gridID ) throws Exception
	{
		basicConfig();
		mappingConfig( addBtnID, gridID );

	}

	public void basicConfig() throws Exception
	{
		if ( !searchableColHeaders.isEmpty() )
		{
			for ( int i = 0; i < searchableColHeadersArr.length; i++ )
			{
				if ( !searchableColArr[i].isEmpty() && searchableColHeadersArr[i].contains( "Service" ) )
					searchableGridServiceAndBand( searchableColHeadersArr[i], "psvcName", searchableColArr[i], "Service Search" );

				if ( !searchableColArr[i].isEmpty() && textElementPresent( searchableColHeadersArr[i] ) && searchableColHeadersArr[i].contains( "Tariff Rate Name" ) )
					TextBoxHelper.type( searchableColHeadersArr[i], searchableColArr[i] );
				else if ( !searchableColArr[i].isEmpty() && comboElementPresent( "Tariff_Rate_Name" ) && searchableColHeadersArr[i].contains( "Tariff Rate Name" ) )
					ComboBoxHelper.select( "Tariff_Rate_Name_gwt_uid_", searchableColArr[i] );

				if ( !searchableColArr[i].isEmpty() && textElementPresent( searchableColHeadersArr[i] ) && searchableColHeadersArr[i].contains( "Band" ) )
					searchableGridServiceAndBand( searchableColHeadersArr[i], "bndName", searchableColArr[i], "Band Search" );
				if ( !searchableColArr[i].isEmpty() && textElementPresent( searchableColHeadersArr[i] ) && searchableColHeadersArr[i].contains( "Currency" ) )
					TextBoxHelper.type( searchableColHeadersArr[i], searchableColArr[i] );
				else if ( !searchableColArr[i].isEmpty() && comboElementPresent( "currencyEditor" ) && searchableColHeadersArr[i].contains( "Currency" ) )
					ComboBoxHelper.select( "currencyEditor_gwt_uid_", searchableColArr[i] );
			}
			ButtonHelper.click( usage + "search" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			if ( PopupHelper.isPresent( "window-scroll-panel" ) )
				ButtonHelper.click( "OKButton" );
		}
	}

	public void searchableGridServiceAndBand( String colHeader, String txtBxID, String value, String entityScreenTitle ) throws Exception
	{
		if ( textElementPresent( colHeader ) )
			TextBoxHelper.type( colHeader, value );
		else if ( ElementHelper.isElementPresent( "//div[contains(@id,'trigger')]" ) )
			PSEntityComboHelper.selectUsingGridFilterTextBox( colHeader, entityScreenTitle, txtBxID, colHeader, "Name" );
	}

	public boolean textElementPresent( String labelName ) throws Exception
	{

		boolean flag = ElementHelper.isElementPresent( "//input[contains(@id,'" + labelName + "')]" );
		return flag;
	}

	public boolean comboElementPresent( String labelName ) throws Exception
	{
		String locator = "//div[contains(@id," + labelName + "_gwt_uid_)]";
		boolean flag = ElementHelper.isElementPresent( locator );
		return flag;
	}

	public void mappingConfig( String addBtn, String gridID ) throws Exception
	{

		gridColumnsValidation();
		if ( !loadColValues.isEmpty() )
			loadConfig( gridID );
		else
		{
			for ( int row = 0; row < bandArr.length; row++ )
			{
				ButtonHelper.click( addBtn );
				GenericHelper.waitForLoadmask( detailScreenWaitSec );
				for ( int col = 0; col < mapGridColHeaders.length; col++ )
				{
					if ( mapGridColHeaders[col].contains( "Service" ) && !serviceName.isEmpty() )
					{
						GridHelper.clickRow( gridID, row + 1, mapGridColHeaders[col] );
						serviceSelection( gridID, mapGridColHeaders[col], serviceArr[row], row, mapGridColHeaders[col], "psvcName" );
					}
					if ( mapGridColHeaders[col].contains( "Band" ) && !band.isEmpty() )
					{
						GridHelper.clickRow( gridID, row + 1, mapGridColHeaders[col] );
						serviceSelection( gridID, mapGridColHeaders[col], bandArr[row], row, mapGridColHeaders[col], "bndName" );
					}
					if ( mapGridColHeaders[col].contentEquals( "Currency" ) && !currency.isEmpty() )
						currencySelection( gridID, mapGridColHeaders[col], currencyArr[row], row );
					if ( mapGridColHeaders[col].contains( "Usage" ) && !billedUsage.isEmpty() && !billedUsageArr[row].isEmpty() )
						GridHelper.updateGridTextBox( gridID, mapGridColHeaders[col], row + 1, mapGridColHeaders[col], billedUsageArr[row] );
					if ( mapGridColHeaders[col].contentEquals( "Rate" ) && !rate.isEmpty() && !rateArr[row].isEmpty() )
						GridHelper.updateGridTextBox( gridID, mapGridColHeaders[col], row + 1, mapGridColHeaders[col], rateArr[row] );

					if ( mapGridColHeaders[col].contains( "Setup Amount" ) && !setupAmt.isEmpty() && !setupAmtArr[row].isEmpty() )
						GridHelper.updateGridTextBox( gridID, mapGridColHeaders[col], row + 1, mapGridColHeaders[col], setupAmtArr[row] );
					if ( mapGridColHeaders[col].contains( "From Date" ) && !fromdate.isEmpty() && !fromDateArr[row].isEmpty() )
						GridHelper.updateGridTextBox( gridID, "trigger-From Date", row + 1, mapGridColHeaders[col], fromDateArr[row] );
					if ( mapGridColHeaders[col].contains( "To Date" ) && !toDate.isEmpty() && !toDateArr[row].isEmpty() )
						GridHelper.updateGridTextBox( gridID, "trigger-To Date", row + 1, mapGridColHeaders[col], toDateArr[row] );
					if ( mapGridColHeaders[col].contains( "Date" ) && !date.isEmpty() && !dateArr[row].isEmpty() )
						GridHelper.updateGridTextBox( gridID, "trigger-Date", row + 1, mapGridColHeaders[col], dateArr[row] );

					if ( mapGridColHeaders[col].contains( "Count" ) && !count.isEmpty() && !countArr[row].isEmpty() )
						GridHelper.updateGridTextBox( gridID, mapGridColHeaders[col], row + 1, mapGridColHeaders[col], countArr[row] );

					if ( mapGridColHeaders[col].contains( "Tariff Rate Name" ) && !tariffRateName.isEmpty() )
						trnSelection( gridID, mapGridColHeaders[col], trnArr[row], row );
					if ( mapGridColHeaders[col].contains( "Transaction" ) && !txnAmt.isEmpty() && !txnAmtArr[row].isEmpty() )
						GridHelper.updateGridTextBox( gridID, mapGridColHeaders[col], row + 1, mapGridColHeaders[col], txnAmtArr[row] );

				}

			}

		}
	}

	public void loadConfig( String gridID ) throws Exception
	{
		ButtonHelper.click( "USGToolbar.Load" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		if ( PopupHelper.isPresent() )
			ButtonHelper.click( "OKButton" );
		DataVerificationHelper dataObj = new DataVerificationHelper();
		dataObj.validateData( "grid_column_header_undefined_", usgMap, gridID, loadColHeaders, loadColValues );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	public void trnSelection( String gridID, String colHeader, String value, int row ) throws Exception
	{
		if ( !value.isEmpty() )
		{
			GridHelper.clickRow( gridID, row + 1, colHeader );
			if ( comboElementPresent( "Tariff_Rate_Name" ) )
			{
				if ( !ComboBoxHelper.isPresent( "Tariff_Rate_Name_gwt_uid_" ) )
					GridHelper.clickRow( gridID, row + 1, colHeader );
				ComboBoxHelper.select( "Tariff_Rate_Name_gwt_uid_", value );
				GridHelper.click( gridID );
			}
			else if ( textElementPresent( colHeader ) )
				entityTextBoxSelection( gridID, colHeader, colHeader, value, row + 1 );

		}
	}

	public void currencySelection( String gridID, String colHeader, String value, int row ) throws Exception
	{
		if ( !value.isEmpty() )
		{
			GridHelper.clickRow( gridID, row + 1, colHeader );
			if ( comboElementPresent( "currencyEditor" ) )
			{
				if ( !ComboBoxHelper.isPresent( "currencyEditor_gwt_uid_" ) )
					GridHelper.clickRow( gridID, row + 1, colHeader );
				ComboBoxHelper.select( "currencyEditor_gwt_uid_", value );
				GridHelper.click( gridID );
			}
			else if ( textElementPresent( colHeader ) )
				entityTextBoxSelection( gridID, colHeader, colHeader, value, row + 1 );

		}
	}

	public void serviceSelection( String gridID, String colHeader, String value, int row, String entityID, String txtbxID ) throws Exception
	{
		if ( !value.isEmpty() )
		{
			//GridHelper.clickRow( gridID, row + 1, colHeader );
			GenericHelper.waitForLoadmask( detailScreenWaitSec );
			if ( ElementHelper.isElementPresent( "//div[contains(@id,'trigger')]" ) )
			{

				entityComboSelection( gridID, entityID, txtbxID, value, colHeader, "Name", row + 1 );
			}
			else if ( textElementPresent( colHeader ) )
				entityTextBoxSelection( gridID, colHeader, colHeader, value, row + 1 );

		}
	}

	public void entityTextBoxSelection( String gridID, String textBoxID, String columnName, String value, int row ) throws Exception
	{
		if ( !TextBoxHelper.isPresent( textBoxID ) )
			GridHelper.clickRow( gridID, row, columnName );
		TextBoxHelper.type( textBoxID, value );
		GridHelper.click( gridID );
	}

	public void entityComboSelection( String gridID, String entityID, String txtID, String value, String entityColHeader, String colHeader, int row ) throws Exception
	{
		GridHelper.updateGridEntityCombo( gridID, entityID, row, entityColHeader, value );
		SearchGridHelper.gridFilterSearchWithTextBox( "popupWindow", txtID, value, colHeader );
		GridHelper.clickRow( "SearchGrid", value, colHeader );
		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	/*
	 * this method is to validate internal grids
	 */
	private void gridColumnsValidation() throws Exception
	{

		ArrayList<String> excelColumnNames = new ArrayList<String>();
		String[] searchGridColumnsArr = strObj.stringSplitFirstLevel( gridColumnsUsg );
		for ( int col = 0; col < searchGridColumnsArr.length; col++ )
		{
			excelColumnNames.add( searchGridColumnsArr[col] );
		}
		String excelColName = excelColumnNames.get( 0 );
		if ( excelColName.isEmpty() )
			excelColumnNames.remove( 0 );
		genericObj.totalColumns( excelColumnNames, "usageGrid", "grid_column_header_undefined_" );
	}

	public void usageTabValidation() throws Exception
	{
		for ( paramVal = 0; paramVal < colSize; paramVal++ )

		{
			usgMap = excelHolderObj.dataMap( paramVal );
			usage = ExcelHolder.getKey( usgMap, "Usage" );
			String colHeader = ExcelHolder.getKey( usgMap, "ColHeaders" );
			String result = ExcelHolder.getKey( usgMap, "Result" );
			String gridID = usage + "grid";
			DataVerificationHelper verifyObj = new DataVerificationHelper();
			verifyObj.validateData( "grid_column_header_undefined_", usgMap, gridID, colHeader, result );
			Log4jHelper.logInfo( "Carrier Invoice imported data is validated successfully" );
		}
	}

	public void initalizeArray() throws Exception
	{
		mapGridColHeaders = strObj.stringSplitFirstLevel( gridColumnsUsg );
		bandArr = strObj.stringSplitFirstLevel( band );
		serviceArr = strObj.stringSplitFirstLevel( serviceName );
		currencyArr = strObj.stringSplitFirstLevel( currency );
		billedUsageArr = strObj.stringSplitFirstLevel( billedUsage );
		rateArr = strObj.stringSplitFirstLevel( rate );
		txnAmtArr = strObj.stringSplitFirstLevel( txnAmt );
		setupAmtArr = strObj.stringSplitFirstLevel( setupAmt );
		fromDateArr = strObj.stringSplitFirstLevel( fromdate );
		toDateArr = strObj.stringSplitFirstLevel( toDate );
		dateArr = strObj.stringSplitFirstLevel( date );
		trnArr = strObj.stringSplitFirstLevel( tariffRateName );
		searchableColArr = strObj.stringSplitFirstLevel( searchableColumns );
		searchableColHeadersArr = strObj.stringSplitFirstLevel( searchableColHeaders );
		countArr = strObj.stringSplitFirstLevel( count );
	}

	public void initialiseVariables( Map<String, String> map ) throws Exception
	{
		usage = ExcelHolder.getKey( map, "Usage" );
		serviceName = ExcelHolder.getKey( map, "ServiceName" );
		tariffRateName = ExcelHolder.getKey( map, "TariffRateName" );
		band = ExcelHolder.getKey( map, "Band" );
		currency = ExcelHolder.getKey( map, "Currency" );
		billedUsage = ExcelHolder.getKey( map, "BilledUsage" );
		rate = ExcelHolder.getKey( map, "Rate" );
		txnAmt = ExcelHolder.getKey( map, "TxnAmt" );
		setupAmt = ExcelHolder.getKey( map, "SetupAmt" );
		fromdate = ExcelHolder.getKey( map, "FromDate" );
		date = ExcelHolder.getKey( map, "Date" );
		toDate = ExcelHolder.getKey( map, "ToDate" );
		searchableColumns = ExcelHolder.getKey( map, "SearchableColumns" );
		gridColumnsUsg = ExcelHolder.getKey( map, "GridColumnsUsg" );
		searchableColHeaders = ExcelHolder.getKey( map, "SearchableColHeaders" );
		count = ExcelHolder.getKey( map, "Count" );
		loadColHeaders = ExcelHolder.getKey( map, "LoadColHeaders" );
		loadColValues = ExcelHolder.getKey( map, "LoadColumnValues" );
	}
}
