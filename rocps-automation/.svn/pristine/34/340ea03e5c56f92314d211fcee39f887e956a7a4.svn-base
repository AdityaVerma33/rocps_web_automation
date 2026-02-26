package com.subex.rocps.automation.helpers.application.referenceTable;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class RoamingChargeItemMetricType extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> roamChgItemMetTypeExcelMap = null;
	protected Map<String, String> roamChgItemMetTypeMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected int colSize;
	protected String clientPartition;
	protected String colmHdrs;
	protected String tariffMetType;
	protected String chargeItem;
	protected int index;
	protected PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl psActionImpl = new PSActionImpl();

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public RoamingChargeItemMetricType( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		roamChgItemMetTypeExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( roamChgItemMetTypeExcelMap );
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
	public RoamingChargeItemMetricType( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		roamChgItemMetTypeExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( roamChgItemMetTypeExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		tariffMetType = ExcelHolder.getKey( map, "TariffMetricType" );
		chargeItem = ExcelHolder.getKey( map, "ChargedItem" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'roamChgItemMetType' screen common method
	 */
	private void roamChgItemMetTypeScreen() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "Roaming Charge Item Metric Type Map" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		roamChgItemMetTypeMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Charged Item" );
	}

	/*
	 * This method is for 'Roaming Charge Item Metric Type Map' screen column validation
	 */
	public void roamChgItemMetTypeColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamChgItemMetTypeScreen();
				colmHdrs = ExcelHolder.getKey( roamChgItemMetTypeMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Charged Item", colmHdrs, "Roaming Charge Item Metric Type Map" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Roaming Charge Item Metric Type Map'  creation
	 */
	public void roamChgItemMetTypeCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamChgItemMetTypeScreen();
				initializeVariable( roamChgItemMetTypeMap );
				boolean isroamChgItemMetTypePresent = psGenericHelper.isDataPresent( tariffMetType, "Tariff Metric Type" );
				if ( !isroamChgItemMetTypePresent )
				{
					psActionImpl.clickNewAction( clientPartition, "PS_Detail_roamChgItemMetTypeMap_detailXpath" );
					configureroamChgItemMetType();
					clickOnSave();
					Log4jHelper.logInfo( "'Roaming Charge Item Metric Type Map' is successfully created with  'Tariff Metric Type':- '" + tariffMetType );

				}
				else
					Log4jHelper.logInfo( "'Roaming Charge Item Metric Type Map' is already avilable with  'Tariff Metric Type':- '" + tariffMetType );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Roaming Charge Item Metric Type Map'  Edit
	 */
	public void roamChgItemMetTypeEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				roamChgItemMetTypeScreen();
				initializeVariable( roamChgItemMetTypeMap );
				boolean isroamChgItemMetTypePresent = psGenericHelper.isDataPresent( tariffMetType, "Tariff Metric Type" );
				if ( isroamChgItemMetTypePresent )
				{
					GridHelper.clickRow( "SearchGrid", tariffMetType, "Tariff Metric Type" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					psActionImpl.clickOnAction( "Common Tasks", "Edit", "PS_Detail_roamChgItemMetTypeMap_detailXpath" );
					modifyroamChgItemMetType();
					clickOnSave();
					Log4jHelper.logInfo( "'Roaming Charge Item Metric Type Map' is successfully updated with  'Tariff Metric Type':- '" + tariffMetType );

				}
				else

					Log4jHelper.logInfo( "'Roaming Charge Item Metric Type Map' is not avilable with  'Tariff Metric Type':- '" + tariffMetType );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/* This method is used 
	 * configure  Roaming Charge Item Metric Type Map
	 */
	private void configureroamChgItemMetType() throws Exception
	{

		ComboBoxHelper.select( "PS_Detail_roamChgItemMetTypeMap_tarifMetType_combId", tariffMetType );
		ComboBoxHelper.select( "PS_Detail_roamChgItemMetTypeMap_chgItem_comboId", chargeItem );
		

	}

	/* This method is used 
	 * modify  Roaming Charge Item Metric Type Map
	 */
	private void modifyroamChgItemMetType() throws Exception
	{
		assertEquals( ComboBoxHelper.getValue( "PS_Detail_roamChgItemMetTypeMap_tarifMetType_combId" ), tariffMetType, "'Roaming Charge Item Metric Type Map' -Tariff Metric Type is not matched" );
		ProductUtil.modifyComboBox( "PS_Detail_roamChgItemMetTypeMap_chgItem_comboId", chargeItem );

	}

	/* This method is used to click on save button*/
	private void clickOnSave() throws Exception
	{
		psGenericHelper.detailSave( "PS_Detail_roamChgItemMetTypeMap_save_btnId", tariffMetType, "Tariff Metric Type" );
	}
}
