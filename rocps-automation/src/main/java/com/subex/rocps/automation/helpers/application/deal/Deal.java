package com.subex.rocps.automation.helpers.application.deal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.deal.deal.CapsConfiguration;
import com.subex.rocps.automation.helpers.application.deal.deal.DealAdvanceConfiguration;
import com.subex.rocps.automation.helpers.application.deal.deal.DealBandConfiguration;
import com.subex.rocps.automation.helpers.application.deal.deal.DealDetailImpl;
import com.subex.rocps.automation.helpers.application.deal.deal.DealSearchmpl;
import com.subex.rocps.automation.helpers.application.deal.deal.DealTierConfiguration;
import com.subex.rocps.automation.helpers.application.filters.AdvanceSearchFiltersHelper;
import com.subex.rocps.automation.helpers.application.filters.GridFilterHelper;
import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;
import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.account.AccountActionImpl;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.account.AccountDetailImpl;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.account.AccountSearchImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class Deal extends PSAcceptanceTest
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
	protected String dealPeriod;
	protected String incoming;
	protected String outgoing;
	protected String bandInTestcase;
	protected String bandOutTestcase;
	protected String tierInTestcase;
	protected String tierOutTestcase;
	protected String capInTestcase;
	protected String capOutTestcase;
	GridFilterSearchHelper gridHelperObj = new GridFilterSearchHelper();
	protected Map<String, String> mapObj = null;	
	Map<String, ArrayList<String>> configMap = null;
	DealSearchmpl searchObj = new DealSearchmpl();

	String columnHeader;
	String results;
	int colSize;
	int paramVal;

	protected PSGenericHelper genHelperObj = new PSGenericHelper();

	DataVerificationHelper verifyObj = new DataVerificationHelper();
	//String terminateActualVal;
	PSStringUtils strObj = new PSStringUtils();

	/*
	 * Constructor : Initialising the excel
	 */
	public Deal( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
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
	public Deal( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
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
	 * Configuring the deal
	 * 
	 */
	public void dealCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Deal" );			
				dealMap = excelHolderObj.dataMap( 0 );
				initializeInstanceVariables();
			//	ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				if ( !isDealPresent() )
					dealConfig();
				else
					Log4jHelper.logInfo( "Deal is already available for this account" + account);			
			
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public void dealConfig() throws Exception
	{
		DealDetailImpl detailObj = new DealDetailImpl( dealMap );
		detailObj.newDeal();
		detailObj.basicDetails();
		incomingConfig();
		outgoingConfig();
		detailObj.dealSave();
		assertTrue( isDealPresent(), "Deal is saved successfully." );		

	}
	public void dealDetailConfig() throws Exception
	{
		dealMap = excelHolderObj.dataMap( 0 );
		initializeInstanceVariables();
		DealDetailImpl detailObj = new DealDetailImpl( dealMap );
		detailObj.basicDetails();
		incomingConfig();
		outgoingConfig();
		detailObj.dealSave();
		

	}
	public void incomingConfig() throws Exception
	{		
		if ( ValidationHelper.isNotEmpty( bandInTestcase ) )		
			bandTestCaseInitialize( this.path, this.workBookName, this.sheetName, bandInTestcase , "Incoming");		
		if ( ValidationHelper.isNotEmpty( tierInTestcase ) )
			tierTestCaseInitialize(  this.path, this.workBookName, this.sheetName, tierInTestcase , "Incoming");
		if ( ValidationHelper.isNotEmpty( capInTestcase ) )
			capConfiurationTestcase( this.path, this.workBookName, this.sheetName, capInTestcase , "Incoming" );
	}
	
	public void outgoingConfig() throws Exception
	{
		if ( ValidationHelper.isNotEmpty( bandOutTestcase ) )		
			bandTestCaseInitialize( this.path, this.workBookName, this.sheetName, bandOutTestcase , "Outgoing");		
		if ( ValidationHelper.isNotEmpty( tierOutTestcase ) )
			tierTestCaseInitialize(  this.path, this.workBookName, this.sheetName, tierOutTestcase , "Outgoing");
		if ( ValidationHelper.isNotEmpty( capOutTestcase ) )
			capConfiurationTestcase( this.path, this.workBookName, this.sheetName, capOutTestcase , "Outgoing" );
	}
	
	public boolean isDealPresent() throws Exception
	{
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		genHelperObj.waitforHeaderElement( "Account" );
		gridHelperObj.accountFilter( "grid_column_header_filtersearchGrid_account$paccName", "account", account, "Account"  );
		//SearchGridHelper.gridFilterAdvancedSearch( "PS_Detail_Deal_account_filter_txtBxID", account, "Account" );
		SearchGridHelper.gridFilterSearchWithTextBox( "PS_Detail_Deal_deal_txtbxID", account, "Deal Name" );
		SearchGridHelper.gridFilterSearchWithTextBox( "PS_Detail_Deal_contractNo_txtbxID", contractNo, "Contract No" );
		if(!dealPeriod.isEmpty())			
			CalendarHelper.setOnDate(  "PS_Detail_Deal_dealPeriod_calenderID", dealPeriod );
		return GridHelper.isValuePresent( "SearchGrid", account, "Account" );
	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Deal" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			dealMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( dealMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			String[] searchGridColumnsArr = strObj.stringSplitFirstLevel( searchScreenColumns );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genHelperObj.totalColumns( excelColumnNames );
		}
	}
	
	/*
	 * This method is for edit deal
	 */
	public void dealEdit() throws Exception {

		NavigationHelper.navigateToScreen("Deal");
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			dealMap = excelHolderObj.dataMap(paramVal);

			initializeInstanceVariables();
			//ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );	
			if (isDealPresent()) {				
				int row  = GridHelper.getRowNumber( "SearchGrid", account, "Account" );
				NavigationHelper.navigateToEdit( "SearchGrid", row );
				assertTrue( ElementHelper.isElementPresent( "//*[text()='Deal Details']" ), "Edit Deal Page is not opened" );
				DealDetailImpl detailObj = new DealDetailImpl( dealMap );				

				detailObj.editBasicDetails();
				incomingConfig();
				outgoingConfig();
				detailObj.dealSave();
				Log4jHelper.logInfo("Deal is deleted successfully :" + account);
			} else
				Log4jHelper.logInfo("Deal is not available with :" + account);
		}
	}
	
	/*
	 * This method is for Validate  delete
	 */
	public void dealValidate() throws Exception {

		NavigationHelper.navigateToScreen("Deal");
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			dealMap = excelHolderObj.dataMap(paramVal);
			dealPeriod = ExcelHolder.getKey( dealMap, "DealPeriod" );
			//partition = ExcelHolder.getKey(dealMap, "Partition");
			account = ExcelHolder.getKey( dealMap, "Account" );			
			contractNo = ExcelHolder.getKey( dealMap, "Contract No" );			
			if (isDealPresent()) {
				GridHelper.clickRow( "SearchGrid", account, "Account" );
				//assertEquals( GridHelper.getCellValue( "SearchGrid", 1, " Status" ), "Draft" );
				PSGenericHelper.waitForParentActionElementTOBeclickable( "Change Status" );
				NavigationHelper.navigateToAction( "Change Status", "Validate" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				ElementHelper.waitForElement( "//*[@id='window-scroll-panel']", searchScreenWaitSec );
				if(PopupHelper.isPresent())
					ButtonHelper.click( "OKButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				ButtonHelper.click( "SearchButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				assertTrue( isDealPresent(), "Deal is not available" );
				searchObj.waitForTaskCompletion( "SearchGrid" );
				assertTrue(GridHelper.isValuePresent("SearchGrid", "Validation Success", " Status"), "Deal validation is not successfull");
				Log4jHelper.logInfo("Deal is validated successfully :" + account);
			} else
				Log4jHelper.logInfo("Deal is not available with :" + account);
		}
	}
	
	/*
	 * This method is for checkIn  action
	 */
	public void dealCheckIn() throws Exception {

		NavigationHelper.navigateToScreen("Deal");
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			dealMap = excelHolderObj.dataMap(paramVal);
			dealPeriod = ExcelHolder.getKey( dealMap, "DealPeriod" );			
			account = ExcelHolder.getKey( dealMap, "Account" );			
			contractNo = ExcelHolder.getKey( dealMap, "Contract No" );			
			if (isDealPresent()) {
				GridHelper.clickRow( "SearchGrid", account, "Account" );
				assertEquals( GridHelper.getCellValue( "SearchGrid", 1, " Status" ), "Validation Success" );
				PSGenericHelper.waitForParentActionElementTOBeclickable( "Deal Actions" );
				NavigationHelper.navigateToAction( "Deal Actions", "Check In" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				ElementHelper.waitForElement( "//*[@id='window-scroll-panel']", searchScreenWaitSec );
				if(PopupHelper.isPresent())
				{
					ElementHelper.waitForElement( "//*[@id='window-scroll-panel']", searchScreenWaitSec );
					ButtonHelper.click( "OKButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					ButtonHelper.click( "SearchButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
				}								
			//	assertTrue(GridHelper.isValuePresent("SearchGrid", "Validated Success", " Status"), "Deal check in is not successfull");
				Log4jHelper.logInfo("Deal is validated successfully :" + account);
			} else
				Log4jHelper.logInfo("Deal is not available with :" + account);
		}
	}
	
	/*
	 * This method is for authorize  action
	 */
	public void dealAuthorize() throws Exception {

		NavigationHelper.navigateToScreen("Deal");
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			dealMap = excelHolderObj.dataMap(paramVal);
			dealPeriod = ExcelHolder.getKey( dealMap, "DealPeriod" );			
			account = ExcelHolder.getKey( dealMap, "Account" );			
			contractNo = ExcelHolder.getKey( dealMap, "Contract No" );			
			if (isDealPresent()) {
				GridHelper.clickRow( "SearchGrid", account, "Account" );
				assertEquals( GridHelper.getCellValue( "SearchGrid", 1, " Status" ), "Validation Success" );
				PSGenericHelper.waitForParentActionElementTOBeclickable( "Change Status" );
				NavigationHelper.navigateToAction( "Change Status", "Authorise" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				Thread.sleep( 2000 );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				ElementHelper.waitForElement( "//*[@id='window-scroll-panel']", searchScreenWaitSec );
				if(PopupHelper.isPresent())
				{					
					ElementHelper.waitForElement( "//button[contains(@id,'ok')]", searchScreenWaitSec );
					ButtonHelper.click( "OKButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );					
				}
				ButtonHelper.click( "SearchButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				assertTrue( isDealPresent() , "DEal is not available");
				searchObj.waitForTaskCompletion( "SearchGrid" );			
				assertTrue(GridHelper.isValuePresent("SearchGrid", "Authorised", " Status"), "Deal Authorisation is not successfull");
				Log4jHelper.logInfo("Deal is Authorised successfully :" + account);
			} else
				Log4jHelper.logInfo("Deal is not available with :" + account);
		}
	}
	
	/*
	 * This method is for change status draft  action
	 */
	public void dealChangeStatusDraft() throws Exception {

		NavigationHelper.navigateToScreen("Deal");
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			dealMap = excelHolderObj.dataMap(paramVal);
			dealPeriod = ExcelHolder.getKey( dealMap, "DealPeriod" );			
			account = ExcelHolder.getKey( dealMap, "Account" );			
			contractNo = ExcelHolder.getKey( dealMap, "Contract No" );			
			if (isDealPresent()) {
				GridHelper.clickRow( "SearchGrid", account, "Account" );
				PSGenericHelper.waitForParentActionElementTOBeclickable( "Change Status" );
				NavigationHelper.navigateToAction( "Change Status", "Draft" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				Thread.sleep( 2000 );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );			
				if(PopupHelper.isPresent())
				{					
					ElementHelper.waitForElement( "//button[contains(@id,'ok')]", searchScreenWaitSec );
					ButtonHelper.click( "OKButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );					
				}
				else
				{
				ButtonHelper.click( "SearchButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );						
				assertTrue(GridHelper.isValuePresent("SearchGrid", "Draft", " Status"), "Deal change status is not successfull");
				}
				Log4jHelper.logInfo("Deal is Authorised successfully :" + account);
				
			} else
				Log4jHelper.logInfo("Deal is not available with :" + account);
		}
	}
	
	/*
	 * This method is for view results  
	 */
	public void dealViewResults() throws Exception {

		NavigationHelper.navigateToScreen("Deal");
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			dealMap = excelHolderObj.dataMap(paramVal);
			account = ExcelHolder.getKey( dealMap, "Account" );
			dealPeriod = ExcelHolder.getKey( dealMap, "DealPeriod" );
			contractNo = ExcelHolder.getKey( dealMap, "Contract No" );
			if (isDealPresent()) {
				GridHelper.clickRow( "SearchGrid", account, "Account" );
				PSGenericHelper.waitForParentActionElementTOBeclickable( "View Results" );
				NavigationHelper.navigateToAction( "View Results", "View Results" );
				Thread.sleep( 2000 );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				BilateralResult biObj = new BilateralResult( dealMap );
				biObj.viewResults();	
				
				Log4jHelper.logInfo("Deal  bilateral results is validated successfully :" + account);
			} else
				Log4jHelper.logInfo("Deal is not available with :" + account);
		}
	}
	
	/*
	 * This method is for view audit 
	 */
	public void dealViewAudit() throws Exception {

		NavigationHelper.navigateToScreen("Deal");
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			dealMap = excelHolderObj.dataMap(paramVal);			
			account = ExcelHolder.getKey( dealMap, "Account" );
			contractNo = ExcelHolder.getKey( dealMap, "Contract No" );	
			String category =  ExcelHolder.getKey( dealMap, "Category" );
			String colHeader =  ExcelHolder.getKey( dealMap, "ColHeader" );
			String results =  ExcelHolder.getKey( dealMap, "Results" );
			dealPeriod = ExcelHolder.getKey( dealMap, "DealPeriod" );
			if (isDealPresent()) {
				GridHelper.clickRow( "SearchGrid", account, "Account" );
				PSGenericHelper.waitForParentActionElementTOBeclickable( "Audit" );
				NavigationHelper.navigateToAction( "Audit", "View Audit" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				assertTrue( ElementHelper.isElementPresent( "//*[text()='Category']" ), "Deal Audit Search Screen did not open" );
				if(ValidationHelper.isNotEmpty( category ))
					ComboBoxHelper.select( "dummyCategory_gwt_uid_", category );	
				ButtonHelper.click( "SearchButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				if(!results.isEmpty())
					verifyObj.validateData( "grid_column_header_searchGrid_", dealMap, "SearchGrid", colHeader, results );				
				Log4jHelper.logInfo("Deal is view audit is validated successfully :" + account);
			} else
				Log4jHelper.logInfo("Deal is not available with :" + account);
		}
	}
	/*
	 * This method is for new notes creation
	 */
	public void dealNewNote() throws Exception {

		NavigationHelper.navigateToScreen("Deal");
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			dealMap = excelHolderObj.dataMap(paramVal);

			partition = ExcelHolder.getKey(dealMap, "Partition");
			account = ExcelHolder.getKey( dealMap, "Account" );
			contractNo = ExcelHolder.getKey( dealMap, "Contract No" );	
			dealPeriod = ExcelHolder.getKey( dealMap, "DealPeriod" );
			String noteName =  ExcelHolder.getKey( dealMap, "NoteName" );
			String description =  ExcelHolder.getKey( dealMap, "Description" );
			String attachments =  ExcelHolder.getKey( dealMap, "Attachments" );
			if (isDealPresent()) {
				GridHelper.clickRow( "SearchGrid", account, "Account" );
				NavigationHelper.navigateToAction( "Notes", "New" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				assertTrue( ElementHelper.isElementPresent( "//*[text()='Description']" ), "Deal New Notes Screen did not open" );
				TextBoxHelper.type( "notName", noteName );				
				TextAreaHelper.type( "description", description );
				ButtonHelper.click( "noteDetail.save" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );				
				Log4jHelper.logInfo("Deal new note is created successfully :" + account);
			} else
				Log4jHelper.logInfo("Deal is not available with :" + account);
		}
	}
	
	/*
	 * This method is for change owner action
	 */
	public void dealChangeOwner() throws Exception {

		NavigationHelper.navigateToScreen("Deal");
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			dealMap = excelHolderObj.dataMap(paramVal);

			partition = ExcelHolder.getKey(dealMap, "Partition");
			account = ExcelHolder.getKey( dealMap, "Account" );
			contractNo = ExcelHolder.getKey( dealMap, "Contract No" );	
			dealPeriod = ExcelHolder.getKey( dealMap, "DealPeriod" );
			String username =  ExcelHolder.getKey( dealMap, "Username" );
			String partition =  ExcelHolder.getKey( dealMap, "Partition" );
			String role =  ExcelHolder.getKey( dealMap, "Role" );
			if (isDealPresent()) {
				GridHelper.clickRow( "SearchGrid", account, "Account" );
				PSGenericHelper.waitForParentActionElementTOBeclickable( "Deal Actions" );
				NavigationHelper.navigateToAction( "Deal Actions", "Change Owner" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				//assertTrue( ElementHelper.isElementPresent( "//*[text()='username']" ), "Deal change owner Screen did not open" );
				TextBoxHelper.type( "usrName", username );				
				ComboBoxHelper.select( "partitionTbl_ptnName_gwt_uid_", partition );
				ComboBoxHelper.select( "rolName_gwt_uid_", role );
				ButtonHelper.click( "SearchButton" );
				GenericHelper.waitForLoadmask();
				/*int row = GridHelper.getRowNumber( "SearchGrid", username, "Name" );
				if(row ==0 )
					GridHelper.clickRow( "SearchGrid", row, "Name" );*/
				
				ButtonHelper.click( "CancelButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );				
				Log4jHelper.logInfo("Deal change owner is created successfully :" + account);
			} else
				Log4jHelper.logInfo("Deal is not available with :" + account);
		}
	}
	/*
	 * This method is for deal history
	 */
	public void dealViewHistory() throws Exception {

		NavigationHelper.navigateToScreen("Deal");
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			dealMap = excelHolderObj.dataMap(paramVal);

			partition = ExcelHolder.getKey(dealMap, "Partition");
			account = ExcelHolder.getKey( dealMap, "Account" );
			contractNo = ExcelHolder.getKey( dealMap, "Contract No" );
			dealPeriod = ExcelHolder.getKey( dealMap, "DealPeriod" );
			String noteName =  ExcelHolder.getKey( dealMap, "NoteName" );			
			String colHeader =  ExcelHolder.getKey( dealMap, "ColHeader" );
			String results =  ExcelHolder.getKey( dealMap, "Results" );
			if (isDealPresent()) {
				GridHelper.clickRow( "SearchGrid", account, "Account" );
				NavigationHelper.navigateToAction( "Notes", "New" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				genHelperObj.waitforPopupHeaderElement( "Username" );
				TextBoxHelper.type( "note$notName", noteName );				
				if(!results.isEmpty())			
					verifyObj.validateData( "grid_column_header_searchGrid_", dealMap, "SearchGrid", colHeader, results );
				
				ButtonHelper.click( "cancel" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );				
				Log4jHelper.logInfo("Deal history is validated successfully :" + account);
			} else
				Log4jHelper.logInfo("Deal is not available with :" + account);
		}
	}
	/*
	 * This method is for Deal  delete
	 */
	public void dealDelete() throws Exception {

		NavigationHelper.navigateToScreen("Deal");
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			dealMap = excelHolderObj.dataMap(paramVal);

			partition = ExcelHolder.getKey(dealMap, "Partition");
			account = ExcelHolder.getKey( dealMap, "Account" );
			contractNo = ExcelHolder.getKey( dealMap, "Contract No" );
			dealPeriod = ExcelHolder.getKey( dealMap, "DealPeriod" );
			genHelperObj.selectPartitionFilter(partition, "Non Deleted Items");		

			if (isDealPresent()) {
				genHelperObj.clickDeleteOrUnDeleteAction(account, "Account", "Delete");
				genHelperObj.selectPartitionFilter(partition, "Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", account, "Account"), account);
				Log4jHelper.logInfo("Deal is deleted successfully :" + account);
			} else
				Log4jHelper.logInfo("Deal is not available with :" + account);
		}
	}

	/*
	 * This method is for deal un delete
	 */
	public void dealUnDelete() throws Exception {

		NavigationHelper.navigateToScreen("Deal");
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			dealMap = excelHolderObj.dataMap(paramVal);

			partition = ExcelHolder.getKey(dealMap, "Partition");
			account = ExcelHolder.getKey( dealMap, "Account" );
			contractNo = ExcelHolder.getKey( dealMap, "Contract No" );
			dealPeriod = ExcelHolder.getKey( dealMap, "DealPeriod" );
			genHelperObj.selectPartitionFilter(partition, "Deleted Items");		

			if (isDealPresent()) {
				genHelperObj.clickDeleteOrUnDeleteAction(account, "Account", "Undelete");
				genHelperObj.selectPartitionFilter(partition, "Non Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", account, "Account"), account);
				Log4jHelper.logInfo("Deal is un deleted successfully :" + account);
			} else
				Log4jHelper.logInfo("Deal is not available with :" + account);
		}

	}
	

	private void bandTestCaseInitialize( String path, String workBook, String sheetName, String testCaseName, String direction ) throws Exception
	{
		this.path = path;
		this.workBookName = workBook;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		configMap = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( configMap );	
		colSize = excelHolderObj.totalColumns();
		for ( int paramVal = 0; paramVal < colSize; paramVal++ )
		{
			mapObj = excelHolderObj.dataMap( paramVal );
		DealBandConfiguration bandObj = new DealBandConfiguration( mapObj );
		bandObj.bandConfig( direction );
		}
	}
	
	private void tierTestCaseInitialize( String path, String workBook, String sheetName, String testCaseName , String direction) throws Exception
	{
		this.path = path;
		this.workBookName = workBook;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		configMap = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( configMap );	
		colSize = excelHolderObj.totalColumns();
		for ( int paramVal = 0; paramVal < colSize; paramVal++ )
		{
			mapObj = excelHolderObj.dataMap( paramVal );
			DealTierConfiguration tierObj = new DealTierConfiguration( mapObj );
			tierObj.tierConfiguration( direction );
		}
	}
	
	private void capConfiurationTestcase(String path, String workBook, String sheetName, String testCaseName , String direction) throws Exception
	{
		/*this.path = path;
		this.workBookName = workBook;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;*/
		configMap = excelData.readDataByColumn( path, workBook, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( configMap );	
		colSize = excelHolderObj.totalColumns();
		for ( int paramVal = 0; paramVal < colSize; paramVal++ )
		{
		mapObj = excelHolderObj.dataMap( paramVal );
		CapsConfiguration cpObj = new CapsConfiguration( mapObj );
		cpObj.capConfig( direction );
		cpObj.saveCapConfig();
		}
		ButtonHelper.click( "PS_Detail_Deal_Cap_CloseBtn" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		
	}

	public void initializeInstanceVariables() throws Exception
	{
		partition = ExcelHolder.getKey( dealMap, "Partition" );
		account = ExcelHolder.getKey( dealMap, "Account" );
		dealPeriod = ExcelHolder.getKey( dealMap, "DealPeriod" );
		contractNo = ExcelHolder.getKey( dealMap, "Contract No" );
		bandInTestcase = ExcelHolder.getKey( dealMap, "BandInTestcase" );
		bandOutTestcase = ExcelHolder.getKey( dealMap, "BandOutTestcase" );
		tierInTestcase = ExcelHolder.getKey( dealMap, "TierInTestcase" );
		tierOutTestcase = ExcelHolder.getKey( dealMap, "TierOutTestcase" );
		capInTestcase = ExcelHolder.getKey( dealMap, "CapInTestcase" );
		capOutTestcase = ExcelHolder.getKey( dealMap, "CapOutTestcase" );
	}

}
  