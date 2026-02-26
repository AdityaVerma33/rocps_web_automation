package com.subex.rocps.automation.helpers.application.bills;

import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bills.billbreakdownconfiguration.BillBreakdownConfigActionImpl;
import com.subex.rocps.automation.helpers.application.bills.billbreakdownconfiguration.BillBreakdownConfigDetailImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class BillBreakdownConfiguration extends PSAcceptanceTest
{

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> billConfigExcel = null;
	protected Map<String, String> billConfigMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String name;
	protected String displayName;
	protected String useJoins;
	protected String tableInstance;
	protected String breakdownPrefix;
	protected String breakdownsummaryPrefix;
	protected String useDataRange;
	protected String billKeys;
	protected String lineItemKey;
	protected String visible;
	protected String filter;
	protected String disputed;
	protected String displayDP;
	protected String computedKey;
	protected String keyComputation;
	protected String billusageQueryConfig;
	protected String tableName;
	protected String billValues;
	protected String functionValue;
	protected String amountValue;
	protected String visibleValue;
	protected String disputedValue;
	protected String displayDPValue;
	protected String computedValue;
	protected String valueComputation;
	protected String ratingComponent;
	protected String ratableFlag;
	protected String billedUsage;
	protected String rate;
	protected String tariff;
	protected String count;
	protected String setupAmt;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	BillBreakdownConfigActionImpl billConfigActionObj = new BillBreakdownConfigActionImpl();
	PSStringUtils strObj = new PSStringUtils();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	public BillBreakdownConfiguration( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		billConfigExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( billConfigExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public BillBreakdownConfiguration( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		billConfigExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( billConfigExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Bill Breakdown Configuration
	 * 
	 * @method : isBillConfigPresent returns false then Bill Breakdown Configuration
	 * is configured isBillConfigPresent returns true then Bill Breakdown
	 * Configuration is not configured
	 */
	public void billConfigCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Bill Breakdown Configuration" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				billConfigMap = excelHolderObj.dataMap( paramVal );

				initializeVariables( billConfigMap );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isBillConfigPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_billConfig_name_txtID", name, "Name" );

				if ( !isBillConfigPresent )
				{
					assertEquals( NavigationHelper.getScreenTitle(), "Bill Breakdown Conf..." );
					newbillbreakdownConfig();
					GenericHelper.waitForLoadmask( searchScreenWaitSec );

					assertTrue( GridHelper.isValuePresent( "Detail_eventDefn_gridID", name, "Name" ), "Bill Breakdown Configuration is not configured" );
					Log4jHelper.logInfo( "Bill Breakdown Configuration is created successfully with name " + name );
				}
				else
				{
					Log4jHelper.logInfo( "Bill Breakdown Configuration is available with name " + name );
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
	 * Editing the Bill Breakdown Configuration
	 * 
	 * @method : isBillConfigPresent returns false then Bill Breakdown Configuration
	 * is configured isBillConfigPresent returns true then Bill Breakdown
	 * Configuration is not configured
	 */
	public void billConfigEdit() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Bill Breakdown Configuration" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				billConfigMap = excelHolderObj.dataMap( paramVal );

				initializeVariables( billConfigMap );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isBillConfigPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_billConfig_name_txtID", name, "Name" );

				if ( isBillConfigPresent )
				{
					int row = GridHelper.getRowNumber( "SearchGrid", name, "Name" );
					NavigationHelper.navigateToEdit( "SearchGrid", row );
					editbillbreakdownConfig();
					GenericHelper.waitForLoadmask( searchScreenWaitSec );

					assertTrue( GridHelper.isValuePresent( "Detail_eventDefn_gridID", name, "Name" ), "Bill Breakdown Configuration is not configured" );
					Log4jHelper.logInfo( "Bill Breakdown Configuration is updated successfully with name " + name );
				}
				else
				{
					Log4jHelper.logInfo( "Bill Breakdown Configuration is not available with name " + name );
				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	protected void newbillbreakdownConfig() throws Exception
	{
		BillBreakdownConfigDetailImpl billconfigDetailObj = new BillBreakdownConfigDetailImpl();

		billConfigActionObj.createNewbillBreaskdownConfig( clientPartition );
		billconfigDetailObj.billbreakdownBasicDetails( name, displayName, breakdownPrefix, breakdownsummaryPrefix, useDataRange );
		billconfigDetailObj.tableInstanceSelection( useJoins, tableInstance, tableName, billusageQueryConfig );

		if ( !ratingComponent.isEmpty() )
		{
			billconfigDetailObj.ratingComponentconfig( ratingComponent, ratableFlag, billedUsage, rate, tariff, count, setupAmt );
		}

		billconfigDetailObj.billBreakdownKeysConfig( billKeys, lineItemKey, visible, filter, disputed, displayDP );
		billconfigDetailObj.billBreakdownValuesConfig( billValues, functionValue, amountValue, visibleValue, disputedValue, displayDPValue );
		billconfigDetailObj.billbreakdownConfigSave();

	}

	protected void editbillbreakdownConfig() throws Exception
	{
		BillBreakdownConfigDetailImpl billconfigDetailObj = new BillBreakdownConfigDetailImpl();

		billconfigDetailObj.editBillbreakdownBasicDetails( name, displayName, breakdownPrefix, breakdownsummaryPrefix, useDataRange );
		billconfigDetailObj.editTableInstanceSelection( useJoins, tableInstance, tableName, billusageQueryConfig );

		if ( !ratingComponent.isEmpty() )
		{
			billconfigDetailObj.editratingComponentconfig( ratingComponent, ratableFlag, billedUsage, rate, tariff, count, setupAmt );
		}

		billconfigDetailObj.editBillBreakdownKeysConfig( billKeys, lineItemKey, visible, filter, disputed, displayDP );
		billconfigDetailObj.editBillBreakdownValuesConfig( billValues, functionValue, amountValue, visibleValue, disputedValue, displayDPValue );
		billconfigDetailObj.billbreakdownConfigSave();

	}

	/*
	 * This method is to change bill breakdown change status action
	 */
	public void billbreakdownChangeStatus() throws Exception
	{
		NavigationHelper.navigateToScreen( "Bill Breakdown Configuration" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			billConfigMap = excelHolderObj.dataMap( paramVal );
			name = ExcelHolder.getKey( billConfigMap, "Name" );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isBillConfigPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_billConfig_name_txtID", name, "Name" );
			if ( isBillConfigPresent )
			{
				String actualVal = GridHelper.getCellValue( "SearchGrid", 1, "Status" );
				if ( actualVal.contains( "Draft" ) )
				{
					billConfigActionObj.changeBillBreakdownConfigStatus( name );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					Thread.sleep( 2000 );
					ButtonHelper.click( "ClearButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					String headerElement = "//*[@id='searchGrid']//th//div[contains(text(),'Name')]";
					if ( !ElementHelper.isElementPresent( headerElement ) )
						ElementHelper.waitForElement( headerElement, 120 );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					SearchGridHelper.gridFilterSearchWithTextBox( "PS_Detail_billConfig_name_txtID", name, "Name" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					ButtonHelper.click( "SearchButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					int row = GridHelper.getRowCount( "SearchGrid" );
					if ( row != 0 )
					{
						ButtonHelper.click( "SearchButton" );
						GenericHelper.waitForLoadmask( searchScreenWaitSec );
						ButtonHelper.click( "SearchButton" );
						GenericHelper.waitForLoadmask( searchScreenWaitSec );
						assertTrue( GridHelper.isValuePresent( "SearchGrid", "Accepted", "Status" ) );
						GenericHelper.waitForLoadmask();
						Log4jHelper.logInfo( "Bill Breakdown configuration status changed sucessfully" );
					}
					else
						FailureHelper.failTest( "Bill Breaddown config status has not been changed" );
				}
				else
					Log4jHelper.logInfo( "bill breakdown Configuration status has already changed" + name );
			}
			else
				Log4jHelper.logInfo( "bill breakdown Configuration is not available with :" + name );
		}
	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Bill Breakdown Configuration" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			billConfigMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( billConfigMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			String[] searchGridColumnsArr = strObj.stringSplitFirstLevel( searchScreenColumns );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genericHelperObj.totalColumns( excelColumnNames );
		}

	}

	/*
	 * This method is to initialize instance variables
	 */
	protected void initializeVariables( Map<String, String> map ) throws Exception
	{

		clientPartition = ExcelHolder.getKey( map, "Partition" );
		name = ExcelHolder.getKey( map, "Name" );
		displayName = ExcelHolder.getKey( map, "DisplayName" );
		useJoins = ExcelHolder.getKey( map, "UseJoins" );
		billusageQueryConfig = ExcelHolder.getKey( map, "BillusageQueryConfig" );
		tableInstance = ExcelHolder.getKey( map, "TableInstance" );
		breakdownPrefix = ExcelHolder.getKey( map, "BillBreakdownPrefix" );
		breakdownsummaryPrefix = ExcelHolder.getKey( map, "BillBreakdownsummaryPrefix" );
		useDataRange = ExcelHolder.getKey( map, "UseDataRange" );
		billKeys = ExcelHolder.getKey( map, "BillKeys" );
		lineItemKey = ExcelHolder.getKey( map, "LineItemKey" );
		visible = ExcelHolder.getKey( map, "Visible" );
		filter = ExcelHolder.getKey( map, "Filter" );
		disputed = ExcelHolder.getKey( map, "Disputed" );
		displayDP = ExcelHolder.getKey( map, "DisplayDP" );
		computedKey = ExcelHolder.getKey( map, "ComputedKey" );
		keyComputation = ExcelHolder.getKey( map, "KeyComputation" );
		tableName = ExcelHolder.getKey( map, "TableName" );
		billValues = ExcelHolder.getKey( map, "BillValues" );
		functionValue = ExcelHolder.getKey( map, "FunctionValue" );
		amountValue = ExcelHolder.getKey( map, "AmountValue" );
		visibleValue = ExcelHolder.getKey( map, "VisibleValue" );
		disputedValue = ExcelHolder.getKey( map, "DisputedValue" );
		displayDPValue = ExcelHolder.getKey( map, "DisplayDPValue" );
		computedValue = ExcelHolder.getKey( map, "ComputedValue" );
		valueComputation = ExcelHolder.getKey( map, "ValueComputation" );
		ratingComponent = ExcelHolder.getKey( map, "RatingComponent" );
		ratableFlag = ExcelHolder.getKey( map, "RatableFlag" );
		billedUsage = ExcelHolder.getKey( map, "BilledUsage" );
		rate = ExcelHolder.getKey( map, "Rate" );
		tariff = ExcelHolder.getKey( map, "Tariff" );
		count = ExcelHolder.getKey( map, "Count" );
		setupAmt = ExcelHolder.getKey( map, "SetupAmt" );
	}

	/*
	 * This method is for bill breakdown Configuration deletion
	 */
	public void billbreakdownConfigDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Bill Breakdown Configuration" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			billConfigMap = excelHolderObj.dataMap( paramVal );

			clientPartition = ExcelHolder.getKey( billConfigMap, "Partition" );
			name = ExcelHolder.getKey( billConfigMap, "Name" );

			genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );

			boolean isBillConfigPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_billConfig_name_txtID", name, "Name" );

			if ( isBillConfigPresent )
			{
				billConfigActionObj.clickDeleteAction( name );
				genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "bill breakdown Configuration is deleted successfully :" + name );

			}
			else
			{
				Log4jHelper.logInfo( "bill breakdown Configuration is not available with :" + name );
			}

		}
	}

	/*
	 * This method is for billbreakdownConfig un delete
	 */
	public void billbreakdownConfigUnDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Bill Breakdown Configuration" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			billConfigMap = excelHolderObj.dataMap( paramVal );

			clientPartition = ExcelHolder.getKey( billConfigMap, "Partition" );
			name = ExcelHolder.getKey( billConfigMap, "Name" );
			genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );

			boolean isBillConfigPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_billConfig_name_txtID", name, "Name" );

			if ( isBillConfigPresent )
			{
				billConfigActionObj.clickUnDeleteAction( name );
				genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "bill breakdown Configuration is un deleted successfully :" + name );

			}
			else
			{
				Log4jHelper.logInfo( "bill breakdown Configuration is not available with :" + name );
			}

		}
	}
}
