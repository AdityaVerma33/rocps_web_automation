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
import com.subex.rocps.automation.helpers.application.bills.billingcycle.BillingCycleActionImpl;
import com.subex.rocps.automation.helpers.application.bills.billingcycle.BillingCycleDetailImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class BillingCycle extends PSAcceptanceTest
{

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> billingCycleExcel = null;
	protected Map<String, String> billingCycleMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String name;
	protected String franchise;
	protected String frequency;
	protected String anniversaryAlign;
	protected String alignUsingthe;
	protected String dayOfMonthWeek;
	protected String alignmentDate;
	protected String openPeriodDays;
	protected String closeCurrentPeriodDays;
	protected String asSoonAfterPeriod;
	protected String specificDayOfWeek;
	protected String dayOfWeek;
	protected String issueDate;
	protected String lateTrafficOpenBillPeriod;
	protected String lateTrafficClosedBillPeriod;
	protected String clientPartition;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	BillingCycleActionImpl billCycleActionObj = new BillingCycleActionImpl();
	PSStringUtils strObj = new PSStringUtils();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	@Test
	public BillingCycle( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		billingCycleExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( billingCycleExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public BillingCycle( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		billingCycleExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( billingCycleExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Billing Cycle
	 * 
	 * @method : isBillingCyclePresent returns false then Billing Cycle is
	 * configured isBillingCyclePresent returns true then Billing Cycle is not
	 * configured
	 */
	public void billingCycleCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Billing Cycle" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				billingCycleMap = excelHolderObj.dataMap( paramVal );
				initializeVariables( billingCycleMap );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isBillingCyclePresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_billingCycle_name_txtID", name, "Name" );

				if ( !isBillingCyclePresent )
				{
					newBillingcycle();
					Log4jHelper.logInfo( "Billing Cycle is created successfully with name " + name );
				}
				else
				{
					Log4jHelper.logInfo( "Billing Cycle is available with name " + name );
				}
			}
		}
		catch ( Exception e )
		{

			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*Method is for edit the billingCycle*/
	public void billingCycleEdit() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Billing Cycle" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				billingCycleMap = excelHolderObj.dataMap( paramVal );
				initializeVariables( billingCycleMap );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isBillingCyclePresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_billingCycle_name_txtID", name, "Name" );

				if ( isBillingCyclePresent )
				{
					int row = GridHelper.getRowNumber( "SearchGrid", name, "Name" );
					NavigationHelper.navigateToEdit( "SearchGrid", row );
					editConfigBillingCycle();
					Log4jHelper.logInfo( "Billing Cycle is updated successfully with name " + name );
				}
				else
				{
					Log4jHelper.logInfo( "Billing Cycle is not available with name " + name );
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
	 * This method is to create new Bill Cycle
	 */
	protected void newBillingcycle() throws Exception
	{

		billCycleActionObj.createNewBillingcycle( clientPartition );
		configureBillingCycle();

	}

	/*
	 * This method is to configure  Bill Cycle
	 */
	private void configureBillingCycle() throws Exception
	{
		BillingCycleDetailImpl billCycleDetailObj = new BillingCycleDetailImpl();
		billCycleDetailObj.basicDetails( name, franchise );
		billCycleDetailObj.billingCycleFrequency( frequency, anniversaryAlign, dayOfMonthWeek, alignmentDate );
		billCycleDetailObj.periodOpenCloseDates( openPeriodDays, closeCurrentPeriodDays );
		billCycleDetailObj.productionStartDate( asSoonAfterPeriod, specificDayOfWeek, dayOfWeek );
		billCycleDetailObj.billIssueDate( issueDate );
		billCycleDetailObj.lateTrafficMapping( lateTrafficOpenBillPeriod, lateTrafficClosedBillPeriod );
		genericHelperObj.detailSave( "PS_Detail_billingCycle_save_btnID", name, "Name" );
	}

	/*
	 * This method is to edit  Bill Cycle
	 */
	private void editConfigBillingCycle() throws Exception
	{
		BillingCycleDetailImpl billCycleDetailObj = new BillingCycleDetailImpl();
		billCycleDetailObj.editbasicDetails( name, franchise );
		billCycleDetailObj.editbillingCycleFrequency( frequency, anniversaryAlign, dayOfMonthWeek, alignmentDate );
		billCycleDetailObj.editperiodOpenCloseDates( openPeriodDays, closeCurrentPeriodDays );
		billCycleDetailObj.productionStartDate( asSoonAfterPeriod, specificDayOfWeek, dayOfWeek );
		billCycleDetailObj.editbillIssueDate( issueDate );
		billCycleDetailObj.lateTrafficMapping( lateTrafficOpenBillPeriod, lateTrafficClosedBillPeriod );
		genericHelperObj.detailSave( "PS_Detail_billingCycle_save_btnID", name, "Name" );
	}

	/*
	 * This method is to initialize instance variables
	 */
	protected void initializeVariables( Map<String, String> map ) throws Exception
	{

		clientPartition = ExcelHolder.getKey( map, "Partition" );
		name = ExcelHolder.getKey( map, "Name" );
		franchise = ExcelHolder.getKey( map, "Franchise" );
		issueDate = ExcelHolder.getKey( map, "IssueDate" );
		frequency = ExcelHolder.getKey( map, "Frequency" );
		anniversaryAlign = ExcelHolder.getKey( map, "AnniversaryAlign" );
		alignUsingthe = ExcelHolder.getKey( map, "AlignUsingthe" );
		dayOfMonthWeek = ExcelHolder.getKey( map, "DayOfMonthWeek" );
		alignmentDate = ExcelHolder.getKey( map, "AlignmentDate" );
		openPeriodDays = ExcelHolder.getKey( map, "OpenPeriodDays" );
		closeCurrentPeriodDays = ExcelHolder.getKey( map, "CloseCurrentPeriodDays" );
		asSoonAfterPeriod = ExcelHolder.getKey( map, "AsSoonAfterPeriod" );
		specificDayOfWeek = ExcelHolder.getKey( map, "SpecificDayOfWeek" );
		dayOfWeek = ExcelHolder.getKey( map, "DayOfWeek" );
		lateTrafficOpenBillPeriod = ExcelHolder.getKey( map, "LateTrafficOpenBillPeriod" );
		lateTrafficClosedBillPeriod = ExcelHolder.getKey( map, "LateTrafficClosedBillPeriod" );
	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Billing Cycle" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			billingCycleMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( billingCycleMap, "SearchScreenColumns" );
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
	 * This method is for Billing cycle deletion
	 */
	public void billingCycleDelete() throws Exception
	{
		NavigationHelper.navigateToScreen( "Billing Cycle" );

		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			billingCycleMap = excelHolderObj.dataMap( paramVal );
			name = ExcelHolder.getKey( billingCycleMap, "Name" );
			clientPartition = ExcelHolder.getKey( billingCycleMap, "Partition" );

			genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );

			boolean isBillingCyclePresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_billingCycle_name_txtID", name, "Name" );

			if ( isBillingCyclePresent )
			{
				billCycleActionObj.clickDeleteAction( name );
				genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "Billing cycle is deleted successfully :" + name );

			}
			else
				Log4jHelper.logInfo( "Billing cycle is not available with :" + name );
		}
	}

	/*
	 * This method is for billing Cycle un delete
	 */
	public void billingCycleUnDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Billing Cycle" );

		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			billingCycleMap = excelHolderObj.dataMap( paramVal );
			name = ExcelHolder.getKey( billingCycleMap, "Name" );
			clientPartition = ExcelHolder.getKey( billingCycleMap, "Partition" );
			genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );

			boolean isBillingCyclePresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_billingCycle_name_txtID", name, "Name" );

			if ( isBillingCyclePresent )
			{
				billCycleActionObj.clickUnDeleteAction( name );
				genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "Billing cycle is un deleted successfully :" + name );

			}
			else
				Log4jHelper.logInfo( "Billing cycle is not available with :" + name );
		}
	}
}
