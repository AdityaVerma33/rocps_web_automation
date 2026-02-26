package com.subex.automation.testcases.systemtesting.datafederation;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.FederatedDataSourceHelper;
import com.subex.automation.helpers.application.screens.FederatedViewHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.application.screens.SettingsHelper;
import com.subex.automation.helpers.application.screens.StreamHelper;
import com.subex.automation.helpers.application.screens.TableDefinitionHelper;
import com.subex.automation.helpers.application.screens.TableInstanceHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.ExecuteScript;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testPrerequisite1 extends ROCAcceptanceTest {
	
	private static String path = null;
	final String fileName = "FederatedDataSource_TestData.xlsx";
	final String sheetName = "FederatedDataSource";
	
	public testPrerequisite1() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Create Stream", groups = { "prerequisite1" })
	public void createStream() throws Exception
	{
		try {
			Log4jHelper.logInfo("Running Data Federation Flow");
			ControllerHelper controller = new ControllerHelper();
			controller.stopServices();
			
			SettingsHelper settings = new SettingsHelper();
			settings.updateSettings(path, fileName, sheetName, "UpdateSettings", 1);
			
			StreamHelper stream = new StreamHelper();
			stream.createStream(path, fileName, sheetName, "Stream", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Create Table Definition", dependsOnMethods = { "createStream" }, groups = { "prerequisite1" })
	public void createTD() throws Exception {
		try {
			TableDefinitionHelper tableDefinition = new TableDefinitionHelper();
			tableDefinition.importFromDiamond(path, fileName, sheetName, "ImportFromDiamond", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Create Table Instance", dependsOnMethods = { "createTD" }, groups = { "prerequisite1" })
	public void createTableInstance() throws Exception {
		try {
			TableInstanceHelper tableInstance = new TableInstanceHelper();
			tableInstance.createTableInstance(path, fileName, sheetName, "TableInstance", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Manual data load into lookup table", dependsOnMethods = { "createTableInstance" }, groups = { "prerequisite1" })
	public void manualDataLoad() throws Exception {
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.manualDataLoad(path, fileName, sheetName, "ManualDataload", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Create Federated DataSource", dependsOnMethods = { "manualDataLoad" }, groups = { "prerequisite1" })
	public void createFederatedDataSource()throws Exception
	{
		try {
			Log4jHelper.logInfo("Running Data Federation Flow");
			FederatedDataSourceHelper fedDataSource = new FederatedDataSourceHelper();
			fedDataSource.createFederatedDataSource(path, fileName, sheetName, "FederatedDataSource", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Test connection for Federated DataSource", dependsOnMethods = { "createFederatedDataSource" }, groups = { "prerequisite1" })
	public void testConnection()throws Exception
	{
		try {
			FederatedDataSourceHelper fedDataSource = new FederatedDataSourceHelper();
			fedDataSource.testConnection(path, fileName, sheetName, "FederatedDataSource", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=7, description="Create Table in Remote Database", dependsOnMethods = { "testConnection" }, groups = { "prerequisite1" })
	public void createTableInRemoteDB()throws Exception
	{
		try {
			String fileName = automationPath + "\\src\\main\\resources\\System_Test_Flows\\FederatedDataSource_Script.sql";
			ExecuteScript.exeScript(fileName, "FederatedDataSource");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=8, description="Create Federated View", dependsOnMethods = { "createTableInRemoteDB" }, groups = { "prerequisite1" })
	public void createFederatedView()throws Exception
	{
		try {
			FederatedViewHelper federatedView = new FederatedViewHelper();
			federatedView.createFederatedView(path, fileName, sheetName, "FederatedView", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}