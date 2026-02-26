package com.subex.rocps.automation.helpers.application.Sales;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.Sales.salesProposal.SalesProposalActionImpl;
import com.subex.rocps.automation.helpers.application.Sales.salesProposal.SalesProposalDetail;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class SalesProposal extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> salesProposalExcelMap = null;
	protected Map<String, String> salesProposalMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	protected String salesProposalName;
	protected String clientPartition;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl psActionImpl = new PSActionImpl();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
	SalesProposalActionImpl salesProposalActionImpl = new SalesProposalActionImpl();

	/**
	 * Constructor : Initializing the excel without occurrence
	 * 
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception
	 */
	public SalesProposal( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		salesProposalExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( salesProposalExcelMap );
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
	public SalesProposal( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		salesProposalExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( salesProposalExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		salesProposalName = ExcelHolder.getKey( map, "Name" );

	}

	/*
	 * This method is for 'Sales Proposal' screen common method
	 */
	private void salesProposalScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Sales Proposal" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		salesProposalMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Name" );
	}

	/*
	 * This method is for 'Sales Proposal' screen column validation
	 */
	public void salesProposalColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				salesProposalScreen();
				colmHdrs = ExcelHolder.getKey( salesProposalMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Name", colmHdrs, "Sales Proposal" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Sales Proposal' creation
	 */
	public void salesProposalCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				salesProposalScreen();
				initializeVariable( salesProposalMap );
				boolean issalesProposalPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_salesProposal_name_txtId", salesProposalName, "Name" );
				if ( !issalesProposalPresent )
				{
					salesProposalActionImpl.clickNewAction( clientPartition );
					SalesProposalDetail salesProposalDetailImpl = new SalesProposalDetail( salesProposalMap );
					salesProposalDetailImpl.configureSalesProposal();
					Log4jHelper.logInfo( "Sales Proposal is successfuly saved with " + salesProposalName );
				}
				else
					Log4jHelper.logInfo( "Sales Proposal is already available with " + salesProposalName );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	// Method: for Sales Proposal deletion action
	public void salesProposalDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			salesProposalScreen();
			initializeVariable( salesProposalMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean issalesProposalNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_salesProposal_name_txtId", salesProposalName, "Name" );
			assertTrue( issalesProposalNamePresent, "Sales Proposal is not available in the screen with  name: -'" + salesProposalName );
			psGenericHelper.clickDeleteOrUnDeleteAction( salesProposalName, "Name", "Delete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			issalesProposalNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_salesProposal_name_txtId", salesProposalName, "Name" );
			assertTrue( issalesProposalNamePresent, salesProposalName + " is not present" );
			Log4jHelper.logInfo( "Sales Proposal is deleted successfully with the value-:'" + salesProposalName );
		}

	}

	// Method: for Sales Proposal Undeletion action
	public void salesProposalUnDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			salesProposalScreen();
			initializeVariable( salesProposalMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			boolean issalesProposalNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_salesProposal_name_txtId", salesProposalName, "Name" );
			assertTrue( issalesProposalNamePresent, "Sales Proposal is not available in the screen with  name: -'" + salesProposalName );
			psGenericHelper.clickDeleteOrUnDeleteAction( salesProposalName, "Name", "Undelete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Non Deleted Items" );
			issalesProposalNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_salesProposal_name_txtId", salesProposalName, "Name" );
			assertTrue( issalesProposalNamePresent, salesProposalName + " is not present" );
			Log4jHelper.logInfo( "Sales Proposal is undeleted successfully with the  value:  '" + salesProposalName );
		}

	}
}
