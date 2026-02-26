package com.subex.rocps.sprintTestCase.bklg207;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class BillReportTestExec extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "PSSprintFunctional_ExcelData.xlsx";
	String sheetName = "BKLG207_BillReportConfiguration";
	@org.testng.annotations.Test( priority = 2 )
	public void billParameterColumnValidation() throws Exception
	{
		String testCaseName = "BillReportConfScreenColumns";
		BillReportConfigurationHelper billReport = new BillReportConfigurationHelper( path, workBookName, sheetName, testCaseName );
		billReport.billReportConfColumsValidation();
	}
	
	@org.testng.annotations.Test( priority = 1 )
	public void newTableBillParameterConf() throws Exception
	{
		System.out.println( "entered New Method" );
		String testCaseName = "BillReportConfCreatation";
		BillReportConfigurationHelper billReport = new BillReportConfigurationHelper( path, workBookName, sheetName, testCaseName );
		billReport.billReportConfCreation();
	}



}
