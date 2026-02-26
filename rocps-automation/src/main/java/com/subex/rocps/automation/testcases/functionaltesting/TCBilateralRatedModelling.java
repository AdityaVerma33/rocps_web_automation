package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.bilateral.BilateralRatedDetailModelling;
import com.subex.rocps.automation.helpers.application.bills.RerateRequest;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCBilateralRatedModelling extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "BilateralModelling";

	@org.testng.annotations.Test( priority = 1, description = "BilateralModelling - creation" )
	public void bilateralModellingColVal() throws Exception
	{
		try
		{
			BilateralRatedDetailModelling brdObj = new BilateralRatedDetailModelling( path, workBookName, sheetName, "BilateralColVal", 1 );
			brdObj.searchScreenColumnsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "BilateralModelling - Multiple creation" )
	public void bilateralModellingCreation() throws Exception
	{
		try
		{
			BilateralRatedDetailModelling brdObj = new BilateralRatedDetailModelling( path, workBookName, sheetName, "BilateralRated", 1 );
			brdObj.configurebrdModelling();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "BilateralModelling - extra" )
	public void bilateralModellingExtra() throws Exception
	{
		try
		{
			BilateralRatedDetailModelling brdObj = new BilateralRatedDetailModelling( path, workBookName, sheetName, "BilateralRated_ExtraColumns", 1 );
			brdObj.configurebrdModelling();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "BilateralModelling - delete" )
	public void bilateralModellingDelete() throws Exception
	{
		try
		{
			BilateralRatedDetailModelling brdObj = new BilateralRatedDetailModelling( path, workBookName, sheetName, "BilateralRated_Delete", 1 );
			brdObj.brdDelete();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "BilateralModelling - undelete" )
	public void bilateralModellingUnDelete() throws Exception
	{
		try
		{
			BilateralRatedDetailModelling brdObj = new BilateralRatedDetailModelling( path, workBookName, sheetName, "BilateralRated_UnDelete", 1 );
			brdObj.brdUnDelete();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "BilateralModelling - change status" )
	public void bilateralModellingchangeStatus() throws Exception
	{
		try
		{
			BilateralRatedDetailModelling brdObj = new BilateralRatedDetailModelling( path, workBookName, sheetName, "BilateralRatedChange status", 1 );
			brdObj.brdChangeStatus();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	//@org.testng.annotations.Test( priority = 7, description = "BilateralModelling -Discontinue" )
	public void bilateralModellingDiscontinue() throws Exception
	{
		try
		{
			BilateralRatedDetailModelling brdObj = new BilateralRatedDetailModelling( path, workBookName, sheetName, "BilateralRatedModelling Discontinue", 1 );
			brdObj.brdDiscontinue();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "BilateralModelling -View Result" )
	public void bilateralModellingViewResults() throws Exception
	{
		try
		{
			BilateralRatedDetailModelling brdObj = new BilateralRatedDetailModelling( path, workBookName, sheetName, "BilateralRatedModelling ViewResults", 1 );
			brdObj.brdViewResults();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
