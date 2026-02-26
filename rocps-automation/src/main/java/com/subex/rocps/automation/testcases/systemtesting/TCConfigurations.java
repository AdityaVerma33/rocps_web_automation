package com.subex.rocps.automation.testcases.systemtesting;

import com.subex.rocps.automation.helpers.application.matchandrate.EventIdentiferDefinition;
import com.subex.rocps.automation.helpers.application.matchandrate.Operator;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Route;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.RouteGroup;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Switch;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.referenceTable.Bank;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Franchise;
import com.subex.rocps.automation.helpers.application.referenceTable.ServiceTariffType;
import com.subex.rocps.automation.helpers.application.referenceTable.SignallingType;
import com.subex.rocps.automation.helpers.application.referenceTable.TrafficType;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.TariffReferenceTableHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCConfigurations extends PSAcceptanceTest {

	String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
	String workBookName = "SystemTestCases.xlsx";
	String sheetName = "Configurations";

	@org.testng.annotations.Test(priority = 1, description = "task Controller capabilities", groups = {
			"Prerequisites6" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void taskControllerCapabilities() throws Exception {
		try {

			TaskControllerHelper taskObj = new TaskControllerHelper();
			taskObj.setTaskControllerCapability(path, workBookName, sheetName, "TCCapability", 1);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 2, description = "signalling type creation", groups = {
			"Prerequisites6" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class, dependsOnMethods = {
					"taskControllerCapabilities" })
	public void createsignallingtype() throws Exception {
		try {
			SignallingType sigObj = new SignallingType(path, workBookName, sheetName, "SignallingType");
			sigObj.signallingTypeCreation();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 3, description = "tariffMetricType creation", groups = {
			"Prerequisites6" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void createtariffMetricType() throws Exception {
		try {
			TariffReferenceTableHelper tariffObj = new TariffReferenceTableHelper();
			tariffObj.tariffMetricType(path, workBookName, sheetName, "TariffMetricType", 1);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 4, description = "tariffRateName creation", groups = {
			"Prerequisites6" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void createtariffRateName() throws Exception {
		try {
			TariffReferenceTableHelper tariffRateObj = new TariffReferenceTableHelper();
			tariffRateObj.tariffRateName(path, workBookName, sheetName, "TariffRateName", 1);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 5, description = "trafficType creation", groups = {
			"Prerequisites6" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void createtariffType() throws Exception {
		try {
			TrafficType trafficObj = new TrafficType(path, workBookName, sheetName, "TrafficType");
			trafficObj.trafficTypeCreation();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 6, description = "Service TariffType creation", groups = {
			"Prerequisites6" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void createServiceTariffType() throws Exception {
		try {
			ServiceTariffType serviceObj = new ServiceTariffType(path, workBookName, sheetName, "ServiceTariffType");
			serviceObj.serviceTariffTypeCreation();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 7, description = "bank creation", groups = {
			"Prerequisites6" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void createBank() throws Exception {
		try {
			Bank bankObj = new Bank(path, workBookName, sheetName, "Bank");
			bankObj.bankCreation();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			;
			throw e;
		}
	}

	
	@org.testng.annotations.Test(priority = 8, description = "franchise creation", groups = {
			"Prerequisites6" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void createFranchise() throws Exception {
		try {
			Franchise franchiseObj = new Franchise(path, workBookName, sheetName, "Franchise");
			franchiseObj.franchiseCreation();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			;
			throw e;
		}
	}
	 

	@org.testng.annotations.Test(priority = 9, description = "agent creation", groups = {
			"Prerequisites6" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void createAgent() throws Exception {
		try {
			Agent agentObj = new Agent(path, workBookName, sheetName, "Agent");
			agentObj.agentCreation();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);

			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 10, description = "account creation", groups = {
			"Prerequisites6" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class,dependsOnMethods= {"createAgent"})
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

	@org.testng.annotations.Test(priority = 11, description = "Operator creation", groups = {
			"Prerequisites6" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class,dependsOnMethods= {"createAccount"})
	public void createOperator() throws Exception {
		try {

			Operator opeObj = new Operator(path, workBookName, sheetName, "Operator");
			opeObj.operatorCreation();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 12, description = "switch creation", groups = {
			"Prerequisites6" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void createSwitch() throws Exception {
		try {

			Switch switchObj = new Switch(path, workBookName, sheetName, "Switch");
			switchObj.configureSwitch();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 13, description = "route group creation", groups = {
			"Prerequisites6" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class,dependsOnMethods= {"createOperator"})
	public void createrouteGroup() throws Exception {
		try {

			RouteGroup routegrpObj = new RouteGroup(path, workBookName, sheetName, "RouteGroup");
			routegrpObj.routeGrpCreation();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 14, description = "route creation", groups = {
			"Prerequisites6" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class,dependsOnMethods= {"createrouteGroup"})
	public void createRoute() throws Exception {
		try {
			Route routeObj = new Route(path, workBookName, sheetName, "Route");
			routeObj.routeCreation();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 15, description = "event identifier Definition creation", groups = {
			"Prerequisites6" }, retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void createEventIdentifierDefn() throws Exception {
		try {
			EventIdentiferDefinition eidObj = new EventIdentiferDefinition(path, workBookName, sheetName, "EventDefn");
			eidObj.eventCreation();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

}
