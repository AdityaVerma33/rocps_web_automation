package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.bills.BillingGroupCode;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.products.ProductBundleDrillDownHelper;
import com.subex.rocps.automation.helpers.application.products.ProductBundlesHelper;
import com.subex.rocps.automation.helpers.application.products.ProductInstanceHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCProductBundleDrillDown extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "ProductBundleDrillDown";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = "Product InstancePrerequistes Account, Bill Profile creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void ProductBundleDrillDownPrerequistes1() throws Exception

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

			ProductBundlesHelper productBundlesObj = new ProductBundlesHelper( path, workBookName, "ProductBundle", "Test02_PB2_WithoutItems" );
			productBundlesObj.createProductBundle();

			ProductBundlesHelper productBundleObMand = new ProductBundlesHelper( path, workBookName, "ProductInstance", "TestProductBundle_Mandatory" );
			productBundleObMand.createProductBundle();

			ProductBundlesHelper productBundleObOp1 = new ProductBundlesHelper( path, workBookName, "ProductInstance", "TestProductBundle_OptProducts" );
			productBundleObOp1.createProductBundle();

			ProductBundlesHelper productBundleObOp2 = new ProductBundlesHelper( path, workBookName, "ProductInstance", "TestProductBundle_OptProdItems" );
			productBundleObOp2.createProductBundle();

			ProductBundlesHelper productBundleOb1 = new ProductBundlesHelper( path, workBookName, "ProductInstance", "TestBundleChangeStatus" );
			productBundleOb1.changeStatusBundleAction();

		}

		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 3, enabled = true, description = "Product Instance creation Prerequisite ", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void ProductInstanceCreationPrerequisite() throws Exception
	{
		try
		{
			ProductInstanceHelper productInstanceHelpOb1 = new ProductInstanceHelper( path, workBookName, "ProductInstance", "TestProductInstanceMandatory" );
			productInstanceHelpOb1.createProductInstWithMandatory();

			ProductInstanceHelper productInstanceHelpOb2 = new ProductInstanceHelper( path, workBookName, "ProductInstance", "TestProductInstOptProducts" );
			productInstanceHelpOb2.createPrInstanceWithoutMandatory();

			ProductInstanceHelper productInstanceHelpOb3 = new ProductInstanceHelper( path, workBookName, "ProductInstance", "TestProductInstOptProdItems" );
			productInstanceHelpOb3.createPrInstanceWithoutMandatory();

		}

		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 4, enabled = true, description = "Product bundle Drill-Down verify the column Headers Of all Product Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			ProductBundleDrillDownHelper pBundleDrillDownHelper = new ProductBundleDrillDownHelper( path, workBookName, sheetName, "ProductBundleDrillDownScreencolVal" );
			pBundleDrillDownHelper.productBundleDrillDownColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 5, enabled = true, description = "Product bundle Drill-Down: Product Bundle Edit" )
	public void productBundleEdit() throws Exception
	{
		try
		{

			ProductBundleDrillDownHelper pBundleDrillDownHelperOb = new ProductBundleDrillDownHelper( path, workBookName, sheetName, "TestProductBundleEditAction" );
			pBundleDrillDownHelperOb.productBundleEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 6, enabled = true, description = "Product bundle Drill-Down: Product Instance Edit" )
	public void productInstanceEdit() throws Exception
	{
		try
		{

			ProductBundleDrillDownHelper pBundleDrillDownHelperOb = new ProductBundleDrillDownHelper( path, workBookName, sheetName, "TestProductInstanceEditAction" );
			pBundleDrillDownHelperOb.productInstanceEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 7, enabled = true, description = "Product bundle Drill-Down: Product Instance Item Edit" )
	public void productInstanceItemEdit() throws Exception
	{
		try
		{

			ProductBundleDrillDownHelper pBundleDrillDownHelperOb = new ProductBundleDrillDownHelper( path, workBookName, sheetName, "TestProductInstanceItemEditAction" );
			pBundleDrillDownHelperOb.productInstanceItemEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 8, enabled = true, description = "Product bundle Drill-Down: 'One Off Item Instance' Edit" )
	public void oneOffInstanceItemEdit() throws Exception
	{
		try
		{

			ProductBundleDrillDownHelper pBundleDrillDownHelperOb = new ProductBundleDrillDownHelper( path, workBookName, sheetName, "TestOneOffInstItemEditAction" );
			pBundleDrillDownHelperOb.oneOffItemInstanceEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 9, enabled = true, description = "Product bundle Drill-Down: 'Recurring Item Instance' Edit" )
	public void recurringInstanceItemEdit() throws Exception
	{
		try
		{

			ProductBundleDrillDownHelper pBundleDrillDownHelperOb = new ProductBundleDrillDownHelper( path, workBookName, sheetName, "TestRecurringInstItemEditAction" );
			pBundleDrillDownHelperOb.recurringItemInstanceEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@Test( priority = 10, enabled = true, description = "Product bundle Drill-Down: 'Product Instance Item Member' Edit" )
	public void productInstanceItemMemberEdit() throws Exception
	{
		try
		{

			ProductBundleDrillDownHelper pBundleDrillDownHelperOb = new ProductBundleDrillDownHelper( path, workBookName, sheetName, "TestProductInstItemMemberEditAction" );
			pBundleDrillDownHelperOb.productInstItemMemberEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

}
