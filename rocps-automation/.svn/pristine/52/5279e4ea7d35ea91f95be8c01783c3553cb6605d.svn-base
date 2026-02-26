package com.subex.rocps.automation.helpers.application.products;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.ipMeasurConfig.IPMeasurConfigActImpl;
import com.subex.rocps.automation.helpers.application.products.ipMeasurConfig.IPMeasurConfigDetailImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class IPMeasuremConfigurationHelper extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> ipMeasConfigurationExcelMap = null;
	protected Map<String, String> ipMeasConfigurationMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	protected String ipMeasuConfigName;
	protected String clientPartition;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl psActionImpl = new PSActionImpl();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
	IPMeasurConfigActImpl ipConfigActImpl = new IPMeasurConfigActImpl();

	/**
	 * Constructor : Initializing the excel without occurrence
	 * 
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception
	 */
	public IPMeasuremConfigurationHelper( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		ipMeasConfigurationExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( ipMeasConfigurationExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/**
	 * Constructor : Initializing the excel with occurrence
	 * 
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @param occurence
	 * @throws Exception
	 */
	public IPMeasuremConfigurationHelper( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		ipMeasConfigurationExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( ipMeasConfigurationExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		ipMeasuConfigName = ExcelHolder.getKey( map, "Name" );

	}

	/*
	 * This method is for 'IP Measurement Configuration' screen common method
	 */
	private void ipMeasConfigurationScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "IP Measurement Configuration" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ipMeasConfigurationMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Name" );
	}

	/*
	 * This method is for 'IP Measurement Configuration' screen column validation
	 */
	public void ipMeasConfigurationColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				ipMeasConfigurationScreen();
				colmHdrs = ExcelHolder.getKey( ipMeasConfigurationMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Name", colmHdrs, "IP Measurement Configuration" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'IP Measurement Configuration' creation
	 */
	public void ipMeasConfigurationCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				ipMeasConfigurationScreen();
				initializeVariable( ipMeasConfigurationMap );
				boolean isIpMeasConfigPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_ipMeasuConfig_name_txtId", ipMeasuConfigName, "Name" );
				if ( !isIpMeasConfigPresent )
				{
					ipConfigActImpl.clickNewAction( clientPartition );
					IPMeasurConfigDetailImpl ipMeasurConfigDetailImpl = new IPMeasurConfigDetailImpl( ipMeasConfigurationMap );
					ipMeasurConfigDetailImpl.configureIpMeasurment();
					Log4jHelper.logInfo( "Ip Measurement Configuration is successfuly saved with " + ipMeasuConfigName );
				}
				else
					Log4jHelper.logInfo( "Ip Measurement Configuration is already available with " + ipMeasuConfigName );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'IP Measurement Configuration' edit
	 */
	public void ipMeasConfigurationEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				ipMeasConfigurationScreen();
				initializeVariable( ipMeasConfigurationMap );
				boolean isIpMeasConfigPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_ipMeasuConfig_name_txtId", ipMeasuConfigName, "Name" );
				if ( isIpMeasConfigPresent )
				{
					GridHelper.clickRow( "SearchGrid", ipMeasuConfigName, "Name" );
					ipConfigActImpl.clickOnAction( "Common Tasks", "Edit" );
					IPMeasurConfigDetailImpl ipMeasurConfigDetailImpl = new IPMeasurConfigDetailImpl( ipMeasConfigurationMap );
					ipMeasurConfigDetailImpl.modifyIpMeasurment();
					Log4jHelper.logInfo( "Ip Measurement Configuration is successfuly updated with " + ipMeasuConfigName );
				}
				else
					Log4jHelper.logInfo( "Ip Measurement Configuration is not available with " + ipMeasuConfigName );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	// Method: for Ip Measurement Configurationdeletion action
	public void ipMeasConfigurationDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			ipMeasConfigurationScreen();
			initializeVariable( ipMeasConfigurationMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isipMeasuConfigNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_ipMeasuConfig_name_txtId", ipMeasuConfigName, "Name" );
			assertTrue( isipMeasuConfigNamePresent, "Ip Measurement Configuration is not available in the screen with  name: -'" + ipMeasuConfigName );
			psGenericHelper.clickDeleteOrUnDeleteAction( ipMeasuConfigName, "Name", "Delete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			isipMeasuConfigNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_ipMeasuConfig_name_txtId", ipMeasuConfigName, "Name" );
			assertTrue( isipMeasuConfigNamePresent, ipMeasuConfigName + " is not present" );
			Log4jHelper.logInfo( "Ip Measurement Configuration is deleted successfully with the value-:'" + ipMeasuConfigName );
		}

	}

	// Method: for Ip Measurement Configuration Undeletion action
	public void ipMeasConfigurationUnDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			ipMeasConfigurationScreen();
			initializeVariable( ipMeasConfigurationMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			boolean isipMeasuConfigNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_ipMeasuConfig_name_txtId", ipMeasuConfigName, "Name" );
			assertTrue( isipMeasuConfigNamePresent, "Ip Measurement Configuration is not available in the screen with  name: -'" + ipMeasuConfigName );
			psGenericHelper.clickDeleteOrUnDeleteAction( ipMeasuConfigName, "Name", "Undelete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Non Deleted Items" );
			isipMeasuConfigNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_ipMeasuConfig_name_txtId", ipMeasuConfigName, "Name" );
			assertTrue( isipMeasuConfigNamePresent, ipMeasuConfigName + " is not present" );
			Log4jHelper.logInfo( "Ip Measurement Configuration is undeleted successfully with the  value:  '" + ipMeasuConfigName );
		}

	}
}
