package com.subex.automation.testcases.systemtesting.audits2;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.AuditDefinitionHelper;
import com.subex.automation.helpers.application.screens.AuditRequestHelper;
import com.subex.automation.helpers.application.screens.ComparisonMeasureHelper;
import com.subex.automation.helpers.application.screens.DataMatchMeasureHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.application.screens.SQLMeasureHelper;
import com.subex.automation.helpers.application.screens.TrendMeasureHelper;
import com.subex.automation.helpers.application.screens.TrendSummaryTableHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testPrerequisite2 extends ROCAcceptanceTest
{
	private static String path = null;
	private static String dataDir = null;
	final String fileName = "AuditFlow2_TestData.xlsx";
	final String sheetName = "AuditFlow2";
	
	public testPrerequisite2() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Create directories", dependsOnGroups = { "prerequisite1" }, groups = { "prerequisite2" })
	public void dataDirCleanup() throws Exception
	{
		try {
			//Create Input, Collected Files and ParseOutput directories for parse task
			dataDir = configProp.getDataDirPath();
			FileHelper.createDir(applicationOS, dataDir + "/Input");
			FileHelper.createDir(applicationOS, dataDir + "/Collected Files");
			FileHelper.createDir(applicationOS, dataDir + "/ParseOutput");
			
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input/Audit_Flow2", true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input/Audit_Flow2/Usage1", true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input/Audit_Flow2/Usage2", true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Collected Files/Audit_Flow2", true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/ParseOutput/Audit_Flow2", true);
			NavigationHelper.navigateToScreen("Measures", "Measure Search");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Create Query Measures", dependsOnMethods = { "dataDirCleanup" }, groups = { "prerequisite2" })
	public void createQueryMeasure() throws Exception
	{
		try {
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Create Data Match Measure", dependsOnMethods = { "createQueryMeasure" }, groups = { "prerequisite2" })
	public void createDataMatchMeasure() throws Exception
	{
		try {
			DataMatchMeasureHelper dataMatchMeasure = new DataMatchMeasureHelper();
			dataMatchMeasure.createDataMatchMeasure(path, fileName, sheetName, "DataMatchMeasure", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Create Comparison Measure", dependsOnMethods = { "createDataMatchMeasure" }, groups = { "prerequisite2" })
	public void createComparisonMeasure() throws Exception
	{
		try {
			ComparisonMeasureHelper comparisonMeasure = new ComparisonMeasureHelper();
			comparisonMeasure.createComparisonMeasure(path, fileName, sheetName, "ComparisonMeasure", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Create a Trend Summary", dependsOnMethods = { "createComparisonMeasure" }, groups = { "prerequisite2" })
	public void creatTrendSummaryTable() throws Exception
	{
		try {
			TrendSummaryTableHelper trendSummaryTable = new TrendSummaryTableHelper();
			trendSummaryTable.createTrendSummaryTable(path, fileName, sheetName, "TrendSummaryTable", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Create Trend Measure", dependsOnMethods = { "creatTrendSummaryTable" }, groups = { "prerequisite2" })
	public void createTrendMeasure() throws Exception
	{
		try {
			TrendMeasureHelper trendMeasure = new TrendMeasureHelper();
			trendMeasure.createTrendMeasure(path, fileName, sheetName, "TrendMeasure", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=7, description="Create SQL Measure", dependsOnMethods = { "createTrendMeasure" }, groups = { "prerequisite2" })
	public void createSQLMeasure()throws Exception
	{
		try {
			SQLMeasureHelper sqlMeasure = new SQLMeasureHelper();
			sqlMeasure.createSQLMeasure(path, fileName, sheetName, "SQLMeasure", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=8, description="Create an Audit with all the measures", dependsOnMethods = { "createSQLMeasure" }, groups = { "prerequisite2" })
	public void createAuditDefinition()throws Exception
	{
		try {
			AuditDefinitionHelper auditDefinition = new AuditDefinitionHelper();
			auditDefinition.createAuditDefinition(path, fileName, sheetName, "Audits", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=9, description="Creating an Audit Request for the Audit", dependsOnMethods = { "createAuditDefinition" }, groups = { "prerequisite2" })
	public void createAuditRequest()throws Exception
	{
		try {
			AuditRequestHelper auditRequest = new AuditRequestHelper();
			auditRequest.createAuditRequest(path, fileName, sheetName, "AuditRequest", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}