package com.subex.rocps.sprintTestCase.bklg207;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TestExec extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "PSSprintFunctional_ExcelData.xlsx";
	String sheetName = "BKLG207_BillParameter";
	
	@org.testng.annotations.Test( priority = 3)
	public void newConstantBillParameterConf() throws Exception
	{
		System.out.println( "entered New Method" );
		String testCaseName = "BillParameterCreation_ConstantSoucreType";
		BillParameterSearch billPar = new BillParameterSearch( path, workBookName, sheetName, testCaseName );
		billPar.billParameterCreation();
	}

	@org.testng.annotations.Test( priority = 2 )
	public void billParameterColumnValidation() throws Exception
	{
		String testCaseName = "BillParameterScreenColumns";
		BillParameterSearch billPar = new BillParameterSearch( path, workBookName, sheetName, testCaseName );
		billPar.billParameterColumsValidation();
	}

	@org.testng.annotations.Test( priority = 1 )
	public void newTableBillParameterConf() throws Exception
	{
		System.out.println( "entered New Method" );
		String testCaseName = "BillParameterCreation_TableSoucreType";
		BillParameterSearch billPar = new BillParameterSearch( path, workBookName, sheetName, testCaseName );
		billPar.billParameterCreation();
	}

	

	@org.testng.annotations.Test( priority = 4 )
	public void deleteBillParameterConf() throws Exception
	{
		System.out.println( "entered New Method" );
		String testCaseName = "BillParameterDeletion";
		BillParameterSearch billPar = new BillParameterSearch( path, workBookName, sheetName, testCaseName );
		billPar.deleteBillParameter();
	}

	@org.testng.annotations.Test( priority = 5 )
	public void unDeleteBillParameterConf() throws Exception
	{
		System.out.println( "entered New Method" );
		String testCaseName = "BillParameterUnDeletion";
		BillParameterSearch billPar = new BillParameterSearch( path, workBookName, sheetName, testCaseName );
		billPar.unDeleteBillParameter();
	}
}
