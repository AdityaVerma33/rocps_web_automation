package com.subex.rocps.automation.helpers.application.roaming;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.AdvanceSearchFiltersHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.roaming.imsiManagement.IMSIManagementActionImpl;
import com.subex.rocps.automation.helpers.application.roaming.imsiManagement.IMSIManagementDetails;
import com.subex.rocps.automation.helpers.application.roaming.testSIMManagement.TestSIMManagementAction;
import com.subex.rocps.automation.helpers.application.roaming.testSIMManagement.TestSIMManagementDetails;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TestSIMManagement extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> testSIMManagementExcelMap = null;
	protected Map<String, String> testSIMManagementMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String clientPartition;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	protected String tadigCode;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	AdvanceSearchFiltersHelper advanceSearchHelpOb = new AdvanceSearchFiltersHelper();

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public TestSIMManagement( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		testSIMManagementExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( testSIMManagementExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/**Constructor : Initializing the excel with occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @param occurence
	 * @throws Exception 
	 */
	public TestSIMManagement( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		testSIMManagementExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( testSIMManagementExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		tadigCode = ExcelHolder.getKey( map, "Tadig" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'Test SIM Management' screen common method
	 */
	private void testSIMManagementScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Test SIM Management" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		testSIMManagementMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Tadig" );
	}

	/*
	 * This method is for 'Test SIM Management' screen column validation
	 */
	public void testSIMManagementColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				testSIMManagementScreen();
				colmHdrs = ExcelHolder.getKey( testSIMManagementMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String testSIMManagementGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : testSIMManagementGridColumnsArr )
					excelColumnNames.add( column );
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'Test SIM Management' Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Test SIM Management'  creation
	 */
	public void testSIMManagementCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				testSIMManagementScreen();
				initializeVariable( testSIMManagementMap );
				boolean isRoamingDfnpresent = isRoamingDfnpresent();
				if ( !isRoamingDfnpresent )
				{
					TestSIMManagementAction testManagementAction = new TestSIMManagementAction();
					testManagementAction.clickNewAction( clientPartition );
					TestSIMManagementDetails teSimManagementDetails = new TestSIMManagementDetails( testSIMManagementMap );
					teSimManagementDetails.createTestSimManagement();
					Log4jHelper.logInfo( "Test SIM Management'' is successfully created with tadig value:  '" + tadigCode );

				}
				else

					Log4jHelper.logInfo( "'Test SIM Management'' is already avilable with tadig value:" + tadigCode );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Test SIM Management'  edit
	 */
	public void testSIMManagementEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				testSIMManagementScreen();
				initializeVariable( testSIMManagementMap );
				boolean isRoamingDfnpresent = isRoamingDfnpresent();
				if ( isRoamingDfnpresent )
				{
					TestSIMManagementAction testManagementAction = new TestSIMManagementAction();
					GridHelper.clickRow( "SearchGrid", tadigCode, "Tadig" );
					testManagementAction.clickOnAction( "Common Tasks", "Edit" );
					TestSIMManagementDetails teSimManagementDetails = new TestSIMManagementDetails( testSIMManagementMap );
					teSimManagementDetails.modifyTestSimManagement();
					Log4jHelper.logInfo( "Test SIM Management'' is successfully updated with tadig value:  '" + tadigCode );

				}
				else

					Log4jHelper.logInfo( "'Test SIM Management'' is not avilable with tadig value:" + tadigCode );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	private boolean isRoamingDfnpresent() throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		advanceSearchHelpOb.roamingDefnAdvanceSearch( "Roaming Definition", tadigCode );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforHeaderElement( "Tadig" );
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforHeaderElement( "Tadig" );
		return GridHelper.isValuePresent( "SearchGrid", tadigCode, "Tadig" );
	}

}
