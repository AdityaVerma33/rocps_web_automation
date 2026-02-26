package com.subex.rocps.automation.helpers.application.arms;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class NetworkExclusion extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> networkExclusionExcelMap = null;
	protected Map<String, String> networkExclusionMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	protected String networkExclusionName;
	protected String clientPartition;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl psActionImpl = new PSActionImpl();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();

	/**
	 * Constructor : Initializing the excel without occurrence
	 * 
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception
	 */
	public NetworkExclusion( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		networkExclusionExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( networkExclusionExcelMap );
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
	public NetworkExclusion( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		networkExclusionExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( networkExclusionExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		networkExclusionName = ExcelHolder.getKey( map, "Name" );

	}

	/*
	 * This method is for 'Network Exclusion' screen common method
	 */
	private void networkExclusionScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Network Exclusion" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		networkExclusionMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Name" );
	}

	/*
	 * This method is for 'Network Exclusion' screen column validation
	 */
	public void networkExclusionColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				networkExclusionScreen();
				colmHdrs = ExcelHolder.getKey( networkExclusionMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Name", colmHdrs, "Network Exclusion" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}
}
