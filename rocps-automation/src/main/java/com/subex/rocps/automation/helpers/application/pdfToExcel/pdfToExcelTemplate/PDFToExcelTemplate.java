package com.subex.rocps.automation.helpers.application.pdfToExcel.pdfToExcelTemplate;

import java.util.ArrayList;
import java.util.Map;

import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class PDFToExcelTemplate extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> pdfExcelTemplateExcelMap = null;
	protected Map<String, String> pdfToExcelMap = null;
	protected Map<String, String> collectedFlSrchMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected int colSize;
	protected String clientPartition;
	protected String colmHdrs;
	protected int index;
	protected String pdfPath;
	protected String templateName;
	protected String summaryStartPage;
	protected String summaryEndPage;
	protected String skipPagesLast;
	protected String excelPath;
	protected String pdfFilename;
	protected String excelExtension;
	protected String sheetType;
	protected String footerLines;
	protected String columns;
	protected PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl actionObj = new PSActionImpl();
	PDFToExcelTemplateImpl implObj = new PDFToExcelTemplateImpl();

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public PDFToExcelTemplate( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		pdfExcelTemplateExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( pdfExcelTemplateExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/**Constructor : Initializing the excel with occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @param occurence
	 * @throws Exception 
	 */
	public PDFToExcelTemplate( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		pdfExcelTemplateExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( pdfExcelTemplateExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		templateName = ExcelHolder.getKey( map, "TemplateName" );
		summaryStartPage = ExcelHolder.getKey( map, "SummaryStartPage" );
		summaryEndPage = ExcelHolder.getKey( map, "SummaryEndPage" );
		skipPagesLast = ExcelHolder.getKey( map, "SkipPagesLast" );
		footerLines = ExcelHolder.getKey( map, "FooterLines" );
		excelPath = ExcelHolder.getKey( map, "ExcelPath" );
		pdfPath = ExcelHolder.getKey( map, "PDFPath" );
		pdfFilename = ExcelHolder.getKey( map, "PDFFilename" );
		excelExtension = ExcelHolder.getKey( map, "ExcelExtension" );
		sheetType = ExcelHolder.getKey( map, "SheetType" );
		columns = ExcelHolder.getKey( map, "Columns" );

	}

	/*
	 * This method is for 'Template'  creation
	 */
	public void templateCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				NavigationHelper.navigateToScreen( "PDF to Excel Template" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				pdfToExcelMap = excelHolderObj.dataMap( index );
				initializeVariable( pdfToExcelMap );
				boolean isTemplatePresent = psGenericHelper.isDataPresent( templateName, "Template Name" );
				if ( !isTemplatePresent )
				{

					NavigationHelper.navigateToNew( clientPartition );
					implObj.headerDetails( templateName, summaryStartPage, summaryEndPage, footerLines, skipPagesLast, excelPath, pdfPath, pdfFilename, excelExtension );
					implObj.sheetAndColumns( sheetType, columns );
					implObj.clickConvertButton();
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					implObj.clickOnSave();
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					implObj.validateTemplateCreation( templateName );
					Log4jHelper.logInfo( "'PDF to Excel Template' is successfully created" );

				}
				else

					Log4jHelper.logInfo( "'PDF to Excel Template' is already avilable " );

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Template'  Edit
	 */
	public void templateEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				NavigationHelper.navigateToScreen( "PDF to Excel Template" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				pdfToExcelMap = excelHolderObj.dataMap( index );
				initializeVariable( pdfToExcelMap );
				boolean isTemplatePresent = psGenericHelper.isDataPresent( templateName, "Template Name" );
				if ( isTemplatePresent )
				{
					GridHelper.clickRow( "SearchGrid", templateName, "Template Name" );
					actionObj.clickOnAction( "Common Tasks", "Edit" );
					implObj.headerDetails( templateName, summaryStartPage, summaryEndPage, footerLines, skipPagesLast, excelPath, pdfPath, pdfFilename, excelExtension );
					implObj.sheetAndColumns( sheetType, columns );
					implObj.clickConvertButton();
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					implObj.clickOnSave();
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					implObj.validateTemplateCreation( templateName );
					Log4jHelper.logInfo( "'PDF to Excel Template' is Edited" );

				}
				else

					Log4jHelper.logInfo( "'PDF to Excel Template' is not available " );

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Template'  Edit
	 */
	public void templateDelete() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				NavigationHelper.navigateToScreen( "PDF to Excel Template" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				pdfToExcelMap = excelHolderObj.dataMap( index );
				initializeVariable( pdfToExcelMap );
				boolean isTemplatePresent = psGenericHelper.isDataPresent( templateName, "Template Name" );
				if ( isTemplatePresent )
				{
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					psGenericHelper.clickDeleteOrUnDeleteAction( templateName, "Template Name", "Delete" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
					isTemplatePresent = psGenericHelper.isDataPresent( templateName, "Template Name" );
					assertTrue( isTemplatePresent, templateName + " is not present" );
					Log4jHelper.logInfo( "Ip Measurement Configuration is deleted successfully with the value-:'" + templateName );
				}

			}
		}

		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}
	
	/*
	 * This method is for 'Template'  Edit
	 */
	public void templateUndelete() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				NavigationHelper.navigateToScreen( "PDF to Excel Template" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				pdfToExcelMap = excelHolderObj.dataMap( index );
				initializeVariable( pdfToExcelMap );
				psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isTemplatePresent = psGenericHelper.isDataPresent( templateName, "Template Name" );
				
				if ( isTemplatePresent )
				{
					
					psGenericHelper.clickDeleteOrUnDeleteAction( templateName, "Template Name", "Undelete" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					psGenericHelper.selectPartitionFilter( clientPartition, "Non Deleted Items" );
					isTemplatePresent = psGenericHelper.isDataPresent(  templateName, "Template Name" );
					assertTrue( isTemplatePresent, templateName + " is not present" );
					Log4jHelper.logInfo( "Ip Measurement Configuration is undeleted successfully with the  value:  '" + templateName );
				}

			}
		}

		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}
	
	/*
	 * This method is for 'File Upload' screen column validation
	 */
	public void templateColumnValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				NavigationHelper.navigateToScreen( "PDF TO Excel Template" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				pdfToExcelMap = excelHolderObj.dataMap( index );
				ButtonHelper.click( "SearchButton" );
				colmHdrs = ExcelHolder.getKey( pdfToExcelMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Template Name", colmHdrs, "PDF TO Excel Template" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

}
