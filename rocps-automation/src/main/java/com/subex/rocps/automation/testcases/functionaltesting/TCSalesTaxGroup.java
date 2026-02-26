package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bills.SalesTaxGroup;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCSalesTaxGroup extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "SalesTaxGrp";

	@org.testng.annotations.Test( priority = 1, description = "Sales Tax Group creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void salesTaxGrpCreation() throws Exception
	{
		try
		{
			SalesTaxGroup salesTaxGrpObj = new SalesTaxGroup( path, workBookName, sheetName, "SalesTaxGrp", 1 );
			salesTaxGrpObj.salesTaxGroupCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Sales Tax Group - Multiple tax group creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void salesTaxGrpTaxOnTaxCreation() throws Exception
	{
		try
		{
			SalesTaxGroup salesTaxGrpObj = new SalesTaxGroup( path, workBookName, sheetName, "SalesTaxGrp MultipleTaxGrp", 1 );
			salesTaxGrpObj.salesTaxGroupCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Sales Tax Group deletion" )
	public void salesTaxGrpDelete() throws Exception
	{
		try
		{
			SalesTaxGroup salesTaxGrpDelObj = new SalesTaxGroup( path, workBookName, sheetName, "SalesTaxGrpDelete", 1 );
			salesTaxGrpDelObj.salesTaxGrpDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "Sales Tax Group un delete" )
	public void salesTaxGrpUnDelete() throws Exception
	{
		try
		{
			SalesTaxGroup salesTaxGrpUnDelObj = new SalesTaxGroup( path, workBookName, sheetName, "SalesTaxGrpUnDelete", 1 );
			salesTaxGrpUnDelObj.salesTaxGrpUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "Sales Tax Group search screen column validation" )
	public void salesTaxGrpColVal() throws Exception
	{
		try
		{
			SalesTaxGroup stgColValObj = new SalesTaxGroup( path, workBookName, sheetName, "SalesTaxSearchScreencolVal", 1 );
			stgColValObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "Sales Tax Group creation for Group1", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void salesTaxGrpCreation1() throws Exception
	{
		try
		{
			SalesTaxGroup salesTaxGrpObj = new SalesTaxGroup( path, workBookName, sheetName, "SalesTaxGrp1", 1 );
			salesTaxGrpObj.salesTaxGroupCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, enabled = true, description = "Sales Tax Group creation for Group2", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void salesTaxGrpCreation2() throws Exception
	{
		try
		{
			SalesTaxGroup salesTaxGrpObj = new SalesTaxGroup( path, workBookName, sheetName, "SalesTaxGrp2", 1 );
			salesTaxGrpObj.salesTaxGroupCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, enabled = true, description = "Sales Tax Group creation for Group3", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void salesTaxGrpCreation3() throws Exception
	{
		try
		{
			SalesTaxGroup salesTaxGrpObj = new SalesTaxGroup( path, workBookName, sheetName, "SalesTaxGrp3", 1 );
			salesTaxGrpObj.salesTaxGroupCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, enabled = true, description = "Sales Tax Group edit" )
	public void salesTaxGrpEdit() throws Exception
	{
		try
		{
			SalesTaxGroup salesTaxGrpObj = new SalesTaxGroup( path, workBookName, sheetName, "SalesTaxGrpEdit", 1 );
			salesTaxGrpObj.salesTaxGroupEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

}
