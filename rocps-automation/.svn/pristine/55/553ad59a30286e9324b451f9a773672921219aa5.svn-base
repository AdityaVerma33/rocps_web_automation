package com.subex.rocps.automation.helpers.application.referenceTable;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class RoamingSerContextMapping extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> roamSerContMappingExcelMap = null;
	protected Map<String, String> roamSerContMappingMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected int colSize;
	protected String clientPartition;
	protected String colmHdrs;
	protected String context;
	protected String service;
	protected String matchExpGrp;
	protected String orderNo;

	protected int index;
	protected PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public RoamingSerContextMapping( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		roamSerContMappingExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( roamSerContMappingExcelMap );
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
	public RoamingSerContextMapping( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		roamSerContMappingExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( roamSerContMappingExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		context = ExcelHolder.getKey( map, "Context" );
		service = ExcelHolder.getKey( map, "Service" );
		matchExpGrp = ExcelHolder.getKey( map, "MatchExpGrp" );
		orderNo = ExcelHolder.getKey( map, "OrderNo" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'Roaming Service Context Mapping' screen common method
	 */
	private void roamServContMapScreen() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "Roaming Service Context Mapping" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		roamSerContMappingMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Service" );
	}

	/*
	 * This method is for 'Roaming Service Context Mapping' screen column validation
	 */
	public void roamServContMapColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamServContMapScreen();
				colmHdrs = ExcelHolder.getKey( roamSerContMappingMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String roamServContMapGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : roamServContMapGridColumnsArr )
					excelColumnNames.add( column );
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'Roaming Service Context Mapping' Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Roaming Service Context Mapping'  creation
	 */
	public void RoamingServContMappingCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamServContMapScreen();
				initializeVariable( roamSerContMappingMap );
				boolean isMatchExpGrpPresent = isDataPresent( matchExpGrp, "Match Expression Group" );

				if ( isMatchExpGrpPresent )
					Log4jHelper.logInfo( "'Roaming Service Context Mapping' is already avilable with  match expression group:- '" + matchExpGrp );
				else
				{
					boolean isServicePresent = isRoamingServicePresent( service, "Service" );
					boolean isOrderNoPresent = isDataPresent( orderNo, "Order No" );
					if ( isOrderNoPresent )
						Log4jHelper.logInfo( "'Roaming Service Context Mapping' is already avilable with order No:- '" + orderNo );
					else if ( !isServicePresent && !isOrderNoPresent )
					{
						clickNewAction( clientPartition );
						configureRoamingServContMapping();
						clickOnSave();
						Log4jHelper.logInfo( "'Roaming Service Context Mapping' is successfully created with  service:- '" + service );
					}
					else
						Log4jHelper.logInfo( "'Roaming Service Context Mapping' is already avilable with  service:- '" + service );
				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/* This method is used 
	 * check 'Roaming Service Context Mapping'  present with service
	 */
	private boolean isRoamingServicePresent( String txtValue, String txtColumnHeader ) throws Exception
	{
		ComboBoxHelper.select( "PS_Detail_roamServContMap_ContextFilter_comboId", context );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Service" );
		return isDataPresent( txtValue, txtColumnHeader );

	}

	/* This method is used  to check data present*/
	private boolean isDataPresent( String txtValue, String txtColumnHeader ) throws Exception
	{
		int row = GridHelper.getRowCount( "SearchGrid" );
		boolean rowVal = false;
		if ( row > 0 )
		{
			for ( int i = 0; i < row; i++ )
			{
				String cellValue = GridHelper.getCellValue( "SearchGrid", i + 1, txtColumnHeader );
				GenericHelper.waitForLoadmask();
				rowVal = cellValue.contains( txtValue );
				if ( rowVal )
					return true;
			}
			return rowVal;
		}
		return false;
	}

	/* This method is used 
	 * configure 'Roaming Service Context Mapping'
	 */
	private void configureRoamingServContMapping() throws Exception
	{
		ComboBoxHelper.select( "PS_Detail_roamServContMap_Context_comboId", context );
		ComboBoxHelper.select( "PS_Detail_roamServContMap_Service_comboId", service );
		ComboBoxHelper.select( "PS_Detail_roamServContMap_matchExpGrp_comboId", matchExpGrp );
		TextBoxHelper.type( "PS_Detail_roamServContMap_orderNo_txtId", orderNo );

	}

	/* This method is used to click on save button*/
	private void clickOnSave() throws Exception
	{
		psGenericHelper.detailSaveWithRetry( "PS_Detail_roamServContMap_btnId", service, "Service" );
	}

	/* This method is used to click on 'New' action */
	private void clickNewAction( String clientPartition ) throws Exception
	{
		ProductUtil.waitForParentActionElementTOBeclickable( "Common Tasks" );
		psGenericHelper.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( "PS_Detail_roamServContMap_detailXpath", searchScreenWaitSec );
	}

	/* This method is used to click on  action'*/
	public void clickOnAction( String parentActionNm, String childActionNm ) throws Exception
	{
		PSGenericHelper.waitForParentActionElementTOBeclickable( parentActionNm );
		psGenericHelper.validateActionText( parentActionNm, childActionNm );
		NavigationHelper.navigateToAction( parentActionNm, childActionNm );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_roamServContMap_detailXpath" ), searchScreenWaitSec );
	}
}
