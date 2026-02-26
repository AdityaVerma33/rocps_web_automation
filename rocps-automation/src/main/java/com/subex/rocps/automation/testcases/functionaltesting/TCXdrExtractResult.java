package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.application.screens.FileCollectionHelper;
import com.subex.automation.helpers.application.screens.FileSourceHelper;
import com.subex.automation.helpers.application.screens.RecurringTaskHelper;
import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.application.screens.TriggerHelper;
import com.subex.automation.helpers.util.FailureHelper;

import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRuleGroup;
import com.subex.rocps.automation.helpers.application.matchandrate.Operator;
import com.subex.rocps.automation.helpers.application.monitoring.PSCollectedFileSearchHelper;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Route;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.RouteGroup;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Switch;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.referenceTable.AggrComponentMapping;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.application.tariffs.PSTariffHelper;
import com.subex.rocps.automation.helpers.application.xdrextraction.XdrExtrTempHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCXdrExtractResult extends PSAcceptanceTest {

	String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";

	@org.testng.annotations.Test(priority = 1, enabled = true, description = "task Controller capabilities")
	public void taskControllerCapabilities() throws Exception {
		try {

			TaskControllerHelper taskObj = new TaskControllerHelper();
			taskObj.setTaskControllerCapability(path, workBookName, "XDR RocPrerequiste", "TCCapability", 1);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 2, enabled = true, description = "Mnr and Aggregation for XdrServerPrerequistes without bill")
	public void XdrServerPrerequistesWithoutBill() throws Exception

	{
		try {
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection(path, workBookName, "XDR ExtractionResults", "FileSchedule_Xdr_withoutBill", 1);
			PSCollectedFileSearchHelper collectedFlObj = new PSCollectedFileSearchHelper(path, workBookName,
					"XDR ExtractionResults", "CollectedFileSearchWithoutBill");
			collectedFlObj.validationOfCollectedFile();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			psTaskobj.psVerifyTaskStatus(path, workBookName, "XDR ExtractionResults", "MnrTaskStatus", 1);

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 3, enabled = true, description = "Schedule and verify Recurring task for  Aggregation for XdrServerPrerequistes without bill")
	public void XdrServerPrerequistesWithoutBill2() throws Exception

	{
		try {
			TaskSchedule taskObj = new TaskSchedule();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			taskObj.scheduleRecurringTask(path, workBookName, "XDR ExtractionResults", "RecurringTask_XDR_WithoutBill",
					1);
			psTaskobj.psVerifyTaskStatus(path, workBookName, "XDR ExtractionResults",
					"AggregationTaskStatus_Xdr_WithoutBill", 1);

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 4, enabled = true, description = "Mnr and Aggregation for XdrServerPrerequistes with bill")
	public void XdrServerPrerequistesWithBill() throws Exception

	{
		try {
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection(path, workBookName, "XDR ExtractionResults", "FileSchedule_Xdr_withBill", 1);
			PSCollectedFileSearchHelper collectedFlObj = new PSCollectedFileSearchHelper(path, workBookName,
					"XDR ExtractionResults", "CollectedFileSearchWithBill");
			collectedFlObj.validationOfCollectedFile();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			psTaskobj.psVerifyTaskStatus(path, workBookName, "XDR ExtractionResults", "MnrTaskStatus", 1);
			taskObj.scheduleRecurringTask(path, workBookName, "XDR ExtractionResults", "RecurringTaskWithBill", 1);
			psTaskobj.psVerifyTaskStatus(path, workBookName, "XDR ExtractionResults",
					"AggregationTaskStatus_Xdr_WithBill", 1);

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	@Test(priority = 5, enabled = true, description = "Validate files for XDr extraction Full file without bill")
	public void validateXdrFullFileWithoutbill() throws Exception {
		try {
			XdrExtrTempHelper xdrExtrTempHelper = new XdrExtrTempHelper(path, workBookName, "XDR Extraction",
					"XdrExtractionFullFileValidationWithoutBill");
			xdrExtrTempHelper.xdrFullFileValidation();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@Test(priority = 6, enabled = true, description = "Validate files for XDr extraction Full file with bill")
	public void validateXdrFullFileWithBill() throws Exception {
		try {
			XdrExtrTempHelper xdrExtrTempHelper = new XdrExtrTempHelper(path, workBookName, "XDR Extraction",
					"XdrExtractionFullFileValidationWithBill");
			xdrExtrTempHelper.xdrFullFileValidation();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

}
