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
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TariffTypToCalTypGrpMapping extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> tariffTyToCalTyGrpExcelMap = null;
	protected Map<String, String> tariffTyToCalTyGrpMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected int colSize;
	protected String clientPartition;
	protected String colmHdrs;
	protected String tariffType;
	protected String callTypeGrp;
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
	public TariffTypToCalTypGrpMapping( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		tariffTyToCalTyGrpExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( tariffTyToCalTyGrpExcelMap );
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
	public TariffTypToCalTypGrpMapping( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		tariffTyToCalTyGrpExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( tariffTyToCalTyGrpExcelMap );
		colSize = excelHolderObj.totalColumns();

	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		tariffType = ExcelHolder.getKey( map, "TariffType" );
		callTypeGrp = ExcelHolder.getKey( map, "CallTypeGrp" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'Tariff Type to Call Type Group Mapping' screen common method
	 */
	private void tariffTyToCalTyGrpScreen() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "Tariff Type to Call Type Group Mapping" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		tariffTyToCalTyGrpMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Tariff Type" );
	}

	/*
	 * This method is for 'Tariff Type to Call Type Group Mapping' screen column validation
	 */
	public void tariffTyToCalTyGrpColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				tariffTyToCalTyGrpScreen();
				colmHdrs = ExcelHolder.getKey( tariffTyToCalTyGrpMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String tariffTyToCalTyGrpGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : tariffTyToCalTyGrpGridColumnsArr )
					excelColumnNames.add( column );
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'Tariff Type to Call Type Group Mapping' Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Tariff Type to Call Type Group Mapping'  creation
	 */
	public void tariffTyToCallTyGrpCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				tariffTyToCalTyGrpScreen();
				initializeVariable( tariffTyToCalTyGrpMap );
				boolean istariffTypePresent = isDataPresent( tariffType, "Tariff Type" );
				if ( !istariffTypePresent )
				{
					clickNewAction( clientPartition );
					configureTariffTyToCalTypGrp();
					clickOnSave();
					Log4jHelper.logInfo( "'Tariff Type to Call Type Group Mapping' is successfully created with  tariff type:- '" + tariffType );
				}
				else
					Log4jHelper.logInfo( "'Tariff Type to Call Type Group Mapping' is already avilable with  tariff type:- '" + tariffType );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Tariff Type to Call Type Group Mapping'  edit
	 */
	public void tariffTyToCallTyGrpEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				tariffTyToCalTyGrpScreen();
				initializeVariable( tariffTyToCalTyGrpMap );
				boolean istariffTypePresent = isDataPresent( tariffType, "Tariff Type" );
				if ( istariffTypePresent )
				{
					GridHelper.clickRow( "SearchGrid", tariffType, "Tariff Type" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					clickOnAction( "Common Tasks", "Edit" );
					modifyTariffTyToCalTypGrp();
					clickOnSave();
					Log4jHelper.logInfo( "'Tariff Type to Call Type Group Mapping' is successfully updated with  tariff type:- '" + tariffType );

				}
				else

					Log4jHelper.logInfo( "'Tariff Type to Call Type Group Mapping' is not avilable with  tariff type:- '" + tariffType );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/* This method is used  to check data present*/
	public boolean isDataPresent( String txtValue, String txtColumnHeader ) throws Exception
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
	 * configure Tariff Type to Call Type Group Mapping
	 */
	private void configureTariffTyToCalTypGrp() throws Exception
	{
		ComboBoxHelper.select( "PS_Detail_tarTyCalTyGrpMap_tariffType_comboId", tariffType );
		ComboBoxHelper.select( "PS_Detail_tarTyCalTyGrpMapp_callTypeGrp_comboId", callTypeGrp );
	}

	/* This method is used 
	 * modify Tariff Type to Call Type Group Mapping
	 */
	private void modifyTariffTyToCalTypGrp() throws Exception
	{
		assertEquals( ComboBoxHelper.getValue( "PS_Detail_tarTyCalTyGrpMap_tariffType_comboId" ), tariffType, "'Tariff Type' is not matched" );
		ProductUtil.modifyComboBox( "PS_Detail_tarTyCalTyGrpMapp_callTypeGrp_comboId", callTypeGrp );
	}

	/* This method is used to click on save button*/
	private void clickOnSave() throws Exception
	{
		psGenericHelper.detailSave( "PS_Detail_tarTyCalTyGrpMap_save_btnId", tariffType, "Tariff Type" );
	}

	/* This method is used to click on 'New' action */
	private void clickNewAction( String clientPartition ) throws Exception
	{
		ProductUtil.waitForParentActionElementTOBeclickable( "Common Tasks" );
		psGenericHelper.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( "PS_Detail_tarTyCalTyGrpMap_detailXpath", searchScreenWaitSec );
	}

	/* This method is used to click on  action'*/
	public void clickOnAction( String parentActionNm, String childActionNm ) throws Exception
	{
		PSGenericHelper.waitForParentActionElementTOBeclickable( parentActionNm );
		psGenericHelper.validateActionText( parentActionNm, childActionNm );
		NavigationHelper.navigateToAction( parentActionNm, childActionNm );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_tarTyCalTyGrpMap_detailXpath" ), searchScreenWaitSec );
	}

}
