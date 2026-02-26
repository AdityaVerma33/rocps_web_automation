package com.subex.rocps.automation.helpers.application.referenceTable;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class TariffTypeToChargeTypeMap extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> tariffTypeToChargeTypeExcelMap = null;
	protected Map<String, String> tariffTypeToChargeTypeMap = null;
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
	protected String chargeType;
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
	public TariffTypeToChargeTypeMap( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		tariffTypeToChargeTypeExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( tariffTypeToChargeTypeExcelMap );
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
	public TariffTypeToChargeTypeMap( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		tariffTypeToChargeTypeExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( tariffTypeToChargeTypeExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		tariffType = ExcelHolder.getKey( map, "TariffType" );
		chargeType = ExcelHolder.getKey( map, "ChargeType" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'tariffTypeToChargeType' screen common method
	 */
	private void tariffTypeToChargeTypeScreen() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "Tariff Type To Charge Type Map" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		tariffTypeToChargeTypeMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Charge Type" );
	}

	/*
	 * This method is for 'Tariff Type To Charge Type Map' screen column validation
	 */
	public void tariffTypeToChargeTypeMapColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				tariffTypeToChargeTypeScreen();
				colmHdrs = ExcelHolder.getKey( tariffTypeToChargeTypeMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Charge Type", colmHdrs, "Tariff Type To Charge Type Map" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Tariff Type To Charge Type Map'  creation
	 */
	public void tariffTypeToChargeTypeMapCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				tariffTypeToChargeTypeScreen();
				initializeVariable( tariffTypeToChargeTypeMap );
				boolean istariffTypeToChargeTypePresent = psGenericHelper.isDataPresent( tariffType, "Tariff Type" );
				if ( !istariffTypeToChargeTypePresent )
				{
					psActionImpl.clickNewAction( clientPartition, "PS_Detail_TariffTyTOChgTyMap_detailXpath" );
					configuretariffTypeToChargeType();
					clickOnSave();
					Log4jHelper.logInfo( "'Tariff Type To Charge Type Map' is successfully created with  'Tariff  Type':- '" + tariffType );

				}
				else
					Log4jHelper.logInfo( "'Tariff Type To Charge Type Map' is already avilable with  'Tariff  Type':- '" + tariffType );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Tariff Type To Charge Type Map'  Edit
	 */
	public void tariffTypeToChargeTypeMapEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				tariffTypeToChargeTypeScreen();
				initializeVariable( tariffTypeToChargeTypeMap );
				boolean istariffTypeToChargeTypePresent = psGenericHelper.isDataPresent( tariffType, "Tariff Type" );
				if ( istariffTypeToChargeTypePresent )
				{
					GridHelper.clickRow( "SearchGrid", tariffType, "Tariff Type" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					psActionImpl.clickOnAction( "Common Tasks", "Edit", "PS_Detail_TariffTyTOChgTyMap_detailXpath" );
					modifytariffTypeToChargeType();
					clickOnSave();
					Log4jHelper.logInfo( "'Tariff Type To Charge Type Map' is successfully updated with  'Tariff Type':- '" + tariffType );

				}
				else

					Log4jHelper.logInfo( "'Tariff Type To Charge Type Map' is not avilable with  'Tariff Type':- '" + tariffType );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/* This method is used 
	 * configure  Tariff Type To Charge Type Map
	 */
	private void configuretariffTypeToChargeType() throws Exception
	{

		ComboBoxHelper.select( "PS_Detail_TariffTyTOChgTyMap_tariffType_combId", tariffType );
		ComboBoxHelper.select( "PS_Detail_TariffTyTOChgTyMap_chargeType_combId", chargeType );

	}

	/* This method is used 
	 * modify  Tariff Type To Charge Type Map
	 */
	private void modifytariffTypeToChargeType() throws Exception
	{
		assertEquals( ComboBoxHelper.getValue( "PS_Detail_TariffTyTOChgTyMap_tariffType_combId" ), tariffType, "'Tariff Type To Charge Type Map' -Tariff  Type is not matched" );
		ProductUtil.modifyComboBox( "PS_Detail_TariffTyTOChgTyMap_chargeType_combId", chargeType );

	}

	/* This method is used to click on save button*/
	private void clickOnSave() throws Exception
	{
		psGenericHelper.detailSave( "PS_Detail_TariffTyTOChgTyMap_save_BtnId", tariffType, "Tariff Type" );
	}
}
