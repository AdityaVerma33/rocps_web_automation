package com.subex.rocps.automation.helpers.application.partnerConfiguration;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.account.AccountActionImpl;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.account.AccountSearchImpl;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.billprofile.ApprovalWorkflowTab;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.billprofile.BillProfileBillProductionTab;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.billprofile.BillProfileDetailsTab;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.billprofile.BillProfileEmailTab;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.billprofile.BillProfilePaymentConfigTab;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.billprofile.SettlementsTab;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class BillProfile extends PSAcceptanceTest
{

	protected ExcelReader excelReaderObj = null;
	protected ExcelHolder excelHolderObj = null;
	protected HashMap<String, ArrayList<String>> bipExcelMap = null;
	protected Map<String, String> bipMap = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;

	protected String clientPartition;
	protected String accountName;
	protected String profileType;
	protected String billDoctype;
	protected String emailDigi;
	protected String isM2Mdigi;
	protected String imsiChargesDigi;
	
	protected String profileName;

	int paramVal;
	protected int colSize;
	protected PSGenericHelper genHelperObj = new PSGenericHelper();
	PSStringUtils strObj = new PSStringUtils();

	/*
	 * Constructor : Initialising the excel
	 */
	public BillProfile( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelReaderObj = new ExcelReader();
		bipExcelMap = excelReaderObj.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( bipExcelMap );
		colSize = excelHolderObj.totalColumns();

	}

	/*
	 * Constructor : Initialising the excel
	 * 
	 * @Param : occurence of test case in a sheet
	 */
	public BillProfile( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		excelReaderObj = new ExcelReader();
		bipExcelMap = excelReaderObj.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, this.occurence );
		excelHolderObj = new ExcelHolder( bipExcelMap );
		colSize = excelHolderObj.totalColumns();

	}

	/*
	 * @Method: for creating bill profile Internal Map is informed with key:
	 * param name and value: param value
	 */
	public void billProfileCreation() throws Exception
	{
		try
		{

			NavigationHelper.navigateToScreen( "Account" );

			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				// NavigationHelper.navigateToScreen("Account");
				bipMap = excelHolderObj.dataMap( paramVal );

				// initialising instance variables
				intializeInstVariables();

				String accSrchScrColHeader = "Account Name";
				ButtonHelper.click( "ClearButton" );
				boolean isAccountPresent = genHelperObj.isGridTextValuePresent( "accountName_Detail", accountName, accSrchScrColHeader );
				if ( isAccountPresent )
				{
					GridHelper.clickRow( "SearchGrid", accountName, "Account Name" );

					boolean isBillProfilePresent = genHelperObj.validateActionText( "Bill Profile", profileName );
					if ( !isBillProfilePresent )
					{
						newBillProfileConfig();
					}
					else
					{
						Log4jHelper.logInfo( "Bill profile with name exists :" + profileName );
					}

				}
				else
				{
					Log4jHelper.logInfo( " Account does not exist :" + bipMap.get( "AccountName" ) );
				}
			}

		}
		catch ( Exception e )
		{

			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * @Method: for editing bill profile Internal Map is informed with key:
	 * param name and value: param value
	 */
	public void billProfileEdit() throws Exception
	{
		try
		{

			NavigationHelper.navigateToScreen( "Account" );

			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				// NavigationHelper.navigateToScreen("Account");
				bipMap = excelHolderObj.dataMap( paramVal );

				// initialising instance variables
				intializeInstVariables();

				String accSrchScrColHeader = "Account Name";
				ButtonHelper.click( "ClearButton" );
				boolean isAccountPresent = genHelperObj.isGridTextValuePresent( "accountName_Detail", accountName, accSrchScrColHeader );
				if ( isAccountPresent )
				{
					GridHelper.clickRow( "SearchGrid", accountName, "Account Name" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					boolean isBillProfilePresent = genHelperObj.validateActionText( "Bill Profile", profileName );
					if ( isBillProfilePresent )
					{
						PSGenericHelper.waitForParentActionElementTOBeclickable( "Bill Profile" );
						clickEditAction( "Bill Profile", profileName );
						editBillProfileConfig();
					}
					else
					{
						Log4jHelper.logInfo( "Bill profile with name  does not exist :" + profileName );
					}

				}
				else
				{
					Log4jHelper.logInfo( " Account does not exist :" + bipMap.get( "AccountName" ) );
				}
			}

		}
		catch ( Exception e )
		{

			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * intitialisin the variables performing mandatory field check
	 */
	protected void intializeInstVariables()
	{

		clientPartition = bipMap.get( "Partition" );
		accountName = bipMap.get( "AccountName" );
		profileType = bipMap.get( "ProfileType" );
		profileName = bipMap.get( "ProfileName" );
	}

	/*
	 * Method for checking if account exists Returns true : if account exists
	 * Returns false : if account does not exist
	 */
	protected boolean isAccountPresent() throws Exception
	{

		SearchGridHelper.gridFilterSearchWithTextBox( "accountName_Detail", accountName, "Account Name" );
		return GridHelper.isValuePresent( "SearchGrid", accountName, "Account Name" );
	}

	/*
	 * Method will create new bill profile
	 * 
	 */
	protected void newBillProfileConfig() throws Exception
	{

		AccountActionImpl accActionObj = new AccountActionImpl();
		accActionObj.billProfileAction();

		ComboBoxHelper.select( "detail_bip_profileType_ComboId", profileType );
		genHelperObj.waitforEntityElement();
		PSEntityComboHelper.selectUsingGridFilterTextBox( "Detail_parentAccountEntityId", "Account Search", "accountName_Detail", accountName, "Account Name" );

		BillProfileDetailsTab bipDetailTabObj = new BillProfileDetailsTab( bipMap );
		bipDetailTabObj.detailTabConfig();

		BillProfileBillProductionTab bipBillProdTabObj = new BillProfileBillProductionTab( bipMap );
		bipBillProdTabObj.billProductionTabConfig();

		BillProfilePaymentConfigTab bipPaymtTabObj = new BillProfilePaymentConfigTab( bipMap );
		bipPaymtTabObj.paymentConfigurationTabConfig();
		
		BillProfileEmailTab billProfileEmailTab = new BillProfileEmailTab( bipMap );
		billProfileEmailTab.billProfileEmailTabConfig();
		
		SettlementsTab settlementsTab = new SettlementsTab( bipMap );
		settlementsTab.settlementsTabConfig();

		ApprovalWorkflowTab approvalWorkflowTab = new ApprovalWorkflowTab( bipMap );
		approvalWorkflowTab.approvalWflwTabConfig();
		
		extraOptionsDigi();

		ButtonHelper.click( "detail_bip_billProfileSave_btnId" );

		settlementsTab.confirmationAutoInclPopup();
		GenericHelper.waitForLoadmask();
		
	
		
		
		Log4jHelper.logInfo( "Bill profile is created successfully :" + profileName );

	}

	private void extraOptionsDigi() throws Exception {
		billDoctype=bipMap.get("BillDocType");
		emailDigi=bipMap.get("Email");
		isM2Mdigi=bipMap.get("IsM2M");
		imsiChargesDigi=bipMap.get("IMSI-Charges");
		
		
		if (ValidationHelper.isNotEmpty( billDoctype ))
			ComboBoxHelper.select( "digi_extra_billdoc_type", billDoctype );
		
		if (ValidationHelper.isNotEmpty( emailDigi ))
			TextBoxHelper.type( "digi_extra_email", emailDigi );
		
		if ( ValidationHelper.isTrue( isM2Mdigi ) )
			CheckBoxHelper.check( "digi_extra_isM2M" );
		
		if ( ValidationHelper.isTrue( imsiChargesDigi ) )
			CheckBoxHelper.check( "digi_extra_imsiCharges" );
	}

	/*
	 * Method will edit  bill profile
	 * 
	 */
	protected void editBillProfileConfig() throws Exception
	{

		assertEquals( ComboBoxHelper.getValue( "detail_bip_profileType_ComboId" ), profileType, "Profile type is not matched" );
		assertEquals( EntityComboHelper.getValue( "account" ), accountName, "Account Name is not matched" );

		BillProfileDetailsTab bipDetailTabObj = new BillProfileDetailsTab( bipMap );
		bipDetailTabObj.editdetailTabConfig();

		BillProfileBillProductionTab bipBillProdTabObj = new BillProfileBillProductionTab( bipMap );
		bipBillProdTabObj.editbillProductionTabConfig();

		BillProfilePaymentConfigTab bipPaymtTabObj = new BillProfilePaymentConfigTab( bipMap );
		bipPaymtTabObj.editpaymentConfigurationTabConfig();

		ApprovalWorkflowTab approvalWorkflowTab = new ApprovalWorkflowTab( bipMap );
		approvalWorkflowTab.approvalWflwTabConfig();

		ButtonHelper.click( "detail_bip_billProfileSave_btnId" );
		GenericHelper.waitForLoadmask();
		Log4jHelper.logInfo( "Bill profile is updated successfully :" + profileName );

	}

	/*
	 * Method: For click on edit action of bill profile
	 */
	private void clickEditAction( String parentText, String childText ) throws Exception
	{
		boolean flag = false;

		try
		{
			parentText = GenericHelper.getORProperty( parentText );
			String parentXpath = or.getProperty( "GroupActionName" ).replace( "actionName", parentText );
			String childXpath = or.getProperty( "childTextXpath" ).replace( "partialText", childText );

			ElementHelper.waitForClickableElement( parentXpath, searchScreenWaitSec );
			MouseHelper.click( parentXpath );
			ElementHelper.waitForClickableElement( childXpath, searchScreenWaitSec );
			MouseHelper.click( childXpath );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Bill Profile" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			bipMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( bipMap, "SearchScreenColumns" );
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
	 * This method is to validate search screen columns for bill period
	 */
	public void billPeriodSearchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Bill Period" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			bipMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( bipMap, "SearchScreenColumns" );
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
	 * Method to view aggregation results from account screen
	 */
	public void navigateToViewAggregationResults() throws Exception
	{
		NavigationHelper.navigateToScreen( "Bill Profile" );

		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			bipMap = excelHolderObj.dataMap( paramVal );
			accountName = ExcelHolder.getKey( bipMap, "Account Name" );
			String billProfile = ExcelHolder.getKey( bipMap, "BillProfile" );
			String aggregationName = ExcelHolder.getKey( bipMap, "AggregationName" );

			boolean iBillProfilePresent = genHelperObj.isGridTextValuePresent( "pbipName", billProfile, "Bill Profile Name" );
			if ( iBillProfilePresent )
			{
				GridHelper.clickRow( "SearchGrid", billProfile, "Bill Profile Name" );
				boolean isAggrResultPresent = genHelperObj.validateActionText( "View Aggregation Results", aggregationName );
				if ( isAggrResultPresent )
				{
					GridHelper.clickRow( "SearchGrid", billProfile, "Bill Profile Name" );
					PSGenericHelper.waitForParentActionElementTOBeclickable( "View Aggregation Results" );
					NavigationHelper.navigateToAction( "View Aggregation Results", aggregationName );
					GenericHelper.waitForLoadmask( detailScreenWaitSec );
					assertEquals( NavigationHelper.getScreenTitle(), "Results - VoiceAggr...", "Aggregation screen is not opened" );
					AggregationResult aggrObj = new AggregationResult( path, workBookName, sheetName, testCaseName );
					aggrObj.accountViewAggregationResults();
					Log4jHelper.logInfo( "Aggregation result is validated successfully" );
				}
			}
		}
	}

	/*
	 * Method to view Balance
	 */
	public void navigateToViewBalanceAction() throws Exception
	{
		NavigationHelper.navigateToScreen( "Bill Profile" );

		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			bipMap = excelHolderObj.dataMap( paramVal );
			accountName = ExcelHolder.getKey( bipMap, "Account Name" );
			String billProfile = ExcelHolder.getKey( bipMap, "BillProfile" );
			String colHeaders = ExcelHolder.getKey( bipMap, "ColHeaders" );
			String results = ExcelHolder.getKey( bipMap, "Results" );
			boolean iBillProfilePresent = genHelperObj.isGridTextValuePresent( "pbipName", billProfile, "Bill Profile Name" );
			if ( iBillProfilePresent )
			{
				GridHelper.clickRow( "SearchGrid", billProfile, "Bill Profile Name" );
				boolean isViewBalancePresent = genHelperObj.validateActionText( "View Balance", "View Balance" );
				if ( isViewBalancePresent )
				{
					GridHelper.clickRow( "SearchGrid", billProfile, "Bill Profile Name" );
					PSGenericHelper.waitForParentActionElementTOBeclickable( "View Balance" );
					NavigationHelper.navigateToAction( "View Balance", "View Balance" );
					GenericHelper.waitForLoadmask( detailScreenWaitSec );
					assertEquals( NavigationHelper.getScreenTitle(), "View Balance Search", "View Balance screen is not opened" );
					DataVerificationHelper verifyObj = new DataVerificationHelper();
					verifyObj.validateData( "popupWindow", "grid_column_header_searchGrid_", bipMap, "searchGrid", colHeaders, results );
					Log4jHelper.logInfo( "Bill profile balance is validated successfully" );
				}
			}
		}
	}

}
