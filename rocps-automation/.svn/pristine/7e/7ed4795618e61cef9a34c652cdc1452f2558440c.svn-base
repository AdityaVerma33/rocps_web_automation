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
import com.subex.rocps.automation.helpers.application.filters.AdvanceSearchFiltersHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.provStatusGrp.ProvisionStatusGrpActImpl;
import com.subex.rocps.automation.helpers.application.products.provStatusGrp.ProvisionStatusGrpDetailImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class ProvisionStatusGroupHelper extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> provisionStatusGrpExcelMap = null;
	protected Map<String, String> provisionStatusGrpMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	protected String provStatusGrpName;
	protected String clientPartition;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl psActionImpl = new PSActionImpl();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
	ProvisionStatusGrpActImpl proStatusGrpActImpl = new ProvisionStatusGrpActImpl();

	/**
	 * Constructor : Initializing the excel without occurrence
	 * 
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception
	 */
	public ProvisionStatusGroupHelper( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		provisionStatusGrpExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( provisionStatusGrpExcelMap );
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
	public ProvisionStatusGroupHelper( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		provisionStatusGrpExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( provisionStatusGrpExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		provStatusGrpName = ExcelHolder.getKey( map, "Name" );

	}

	/*
	 * This method is for 'Provision Status Group' screen common method
	 */
	private void provisionStatusGrpScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Provision Status Group" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		provisionStatusGrpMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Name" );
	}

	/*
	 * This method is for 'Provision Status Group' screen column validation
	 */
	public void provisionStatusGrpColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				provisionStatusGrpScreen();
				colmHdrs = ExcelHolder.getKey( provisionStatusGrpMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Name", colmHdrs, "Provision Status Group" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Provision Status Group' creation
	 */
	public void provisionStatusGrpCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				provisionStatusGrpScreen();
				initializeVariable( provisionStatusGrpMap );
				boolean isProvStatusGrpNmPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_provStatusGrp_name_txtId", provStatusGrpName, "Name" );
				if ( !isProvStatusGrpNmPresent )
				{
					proStatusGrpActImpl.clickNewAction( clientPartition );
					ProvisionStatusGrpDetailImpl prStatusGrpDetailImpl = new ProvisionStatusGrpDetailImpl( provisionStatusGrpMap );
					prStatusGrpDetailImpl.configProvStatusGrp();
					Log4jHelper.logInfo( "'Provision Status Group' is successfully created with ' Name '" + provStatusGrpName );
				}
				else
					Log4jHelper.logInfo( "'Provision Status Group' is already avilable with ' Name'" + provStatusGrpName );

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Provision Status Group' edit
	 */
	public void provisionStatusGrpEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				provisionStatusGrpScreen();
				initializeVariable( provisionStatusGrpMap );
				boolean isProvStatusGrpNmPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_provStatusGrp_name_txtId", provStatusGrpName, "Name" );
				if ( isProvStatusGrpNmPresent )
				{
					GridHelper.clickRow( "SearchGrid", provStatusGrpName, "Name" );
					proStatusGrpActImpl.clickOnAction( "Common Tasks", "Edit" );
					ProvisionStatusGrpDetailImpl prStatusGrpDetailImpl = new ProvisionStatusGrpDetailImpl( provisionStatusGrpMap );
					prStatusGrpDetailImpl.modifyProvStatusGrp();
					Log4jHelper.logInfo( "'Provision Status Group' is successfully updated with ' Name '" + provStatusGrpName );
				}
				else
					Log4jHelper.logInfo( "'Provision Status Group' is not avilable with ' Name'" + provStatusGrpName );

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	// Method: for Provision Status Groupdeletion action
	public void provisionStatusGrpDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			provisionStatusGrpScreen();
			initializeVariable( provisionStatusGrpMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isprovStatusGrpNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_provStatusGrp_name_txtId", provStatusGrpName, "Name" );
			assertTrue( isprovStatusGrpNamePresent, "Provision Status Groupis not available in the screen with  name: -'" + provStatusGrpName );
			psGenericHelper.clickDeleteOrUnDeleteAction( provStatusGrpName, "Name", "Delete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			isprovStatusGrpNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_provStatusGrp_name_txtId", provStatusGrpName, "Name" );
			assertTrue( isprovStatusGrpNamePresent, provStatusGrpName + " is not present" );
			Log4jHelper.logInfo( "Provision Status Group is deleted successfully with the value-:'" + provStatusGrpName );
		}

	}

	// Method: for Provision Status GroupUndeletion action
	public void provisionStatusGrpUnDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			provisionStatusGrpScreen();
			initializeVariable( provisionStatusGrpMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			boolean isprovStatusGrpNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_provStatusGrp_name_txtId", provStatusGrpName, "Name" );
			assertTrue( isprovStatusGrpNamePresent, "Provision Status Groupis not available in the screen with  name: -'" + provStatusGrpName );
			psGenericHelper.clickDeleteOrUnDeleteAction( provStatusGrpName, "Name", "Undelete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Non Deleted Items" );
			isprovStatusGrpNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_provStatusGrp_name_txtId", provStatusGrpName, "Name" );
			assertTrue( isprovStatusGrpNamePresent, provStatusGrpName + " is not present" );
			Log4jHelper.logInfo( "Provision Status Group is undeleted successfully with the  value:  '" + provStatusGrpName );
		}

	}
}
