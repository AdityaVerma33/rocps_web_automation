package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.matchandrate.Operator;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCOperator extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "Operator";

	@org.testng.annotations.Test( priority = 1, description = "operator creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void operatorCreation() throws Exception
	{
		try
		{
			Operator ope1Obj = new Operator( path, workBookName, sheetName, "Operator", 1 );
			ope1Obj.operatorCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "operator with all manadtory fields creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void operatorTransitCreation() throws Exception
	{
		try
		{
			Operator ope2Obj = new Operator( path, workBookName, sheetName, "OperatorTransit", 1 );
			ope2Obj.operatorCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "operator with gloabl inclusion flag creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void operatorGlobalFlagCreation() throws Exception
	{
		try
		{
			Operator ope3Obj = new Operator( path, workBookName, sheetName, "OperatorGlobalFalg", 1 );
			ope3Obj.operatorCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "operator - HomeCarrier creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void operatorHCCreation() throws Exception
	{
		try
		{
			Operator ope4Obj = new Operator( path, workBookName, sheetName, "Operator HomeCarrier", 1 );
			ope4Obj.operatorCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "operator - AllFields creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void operatorAllFieldsCreation() throws Exception
	{
		try
		{
			Operator ope4Obj = new Operator( path, workBookName, sheetName, "Operator AllFields", 1 );
			ope4Obj.operatorCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "operator -Out-AdvanceRating creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void operatorOutAdvance() throws Exception
	{
		try
		{
			Operator ope4Obj = new Operator( path, workBookName, sheetName, "Operator OutAdvance", 1 );
			ope4Obj.operatorCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "operator deletion" )
	public void operatorDelete() throws Exception
	{
		try
		{
			Operator opeDelObj = new Operator( path, workBookName, sheetName, "OperatorDelete", 1 );
			opeDelObj.operatorDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "operator un delete" )
	public void operatorUnDelete() throws Exception
	{
		try
		{
			Operator opeUnDelObj = new Operator( path, workBookName, sheetName, "OperatorUnDelete", 1 );
			opeUnDelObj.operatorUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "operator search Screen Col Validation" )
	public void operatorColVal() throws Exception
	{
		try
		{
			Operator opeValObj = new Operator( path, workBookName, sheetName, "OperatorSearchScreencolVal", 1 );
			opeValObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, description = "operator element set mapping", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void operatorElementSetMapping() throws Exception
	{
		try
		{
			Operator opeValObj = new Operator( path, workBookName, sheetName, "Operator ElementSet", 1 );
			opeValObj.operatorCreation();
			Operator opeValObj1 = new Operator( path, workBookName, sheetName, "OperatorElementSepMapping", 1 );
			opeValObj1.operatorElementSetMapping();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 11, description = "Edit operator - HomeCarrier", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void editoperatorHCCreation() throws Exception
	{
		try
		{
			Operator ediOpObj = new Operator( path, workBookName, sheetName, "OperatorEdit", 1 );
			ediOpObj.editOperator();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
