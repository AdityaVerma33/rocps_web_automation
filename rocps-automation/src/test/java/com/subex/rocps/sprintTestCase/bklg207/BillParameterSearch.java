package com.subex.rocps.sprintTestCase.bklg207;

import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.testng.Assert;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.eventUsgReqScheduler.EventUsgReqSchedulerDetailImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class BillParameterSearch extends PSAcceptanceTest
{

	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> billParameterExcelMap = null;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	BillParameterActImpl BillParameterActImpl = new BillParameterActImpl();
	protected DataSelectionHelper dataSelection = new DataSelectionHelper();
	protected Map<String, String> billParameterMap = null;
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
	protected String parameterName;
	protected static String bookName;
	protected static String pathWB;
	protected static String sheetNm;
	String sourceType;
	String tableInstance;
	String clientPartition;
	String constantDataType;
	String constantValue;

	/**
	 * Default constructor
	 */
	public BillParameterSearch()
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
	public BillParameterSearch( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		billParameterExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( billParameterExcelMap );
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
	public BillParameterSearch( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		billParameterExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( billParameterExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		parameterName = ExcelHolder.getKey( map, "ParameterName" );

	}

	/*
	 * This method is for 'Bill Parameter' screen common method
	 */
	private void billParameterScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Bill Parameter" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		billParameterMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Parameter Name" );
	}

	/*
	 * This method is for 'Bill Parameter' screen column validation
	 */
	public void billParameterColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				billParameterScreen();
				colmHdrs = ExcelHolder.getKey( billParameterMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Parameter Name", colmHdrs, "Bill Parameter" );

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}
	/*
	 * This method is to check Bill Parameter already present
	 */
	public boolean isBillParameterPresent(String parameterName ) throws Exception {
		TextBoxHelper.type( "PSDetail_Bill_Parameter_BillParameterName_textId", parameterName );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Parameter Name" );
		
		boolean isBillParameterPresent= genericHelperObj.isDataPresent( parameterName, "Parameter Name" );
		return isBillParameterPresent;
	}
	/*
	 * This method is for create new Bill Parameter.
	 */
	public void billParameterCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				billParameterScreen();
				initializeVariable( billParameterMap );
				boolean isBillParameterPresent=isBillParameterPresent(parameterName );
			if ( !isBillParameterPresent )
				{
					BillParameterActImpl.clickNewAction( clientPartition );

					BillParameterDetailImpl billParameterDetailImpl = new BillParameterDetailImpl( billParameterMap );
					billParameterDetailImpl.configBillParameter();
					psGenericHelper.detailSave( "PSDetail_Bill_Parameter_save_Xpath", parameterName, "Parameter Name" );
					Log4jHelper.logInfo( "Bill Parameter is created successfully with : " + parameterName );
				}
				else
					Log4jHelper.logInfo( "Bill Parameter is already available with : " + parameterName );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is for delete Bill Parameter.
	 */

	public void deleteBillParameter() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			billParameterScreen();
			initializeVariable( billParameterMap );
			TextBoxHelper.type( "PSDetail_Bill_Parameter_BillParameterName_textId", parameterName );
			ButtonHelper.click( "search" );
			boolean isBillParameterPresent = genericHelperObj.isDataPresent( parameterName, "Parameter Name" );
			if ( isBillParameterPresent )
			{
				psGenericHelper.clickDeleteOrUnDeleteAction( parameterName, "Parameter Name", "Delete" );
				genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", parameterName, "Parameter Name" ), parameterName );

				Log4jHelper.logInfo( "Bill Parameter is deleted successfully with : " + parameterName );

			}
		}
	}

	/*
	 * This method is for undelete Bill Parameter.
	 */
	public void unDeleteBillParameter() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			billParameterScreen();
			initializeVariable( billParameterMap );
			genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
			TextBoxHelper.type( "PSDetail_Bill_Parameter_BillParameterName_textId", parameterName );
			ButtonHelper.click( "search" );
			boolean isBillParameterPresent = genericHelperObj.isDataPresent( parameterName, "Parameter Name" );
			if ( isBillParameterPresent )
			{
				System.out.println( "entered if condition" );
				psGenericHelper.clickDeleteOrUnDeleteAction( parameterName, "Parameter Name", "Undelete" );
				genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", parameterName, "Parameter Name" ), parameterName );

				Log4jHelper.logInfo( "Bill Parameter is Undeleted successfully with : " + parameterName );

			}
		}
	}

	


}
