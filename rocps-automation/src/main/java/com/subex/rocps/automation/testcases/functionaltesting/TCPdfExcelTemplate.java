package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.pdfToExcel.pdfToExcelConversion.PDFToExcelConversion;
import com.subex.rocps.automation.helpers.application.pdfToExcel.pdfToExcelTemplate.PDFToExcelTemplate;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCPdfExcelTemplate extends PSAcceptanceTest
{
	@org.testng.annotations.Test( priority = 1, enabled = true, description = "PDF to Excel Template Column Validation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void PdfExcelTemplateColumnValidation() throws Exception
	{
		String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
		String workBookName = "FunctionalTestCases.xlsx";
		String sheetName = "PDFtoExcelTemplate";
		try
		{

			PDFToExcelTemplate pdfObj = new PDFToExcelTemplate( path, workBookName, sheetName, "TemplateScreencolVal");
			pdfObj.templateColumnValidation();
		

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 2, enabled = true, description = "PDF to Excel Template Creation for Xlsx", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void PdfExcelTemplateXlsx() throws Exception
	{
		String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
		String workBookName = "FunctionalTestCases.xlsx";
		String sheetName = "PDFtoExcelTemplate";
		try
		{

			PDFToExcelTemplate pdfObj = new PDFToExcelTemplate( path, workBookName, sheetName, "TemplateCreationXlsx");
			pdfObj.templateCreation();
		

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 3, enabled = true, description = "PDF to Excel Template Creation for Xls", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void PdfExcelTemplateXls() throws Exception
	{
		String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
		String workBookName = "FunctionalTestCases.xlsx";
		String sheetName = "PDFtoExcelTemplate";
		try
		{

			PDFToExcelTemplate pdfObj = new PDFToExcelTemplate( path, workBookName, sheetName, "TemplateCreationXls");
			pdfObj.templateCreation();
		

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 4, enabled = true, description = "PDF to Excel Template Deletion for Xlsx", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void PdfExcelTemplateDeleteXlsx() throws Exception
	{
		String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
		String workBookName = "FunctionalTestCases.xlsx";
		String sheetName = "PDFtoExcelTemplate";
		try
		{

			PDFToExcelTemplate pdfObj = new PDFToExcelTemplate( path, workBookName, sheetName, "TemplateDeletionXlsx");
			pdfObj.templateDelete();
		

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 5, enabled = true, description = "PDF to Excel Template Deletion for Xls", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void PdfExcelTemplateDeleteXls() throws Exception
	{
		String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
		String workBookName = "FunctionalTestCases.xlsx";
		String sheetName = "PDFtoExcelTemplate";
		try
		{

			PDFToExcelTemplate pdfObj = new PDFToExcelTemplate( path, workBookName, sheetName, "TemplateDeletionXls");
			pdfObj.templateDelete();
		

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 6, enabled = true, description = "PDF to Excel Template Undelete for Xlsx", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void PdfExcelTemplateUndeleteXlsx() throws Exception
	{
		String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
		String workBookName = "FunctionalTestCases.xlsx";
		String sheetName = "PDFtoExcelTemplate";
		try
		{

			PDFToExcelTemplate pdfObj = new PDFToExcelTemplate( path, workBookName, sheetName, "TemplateUndeletionXlsx");
			pdfObj.templateUndelete();
		

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@org.testng.annotations.Test( priority = 7, enabled = true, description = "PDF to Excel Template Undelete for Xls", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void PdfExcelTemplateUndeleteXls() throws Exception
	{
		String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
		String workBookName = "FunctionalTestCases.xlsx";
		String sheetName = "PDFtoExcelTemplate";
		try
		{

			PDFToExcelTemplate pdfObj = new PDFToExcelTemplate( path, workBookName, sheetName, "TemplateUndeletionXls");
			pdfObj.templateUndelete();
		

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
