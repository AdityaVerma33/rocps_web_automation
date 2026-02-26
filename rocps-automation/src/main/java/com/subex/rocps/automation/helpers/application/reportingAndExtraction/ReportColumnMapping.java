package com.subex.rocps.automation.helpers.application.reportingAndExtraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.repColMapping.ConfigRepColMapTableInstDetail;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.repColMapping.RepColumnMappingActImpl;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.repColMapping.RepColumnMappingDetailImpl;
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

public class ReportColumnMapping extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> reportColumnMappingExcelMap = null;
	protected Map<String, String> reportColumnMappingMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	protected String configName;
	protected String configTableInstTestCase;
	protected String clientPartition;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl psActionImpl = new PSActionImpl();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public ReportColumnMapping( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		reportColumnMappingExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( reportColumnMappingExcelMap );
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
	public ReportColumnMapping( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		reportColumnMappingExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( reportColumnMappingExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		configName = ExcelHolder.getKey( map, "ConfigurationName" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		configTableInstTestCase = ExcelHolder.getKey( map, "ConfigTableInstanceTestCase" );

	}

	/*
	 * This method is for 'Report Column Mapping' screen common method
	 */
	private void reportColumnMappingScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Report Column Mapping" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		reportColumnMappingMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Configuration Name" );
	}

	/*
	 * This method is for 'Report Column Mapping' screen column validation
	 */
	public void reportColumnMappingColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				reportColumnMappingScreen();
				colmHdrs = ExcelHolder.getKey( reportColumnMappingMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Configuration Name", colmHdrs, "Report Column Mapping" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Report Column Mapping' report creation
	 */
	public void reportColumnMappingCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				reportColumnMappingScreen();
				initializeVariable( reportColumnMappingMap );
				boolean isConfigNmPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_repColMapping_configNm_textID", configName, "Configuration Name" );
				if ( !isConfigNmPresent )
				{
					RepColumnMappingActImpl repColumnMappingActImpl = new RepColumnMappingActImpl();
					repColumnMappingActImpl.clickNewAction( clientPartition );
					RepColumnMappingDetailImpl repMappingDetailImpl = new RepColumnMappingDetailImpl( reportColumnMappingMap );
					repMappingDetailImpl.configRepColumMapping();
					configRepColTableInstance();
					clickOnSave();
					Log4jHelper.logInfo( "'Report Column Mapping' is successfully created with 'Configuration Name '" + configName );

				}
				else

					Log4jHelper.logInfo( "'Report Column Mapping' is already avilable with 'Configuration Name'" + configName );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Report Column Mapping' report edit
	 */
	public void reportColumnMappingEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				reportColumnMappingScreen();
				initializeVariable( reportColumnMappingMap );
				boolean isConfigNmPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_repColMapping_configNm_textID", configName, "Configuration Name" );
				if ( isConfigNmPresent )
				{
					RepColumnMappingActImpl repColumnMappingActImpl = new RepColumnMappingActImpl();
					GridHelper.clickRow( "SearchGrid", configName, "Configuration Name" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					repColumnMappingActImpl.clickOnAction( "Common Tasks", "Edit" );
					RepColumnMappingDetailImpl repMappingDetailImpl = new RepColumnMappingDetailImpl( reportColumnMappingMap );
					repMappingDetailImpl.modifyRepColumMapping();
					configRepColTableInstance();
					clickOnSave();
					Log4jHelper.logInfo( "'Report Column Mapping' is successfully updated with 'Configuration Name '" + configName );

				}
				else

					Log4jHelper.logInfo( "'Report Column Mapping' is not avilable with 'Configuration Name'" + configName );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for configure table instance
	 */
	private void configRepColTableInstance() throws Exception
	{
		if ( ValidationHelper.isNotEmpty( configTableInstTestCase ) )
		{
			List<Map<String, String>> listOfmap = psDataComponentHelper.getListOfTestCaseMap( path, workBookName, sheetName, configTableInstTestCase );
			for ( int i = 0; i < listOfmap.size(); i++ )
			{
				Map<String, String> map = listOfmap.get( i );
				ConfigRepColMapTableInstDetail colMapTableInstDetail = new ConfigRepColMapTableInstDetail( map );
				colMapTableInstDetail.configRepColTableInstance();
			}
		}
		else
			FailureHelper.failTest( "Configure Table Instance Test Case should not be empty" );
	}

	/*
	 * This method is for click on save button
	 */
	private void clickOnSave() throws Exception
	{
		psGenericHelper.detailSave( "PS_Detail_repColMapping_save_BtnId", configName, "Configuration Name" );
	}

}
