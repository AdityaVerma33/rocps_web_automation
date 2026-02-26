package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.bills.BillingGroupCode;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.products.ProductBundlesHelper;
import com.subex.rocps.automation.helpers.application.products.ProductInstanceHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCProductInstance extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "ProductInstance";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = "Product InstancePrerequistes Account, Bill Profile creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void ProductInstrPrerequistes1() throws Exception

	{
		try
		{
			Agent agobj = new Agent( path, workBookName, "Agent", "Agent", 1 );
			agobj.agentCreation();

			Account accobj = new Account( path, workBookName, "Account", "Account_ProdBundle", 1 );
			accobj.accountCreation();

			BillProfile billObj = new BillProfile( path, workBookName, "BillProfile", "BillProfile_ProdBundle", 1 );
			billObj.billProfileCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "Product Bundle creation Prerequisite ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void BundleCreationPrerequisite() throws Exception
	{
		try
		{
			BillingGroupCode billingGroupCode = new BillingGroupCode( path, workBookName, "ProductBundle", "BillingGroupCodeCreate" );
			billingGroupCode.createBillingGroupCode();

			ProductBundlesHelper productBundleObMand = new ProductBundlesHelper( path, workBookName, sheetName, "TestProductBundle_Mandatory" );
			productBundleObMand.createProductBundle();

			ProductBundlesHelper productBundleObOp1 = new ProductBundlesHelper( path, workBookName, sheetName, "TestProductBundle_OptProducts" );
			productBundleObOp1.createProductBundle();

			ProductBundlesHelper productBundleObOp2 = new ProductBundlesHelper( path, workBookName, sheetName, "TestProductBundle_OptProdItems" );
			productBundleObOp2.createProductBundle();

			ProductBundlesHelper productBundleObSpec = new ProductBundlesHelper( path, workBookName, sheetName, "TestProductBundle_SpecifytProdItems" );
			productBundleObSpec.createProductBundle();

			ProductBundlesHelper productBundleOb1 = new ProductBundlesHelper( path, workBookName, sheetName, "TestBundleChangeStatus" );
			productBundleOb1.changeStatusBundleAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 3, enabled = true, description = "Product Instance verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			ProductInstanceHelper productInstanceHelpOb = new ProductInstanceHelper( path, workBookName, sheetName, "ProductInstanceScreencolVal" );
			productInstanceHelpOb.productInstanceColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 4, enabled = true, description = "Product Instance creation with mandatory Options" )
	public void createProductInstanceWithMandaotory() throws Exception
	{
		try
		{
			ProductInstanceHelper productInstanceHelpOb = new ProductInstanceHelper( path, workBookName, sheetName, "TestProductInstanceMandatory" );
			productInstanceHelpOb.createProductInstWithMandatory();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 5, enabled = true, description = "Product Instance creation with Optional Products" )
	public void createProductInstWithOptionalProduct() throws Exception
	{
		try
		{
			ProductInstanceHelper productInstanceHelpOb = new ProductInstanceHelper( path, workBookName, sheetName, "TestProductInstOptProducts" );
			productInstanceHelpOb.createPrInstanceWithoutMandatory();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 6, enabled = true, description = "Product Instance creation with Optional Product items" )
	public void createProductInstWithOptionalItems() throws Exception
	{
		try
		{
			ProductInstanceHelper productInstanceHelpOb = new ProductInstanceHelper( path, workBookName, sheetName, "TestProductInstOptProdItems" );
			productInstanceHelpOb.createPrInstanceWithoutMandatory();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 7, enabled = true, description = "Product Instance creation with Specify Product items" )
	public void createProductInstWithSpecifyItems() throws Exception
	{
		try
		{
			ProductInstanceHelper productInstanceHelpOb = new ProductInstanceHelper( path, workBookName, sheetName, "TestProductInstSpecifyPrItems" );
			productInstanceHelpOb.createPrInstanceWithoutMandatory();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 8, enabled = true, description = "Product Bundle creation Prerequisite for server side ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void BundleCreationPrerequisiteForServer() throws Exception
	{
		try
		{
			BillingGroupCode billingGroupCode = new BillingGroupCode( path, workBookName, "ProductBundle", "BillingGroupCodeCreate" );
			billingGroupCode.createBillingGroupCode();

			ProductBundlesHelper productBundleObCredit = new ProductBundlesHelper( path, workBookName, "ProductInstanceServer", "TestProductBundle_Credittems_Mandatory" );
			productBundleObCredit.createProductBundle();
			ProductBundlesHelper productBundleObCharge = new ProductBundlesHelper( path, workBookName, "ProductInstanceServer", "TestProductBundle_ChargItems_Mandatory" );
			productBundleObCharge.createProductBundle();

			ProductBundlesHelper productBundleObCrCh = new ProductBundlesHelper( path, workBookName, "ProductInstanceServer", "TestProductBundle_CreditCharge_Optional" );
			productBundleObCrCh.createProductBundle();
			ProductBundlesHelper productBundleObChCr = new ProductBundlesHelper( path, workBookName, "ProductInstanceServer", "TestProductBundle_ChargCreditItems_Spec" );
			productBundleObChCr.createProductBundle();

			ProductBundlesHelper productBundleOb1 = new ProductBundlesHelper( path, workBookName, "ProductInstanceServer", "TestBundleChangeStatus" );
			productBundleOb1.changeStatusBundleAction();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 9, enabled = true, description = "Product Instance creation with Credit Product items mandatory" )
	public void createProductInstWithCreditItems() throws Exception
	{
		try
		{
			ProductInstanceHelper productInstanceHelpOb = new ProductInstanceHelper( path, workBookName, "ProductInstanceServer", "TestProductInstCredit" );
			productInstanceHelpOb.createPrInstanceWithoutMandatory();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 10, enabled = true, description = "Product Instance creation with Charge Product items mandatory" )
	public void createProductInstWithChargeItems() throws Exception
	{
		try
		{
			ProductInstanceHelper productInstanceHelpOb = new ProductInstanceHelper( path, workBookName, "ProductInstanceServer", "TestProductInstCharge" );
			productInstanceHelpOb.createPrInstanceWithoutMandatory();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 11, enabled = true, description = "Product Instance creation with Credit, Charge Product items with optional" )
	public void createProductInstWithCreditChargeItems_Optional() throws Exception
	{
		try
		{
			ProductInstanceHelper productInstanceHelpOb = new ProductInstanceHelper( path, workBookName, "ProductInstanceServer", "TestProductInstCreditCharge_Opt" );
			productInstanceHelpOb.createPrInstanceWithoutMandatory();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 12, enabled = true, description = "Product Instance creation with Charge,Credit Product items with specify" )
	public void createProductInstWithChargeCreditItems_Specify() throws Exception
	{
		try
		{
			ProductInstanceHelper productInstanceHelpOb = new ProductInstanceHelper( path, workBookName, "ProductInstanceServer", "TestProductInstChargeCredit_Specify" );
			productInstanceHelpOb.createPrInstanceWithoutMandatory();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}
}
