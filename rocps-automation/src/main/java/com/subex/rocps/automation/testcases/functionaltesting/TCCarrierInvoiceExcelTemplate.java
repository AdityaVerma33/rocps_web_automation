package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.carrierinvoice.CarrierInvoiceExcelTemplate;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCCarrierInvoiceExcelTemplate extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "CarrierInvoiceExcelTemplate";

	@org.testng.annotations.Test( priority = 1, description = "Carrier Invoice Template creation- Search screen column validation" )
	public void ciTemplateColVal() throws Exception
	{
		try
		{
			CarrierInvoiceExcelTemplate ciObj = new CarrierInvoiceExcelTemplate( path, workBookName, sheetName, "CI_SearchScreenColumnVal", 1 );
			ciObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Carrier Invoice Template creation- without SFL" )
	public void ciTemplate1CreationWithoutSFL() throws Exception
	{
		try
		{
			CarrierInvoiceExcelTemplate ciObj = new CarrierInvoiceExcelTemplate( path, workBookName, sheetName, "CI_ExcelTemplate1_withoutSFL", 1 );
			ciObj.carrierInvoiceExcelTemplateConfig();
			ciObj.approveCarrireInvoiceTemplate();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Carrier Invoice Template creation- without standard structure" )
	public void ciTemplate2CreationwithoutSDS() throws Exception
	{
		try
		{
			CarrierInvoiceExcelTemplate ciObj = new CarrierInvoiceExcelTemplate( path, workBookName, sheetName, "CI_ExcelTemplate1_SFL_withoutStdStrcture", 1 );
			ciObj.carrierInvoiceExcelTemplateConfig();
			ciObj.approveCarrireInvoiceTemplate();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "Carrier Invoice Template creation- UsageWithSummary_SFL Std datastructure Blended" )
	public void ciTemplate3CreationBlended() throws Exception
	{
		try
		{
			CarrierInvoiceExcelTemplate ciObj = new CarrierInvoiceExcelTemplate( path, workBookName, sheetName, "CI_ExcelTemplate2_SFLstdDS_blended", 1 );
			ciObj.carrierInvoiceExcelTemplateConfig();
			ciObj.approveCarrireInvoiceTemplate();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 5, description = "Carrier Invoice Template creation- UsageWithSummary_SFL Std datastructure",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class  )
	public void ciTemplate4CreationwithSFL() throws Exception
	{
		try
		{
			CarrierInvoiceExcelTemplate ciObj = new CarrierInvoiceExcelTemplate( path, workBookName, sheetName, "CI_ExcelTemplate4_withSFL", 1 );
			ciObj.carrierInvoiceExcelTemplateConfig();
			ciObj.approveCarrireInvoiceTemplate();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "Carrier Invoice Template creation- NonUsage" )
	public void ciTemplate5Creation() throws Exception
	{
		try
		{
			CarrierInvoiceExcelTemplate ciObj = new CarrierInvoiceExcelTemplate( path, workBookName, sheetName, "CI_ExcelTemplate3_NonUsgae", 1 );
			ciObj.carrierInvoiceExcelTemplateConfig();
			ciObj.approveCarrireInvoiceTemplate();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "Carrier Invoice Template creation- creditNote" )
	public void ciTemplate6Creation() throws Exception
	{
		try
		{
			CarrierInvoiceExcelTemplate ciObj = new CarrierInvoiceExcelTemplate( path, workBookName, sheetName, "CI_ExcelTemplate4_creditNote", 1 );
			ciObj.carrierInvoiceExcelTemplateConfig();
			
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "Carrier Invoice Template creation- Multiple usage tab" )
	public void ciTemplate7Creation() throws Exception
	{
		try
		{
			CarrierInvoiceExcelTemplate ciObj = new CarrierInvoiceExcelTemplate( path, workBookName, sheetName, "CI_ExcelTemplate5_usageMultiple", 1 );
			ciObj.carrierInvoiceExcelTemplateConfig();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "Carrier Invoice Template creation- AllFields" )
	public void ciTemplate8Creation() throws Exception
	{
		try
		{
			CarrierInvoiceExcelTemplate ciObj = new CarrierInvoiceExcelTemplate( path, workBookName, sheetName, "CI_ExcelTemplate6AllFileds", 1 );
			ciObj.carrierInvoiceExcelTemplateConfig();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, description = "Carrier Invoice Template creation- with Usage, non usage and creditnote" )
	public void ciTemplate9Creation() throws Exception
	{
		try
		{
			CarrierInvoiceExcelTemplate ciObj = new CarrierInvoiceExcelTemplate( path, workBookName, sheetName, "CI_ExcelTemplate7usgNonUsgCreditNote", 1 );
			ciObj.carrierInvoiceExcelTemplateConfig();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 11, description = "Carrier Invoice Template creation- without SFL-Default check" )
	public void ciTemplate1Creation() throws Exception
	{
		try
		{
			CarrierInvoiceExcelTemplate ciObj = new CarrierInvoiceExcelTemplate( path, workBookName, sheetName, "CI_ExcelTemplate9_Default", 1 );
			ciObj.carrierInvoiceExcelTemplateConfig();
			ciObj.approveCarrireInvoiceTemplate();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 12, description = "Carrier Invoice Template- Delete" )
	public void ciTemplateDelete() throws Exception
	{
		try
		{
			CarrierInvoiceExcelTemplate ciObj = new CarrierInvoiceExcelTemplate( path, workBookName, sheetName, "CI_Delete", 1 );
			ciObj.carrierInvoiceTempDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 13, description = "Carrier Invoice Template- UnDelete" )
	public void ciTemplateUnDelete() throws Exception
	{
		try
		{
			CarrierInvoiceExcelTemplate ciObj = new CarrierInvoiceExcelTemplate( path, workBookName, sheetName, "CI_UnDelete", 1 );
			ciObj.carrierInvoiceTemplateUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 14, description = "Carrier Invoice Template- Change Status- Approved" )
	public void ciTemplateChangeStatus() throws Exception
	{
		try
		{
			CarrierInvoiceExcelTemplate ciObj = new CarrierInvoiceExcelTemplate( path, workBookName, sheetName, "CI_ApproveAction", 1 );
			ciObj.approveCarrireInvoiceTemplate();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 15, description = "Carrier Invoice Template- View Output Tables",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class  )
	public void ciTemplateViewOutputTables() throws Exception
	{
		try
		{
			CarrierInvoiceExcelTemplate ciObj = new CarrierInvoiceExcelTemplate( path, workBookName, sheetName, "CI_ViewOutputTables", 1 );
			ciObj.carrireInvoiceTemplateViewOutputTables();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 16, description = "Carrier Invoice Template- Move to Draft Status" )
	public void ciTemplateMoveToDraft() throws Exception
	{
		try
		{
			CarrierInvoiceExcelTemplate ciObj = new CarrierInvoiceExcelTemplate( path, workBookName, sheetName, "CI_MoveTODrafts", 1 );
			ciObj.carrireInvoiceTemplateMoveToDraft();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 17, description = "Carrier Invoice Template- Discontinue" )
	public void ciTemplateDiscontinue() throws Exception
	{
		try
		{
			CarrierInvoiceExcelTemplate ciObj = new CarrierInvoiceExcelTemplate( path, workBookName, sheetName, "CI_Discontinue", 1 );
			ciObj.approveCarrireInvoiceTemplate();
			ciObj.discontinueCarrireInvoiceTemplate();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	
}
