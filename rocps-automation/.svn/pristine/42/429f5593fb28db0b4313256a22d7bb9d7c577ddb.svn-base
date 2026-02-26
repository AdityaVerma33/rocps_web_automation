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

public class RoamingTaxation extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> roamingTaxationExcel = null;
	protected Map<String, String> roamingTaxationMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected int colSize;
	protected String clientPartition;
	protected String colmHdrs;
	protected String taxType;
	protected String tax;
	protected String chargeType;
	protected String taxIndicator;
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
	public RoamingTaxation( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		roamingTaxationExcel = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( roamingTaxationExcel );
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
	public RoamingTaxation( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		roamingTaxationExcel = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( roamingTaxationExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		taxType = ExcelHolder.getKey( map, "TaxType" );
		tax = ExcelHolder.getKey( map, "Tax" );
		chargeType = ExcelHolder.getKey( map, "ChargeType" );
		taxIndicator = ExcelHolder.getKey( map, "TaxIndicator" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'Roaming Taxation' screen common method
	 */
	private void roamingTaxationScreen() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "Roaming Taxation" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		roamingTaxationMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Tax Type" );
	}

	/*
	 * This method is for 'Roaming Taxation' screen column validation
	 */
	public void roamingTaxationColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingTaxationScreen();
				colmHdrs = ExcelHolder.getKey( roamingTaxationMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String roamingTaxationGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : roamingTaxationGridColumnsArr )
					excelColumnNames.add( column );
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'Roaming Taxation' Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Roaming Taxation'  creation
	 */
	public void roamingTaxationCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingTaxationScreen();
				initializeVariable( roamingTaxationMap );
				boolean isTaxTypePresent = GridHelper.isValuePresent( "SearchGrid", taxType, "Tax Type" );
				if ( !isTaxTypePresent )
				{
					clickNewAction( clientPartition );
					configureRoamingTaxation();
					clickOnSave();
					Log4jHelper.logInfo( "'Roaming Taxation' is successfully created with tax type:- '" + taxType );

				}
				else

					Log4jHelper.logInfo( "'Roaming Taxation' is already avilable with tax type:- '" + taxType );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Roaming Taxation'  creation
	 */
	public void roamingTaxationEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamingTaxationScreen();
				initializeVariable( roamingTaxationMap );
				boolean isTaxTypePresent = GridHelper.isValuePresent( "SearchGrid", taxType, "Tax Type" );
				if ( isTaxTypePresent )
				{
					GridHelper.clickRow( "SearchGrid", taxType, "Tax Type" );
					clickOnAction( "Common Tasks", "Edit" );
					modifyRoamingTaxation();
					clickOnSave();
					Log4jHelper.logInfo( "'Roaming Taxation' is successfully updated with tax type '" + taxType );

				}
				else

					Log4jHelper.logInfo( "'Roaming Taxation' is not avilable with tax type: -" + taxType );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/* This method is used to configure Roaming Taxation*/
	private void configureRoamingTaxation() throws Exception
	{
		ComboBoxHelper.select( "PS_Detail_roamingTaxation_taxType_ComboId", taxType );
		ComboBoxHelper.select( "PS_Detail_roamingTaxation_tax_ComboId", tax );
		ComboBoxHelper.select( "PS_Detail_roamingTaxation_chargeType_ComboId", chargeType );
		ComboBoxHelper.select( "PS_Detail_roamingTaxation_taxIndicator_ComboId", taxIndicator );
	}

	/* This method is used to modify Roaming Taxation*/
	private void modifyRoamingTaxation() throws Exception
	{
		assertEquals( ComboBoxHelper.getValue( "PS_Detail_roamingTaxation_taxType_ComboId" ), taxType, "Tax Type is not matched" );
		ProductUtil.modifyComboBox( "PS_Detail_roamingTaxation_tax_ComboId", tax );
		ProductUtil.modifyComboBox( "PS_Detail_roamingTaxation_chargeType_ComboId", chargeType );
		ProductUtil.modifyComboBox( "PS_Detail_roamingTaxation_taxIndicator_ComboId", taxIndicator );
	}

	/* This method is used to click on save button*/
	private void clickOnSave() throws Exception
	{
		psGenericHelper.detailSave( "PS_Detail_roamingTaxation_saveBtnId", taxType, "Tax Type" );
	}

	/* This method is used to click on 'New' action */
	private void clickNewAction( String clientPartition ) throws Exception
	{
		ProductUtil.waitForParentActionElementTOBeclickable( "Common Tasks" );
		psGenericHelper.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( "PS_Detail_roamingTaxation_detailXpath", searchScreenWaitSec );
	}

	/* This method is used to click on  action'*/
	public void clickOnAction( String parentActionNm, String childActionNm ) throws Exception
	{
		PSGenericHelper.waitForParentActionElementTOBeclickable( parentActionNm );
		psGenericHelper.validateActionText( parentActionNm, childActionNm );
		NavigationHelper.navigateToAction( parentActionNm, childActionNm );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_roamingTaxation_detailXpath" ), searchScreenWaitSec );
	}

}
