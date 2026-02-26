package com.subex.rocps.automation.helpers.application.bills;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bills.salestax.SalesTaxGroupImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class SalesTaxGroup extends PSAcceptanceTest
{
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> salesTaxExcel = null;
	protected Map<String, String> salesGrpMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String name;
	protected String country;
	String salesTaxName;
	String taxOnTax;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	SalesTaxGroupImpl taxGroupObj = new SalesTaxGroupImpl();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	@Test
	public SalesTaxGroup( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
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
	public SalesTaxGroup( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
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
	 * Configuring the Sales Tax Group
	 * 
	 * @method : isSalesTaxGroupPresent returns false then Sales Tax Group is
	 * configured isSalesTaxGroupPresent returns true then Sales Tax Group is not
	 * configured
	 */

	public void salesTaxGroupCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Sales Tax Group" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				salesGrpMap = excelHolderObj.dataMap( paramVal );
				assertEquals( NavigationHelper.getScreenTitle(), "Sales Tax Group Search" );
				initializeVariables( salesGrpMap );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean issalesTaxGroupPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_salesTaxGroup_name_txtID", name, "Name" );

				if ( !issalesTaxGroupPresent )
				{

					newSalesTaxGroup();

					assertTrue( GridHelper.isValuePresent( "Detail_salesTaxgroup_gridID", name, "Name" ), "Sales Tax Group is not configured" );
					Log4jHelper.logInfo( "Sales Tax Group is created successfully with name " + name );
				}
				else
				{
					Log4jHelper.logInfo( "Sales Tax Group is available with name " + name );
				}
			}
		}
		catch ( Exception e )
		{

			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*Method is for edit the salestaxgroup*/
	public void salesTaxGroupEdit() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Sales Tax Group" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				salesGrpMap = excelHolderObj.dataMap( paramVal );
				assertEquals( NavigationHelper.getScreenTitle(), "Sales Tax Group Search" );
				initializeVariables( salesGrpMap );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean issalesTaxGroupPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_salesTaxGroup_name_txtID", name, "Name" );

				if ( issalesTaxGroupPresent )
				{
					int row = GridHelper.getRowNumber( "SearchGrid", name, "Name" );
					NavigationHelper.navigateToEdit( "SearchGrid", row );
					editSalesTaxGroup();
					Log4jHelper.logInfo( "Sales Tax Group is updated successfully with name " + name );
				}
				else
				{
					Log4jHelper.logInfo( "Sales Tax Group is not available with name " + name );
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
	 * This method is for new Sales Tax Group creation.
	 */

	protected void newSalesTaxGroup() throws Exception
	{

		taxGroupObj.createNew( clientPartition );
		taxGroupObj.basicDetails( name, country );
		taxGroupObj.addSalesTax( salesTaxName, taxOnTax );
		taxGroupObj.saveSalesTaxGroup( name );

	}

	/*
	 * This method is for edit Sales Tax Group .
	 */

	protected void editSalesTaxGroup() throws Exception
	{

		taxGroupObj.editbasicDetails( name, country );
		taxGroupObj.addSalesTax( salesTaxName, taxOnTax );
		taxGroupObj.saveSalesTaxGroup( name );

	}
	/*
	 * This method is to initialize instance variable
	 */

	protected void initializeVariables( Map<String, String> Map ) throws Exception
	{
		name = ExcelHolder.getKey( Map, "Name" );
		country = ExcelHolder.getKey( Map, "Country" );
		clientPartition = ExcelHolder.getKey( Map, "Partition" );
		salesTaxName = ExcelHolder.getKey( salesGrpMap, "SalesTaxName" );
		taxOnTax = ExcelHolder.getKey( salesGrpMap, "TaxOnTax" );
	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Sales Tax Group" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			salesGrpMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( salesGrpMap, "SearchScreenColumns" );
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
	 * This method is for Sales Tax Group deletion
	 */
	public void salesTaxGrpDelete() throws Exception
	{
		NavigationHelper.navigateToScreen( "Sales Tax Group" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			salesGrpMap = excelHolderObj.dataMap( paramVal );
			name = ExcelHolder.getKey( salesGrpMap, "Name" );
			clientPartition = ExcelHolder.getKey( salesGrpMap, "Partition" );

			genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );

			boolean issalesTaxGroupPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_salesTaxGroup_name_txtID", name, "Name" );

			if ( issalesTaxGroupPresent )
			{
				taxGroupObj.clickDeleteAction( name );
				genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "Sales Tax Group is deleted successfully :" + name );

			}
			else
			{
				Log4jHelper.logInfo( "Sales Tax Group is not available with :" + name );
			}

		}
	}

	/*
	 * This method is for Sales Tax Group un delete
	 */
	public void salesTaxGrpUnDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Sales Tax Group" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			salesGrpMap = excelHolderObj.dataMap( paramVal );
			name = ExcelHolder.getKey( salesGrpMap, "Name" );
			clientPartition = ExcelHolder.getKey( salesGrpMap, "Partition" );
			genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );

			boolean issalesTaxGroupPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_salesTaxGroup_name_txtID", name, "Name" );

			if ( issalesTaxGroupPresent )
			{
				taxGroupObj.clickUnDeleteAction( name );
				genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "Sales Tax Group is un deleted successfully :" + name );

			}
			else
			{
				Log4jHelper.logInfo( "Sales Tax Group is not available with :" + name );
			}

		}
	}
}