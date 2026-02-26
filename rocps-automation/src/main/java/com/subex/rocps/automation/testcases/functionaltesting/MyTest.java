package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.matchandrate.Operator;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Switch;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Franchise;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class MyTest extends PSAcceptanceTest {

	String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
	String workBookName = "AutomateRahulTestCases.xlsx";
	String sheetName = "Configurations";

	@org.testng.annotations.Test(priority = 1, description = "bank creation")
	public void createBank() throws Exception {
		try {
			Franchise bankObj = new Franchise(path, workBookName, sheetName, "Bank");
			bankObj.franchiseCreation();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			;
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 2, description = "agent creation", dependsOnMethods = { "createBank" })
	public void createAgent() throws Exception {
		try {
			Agent agentObj = new Agent(path, workBookName, sheetName, "Agent");
			agentObj.agentCreation();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);

			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 3, description = "account creation", dependsOnMethods = { "createAgent" })
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

	@org.testng.annotations.Test(priority = 4, description = "bill profile creation", dependsOnMethods = {
			"createAccount" })
	public void createBillProfile() throws Exception {
		try {
			BillProfile billObj = new BillProfile(path, workBookName, sheetName, "BillProfile");
			billObj.billProfileCreation();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 5, description = "Operator creation", dependsOnMethods = {
			"createBillProfile" })
	public void createOperator() throws Exception {
		try {

			Operator opeObj = new Operator(path, workBookName, sheetName, "Operator");
			opeObj.operatorCreation();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 6, description = "element creation", dependsOnMethods = {
			"createOperator" })
	public void createElement() throws Exception {
		try {
			ElementCreateHelper eleObj = new ElementCreateHelper();
			eleObj.createElement(path, workBookName, sheetName, "Elements", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 7, description = "band creation", dependsOnMethods = { "createElement" })
	public void createband() throws Exception {
		try {
			BandHelper bandObj = new BandHelper();
			bandObj.createBand(path, workBookName, sheetName, "Bands", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	@org.testng.annotations.Test(priority = 8, description = "switch creation", dependsOnMethods = { "createband" })
	public void createSwitch() throws Exception {
		try {

			Switch switchObj = new Switch(path, workBookName, sheetName, "Switch");
			switchObj.configureSwitch();

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

}
