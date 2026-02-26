package com.subex.automation.testcases.systemtesting.audits1;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.AuditDefinitionHelper;
import com.subex.automation.helpers.application.screens.AuditRequestHelper;
import com.subex.automation.helpers.application.screens.ComparisonMeasureHelper;
import com.subex.automation.helpers.application.screens.DataMatchMeasureHelper;
import com.subex.automation.helpers.application.screens.KPIDefinitionHelper;
import com.subex.automation.helpers.application.screens.QueryMeasureHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testPrerequisite4 extends ROCAcceptanceTest {
	
	private static String path = null;
	final String dataLocation = "/src/main/resources/Data/Audit_Flow";
	final String fileName = "AuditFlow1_TestData.xlsx";
	final String sheetName = "AuditFlow1";
	
	public testPrerequisite4() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="/Create a query measure with join between ref_cdr_01 and usage_cdr_01", dependsOnGroups = { "prerequisite3" }, groups = { "prerequisite4" })
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
	
	@Test(priority=2, description="Create comparison measure on the query measures previously created", dependsOnMethods = { "createQueryMeasure" }, groups = { "prerequisite4" })
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
	
	@Test(priority=3, description="Creating a summary query measure taking the comparison measure as input measure", dependsOnMethods = { "createComparisonMeasure" }, groups = { "prerequisite4" })
	public void createSummaryQueryMeasure() throws Exception
	{
		try {
			QueryMeasureHelper queryMeasure = new QueryMeasureHelper();
			queryMeasure.createQueryMeasure(path, fileName, sheetName, "QueryMeasure", 2);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Creating a Data Match Measure on 4 usage tables", dependsOnMethods = { "createSummaryQueryMeasure" }, groups = { "prerequisite4" })
	public void createDataMatchMeasure()throws Exception
	{
		try {
			DataMatchMeasureHelper dataMatchMeasure = new DataMatchMeasureHelper();
			dataMatchMeasure.createDataMatchMeasure(path, fileName, sheetName, "DataMatchMeasure", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Attaching KPI to the summarized query measure", dependsOnMethods = { "createDataMatchMeasure" }, groups = { "prerequisite4" })
	public void createKPI()throws Exception
	{
		try {
			KPIDefinitionHelper kpiDefinition = new KPIDefinitionHelper();
			kpiDefinition.createKPIDefinition(path, fileName, sheetName, "KPIDefinition", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Creating an audit with all the measures created previously", dependsOnMethods = { "createKPI" }, groups = { "prerequisite4" })
	public void createAudit()throws Exception
	{
		try {
			AuditDefinitionHelper audits = new AuditDefinitionHelper();
			audits.createAuditDefinition(path, fileName, sheetName, "Audits", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=7, description="Creating an Audit Request for the Audit", dependsOnMethods = { "createAudit" }, groups = { "prerequisite4" })
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