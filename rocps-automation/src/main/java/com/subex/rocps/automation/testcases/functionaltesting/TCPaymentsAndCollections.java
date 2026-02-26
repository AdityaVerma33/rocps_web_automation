package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.EventUsgRequestHelper;
import com.subex.rocps.automation.helpers.application.payments.PaymentsAndCollections;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCPaymentsAndCollections extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "PaymentsAndCollections";

	@Test( priority = 1, enabled = true, description = "' Payments And Collections'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			PaymentsAndCollections payAndCollections = new PaymentsAndCollections( path, workBookName, sheetName, "PaymentsAndCollectionsScreenColumns" );
			payAndCollections.paymentsAndCollectionsColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "' Payments And Collections' Payment  creation with CI", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void paymentCreation_WithCI() throws Exception
	{
		try
		{
			PaymentsAndCollections payAndCollections = new PaymentsAndCollections( path, workBookName, sheetName, "PaymentCreationwithCI" );
			payAndCollections.paymentsAndCollectionsCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "' Payments And Collections' Collections creation for Bill", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void collectionsCreation_WithBill() throws Exception
	{
		try
		{
			PaymentsAndCollections payAndCollections = new PaymentsAndCollections( path, workBookName, sheetName, "CollectionCreationwithBill" );
			payAndCollections.paymentsAndCollectionsCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 4, enabled = true, description = "' Payments And Collections' Payments creation for Bill", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void paymentsCreation_WithBill() throws Exception
	{
		try
		{
			PaymentsAndCollections payAndCollections = new PaymentsAndCollections( path, workBookName, sheetName, "PaymentCreationwithBill" );
			payAndCollections.paymentsAndCollectionsCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 5, enabled = true, description = "' Payments And Collections'Edit", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void paymentsAndCollectionsEdit() throws Exception
	{
		try
		{
			PaymentsAndCollections payAndCollections = new PaymentsAndCollections( path, workBookName, sheetName, "PaymentsAndCollectionEdit" );
			payAndCollections.paymentsAndCollectionsEdit();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 6, enabled = true, description = "' Payments And Collections'  creation auto autorize with Bill", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void paymentsAndCollectionsCreation_AutoAuthBill() throws Exception
	{
		try
		{
			PaymentsAndCollections payAndCollections = new PaymentsAndCollections( path, workBookName, sheetName, "PaymentsAndCollection_AutoAuthorie_Bill" );
			payAndCollections.paymentsAndCollectionsCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 7, enabled = true, description = "' Payments And Collections'  creation auto autorize with CI", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void paymentsAndCollectionsCreation_AutoAuthCI() throws Exception
	{
		try
		{
			PaymentsAndCollections payAndCollections = new PaymentsAndCollections( path, workBookName, sheetName, "PaymentCreationwithCI_AutoAuthorize" );
			payAndCollections.paymentsAndCollectionsCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 8, enabled = true, description = "' Payments And Collections' AuthorizedStatus", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void paymentsAndCollectionsAuthorizedStatus() throws Exception
	{
		try
		{
			PaymentsAndCollections payAndCollections = new PaymentsAndCollections( path, workBookName, sheetName, "PaymentsAndCollectionAuthorizeStatus" );
			payAndCollections.paymentsAndCollectionsAuthorize();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 9, enabled = true, description = "' Payments And Collections' Reversed Status", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void paymentsAndCollectionsReversedStatus() throws Exception
	{
		try
		{
			PaymentsAndCollections payAndCollections = new PaymentsAndCollections( path, workBookName, sheetName, "PaymentsAndCollectionReverseStatus" );
			payAndCollections.paymentsAndCollectionsReverse();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 10, enabled = true, description = "' Payments And Collections'  move to draft Status", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void paymentsAndCollectionsDraftStatus() throws Exception
	{
		try
		{
			PaymentsAndCollections payAndCollections = new PaymentsAndCollections( path, workBookName, sheetName, "PaymentsAndCollectionMoveToDraftStatus" );
			payAndCollections.paymentsAndCollectionsMoveToDraft();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 11, enabled = true, description = "' Payments And Collections'delete" )
	public void paymentsAndCollectionsDelete() throws Exception
	{
		try
		{
			PaymentsAndCollections payAndCollections = new PaymentsAndCollections( path, workBookName, sheetName, "PaymentsAndCollectionDelete" );
			payAndCollections.paymentsAndCollectionsDelete();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 12, enabled = true, description = "' Payments And Collections' undelete" )
	public void paymentsAndCollectionsUndelete() throws Exception
	{
		try
		{
			PaymentsAndCollections payAndCollections = new PaymentsAndCollections( path, workBookName, sheetName, "PaymentsAndCollectionUnDelete" );
			payAndCollections.paymentsAndCollectionsUnDelete();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
