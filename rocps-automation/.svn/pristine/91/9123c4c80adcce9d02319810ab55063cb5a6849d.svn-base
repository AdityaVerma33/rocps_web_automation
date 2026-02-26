package com.subex.rocps.automation.helpers.application.referenceTable;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;


public class SystemTariffMapping extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> systemTariffMappingExcelMap = null;
	protected Map<String, String> systemTariffMappingMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected int colSize;
	protected String clientPartition;
	protected String colmHdrs;
	protected String elementSet;
	protected String tariffType;
	protected int index;
	protected PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl psActionImpl = new PSActionImpl();
PSDataComponentHelper psDataComponentHelper=new PSDataComponentHelper();
	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public SystemTariffMapping( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		systemTariffMappingExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( systemTariffMappingExcelMap );
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
	public SystemTariffMapping( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		systemTariffMappingExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( systemTariffMappingExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		elementSet = ExcelHolder.getKey( map, "ElementSet" );
		tariffType = ExcelHolder.getKey( map, "TariffType" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'systemTariffMapping' screen common method
	 */
	private void systemTariffMappingScreen() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "System Tariff Mapping" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		systemTariffMappingMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "Element Set" );
	}

	/*
	 * This method is for 'System Tariff Mapping' screen column validation
	 */
	public void systemTariffMappingColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				systemTariffMappingScreen();
				colmHdrs = ExcelHolder.getKey( systemTariffMappingMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Element Set", colmHdrs, "System Tariff Mapping" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'System Tariff Mapping'  creation
	 */
	public void systemTariffMappingCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				systemTariffMappingScreen();
				initializeVariable( systemTariffMappingMap );
				boolean issystemTariffMappingPresent = issystemTariffMappingPresent(elementSet,tariffType);
				if ( !issystemTariffMappingPresent )
				{
					psActionImpl.clickNewAction( clientPartition, "PS_Detail_systemTariffMapping_detailXpath" );
					configuresystemTariffMapping();
					clickOnSave();
					Log4jHelper.logInfo( "'System Tariff Mapping' is successfully created with  elementSet:- '" + elementSet );

				}
				else

					Log4jHelper.logInfo( "'System Tariff Mapping' is already avilable with  elementSet:- '" + elementSet );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	private boolean issystemTariffMappingPresent( String elementSet, String tariffType ) throws Exception
	{
		String expectedRowValue=elementSet+", "+tariffType;
		return psDataComponentHelper.isDataPresentInGrid( "SearchGrid", expectedRowValue );
		 
	}
	/*
	 * This method is for 'systemTariffMapping'  Edit
	 */
	public void systemTariffMappingEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				systemTariffMappingScreen();
				initializeVariable( systemTariffMappingMap );
				boolean issystemTariffMappingPresent=issystemTariffMappingPresent( elementSet, tariffType );
				if ( issystemTariffMappingPresent )
				{
					GridHelper.clickRow( "SearchGrid", elementSet, "Element Set" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					psActionImpl.clickOnAction( "Common Tasks", "Edit", "PS_Detail_systemTariffMapping_detailXpath" );
					modifysystemTariffMapping();
					clickOnSave();
					Log4jHelper.logInfo( "'System Tariff Mapping' is successfully updated with  elementSet:- '" + elementSet );

				}
				else

					Log4jHelper.logInfo( "'System Tariff Mapping' is not avilable with  elementSet:- '" + elementSet );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/* This method is used 
	 * configure  System Tariff Mapping
	 */
	private void configuresystemTariffMapping() throws Exception
	{

		ComboBoxHelper.select( "PS_Detail_systemTariffMapping_elementSet_combId", elementSet );
		ComboBoxHelper.select( "PS_Detail_systemTariffMapping_tariffType_combId", tariffType );

	}

	/* This method is used 
	 * modify  systemTariffMapping
	 */
	private void modifysystemTariffMapping() throws Exception
	{
		assertEquals( ComboBoxHelper.getValue( "PS_Detail_systemTariffMapping_elementSet_combId" ), elementSet, "systemTariffMapping elementSet is not matched" );
	
		psDataComponentHelper.modifyComboBox( "PS_Detail_systemTariffMapping_tariffType_combId", tariffType );

	}

	/* This method is used to click on save button*/
	private void clickOnSave() throws Exception
	{
		psGenericHelper.detailSave( "PS_Detail_systemTariffMapping_save_btnId", elementSet, "Element Set" );
	}
}
