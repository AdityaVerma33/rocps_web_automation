package com.subex.rocps.automation.helpers.application.carrierinvoice.invoicereconconfig;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;

public class InvoiceReconConfigDetailImpl extends PSAcceptanceTest
{
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> reconExcel = null;
	protected Map<String, ArrayList<String>> excelReaderMapObj = null;
	protected Map<String, String> reconMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String name;
	protected String invoiceTemplate;
	protected String reconsilationComponent;
	protected String automationComponent;
	protected String invoiceSelection;
	protected String invoiceStep;
	protected String autoBaseLine;
	protected String stepName;
	protected String invoiceSource;
	protected String invoiceViewQuery;
	protected String systemSource;
	protected String SystemViewQuery;
	protected String systemFieldSelect;
	protected String systemFieldAggregate;
	protected String matchKeyInvoiceField;
	protected String matchKeySystemField;
	protected String matchKeyCurrency;
	protected String matchKeysComparisonType;
	protected String showMismatch;
	protected String matchComparisionInvoice;
	protected String matchComparisionSystem;
	protected String displayName;
	protected String color;
	protected String marked;
	protected String operator;
	protected String value;
	protected String markCriteria;
	protected String percentage;
	protected String absolute;
	protected String lowerThresholdPercen;
	protected String lowerThreshold;
	protected String higherThresholdPercen;
	protected String higherThreshold;
	protected String systemSourcetableInst;
	protected String invoiceSourceTableInst;
	protected String advClause;
	protected String advOpenBraces;
	protected String advColumn1;
	protected String advOperator;
	protected String advAggregate1;
	protected String advAggregate2;
	protected String advColumn2;
	protected String advCloseBraces;
	protected String joinType;
	protected String joinTableName;
	protected String joinColumn1;
	protected String joinOperator;
	protected String joinColumn2;
	protected String invoiceTableName;
	protected String compareTotalAmtFlg;
	protected String totalAmtThresld_percentageFlg;
	protected String totalAmtThresld_absoluteValFlg;
	protected String totalAmtThresld_percentage_lowerThreshold;
	protected String totalAmtThresld_percentage_uperThresold;
	protected String totalAmtThresld_absoluteVal_lowerThreshold;
	protected String totalAmtThresld_absoluteVal_uperThresold;
	String[] invoiceSourceTableInstArr;
	String[] invoiceSourceArr;
	String[] systemSourceArr;
	String[] invoiceFieldArr;
	String[] systemFieldArr;
	String[] matchComparisionInvoiceArr;
	String[] matchComparisionSystemArr;
	String[] displayNameArr;
	String[] markedArr;
	String[] operatorArr;
	String[] valueArr;
	String[] markCriteriaArr;
	String[] systemFieldSelectArr;
	String[] advClauseArr;
	String[] advOpenBracesArr;
	String[] advColumn1Arr;
	String[] advOperatorArr;
	String[] advAggregate1Arr;
	String[] advAggregate2Arr;
	String[] advColumn2Arr;
	String[] advCloseBracesArr;
	String[] matchKeyCurrencyArr;
	String[] matchKeysComparisonTypeArr;
	String[] systemSourcetableInstArr;
	String[] joinTypeArr;
	String[] joinTableNameArr;
	String[] joinColumn1Arr;
	String[] joinOperatorArr;
	String[] joinColumn2Arr;
	PSStringUtils stringObj = new PSStringUtils();
	protected Map<String, String> map;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();

	/*
	 * This method is for invoiceRecon basic details
	 */
	public void invoiceReconBasicConfig( String name, String template, String reconComponent, String automationComponent, String invoiceSelection, String invoiceStep, String autoBaseline ) throws Exception
	{
		TextBoxHelper.type( "pireName", name );
		PSEntityComboHelper.selectUsingGridFilterTextBox( "invoiceTemplate", "Carrier Invoice Template Search", "pintName", template, "Name" );
		ComboBoxHelper.select( "component_gwt_uid_", reconComponent );
		ComboBoxHelper.select( "automationConfiguationAutomationConfiguation_gwt_uid_", automationComponent );

		ComboBoxHelper.select( "invoiceContent_gwt_uid_", invoiceSelection );
		ComboBoxHelper.select( "invTemTableDfn_gwt_uid_", invoiceStep );
		if ( ValidationHelper.isTrue( autoBaseLine ) )
			CheckBoxHelper.check( "pireAutoAcceptFl_InputElement" );

	}

	/*
	 * This method is for recon detail config
	 */
	public void invoiceReconDetailConfig( String path, String workBook, String sheetName, String stepName, Map<String, String> map ) throws Exception
	{
		String[] stepNameArr = stringObj.stringSplitFirstLevel( stepName );
		String invoiceSelection = ExcelHolder.getKey( map, "InvoiceSelection" );
		for ( int i = 0; i < stepNameArr.length; i++ )
		{
			TextBoxHelper.type( "pirsName", stepNameArr[i] );
			ElementHelper.click( "//div[contains(@id,'invTemTableDfn_gwt_uid_')]" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			ButtonHelper.click( "addStep" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			Thread.sleep( 20000 );
			map = excelTestDataInitialize( path, workBook, sheetName, stepNameArr[i] );
			initialiseVariables( map );
			initializeArray();
			if ( invoiceSelection.equals( "Usage" ) )
				configureTotalAmtThreshold();
			invoiceSourceGridConfig();
			systemSourceGridConfig();
			matchKeyGridConfig();
			matchComparisions();
		}
	}

	private void configureTotalAmtThreshold() throws Exception
	{
		if ( ValidationHelper.isTrue( compareTotalAmtFlg ) )
		{
			CheckBoxHelper.check( "pirsSummaryLevelFl_InputElement" );
			ButtonHelper.click( "thresolddtl" );
			ElementHelper.waitForElement( "//div[@id='window-scroll-panel']", searchScreenWaitSec );
			configTotalAmtThreshPercentage();
			configTotalAmtAbsoluteVal();
			ButtonHelper.click( "reconSummaryThresholdDetail.Save" );
			ElementHelper.waitForElementToDisappear( "//div[@id='window-scroll-panel']", searchScreenWaitSec );
		}
		else if ( ValidationHelper.isFalse( compareTotalAmtFlg ) && CheckBoxHelper.isChecked( "piaiSummaryLevelFl_InputElement" ) )
			CheckBoxHelper.uncheck( "piaiSummaryLevelFl_InputElement" );
	}

	private void configTotalAmtThreshPercentage() throws Exception
	{
		if ( ValidationHelper.isFalse( totalAmtThresld_percentageFlg ) && CheckBoxHelper.isChecked( "prsuPercentageValidate_InputElement" ) )
			CheckBoxHelper.uncheck( "prsuPercentageValidate_InputElement" );
		if ( ValidationHelper.isTrue( totalAmtThresld_percentageFlg ) )
		{
			CheckBoxHelper.check( "prsuPercentageValidate_InputElement" );
			TextBoxHelper.type( "prsuMinPctThreshold", totalAmtThresld_percentage_lowerThreshold );
			TextBoxHelper.type( "prsuMaxPctThreshold", totalAmtThresld_percentage_uperThresold );

		}

	}

	private void configTotalAmtAbsoluteVal() throws Exception
	{
		if ( ValidationHelper.isFalse( totalAmtThresld_absoluteValFlg ) && CheckBoxHelper.isChecked( "prsuAbsoluteValidate_InputElement" ) )
			CheckBoxHelper.uncheck( "prsuAbsoluteValidate_InputElement" );
		if ( ValidationHelper.isTrue( totalAmtThresld_absoluteValFlg ) )
		{
			CheckBoxHelper.check( "prsuAbsoluteValidate_InputElement" );
			TextBoxHelper.type( "prsuMinAbsThreshold", totalAmtThresld_absoluteVal_lowerThreshold );
			TextBoxHelper.type( "prsuMaxAbsThreshold", totalAmtThresld_absoluteVal_uperThresold );

		}

	}

	/*
	 * This method is to view query of invoice and system source grid
	 */
	public void viewQueryConfig( String gridID, String value, String viewBtn ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( value ) )
		{
			GridHelper.clickRow( gridID, 1, "Field" );
			ButtonHelper.click( viewBtn );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "View Query Detail" );
			String uiValue = TextAreaHelper.getValue( "viewQueryDetail.window", "sqlQuery" );
			assertEquals( uiValue, value, "View Query Values are not matching" );
			ButtonHelper.click( "viewQueryDetail.cancel" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}
	}

	/*
	 * this metjod is for invoice source grid config
	 */
	public void invoiceSourceGridConfig() throws Exception
	{
		String uiValue;
		for ( int j = 0; j < invoiceSourceArr.length; j++ )
		{
			int row = GridHelper.getRowNumber( "lhsSourceGrid", invoiceSourceArr[j], "Field" );
			if ( row != 0 )
			{
				uiValue = GridHelper.getCellValue( "lhsSourceGrid", row, "Field" );
				assertEquals( uiValue, invoiceSourceArr[j], "Invoice Source Fields are not matching" );
				Log4jHelper.logInfo( "Invoice Source Excel Value :" + invoiceSourceArr[j] );
				Log4jHelper.logInfo( "Invoice Source UI Value :" + uiValue );
			}
			else if ( row == 0 )
			{
				ButtonHelper.click( "lhsToolbar.addTables" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				if ( !invoiceSourceTableInst.isEmpty() )
				{
					tableInstanceSelection( invoiceSourceTableInstArr, "rawSourceToolBar.addTableInstance" );
					dualListSelection( invoiceSourceArr[j] );
				}
			}
		}
		viewQueryConfig( "lhsSourceGrid", invoiceViewQuery, "toolbar-button-label-lhsToolbar.viewQuery" );
		if ( !invoiceTableName.isEmpty() )
		{
			ButtonHelper.click( "toolbar-button-label-lhsToolbar.advanceOption" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			String uiVal = GridHelper.getCellValue( "joinFilterGrid", 1, 2 );
			assertEquals( uiVal, invoiceTableName );
		}
	}

	/*
	 * this method is for system source config grid
	 */
	public void systemSourceGridConfig() throws Exception
	{
		String uiValue;
		int rowCount = GridHelper.getRowCount( "rhsSourceGrid" );
		if ( rowCount == 0 )
		{
			ButtonHelper.click( "rhsToolbar.addTables" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Add Tables Details" );
			if ( !systemSourcetableInst.isEmpty() )
				tableInstanceSelection( systemSourcetableInstArr, "rawSourceToolBar.addTableInstance" );

			for ( String strVal : systemSourceArr )
			{
				dualListSelectionArray( strVal );
			}

			ButtonHelper.click( "tableInstDualListDetail.OK" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			if ( PopupHelper.isPresent( "//*[@class='roc-WindowBox-bottom-bar']" ) )
			{
				ButtonHelper.click( "YesButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
			}
			GenericHelper.waitForLoadmask( searchScreenWaitSec );

		}
		else if ( rowCount != 0 )
		{
			for ( int j = 0; j < systemSourceArr.length; j++ )
			{
				int row = GridHelper.getRowNumber( "rhsSourceGrid", systemSourceArr[j], "Field" );
				if ( row != 0 )
				{
					uiValue = GridHelper.getCellValue( "rhsSourceGrid", row, "Field" );
					assertEquals( uiValue, systemSourceArr[j], "System Source Fields are not matching" );
					Log4jHelper.logInfo( "Invoice Source Excel Value :" + systemSourceArr[j] );
					Log4jHelper.logInfo( "Invoice Source UI Value :" + uiValue );
				}
				else if ( row == 0 )

				{
					ButtonHelper.click( "rhsToolbar.addTables" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					if ( !systemSourcetableInst.isEmpty() )
						tableInstanceSelection( systemSourcetableInstArr, "rawSourceToolBar.addTableInstance" );
					assertEquals( NavigationHelper.getScreenTitle(), "Add Tables Details" );
					dualListSelection( systemSourceArr[j] );
				}
			}
		}

		selectConfig( "rhsSourceGrid", "Select", systemFieldSelectArr );
		viewQueryConfig( "rhsSourceGrid", SystemViewQuery, "toolbar-button-label-rhsToolbar.viewQuery" );
		if ( !joinType.isEmpty() || !advClause.isEmpty() )
			advanceOptionsDetail();

	}

	/*
	 * This method is for match key grid config
	 */
	public void matchKeyGridConfig() throws Exception
	{
		for ( int i = 0; i < invoiceFieldArr.length; i++ )
		{
			int row = GridHelper.getRowNumber( "matchKeyGrid", invoiceFieldArr[i], "Invoice Field" );
			if ( row == 0 )
			{
				ButtonHelper.click( "matchKeyToolbar.add" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				GridHelper.updateGridComboBox( "matchKeyGrid", "lhsMatchKeyColumnCombo_gwt_uid_", i + 1, "Invoice Field", invoiceFieldArr[i] );
				GridHelper.updateGridComboBox( "matchKeyGrid", "rhsMatchKeyColumnCombo_gwt_uid_", i + 1, "System Field", systemFieldArr[i] );
				if ( ValidationHelper.isNotEmpty( matchKeyCurrency ) && ValidationHelper.isTrue( matchKeyCurrencyArr[i] ) )
				{
					GridHelper.clickRow( "matchKeyGrid", i + 1, "Currency" );
					ButtonHelper.click( "toolbar-button-label-matchKeyToolbar.markCurrency" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
				}
				if ( ValidationHelper.isNotEmpty( matchKeysComparisonType ) && ValidationHelper.isNotEmpty( matchKeysComparisonTypeArr[i] ) )
				{
					GridHelper.clickRow( "matchKeyGrid", i + 1, "Comparison  Type" );
					GridHelper.updateGridComboBox( "matchKeyGrid", "pimkCompTypeEditor_gwt_uid_", i + 1, "Comparison  Type", matchKeysComparisonTypeArr[i] );
				}

			}
			else if ( row != 0 )
			{
				assertEquals( GridHelper.getCellValue( "matchKeyGrid", row, "Invoice Field" ), invoiceFieldArr[i] );
				assertEquals( GridHelper.getCellValue( "matchKeyGrid", row, "System Field" ), systemFieldArr[i] );
				if ( ValidationHelper.isNotEmpty( matchKeyCurrency ) && ValidationHelper.isTrue( matchKeyCurrencyArr[i] ) )
				{
					GridHelper.clickRow( "matchKeyGrid", row, "Currency" );
					ButtonHelper.click( "toolbar-button-label-matchKeyToolbar.markCurrency" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
				}
				if ( ValidationHelper.isNotEmpty( matchKeysComparisonType ) && ValidationHelper.isNotEmpty( matchKeysComparisonTypeArr[i] ) )
				{
					GridHelper.clickRow( "matchKeyGrid", i + 1, "Comparison  Type" );
					GridHelper.updateGridComboBox( "matchKeyGrid", "pimkCompTypeEditor_gwt_uid_", i + 1, "Comparison  Type", matchKeysComparisonTypeArr[i] );
				}
			}
		}
	}

	/*
	 * This method is for match key grid config
	 */

	public void matchComparisions() throws Exception
	{

		for ( int i = 0; i < matchComparisionInvoiceArr.length; i++ )
		{
			ElementHelper.scrollToView( "//div[@id='grid_column_header_undefined_lhsSqlQuerySelectModel']", false );
			GenericHelper.waitForLoadmask();
			int row = GridHelper.getRowNumber( "matchCompGrid", matchComparisionInvoiceArr[i], "Invoice Field" );
			if ( row == 0 )
			{
				ButtonHelper.click( "matchComparisonToolbar.add" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				GridHelper.updateGridComboBox( "matchCompGrid", "lhsCompColumnCombo_gwt_uid_", i + 1, "Invoice Field", matchComparisionInvoiceArr[i] );
				GridHelper.updateGridComboBox( "matchCompGrid", "rhsCompColumnCombo_gwt_uid_", i + 1, "System Field", matchComparisionSystemArr[i] );
				GridHelper.updateGridTextBox( "matchCompGrid", "pimcNameEditor", i + 1, "Display Name", displayNameArr[i] );
				GridHelper.updateGridComboBox( "matchCompGrid", "ctriteriaEditor_gwt_uid_", i + 1, "Marked", markedArr[i] );
				if ( ValidationHelper.isNotEmpty( operator ) && ValidationHelper.isNotEmpty( operatorArr[i] ) )
					GridHelper.updateGridComboBox( "matchCompGrid", "operationEditor_gwt_uid_", i + 1, "Operator", operatorArr[i] );
				if ( ValidationHelper.isNotEmpty( value ) && ValidationHelper.isNotEmpty( valueArr[i] ) )
					GridHelper.updateGridTextBox( "matchCompGrid", "valueEditor", i + 1, "Value", valueArr[i] );
				if ( ValidationHelper.isTrue( markCriteriaArr[i] ) )
				{
					GridHelper.clickRow( "matchCompGrid", i + 1, "Criteria" );
					ButtonHelper.click( "matchComparisonToolbar.markCriteria" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					markCriteria();
				}
			}
			if ( row != 0 )
			{
				assertEquals( GridHelper.getCellValue( "matchCompGrid", row, "Invoice Field" ), matchComparisionInvoiceArr[i] );
				assertEquals( GridHelper.getCellValue( "matchCompGrid", row, "System Field" ), matchComparisionSystemArr[i] );
				assertEquals( GridHelper.getCellValue( "matchCompGrid", row, "Display Name" ), displayNameArr[i] );
				GridHelper.updateGridComboBox( "matchCompGrid", "ctriteriaEditor_gwt_uid_", row, "Marked", markedArr[i] );
				if ( ValidationHelper.isNotEmpty( operator ) && ValidationHelper.isNotEmpty( operatorArr[i] ) )
					GridHelper.updateGridComboBox( "matchCompGrid", "operationEditor_gwt_uid_", row, "Operator", operatorArr[i] );
				if ( ValidationHelper.isNotEmpty( value ) && ValidationHelper.isNotEmpty( valueArr[i] ) )
					GridHelper.updateGridTextBox( "matchCompGrid", "valueEditor", row, "Value", valueArr[i] );
				if ( ValidationHelper.isTrue( markCriteriaArr[i] ) )
				{
					GridHelper.clickRow( "matchCompGrid", row, "Criteria" );
					ButtonHelper.click( "matchComparisonToolbar.markCriteria" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					markCriteria();
				}
			}
		}
	}

	/*
	 * This method is for table instance selection
	 */
	public void tableInstanceSelection( String[] value, String addTableInstbtnID ) throws Exception
	{
		for ( int k = 0; k < value.length; k++ )
		{
			if ( !value[k].isEmpty() )
			{
				ButtonHelper.click( "rawSourceToolBar.addTableInstance" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				//assertEquals( NavigationHelper.getScreenTitle(), "Table Instance Search" );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				SearchGridHelper.gridFilterSearchWithTextBox( "tinDisplayName", value[k], "Display Name" );
				GridHelper.clickRow( "popupWindow", "searchGrid", value[k], "Display Name" );
				ButtonHelper.click( "OKButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
			}
		}

	}

	/*
	 * this method is to mark criteria
	 */
	public void markCriteria() throws Exception
	{
		if ( ValidationHelper.isTrue( percentage ) )
		{
			CheckBoxHelper.check( "pirtPercentageValidate_InputElement" );
			TextBoxHelper.type( "pirtMinPctThreshold", lowerThresholdPercen );
			TextBoxHelper.type( "pirtMaxPctThreshold", higherThresholdPercen );
		}

		if ( ValidationHelper.isTrue( absolute ) )
		{
			CheckBoxHelper.check( "pirtAbsoluteValidate_InputElement" );
			TextBoxHelper.type( "pirtMinAbsThreshold", lowerThreshold );
			TextBoxHelper.type( "pirtMaxAbsThreshold", higherThreshold );
		}

		ButtonHelper.click( "invoiceReconThresholdDetail.OK" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	/*
	 * this method is to select config
	 */
	public void selectConfig( String gridID, String colHeader, String[] valueArr ) throws Exception
	{
		for ( int i = 0; i < valueArr.length; i++ )
		{
			if ( ValidationHelper.isTrue( valueArr[i] ) )
			{
				GridHelper.clickRow( gridID, i + 1, colHeader );
				GenericHelper.waitForLoadmask( detailScreenWaitSec );
				GridHelper.clickRow( gridID, i + 1, 2 );
			}
		}
	}

	/*
	 * This method is for dual list selection 
	 */
	public void dualListSelectionArray( String dualListValue ) throws Exception
	{
		String[] dualListValueArr = new PSStringUtils().stringSplitFirstLevel( dualListValue );
		for ( String str : dualListValueArr )
		{
			if ( dualListValueArr.length >= 1 )
			{
				String locator = "//*[@id='rawSourceTree']//div[contains(text(),'" + str + "')]";
				ElementHelper.scrollDown( locator, true );
				ElementHelper.scrollToView( locator, true );
				ElementHelper.click( locator );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				ButtonHelper.click( "tableInstDualListDetail.window", "addButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );

			}
		}
	}

	public void dualListSelection( String dualListValue ) throws Exception
	{
		String locator = "//div[contains(text(),'" + dualListValue + "')]";
		ElementHelper.scrollDown( locator, false );
		ElementHelper.click( locator );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "tableInstDualListDetail.window", "addButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "tableInstDualListDetail.OK" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		if ( PopupHelper.isPresent( "//*[@class='roc-WindowBox-bottom-bar']" ) )
		{
			ButtonHelper.click( "YesButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}
	}

	/*
	 * This method is for advance options in invoice grid and system source grid
	 */
	public void advanceOptionsDetail() throws Exception
	{
		if ( !joinType.isEmpty() )
		{
			//GridHelper.clickRow( "rhsSourceGrid", 1, 1 );
			ButtonHelper.click( "rhsToolbar.advanceOption" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			for ( int i = 0; i < joinTableNameArr.length; i++ )
			{
				int rowNum = GridHelper.getRowNumber( "joinFilterGrid", joinTableNameArr[i], "Table Name" );
				GridHelper.updateGridComboBox( "joinFilterGrid", "joinTypeComboEditor_gwt_uid_", rowNum, 1, "Table Name", joinTypeArr[i] );
				GridHelper.updateGridComboBox( "joinFilterGrid", "lhsJoinTableColumnCombo_gwt_uid_", rowNum, 3, "Table Name", joinColumn1Arr[i] );
				GridHelper.updateGridComboBox( "joinFilterGrid", "joinOperatorComboEditor_gwt_uid_", rowNum, 4, "Table Name", joinOperatorArr[i] );
				GridHelper.updateGridComboBox( "joinFilterGrid", "rhsJoinTableColumnCombo_gwt_uid_", rowNum, 5, "Table Name", joinColumn2Arr[i] );

			}
		}
		if ( !advClause.isEmpty() )
		{
			for ( int i = 0; i < advClauseArr.length; i++ )
			{

				ButtonHelper.click( "toolbar-button-label-rhsToolbar.advanceOption" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );

				ButtonHelper.click( "toolbar-button-label-queryFilterToolbar.addCondition" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				GridHelper.updateGridComboBox( "queryFilterGrid", "sqwOperatorComboEditor_gwt_uid_", i + 1, "Clause", advClauseArr[i] );
				GridHelper.updateGridTextBox( "queryFilterGrid", "leftIndentEditor", i + 1, "(", advOpenBracesArr[i] );
				GridHelper.updateGridComboBox( "queryFilterGrid", "lhsAggregateComboEditor_gwt_uid_", i + 1, "Aggregate", advAggregate1Arr[i] );
				GridHelper.updateGridComboBox( "queryFilterGrid", "id_gwt_uid_", i + 1, "Column", advColumn1Arr[i] );
				GridHelper.updateGridComboBox( "queryFilterGrid", "operatorComboEditor_gwt_uid_", i + 1, "Operator", advOperatorArr[i] );
				GridHelper.updateGridComboBox( "queryFilterGrid", "rhsAggregateComboEditor_gwt_uid_", i + 1, "Aggregate", advAggregate2Arr[i] );
				GridHelper.updateGridComboBox( "queryFilterGrid", "id_gwt_uid_", i + 1, "Column", advColumn2Arr[i] );
				GridHelper.updateGridTextBox( "queryFilterGrid", "rightIndentEditor", i + 1, ")", advCloseBracesArr[i] );

			}
		}

		ButtonHelper.click( "advanceOptionsDetail.OK" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	/*
	 * This method is to save invoice recon config
	 */
	public void saveInvoiceReconConfig( String name ) throws Exception
	{
		ButtonHelper.click( "invoiceReconciliationDetail.save" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ) );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		Log4jHelper.logInfo( "Invoice Recon Config is created successfully for :" + name );
	}

	private Map<String, String> excelTestDataInitialize( String path, String workBook, String sheetName, String testCaseName ) throws Exception
	{
		excelData = new ExcelReader();
		reconExcel = excelData.readDataByColumn( path, workBook, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( reconExcel );
		return map = excelHolderObj.dataMap( 0 );
	}

	public void initializeArray() throws Exception
	{
		invoiceSourceTableInstArr = stringObj.stringSplitFirstLevel( invoiceSourceTableInst );
		systemSourcetableInstArr = stringObj.stringSplitFirstLevel( systemSourcetableInst );
		invoiceSourceArr = stringObj.stringSplitFirstLevel( invoiceSource );
		systemSourceArr = stringObj.stringSplitFirstLevel( systemSource );
		invoiceFieldArr = stringObj.stringSplitFirstLevel( matchKeyInvoiceField );
		systemFieldArr = stringObj.stringSplitFirstLevel( matchKeySystemField );
		matchKeyCurrencyArr = stringObj.stringSplitFirstLevel( matchKeyCurrency );
		matchKeysComparisonTypeArr = stringObj.stringSplitFirstLevel( matchKeysComparisonType );
		matchComparisionInvoiceArr = stringObj.stringSplitFirstLevel( matchComparisionInvoice );
		matchComparisionSystemArr = stringObj.stringSplitFirstLevel( matchComparisionSystem );
		displayNameArr = stringObj.stringSplitFirstLevel( displayName );
		markedArr = stringObj.stringSplitFirstLevel( marked );
		operatorArr = stringObj.stringSplitFirstLevel( operator );
		valueArr = stringObj.stringSplitFirstLevel( value );
		markCriteriaArr = stringObj.stringSplitFirstLevel( markCriteria );
		systemFieldSelectArr = stringObj.stringSplitFirstLevel( systemFieldSelect );
		advClauseArr = stringObj.stringSplitFirstLevel( advClause );
		advOpenBracesArr = stringObj.stringSplitFirstLevel( advOpenBraces );
		advColumn1Arr = stringObj.stringSplitFirstLevel( advColumn1 );
		advOperatorArr = stringObj.stringSplitFirstLevel( advOperator );
		advAggregate1Arr = stringObj.stringSplitFirstLevel( advAggregate1 );
		advAggregate2Arr = stringObj.stringSplitFirstLevel( advAggregate2 );
		advColumn2Arr = stringObj.stringSplitFirstLevel( advColumn2 );
		advCloseBracesArr = stringObj.stringSplitFirstLevel( advCloseBraces );
		joinTypeArr = stringObj.stringSplitFirstLevel( joinType );
		joinTableNameArr = stringObj.stringSplitFirstLevel( joinTableName );
		joinColumn1Arr = stringObj.stringSplitFirstLevel( joinColumn1 );
		joinOperatorArr = stringObj.stringSplitFirstLevel( joinOperator );
		joinColumn2Arr = stringObj.stringSplitFirstLevel( joinColumn2 );
	}

	public void initialiseVariables( Map<String, String> map ) throws Exception
	{
		invoiceTableName = ExcelHolder.getKey( map, "InvoiceTableName" );
		invoiceSource = ExcelHolder.getKey( map, "InvoiceSource" );
		invoiceViewQuery = ExcelHolder.getKey( map, "InvoiceViewQuery" );
		systemSource = ExcelHolder.getKey( map, "SystemSource" );
		SystemViewQuery = ExcelHolder.getKey( map, "SystemViewQuery" );
		systemFieldSelect = ExcelHolder.getKey( map, "SystemFieldSelect" );
		systemFieldAggregate = ExcelHolder.getKey( map, "SystemFieldAggregate" );
		matchKeyInvoiceField = ExcelHolder.getKey( map, "MatchKeyInvoiceField" );
		matchKeySystemField = ExcelHolder.getKey( map, "MatchKeySystemField" );
		matchKeyCurrency = ExcelHolder.getKey( map, "MatchKeyCurrency" );
		matchKeysComparisonType = ExcelHolder.getKey( map, "MatchKeysComparisonType" );
		showMismatch = ExcelHolder.getKey( map, "ShowMismatch" );
		matchComparisionInvoice = ExcelHolder.getKey( map, "MatchComparisionInvoice" );
		matchComparisionSystem = ExcelHolder.getKey( map, "MatchComparisionSystem" );
		displayName = ExcelHolder.getKey( map, "DisplayName" );
		color = ExcelHolder.getKey( map, "Color" );
		marked = ExcelHolder.getKey( map, "Marked" );
		operator = ExcelHolder.getKey( map, "Operator" );
		value = ExcelHolder.getKey( map, "Value" );
		markCriteria = ExcelHolder.getKey( map, "MarkCriteria" );
		percentage = ExcelHolder.getKey( map, "Percentage" );
		absolute = ExcelHolder.getKey( map, "Absolute" );
		lowerThresholdPercen = ExcelHolder.getKey( map, "LowerThresholdPercen" );
		lowerThreshold = ExcelHolder.getKey( map, "LowerThreshold" );
		higherThresholdPercen = ExcelHolder.getKey( map, "HigherThresholdPercen" );
		higherThreshold = ExcelHolder.getKey( map, "HigherThreshold" );
		systemSourcetableInst = ExcelHolder.getKey( map, "SystemSourceTableInst" );
		advClause = ExcelHolder.getKey( map, "AdvClause" );
		advOpenBraces = ExcelHolder.getKey( map, "AdvOpenBraces" );
		advOperator = ExcelHolder.getKey( map, "AdvOperator" );
		advColumn1 = ExcelHolder.getKey( map, "AdvColumn1" );
		advAggregate1 = ExcelHolder.getKey( map, "AdvAggregate1" );
		advAggregate2 = ExcelHolder.getKey( map, "AdvAggregate2" );
		advColumn2 = ExcelHolder.getKey( map, "AdvColumn2" );
		advCloseBraces = ExcelHolder.getKey( map, "AdvCloseBraces" );
		joinType = ExcelHolder.getKey( map, "JoinType" );
		joinColumn1 = ExcelHolder.getKey( map, "JoinColumn1" );
		joinOperator = ExcelHolder.getKey( map, "JoinOperator" );
		joinTableName = ExcelHolder.getKey( map, "JoinTableName" );
		joinColumn2 = ExcelHolder.getKey( map, "JoinColumn2" );
		invoiceSourceTableInst = ExcelHolder.getKey( map, "InvoiceSourceTableInst" );
		compareTotalAmtFlg = map.get( "CompareTotalAmtFlg" );
		totalAmtThresld_percentageFlg = map.get( "TotalAmtThres_PercentFlg" );
		totalAmtThresld_percentage_lowerThreshold = map.get( "TotalAmtThres_Percent_lowerThreshold" );
		totalAmtThresld_percentage_uperThresold = map.get(  "TotalAmtThres_Percent_upperThreshold" );
		totalAmtThresld_absoluteValFlg = map.get(  "TotalAmtThres_absoluteValFlg" );
		totalAmtThresld_absoluteVal_lowerThreshold = map.get("TotalAmtThres_absoluteVal_lowerThreshold" );
		totalAmtThresld_absoluteVal_uperThresold = map.get("TotalAmtThres_absoluteVal_upperThreshold" );

	}
}
