package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.bills.BillingGroupCode;
import com.subex.rocps.automation.helpers.application.bills.SalesTaxGroup;
import com.subex.rocps.automation.helpers.application.bills.SalesTaxRate;
import com.subex.rocps.automation.helpers.application.matchandrate.Operator;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.products.ProductBundlesHelper;
import com.subex.rocps.automation.helpers.application.referenceTable.SalesTax;
import com.subex.rocps.automation.helpers.application.settlements.SettlementsHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCProductBundles extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "ProductBundle";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = "Product Bundle prerequistes", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void productBundlePrerequistes() throws Exception

	{
		try
		{

			BillingGroupCode billingGroupCode = new BillingGroupCode( path, workBookName, sheetName, "BillingGroupCodeCreate" );
			billingGroupCode.createBillingGroupCode();

			SalesTax salesTaxObj = new SalesTax( path, workBookName, "SalesTax", "SalesTax", 1 );
			salesTaxObj.salesTaxCreation();

			SalesTax salesTaxObj1 = new SalesTax( path, workBookName, "SalesTax", "SalesTax VAT", 1 );
			salesTaxObj1.salesTaxCreation();

			SalesTaxRate salesTaxrateObj = new SalesTaxRate( path, workBookName, "SalesTaxRate", "Sales Tax Rate", 1 );
			salesTaxrateObj.salesTaxRateCreation();

			SalesTaxRate salesTaxrateObj2 = new SalesTaxRate( path, workBookName, "SalesTaxRate", "Sales Tax Rate-VAT", 1 );
			salesTaxrateObj2.salesTaxRateCreation();

			SalesTaxGroup salesTaxGrpObj = new SalesTaxGroup( path, workBookName, "SalesTaxGrp", "SalesTaxGrp MultipleTaxGrp", 1 );
			salesTaxGrpObj.salesTaxGroupCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "Product bundle verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			ProductBundlesHelper productBundlesObj = new ProductBundlesHelper( path, workBookName, sheetName, "ProductBundleScreencolVal" );
			productBundlesObj.productBundleColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 3, enabled = true, description = "Product bundle creation with empty data Error message validation" )
	public void bundleErrorMessageValidate() throws Exception
	{
		try
		{
			ProductBundlesHelper productBundlesObj = new ProductBundlesHelper( path, workBookName, sheetName, "TestBundleErrorMessageValidate" );
			productBundlesObj.productBundleValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 4, enabled = true, description = "Product bundle creation without product items", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createProductBundleWithoutItems() throws Exception
	{
		try
		{
			ProductBundlesHelper productBundlesObj = new ProductBundlesHelper( path, workBookName, sheetName, "Test02_PB2_WithoutItems" );
			productBundlesObj.createProductBundle();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 5, enabled = true, description = "Product bundle creation without products", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createProductBundleWithoutProducts() throws Exception
	{
		try
		{
			ProductBundlesHelper productBundlesObj = new ProductBundlesHelper( path, workBookName, sheetName, "Test03_PB3_WithoutProducts" );
			productBundlesObj.createProductBundle();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 6, enabled = true, description = "Product bundle creation with 'One Off' Product item", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createProductBundleWithOneOffItem() throws Exception
	{
		try
		{
			ProductBundlesHelper productBundlesObj = new ProductBundlesHelper( path, workBookName, sheetName, "Test04_PB4_OneOff" );
			productBundlesObj.createProductBundle();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 7, enabled = true, description = "Product bundle creation with 'Recurring' Product item", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createProductBundleWithRecurringItem() throws Exception
	{
		try
		{
			ProductBundlesHelper productBundlesObj = new ProductBundlesHelper( path, workBookName, sheetName, "Test05_PB5_Recurring" );
			productBundlesObj.createProductBundle();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 8, enabled = true, description = "Product bundle creation with validate message for currency, Country, Billing Group" )
	public void createProductBundleWithValidateMessage() throws Exception
	{
		try
		{
			ProductBundlesHelper productBundlesObj = new ProductBundlesHelper( path, workBookName, sheetName, "Test08_BundleValidateMessage_PB8" );
			productBundlesObj.createBundleWithValidateMessage();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 9, enabled = true, description = "Product bundle creation with multiple product items", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createBundleWithMultipleProductItem() throws Exception
	{
		try
		{
			ProductBundlesHelper productBundlesObj = new ProductBundlesHelper( path, workBookName, sheetName, "Test01_PB1_MultipleProductItem" );
			productBundlesObj.createProductBundle();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 10, enabled = true, description = "Product bundle Change Status" )
	public void changeStatusBundle() throws Exception
	{
		try
		{
			ProductBundlesHelper productBundlesObj = new ProductBundlesHelper( path, workBookName, sheetName, "TestBundleChangeStatus" );
			productBundlesObj.changeStatusBundleAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 11, enabled = true, description = "Product bundle ViewAction" , retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void viewBundle() throws Exception
	{
		try
		{
			ProductBundlesHelper productBundlesObj = new ProductBundlesHelper( path, workBookName, sheetName, "TestBundleViewAction" );
			productBundlesObj.viewProdutBundleAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 12, enabled = true, description = "Product bundle edit" )
	public void editBundle() throws Exception
	{
		try
		{
			ProductBundlesHelper productBundlesObj = new ProductBundlesHelper( path, workBookName, sheetName, "TestBundleEditAction" );
			productBundlesObj.editProdutBundleAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 13, enabled = true, description = "Create new Bundle action" )
	public void createNewBundleAction() throws Exception
	{
		try
		{
			ProductBundlesHelper productBundlesObj = new ProductBundlesHelper( path, workBookName, sheetName, "Test_CreateNewBundleAction" );
			productBundlesObj.createNewBundleAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 14, enabled = true, description = "Create new Version action" )
	public void createNewVersionAction() throws Exception
	{
		try
		{
			ProductBundlesHelper productBundlesObj = new ProductBundlesHelper( path, workBookName, sheetName, "Test_CreateNewVersionAction" );
			productBundlesObj.createNewVersioneAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 15, enabled = true, description = "Product bundle deletion" )
	public void productBundleDelete() throws Exception
	{
		try
		{
			ProductBundlesHelper productBundlesObj = new ProductBundlesHelper( path, workBookName, sheetName, "ProductBundleDelete" );
			productBundlesObj.productBundleDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 16, enabled = true, description = "Product bundle undeletion" )
	public void productBundleUnDelete() throws Exception
	{
		try
		{
			ProductBundlesHelper productBundlesObj = new ProductBundlesHelper( path, workBookName, sheetName, "ProductBundleUnDelete" );
			productBundlesObj.productBundleUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

}
