package com.subex.rocps.automation.helpers.application.deal;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.deal.deal.DealRatesImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.ImageHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class DealRate extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> dealExcelMap = null;
	protected Map<String, ArrayList<String>> excelReaderMapObj = null;
	protected Map<String, String> dealMap = null;
	protected ExcelHolder excelHolderObj = null;
	String path;
	String workBookName;
	String sheetName;
	String testCaseName;
	int occurence;
	protected String partition;
	protected String account;
	protected String contractNo;
	protected String dealName;
	protected String dealPeriod;
	protected String newDealPeriod;
	protected String editTierRateTC;
	protected String editShortFalRateTC;
	protected String editExternalRateTC;
	protected String direction;
	protected String outgoing;
	protected Map<String, String> mapObj = null;
	Map<String, ArrayList<String>> rateMap = null;
	Map<String, String> configMap = null;
	String columnHeader;
	String results;
	int colSize;
	int paramVal;
	protected PSGenericHelper genHelperObj = new PSGenericHelper();
	DataVerificationHelper verifyObj = new DataVerificationHelper();
	PSStringUtils strObj = new PSStringUtils();

	/*
	 * Constructor : Initialising the excel
	 */
	public DealRate( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		dealExcelMap = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( dealExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Constructor : Initialising the excel
	 * 
	 * @Param : occurence of test case in a sheet
	 */
	public DealRate( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		dealExcelMap = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, this.occurence );
		excelHolderObj = new ExcelHolder( dealExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the deal rates
	 * 
	 */
	public void dealRateConfig() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Deal" );

			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				dealMap = excelHolderObj.dataMap( paramVal );
				initializeInstanceVariables();
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );

				if ( isDealPresent() )
				{
					String dealStatus = GridHelper.getCellValue( "SearchGrid", 1, "Status" );
					if ( dealStatus.equals( "Draft" ) )
					{
						GridHelper.clickRow( "SearchGrid", 1, "Account" );
						dealRate();
					}
					else
						Log4jHelper.logInfo( "Deal staus is not in draft status but found -" + dealStatus );
				}
			}

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public void dealRate() throws Exception
	{
		List<WebElement> inelement = ElementHelper.getElements( or.getProperty( "PS_Detail_Deal_newInDealXpath" ) );
		List<WebElement> outElement = ElementHelper.getElements( or.getProperty( "PS_Detail_Deal_newOutDealXpath" ) );
		int inElementSIze = inelement.size();
		int outElementSIze = outElement.size();
		if ( !newDealPeriod.isEmpty() && ( inElementSIze == 1 || outElementSIze == 1 ) )
		{
			DealRatesImpl rate2Obj = new DealRatesImpl( dealMap );
			String[] newdealPeriodAr = strObj.stringSplitFirstLevel( newDealPeriod );
			rate2Obj.newDealPeriod( newdealPeriodAr[0], newdealPeriodAr[1] );
		}

		if ( !editTierRateTC.isEmpty() )
			dealrateTestcaseConfig( editTierRateTC, "View/Edit Tier Rate" );

		if ( !editShortFalRateTC.isEmpty() )
			dealrateTestcaseConfig( editShortFalRateTC, "View/Edit shortfall rate" );

		if ( !editExternalRateTC.isEmpty() )
			dealrateTestcaseConfig( editExternalRateTC, "View/Edit External Rate" );
	}

	/*
	 * This method is for deal rate testcase
	 */
	public void dealrateTestcaseConfig( String viewEditTestcase, String actionName ) throws Exception
	{
		rateMap = dealRateTestDataInitialize( this.path, this.workBookName, this.sheetName, viewEditTestcase );
		excelHolderObj = new ExcelHolder( rateMap );
		colSize = excelHolderObj.totalColumns();
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			dealMap = excelHolderObj.dataMap( paramVal );
			DealRatesImpl rate2Obj = new DealRatesImpl( dealMap );
			rate2Obj.rateConfig( actionName, paramVal );
		}
		saveDealRate();
	}

	/*
	 * This method is to check if the deal is already present
	 */
	public boolean isDealPresent() throws Exception
	{
		genHelperObj.waitforHeaderElement( "Account" );
		SearchGridHelper.gridFilterAdvancedSearch( "account", account, "Account" );
		SearchGridHelper.gridFilterSearchWithTextBox( "pdelName", account, "Deal Name" );
		SearchGridHelper.gridFilterSearchWithTextBox( "pdelContractNo", contractNo, "Contract No" );
		return GridHelper.isValuePresent( "SearchGrid", account, "Account" );
	}

	/*
	 * This method is to save deal rates
	 */
	public void saveDealRate() throws Exception
	{
		if ( PopupHelper.isTextPresent( "Few bands have no rates configured. Do you wish to proceed?" ) )
		{
			ButtonHelper.click( "YesButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}
		ButtonHelper.click( "SaveButton" );
		Thread.sleep( 2000 );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitForElementToDisappear( "SaveButton", detailScreenWaitSec );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
	}

	private Map<String, ArrayList<String>> dealRateTestDataInitialize( String path, String workBook, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBook;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;

		excelReaderMapObj = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( excelReaderMapObj );
		return excelReaderMapObj;
	}

	/*
	 * This method is to initialize instance variables
	 */
	public void initializeInstanceVariables() throws Exception
	{
		//partition = ExcelHolder.getKey( dealMap, "Partition" );
		account = ExcelHolder.getKey( dealMap, "Account" );
		contractNo = ExcelHolder.getKey( dealMap, "Contract No" );
		dealName = ExcelHolder.getKey( dealMap, "DealName" );
		dealPeriod = ExcelHolder.getKey( dealMap, "DealPeriod" );
		editTierRateTC = ExcelHolder.getKey( dealMap, "EditTierRate" );
		newDealPeriod = ExcelHolder.getKey( dealMap, "NewDealPeriod" );
		editShortFalRateTC = ExcelHolder.getKey( dealMap, "EditShortFalRate" );
		editExternalRateTC = ExcelHolder.getKey( dealMap, "EditExternalRate" );
		//direction = ExcelHolder.getKey( dealMap, "Direction" );

	}
}
