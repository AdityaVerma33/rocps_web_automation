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

public class CallTypeGroup extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> callTypeGrpExcelMap = null;
	protected Map<String, String> callTypeGrpMap = null;
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
	protected String callTypeLevel1;
	protected String callTypeLevel2;
	protected String callTypeLevel3;
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
	public CallTypeGroup( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		callTypeGrpExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( callTypeGrpExcelMap );
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
	public CallTypeGroup( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		callTypeGrpExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( callTypeGrpExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		groupNm = ExcelHolder.getKey( map, "GroupName" );
		callTypeLevel1 = ExcelHolder.getKey( map, "CallTypeLevel1" );
		callTypeLevel2 = ExcelHolder.getKey( map, "CallTypeLevel2" );
		callTypeLevel3 = ExcelHolder.getKey( map, "CallTypeLevel3" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'Call Type Group' screen common method
	 */
	private void callTypeGrpScreen() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "Call Type Group" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		callTypeGrpMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Group Name" );
	}

	/*
	 * This method is for 'Call Type Group' screen column validation
	 */
	public void callTypeGrpColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				callTypeGrpScreen();
				colmHdrs = ExcelHolder.getKey( callTypeGrpMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String callTypeGrpGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : callTypeGrpGridColumnsArr )
					excelColumnNames.add( column );
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'Call Type Group' Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Call Type Group'  creation
	 */
	public void callTypeGrpCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				callTypeGrpScreen();
				initializeVariable( callTypeGrpMap );
				boolean isTariffTypePresent = GridHelper.isValuePresent( "SearchGrid", groupNm, "Group Name" );
				if ( !isTariffTypePresent )
				{
					clickNewAction( clientPartition );
					configureCallTypeGrp();
					clickOnSave();
					Log4jHelper.logInfo( "'Call Type Group' is successfully created with  Group Name:- '" + groupNm );

				}
				else

					Log4jHelper.logInfo( "'Call Type Group' is already avilable with  Group Name:- '" + groupNm );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Call Type Group'  edit
	 */
	public void callTypeGrpEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				callTypeGrpScreen();
				initializeVariable( callTypeGrpMap );
				boolean isTariffTypePresent = GridHelper.isValuePresent( "SearchGrid", groupNm, "Group Name" );
				if ( isTariffTypePresent )
				{
					GridHelper.clickRow( "SearchGrid", groupNm, "Group Name"  );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					clickOnAction( "Common Tasks", "Edit" );
					modifyCallTypeGrp();
					clickOnSave();
					Log4jHelper.logInfo( "'Call Type Group' is successfully updated with  Group Name:- '" + groupNm );

				}
				else

					Log4jHelper.logInfo( "'Call Type Group' is not avilable with  Group Name:- '" + groupNm );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}
	/* This method is used to
	 *  configure Call type group
	 */

	private void configureCallTypeGrp() throws Exception
	{
		TextBoxHelper.type( "PS_Detail_callTypeGrp_groupNm_txtId", groupNm );
		ComboBoxHelper.select( "PS_Detail_callTypeGrp_callTypeL1_comboId", callTypeLevel1 );
		ComboBoxHelper.select( "PS_Detail_callTypeGrp_callTypeL2_comboId", callTypeLevel2 );
		TextBoxHelper.type( "PS_Detail_callTypeGrp_callTypeL3_txtId", callTypeLevel3 );
	}

	/* This method is used to
	 *  modify Call type group
	 */

	private void modifyCallTypeGrp() throws Exception
	{
		assertEquals( TextBoxHelper.getValue( "PS_Detail_callTypeGrp_groupNm_txtId" ), groupNm, "Group Name is not matched" );
		ProductUtil.modifyComboBox( "PS_Detail_callTypeGrp_callTypeL1_comboId", callTypeLevel1 );
		ProductUtil.modifyComboBox( "PS_Detail_callTypeGrp_callTypeL2_comboId", callTypeLevel2 );
		ProductUtil.modifyTextBox( "PS_Detail_callTypeGrp_callTypeL3_txtId", callTypeLevel3 );
	}

	/* This method is used to click on save button*/
	private void clickOnSave() throws Exception
	{
		psGenericHelper.detailSaveWithRetry( "PS_Detail_callTypeGrp_save_btnId", groupNm, "Group Name" );
	}

	/* This method is used to click on 'New' action */
	private void clickNewAction( String clientPartition ) throws Exception
	{
		ProductUtil.waitForParentActionElementTOBeclickable( "Common Tasks" );
		psGenericHelper.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( "PS_Detail_callTypeGrp_detailXpath", searchScreenWaitSec );
	}

	/* This method is used to click on  action'*/
	public void clickOnAction( String parentActionNm, String childActionNm ) throws Exception
	{
		PSGenericHelper.waitForParentActionElementTOBeclickable( parentActionNm );
		psGenericHelper.validateActionText( parentActionNm, childActionNm );
		NavigationHelper.navigateToAction( parentActionNm, childActionNm );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_callTypeGrp_detailXpath" ), searchScreenWaitSec );
	}

}
