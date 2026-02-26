package com.subex.rocps.automation.helpers.application.accruals;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.accruals.glCodeDefn.GLCodeDefnActionImpl;
import com.subex.rocps.automation.helpers.application.accruals.glCodeDefn.GLCodeDefnDetails;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class GLCodeDefn extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> glCodeDefnExcelMap = null;
	protected Map<String, String> glCodeDefnMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String clientPartition;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	protected String glCdDefnNm;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public GLCodeDefn( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		glCodeDefnExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( glCodeDefnExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/**Constructor :  Initializing the excel with occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @param occurence
	 * @throws Exception 
	 */
	public GLCodeDefn( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		glCodeDefnExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( glCodeDefnExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for 'GL Code Definition' screen common method
	 */
	private void glCodeDefnScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "GL Code Definition" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		glCodeDefnMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Name" );
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		glCdDefnNm = ExcelHolder.getKey( map, "Name" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'GL Code Definition' screen column validation
	 */
	public void glCodeDefnColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				glCodeDefnScreen();
				colmHdrs = ExcelHolder.getKey( glCodeDefnMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String glCodeDefnGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : glCodeDefnGridColumnsArr )
					excelColumnNames.add( column );
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'GL Code Definition' Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'GL Code Definition' report creation
	 */
	public void glCodeDefnCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				glCodeDefnScreen();
				initializeVariable( glCodeDefnMap );
				boolean isGlCodeDefnPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_glCodeDefn_name_textId", glCdDefnNm, "Name" );
				if ( !isGlCodeDefnPresent )
				{
					GLCodeDefnActionImpl glCodeDefnActionImpl = new GLCodeDefnActionImpl();
					glCodeDefnActionImpl.clickNewAction( clientPartition );
					GLCodeDefnDetails glCodeDefnDetails = new GLCodeDefnDetails( glCodeDefnMap );
					glCodeDefnDetails.createGlCdDefn();
					Log4jHelper.logInfo( "'GL Code Definition' is successfully created with name '" + glCdDefnNm );
				}
				else

					Log4jHelper.logInfo( "'GL Code Definition' is already avilable with name" + glCdDefnNm );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'GL Code Definition' report edit
	 */
	public void glCodeDefnEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				glCodeDefnScreen();
				initializeVariable( glCodeDefnMap );
				boolean isGlCodeDefnPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_glCodeDefn_name_textId", glCdDefnNm, "Name" );
				if ( isGlCodeDefnPresent )
				{

					GLCodeDefnActionImpl glCodeDefnActionImpl = new GLCodeDefnActionImpl();
					GLCodeDefnDetails glCodeDefnDetails = new GLCodeDefnDetails( glCodeDefnMap );
					int row = GridHelper.getRowNumber( "SearchGrid", glCdDefnNm, "Name" );
					GridHelper.clickRow( "SearchGrid", row, "Name" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					glCodeDefnActionImpl.clickOnAction( "Common Tasks", "Edit" );
					glCodeDefnDetails.modifyGlCdDefn();
					Log4jHelper.logInfo( "'GL Code Definition' is successfully updated with name '" + glCdDefnNm );
				}
				else

					Log4jHelper.logInfo( "'GL Code Definition' is not avilable with name" + glCdDefnNm );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}



}
