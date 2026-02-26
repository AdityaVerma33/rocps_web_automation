package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bills.BillPackage;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCBillPackage extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "BillPackage";

	@org.testng.annotations.Test( priority = 1, description = "Bill Package-Multi non delta creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billPackageMultiNonDeltaCreation() throws Exception
	{
		try
		{
			BillPackage billPackageObj = new BillPackage( path, workBookName, sheetName, "BillPackage MultiNonDelta", 1 );
			billPackageObj.billPackageCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Bill Package-MultiDelta creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billPackageMultiDeltaCreation() throws Exception
	{
		try
		{
			BillPackage billPackageObj = new BillPackage( path, workBookName, sheetName, "BillPackage MultiDelta", 1 );
			billPackageObj.billPackageCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Bill Package-single non delta creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billPackageSingleNondeltaCreation() throws Exception
	{
		try
		{
			BillPackage billPackageObj = new BillPackage( path, workBookName, sheetName, "BillPackage SingleNonDelta", 1 );
			billPackageObj.billPackageCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "Bill Package-Single Delta creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billPackageSingleDeltaCreation() throws Exception
	{
		try
		{
			BillPackage billPackageObj = new BillPackage( path, workBookName, sheetName, "BillPackage SingleDelta", 1 );
			billPackageObj.billPackageCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "Bill Package search screen col validation" )
	public void billPackageColVal() throws Exception
	{
		try
		{
			BillPackage billpackColValObj = new BillPackage( path, workBookName, sheetName, "BillPackageSearchScreencolVal", 1 );
			billpackColValObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "Bill Package-single non delta creation1", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billPackageSingleNondeltaCreation1() throws Exception
	{
		try
		{
			BillPackage billPackageObj = new BillPackage( path, workBookName, sheetName, "BillPackage SingleNonDelta1", 1 );
			billPackageObj.billPackageCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "Bill Package-Single Delta creation1", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void billPackageSingleDeltaCreation1() throws Exception
	{
		try
		{
			BillPackage billPackageObj = new BillPackage( path, workBookName, sheetName, "BillPackage SingleDelta1", 1 );
			billPackageObj.billPackageCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "Bill Package deletion" )
	public void billPackageDelete() throws Exception
	{
		try
		{
			BillPackage billPackageDelObj = new BillPackage( path, workBookName, sheetName, "BillPackageDelete", 1 );
			billPackageDelObj.billPackageDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "Bill Package un delete" )
	public void billPackageUnDelete() throws Exception
	{
		try
		{
			BillPackage billPackageUnDelObj = new BillPackage( path, workBookName, sheetName, "BillPackageUnDelete", 1 );
			billPackageUnDelObj.billPackageUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, description = "Bill Package -edit" )
	public void billPackageEdit() throws Exception
	{
		try
		{
			BillPackage billPackageObj = new BillPackage( path, workBookName, sheetName, "BillPackageEdit", 1 );
			billPackageObj.billPackageEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
