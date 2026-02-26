package com.subex.rocps.automation.helpers.application.bills;

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
import com.subex.rocps.automation.helpers.application.bills.billDataset.BillDatasetActImpl;
import com.subex.rocps.automation.helpers.application.bills.billDataset.BillDatasetDetailImpl;
import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.eventUsgReqScheduler.EventUsgReqSchedulerDetailImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class BillDatasetSearch extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> billDatasetExcelMap = null;
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
	protected String fileName;
	protected String tableInstance;
	protected String clientPartition;
	protected String constantDataType;
	protected String constantValue;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	BillDatasetActImpl billDatasetActImpl = new BillDatasetActImpl();

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
		ButtonHelper.click( "ClearButton" );
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
				boolean isBillDatasetPresent = psGenericHelper.isGridTextValuePresent( "PSBillDataset_Detail_BillDatasetNameID", datasetName, "Dataset Name" );
				if ( !isBillDatasetPresent )
				{
					billDatasetActImpl.clickNewAction( clientPartition );
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
			boolean isBillDatasetPresent = psGenericHelper.isGridTextValuePresent( "PSBillDataset_Detail_BillDatasetNameID", datasetName, "Dataset Name" );
			if ( isBillDatasetPresent )
			{
				psGenericHelper.clickDeleteOrUnDeleteAction( datasetName, "Dataset Name", "Delete" );
				psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
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
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			boolean isBillDatasetPresent = psGenericHelper.isGridTextValuePresent( "PSBillDataset_Detail_BillDatasetNameID", datasetName, "Dataset Name" );
			if ( isBillDatasetPresent )
			{
				psGenericHelper.clickDeleteOrUnDeleteAction( datasetName, "Dataset Name", "Undelete" );
				psGenericHelper.selectPartitionFilter( clientPartition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", datasetName, "Dataset Name" ), datasetName );
				Log4jHelper.logInfo( "Bill Dataset is Undeleted successfully with : " + datasetName );

			}
		}

	}
}
