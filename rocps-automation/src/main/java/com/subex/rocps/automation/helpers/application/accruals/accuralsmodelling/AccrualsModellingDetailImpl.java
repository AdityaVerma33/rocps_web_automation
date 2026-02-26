package com.subex.rocps.automation.helpers.application.accruals.accuralsmodelling;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class AccrualsModellingDetailImpl extends PSAcceptanceTest
{
	protected String partition;
	protected String name;
	protected String prefix;
	protected String accuralModellingComponent;
	protected String provisionComponent;
	protected String gLCodeComponent;
	protected String bilateralModelling;
	protected String schema;
	protected String tableInstanceActual;
	protected String tableInstanceEstimation;
	protected String fieldName;
	protected String displayName;
	protected String dataType;
	protected String keyValue;
	protected String visible;
	protected String searchable;
	protected String transactionAmount;
	protected String partitionIndex;
	protected String summaryTable;
	protected String estimationTable;
	protected String[] fieldNameArr;
	protected String[] displayNameArr;
	protected String[] dataTypeArr;
	protected String[] keyValueArr;
	protected String[] visibleArr;
	protected String[] searchableArr;
	protected String[] transactionAmountArr;
	protected String[] partitionIndexArr;
	protected String[] summaryTableArr;
	protected String[] estimationTableArr;
	protected Map<String, String> map;
	PSStringUtils strObj = new PSStringUtils();
	PSGenericHelper genericObj = new PSGenericHelper();

	public AccrualsModellingDetailImpl( Map<String, String> map ) throws Exception
	{
		this.map = map;
		initialiseVariables( map );
		initializeArray();
	}

	public void newAccuralsModelling() throws Exception
	{
		genericObj.clickNewAction( partition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertTrue( ElementHelper.isElementPresent( "//div[text()='Accrual  Details']" ), "Detail Page is not loaded" );
	}

	public void basicDetails() throws Exception
	{
		TextBoxHelper.type( "PS_Detail_accrualMod_name_txtID", name );
		if ( ValidationHelper.isNotEmpty( prefix ) )
			TextBoxHelper.type( "PS_Detail_accrualMod_prefix_txtID", prefix );
		if ( ValidationHelper.isNotEmpty( accuralModellingComponent ) )
			ComboBoxHelper.select( "PS_Detail_accrualMod_accrualModComp_comboID", accuralModellingComponent );
		if ( ValidationHelper.isNotEmpty( provisionComponent ) )
			ComboBoxHelper.select( "PS_Detail_accrualMod_provisionComo_comboID", provisionComponent );
		if ( ValidationHelper.isNotEmpty( gLCodeComponent ) )
			ComboBoxHelper.select( "PS_Detail_accrualMod_glCodeComp_comboID", gLCodeComponent );
		if ( ValidationHelper.isNotEmpty( bilateralModelling ) )
			ComboBoxHelper.select( "PS_Detail_accrualMod_bilateralMod_comboid", bilateralModelling );
		if ( ValidationHelper.isNotEmpty( schema ) )
			ComboBoxHelper.select( "PS_Detail_accrualMod_schema_comboID", schema );
	}

	public void tableInstance() throws Exception
	{
		tableInstanceSelection( tableInstanceActual, 1 );
		if ( !tableInstanceEstimation.isEmpty() )
			tableInstanceSelection( tableInstanceEstimation, 2 );

	}

	public void editTableInstance() throws Exception
	{
		String actualVal = GridHelper.getCellValue( "PS_Detail_accrualMod_tableInstance_gridID", 1, "Table Instance" );
		if ( !actualVal.contains( tableInstanceActual ) )
			tableInstanceSelection( tableInstanceActual, 1 );

		if ( !tableInstanceEstimation.isEmpty() )
		{
			String actualEstVal = GridHelper.getCellValue( "PS_Detail_accrualMod_tableInstance_gridID", 2, "Table Instance" );
			if ( !actualEstVal.contains( tableInstanceEstimation ) )
				tableInstanceSelection( tableInstanceEstimation, 2 );
		}

	}

	private void tableInstanceSelection( String value, int row ) throws Exception
	{
		GridHelper.updateGridEntityCombo( "PS_Detail_accrualMod_tableInstance_gridID", "PS_Detail_accrualMod_tableInstance_comboID", row, "Table Instance", value );
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		genericObj.waitforPopupHeaderElement( "Display Name" );
		SearchGridHelper.gridFilterSearchWithTextBox( "popupWindow", "PS_Detail_accrualMod_tableInstance_textID", value, "Display Name" );
		GridHelper.clickRow( "SearchGrid", value, "Display Name" );
		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );

	}

	public void columnDetailsTabConfig() throws Exception
	{
		TabHelper.gotoTab( "//*[text()='Column  Details']" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( int index = 0; index < fieldNameArr.length; index++ )
		{
			int row = GridHelper.getRowNumber( "PS_Detail_accrualMod_columnDetail_gridID", fieldNameArr[index], "Field Name" );
			if ( row != 0 )
			{
				griConfig( row, index );
				tableColumnMap( index, row );
				Thread.sleep( 1000 );
			}
			else
			{
				ButtonHelper.click( "PS_Detail_accrualMod_columnDetail_addBtnID" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				int rowNo = GridHelper.getRowCount( "PS_Detail_accrualMod_columnDetail_gridID" );
				griConfig( rowNo, index );
				System.out.println( rowNo );
				tableColumnMap( index, rowNo );
				Thread.sleep( 1000 );
			}

		}
	}

	private boolean isCheckBoxChecked( int row, int col ) throws Exception
	{
		String xpath = GenericHelper.getORProperty( "//*[@id='ardModlingColGrid']//table/tbody//tr[" + row + "]//td[" + col + "]//div[@id='grid_check_editor']/img" );
		String elementTxt = ElementHelper.getAttribute( xpath, "src" );
		if ( elementTxt.contains( "unchecked" ) )
			return false;
		else
			return true;
	}

	public void griConfig( int row, int index ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( fieldName ) && ValidationHelper.isNotEmpty( fieldNameArr[index] ) )
			clickTextBox( "PS_Detail_accrualMod_fieldName_txtID", row, "Field Name", fieldNameArr[index] );
		if ( ValidationHelper.isNotEmpty( displayName ) && ValidationHelper.isNotEmpty( displayNameArr[index] ) )
			clickTextBox( "PS_Detail_accrualMod_displayName_txtID", row, "Display Name", displayNameArr[index] );
		if ( ValidationHelper.isNotEmpty( dataType ) && ValidationHelper.isNotEmpty( dataTypeArr[index] ) )
			clickTextBox( "PS_Detail_accrualMod_datatype_txtID", row, "Data Type", dataTypeArr[index] );
		if ( ValidationHelper.isNotEmpty( keyValue ) && ValidationHelper.isNotEmpty( keyValueArr[index] ) )
			clickComboBox( "PS_Detail_accrualMod_columnDetail_gridID", row, keyValueArr[index], "Key/ Value", "keyValueEditor_gwt_uid_" );
		if ( ValidationHelper.isNotEmpty( visible ) && ValidationHelper.isTrue( visibleArr[index] ) && !isCheckBoxChecked( row, 5 ) )
			GridHelper.updateGridCheckBox( "PS_Detail_accrualMod_columnDetail_gridID", row, "Visible", visibleArr[index] );
		if ( ValidationHelper.isNotEmpty( searchable ) && ValidationHelper.isTrue( searchableArr[index] ) && !isCheckBoxChecked( row, 6 ) )
			GridHelper.updateGridCheckBox( "PS_Detail_accrualMod_columnDetail_gridID", row, "Searchable", searchableArr[index] );
		if ( ValidationHelper.isNotEmpty( transactionAmount ) && ValidationHelper.isTrue( transactionAmountArr[index] ) && !isCheckBoxChecked( row, 7 ) )
			GridHelper.updateGridCheckBox( "PS_Detail_accrualMod_columnDetail_gridID", row, "Transaction Amount", transactionAmountArr[index] );
		if ( ValidationHelper.isNotEmpty( partitionIndex ) && ValidationHelper.isTrue( partitionIndexArr[index] ) && !isCheckBoxChecked( row, 8 ) )
			GridHelper.updateGridCheckBox( "PS_Detail_accrualMod_columnDetail_gridID", row, "Partition Index", partitionIndexArr[index] );
	}

	public void tableColumnMap( int index, int rowNum ) throws Exception
	{
		GridHelper.clickRow( "PS_Detail_accrualMod_columnDetail_gridID", rowNum, "Field Name" );
		if ( !TextBoxHelper.isPresent( "PS_Detail_accrualMod_fieldName_txtID" ) )
			GridHelper.clickRow( "PS_Detail_accrualMod_columnDetail_gridID", rowNum, "Field Name" );

		if ( !summaryTableArr[index].isEmpty() )
			clickComboBox( "PS_Detail_accrualMod_tableColMap_gridID", 1, summaryTableArr[index], "Table Column", "tclDisplayEditor_gwt_uid_" );

		if ( !tableInstanceEstimation.isEmpty() && !estimationTableArr[index].isEmpty() )
			clickComboBox( "PS_Detail_accrualMod_tableColMap_gridID", 2, estimationTableArr[index], "Table Column", "tclDisplayEditor_gwt_uid_" );
	}

	public void clickComboBox( String gridID, int row1, String value, String colHeder, String comboID ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( value ) )
		{
			GridHelper.clickRow( gridID, row1, colHeder );
			if ( !ComboBoxHelper.isPresent( comboID ) )
				GridHelper.clickRow( gridID, row1, colHeder );
			ComboBoxHelper.select( comboID, value );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}
	}

	public void clickTextBox( String textBoxId, int row, String columnName, String value ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( value ) )
		{
			GridHelper.clickRow( "PS_Detail_accrualMod_columnDetail_gridID", row, columnName );
			if ( !TextBoxHelper.isPresent( textBoxId ) )
				GridHelper.clickRow( "PS_Detail_accrualMod_columnDetail_gridID", row, columnName );
			TextBoxHelper.type( textBoxId, value );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}
	}

	public void editColumnDetailsGrid() throws Exception
	{
		TabHelper.gotoTab( "//*[text()='Column  Details']" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( int index = 0; index < fieldNameArr.length; index++ )
		{
			int row = GridHelper.getRowNumber( "PS_Detail_accrualMod_columnDetail_gridID", fieldNameArr[index], "Field Name" );
			if ( row != 0 )
			{
				griConfig( row, index );
				editTableColumnMap( index, row );
				Thread.sleep( 1000 );
			}
			else
			{
				ButtonHelper.click( "PS_Detail_accrualMod_columnDetail_addBtnID" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				int rowNo = GridHelper.getRowCount( "PS_Detail_accrualMod_columnDetail_gridID" );
				griConfig( rowNo, index );
				System.out.println( rowNo );
				editTableColumnMap( index, rowNo );
				Thread.sleep( 1000 );
			}

		}
	}

	public void editTableColumnMap( int index, int rowNum ) throws Exception
	{
		GridHelper.clickRow( "PS_Detail_accrualMod_columnDetail_gridID", rowNum, "Field Name" );
		GridHelper.clickRow( "PS_Detail_accrualMod_columnDetail_gridID", rowNum, "Field Name" );

		int row = GridHelper.getRowNumber( "PS_Detail_accrualMod_tableColMap_gridID", tableInstanceActual, "Table Instance" );
		//assertEquals( GridHelper.getCellValue( "ardModelingColMapGrid", row, "Table Column" ), "");
		clickComboBox( "PS_Detail_accrualMod_tableColMap_gridID", row, summaryTableArr[index], "Table Column", "tclDisplayEditor_gwt_uid_" );
		if ( !tableInstanceEstimation.isEmpty() )
		{
			int row1 = GridHelper.getRowNumber( "PS_Detail_accrualMod_tableColMap_gridID", tableInstanceEstimation, "Table Instance" );
			clickComboBox( "PS_Detail_accrualMod_tableColMap_gridID", row1, estimationTableArr[index], "Table Column", "tclDisplayEditor_gwt_uid_" );
		}

	}

	public void saveAccrualsModelling() throws Exception
	{
		genericObj.detailSave( "PS_Detail_accrualMod_save_btnID", name, "Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	public void initialiseVariables( Map<String, String> map ) throws Exception
	{
		partition = ExcelHolder.getKey( map, "Partition" );
		name = ExcelHolder.getKey( map, "Name" );
		prefix = ExcelHolder.getKey( map, "Prefix" );
		accuralModellingComponent = ExcelHolder.getKey( map, "AccuralModellingComponent" );
		provisionComponent = ExcelHolder.getKey( map, "ProvisionComponent" );
		gLCodeComponent = ExcelHolder.getKey( map, "GLCodeComponent" );
		bilateralModelling = ExcelHolder.getKey( map, "BilateralModelling" );
		schema = ExcelHolder.getKey( map, "Schema" );
		tableInstanceActual = ExcelHolder.getKey( map, "TableInstanceActual" );
		tableInstanceEstimation = ExcelHolder.getKey( map, "TableInstanceEstimation" );
		fieldName = ExcelHolder.getKey( map, "FieldName" );
		displayName = ExcelHolder.getKey( map, "DisplayName" );
		dataType = ExcelHolder.getKey( map, "DataType" );
		keyValue = ExcelHolder.getKey( map, "KeyValue" );
		visible = ExcelHolder.getKey( map, "Visible" );
		searchable = ExcelHolder.getKey( map, "Searchable" );
		transactionAmount = ExcelHolder.getKey( map, "TransactionAmount" );
		partitionIndex = ExcelHolder.getKey( map, "PartitionIndex" );
		summaryTable = ExcelHolder.getKey( map, "SummaryTable" );
		estimationTable = ExcelHolder.getKey( map, "EstimationTable" );
	}

	public void initializeArray() throws Exception
	{
		fieldNameArr = strObj.stringSplitFirstLevel( fieldName );
		displayNameArr = strObj.stringSplitFirstLevel( displayName );
		dataTypeArr = strObj.stringSplitFirstLevel( dataType );
		keyValueArr = strObj.stringSplitFirstLevel( keyValue );
		visibleArr = strObj.stringSplitFirstLevel( visible );
		searchableArr = strObj.stringSplitFirstLevel( searchable );
		transactionAmountArr = strObj.stringSplitFirstLevel( transactionAmount );
		partitionIndexArr = strObj.stringSplitFirstLevel( partitionIndex );
		summaryTableArr = strObj.stringSplitFirstLevel( summaryTable );
		estimationTableArr = strObj.stringSplitFirstLevel( estimationTable );
	}
}
