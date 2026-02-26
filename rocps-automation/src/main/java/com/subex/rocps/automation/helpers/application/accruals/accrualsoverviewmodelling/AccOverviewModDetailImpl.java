package com.subex.rocps.automation.helpers.application.accruals.accrualsoverviewmodelling;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class AccOverviewModDetailImpl extends PSAcceptanceTest
{

	protected String partition;
	protected String name;
	protected String tablePrefix;
	protected String schema;
	protected String filterComponent;
	protected String fXRule;
	protected String graceDays;
	protected String aRDTable;
	protected String amountColumn;
	protected String billBreakdownConfig;
	protected String billAmountColumn;
	protected String showMeasuredEstimation;
	protected String showAcceptedDraft;
	protected String showTopLevelAdjustment;
	protected String showCrossFXRateColumn;
	protected String carryForwardAdjustment;
	protected String fieldName;
	protected String displayName;
	protected String type;
	protected String searchable;
	protected String[] billBreakdownConfigArr;
	protected String[] billAmountColumnArr;
	protected String[] fieldNameArr;
	protected String[] displayNameArr;
	protected String[] typeArr;
	protected String[] searchableArr;

	protected Map<String, String> map;
	PSStringUtils strObj = new PSStringUtils();
	PSGenericHelper genericObj = new PSGenericHelper();

	public AccOverviewModDetailImpl( Map<String, String> map ) throws Exception
	{
		this.map = map;
		initialiseVaribles( map );

	}

	public void newAccuralsOverviewModelling() throws Exception
	{
		genericObj.clickNewAction( partition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertTrue( ElementHelper.isElementPresent( "//div[text()='Basic Modelling Details']" ), "Detail Page is not loaded" );
	}

	public void basicDetails() throws Exception
	{
		TextBoxHelper.type( "PS_Detail_accrualOvMod_name_txtID", name );
		TextBoxHelper.type( "PS_Detail_accrualOvMod_tablePrefix_txtID", tablePrefix );
		ComboBoxHelper.select( "PS_Detail_accrualOvMod_schema_comboID", schema );
		ComboBoxHelper.select( "PS_Detail_accrualOvMod_filterComp_comboID", filterComponent );
		ComboBoxHelper.select( "PS_Detail_accrualOvMod_fxrule_comboID", fXRule );
		TextBoxHelper.type( "PS_Detail_accrualOvMod_graceDays_txtID", graceDays );

		ComboBoxHelper.select( "PS_Detail_accrualOvMod_ardtable_comboID", aRDTable );
		ComboBoxHelper.select( "PS_Detail_accrualOvMod_amtColumn_comboID", amountColumn );

	}

	public void billedInputDetails() throws Exception
	{
		billBreakdownConfigArr = strObj.stringSplitFirstLevel( billBreakdownConfig );
		billAmountColumnArr = strObj.stringSplitFirstLevel( billAmountColumn );
		for ( int i = 0; i < billBreakdownConfigArr.length; i++ )
		{
			int row = GridHelper.getRowNumber( "PS_Detail_accrualOvMod_billbreakdown_gridID", billAmountColumnArr[i], "Amount  Column" );
			if ( row == 0 )
			{
				ButtonHelper.click( "PS_Detail_accrualOvMod_billbreakdown_addbtnID" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );

				ComboBoxHelper.select( "PS_Detail_accrualOvMod_billConfig_comboID", billBreakdownConfigArr[i] );
				ComboBoxHelper.select( "PS_Detail_accrualOvMod_billAmt_comboID", billAmountColumnArr[i] );

				ButtonHelper.click( "PS_Detail_accrualOvMod_bill_okBtn" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
			}
			else
			{
				assertEquals( GridHelper.getCellValue( "PS_Detail_accrualOvMod_billbreakdown_gridID", row, "Bill  Breakdown  Configuration" ), billBreakdownConfigArr[i] );
				assertEquals( GridHelper.getCellValue( "PS_Detail_accrualOvMod_billbreakdown_gridID", row, "Amount  Column" ), billAmountColumnArr[i] );
			}
		}
	}

	public void viewConfigurations() throws Exception
	{
		if ( ValidationHelper.isTrue( showMeasuredEstimation ) && !CheckBoxHelper.isChecked( "paovIsMesAccDiffFl_InputElement" ) )
			CheckBoxHelper.check( "PS_Detail_accrualOvMod_mesuredEstimate_chkBx" );
		if ( ValidationHelper.isTrue( showAcceptedDraft ) && !CheckBoxHelper.isChecked( "paovIsDraftAccDiffFL_InputElement" ) )
			CheckBoxHelper.check( "PS_Detail_accrualOvMod_showacceptedDraft_chkbx" );
		if ( ValidationHelper.isTrue( showTopLevelAdjustment ) && !CheckBoxHelper.isChecked( "paovIsTlaAmtDiffFl_InputElement" ) )
			CheckBoxHelper.check( "PS_Detail_accrualOvMod_adj_chkID" );
		if ( ValidationHelper.isTrue( showCrossFXRateColumn ) && !CheckBoxHelper.isChecked( "paovShowCrossFxFl_InputElement" ) )
			CheckBoxHelper.check( "paovShowCrossFxFl_InputElement" );
		if ( ValidationHelper.isTrue( carryForwardAdjustment ) && !CheckBoxHelper.isChecked( "paovCarryFwdAdjFl_InputElement" ) )
			CheckBoxHelper.check( "PS_Detail_accrualOvMod_carryforward_chkBx" );
	}

	public void computedColumnDetailsTab() throws Exception
	{
		fieldNameArr = strObj.stringSplitFirstLevel( fieldName );
		displayNameArr = strObj.stringSplitFirstLevel( displayName );
		typeArr = strObj.stringSplitFirstLevel( type );
		searchableArr = strObj.stringSplitFirstLevel( searchable );
		if ( ValidationHelper.isNotEmpty( fieldName ) )
		{
			for ( int i = 0; i < fieldNameArr.length; i++ )
			{
				int row = GridHelper.getRowNumber( "PS_Detail_accrualOvMod_computed_gridID", fieldNameArr[i], "Field Name" );
				if ( row == 0 )
				{
					ButtonHelper.click( "computedColumnGridToolbar.Add" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );

					GridHelper.updateGridTextBox( "PS_Detail_accrualOvMod_computed_gridID", "PS_Detail_accrualOvMod_fieldName_txtID", i + 1, "Field Name", fieldNameArr[i] );
					GridHelper.updateGridTextBox( "PS_Detail_accrualOvMod_computed_gridID", "PS_Detail_accrualOvMod_displayName_txtID", i + 1, "Display Name", displayNameArr[i] );
					GridHelper.updateGridComboBox( "PS_Detail_accrualOvMod_computed_gridID", "PS_Detail_accrualOvMod_type_txtID", i + 1, "Type", typeArr[i] );
					GridHelper.updateGridCheckBox( "PS_Detail_accrualOvMod_computed_gridID", i + 1, "Searchable", searchableArr[i] );
				}
			}
		}

	}

	public void saveAccrualOverviewMod() throws Exception
	{
		ButtonHelper.click( "PS_Detail_accrualOvMod_saveBtnID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	public void editBasicDetails() throws Exception
	{
		TextBoxHelper.type( "paovName", name );
		if ( ValidationHelper.isNotEmpty( tablePrefix ) )
			TextBoxHelper.type( "paovTblPrefix", tablePrefix );
		if ( ValidationHelper.isNotEmpty( schema ) )
			ComboBoxHelper.select( "schemaTbl_gwt_uid_", schema );
		if ( ValidationHelper.isNotEmpty( filterComponent ) )
			ComboBoxHelper.select( "filterComponent_gwt_uid_", filterComponent );
		if ( ValidationHelper.isNotEmpty( fXRule ) )
			ComboBoxHelper.select( "fxRuleComponent_gwt_uid_", fXRule );
		if ( ValidationHelper.isNotEmpty( graceDays ) )
			TextBoxHelper.type( "paovNumDays", graceDays );
		if ( ValidationHelper.isNotEmpty( aRDTable ) )
			ComboBoxHelper.select( "tableInst_gwt_uid_", aRDTable );
		if ( ValidationHelper.isNotEmpty( amountColumn ) )
			ComboBoxHelper.select( "amtTableColumn_gwt_uid_", amountColumn );

	}

	public void initialiseVaribles( Map<String, String> map ) throws Exception
	{
		partition = ExcelHolder.getKey( map, "Partition" );
		name = ExcelHolder.getKey( map, "Name" );
		tablePrefix = ExcelHolder.getKey( map, "TablePrefix" );
		schema = ExcelHolder.getKey( map, "Schema" );
		filterComponent = ExcelHolder.getKey( map, "FilterComponent" );
		fXRule = ExcelHolder.getKey( map, "FXRule" );
		graceDays = ExcelHolder.getKey( map, "GraceDays" );
		aRDTable = ExcelHolder.getKey( map, "ARDTable" );
		amountColumn = ExcelHolder.getKey( map, "AmountColumn" );
		billBreakdownConfig = ExcelHolder.getKey( map, "BillBreakdownConfig" );
		billAmountColumn = ExcelHolder.getKey( map, "BillAmountColumn" );
		showMeasuredEstimation = ExcelHolder.getKey( map, "ShowMeasuredEstimation" );
		showAcceptedDraft = ExcelHolder.getKey( map, "ShowAcceptedDraft" );
		showTopLevelAdjustment = ExcelHolder.getKey( map, "ShowTopLevelAdjustment" );
		showCrossFXRateColumn = ExcelHolder.getKey( map, "ShowCrossFXRateColumn" );
		carryForwardAdjustment = ExcelHolder.getKey( map, "CarryForwardAdjustment" );
		fieldName = ExcelHolder.getKey( map, "FieldName" );
		displayName = ExcelHolder.getKey( map, "DisplayName" );
		type = ExcelHolder.getKey( map, "Type" );
		searchable = ExcelHolder.getKey( map, "Searchable" );
	}

}
