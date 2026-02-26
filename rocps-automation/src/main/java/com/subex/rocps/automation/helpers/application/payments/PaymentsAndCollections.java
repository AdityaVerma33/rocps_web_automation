package com.subex.rocps.automation.helpers.application.payments;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.payments.paymAndCollections.PaymAndCollectionsDetailImpl;
import com.subex.rocps.automation.helpers.application.payments.paymAndCollections.PaymentAndCollectionsActImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class PaymentsAndCollections extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> paymentsAndCollectionsExcelMap = null;
	protected Map<String, String> paymentsAndCollectionsMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	protected String paymentsAndCollectionsName;
	protected String clientPartition;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
	PaymentAndCollectionsActImpl payAndCollectionsActImpl = new PaymentAndCollectionsActImpl();

	/**
	 * Constructor : Initializing the excel without occurrence
	 * 
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception
	 */
	public PaymentsAndCollections( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		paymentsAndCollectionsExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( paymentsAndCollectionsExcelMap );
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
	public PaymentsAndCollections( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		paymentsAndCollectionsExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( paymentsAndCollectionsExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		paymentsAndCollectionsName = ExcelHolder.getKey( map, "TransacionReference" );

	}

	/*
	 * This method is for 'Payments And Collections' screen common method
	 */
	private void paymentsAndCollectionsScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Payments And Collections" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		paymentsAndCollectionsMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Type" );
	}

	/*
	 * This method is for 'Payments And Collections' screen column validation
	 */
	public void paymentsAndCollectionsColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				paymentsAndCollectionsScreen();
				colmHdrs = ExcelHolder.getKey( paymentsAndCollectionsMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Type", colmHdrs, "Payments And Collections" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Payments And Collections' creation
	 */
	public void paymentsAndCollectionsCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				paymentsAndCollectionsScreen();
				initializeVariable( paymentsAndCollectionsMap );
				String paymetCollectActName = ExcelHolder.getKey( paymentsAndCollectionsMap, "PaymentCollectionAction" );
				paymetCollectActName = "New " + paymetCollectActName;
				boolean ispaymentsAndCollectionsPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_paymAndCollect_transactRef_txtId", paymentsAndCollectionsName, "Transaction Reference" );
				if ( !ispaymentsAndCollectionsPresent )
				{
					payAndCollectionsActImpl.clickOnActionWithPartition( "Create", paymetCollectActName, clientPartition );
					PaymAndCollectionsDetailImpl payAndCollectionsDetailImpOb = new PaymAndCollectionsDetailImpl( paymentsAndCollectionsMap );
					payAndCollectionsDetailImpOb.createPaymentCollections();
					checkForAutoAuthorize( paymentsAndCollectionsName );
					Log4jHelper.logInfo( "Payments And Collections is successfuly saved with " + paymentsAndCollectionsName );
				}
				else
					Log4jHelper.logInfo( "Payments And Collections is already available with " + paymentsAndCollectionsName );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Payments And Collections' edit
	 */
	public void paymentsAndCollectionsEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				paymentsAndCollectionsScreen();
				initializeVariable( paymentsAndCollectionsMap );
				String paymetCollectActName = ExcelHolder.getKey( paymentsAndCollectionsMap, "PaymentCollectionAction" );
				paymetCollectActName = "New " + paymetCollectActName;
				boolean ispaymentsAndCollectionsPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_paymAndCollect_transactRef_txtId", paymentsAndCollectionsName, "Transaction Reference" );
				if ( ispaymentsAndCollectionsPresent )
				{
					String authorizationStatus = GridHelper.getCellValue( "SearchGrid", 1, "Authorization Status" );
					if ( authorizationStatus.contentEquals( "Draft" ) )
					{
						GridHelper.clickRow( "SearchGrid", paymentsAndCollectionsName, "Transaction Reference" );
						payAndCollectionsActImpl.clickOnAction( "Common Tasks", "Edit" );
						PaymAndCollectionsDetailImpl payAndCollectionsDetailImpOb = new PaymAndCollectionsDetailImpl( paymentsAndCollectionsMap );
						payAndCollectionsDetailImpOb.modifyPaymentCollections();
						checkForAutoAuthorize( paymentsAndCollectionsName );
						Log4jHelper.logInfo( "Payments And Collections is successfuly updated with " + paymentsAndCollectionsName );
					}
					else
						Log4jHelper.logInfo( "This -'" + paymentsAndCollectionsName + "' Payments And Collections is not in draft status but found   " + authorizationStatus );
				}
				else
					Log4jHelper.logInfo( "Payments And Collections is not available with " + paymentsAndCollectionsName );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	// Method: for Payments and Collections deletion action
	public void paymentsAndCollectionsDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			paymentsAndCollectionsScreen();
			initializeVariable( paymentsAndCollectionsMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean ispaymentsAndCollectionsNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_paymAndCollect_transactRef_txtId", paymentsAndCollectionsName, "Transaction Reference" );
			assertTrue( ispaymentsAndCollectionsNamePresent, "Payments and Collections is not available in the screen with  name: -'" + paymentsAndCollectionsName );
			String authorizationStatus = GridHelper.getCellValue( "SearchGrid", 1, "Authorization Status" );
			if ( authorizationStatus.contentEquals( "Draft" ) )
			{
				psGenericHelper.clickDeleteOrUnDeleteAction( paymentsAndCollectionsName, "Transaction Reference", "Delete" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
				ispaymentsAndCollectionsNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_paymAndCollect_transactRef_txtId", paymentsAndCollectionsName, "Transaction Reference" );
				assertTrue( ispaymentsAndCollectionsNamePresent, paymentsAndCollectionsName + " is not present" );
				Log4jHelper.logInfo( "Payments and Collections is deleted successfully with the value-:'" + paymentsAndCollectionsName );
			}
			else
				Log4jHelper.logInfo( "This -'" + paymentsAndCollectionsName + "' Payments And Collections is not in draft status but found   " + authorizationStatus );
		}

	}

	// Method: for Payments and Collections Undeletion action
	public void paymentsAndCollectionsUnDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			paymentsAndCollectionsScreen();
			initializeVariable( paymentsAndCollectionsMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			boolean ispaymentsAndCollectionsNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_paymAndCollect_transactRef_txtId", paymentsAndCollectionsName, "Transaction Reference" );
			assertTrue( ispaymentsAndCollectionsNamePresent, "Payments and Collections is not available in the screen with  name: -'" + paymentsAndCollectionsName );
			String authorizationStatus = GridHelper.getCellValue( "SearchGrid", 1, "Authorization Status" );
			if ( authorizationStatus.contentEquals( "Draft" ) )
			{
				psGenericHelper.clickDeleteOrUnDeleteAction( paymentsAndCollectionsName, "Transaction Reference", "Undelete" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				psGenericHelper.selectPartitionFilter( clientPartition, "Non Deleted Items" );
				ispaymentsAndCollectionsNamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_paymAndCollect_transactRef_txtId", paymentsAndCollectionsName, "Transaction Reference" );
				assertTrue( ispaymentsAndCollectionsNamePresent, paymentsAndCollectionsName + " is not present" );
				Log4jHelper.logInfo( "Payments and Collections is undeleted successfully with the  value:  '" + paymentsAndCollectionsName );
			}
			else
				Log4jHelper.logInfo( "This -'" + paymentsAndCollectionsName + "' Payments And Collections is not in draft status but found   " + authorizationStatus );

		}

	}

	/*
	 * This method is for 'Payments And Collections' authorize
	 */
	public void paymentsAndCollectionsAuthorize() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				paymentsAndCollectionsScreen();
				initializeVariable( paymentsAndCollectionsMap );
				boolean ispaymentsAndCollectionsPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_paymAndCollect_transactRef_txtId", paymentsAndCollectionsName, "Transaction Reference" );
				if ( ispaymentsAndCollectionsPresent )
				{
					String authorizationStatus = GridHelper.getCellValue( "SearchGrid", 1, "Authorization Status" );
					if ( authorizationStatus.contentEquals( "Draft" ) )
					{
						GridHelper.clickRow( "SearchGrid", paymentsAndCollectionsName, "Transaction Reference" );
						payAndCollectionsActImpl.performAction( "Authorization", "Authorize" );
						if ( PopupHelper.isTextPresent( "Are you sure you want to Authorize the transaction ?" ) )
							ButtonHelper.click( "YesButton" );
						ButtonHelper.click( "SearchButton" );
						psGenericHelper.waitforHeaderElement( "Type" );
						authorizationStatus = GridHelper.getCellValue( "SearchGrid", 1, "Authorization Status" );
						assertEquals( authorizationStatus, "Authorized", "Payments And Collections is  not in authorized status of name " + paymentsAndCollectionsName + " but found-: " + authorizationStatus );
						Log4jHelper.logInfo( "Payments And Collections is successfuly updated authorized status: " + paymentsAndCollectionsName );
					}
					else
						Log4jHelper.logInfo( "This -'" + paymentsAndCollectionsName + "' Payments And Collections is not in draft status but found   " + authorizationStatus );
				}
				else
					Log4jHelper.logInfo( "Payments And Collections is not available with " + paymentsAndCollectionsName );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Payments And Collections' reverse
	 */
	public void paymentsAndCollectionsReverse() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				paymentsAndCollectionsScreen();
				initializeVariable( paymentsAndCollectionsMap );
				boolean ispaymentsAndCollectionsPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_paymAndCollect_transactRef_txtId", paymentsAndCollectionsName, "Transaction Reference" );
				if ( ispaymentsAndCollectionsPresent )
				{
					String authorizationStatus = GridHelper.getCellValue( "SearchGrid", 1, "Authorization Status" );
					if ( authorizationStatus.contentEquals( "Authorized" ) )
					{
						GridHelper.clickRow( "SearchGrid", paymentsAndCollectionsName, "Transaction Reference" );
						payAndCollectionsActImpl.performAction( "Authorization", "Reverse" );
						if ( PopupHelper.isTextPresent( "Are you sure you want to Reverse the transaction ?" ) )
							ButtonHelper.click( "YesButton" );
						ButtonHelper.click( "SearchButton" );
						psGenericHelper.waitforHeaderElement( "Type" );
						authorizationStatus = GridHelper.getCellValue( "SearchGrid", 1, "Authorization Status" );
						assertEquals( authorizationStatus, "Reversed", "Payments And Collections is  not in authorized status of name " + paymentsAndCollectionsName + " but found-: " + authorizationStatus );
						Log4jHelper.logInfo( "Payments And Collections is successfuly updated Reverse status: " + paymentsAndCollectionsName );
					}
					else
						Log4jHelper.logInfo( "This -'" + paymentsAndCollectionsName + "' Payments And Collections is not in Authorized status but found   " + authorizationStatus );
				}
				else
					Log4jHelper.logInfo( "Payments And Collections is not available with " + paymentsAndCollectionsName );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}
	/*
	 * This method is for 'Payments And Collections' move to draft
	 */
	public void paymentsAndCollectionsMoveToDraft() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				paymentsAndCollectionsScreen();
				initializeVariable( paymentsAndCollectionsMap );
				boolean ispaymentsAndCollectionsPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_paymAndCollect_transactRef_txtId", paymentsAndCollectionsName, "Transaction Reference" );
				if ( ispaymentsAndCollectionsPresent )
				{
					String authorizationStatus = GridHelper.getCellValue( "SearchGrid", 1, "Authorization Status" );
					if ( authorizationStatus.contentEquals( "Reversed" ) )
					{
						GridHelper.clickRow( "SearchGrid", paymentsAndCollectionsName, "Transaction Reference" );
						payAndCollectionsActImpl.performAction( "Authorization", "Move To Draft" );
						if ( PopupHelper.isTextPresent( "Are you sure you want to move this payment to draft ?" ) )
							ButtonHelper.click( "YesButton" );
						ButtonHelper.click( "SearchButton" );
						psGenericHelper.waitforHeaderElement( "Type" );
						authorizationStatus = GridHelper.getCellValue( "SearchGrid", 1, "Authorization Status" );
						assertEquals( authorizationStatus, "Draft", "Payments And Collections is  not in authorized status of name " + paymentsAndCollectionsName + " but found-: " + authorizationStatus );
						Log4jHelper.logInfo( "Payments And Collections is successfuly updated Move To Draft status: " + paymentsAndCollectionsName );
					}
					else
						Log4jHelper.logInfo( "This -'" + paymentsAndCollectionsName + "' Payments And Collections is not in Authorized status but found   " + authorizationStatus );
				}
				else
					Log4jHelper.logInfo( "Payments And Collections is not available with " + paymentsAndCollectionsName );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}
	// Method to check for auto Authorize
	private void checkForAutoAuthorize( String payAndCollectionsNm ) throws Exception
	{
		String autoAuthorie = ExcelHolder.getKey( paymentsAndCollectionsMap, "AutoAutorize" );
		if ( ValidationHelper.isTrue( autoAuthorie ) )
		{
			String authorizationStatus = GridHelper.getCellValue( "SearchGrid", 1, "Authorization Status" );
			assertEquals( authorizationStatus, "Authorized", "Payments And Collections is  not in authorized status of name " + payAndCollectionsNm + " but found-: " + authorizationStatus );
			Log4jHelper.logInfo( "Payments And Collections is in authorized status of name " + payAndCollectionsNm );

		}

	}
}
