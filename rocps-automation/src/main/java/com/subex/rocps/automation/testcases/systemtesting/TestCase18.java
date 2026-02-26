package com.subex.rocps.automation.testcases.systemtesting;

import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.application.bills.Bills;
import com.subex.rocps.automation.helpers.application.matchandrate.EventIdentiferDefinition;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRule;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRuleGroup;
import com.subex.rocps.automation.helpers.application.matchandrate.Operator;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Route;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.RouteGroup;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Switch;
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

public class TestCase18 extends PSAcceptanceTest {

	String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
	String workBookName = "SystemTestCases.xlsx";
	String sheetName = "Reversion-NonDelta";

	@org.testng.annotations.Test(priority = 1, description = "elements creation")
	public void createElement() throws Exception {
		try {

			ElementCreateHelper eleObj = new ElementCreateHelper();
			eleObj.createElement(path, workBookName, sheetName, "Elements", 1);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 2, description = "bands creation", dependsOnMethods = { "createElement" })
	public void createBand() throws Exception {
		try {
			BandHelper bndObj = new BandHelper();
			bndObj.createBand(path, workBookName, sheetName, "Bands", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 3, description = "tariffClassCreation creation", dependsOnMethods = {
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

	@org.testng.annotations.Test(priority = 4, description = "tariff creation", dependsOnMethods = {
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

	@org.testng.annotations.Test(priority = 5, description = "event identifier definition creation", dependsOnMethods = {
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

	@org.testng.annotations.Test(priority = 6, description = "operator creation", dependsOnMethods = {
			"createAccount" })
	public void createOperator() throws Exception {
		try {
			Operator operatorObj = new Operator(path, workBookName, sheetName, "Operator");
			operatorObj.operatorCreation();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 7, description = "switch creation", dependsOnMethods = { "createOperator" })
	public void createSwitch() throws Exception {
		try {
			Switch switchObj = new Switch(path, workBookName, sheetName, "Switch");
			switchObj.configureSwitch();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 8, description = "route group creation", dependsOnMethods = {
			"createOperator" })
	public void createRouteGroup() throws Exception {
		try {
			RouteGroup routeGrpObj = new RouteGroup(path, workBookName, sheetName, "RouteGroup");
			routeGrpObj.routeGrpCreation();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 9, description = "route creation", dependsOnMethods = { "createSwitch",
			"createRouteGroup" })
	public void createRoute() throws Exception {
		try {
			Route routeObj = new Route(path, workBookName, sheetName, "Routes");
			routeObj.routeCreation();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 10, description = "event match rule group creation", dependsOnMethods = {
			"createEventIdentifierDefn" })
	public void createEventMatchRuleGroup() throws Exception {
		try {
			EventMatchRuleGroup emrgObj = new EventMatchRuleGroup(path, workBookName, sheetName, "EventMatchRuleGroup");
			emrgObj.configureEventMatchRuleGroup();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 11, description = "account creation", dependsOnMethods = {
			"createEventIdentifierDefn" })
	public void createAccount() throws Exception {
		try {
			Account accObj = new Account(path, workBookName, sheetName, "Account");
			accObj.accountCreation();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 12, description = "Create a bill profile", dependsOnMethods = {
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

	@org.testng.annotations.Test(priority = 13, description = "event match rule creation", dependsOnMethods = {
			"createEventMatchRuleGroup", "createBillProfile", "createTariff" })
	public void createEventMatchRule() throws Exception {
		try {
			EventMatchRule emrObj = new EventMatchRule(path, workBookName, sheetName, "EventMatchRule");
			emrObj.configureEventMatchRule();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 14, description = "scheduling file collection and verify task status", dependsOnMethods = {
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

	@org.testng.annotations.Test(priority = 15, description = "run aggregation master task and verifying task status", dependsOnMethods = {
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

	@org.testng.annotations.Test(priority = 16, description = "verifying aggregation result", dependsOnMethods = {
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

	@org.testng.annotations.Test(priority = 17, description = "hot bill creation", dependsOnMethods = {
			"verifyAggregationResult" })
	public void createhotBill() throws Exception {
		try {
			Bills billObj = new Bills(path, workBookName, sheetName, "HotBill");
			billObj.billAction();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 18, description = "scheduling file collection and verify task status", dependsOnMethods = {
			"createhotBill" })
	public void scheduleFileCollectionFinal() throws Exception {
		try {

			TaskSchedule taskObj = new TaskSchedule();
			taskObj.fileCollection(path, workBookName, sheetName, "FileSchedule", 2);
			TaskSearchHelper tskObj = new TaskSearchHelper();
			tskObj.verifyTaskStatus(path, workBookName, sheetName, "MnrTaskStatus", 2);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 19, description = "run aggregation master task and verifying task status", dependsOnMethods = {
			"scheduleFileCollectionFinal" })
	public void recurringtasksFinal() throws Exception {
		try {

			TaskSearchHelper tskObj = new TaskSearchHelper();
			TaskSchedule taskObj1 = new TaskSchedule();
			taskObj1.scheduleRecurringTask(path, workBookName, sheetName, "RecurringTask", 2);
			tskObj.verifyTaskStatus(path, workBookName, sheetName, "AggrTaskStatus", 2);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 20, description = "verifying aggregation result", dependsOnMethods = {
			"recurringtasksFinal" })
	public void verifyAggregationResultFinal() throws Exception {
		try {
			AggregationResult aggrResObj = new AggregationResult(path, workBookName, sheetName,
					"AggregationResultFinal");
			aggrResObj.viewAggregationResult();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 21, description = "create test bill action", dependsOnMethods = {
			"verifyAggregationResultFinal" })
	public void createTestBillAction() throws Exception {
		try {
			Bills billObj = new Bills(path, workBookName, sheetName, "HotBill-CreateTestBill");
			billObj.billAction();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 22, description = "hot bill - Reversion", dependsOnMethods = {
			"createTestBillAction" })
	public void reVersionBill() throws Exception {
		try {
			Bills billObj = new Bills(path, workBookName, sheetName, "HotBill-ReVersion");
			billObj.billAction();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

}
