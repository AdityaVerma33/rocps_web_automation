package com.subex.rocps.sprintTestCase.bklg207;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class BillReportConfigurationHelper extends PSAcceptanceTest
{
	
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> billReportConfExcelMap = null;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	BillReportConfigActImpl BillReportConfigActImpl = new BillReportConfigActImpl();
	protected DataSelectionHelper dataSelection = new DataSelectionHelper();
	protected Map<String, String> billReportConfMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int colSize;
	protected int paramVal;
	protected int occurence;
	protected int index;
	protected String colmHdrs;
	protected String configureName;
	protected String clientPartition;
	
	
	/**
	 * Default constructor
	 */
	public BillReportConfigurationHelper()
	{

	}

	/**
	 * Constructor : Initializing the excel without occurrence
	 * 
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception
	 */
	public BillReportConfigurationHelper( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		billReportConfExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( billReportConfExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/**
	 * Constructor : Initializing the excel with occurrence
	 * 
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @param occurence
	 * @throws Exception
	 */
	public BillReportConfigurationHelper( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		billReportConfExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( billReportConfExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		configureName = ExcelHolder.getKey( map, "ConfigName" );

	}

	/*
	 * This method is for 'Bill Report Configuration' screen common method
	 */
	private void billReportConfScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Bill Report Configuration");
		GenericHelper.waitForLoadmask( 100 );
		billReportConfMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Name" );
	}

	/*
	 * This method is for 'Bill Report Configuration' screen column validation
	 */
	public void billReportConfColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				billReportConfScreen();
				colmHdrs = ExcelHolder.getKey( billReportConfMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Configuration Name", colmHdrs, "Bill Report Configu..." );

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}
	/*
	 * This method is to check 'Bill Report Configuration' already present
	 */
	public boolean isBillReportConfPresent(String configureName ) throws Exception {
		TextBoxHelper.type( "PSDetail_Bill_Report_Conf_Name_TextId", configureName );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Configuration Name" );
		
		boolean isBillReportConfPresent= genericHelperObj.isDataPresent( configureName, "Configuration Name" );
		return isBillReportConfPresent;
	}
	/*
	 * This method is for create new 'Bill Report Configuration'.
	 */
	public void billReportConfCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				billReportConfScreen();
				initializeVariable( billReportConfMap );
				boolean isBillReportConfPresent=isBillReportConfPresent(configureName );
			if ( !isBillReportConfPresent )
				{
					BillReportConfigActImpl.clickNewAction( clientPartition );

					BillReprtConfiguredetail billReprtConfiguredetail = new BillReprtConfiguredetail( billReportConfMap );
					billReprtConfiguredetail.configBillReportConf();
					psGenericHelper.detailSave( "PSDetail_Bill_Report_Conf_save_Xpath", configureName, "Parameter Name" );
					Log4jHelper.logInfo( "Bill Report configuration is created successfully with : " + configureName );
				}
				else
					Log4jHelper.logInfo( "Bill Report configuration is already available with : " + configureName );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}




}
