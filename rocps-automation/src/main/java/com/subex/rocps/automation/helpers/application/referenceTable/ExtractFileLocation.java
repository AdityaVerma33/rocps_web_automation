package com.subex.rocps.automation.helpers.application.referenceTable;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class ExtractFileLocation extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> extractFlLocationExcel = null;
	protected Map<String, String> extractFlLocationMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected int colSize;
	protected String clientPartition;
	protected String name;
	protected String directory;
	protected int index;
	protected PSGenericHelper psGenericHelper = new PSGenericHelper();

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public ExtractFileLocation( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		extractFlLocationExcel = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( extractFlLocationExcel );
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
	public ExtractFileLocation( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		extractFlLocationExcel = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( extractFlLocationExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		name = ExcelHolder.getKey( map, "Name" );
		directory = ExcelHolder.getKey( map, "Directory" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'Extract File Location' screen common method
	 */
	private void extractFlLocationScreen() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "Extract File Location" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		extractFlLocationMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Name" );
	}

	/*
	 * This method is for 'Extract File Location' report creation
	 */
	public void extractFlLocationCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				extractFlLocationScreen();
				initializeVariable( extractFlLocationMap );
				boolean isExtFlPresent = isExtractFlPresent( name );
				if ( !isExtFlPresent )
				{
					clickNewAction( clientPartition );
					configExtFlLocation();
					Log4jHelper.logInfo( "'Extract File Location' is successfully created with name '" + name );

				}
				else

					Log4jHelper.logInfo( "'Extract File Location' is already avilable with name" + name );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/* This method is used to configure extract file location*/
	private void configExtFlLocation() throws Exception
	{
		TextBoxHelper.type( "PS_Detail_extFlLocation_name_textID", name );
		PropertyGridHelper.typeInDataDir( "Directory *", directory, ";" );
		psGenericHelper.detailSave( "PS_Detail_extFlLocation_save_btnId", name, "Name" );
	}

	/* This method is used to click on 'New' action in Extract File Location*/
	private void clickNewAction( String clientPartition ) throws Exception
	{
		ProductUtil.waitForParentActionElementTOBeclickable( "Common Tasks" );
		psGenericHelper.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( "PS_Detail_extFlLocation_detail_xpath", searchScreenWaitSec );
	}

	/* This method is used to check extract file present*/
	private boolean isExtractFlPresent( String name ) throws Exception
	{
		SearchGridHelper.searchWithTextBox( "PS_Detail_extFlLocation_name_textID", name, "Name" );
		int row = GridHelper.getRowCount( "SearchGrid" );
		if ( row > 0 )
			return true;
		return false;
	}

}
