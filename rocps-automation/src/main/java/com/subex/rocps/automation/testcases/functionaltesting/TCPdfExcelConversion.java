package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.pdfToExcel.pdfToExcelConversion.PDFToExcelConversion;
import com.subex.rocps.automation.helpers.application.pdfToExcel.pdfToExcelTemplate.PDFToExcelTemplate;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCPdfExcelConversion extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "PDFtoExcelConversion";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = "PDF to Excel Conversion Column Validation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void pdfExcelPrerequisite() throws Exception
	{

		try
		{

			PDFToExcelTemplate pdfObj = new PDFToExcelTemplate( path, workBookName, "PDFtoExcelTemplate", "TemplateCreationXlsx");
			pdfObj.templateCreation();
			PDFToExcelTemplate pdfObj1 = new PDFToExcelTemplate( path, workBookName, "PDFtoExcelTemplate", "TemplateCreationXls");
			pdfObj1.templateCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, enabled = true, description = "PDF to Excel Conversion Column Validation", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void PdfExcelConversionColumnValidation() throws Exception
	{
		String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
		String workBookName = "FunctionalTestCases.xlsx";
		String sheetName = "PDFtoExcelConversion";
		try
		{

			PDFToExcelConversion pdfObj = new PDFToExcelConversion( path, workBookName, sheetName, "ConversionScreencolVal" );
			pdfObj.conversionColumnValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, enabled = true, description = "PDF to Excel Conversion Xlsx", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void PdfExcelConversionXlsx() throws Exception
	{
		String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
		String workBookName = "FunctionalTestCases.xlsx";
		String sheetName = "PDFtoExcelConversion";
		try
		{

			PDFToExcelConversion convObj = new PDFToExcelConversion( path, workBookName, sheetName, "ConversionXlsx" );
			convObj.conversionCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, enabled = true, description = "PDF to Excel Conversion Xls", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void PdfExcelConversionXls() throws Exception
	{
		String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
		String workBookName = "FunctionalTestCases.xlsx";
		String sheetName = "PDFtoExcelConversion";
		try
		{

			PDFToExcelConversion convObj = new PDFToExcelConversion( path, workBookName, sheetName, "ConversionXls" );
			convObj.conversionCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
