package com.subex.rocps.automation.helpers.application.carrierinvoice;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.carrierinvoice.automaticinvoiceconfig.AutomaticInvoiceConfigDetailImpl;
import com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoice.CarrierInvoiceActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class AutomaticInvoiceConfig extends PSAcceptanceTest
{
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> citempExcel = null;
	protected Map<String, String> citempMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String partition;
	protected String configName;
	protected String fileName;
	protected String billProfile;

	String regex = new PSStringUtils().regexFirstLevelDelimeter();
	CarrierInvoiceActionImpl ciActionObj = new CarrierInvoiceActionImpl();
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	@Test
	public AutomaticInvoiceConfig( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		citempExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( citempExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public AutomaticInvoiceConfig( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		citempExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( citempExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the auto invoice congig
	 * 
	 */
	public void autoInvoiceCongigCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Automatic Invoice Config" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				citempMap = excelHolderObj.dataMap( paramVal );

				//initializeVariables( citempMap );
				configName = ExcelHolder.getKey( citempMap, "ConfigurationName" );
				boolean isAutoinvoiceConfigPResent = genericHelperObj.isGridTextValuePresent( "piaiName", configName, "Configuration Name" );
				if ( !isAutoinvoiceConfigPResent )
				{
					newAutoInvoiceConfig();
					Log4jHelper.logInfo( "Auto Invoice Congiguration is  created successfully " + configName );
				}

				else
					Log4jHelper.logInfo( "Auto Invoice Congiguration is already available" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public void newAutoInvoiceConfig() throws Exception
	{
		AutomaticInvoiceConfigDetailImpl detailObj = new AutomaticInvoiceConfigDetailImpl( citempMap );
		detailObj.newAutoInvoice();
		detailObj.autoInvoiceConfig();
		detailObj.saveAutomaticInvoiceConfig();
	}

	/*
	 * This method is for search screen column validation
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Automatic Invoice Config" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			citempMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( citempMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			String[] searchGridColumnsArr = searchScreenColumns.split( regex, -1 );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genericHelperObj.totalColumns( excelColumnNames );
		}

	}

	public void editAutoInvoiceCongigCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Automatic Invoice Config" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				citempMap = excelHolderObj.dataMap( paramVal );

				configName = ExcelHolder.getKey( citempMap, "ConfigurationName" );
				boolean isAutoinvoiceConfigPResent = genericHelperObj.isGridTextValuePresent( "piaiName", configName, "Configuration Name" );
				if ( isAutoinvoiceConfigPResent )
				{
					int row = GridHelper.getRowNumber( "SearchGrid", configName, "Configuration Name" );
					NavigationHelper.navigateToEdit( "SearchGrid", row );
					assertEquals( NavigationHelper.getScreenTitle(), "Edit Automatic Invoice Configuration" );
					AutomaticInvoiceConfigDetailImpl detailObj = new AutomaticInvoiceConfigDetailImpl( citempMap );
					detailObj.editAutoInvoiceConfig();

					detailObj.saveAutomaticInvoiceConfig();
					Log4jHelper.logInfo( "Auto Invoice Congiguration is  updated  "+configName );
				}
				else
					Log4jHelper.logInfo( "Auto Invoice Congiguration is not already available" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is for automaticInvoiceConfig deletion
	 */
	public void automaticInvoiceConfigDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Automatic Invoice Config" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			citempMap = excelHolderObj.dataMap( paramVal );

			partition = ExcelHolder.getKey( citempMap, "Partition" );
			configName = ExcelHolder.getKey( citempMap, "Name" );

			genericHelperObj.selectPartitionFilter( partition, "Non Deleted Items" );
			boolean isEventModDefn = genericHelperObj.isGridTextValuePresent( "piaiName", configName, "Configuration Name" );
			if ( isEventModDefn )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( configName, "Configuration Name", "Delete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( partition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", configName, "Name" ), configName );
				Log4jHelper.logInfo( "Automatic Invoice Config is deleted successfully : " + configName );

			}
			else
			{
				Log4jHelper.logInfo( "Automatic Invoice Config is not available : " + configName );
			}

		}
	}

	/*
	 * This method is for automaticInvoiceConfig un delete
	 */
	public void automaticInvoiceConfigUnDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Automatic Invoice Config" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			citempMap = excelHolderObj.dataMap( paramVal );

			partition = ExcelHolder.getKey( citempMap, "Partition" );
			configName = ExcelHolder.getKey( citempMap, "Name" );

			genericHelperObj.selectPartitionFilter( partition, "Deleted Items" );
			boolean isEventModDefn = genericHelperObj.isGridTextValuePresent( "piaiName", configName, "Configuration Name" );
			if ( isEventModDefn )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( configName, "Configuration Name", "Undelete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( partition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", configName, "Configuration Name" ), configName );
				Log4jHelper.logInfo( "Automatic Invoice Config is un deleted successfully : " + configName );

			}
			else
			{
				Log4jHelper.logInfo( "Automatic Invoice Config is not available : " + configName );
			}

		}
	}

}
