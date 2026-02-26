package com.subex.rocps.automation.helpers.application.carrierinvoice.systemfieldlist;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;

public class SystemFieldListDetailImpl extends PSAcceptanceTest
{
	protected String name;
	protected String invoiceSelection;
	protected String tableInst;
	protected String standardStructure;
	protected String outputPrefix;
	protected String outputTableComp;
	protected String tableColumn;
	protected String destinationEntity;
	protected String entityColumn;
	protected String entityMatch;
	protected String systemName;
	protected String partnerFieldName;
	protected String configType;
	protected String gridColumns;
	protected Map<String, String> map;
	String[] tableColumnArr;
	String[] destinationEntityArr;
	String[] entityColumnArr;
	String[] entityMatchArr;
	String[] systemNameArr;
	String[] partnerFieldNameArr;
	String[] configTypeArr;
	PSStringUtils strObj = new PSStringUtils();
	PSGenericHelper genericObj = new PSGenericHelper();

	public SystemFieldListDetailImpl( Map<String, String> map ) throws Exception
	{

		this.map = map;

		initialiseVariables( map );
		initializeArray();
	}

	/*
	 * This method is to configure basic details of system field list
	 */
	public void basicConfig() throws Exception
	{
		TextBoxHelper.type( "PS_systemFieldList_Name_txtID", name );
		ComboBoxHelper.select( "PS_systemFieldList_invoiceSelction_comboID", invoiceSelection );
		String entityXpath = "//*[@id='systemFieldListDetail']//div[contains(@id,'trigger')]";
		if(!ElementHelper.isElementPresent( entityXpath))
			ElementHelper.waitForElement( entityXpath, 120 );
		PSEntityComboHelper.selectUsingGridFilterTextBox( "PS_systemFieldList_tableInst_IconID", "Table Instance Search", "PS_systemFieldList_tableInstDisplayName_txtID", tableInst, "Display Name" );
		if ( ValidationHelper.isTrue( standardStructure ) )
		{
			CheckBoxHelper.check( "PS_systemFieldList_stdStructure_ChkBxID" );

			TextBoxHelper.type( "PS_systemFieldList_outputPrefix_txtID", outputPrefix );
			ComboBoxHelper.select( "PS_systemFieldList_OutPutTableComp_comboID", outputTableComp );
		}
		if ( !gridColumns.isEmpty() )
				gridColumnsValidation();
	}

	/*
	 * This method is to configure system field mapping
	 */
	public void systemFieldMappingGrid() throws Exception
	{
		for ( int row = 0; row < tableColumnArr.length; row++ )
		{
			ButtonHelper.click( "PS_systemFieldList_mapping_Grid_addBtnID" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			if ( ValidationHelper.isNotEmpty( tableColumnArr[row] ) )
			{
				ElementHelper.scrollToView( "//div[@id='grid_column_header_undefined_tableColumn']", false );
				GenericHelper.waitForLoadmask();
				GridHelper.updateGridComboBox( "PS_systemFieldList_mapping_gridID", "PS_systemFieldList_tableColumn_ComboID", row + 1, "Table Column", tableColumnArr[row] );
			}
			if ( ValidationHelper.isNotEmpty( destinationEntityArr[row] ) )
			{
				GridHelper.updateGridEntityCombo( "PS_systemFieldList_mapping_gridID", "PS_systemFieldList_destinationEntity_comboID", row + 1, "Destination Entity", destinationEntityArr[row] );
				PSSearchGridHelper.gridFilterSearchWithTextBox(  "PS_systemFieldList_entity_txtID", destinationEntityArr[row], "Entity" );
				GridHelper.clickRow( "SearchGrid", destinationEntityArr[row], "Entity" );
				ButtonHelper.click( "OKButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
			}
			if ( ValidationHelper.isNotEmpty( entityColumnArr[row] ) )
				GridHelper.updateGridComboBox( "PS_systemFieldList_mapping_gridID", "PS_systemFieldList_entityColumn_comboID", row + 1, "Entity Column", entityColumnArr[row] );
			if ( ValidationHelper.isNotEmpty( entityMatchArr[row] ) )
			{
				GridHelper.updateGridComboBox( "PS_systemFieldList_mapping_gridID", "PS_systemFieldList_entityMatch_comboID", row + 1, "Entity Match", entityMatchArr[row] );
				if(entityMatchArr[row].contains( "cur_" ))
				{
					String msg = "The Entity Match Column of the Entity 'Currency' must have unique data or else in the Carrier Invoice Template(Default Value) and in the Carrier Invoice Detail a Error Message will be shown.";
					assertTrue(PopupHelper.isTextPresent( "window-scroll-panel", msg ));
					ButtonHelper.click( "OKButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					
				}if(entityMatchArr[row].contains( "trn_" ))
				{
					String msg = "The Entity Match Column of the Entity 'TariffRateName' must have unique data or else in the Carrier Invoice Template(Default Value) and in the Carrier Invoice Detail a Error Message will be shown.";
					assertTrue(PopupHelper.isTextPresent( "window-scroll-panel", msg ));
					ButtonHelper.click( "OKButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
				}
			}
			if ( ValidationHelper.isNotEmpty( systemNameArr[row] ) )				
				GridHelper.updateGridTextBox( "PS_systemFieldList_mapping_gridID", "PS_systemFieldList_systemName_comboID", row + 1, "System Name", systemNameArr[row] );
			if ( ValidationHelper.isNotEmpty( partnerFieldNameArr[row] ) )
				GridHelper.updateGridTextBox( "PS_systemFieldList_mapping_gridID", "PS_systemFieldList_partnerName_comboID", row + 1, "Partner Field Name", partnerFieldNameArr[row] );
			if ( ValidationHelper.isNotEmpty( configTypeArr[row] ) )
			{
				ElementHelper.scrollToView( "//div[@id='grid_column_header_undefined_psfmFieldType']", false );
				GenericHelper.waitForLoadmask();
				GridHelper.updateGridComboBox( "PS_systemFieldList_mapping_gridID", "PS_systemFieldList_configType_comboID", row + 1, "Config  Type", configTypeArr[row] );
			}
		}
	}

	/*
	 * this method is to validate internal grids
	 */
	public void gridColumnsValidation() throws Exception
	{

		ArrayList<String> excelColumnNames = new ArrayList<String>();
		String[] searchGridColumnsArr = strObj.stringSplitFirstLevel( gridColumns );
		for ( int col = 0; col < searchGridColumnsArr.length; col++ )
		{
			excelColumnNames.add( searchGridColumnsArr[col] );
		}
		genericObj.totalColumns( excelColumnNames, "PS_systemFieldList_mapping_gridID", "grid_column_header_undefined_" );
	}

	/*
	 * This method is to save system field list
	 */
	public void saveSystemFiledList() throws Exception
	{
		/*ButtonHelper.click( "systemFieldListDetail.save" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ) )*/;
		genericObj.detailSave( "systemFieldListDetail.save", name, "Name" );
		Log4jHelper.logInfo( "System Field List is created successfully for :" + name );
	}

	public void editbasicConfig() throws Exception
	{
		ButtonHelper.click( "PS_systemFieldList_Name_txtID" );
		if(PopupHelper.isPresent( "window-scroll-panel"))
			ButtonHelper.click( "OKButton" );
		TextBoxHelper.type( "PS_systemFieldList_Name_txtID", name );
		
		if(ValidationHelper.isNotEmpty( invoiceSelection ))
			ComboBoxHelper.select( "PS_systemFieldList_invoiceSelction_comboID", invoiceSelection );
		String entityXpath = "//*[@id='systemFieldListDetail']//div[contains(@id,'trigger')]";
		if(!ElementHelper.isElementPresent( entityXpath))
			ElementHelper.waitForElement( entityXpath, 120 );
		if(ValidationHelper.isNotEmpty( tableInst ))
			PSEntityComboHelper.selectUsingGridFilterTextBox( "PS_systemFieldList_tableInst_IconID", "Table Instance Search", "PS_systemFieldList_tableInstDisplayName_txtID", tableInst, "Display Name" );
		if ( ValidationHelper.isNotEmpty( standardStructure ) && ValidationHelper.isTrue( standardStructure ) )
		{
			CheckBoxHelper.check( "PS_systemFieldList_stdStructure_ChkBxID" );

			TextBoxHelper.type( "PS_systemFieldList_outputPrefix_txtID", outputPrefix );
			ComboBoxHelper.select( "PS_systemFieldList_OutPutTableComp_comboID", outputTableComp );
		}
		if ( !gridColumns.isEmpty() )
				gridColumnsValidation();
	}
	
	public void editsystemFieldMappingGrid() throws Exception
	{
		for ( int row = 0; row < tableColumnArr.length; row++ )
		{
			int rowno = GridHelper.getRowNumber( "PS_systemFieldList_mapping_gridID", systemNameArr[row], "System Name" );
			if(rowno == 0)
			{
			ButtonHelper.click( "PS_systemFieldList_mapping_Grid_addBtnID" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			if ( ValidationHelper.isNotEmpty( tableColumnArr[row] ) )
				GridHelper.updateGridComboBox( "PS_systemFieldList_mapping_gridID", "PS_systemFieldList_tableColumn_ComboID", row + 1, "Table Column", tableColumnArr[row] );
			if ( ValidationHelper.isNotEmpty( destinationEntityArr[row] ) )
			{
				GridHelper.updateGridEntityCombo( "PS_systemFieldList_mapping_gridID", "PS_systemFieldList_destinationEntity_comboID", row + 1, "Destination Entity", destinationEntityArr[row] );
				SearchGridHelper.gridFilterSearchWithTextBox( "popupWindow", "PS_systemFieldList_entity_txtID", destinationEntityArr[row], "Entity" );
				GridHelper.clickRow( "SearchGrid", destinationEntityArr[row], "Entity" );
				ButtonHelper.click( "OKButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
			}
			if ( ValidationHelper.isNotEmpty( entityColumnArr[row] ) )
				GridHelper.updateGridComboBox( "PS_systemFieldList_mapping_gridID", "PS_systemFieldList_entityColumn_comboID", row + 1, "Entity Column", entityColumnArr[row] );
			if ( ValidationHelper.isNotEmpty( entityMatchArr[row] ) )
			{
				GridHelper.updateGridComboBox( "PS_systemFieldList_mapping_gridID", "PS_systemFieldList_entityMatch_comboID", row + 1, "Entity Match", entityMatchArr[row] );
				if(entityMatchArr[row].contains( "cur_" ))
				{
					String msg = "The Entity Match Column of the Entity 'Currency' must have unique data or else in the Carrier Invoice Template(Default Value) and in the Carrier Invoice Detail a Error Message will be shown.";
					assertTrue(PopupHelper.isTextPresent( "window-scroll-panel", msg ));
					ButtonHelper.click( "OKButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					
				}if(entityMatchArr[row].contains( "trn_" ))
				{
					String msg = "The Entity Match Column of the Entity 'TariffRateName' must have unique data or else in the Carrier Invoice Template(Default Value) and in the Carrier Invoice Detail a Error Message will be shown.";
					assertTrue(PopupHelper.isTextPresent( "window-scroll-panel", msg ));
					ButtonHelper.click( "OKButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
				}
			}
			if ( ValidationHelper.isNotEmpty( systemNameArr[row] ) )				
				GridHelper.updateGridTextBox( "PS_systemFieldList_mapping_gridID", "PS_systemFieldList_systemName_comboID", row + 1, "System Name", systemNameArr[row] );
			if ( ValidationHelper.isNotEmpty( partnerFieldNameArr[row] ) )
				GridHelper.updateGridTextBox( "PS_systemFieldList_mapping_gridID", "PS_systemFieldList_partnerName_comboID", row + 1, "Partner Field Name", partnerFieldNameArr[row] );
			if ( ValidationHelper.isNotEmpty( configTypeArr[row] ) )
			{
				ElementHelper.scrollToView( "//div[@id='grid_column_header_undefined_psfmFieldType']", false );
				GenericHelper.waitForLoadmask();
				GridHelper.updateGridComboBox( "PS_systemFieldList_mapping_gridID", "PS_systemFieldList_configType_comboID", row + 1, "Config  Type", configTypeArr[row] );
			}
			}else
			{
				assertEquals( GridHelper.getCellValue( "PS_systemFieldList_mapping_gridID", rowno, "Table Column" ), tableColumnArr[row] , "Values are not matching in the grid");
				assertEquals( GridHelper.getCellValue( "PS_systemFieldList_mapping_gridID", rowno, "System Name" ), systemNameArr[row] ,"Values are not matching in the grid");
				assertEquals( GridHelper.getCellValue( "PS_systemFieldList_mapping_gridID", rowno, "Partner Field Name" ), partnerFieldNameArr[row] ,"Values are not matching in the grid");
			}
		}
	}
	public void initializeArray() throws Exception
	{
		tableColumnArr = strObj.stringSplitFirstLevel( tableColumn );
		destinationEntityArr = strObj.stringSplitFirstLevel( destinationEntity );
		entityColumnArr = strObj.stringSplitFirstLevel( entityColumn );
		entityMatchArr = strObj.stringSplitFirstLevel( entityMatch );
		systemNameArr = strObj.stringSplitFirstLevel( systemName );
		partnerFieldNameArr = strObj.stringSplitFirstLevel( partnerFieldName );
		configTypeArr = strObj.stringSplitFirstLevel( configType );
	}

	public void initialiseVariables( Map<String, String> map ) throws Exception
	{
		name = ExcelHolder.getKey( map, "Name" );
		invoiceSelection = ExcelHolder.getKey( map, "InvoiceSelection" );
		tableInst = ExcelHolder.getKey( map, "TableInst" );
		standardStructure = ExcelHolder.getKey( map, "StandardStructure" );
		outputPrefix = ExcelHolder.getKey( map, "OutputPrefix" );
		outputTableComp = ExcelHolder.getKey( map, "OutputTableComp" );
		tableColumn = ExcelHolder.getKey( map, "TableColumn" );
		destinationEntity = ExcelHolder.getKey( map, "DestinationEntity" );
		entityColumn = ExcelHolder.getKey( map, "EntityColumn" );
		entityMatch = ExcelHolder.getKey( map, "EntityMatch" );
		systemName = ExcelHolder.getKey( map, "SystemName" );
		partnerFieldName = ExcelHolder.getKey( map, "PartnerFieldName" );
		configType = ExcelHolder.getKey( map, "ConfigType" );
		gridColumns = ExcelHolder.getKey( map, "GridColumns" );
	}

}
