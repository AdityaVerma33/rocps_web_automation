package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.referenceTable.SignallingType;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCSignallingType extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "SignallingType";

	@org.testng.annotations.Test( priority = 1, description = "Signalling  Type creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void signallingTypeCreation() throws Exception
	{
		try
		{
			SignallingType sigObj = new SignallingType( path, workBookName, sheetName, "SignallingType" );
			sigObj.signallingTypeCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Signalling Type deletion", dependsOnMethods = "signallingTypeCreation" )
	public void signallingTypeDelete() throws Exception
	{
		try
		{
			SignallingType sigDelObj = new SignallingType( path, workBookName, sheetName, "SignallingTypeDelete" );
			sigDelObj.signallingTypeDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Signalling Type un delete", dependsOnMethods = "signallingTypeDelete" )
	public void signallingTypeUnDelete() throws Exception
	{
		try
		{
			SignallingType sigUnDelObj = new SignallingType( path, workBookName, sheetName, "SignallingTypeUnDelete" );
			sigUnDelObj.signallingTypeUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "Signalling Type search screen column validation" )
	public void signallingTypeColVal() throws Exception
	{
		try
		{
			SignallingType sigColValObj = new SignallingType( path, workBookName, sheetName, "SigTypeSearchScreencolVal" );
			sigColValObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "Signalling  Type creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void signallingTypeCreation1() throws Exception
	{
		try
		{
			SignallingType sigObj = new SignallingType( path, workBookName, sheetName, "SignallingtypeCreate" );
			sigObj.signallingTypeCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "Signalling  Type creation" )
	public void signallingTypeEdit() throws Exception
	{
		try
		{
			SignallingType sigObj = new SignallingType( path, workBookName, sheetName, "SignallingtypeEdit" );
			sigObj.signallingTypeEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
