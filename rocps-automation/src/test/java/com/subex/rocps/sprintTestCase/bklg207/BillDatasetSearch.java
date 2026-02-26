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

public class BillDatasetSearch extends PSAcceptanceTest
{

	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> billDatasetExcelMap = null;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	BillDatasetActImpl BillDatasetActImpl = new BillDatasetActImpl();
	protected DataSelectionHelper dataSelection = new DataSelectionHelper();
	protected Map<String, String> billDatasetMap = null;
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
	protected String datasetName;
	protected static String bookName;
	protected static String pathWB;
	protected static String sheetNm;
	String fileName;
	String tableInstance;
	String clientPartition;
	String constantDataType;
	String constantValue;

	/**
	 * Default constructor
	 */
	public BillDatasetSearch()
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
	public BillDatasetSearch( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		billDatasetExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( billDatasetExcelMap );
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
	public BillDatasetSearch( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		billDatasetExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( billDatasetExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		datasetName = ExcelHolder.getKey( map, "DatasetName" );

	}

	/*
	 * This method is for 'Bill Dataset' screen common method
	 */
	private void billDatasetScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Bill Dataset" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		billDatasetMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "PSBillDataset_Search_ClearButtonID" );
		psGenericHelper.waitforHeaderElement( "Dataset Name" );
	}

	/*
	 * This method is for 'Bill Dataset' screen column validation
	 */
	public void billDatasetColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				billDatasetScreen();
				colmHdrs = ExcelHolder.getKey( billDatasetMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Dataset Name", colmHdrs, "Bill Dataset" );

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}
	/*
	 * This method is to check Bill Dataset already present
	 */
	public boolean isBillDatasetPresent(String datasetName ) throws Exception {
		ElementHelper.click(or.getProperty("PSBillDataset_Detail_DatasetFilterID"));
        GenericHelper.waitForLoadmask();
		TextBoxHelper.type( "PSBillDataset_Detail_BillDatasetNameID", datasetName );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Dataset Name" );
		
		boolean isBillDatasetPresent= genericHelperObj.isDataPresent( datasetName, "Dataset Name" );
		return isBillDatasetPresent;
	}
	/*
	 * This method is for create new Bill Dataset.
	 */
	public void billDatasetCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				billDatasetScreen();
				initializeVariable( billDatasetMap );
				boolean isBillDatasetPresent=isBillDatasetPresent(datasetName );
			if ( !isBillDatasetPresent )
				{
					BillDatasetActImpl.clickNewAction( clientPartition );

					BillDatasetDetailImpl billDatasetDetailImpl = new BillDatasetDetailImpl( billDatasetMap );
					billDatasetDetailImpl.configBillDataset();
					psGenericHelper.detailSave( "billDatasetDetail.save", datasetName, "Dataset Name" );
					Log4jHelper.logInfo( "Bill Dataset is created successfully with : " + datasetName );
				}
				else
					Log4jHelper.logInfo( "Bill Dataset is already available with : " + datasetName );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is to Delete the created Bill Dataset.
	 */
	public void deleteBillDataset() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			billDatasetScreen();
			initializeVariable( billDatasetMap );
			boolean isBillDatasetPresent = genericHelperObj.isGridTextValuePresent( "PSBillDataset_Detail_BillDatasetNameID", datasetName, "Dataset Name" );
			if ( isBillDatasetPresent )
			{
				psGenericHelper.clickDeleteOrUnDeleteAction( datasetName, "Dataset Name", "Delete" );
				genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", datasetName, "Dataset Name" ), datasetName );

				Log4jHelper.logInfo( "Bill Dataset is deleted successfully with : " + datasetName );

			}
		}
	}
	/*
	 * This method is to UnDelete the created Bill Dataset.
	 */
	public void unDeleteBillDataset() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			billDatasetScreen();
			initializeVariable( billDatasetMap );
			genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
			boolean isBillDatasetPresent = genericHelperObj.isGridTextValuePresent( "PSBillDataset_Detail_BillDatasetNameID", datasetName, "Dataset Name" );
			if ( isBillDatasetPresent )
			{
				System.out.println( "entered if condition" );
				psGenericHelper.clickDeleteOrUnDeleteAction( datasetName, "Dataset Name", "Undelete" );
				genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", datasetName, "Dataset Name" ), datasetName );

				Log4jHelper.logInfo( "Bill Dataset is Undeleted successfully with : " + datasetName );

			}
		}

	

}}

