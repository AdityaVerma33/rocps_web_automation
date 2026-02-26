//package com.subex.rocps.automation.testcases.functionaltesting;
//
//import com.subex.rocps.automation.helpers.application.admin.Entities;
//import com.subex.rocps.automation.helpers.application.referenceTable.LineOfBusiness;
//import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
//
//import com.subex.automation.helpers.application.screens.EntitiesHelper;
//import com.subex.automation.helpers.util.FailureHelper;
//
//public class TCLineOfBusiness extends PSAcceptanceTest
//{
//	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
//	String workBookName = "FunctionalTestCases.xlsx";
//	String sheetName = "LineOfBusiness";
//
//	@org.testng.annotations.Test( priority = 1, description = "Line of Business creation" )
//	public void lineOfBusinessCreation() throws Exception
//	{
//		try
//		{
//			LineOfBusiness accobj = new LineOfBusiness( path, workBookName, sheetName, "LineOfBusinessCreation", 1 );
//			accobj.lineOfBusinessCreation();			
//			
//		}
//		catch ( Exception e )
//		{
//			FailureHelper.reportFailure( e );
//			throw e;
//		}
//	}
//	
//	@org.testng.annotations.Test( priority = 2, description = "Line of Business search screen column validation" )
//	public void lineOfBusinessColVal() throws Exception
//	{
//		try
//		{
//			LineOfBusiness accobj = new LineOfBusiness( path, workBookName, sheetName, "LineOfBusinessColVal", 1 );
//			accobj.searchScreenColumnsValidation();			
//			
//		}
//		catch ( Exception e )
//		{
//			FailureHelper.reportFailure( e );
//			throw e;
//		}
//	}
//	
//	@org.testng.annotations.Test( priority = 3, description = "Line of Business Delete" )
//	public void lineOfBusinessDelete() throws Exception
//	{
//		try
//		{
//			LineOfBusiness accobj = new LineOfBusiness( path, workBookName, sheetName, "LineOfBusinessDelete", 1 );
//			accobj.lineOfBusinessDelete();			
//			
//		}
//		catch ( Exception e )
//		{
//			FailureHelper.reportFailure( e );
//			throw e;
//		}
//	}
//	
//	@org.testng.annotations.Test( priority = 4, description = "Line of Business UnDelete" )
//	public void lineOfBusinessUnDelete() throws Exception
//	{
//		try
//		{
//			LineOfBusiness accobj = new LineOfBusiness( path, workBookName, sheetName, "LineOfBusinessUnDelete", 1 );
//			accobj.lineOfBusinessUnDelete();			
//			
//		}
//		catch ( Exception e )
//		{
//			FailureHelper.reportFailure( e );
//			throw e;
//		}
//	}
//	
//}
