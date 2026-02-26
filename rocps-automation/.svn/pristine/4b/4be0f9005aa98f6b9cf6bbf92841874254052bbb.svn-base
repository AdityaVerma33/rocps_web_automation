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

public class RoamingExprGroup extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> roamingExpGrpExcelMap = null;
	protected Map<String, String> roamingExpGrpMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected int colSize;
	protected String clientPartition;
	protected String colmHdrs;
	protected String groupNm;
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
	public RoamingExprGroup( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		roamingExpGrpExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( roamingExpGrpExcelMap );
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
	public RoamingExprGroup( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		roamingExpGrpExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( roamingExpGrpExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		groupNm = ExcelHolder.getKey( map, "GroupName" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'Roaming Expression Group' screen common method
	 */
	private void roamingExpGrpScreen() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "Roaming Expression Group" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		roamingExpGrpMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Group Name" );
	}

	/*
	 * This method is for 'Roaming Expression Group' screen column validation
	 */
	public void roamingExpGrpColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingExpGrpScreen();
				colmHdrs = ExcelHolder.getKey( roamingExpGrpMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String roamingExpGrpGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : roamingExpGrpGridColumnsArr )
					excelColumnNames.add( column );
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'Roaming Expression Group' Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Roaming Expression Group'  creation
	 */
	public void roamingExpGrpCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingExpGrpScreen();
				initializeVariable( roamingExpGrpMap );
				boolean isTariffTypePresent = GridHelper.isValuePresent( "SearchGrid", groupNm, "Group Name" );
				if ( !isTariffTypePresent )
				{
					clickNewAction( clientPartition );
					configureRoamingExpGrp();
					clickOnSave();
					Log4jHelper.logInfo( "'Roaming Expression Group' is successfully created with :- '" + groupNm );

				}
				else
					Log4jHelper.logInfo( "'Roaming Expression Group' is already avilable with :- '" + groupNm );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/* This method is used to
	 *  configure roaming expression group
	 */

	private void configureRoamingExpGrp() throws Exception
	{
		TextBoxHelper.type( "PS_Detail_roamExpGrp_name_txtId", groupNm );
	}

	/* This method is used to click on save button*/
	private void clickOnSave() throws Exception
	{
		psGenericHelper.detailSave( "PS_Detail_roamExpGrp_save_btnId", groupNm, "Group Name" );
	}

	/* This method is used to click on 'New' action */
	private void clickNewAction( String clientPartition ) throws Exception
	{
		ProductUtil.waitForParentActionElementTOBeclickable( "Common Tasks" );
		psGenericHelper.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( "PS_Detail_roamExpGrp_detailXpath", searchScreenWaitSec );
	}

	/* This method is used to click on  action'*/
	public void clickOnAction( String parentActionNm, String childActionNm ) throws Exception
	{
		PSGenericHelper.waitForParentActionElementTOBeclickable( parentActionNm );
		psGenericHelper.validateActionText( parentActionNm, childActionNm );
		NavigationHelper.navigateToAction( parentActionNm, childActionNm );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_roamExpGrp_detailXpath" ), searchScreenWaitSec );
	}

}
