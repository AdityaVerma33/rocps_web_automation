package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCBillProfile extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "BillProfile";

	@org.testng.annotations.Test( priority = 1, description = "account creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void accountCreation() throws Exception
	{
		try
		{
			Account accobj = new Account( path, workBookName, sheetName, "Account", 1 );
			accobj.accountCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "bill profile- Invoice creation", dependsOnMethods = "accountCreation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billProfileCreation() throws Exception
	{
		try
		{
			BillProfile billObj = new BillProfile( path, workBookName, sheetName, "BillProfileInvoice", 1 );
			billObj.billProfileCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "bill profile-AllFields creation", dependsOnMethods = "accountCreation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billProfileAllfieldsCreation() throws Exception
	{
		try
		{
			BillProfile billObj = new BillProfile( path, workBookName, sheetName, "BillProfileAllFields", 1 );
			billObj.billProfileCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "bill profile-Transit creation", dependsOnMethods = "accountCreation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billProfileTransitCreation() throws Exception
	{
		try
		{
			BillProfile billObj = new BillProfile( path, workBookName, sheetName, "BillProfile Transit", 1 );
			billObj.billProfileCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "bill profile-non delta creation", dependsOnMethods = "accountCreation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billProfileNonDeltaCreation() throws Exception
	{
		try
		{
			BillProfile billObj = new BillProfile( path, workBookName, sheetName, "BillProfile nondelta", 1 );
			billObj.billProfileCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "bill profile-new cross FX creation", dependsOnMethods = "accountCreation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billProfileCrossFXCreation() throws Exception
	{
		try
		{
			BillProfile billObj = new BillProfile( path, workBookName, sheetName, "BillProfile crossFX", 1 );
			billObj.billProfileCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "bill profile-Delta creation", dependsOnMethods = "accountCreation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billProfileDeltacreation() throws Exception
	{
		try
		{
			BillProfile billObj = new BillProfile( path, workBookName, sheetName, "BillProfile Delta InOutAdvanceRating", 1 );
			billObj.billProfileCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "bill profile search screen col vlaidation" )
	public void billProfileColVal() throws Exception
	{
		try
		{
			BillProfile billObj = new BillProfile( path, workBookName, sheetName, "BillProfileSearchScreencolVal", 1 );
			billObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "bill Period search screen col vlaidation" )
	public void billPeriodColVal() throws Exception
	{
		try
		{
			BillProfile billObj = new BillProfile( path, workBookName, "BillPeriod", "BillPeriodSearchScreencolVal", 1 );
			billObj.billPeriodSearchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, description = "bill profile- Invoice creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billProfileCreationTest() throws Exception
	{
		try
		{
			BillProfile billObj = new BillProfile( path, workBookName, sheetName, "BillProfileTest", 1 );
			billObj.billProfileCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 11, description = "bill profile- edit" )
	public void billProfileEdit() throws Exception
	{
		try
		{
			BillProfile billObj = new BillProfile( path, workBookName, sheetName, "BillProfileEdit", 1 );
			billObj.billProfileEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 12, description = "bill Profile email creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billProfileEmailCreation() throws Exception
	{
		try
		{
			Account accobj = new Account( path, workBookName, sheetName, "Account_EmailTab", 1 );
			accobj.accountCreation();

			BillProfile billObj = new BillProfile( path, workBookName, sheetName, "BillProfile_Email", 1 );
			billObj.billProfileCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}


}
