package com.subex.rocps.automation.helpers.application.roaming;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.roaming.roamingAgreemConfig.RoamingAgreeConfigAction;
import com.subex.rocps.automation.helpers.application.roaming.roamingAgreemConfig.RoamingAgreemConfigDetail;
import com.subex.rocps.automation.helpers.application.roaming.roamingDfn.RoamingDfnActionImpl;
import com.subex.rocps.automation.helpers.application.roaming.roamingDfn.RoamingDfnDetail;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.componentHelpers.SearchHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class RoamingAgreementConfig extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> roamingAgreeConfigExcelMap = null;
	protected Map<String, String> roamingAgreeConfigMap = null;
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
	protected String typeOfAgreement;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();

	/***Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public RoamingAgreementConfig( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		roamingAgreeConfigExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( roamingAgreeConfigExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/***Constructor :  Initializing the excel with occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public RoamingAgreementConfig( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		roamingAgreeConfigExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( roamingAgreeConfigExcelMap );
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
	 * This method is for 'Roaming Agreement Configuration' screen common method
	 */
	private void roamingAgreeConfigScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Roaming Agreement Configuration" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		roamingAgreeConfigMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Tadig" );
	}

	/*
	 * This method is for 'Roaming Agreement Configuration' screen column validation
	 */
	public void roamingAgreeConfigColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingAgreeConfigScreen();
				colmHdrs = ExcelHolder.getKey( roamingAgreeConfigMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String roamingAgreeConfigGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : roamingAgreeConfigGridColumnsArr )
					excelColumnNames.add( column );
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'Roaming Agreement Configuration' Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}
	
	/*
	 * This method is for 'Roaming Agreement Configuration'  creation
	 */
	public void roamingAgreemConfigCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingAgreeConfigScreen();
				initializeVariable( roamingAgreeConfigMap );
				boolean isTadigCodePresent = isTadigCodePresent();
				if ( !isTadigCodePresent )
				{
					RoamingAgreeConfigAction roamingAgreeConfigActObj=new RoamingAgreeConfigAction();
					roamingAgreeConfigActObj.clickNewAction( clientPartition );
					RoamingAgreemConfigDetail roamAgreeConfigDetailOb=new RoamingAgreemConfigDetail( roamingAgreeConfigMap );
					roamAgreeConfigDetailOb.createRoamingAgreeConfig();
					Log4jHelper.logInfo( "'Roaming Agreement Configuration'' is successfully created with tadig value:  '" + tadigCode+"'" );

				}
				else

					Log4jHelper.logInfo( "'Roaming Agreement Configuration'' is already avilable with tadig value:" + tadigCode );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}
	
	/*
	 * This method is for 'Tadig Code' filter 
	 */
	private boolean isTadigCodePresent() throws Exception
	{
		boolean isTadigCodePresent = false;
		tadigCodeAdvanceSearchFilter( "SearchGrid", "Tadig", tadigCode );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Tadig" );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Tadig" );
		isTadigCodePresent = GridHelper.isValuePresent( "SearchGrid", tadigCode, "Tadig" );
		return isTadigCodePresent;
	}
	/*
	 * This method is for 'Tadig Code' advance search filter 
	 */
	private void tadigCodeAdvanceSearchFilter( String gridId, String filterHeaderName, String tadig ) throws Exception
	{
		SearchHelper searchHelper = new SearchHelper();
		String advanceSearchBtn = GenericHelper.getORProperty( "PS_suggestionFilterAdvanceTextXpath" ).replace( "filterTxtId", "roamingDefinition" );
		String tadigSearchBtnLocator = GenericHelper.getORProperty( "PS_suggestionFilterSearchBtnXpath" ).replace( "filterTxtId", "roamingDefinition" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		searchHelper.clickFilterIcon( gridId, filterHeaderName );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.click( advanceSearchBtn );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		Thread.sleep( 1000 );
		psGenericHelper.waitforPopupHeaderElement( "Network" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		PSSearchGridHelper.gridFilterSearchWithComboBox( "PS_Detail_roamingDfn_tadig_comboId", tadig, "Tadig code" );
		boolean istadigPresent = GridHelper.isValuePresent( "Detail_popUpWindowId", "SearchGrid", tadig, "Tadig code" );
		assertTrue( istadigPresent, "Roaming Definition  with tadig value :'" + tadig + "'  is not found in 'Roaming Definition  Search' popupScreen " );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "Detail_popUpWindowId", "SearchGrid", 1, "Tadig code" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.clickIfEnabled( "Detail_popUpWindowId", "OKButton" );
		GenericHelper.waitForLoadmask();
		ElementHelper.waitForElement( tadigSearchBtnLocator, searchScreenWaitSec );
		ButtonHelper.click( tadigSearchBtnLocator );
		GenericHelper.waitForLoadmask();

	}

}
