package com.subex.rocps.automation.helpers.application.matchandrate.operator;

import java.util.List;
import java.util.Map;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class OperatorActImpl extends PSAcceptanceTest
{
	protected Map<String, String> operatorActMap = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String systemTariff;
	protected String operatorTariff;
	protected String systemTRN;
	protected String systemTRNArr[];
	protected String operatorTRN;
	protected String operatorTRNArr[];
	protected Map<String, String> operatorTrnMappingMap = null;
	protected String trnMapping_systemTRN;
	protected String trnMapping_operatorTRN;
	protected String trnMapping_mappingPercentage;

	PSActionImpl psActionImpl = new PSActionImpl();
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();

	/**
	 * Default Constructor
	 */
	public OperatorActImpl()
	{

	}

	/**Constructor
	 * @param operatorActMap
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 */
	public OperatorActImpl( String path, String workBookName, String sheetName, Map<String, String> operatorActMap )
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.operatorActMap = operatorActMap;
	}

	/*
	 * This method is to initialize instance variables
	 */
	public void initOperatorTariffMappingVar( Map<String, String> map ) throws Exception
	{
		systemTariff = ExcelHolder.getKey( map, "SystemTariff" );
		operatorTariff = ExcelHolder.getKey( map, "OperatorTariff" );
		systemTRN = ExcelHolder.getKey( map, "SystemTRN" );
		operatorTRN = ExcelHolder.getKey( map, "OperatorTRN" );
	}

	/*
	 * This method is to initialize instance variables
	 */
	public void initTrnMappingVariable( Map<String, String> map ) throws Exception
	{
		trnMapping_systemTRN = ExcelHolder.getKey( map, "TrnMaping_SystemTRN" );
		trnMapping_operatorTRN = ExcelHolder.getKey( map, "TrnMapping_OperatorTRN" );
		trnMapping_mappingPercentage = ExcelHolder.getKey( map, "TrnMapping_Percentage" );
	}

	public void operatorTariffMapping() throws Exception
	{
		String popupWindow="operatorTariffMappingDetail.window";
		String gridId = "mappingGrid";
		initOperatorTariffMappingVar( operatorActMap );
		psActionImpl.clickOnAction( "Actions", "Operator Tariff Mapping" );
		psGenericHelper.waitforPopupHeaderElement(popupWindow, gridId, "System  Tariff" );
		GridHelper.clickRow( gridId, systemTariff, "System  Tariff" );
		configureMapping(gridId);
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforPopupHeaderElementToDisappear( popupWindow,gridId, "System  Tariff" );

	}

	private void configureMapping(String mappingGridId) throws Exception
	{
		String popupWindow="trnMappingConfigDetail.window";
		String gridId = "trnMappingGrid";
		ButtonHelper.click( "toolbar-button-label-mappingGridToolbar.ConfigureMapping" );
		psGenericHelper.waitforPopupHeaderElement( popupWindow,gridId, "System  Trn" );
		ComboBoxHelper.select( "tariff_gwt_uid_", operatorTariff );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		systemTRNArr = psStringUtils.stringSplitFirstLevel( systemTRN );
		operatorTRNArr = psStringUtils.stringSplitFirstLevel( operatorTRN );
		int systemTrnLength = systemTRNArr.length;
		int operatorTrnLength = operatorTRNArr.length;
		int expectedMappingCount = systemTrnLength * operatorTrnLength;
		int actualMappingCount = GridHelper.getRowCount( gridId );
		assertEquals( actualMappingCount, expectedMappingCount, " Actual mapping count of system trn and operator trn not matched" );
		configureTrnMappingConfig( popupWindow,gridId );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforPopupHeaderElementToDisappear( popupWindow,gridId, "System  Trn" );
		String expecedRowValues = systemTariff + ", " + operatorTariff;
		assertTrue(psDataComponentHelper.isDataPresentInGrid( mappingGridId, expecedRowValues ), "Expected row values is not matched-"+expecedRowValues+" on "+mappingGridId);
		ButtonHelper.click( "operatorTariffMappingDetail.save" );
		confirmationPopup();
		GenericHelper.waitForLoadmask( searchScreenWaitSec );

	}

	private void confirmationPopup() throws Exception
	{
		if(PopupHelper.isPresent())
			ButtonHelper.click( "OKButton" );
		
	}

	private void configureTrnMappingConfig(String popupWindow, String gridId ) throws Exception
	{
		String trnMappingTestCase = ExcelHolder.getKey( operatorActMap, "TRNMappingTestCase" );
		List<Map<String, String>> listOfTrnMappingMap = psDataComponentHelper.getListOfTestCaseMap( path, workBookName, sheetName, trnMappingTestCase );
		for ( int index = 0; index < listOfTrnMappingMap.size(); index++ )
		{
			operatorTrnMappingMap = listOfTrnMappingMap.get( index );
			initTrnMappingVariable( operatorTrnMappingMap );
			String expecedRowValues = trnMapping_systemTRN + ", " + trnMapping_operatorTRN;
			int rowNum = psDataComponentHelper.getRowNumOfGridWithMultCellValue( gridId, expecedRowValues );
			assertTrue( rowNum > 0, "The given expected values-:'" + expecedRowValues + "' is not found on grid-" + gridId );
			GridHelper.clickRow(popupWindow, gridId, rowNum, "Percentage" );
			GridHelper.updateGridTextBox( gridId, "percentageEditor", rowNum, "Percentage", trnMapping_mappingPercentage );
			operatorTrnMappingMap = null;
		}
		ButtonHelper.click( "trnMappingConfigDetail.Ok" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}
}
