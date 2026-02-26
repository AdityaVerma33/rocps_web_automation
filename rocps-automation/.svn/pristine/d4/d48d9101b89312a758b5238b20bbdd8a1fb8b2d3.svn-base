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

public class BandTypToCalTypGrpMaping extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> bandTyToCallTyGrpExcelMap = null;
	protected Map<String, String> bandTyToCallTyGrpMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected int colSize;
	protected String clientPartition;
	protected String colmHdrs;
	protected String bandType;
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
	public BandTypToCalTypGrpMaping( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		bandTyToCallTyGrpExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( bandTyToCallTyGrpExcelMap );
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
	public BandTypToCalTypGrpMaping( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		bandTyToCallTyGrpExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( bandTyToCallTyGrpExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		bandType = ExcelHolder.getKey( map, "BandType" );
		callTypeGrp = ExcelHolder.getKey( map, "CallTypeGrp" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'Band Type to Call Type Group Mapping' screen common method
	 */
	private void bandTyToCallTyGrpScreen() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "Band Type to Call Type Group Mapping" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		bandTyToCallTyGrpMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Band Type" );
	}

	/*
	 * This method is for 'Band Type to Call Type Group Mapping' screen column validation
	 */
	public void bandTyToCallTyGrpColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				bandTyToCallTyGrpScreen();
				colmHdrs = ExcelHolder.getKey( bandTyToCallTyGrpMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String bandTyToCallTyGrpGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : bandTyToCallTyGrpGridColumnsArr )
					excelColumnNames.add( column );
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'Band Type to Call Type Group Mapping' Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Band Type to Call Type Group Mapping'  creation
	 */
	public void bandTyToCallTyGrpCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				bandTyToCallTyGrpScreen();
				initializeVariable( bandTyToCallTyGrpMap );
				boolean isbandTypePresent = isDataPresent( bandType, "Band Type" );
				if ( !isbandTypePresent )
				{
					clickNewAction( clientPartition );
					configureBandTyToCalTypGrp();
					clickOnSave();
					Log4jHelper.logInfo( "'Band Type to Call Type Group Mapping' is successfully created with  band type:- '" + bandType );
				}
				else
					Log4jHelper.logInfo( "'Band Type to Call Type Group Mapping' is already avilable with  band type:- '" + bandType );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Band Type to Call Type Group Mapping'  edit
	 */
	public void bandTyToCallTyGrpEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				bandTyToCallTyGrpScreen();
				initializeVariable( bandTyToCallTyGrpMap );
				boolean isbandTypePresent = isDataPresent( bandType, "Band Type" );
				if ( isbandTypePresent )
				{
					GridHelper.clickRow( "SearchGrid", bandType, "Band Type" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					clickOnAction( "Common Tasks", "Edit" );
					modifyBandTyToCalTypGrp();
					clickOnSave();
					Log4jHelper.logInfo( "'Band Type to Call Type Group Mapping' is successfully updated with  band type:- '" + bandType );

				}
				else

					Log4jHelper.logInfo( "'Band Type to Call Type Group Mapping' is not avilable with  value:- '" + bandType );
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
	 * configure Band Type to Call Type Group Mapping
	 */
	private void configureBandTyToCalTypGrp() throws Exception
	{
		ComboBoxHelper.select( "PS_Detail_bandTyCalTyGrpMap_bandType_comboId", bandType );
		ComboBoxHelper.select( "PS_Detail_bandTyCalTyGrpMap_callTypeGrp_comboId", callTypeGrp );
	}

	/* This method is used 
	 * modify Band Type to Call Type Group Mapping
	 */
	private void modifyBandTyToCalTypGrp() throws Exception
	{
		assertEquals( ComboBoxHelper.getValue( "PS_Detail_bandTyCalTyGrpMap_bandType_comboId" ), bandType, "'Band Type' is not matched" );
		ProductUtil.modifyComboBox( "PS_Detail_bandTyCalTyGrpMap_callTypeGrp_comboId", callTypeGrp );
	}

	/* This method is used to click on save button*/
	private void clickOnSave() throws Exception
	{
		psGenericHelper.detailSave( "PS_Detail_bandTyCalTyGrpMap_save_btnId", bandType, "Band Type" );
	}

	/* This method is used to click on 'New' action */
	private void clickNewAction( String clientPartition ) throws Exception
	{
		ProductUtil.waitForParentActionElementTOBeclickable( "Common Tasks" );
		psGenericHelper.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( "PS_Detail_bandTyCalTyGrpMap_detailXpath", searchScreenWaitSec );
	}

	/* This method is used to click on  action'*/
	public void clickOnAction( String parentActionNm, String childActionNm ) throws Exception
	{
		PSGenericHelper.waitForParentActionElementTOBeclickable( parentActionNm );
		psGenericHelper.validateActionText( parentActionNm, childActionNm );
		NavigationHelper.navigateToAction( parentActionNm, childActionNm );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_bandTyCalTyGrpMap_detailXpath" ), searchScreenWaitSec );
	}

}
