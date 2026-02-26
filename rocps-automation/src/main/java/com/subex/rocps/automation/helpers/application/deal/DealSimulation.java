package com.subex.rocps.automation.helpers.application.deal;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.deal.deal.DealDetailImpl;
import com.subex.rocps.automation.helpers.application.deal.dealSimuation.DealSimulationActImpl;
import com.subex.rocps.automation.helpers.application.deal.dealSimuation.DealSimulationDetailImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.payments.paymAndCollections.PaymentAndCollectionsActImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class DealSimulation extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> dealSimulationExcelMap = null;
	protected Map<String, String> dealSimulationMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	protected String dealSimulationName;
	protected String dealSimulationType;
	protected String clientPartition;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
	DealSimulationActImpl dealSimulationActImpl = new DealSimulationActImpl();

	/**
	 * Constructor : Initializing the excel without occurrence
	 * 
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception
	 */
	public DealSimulation( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		dealSimulationExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( dealSimulationExcelMap );
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
	public DealSimulation( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		dealSimulationExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( dealSimulationExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variables
	 */
	private void initializeVariables( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		dealSimulationName = ExcelHolder.getKey( map, "SimulationName" );
		dealSimulationType = ExcelHolder.getKey( map, "DealSimulationType" );

	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		dealSimulationName = ExcelHolder.getKey( map, "SimulationName" );

	}

	/*
	 * This method is for 'Deal Simulation' screen common method
	 */
	private void dealSimulationScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Deal Simulation" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		dealSimulationMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Account" );
	}

	/*
	 * This method is for 'Deal Simulation' screen column validation
	 */
	public void dealSimulationColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				dealSimulationScreen();
				colmHdrs = ExcelHolder.getKey( dealSimulationMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Account", colmHdrs, "Deal Simulation" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Deal Simulation' CREATION
	 */
	public void dealSimulationCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				dealSimulationScreen();
				initializeVariables( dealSimulationMap );
				boolean idDealSimulationPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_DealSimulation_name_txtId", dealSimulationName, "Simulation Name" );
				if ( !idDealSimulationPresent )
				{
					dealSimulationActImpl.clickNewAction( clientPartition );
					DealSimulationDetailImpl dealSimulationDetailImpl = new DealSimulationDetailImpl( dealSimulationMap );
					dealSimulationDetailImpl.createDealSimulation();
					saveDealSimulation( dealSimulationName );
					Log4jHelper.logInfo( "Deal Simulation is successfuly saved with " + dealSimulationName );
				}
				else
					Log4jHelper.logInfo( "Deal Simulation is already available with " + dealSimulationName );
			}

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	// Method: for Deal Simulation deletion action
	public void dealSimulationDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			dealSimulationScreen();
			initializeVariable( dealSimulationMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isdealSimulationNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_DealSimulation_name_txtId", dealSimulationName, "Simulation Name" );
			assertTrue( isdealSimulationNamePresent, "Deal Simulation is not available in the screen with  name: -'" + dealSimulationName );
			if ( !isDealConfiguredToSimulation() )
			{
				psGenericHelper.clickDeleteOrUnDeleteAction( dealSimulationName, "Simulation Name", "Delete" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				//psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
				isdealSimulationNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_DealSimulation_name_txtId", dealSimulationName, "Simulation Name" );
				assertTrue( isdealSimulationNamePresent, dealSimulationName + " is not present" );
				Log4jHelper.logInfo( "Deal Simulation is deleted successfully with the value-:'" + dealSimulationName );
			}
			else
				Log4jHelper.logInfo( "Deal Simulation is  attached to  'Copied deal name' or created Deal's Account' for the value-:'" + dealSimulationName );
		}

	}

	// Method: for Deal Simulation Undeletion action
	public void dealSimulationUnDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			dealSimulationScreen();
			initializeVariable( dealSimulationMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			//psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			boolean isdealSimulationNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_DealSimulation_name_txtId", dealSimulationName, "Simulation Name" );
			assertTrue( isdealSimulationNamePresent, "Deal Simulation is not available in the screen with  name: -'" + dealSimulationName );
			if ( !isDealConfiguredToSimulation() )
			{
				psGenericHelper.clickDeleteOrUnDeleteAction( dealSimulationName, "Simulation Name", "Undelete" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				//psGenericHelper.selectPartitionFilter( clientPartition, "Non Deleted Items" );
				isdealSimulationNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_DealSimulation_name_txtId", dealSimulationName, "Simulation Name" );
				assertTrue( isdealSimulationNamePresent, dealSimulationName + " is not present" );
				Log4jHelper.logInfo( "Deal Simulation is undeleted successfully with the  value:  '" + dealSimulationName );
			}
			else
				Log4jHelper.logInfo( "Deal Simulation is  attached to  'Copied deal name' or created Deal's Account' for the value-:'" + dealSimulationName );

		}

	}

	/*
	 * This method is for 'Deal Simulation' isDealConfiguredToSimuation
	 */
	private boolean isDealConfiguredToSimulation() throws Exception
	{
		String copiedDealName = GridHelper.getCellValue( "SearchGrid", 1, "Copied Deal Name" );
		String createdDealAccountName = GridHelper.getCellValue( "SearchGrid", 1, "Account" );
		if ( ValidationHelper.isNotEmpty( copiedDealName ) )
			return true;
		else if ( ValidationHelper.isNotEmpty( createdDealAccountName ) )
			return true;
		else
			return false;
	}

	/*
	 * This method is for 'Deal Simulation' save
	 */
	private void saveDealSimulation( String dealSimulationName ) throws Exception
	{
		if ( dealSimulationType.contentEquals( "New" ) )
		{
			clickOnSaveBtn();
			String dealCreationTestCase = ExcelHolder.getKey( dealSimulationMap, "DealCreationTestCase" );
			if ( ValidationHelper.isNotEmpty( dealCreationTestCase ) )
			{
				Deal dealObj = new Deal( path, workBookName, sheetName, dealCreationTestCase );
				dealObj.dealDetailConfig();
				verifyDealSimulationOnSearchScreen( dealSimulationName );
			}
			else
			{
				ButtonHelper.click( "CancelButton" );
				verifyDealSimulationOnSearchScreen( dealSimulationName );
			}
		}
		else
			psGenericHelper.detailSaveWithRetry( "PS_Detail_DealSimulation_save_btnId", dealSimulationName, "Simulation Name" );
	}

	/*
	 * This method is for 'Deal Simulation' click on save button
	 */
	private void clickOnSaveBtn() throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.clickIfEnabled( "PS_Detail_DealSimulation_save_btnId" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitForElementToDisappear( "PS_Detail_DealSimulation_save_btnId", detailScreenWaitSec );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
	}

	/*
	 * This method is for 'Deal Simulation' verify on search screen
	 */
	private void verifyDealSimulationOnSearchScreen( String dealSimulationName ) throws Exception
	{
		psGenericHelper.waitforHeaderElement( "Simulation Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforHeaderElement( "Simulation Name" );
		assertTrue( GridHelper.isValuePresent( "SearchGrid", dealSimulationName, "Simulation Name" ), "Value '" + dealSimulationName + "' is not found in grid." );
	}

}
