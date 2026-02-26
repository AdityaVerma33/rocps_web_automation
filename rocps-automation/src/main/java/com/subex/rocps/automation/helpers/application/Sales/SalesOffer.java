package com.subex.rocps.automation.helpers.application.Sales;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.Sales.salesOffer.SalesOfferActionImpl;
import com.subex.rocps.automation.helpers.application.Sales.salesOffer.SalesOfferDetail;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class SalesOffer extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> salesOfferExcelMap = null;
	protected Map<String, String> salesOfferMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	protected String salesOfferName;
	protected String clientPartition;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl psActionImpl = new PSActionImpl();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
	SalesOfferActionImpl salesOfferActionImpl = new SalesOfferActionImpl();

	/**
	 * Constructor : Initializing the excel without occurrence
	 * 
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception
	 */
	public SalesOffer( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		salesOfferExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( salesOfferExcelMap );
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
	public SalesOffer( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		salesOfferExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( salesOfferExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		salesOfferName = ExcelHolder.getKey( map, "Name" );

	}

	/*
	 * This method is for 'Sales Offer' screen common method
	 */
	private void salesOfferScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Sales Offer" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		salesOfferMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Name" );
	}

	/*
	 * This method is for 'Sales Offer' screen column validation
	 */
	public void salesOfferColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				salesOfferScreen();
				colmHdrs = ExcelHolder.getKey( salesOfferMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Name", colmHdrs, "Sales Offer" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Sales Offer' creation
	 */
	public void salesOfferCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				salesOfferScreen();
				initializeVariable( salesOfferMap );
				boolean issalesOfferPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_salesOffer_name_txtId", salesOfferName, "Name" );
				if ( !issalesOfferPresent )
				{
					salesOfferActionImpl.clickNewAction( clientPartition );
					SalesOfferDetail salesOfferDetailImpl = new SalesOfferDetail( salesOfferMap );
					salesOfferDetailImpl.configureSalesOffer();
					Log4jHelper.logInfo( "Sales Offer is successfuly saved with " + salesOfferName );
				}
				else
					Log4jHelper.logInfo( "Sales Offer is already available with " + salesOfferName );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	// Method: for Sales Offer deletion action
	public void salesOfferDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			salesOfferScreen();
			initializeVariable( salesOfferMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean issalesOfferNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_salesOffer_name_txtId", salesOfferName, "Name" );
			assertTrue( issalesOfferNamePresent, "Sales Offer is not available in the screen with  name: -'" + salesOfferName );
			psGenericHelper.clickDeleteOrUnDeleteAction( salesOfferName, "Name", "Delete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			issalesOfferNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_salesOffer_name_txtId", salesOfferName, "Name" );
			assertTrue( issalesOfferNamePresent, salesOfferName + " is not present" );
			Log4jHelper.logInfo( "Sales Offer is deleted successfully with the value-:'" + salesOfferName );
		}

	}

	// Method: for Sales Offer Undeletion action
	public void salesOfferUnDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			salesOfferScreen();
			initializeVariable( salesOfferMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			boolean issalesOfferNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_salesOffer_name_txtId", salesOfferName, "Name" );
			assertTrue( issalesOfferNamePresent, "Sales Offer is not available in the screen with  name: -'" + salesOfferName );
			psGenericHelper.clickDeleteOrUnDeleteAction( salesOfferName, "Name", "Undelete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Non Deleted Items" );
			issalesOfferNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_salesOffer_name_txtId", salesOfferName, "Name" );
			assertTrue( issalesOfferNamePresent, salesOfferName + " is not present" );
			Log4jHelper.logInfo( "Sales Offer is undeleted successfully with the  value:  '" + salesOfferName );
		}

	}
}
