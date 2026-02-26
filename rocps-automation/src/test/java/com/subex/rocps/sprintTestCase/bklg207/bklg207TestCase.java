package com.subex.rocps.sprintTestCase.bklg207;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class bklg207TestCase extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "PSSprintFunctional_ExcelData.xlsx";
	String sheetName = "BKLG207_BillDatasetScreen";

	@org.testng.annotations.Test( priority = 3 )
	public void newConstantBillDatasetConf() throws Exception
	{
		System.out.println( "entered New Method" );
		String testCaseName = "BillDatasetCreation_ConstantSoucreType";
		BillDatasetSearch billDs = new BillDatasetSearch( path, workBookName, sheetName, testCaseName );
		billDs.billDatasetCreation();
	}
	
		@org.testng.annotations.Test( priority = 2 )
		public void billDatasetColumnValidation() throws Exception
		{
			String testCaseName = "BillDatasetScreenColumns";
			BillDatasetSearch billDs = new BillDatasetSearch( path, workBookName, sheetName, testCaseName );
			billDs.billDatasetColumsValidation();
		}
		
		@org.testng.annotations.Test( priority = 1 )
		public void newTableBillDatasetConf() throws Exception
		{
			System.out.println( "entered New Method" );
			String testCaseName = "BillDatasetCreation_TableSoucreType";
			BillDatasetSearch billDs = new BillDatasetSearch( path, workBookName, sheetName, testCaseName );
			billDs.billDatasetCreation();
		}

	@org.testng.annotations.Test( priority = 4 )
	public void deleteBillDatasetConf() throws Exception
	{
		System.out.println( "entered New Method" );
		String testCaseName = "BillDatasetDeletion";
		BillDatasetSearch billDs = new BillDatasetSearch( path, workBookName, sheetName, testCaseName );
		billDs.deleteBillDataset();
	}

		@org.testng.annotations.Test( priority = 5 )
		public void unDeleteBillDatasetConf() throws Exception
		{
			System.out.println( "entered New Method" );
			String testCaseName = "BillDatasetUnDeletion";
			BillDatasetSearch billDs = new BillDatasetSearch( path, workBookName, sheetName, testCaseName );
			billDs.unDeleteBillDataset();
		}
		
}