package com.subex.rocps.automation.helpers.application.pdfToExcel.pdfToExcelConversion;

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
import com.subex.rocps.automation.utils.PSFileValidationHelper;
import com.subex.rocps.automation.utils.PSStringUtils;

public class PDFToExcelConversion extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> pdfExcelConversionExcelMap = null;
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
	protected int index;
	protected String name;
	protected String colmHdrs;
	protected String template;
	protected String pdfFile;
	protected String pdfPath;
	protected String exportFolder;
	protected String extension;
	PSFileValidationHelper fileObj = new PSFileValidationHelper();
	protected PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl actionObj = new PSActionImpl();

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public PDFToExcelConversion( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		pdfExcelConversionExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( pdfExcelConversionExcelMap );
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
	public PDFToExcelConversion( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		pdfExcelConversionExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( pdfExcelConversionExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		name = ExcelHolder.getKey( map, "Name" );
		template = ExcelHolder.getKey( map, "Template" );
		pdfFile = ExcelHolder.getKey( map, "PdfFile" );
		pdfPath = ExcelHolder.getKey( map, "PdfPath" );
		exportFolder = ExcelHolder.getKey( map, "ExportFolder" );
		extension = ExcelHolder.getKey( map, "Extension" );
	}

	/*
	 * This method is for 'Template'  creation
	 */
	public void conversionCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				NavigationHelper.navigateToScreen( "PDF to Excel Conversion" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				pdfToExcelMap = excelHolderObj.dataMap( index );
				initializeVariable( pdfToExcelMap );
				boolean isConvPresent = psGenericHelper.isDataPresent( name, "Name" );
				if ( !isConvPresent )
				{
					PDFToExcelConversionImpl obj = new PDFToExcelConversionImpl();
					NavigationHelper.navigateToNew( clientPartition );
					obj.headerDetails( name, template, pdfFile, pdfPath );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					obj.clickOnSave();
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					obj.validateConversion( name );
					fileObj.validatePartialFileNameInDir( exportFolder, (pdfFile.substring( 0, pdfFile.length() - 4 )), extension );
					Log4jHelper.logInfo( "'PDF to Excel Conversion' is successfully created" );

				}
				else

					Log4jHelper.logInfo( "'PDF to Excel Conversion' is already avilable " );

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
	public void conversionColumnValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				NavigationHelper.navigateToScreen( "PDF TO Excel Conversion" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				pdfToExcelMap = excelHolderObj.dataMap( index );
				ButtonHelper.click( "SearchButton" );
				colmHdrs = ExcelHolder.getKey( pdfToExcelMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Name", colmHdrs, "PDF TO Excel Conversion" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}




}
