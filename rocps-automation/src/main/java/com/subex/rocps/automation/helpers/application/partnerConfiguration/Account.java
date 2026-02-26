package com.subex.rocps.automation.helpers.application.partnerConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.matchandrate.Operator;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.account.AccountActionImpl;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.account.AccountDetailImpl;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.account.AccountSearchImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class Account extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> accExcelMap = null;
	protected Map<String, String> accMap = null;
	protected ExcelHolder excelHolderObj = null;
	String path;
	String workBookName;
	String sheetName;
	String testCaseName;
	int occurence;
	protected List<String> columnList = null;
	protected Map<String, String> mapObj = null;
	protected String clientPartition;
	protected String accountName;
	protected String customerType;
	String columnHeader;
	String results;
	int colSize;
	int paramVal;
	protected String terminateFrom;
	protected String terminateComment;
	protected PSGenericHelper genHelperObj = new PSGenericHelper();
	AccountActionImpl accActionObj = new AccountActionImpl();
	DataVerificationHelper verifyObj = new DataVerificationHelper();
	String terminateActualVal;
	PSStringUtils strObj = new PSStringUtils();
	AccountSearchImpl accSearchObj = new AccountSearchImpl();
	/*
	 * Constructor : Initialising the excel
	 */
	public Account( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		accExcelMap = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( accExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Constructor : Initialising the excel
	 * 
	 * @Param : occurence of test case in a sheet
	 */
	public Account( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		accExcelMap = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, this.occurence );
		excelHolderObj = new ExcelHolder( accExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the account
	 * 
	 * @method : isAccountPresent returns false then account is configured
	 * isAccountPresent returns true then account is not configured
	 */
	public void accountCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Account" );

			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				accMap = excelHolderObj.dataMap( paramVal );
				initializeInstanceVariables();

				AccountActionImpl accActionObj = new AccountActionImpl();
				AccountDetailImpl accDetailTabObj = new AccountDetailImpl( accMap );

				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isAccountPresent = genHelperObj.isGridTextValuePresent( "accountName_Detail", accountName, "Account Name" );
				if ( !isAccountPresent )
				{
					accActionObj.clickNewAction( clientPartition );
					GenericHelper.waitForLoadmask();
					NavigationHelper.selectPartition(clientPartition);
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					accDetailTabObj.openAccountDetail();

					accDetailTabObj.detailTabConfig();
					ContactInformation();
					accDetailTabObj.saveAccount( accountName );
					Log4jHelper.logInfo( "Account is created successfully with name " + accountName );

				}
				else
				{
					Log4jHelper.logInfo( "Account is available with name " + accountName );
				}

			}

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public void ContactInformation() throws Exception
	{
		AccountDetailImpl accDetailTabObj = new AccountDetailImpl( accMap );
		accDetailTabObj.navigateToContactTab();
		accDetailTabObj.addressDetails();
		accDetailTabObj.contactDetails();
	}

	/*
	 * Method: for initializing the instance variables
	 */
	public void initializeInstanceVariables()
	{
		clientPartition = accMap.get( "Partition" );
		accountName = accMap.get( "AccountName" );
		customerType = accMap.get( "CustomerType" );
	}
	
	public void editAccountCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Account" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				accMap = excelHolderObj.dataMap( paramVal );
				initializeInstanceVariables();

				AccountActionImpl accActionObj = new AccountActionImpl();
				AccountDetailImpl accDetailTabObj = new AccountDetailImpl( accMap );

				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isAccountPresent = genHelperObj.isGridTextValuePresent( "accountName_Detail", accountName, "Account Name" );
				if ( isAccountPresent )
				{
					int row = GridHelper.getRowNumber( "SearchGrid", accountName, "Account Name" );
					NavigationHelper.navigateToEdit( "SearchGrid", row );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					assertEquals(ComboBoxHelper.getValue("accountType_Detail"), customerType);
					assertEquals(TextBoxHelper.getValue("accountName_Detail"), accountName);

					accDetailTabObj.editAccountDetailsConfig();
					accDetailTabObj.editAccountCategory();
					accDetailTabObj.editBankDetails();
					accDetailTabObj.extraArgument();
					ContactInformation();
					accDetailTabObj.saveAccount( accountName );
					Log4jHelper.logInfo( "Account is edited successfully with name " + accountName );

				}
				else				
					Log4jHelper.logInfo( "Account is not available with name " + accountName );
			}

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * Method for operator creation from account screen
	 */
	public void navigateToOperator() throws Exception
	{
		NavigationHelper.navigateToScreen( "Account" );

		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			accMap = excelHolderObj.dataMap( paramVal );
			accountName = ExcelHolder.getKey( accMap, "Account Name" );
			String operatorName = ExcelHolder.getKey( accMap, "Name" );
			boolean isAccountPresent = genHelperObj.isGridTextValuePresent( "accountName_Detail", accountName, "Account Name" );
			if ( isAccountPresent )
			{
				GridHelper.clickRow( "SearchGrid", accountName, "Account" );
				boolean isOperatorPresent = genHelperObj.validateActionText( "Operator", operatorName );
				if (! isOperatorPresent )
				{
					accActionObj.operatorAction();
					Operator opObj = new Operator( path, workBookName, sheetName, testCaseName );
					opObj.initializeVariables( accMap );
					opObj.newOperator();
					opObj.saveOperator();
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					assertTrue( isOperatorPresent , "Oerator is not saved");
				}
				else
					Log4jHelper.logInfo( "Oerator is already present" + operatorName );
			}
			else
				Log4jHelper.logInfo( "Account is not presnet" + accountName );
		}
	}

	/*
	 * Method to view aggregation results from account screen
	 */
	public void navigateToViewAggregationResults() throws Exception
	{
		NavigationHelper.navigateToScreen( "Account" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			accMap = excelHolderObj.dataMap( paramVal );
			accountName = ExcelHolder.getKey( accMap, "Account Name" );
			String billProfile = ExcelHolder.getKey( accMap, "BillProfile" );
			String aggregationName = ExcelHolder.getKey( accMap, "AggregationName" );
			
			String billProfileName = accSearchObj.retriveBillProfileValue( billProfile, accountName );
			
			boolean isAccountPresent = genHelperObj.isGridTextValuePresent( "accountName_Detail", accountName, "Account Name" );
			if ( isAccountPresent )
			{
				GridHelper.clickRow( "SearchGrid", accountName, "Account" );
				boolean isAggrResultPresent = genHelperObj.validateActionText( "View Aggregation Results", billProfileName );
				if ( isAggrResultPresent )
				{
					accActionObj.viewAggreagtionResultsAction( billProfileName, aggregationName );
					AggregationResult aggrObj = new AggregationResult( path, workBookName, sheetName, testCaseName );
					aggrObj.accountViewAggregationResults();
					Log4jHelper.logInfo( "Aggregation result is validated successfully" );
				}
			}
		}
	}
	
	/*
	 * Method to terminate account
	 */
	public void accountChangeStatus() throws Exception
	{
		NavigationHelper.navigateToScreen( "Account" );

		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			accMap = excelHolderObj.dataMap( paramVal );
			accountName = ExcelHolder.getKey( accMap, "AccountName" );
			terminateFrom = accMap.get("TerminateFrom");
			terminateComment = accMap.get("TerminateComment");
			columnHeader = accMap.get( "ColumnHeader" );
			results = accMap.get( "Results" );
			if(terminateFrom.contains( "00:00:00" ))			
				terminateActualVal = terminateFrom.replace( "00:00:00", "" );
			else
				terminateActualVal = terminateFrom;
			
			boolean isAccountPresent = genHelperObj.isGridTextValuePresent( "accountName_Detail", accountName, "Account Name" );
			if ( isAccountPresent )
			{
				GridHelper.clickRow( "SearchGrid", accountName, "Account" );
				accActionObj.changeStatusAction();
				ButtonHelper.click( "OKButton" );
				GenericHelper.waitForLoadmask( detailScreenWaitSec );
				AccountDetailImpl accDetailTabObj = new AccountDetailImpl( accMap );
				accDetailTabObj.terminateAccount(terminateActualVal, terminateComment);				
				SearchGridHelper.gridFilterSearchWithComboBox( "paccIsMarkedToTerminate_gwt_uid_", "Yes", "Marked For Termination" );				
				assertTrue( GridHelper.isValuePresent( "SearchGrid", accountName, "Account Name" ), "Account Termination did not happen");
				verifyObj.validateData( "grid_column_header_searchGrid_", columnHeader, "SearchGrid", results );
				Log4jHelper.logInfo( "Account is terminated Successfully " +accountName);
			}
		}
		
	}
	
	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{		
		NavigationHelper.navigateToScreen( "Account" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			accMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( accMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			String[] searchGridColumnsArr = strObj.stringSplitFirstLevel( searchScreenColumns );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genHelperObj.totalColumns( excelColumnNames );
		}

	}
	
	public void viewProducts() throws Exception
	{
		NavigationHelper.navigateToScreen( "Account" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			accMap = excelHolderObj.dataMap( paramVal );			
			accountName = accMap.get( "AccountName" );	
			String billProfile = ExcelHolder.getKey( accMap, "BillProfile" );
			String colHeaders = ExcelHolder.getKey( accMap, "ColHeaders" );
			String results = ExcelHolder.getKey( accMap, "Results" );
			boolean isAccountPresent = genHelperObj.isGridTextValuePresent( "accountName_Detail", accountName, "Account Name" );
			if ( isAccountPresent )
			{				
				GridHelper.clickRow( "SearchGrid", accountName, "Account Name" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				String billProfileName = accSearchObj.retriveBillProfileValue( billProfile, accountName );
				accActionObj.viewProductAction( billProfileName );
				if(!results.isEmpty())
					verifyObj.validateData( "grid_column_header_searchGrid_", colHeaders, "SearchGrid", results );
				
				Log4jHelper.logInfo( "Account product bundle is successfully validated " + accountName );
			}
			}
		
	}
	
	public void viewEventMatchRules() throws Exception
	{
		NavigationHelper.navigateToScreen( "Account" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			accMap = excelHolderObj.dataMap( paramVal );			
			accountName = accMap.get( "AccountName" );	
			String eventMatchRule = ExcelHolder.getKey( accMap, "EventMatchRule" );
			//String colHeaders = ExcelHolder.getKey( accMap, "ColHeaders" );
			//String results = ExcelHolder.getKey( accMap, "Results" );
			boolean isAccountPresent = genHelperObj.isGridTextValuePresent( "accountName_Detail", accountName, "Account Name" );
			if ( isAccountPresent )
			{				
				GridHelper.clickRow( "SearchGrid", accountName, "Account Name" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );				
				accActionObj.viewEventMatchRules( eventMatchRule );
				ButtonHelper.click( "CancelButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );				
			}
			}
	}
	
	public void viewEventUsage() throws Exception
	{
		NavigationHelper.navigateToScreen( "Account" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			accMap = excelHolderObj.dataMap( paramVal );			
			accountName = accMap.get( "AccountName" );				
			String billProfile = ExcelHolder.getKey( accMap, "BillProfile" );
			String colHeaders = ExcelHolder.getKey( accMap, "ColHeaders" );
			String results = ExcelHolder.getKey( accMap, "Results" );
			boolean isAccountPresent = genHelperObj.isGridTextValuePresent( "accountName_Detail", accountName, "Account Name" );
			if ( isAccountPresent )
			{				
				GridHelper.clickRow( "SearchGrid", accountName, "Account Name" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				String billProfileName = accSearchObj.retriveBillProfileValue( billProfile, accountName );
				accActionObj.viewEventUsage( billProfileName );
				if(!results.isEmpty())
					verifyObj.validateData( "grid_column_header_searchGrid_", colHeaders, "SearchGrid", results );
				
				Log4jHelper.logInfo( "Account view Event Usage action is successfully validated " + accountName );
			}
			}
	}
	
	
	/*public void exportAccount() throws Exception
	{
		NavigationHelper.navigateToScreen( "Account" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			accMap = excelHolderObj.dataMap( paramVal );			
			accountName = accMap.get( "AccountName" );	
			String 	exportContentRow = accMap.get( "ExportContentRow" );	
			String exportContentHeader = accMap.get( "ExportContentHeader" );
			boolean isAccountPresent = genHelperObj.isGridTextValuePresent( "accountName_Detail", accountName, "Account Name" );
			if ( isAccountPresent )
			{				
				GridHelper.clickRow( "SearchGrid", accountName, "Account Name" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
			
				NavigationHelper.navigateToAction( "Export", "Selected Rows" );	
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				FileExport fileObj = new FileExport();
				fileObj.exportSelected( accountName );
				Log4jHelper.logInfo( "Account export Action is successfully validated " + accountName );
			}
			}
	}*/

	/*
	 * This method is to delete account
	 */
	public void accountDelete() throws Exception
	{
		NavigationHelper.navigateToScreen( "Account" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			accMap = excelHolderObj.dataMap( paramVal );
			clientPartition = accMap.get( "Partition" );
			accountName = accMap.get( "AccountName" );
			genHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
			boolean isAccountPresent = genHelperObj.isGridTextValuePresent( "accountName_Detail", accountName, "Account Name" );
			if ( isAccountPresent )
			{
				genHelperObj.clickDeleteOrUnDeleteAction( accountName, "Name", "Delete" );
				GenericHelper.waitForLoadmask();
				genHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", accountName, "Name" ), accountName );
				Log4jHelper.logInfo( "Account is deleted successfully with name " + accountName );

			}
			else			
				Log4jHelper.logInfo( "Account is not available with name " + accountName );
		}
	}

	/*
	 * This method is to un delete account
	 */
	public void accountUnDelete() throws Exception
	{
		NavigationHelper.navigateToScreen( "Account" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			accMap = excelHolderObj.dataMap( paramVal );
			clientPartition = accMap.get( "Partition" );
			accountName = accMap.get( "AccountName" );

			genHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
			GenericHelper.waitForLoadmask();
			boolean isAccountPresent = genHelperObj.isGridTextValuePresent( "accountName_Detail", accountName, "Account Name" );

			if ( isAccountPresent )
			{
				genHelperObj.clickDeleteOrUnDeleteAction( accountName, "Name", "Undelete" );
				GenericHelper.waitForLoadmask();
				genHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", accountName, "Name" ), accountName );
				Log4jHelper.logInfo( "Account is un deleted successfully : " + accountName );

			}
			else			
				Log4jHelper.logInfo( "Account is not available : " + accountName );

		}
	}	
}
