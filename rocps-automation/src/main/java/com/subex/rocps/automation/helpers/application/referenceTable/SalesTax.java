package com.subex.rocps.automation.helpers.application.referenceTable;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class SalesTax extends PSAcceptanceTest
{
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> salesTaxExcel = null;
	protected Map<String, String> salesRateMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String tableName;
	protected String salesTaxName;
	protected String taxName;
	protected String code;
	protected String country;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	@Test
	public SalesTax( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		salesTaxExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( salesTaxExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public SalesTax( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		salesTaxExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( salesTaxExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Sales Tax
	 * 
	 */

	public void salesTaxCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToReferenceTable( "Sales Tax" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				salesRateMap = excelHolderObj.dataMap( paramVal );
				initializeVariables( salesRateMap );
				//ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isSalesTaxPresent = GridHelper.isValuePresent( "SearchGrid", salesTaxName, "Name" );
				if ( !isSalesTaxPresent )
				{
					newSalesTax();
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					Log4jHelper.logInfo( "Sales Tax is created successfully with name " + salesTaxName );
				}
				else
				{
					Log4jHelper.logInfo( "Sales Tax is available with name " + salesTaxName );
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
	 * Configuring the Sales Tax
	 * 
	 */

	public void salesTaxEdit() throws Exception
	{
		try
		{
			NavigationHelper.navigateToReferenceTable( "Sales Tax" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				salesRateMap = excelHolderObj.dataMap( paramVal );
				initializeVariables( salesRateMap );
				//ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isSalesTaxPresent = GridHelper.isValuePresent( "SearchGrid", salesTaxName, "Name" );
				if ( isSalesTaxPresent )
				{
					int row = GridHelper.getRowNumber( "SearchGrid", salesTaxName, "Name" );
					NavigationHelper.navigateToEdit( "SearchGrid", row );
					editSalesTax();
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					Log4jHelper.logInfo( "Sales Tax is updated successfully with name '" + salesTaxName );
				}
				else
				{
					Log4jHelper.logInfo( "Sales Tax is not  available with name " + salesTaxName );
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
	 * This method is for new Sales Tax creation.
	 */

	protected void newSalesTax() throws Exception
	{

		createNew();
		GenericHelper.waitForLoadmask();
		configSalesTax();
		assertEquals( NavigationHelper.getScreenTitle(), "New Sales Tax" );
		genericHelperObj.detailSave( "PS_details_tax_save", salesTaxName, "Name" );

	}

	/*
	 * This method is for configure Sales Tax .
	 */
	private void configSalesTax() throws Exception
	{
		TextBoxHelper.type( "PS_details_tax_code", code );
		TextBoxHelper.type( "PS_Details_tax_salesTax", taxName );
		ComboBoxHelper.select( "PS_details_tax_country", country );
	}

	/*
	 * This method is for edit Sales Tax creation.
	 */

	protected void editSalesTax() throws Exception
	{

		assertEquals( NavigationHelper.getScreenTitle(), "Edit Sales Tax" );
		configSalesTax();
		genericHelperObj.detailSave( "PS_details_tax_save", salesTaxName, "Name" );
	}

	/*
	 * This Method is for creating new Sales Tax
	 */
	protected void createNew() throws Exception
	{
		genericHelperObj.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask();

	}
	/*
	 * This method is to save sales Tax
	 */

	protected void saveSalesTax() throws Exception
	{
		ButtonHelper.click( "PS_details_tax_save" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "Reference Table Search" );
	}

	/*
	 * This method is to initialize instance variable
	 */

	protected void initializeVariables( Map<String, String> salesTaxMap ) throws Exception
	{
		salesTaxName = ExcelHolder.getKey( salesTaxMap, "SalesTaxName" );
		tableName = ExcelHolder.getKey( salesTaxMap, "TableName" );
		taxName = ExcelHolder.getKey( salesTaxMap, "SalesTaxName" );
		code = ExcelHolder.getKey( salesTaxMap, "Code" );
		country = ExcelHolder.getKey( salesTaxMap, "Country" );
		clientPartition = ExcelHolder.getKey( salesTaxMap, "Partition" );
	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "Sales Tax" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			salesRateMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( salesRateMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			PSStringUtils strObj = new PSStringUtils();
			String[] searchGridColumnsArr = strObj.stringSplitFirstLevel( searchScreenColumns );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genericHelperObj.totalColumns( excelColumnNames );
		}

	}

	/*
	 * This method is to delete salesTax
	 */
	public void salesTaxDelete() throws Exception
	{

		NavigationHelper.navigateToReferenceTable( "Sales Tax" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			salesRateMap = excelHolderObj.dataMap( paramVal );

			clientPartition = ExcelHolder.getKey( salesRateMap, "Partition" );
			salesTaxName = ExcelHolder.getKey( salesRateMap, "SalesTaxName" );

			genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
			boolean isSalesTaxPresent = GridHelper.isValuePresent( "SearchGrid", salesTaxName, "Name" );
			if ( isSalesTaxPresent )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( salesTaxName, "Name", "Delete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", salesTaxName, "Name" ), salesTaxName );
				Log4jHelper.logInfo( "salesTax is deleted successfully : " + salesTaxName );

			}
			else
			{
				Log4jHelper.logInfo( "salesTax is not available : " + salesTaxName );
			}

		}
	}

	/*
	 * This method is to un delete salesTax
	 */
	public void salesTaxUnDelete() throws Exception
	{

		NavigationHelper.navigateToReferenceTable( "Sales Tax" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			salesRateMap = excelHolderObj.dataMap( paramVal );

			clientPartition = ExcelHolder.getKey( salesRateMap, "Partition" );
			salesTaxName = ExcelHolder.getKey( salesRateMap, "SalesTaxName" );

			genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
			GenericHelper.waitForLoadmask();
			boolean isSalesTaxPresent = GridHelper.isValuePresent( "SearchGrid", salesTaxName, "Name" );
			if ( isSalesTaxPresent )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( salesTaxName, "Name", "Undelete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", salesTaxName, "Name" ), salesTaxName );
				Log4jHelper.logInfo( "salesTax is un deleted successfully : " + salesTaxName );

			}
			else
			{
				Log4jHelper.logInfo( "salesTax is not available : " + salesTaxName );
			}

		}
	}
}
