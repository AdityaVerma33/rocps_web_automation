package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.carrierinvoice.InvoiceReconciliationRequest;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCInvoiceReconRequest extends PSAcceptanceTest {
	String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "ReconRequest";

	@org.testng.annotations.Test(priority = 1, description = "Invoice Recon Request search screen column validation")
	public void invoiceReconRequestColVal() throws Exception {
		try {
			InvoiceReconciliationRequest autoObj = new InvoiceReconciliationRequest(path, workBookName, sheetName,
					"Recon_ColumnVal", 1);
			autoObj.searchScreenColumnsValidation();
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 2, description = "Invoice Recon Config Creation")
	public void invoiceReconRequestCreation() throws Exception {
		try {
			InvoiceReconciliationRequest autoObj = new InvoiceReconciliationRequest(path, workBookName, sheetName,
					"Recon_Request1", 1);
			autoObj.invoiceReconciliationRequestCreation();
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 3, description = "Invoice Recon Config-Schedule Now")
	public void invoiceReconRequestScheduleNow() throws Exception {
		try {
			InvoiceReconciliationRequest autoObj = new InvoiceReconciliationRequest(path, workBookName, sheetName,
					"Recon_ScheduleNow", 1);
			autoObj.invoiceReconConfigScheduleNow();

			TaskSearchHelper tskObj = new TaskSearchHelper();
			tskObj.verifyTaskStatus(path, workBookName, sheetName, "ReconTaskStatus", 1);
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 4, description = "Invoice Recon Config-view results")
	public void invoiceReconRequestViewResults() throws Exception {
		try {
			InvoiceReconciliationRequest autoObj = new InvoiceReconciliationRequest(path, workBookName, sheetName,
					"Recon_ViewResults", 1);
			autoObj.invoiceReconConfigViewResults();
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 5, description = "Invoice Recon Config-REschedule")
	public void invoiceReconRequestReschedule() throws Exception {
		try {
			InvoiceReconciliationRequest autoObj = new InvoiceReconciliationRequest(path, workBookName, sheetName,
					"Recon_Reschedule", 1);
			//autoObj.invoiceReconConfigReschedule();
		//	autoObj.reconValidation();
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 6, description = "Invoice Recon Config-Baseline")
	public void invoiceReconRequestBaseline() throws Exception {
		try {
			InvoiceReconciliationRequest autoObj = new InvoiceReconciliationRequest(path, workBookName, sheetName,
					"Recon_Baseline", 1);
			autoObj.invoiceReconConfigBaseLine();
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 7, description = "Invoice Recon Config-View Recon Action")
	public void invoiceReconRequestViewRecon() throws Exception {
		try {
			InvoiceReconciliationRequest autoObj = new InvoiceReconciliationRequest(path, workBookName, sheetName,
					"Recon_ViewRecon", 1);
			autoObj.invoiceReconConfigViewRecon();
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

}