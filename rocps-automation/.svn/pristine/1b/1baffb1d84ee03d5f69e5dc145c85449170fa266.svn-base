package com.subex.rocps.automation.helpers.application.bills;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bills.billReportConfiguration.BillReportConfigActImpl;
import com.subex.rocps.automation.helpers.application.bills.billReportConfiguration.BillReprtConfiguredetail;
import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class BillReportConfigurationHelper extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> billReportConfExcelMap = null;
	protected Map<String, String> billReportConfMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int colSize;
	protected int paramVal;
	protected int occurence;
	protected int index;
	protected String colmHdrs;
	protected String configureName;
	protected String clientPartition;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	DataSelectionHelper dataSelection = new DataSelectionHelper();
	BillReportConfigActImpl billReportConfigActImpl = new BillReportConfigActImpl();

	/**
	 * Constructor : Initializing the excel without occurrence
	 * 
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception
	 */
	public BillReportConfigurationHelper( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		billReportConfExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( billReportConfExcelMap );
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
	public BillReportConfigurationHelper( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		billReportConfExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( billReportConfExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		configureName = ExcelHolder.getKey( map, "ConfigName" );

	}
	/*
	 * This method is for 'Bill Report Configuration' screen common method
	 */
	private void billReportConfScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Bill Report Configuration" );
		GenericHelper.waitForLoadmask( 100 );
		billReportConfMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Configuration Name" );
	}

	/*
	 * This method is for 'Bill Report Configuration' screen column validation
	 */
	public void billReportConfColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				billReportConfScreen();
				colmHdrs = ExcelHolder.getKey( billReportConfMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Configuration Name", colmHdrs, "Bill Report Configuration" );

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is to check 'Bill Report Configuration' already present
	 */
	private boolean isBillReportConfPresent( String configureName ) throws Exception
	{
		TextBoxHelper.type( "PSDetail_Bill_Report_Conf_Name_TextId", configureName );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Configuration Name" );
		boolean isBillReportConfPresent = psGenericHelper.isDataPresent( configureName, "Configuration Name" );
		return isBillReportConfPresent;
	}

	/*
	 * This method is for create new 'Bill Report Configuration'.
	 */
	public void billReportConfCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				billReportConfScreen();
				initializeVariable( billReportConfMap );
				boolean isBillReportConfPresent = isBillReportConfPresent( configureName );
				if ( !isBillReportConfPresent )
				{
					billReportConfigActImpl.clickNewAction( clientPartition );
					BillReprtConfiguredetail billReprtConfiguredetail = new BillReprtConfiguredetail( billReportConfMap );
					billReprtConfiguredetail.configBillReportConf();
					psGenericHelper.detailSave( "PSDetail_Bill_Report_Conf_save_btnId", configureName, "Configuration Name" );
					Log4jHelper.logInfo( "Bill Report configuration is created successfully with : " + configureName );
				}
				else
					Log4jHelper.logInfo( "Bill Report configuration is already available with : " + configureName );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	// Method: for Bill Report Configuration deletion action
	public void billReportConfigurationDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			billReportConfScreen();
			initializeVariable( billReportConfMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isconfigureNamePresent =  isBillReportConfPresent( configureName );
			assertTrue( isconfigureNamePresent, "Bill Report Configuration is not available in the screen with  name: -'" + configureName );
			psGenericHelper.clickDeleteOrUnDeleteAction( configureName, "Configuration Name", "Delete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			isconfigureNamePresent =  isBillReportConfPresent( configureName );
			assertTrue( isconfigureNamePresent, configureName + " is not present" );
			Log4jHelper.logInfo( "Bill Report Configuration is deleted successfully with the value-:'" + configureName );
		}

	}

	// Method: for Bill Report Configuration Undeletion action
	public void billReportConfigurationUnDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			billReportConfScreen();
			initializeVariable( billReportConfMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			boolean isconfigureNamePresent =  isBillReportConfPresent( configureName );
			assertTrue( isconfigureNamePresent, "Bill Report Configuration is not available in the screen with  name: -'" + configureName );
			psGenericHelper.clickDeleteOrUnDeleteAction( configureName, "Configuration Name", "Undelete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Non Deleted Items" );
			isconfigureNamePresent =  isBillReportConfPresent( configureName );
			assertTrue( isconfigureNamePresent, configureName + " is not present" );
			Log4jHelper.logInfo( "Bill Report Configuration is undeleted successfully with the  value:  '" + configureName );
		}

	}
}
