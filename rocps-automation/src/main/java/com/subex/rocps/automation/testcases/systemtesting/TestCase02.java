package com.subex.rocps.automation.testcases.systemtesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.application.matchandrate.EventIdentiferDefinition;
import com.subex.rocps.automation.helpers.application.matchandrate.EventIdentifierValue;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRule;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRuleGroup;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.application.screens.FastEntryHelper;
import com.subex.automation.helpers.application.screens.TariffClassHelper;
import com.subex.automation.helpers.application.screens.TariffHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TestCase02 extends PSAcceptanceTest {

	String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
	String workBookName = "SystemTestCases.xlsx";
	String sheetName = "Mnr-StringMatch";

	@org.testng.annotations.Test(priority = 1, description = "account creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void createAccount() throws Exception {
		try {
			Account accObj = new Account(path, workBookName, sheetName, "Account");
			accObj.accountCreation();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			;
			throw e;
		}
	}

	@Test(priority = 2, description = "Create a bill profile", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class, dependsOnMethods = {
			"createAccount" })
	public void createBillProfile() throws Exception {
		try {

			BillProfile bipObj = new BillProfile(path, workBookName, sheetName, "BillProfile");
			bipObj.billProfileCreation();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 3, description = "elements creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class, dependsOnMethods = {
			"createBillProfile" })
	public void createElement() throws Exception {
		try {

			ElementCreateHelper eleObj = new ElementCreateHelper();
			eleObj.createElement(path, workBookName, sheetName, "Elements", 1);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 4, description = "bands creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class, dependsOnMethods = {
			"createElement" })
	public void createBand() throws Exception {
		try {
			BandHelper bndObj = new BandHelper();
			bndObj.createBand(path, workBookName, sheetName, "Bands", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 5, description = "tariffClassCreation creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class, dependsOnMethods = {
			"createBand" })
	public void createTariffClass() throws Exception {
		try {
			TariffClassHelper tfcObj = new TariffClassHelper();
			tfcObj.createTariffClass(path, workBookName, sheetName, "TariffClass", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 6, description = "tariff creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class, dependsOnMethods = {
			"createTariffClass" })
	public void createTariff() throws Exception {
		try {
			TariffHelper tffObj = new TariffHelper();
			FastEntryHelper fastEntryObj = new FastEntryHelper();
			tffObj.createTariff(path, workBookName, sheetName, "Tariff", 1);
			fastEntryObj.createFastEntry(path, workBookName, sheetName, "FastEntry", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 7, description = "event identifier creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class, dependsOnMethods = {
			"createTariff" })
	public void createEventIdentifierDefn() throws Exception {
		try {
			EventIdentiferDefinition eidObj = new EventIdentiferDefinition(path, workBookName, sheetName,
					"EventIdentifierDefinition");
			eidObj.eventCreation();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 8, description = "event identifier value creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class, dependsOnMethods = {
			"createEventIdentifierDefn" })
	public void createEventIdentifierVal() throws Exception {
		try {
			EventIdentifierValue eivObj = new EventIdentifierValue(path, workBookName, sheetName,
					"EventIdentifierValue");
			eivObj.eventIdentifierValue();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 9, description = "event match rule group creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class, dependsOnMethods = {
			"createEventIdentifierVal" })
	public void createEventMatchRuleGroup() throws Exception {
		try {
			EventMatchRuleGroup emrgObj = new EventMatchRuleGroup(path, workBookName, sheetName, "EventMatchRuleGroup");
			emrgObj.configureEventMatchRuleGroup();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 10, description = "event match rule creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class, dependsOnMethods = {
			"createEventMatchRuleGroup" })
	public void createEventMatchRule() throws Exception {
		try {
			EventMatchRule emrObj = new EventMatchRule(path, workBookName, sheetName, "EventMatchRule");
			emrObj.configureEventMatchRule();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 11, description = "scheduling file collection and verify task status", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class, dependsOnMethods = {
			"createEventMatchRule" })
	public void scheduleFileCollection() throws Exception {
		try {

			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection(path, workBookName, sheetName, "FileSchedule", 1);
			TaskSearchHelper tskObj = new TaskSearchHelper();
			tskObj.verifyTaskStatus(path, workBookName, sheetName, "MnrTaskStatus", 1);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 12, description = "run aggregation master task and verifying task status", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class, dependsOnMethods = {
			"scheduleFileCollection" })
	public void recurringtasks() throws Exception {
		try {

			TaskSearchHelper tskObj = new TaskSearchHelper();
			TaskSchedule taskObj1 = new TaskSchedule();
			taskObj1.scheduleRecurringTask(path, workBookName, sheetName, "RecurringTask", 1);
			tskObj.verifyTaskStatus(path, workBookName, sheetName, "AggrTaskStatus", 1);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 13, description = "verifying aggregation result", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class, dependsOnMethods = {
	"recurringtasks" })
	public void verifyAggregationResult() throws Exception {
		try {
			AggregationResult aggrResObj = new AggregationResult(path, workBookName, sheetName, "AggregationResult");
			aggrResObj.viewAggregationResult();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

}
