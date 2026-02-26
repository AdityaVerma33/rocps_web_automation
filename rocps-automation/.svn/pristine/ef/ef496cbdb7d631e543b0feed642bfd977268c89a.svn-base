package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.accruals.AccrualsModelling;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCAccrualsModelling extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "AccrualsModelling";

	@org.testng.annotations.Test( priority = 1, description = "Accruals Modelling creation" )
	public void accrualsModellingCreation() throws Exception
	{
		try
		{
			AccrualsModelling accobj = new AccrualsModelling( path, workBookName, sheetName, "AccuralsMod", 1 );
			accobj.configureAccuralsModelling();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	} 
	
	@org.testng.annotations.Test( priority = 2, description = "Accruals Modelling creation with additional columns" )
	public void accrualsModellingMultipleCol() throws Exception
	{
		try
		{
			AccrualsModelling accobj = new AccrualsModelling( path, workBookName, sheetName, "AccuralsMod-additionalCol", 1 );
			accobj.configureAccuralsModelling();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 3, description = "Accruals Modelling creation with additional columns" )
	public void accrualsModellingEdit() throws Exception
	{
		try
		{
			AccrualsModelling accobj = new AccrualsModelling( path, workBookName, sheetName, "AccuralsMod-Edit", 1 );
			accobj.accrualsModelingEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 3, description = "Accruals Modelling change status" )
	public void accrualsModellingChangeStatus() throws Exception
	{
		try
		{
			AccrualsModelling accobj = new AccrualsModelling( path, workBookName, sheetName, "AccuralsMod-changestatus", 1 );
			accobj.accuralModellingChangeStatus();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 4, description = "Accruals Modelling Delete" )
	public void accrualsModellingDelete() throws Exception
	{
		try
		{
			AccrualsModelling accobj = new AccrualsModelling( path, workBookName, sheetName, "AccuralsMod-Delete", 1 );
			accobj.accrualsModellingDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 5, description = "Accruals Modelling undelete" )
	public void accrualsModellingUnDelete() throws Exception
	{
		try
		{
			AccrualsModelling accobj = new AccrualsModelling( path, workBookName, sheetName, "AccuralsMod-UnDelete", 1 );
			accobj.accrualsModellingUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 6, description = "Accruals Modelling search screen column validation" )
	public void accrualsModellingColVal() throws Exception
	{
		try
		{
			AccrualsModelling accobj = new AccrualsModelling( path, workBookName, sheetName, "AccuralsModColVal", 1 );
			accobj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	
	@org.testng.annotations.Test( priority = 7, description = "Accruals Modelling Discontinue" )
	public void accrualsModellingDiscontinue() throws Exception
	{
		try
		{
			AccrualsModelling accobj = new AccrualsModelling( path, workBookName, sheetName, "AccuralsMod-Discontinue", 1 );
			accobj.accuralModellingDiscontinue();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
}
