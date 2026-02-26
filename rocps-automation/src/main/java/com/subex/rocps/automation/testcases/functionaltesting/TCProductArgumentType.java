package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.products.ProductArgumentTypeHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCProductArgumentType extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "ProductArgumentType";

	@Test( priority = 1, enabled = true, description = "Product Argument Type verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			ProductArgumentTypeHelper productArgTypeOb = new ProductArgumentTypeHelper( path, workBookName, sheetName, "ProductArgTypeScreencolVal" );
			productArgTypeOb.productArgumentTypeColumnValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 2, enabled = true, description = "Product Argument Type creation with Instance Type" )
	public void createProductArgTypeWithInstance() throws Exception
	{
		try
		{
			ProductArgumentTypeHelper productArgTypeOb = new ProductArgumentTypeHelper( path, workBookName, sheetName, "ProductArgTypeWithInstance" );
			productArgTypeOb.createProductArgType();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 3, enabled = true, description = "Product Argument Type creation with Definition" )
	public void createProductArgTypeWithDefinition() throws Exception
	{
		try
		{
			ProductArgumentTypeHelper productArgTypeOb = new ProductArgumentTypeHelper( path, workBookName, sheetName, "ProductArgTypeWithDefinition" );
			productArgTypeOb.createProductArgType();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 4, enabled = true, description = "Product Argument Type creation with Both type" )
	public void createProductArgTypeWithBothType() throws Exception
	{
		try
		{
			ProductArgumentTypeHelper productArgTypeOb = new ProductArgumentTypeHelper( path, workBookName, sheetName, "ProductArgTypeWithBoth" );
			productArgTypeOb.createProductArgType();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 5, enabled = true, description = "Product Argument Type Edit action" )
	public void editProductArgumentType() throws Exception
	{
		try
		{
			ProductArgumentTypeHelper productArgTypeOb = new ProductArgumentTypeHelper( path, workBookName, sheetName, "ProductArgTypeEdit" );
			productArgTypeOb.editProdutArgTypeAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 6, enabled = true, description = "Product Argument Type Delete action" )
	public void productArgumentTypeDelete() throws Exception
	{
		try
		{
			ProductArgumentTypeHelper productArgTypeOb = new ProductArgumentTypeHelper( path, workBookName, sheetName, "ProductArgTypeDelete" );
			productArgTypeOb.producArgumentTypeDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 7, enabled = true, description = "Product Argument Type UnDelete action" )
	public void productArgumentTypeUnDelete() throws Exception
	{
		try
		{
			ProductArgumentTypeHelper productArgTypeOb = new ProductArgumentTypeHelper( path, workBookName, sheetName, "ProductArgTypeUnDelete" );
			productArgTypeOb.productArgumentTypeUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}
}
