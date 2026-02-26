package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.bills.CreditNotes;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCCreditNotes extends PSAcceptanceTest {
	String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "CreditNotes";

	 @org.testng.annotations.Test( priority = 1, description = "Credit Note  creation" , retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void creditNotesCreation() throws Exception {
		try {
			CreditNotes creditObj = new CreditNotes(path, workBookName, sheetName, "CreditNotes", 1);
			creditObj.creditNotesCreation();
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 2, description = "Credit Notes - Single Delta Bill ")
	public void creditNotesSingleDeltaBill() throws Exception {
		try {
			CreditNotes creditstatusObj = new CreditNotes(path, workBookName, sheetName, "CreditNotes singleBill_Delta",
					1);
			creditstatusObj.creditNotesCreation();

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 3, description = "Credit Notes - MultiCreditSingleBill_Delta")
	public void creditNotesMultiCreditDeltaBill() throws Exception {
		try {
			CreditNotes creditstatusObj = new CreditNotes(path, workBookName, sheetName,
					"CreditNotes MultiCreditSingleBill_Delta", 1);
			creditstatusObj.creditNotesCreation();

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 4, description = "CreditNotes _salesTaxChecked_Unchecked_Delta")
	public void creditNotesSalesTaxDeltaBill() throws Exception {
		try {
			CreditNotes creditstatusObj = new CreditNotes(path, workBookName, sheetName,
					"CreditNotes _salesTaxChecked_Unchecked_Delta", 1);
			creditstatusObj.creditNotesCreation();

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 5, description = "Credit Notes - currency Delta Bill ")
	public void creditNotesCurrencyDeltaBill() throws Exception {
		try {
			CreditNotes creditstatusObj = new CreditNotes(path, workBookName, sheetName,
					"CreditNotes _currency change DeltaBill", 1);
			creditstatusObj.creditNotesCreation();

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 6, description = "Credit Notes - sales tax nonDelta Bill ")
	public void creditNotessalesTaxnonDeltaBill() throws Exception {
		try {
			CreditNotes creditstatusObj = new CreditNotes(path, workBookName, sheetName,
					"CreditNotes _salesTaxUnCheck_check_NonDelta", 1);
			creditstatusObj.creditNotesCreation();

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 7, description = "Credit Notes - currency non Delta Bill ")
	public void creditNotesCurrencynonDeltaBill() throws Exception {
		try {
			CreditNotes creditCurrObj = new CreditNotes(path, workBookName, sheetName,
					"CreditNotes _currencychange nonDeltaBill", 1);
			creditCurrObj.creditNotesCreation();

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 8, description = "Credit Notes - includeinnext bill Delta Bill ")
	public void creditNotesincludeBillBill() throws Exception {
		try {
			CreditNotes creditincludeBillObj = new CreditNotes(path, workBookName, sheetName,
					"CreditNotes _includeinBill_DeltaBill", 1);
			creditincludeBillObj.creditNotesCreation();

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 9, description = "Credit Notes - EditCredit Line Item")
	public void creditNotesEdit() throws Exception {
		try {
			CreditNotes creditEditBillObj = new CreditNotes(path, workBookName, sheetName, "CreditNotes EditBillLinked",
					1);
			creditEditBillObj.editCreditNoteLineItem();

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 10, description = "Credit Notes - Multiple credit line items")
	public void creditNotessMultipleCreditsCreation() throws Exception {
		try {
			CreditNotes creditMulObj = new CreditNotes(path, workBookName, sheetName,
					"CreditNotes MultiCredit_standaloneDelta", 1);
			creditMulObj.creditNotesCreation();
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 11, description = "Credit Notes -  salesTax checked standalone ")
	public void creditNotesSalesTaxCheckCreation() throws Exception {
		try {
			CreditNotes creditsalObj = new CreditNotes(path, workBookName, sheetName,
					"CreditNotes salesTaxChecked_StandaloneDelta", 1);
			creditsalObj.creditNotesCreation();
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 12, description = "Credit Notes -  salesTax unchekced standalone")
	public void creditNotesSalesTaxUncheckCreation() throws Exception {
		try {
			CreditNotes creditsalObj = new CreditNotes(path, workBookName, sheetName,
					"CreditNotes salesTaxUnChecked_StandaloneDelta", 1);
			creditsalObj.creditNotesCreation();
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 13, description = "Credit Notes -  Include Bill standalone")
	public void creditNotesincludeBillCreation() throws Exception {
		try {
			CreditNotes creditsalObj = new CreditNotes(path, workBookName, sheetName,
					"CreditNotes includeBill_StandaloneDelta", 1);
			creditsalObj.creditNotesCreation();
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 14, description = "Credit Notes - Edit Standalone")
	public void creditNotesEditStandalone() throws Exception {
		try {
			CreditNotes creditEditObj = new CreditNotes(path, workBookName, sheetName, "CreditNotes EditStandalone", 1);
			creditEditObj.editCreditNoteLineItem();

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 15, description = "Credit Notes - change status")
	public void creditNotesChnageStatus() throws Exception {
		try {
			CreditNotes creditstatusObj = new CreditNotes(path, workBookName, sheetName, "CreditNotes changeStatus", 1);
			creditstatusObj.changeCreditNoteStatus();

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 16, description = "Credit Notes - cancel credit note")
	public void creditNotesCancel() throws Exception {
		try {
			CreditNotes creditstatusObj = new CreditNotes(path, workBookName, sheetName, "CreditNotes Cancel", 1);
			creditstatusObj.cancelCreditNote();

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 17, description = "Credit Notes - search screen column validation")
	public void creditNotesColVal() throws Exception {
		try {
			CreditNotes creditstatusObj = new CreditNotes(path, workBookName, sheetName,
					"CreditNotesSearchScreencolVal", 1);
			creditstatusObj.searchScreenColumnsValidation();

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

}
