package com.subex.rocps.automation.helpers.application.referenceTable;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class TariffType extends PSAcceptanceTest
{
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> tariffTypeExcel = null;
	protected Map<String, String> tariffTypeMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String colmHdrs;
	protected String clientPartition;
	protected String name;
	protected String code;
	protected String enableTariffType;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected PSActionImpl psActionImpl = new PSActionImpl();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	@Test
	public TariffType( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		tariffTypeExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( tariffTypeExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public TariffType( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		tariffTypeExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( tariffTypeExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for 'tariffTypee' screen common method
	 */
	private void tariffTypeScreen() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "Tariff Type" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		tariffTypeMap = excelHolderObj.dataMap( paramVal );
		ButtonHelper.click( "SearchButton" );
		genericHelperObj.waitforHeaderElement( "Name" );
	}

	/*
	 * Configuring the Tariff Type
	 * 
	 */
	public void tariffTypeCreation() throws Exception
	{
		try
		{

			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				tariffTypeScreen();
				initializeVariables( tariffTypeMap );
				if ( !isTariffTypePresent() )
				{

					psActionImpl.clickNewAction( clientPartition, "PS_Detail_TariffType_detailXpath" );
					GenericHelper.waitForLoadmask();
					newTariffType();

					ButtonHelper.click( "OK_Button_ByID" );
					GenericHelper.waitForSave();
					Log4jHelper.logInfo( "Tariff type is created successfully with name " + name );
				}
				else
				{
					Log4jHelper.logInfo( "Tariff type is available with name " + name );
				}

			}

		}
		catch ( Exception e )
		{

			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is to create new Tariff Type
	 */
	protected void newTariffType() throws Exception
	{

		TextBoxHelper.type( "PS_Detail_wrapperID", "PS_Detail_tariffType_code_txtID", code );
		TextBoxHelper.type( "PS_Detail_wrapperID", "PS_Detail_tariffType_name_txtID", name );
		ComboBoxHelper.select( "PS_Detail_tariftype_enabletarifftype_comboID", enableTariffType );

	}

	/*
	 * This method is to initialize instance variables
	 */
	protected void initializeVariables( Map<String, String> map ) throws Exception
	{

		clientPartition = ExcelHolder.getKey( map, "Partition" );
		code = ExcelHolder.getKey( map, "Code" );
		name = ExcelHolder.getKey( map, "Name" );
		enableTariffType = ExcelHolder.getKey( map, "EnableTariffType" );

	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{

		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			tariffTypeScreen();
			colmHdrs = ExcelHolder.getKey( tariffTypeMap, "SearchScreenColumns" );
			genericHelperObj.screenColumnValidation( "Name", colmHdrs, "Tariff Type" );
		}

	}

	/*
	 * This method is to check if the tarifftype is already present
	 */
	protected boolean isTariffTypePresent() throws Exception
	{

		SearchGridHelper.searchWithTextBox( "PS_Detail_tariffType_name_txtID", name, "name" );

		return GridHelper.isValuePresent( "Detail_eventDefn_gridID", name, "name" );
	}
}