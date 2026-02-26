package com.subex.rocps.automation.testcases.systemtesting;

import com.subex.rocps.automation.helpers.application.bills.BillBreakDownInput;
import com.subex.rocps.automation.helpers.application.bills.BillBreakdownConfiguration;
import com.subex.rocps.automation.helpers.application.bills.BillBreakdownExtraField;
import com.subex.rocps.automation.helpers.application.bills.BillBreakdownInputGroup;
import com.subex.rocps.automation.helpers.application.bills.BillBreakdownOutput;
import com.subex.rocps.automation.helpers.application.bills.BillBreakdownOutputGroup;
import com.subex.rocps.automation.helpers.application.bills.BillPackage;
import com.subex.rocps.automation.helpers.application.bills.SalesTaxGroup;
import com.subex.rocps.automation.helpers.application.bills.SalesTaxRate;
import com.subex.rocps.automation.helpers.application.matchandrate.CrossFXRate;
import com.subex.rocps.automation.helpers.application.referenceTable.CrossFXRateGroup;
import com.subex.rocps.automation.helpers.application.referenceTable.SalesTax;
import com.subex.rocps.automation.helpers.application.system.Streams;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class BillPreRequisites extends PSAcceptanceTest {

	String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
	String workBookName = "SystemTestCases.xlsx";
	String sheetName = "BillPreRequisites";

	@org.testng.annotations.Test(priority = 1, description = "Create billing stream", groups = {
			"Prerequisites4" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void createBillingstream() throws Exception {
		try {
			String partition = null;
			Streams streamObj = new Streams();
			streamObj.newStreamConfig(path, workBookName, sheetName, "Streams", 1);
			streamObj.editStreamConfig(path, workBookName, sheetName, "Streams", 1);
			streamObj.billingStreamConfig(path, workBookName, sheetName, "Credit", 1);
			streamObj.billingStreamConfig(path, workBookName, sheetName, "Bill", 1);
			streamObj.saveStreamDetail();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 2, description = "bill breakdown extra field creation", groups = {
			"Prerequisites4" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class, dependsOnMethods = {
					"createBillingstream" })
	public void createBillBreakdownExtraField() throws Exception {
		try {

			BillBreakdownExtraField billextrafieldObj = new BillBreakdownExtraField(path, workBookName, sheetName,
					"BillBreakdownExtraField");
			billextrafieldObj.billExtraFieldCreation();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 3, description = "bill breakdown Configuration creation", groups = {
			"Prerequisites4" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class, dependsOnMethods = {
					"createBillBreakdownExtraField" })
	public void createBillBreakdownConfig() throws Exception {
		try {

			BillBreakdownConfiguration billconfigObj = new BillBreakdownConfiguration(path, workBookName, sheetName,
					"BillBreakdownConfig");
			billconfigObj.billConfigCreation();
			billconfigObj.billbreakdownChangeStatus();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 4, description = "bill breakdown Input creation", groups = {
			"Prerequisites4" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class, dependsOnMethods = {
					"createBillBreakdownConfig" })
	public void createBillBreakdownInput() throws Exception {
		try {

			BillBreakDownInput billInputObj = new BillBreakDownInput(path, workBookName, sheetName, "BillInput");
			billInputObj.billInputCreation();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 5, description = "bill breakdown Input Group creation", groups = {
			"Prerequisites4" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class, dependsOnMethods = {
					"createBillBreakdownInput" })
	public void createBillBreakdownInputGrp() throws Exception {
		try {

			BillBreakdownInputGroup billInputGrpObj = new BillBreakdownInputGroup(path, workBookName, sheetName,
					"BillInputGrp");
			billInputGrpObj.billInputGrpCreation();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 6, description = "bill breakdown Output creation", groups = {
			"Prerequisites4" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class, dependsOnMethods = {
					"createBillBreakdownInputGrp" })
	public void createBillBreakdownOutput() throws Exception {
		try {

			BillBreakdownOutput billOutputObj = new BillBreakdownOutput(path, workBookName, sheetName,
					"BillBreakdownOutput");
			billOutputObj.billOutputCreation();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 7, description = "bill breakdown Output Group creation", groups = {
			"Prerequisites4" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class, dependsOnMethods = {
					"createBillBreakdownOutput" })
	public void createBillBreakdownOutputGrp() throws Exception {
		try {

			BillBreakdownOutputGroup billOutputGrpObj = new BillBreakdownOutputGroup(path, workBookName, sheetName,
					"BillOutputGrp");
			billOutputGrpObj.billOutputGrpCreation();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 8, description = "bill package creation", groups = {
			"Prerequisites4" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class, dependsOnMethods = {
					"createBillBreakdownOutputGrp" })
	public void createBillPackage() throws Exception {
		try {

			BillPackage billpackageObj = new BillPackage(path, workBookName, sheetName, "BillPackage");
			billpackageObj.billPackageCreation();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 9, description = "cross FX Rate Group creation", groups = {
			"Prerequisites4" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class, dependsOnMethods = {
					"createBillPackage" })
	public void createCrossFXRateGroup() throws Exception {
		try {

			CrossFXRateGroup crossFXRateGrpObj = new CrossFXRateGroup(path, workBookName, sheetName, "CrossFxRateGrp");
			crossFXRateGrpObj.crossFXRateGrpCreation();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 10, description = "cross FX Rate creation", groups = {
			"Prerequisites4" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class, dependsOnMethods = {
					"createCrossFXRateGroup" })
	public void createCrossFXRate() throws Exception {
		try {

			CrossFXRate crossFXRateObj = new CrossFXRate(path, workBookName, sheetName, "CrossFXRate");
			crossFXRateObj.crossFXRatesCreation();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 11, description = "Sales Tax creation", groups = {
			"Prerequisites4" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class, dependsOnMethods = {
					"createCrossFXRate" })
	public void

			createSalesTax() throws Exception {
		try {

			SalesTax salesTaxObj = new SalesTax(path, workBookName, sheetName, "Sales Tax");
			salesTaxObj.salesTaxCreation();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 12, description = "Sales Tax rate creation", groups = {
			"Prerequisites4" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class, dependsOnMethods = {
					"createSalesTax" })
	public void createSalesTaxRate() throws Exception {
		try {

			SalesTaxRate salesTaxRateObj = new SalesTaxRate(path, workBookName, sheetName, "Sales Tax Rate");
			salesTaxRateObj.salesTaxRateCreation();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 13, description = "Sales Tax rate group creation", groups = {
			"Prerequisites4" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class, dependsOnMethods = {
					"createSalesTaxRate" })
	public void createSalesTaxRateGroup() throws Exception {
		try {

			SalesTaxGroup salesTaxgrpObj = new SalesTaxGroup(path, workBookName, sheetName, "Sales Tax Group");
			salesTaxgrpObj.salesTaxGroupCreation();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

}
