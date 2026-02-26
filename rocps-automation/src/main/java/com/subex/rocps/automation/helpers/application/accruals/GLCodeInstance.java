package com.subex.rocps.automation.helpers.application.accruals;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.accruals.glCodeDefn.GLCodeDefnActionImpl;
import com.subex.rocps.automation.helpers.application.accruals.glCodeDefn.GLCodeDefnDetails;
import com.subex.rocps.automation.helpers.application.accruals.glCodeInstance.GLCodeInstActionImpl;
import com.subex.rocps.automation.helpers.application.accruals.glCodeInstance.GLCodeInstanceDetail;
import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class GLCodeInstance extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> glCodeInstExcelMap = null;
	protected Map<String, String> glCodeInstMap = null;
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
	protected String glCdDfnNm;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public GLCodeInstance( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		glCodeInstExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( glCodeInstExcelMap );
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
	public GLCodeInstance( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		glCodeInstExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( glCodeInstExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		glCdDfnNm = ExcelHolder.getKey( map, "GlCdDefnName" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'GL Code Instance' screen common method
	 */
	private void glCodeInstanceScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "GL Code Instance" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		glCodeInstMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "GL Code" );
	}

	/*
	 * This method is for 'GL Code Instance' screen column validation
	 */
	public void glCdInstanceColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				glCodeInstanceScreen();
				colmHdrs = ExcelHolder.getKey( glCodeInstMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String glCodeInstGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : glCodeInstGridColumnsArr )
					excelColumnNames.add( column );
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'GL Code Instance' Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'GL Code Instance' report creation
	 */
	public void glCodeInstanceCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				glCodeInstanceScreen();
				initializeVariable( glCodeInstMap );
				boolean isGLCodeInstPresent = isGLCodeInstPresent( glCdDfnNm );
				if ( !isGLCodeInstPresent )
				{
					GLCodeInstActionImpl glCodeInstActionImpl = new GLCodeInstActionImpl();
					glCodeInstActionImpl.clickNewAction( clientPartition );
					GLCodeInstanceDetail glCodeInstanceDetail = new GLCodeInstanceDetail( glCodeInstMap );
					glCodeInstanceDetail.createGlCodeInstance();

					Log4jHelper.logInfo( "'GL Code Instance' is successfully created for '" + glCdDfnNm );
				}
				else

					Log4jHelper.logInfo( "'GL Code Instance' is already avilable  for " + glCdDfnNm );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'GL Code Instance' report edit
	 */
	public void glCodeInstanceEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				glCodeInstanceScreen();
				initializeVariable( glCodeInstMap );
				boolean isGLCodeInstPresent = isGLCodeInstPresent( glCdDfnNm );
				if ( isGLCodeInstPresent )
				{
					GLCodeInstActionImpl glCodeInstActionImpl = new GLCodeInstActionImpl();
					GLCodeInstanceDetail glCodeInstanceDetail = new GLCodeInstanceDetail( glCodeInstMap );
					int row = GridHelper.getRowNumber( "SearchGrid", glCdDfnNm, "GL Code Definition" );
					GridHelper.clickRow( "SearchGrid", row, "GL Code Definition" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					glCodeInstActionImpl.clickOnAction( "Common Tasks", "Edit" );
					glCodeInstanceDetail.modifyGlCodeInstance();
					Log4jHelper.logInfo( "'GL Code Instance' is successfully updated for '" + glCdDfnNm );
				}
				else

					Log4jHelper.logInfo( "'GL Code Instance' is not avilable for " + glCdDfnNm );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	// Method: for 'GL Code Instance' deletion action
	public void glCodeInstDelete() throws Exception
	{
		glCodeInstanceScreen();
		initializeVariable( glCodeInstMap );
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		boolean isGLCodeInstPresent = isGLCodeInstPresent( glCdDfnNm );
		assertTrue( isGLCodeInstPresent, "GL Code Instance' is not available in the screen for : -'" + glCdDfnNm );
		psGenericHelper.clickDeleteOrUnDeleteAction( glCdDfnNm, "GL Code Definition", "Delete" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
		isGLCodeInstPresent = isGLCodeInstPresent( glCdDfnNm );
		assertTrue( isGLCodeInstPresent, glCdDfnNm + " is not present" );
		Log4jHelper.logInfo( "'GL Code Instance' is deleted successfully for-:'" + glCdDfnNm );

	}

	// Method: for 'GL Code Instance' Undeletion action
	public void glCodeInstUnDelete() throws Exception
	{

		glCodeInstanceScreen();
		initializeVariable( glCodeInstMap );
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
		boolean isGLCodeInstPresent = isGLCodeInstPresent( glCdDfnNm );
		assertTrue( isGLCodeInstPresent, "'GL Code Instance' is not available in the screen for: -'" + glCdDfnNm );
		psGenericHelper.clickDeleteOrUnDeleteAction( glCdDfnNm, "GL Code Definition", "Undelete" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.selectPartitionFilter( clientPartition, "Non Deleted Items" );
		isGLCodeInstPresent = isGLCodeInstPresent( glCdDfnNm );
		assertTrue( isGLCodeInstPresent, glCdDfnNm + " is not present" );
		Log4jHelper.logInfo( "'GL Code Instance' is undeleted successfully for:  '" + glCdDfnNm );

	}

	/*
	 * This method is to check 'GL Code Instance' present 
	 */
	private boolean isGLCodeInstPresent( String glCodeDefnNm ) throws Exception
	{
		boolean isGlCdInstPresent = false;
		PSSearchGridHelper.gridFilterSearchWithComboBox( "PS_Detail_glCodeInst_glCdDfn_comboId", glCodeDefnNm, "GL Code Definition" );
		isGlCdInstPresent = GridHelper.isValuePresent( "SearchGrid", glCodeDefnNm, "GL Code Definition" );
		return isGlCdInstPresent;

	}

}
