package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.BandHelper;
import com.subex.automation.helpers.application.screens.ElementCreateHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.matchandrate.Operator;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.Switch;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Account;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Agent;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.Franchise;
import com.subex.rocps.automation.helpers.application.referenceTable.RoamingDefnGroup;
import com.subex.rocps.automation.helpers.application.referenceTable.TadigCodes;
import com.subex.rocps.automation.helpers.application.roaming.RoamingAgreementConfig;
import com.subex.rocps.automation.helpers.application.roaming.RoamingConfiguration;
import com.subex.rocps.automation.helpers.application.roaming.RoamingDefinition;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class MyTestRoaming extends PSAcceptanceTest {

	String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
	String workBookName = "ROCPS_Roaming.xlsx";
	String sheetName = "Configurations";

	/*
	 * @Test(priority = 1, enabled = true, description =
	 * "'Tadig Codes'  creation Prerequisite for Roaming Definition", retryAnalyzer
	 * = com.subex.rocps.automation.helpers.listener.Retry.class) public void
	 * tadigCodeCreation() throws Exception { try {
	 * 
	 * TadigCodes tadigCodes = new TadigCodes(path, workBookName, sheetName,
	 * "TestTadigCodeCreation"); tadigCodes.tadigCodesCreation(); } catch (Exception
	 * e) { FailureHelper.setErrorMessage(e); throw e; } }
	 * 
	 * @Test(priority = 2, enabled = true, description =
	 * "'Roaming Definition' creation for 'Home' Type Of Agreement", retryAnalyzer =
	 * com.subex.rocps.automation.helpers.listener.Retry.class) public void
	 * roamingDefnCreationHomeAgreement() throws Exception { try {
	 * 
	 * RoamingDefinition roamingDefn = new RoamingDefinition(path, workBookName,
	 * sheetName, "TestRoamingDfnCreationHome"); roamingDefn.roamingDefnCreation();
	 * } catch (Exception e) { FailureHelper.setErrorMessage(e); throw e; } }
	 * 
	 * @Test(priority = 3, enabled = true, description =
	 * "'Roaming Definition' creation for 'Direct' Type Of Agreement", retryAnalyzer
	 * = com.subex.rocps.automation.helpers.listener.Retry.class) public void
	 * roamingDefnCreationDirectAgreement() throws Exception { try {
	 * 
	 * RoamingDefinition roamingDefn = new RoamingDefinition(path, workBookName,
	 * sheetName, "TestRoamingDfnCreationDirect");
	 * roamingDefn.roamingDefnCreation(); } catch (Exception e) {
	 * FailureHelper.setErrorMessage(e); throw e; } }
	 * 
	 * @Test(priority = 4, enabled = true, description =
	 * "'Roaming  Configuration'  creation Tap In configuration type", retryAnalyzer
	 * = com.subex.rocps.automation.helpers.listener.Retry.class) public void
	 * roamingConfigCreationTapIn() throws Exception { try { RoamingConfiguration
	 * roamingConfiguration = new RoamingConfiguration(path, workBookName,
	 * sheetName, "TestTapInRoamingConfigCreation");
	 * roamingConfiguration.roamingConfigCreation(); } catch (Exception e) {
	 * FailureHelper.setErrorMessage(e); throw e; } }
	 */
	
	@Test(priority = 5, enabled = true, description = "'Roaming  Agreement Configuration Creation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void roamingAgreementConfigCreation() throws Exception {
		try {
			RoamingAgreementConfig roamingAgreementConfig = new RoamingAgreementConfig(path, workBookName, sheetName,
					"TestRoamingAgreeConfigCreationHome");
			roamingAgreementConfig.roamingAgreemConfigCreation();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

}
