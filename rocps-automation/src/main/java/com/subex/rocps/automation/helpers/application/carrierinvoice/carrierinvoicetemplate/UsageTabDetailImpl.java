package com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoicetemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoice.CarrierInvoiceActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;

public class UsageTabDetailImpl extends PSAcceptanceTest
{
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> usgExcel = null;
	protected Map<String, String> usgMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String usage;
	protected String start;
	protected String end;
	protected String sheetNameUsg;
	protected String startesFrom;
	protected String column;
	protected String verticalOffset;
	protected String horitontalOffset;
	protected String verticalOffsetVal;
	protected String horitontalOffsetVal;
	protected String subTotal;
	protected String skipLines;
	protected String useInlineCompute;
	protected String subtotalStart;
	protected String columnSubtotal;
	protected String verticalOffsetSubTotal;
	protected String horitontalOffsetSubTotal;
	protected String systemFieldList;
	protected String verticalOffsetSubTotalVal;
	protected String horitontalOffsetSubTotalVal;
	protected String includeInTotal;
	protected String crossFxRate;
	protected String blankLines;
	protected String noOfBlankLines;
	protected String invoiceFieldUsg;
	protected String systemFeildUsg;
	protected String cellUsg;
	protected String fieldTypeUsg;
	protected String mandatoryUsg;
	protected String mappingUsg;
	protected String searchableUsg;
	protected String defaultValUsg;
	protected String fieldMappingUsg;
	protected String dataTypeUsg;
	protected String gridColumnsUsg;
	protected String[] invoiceFieldUsgArr;
	protected String[] systemFeildUsgArr;
	protected String[] cellUsgArr;
	protected String[] fieldTypeUsgArr;
	protected String[] mandatoryUsgArr;
	protected String[] mappingUsgArr;
	protected String[] searchableUsgArr;
	protected String[] defaultValUsgArr;
	protected String[] fieldMappingUsgArr;
	protected String[] dataTypeUsgArr;
	PSStringUtils strObj = new PSStringUtils();
	protected Map<String, String> map;
	CarrierInvoiceTemplateActionImpl ciActionObj = new CarrierInvoiceTemplateActionImpl();
	PSGenericHelper genericObj = new PSGenericHelper();

	public UsageTabDetailImpl( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		excelData = new ExcelReader();
		usgExcel = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( usgExcel );
		colSize = excelHolderObj.totalColumns();

	}

	public void usagTabConfiguration( String tabName ) throws Exception
	{
		for ( paramVal = 0; paramVal < colSize; paramVal++ ) //break into methods
		{
			usgMap = excelHolderObj.dataMap( paramVal );
			initialiseVariables( usgMap );
			initializeArray();
			if ( tabName.contentEquals( "Usage" ) )			
				tabConfigs( "usageTabName", "addUsageTab", "usage_", paramVal );
			
			if ( tabName.contentEquals( "Non-Usage" ) )
				tabConfigs( "nonUsageTabName", "addNonUsageTab", "non_usage_", paramVal );
			
			if ( tabName.contentEquals( "Credit Note" ) )			
				tabConfigs( "creditNoteTabName", "addCreditNoteTab", "credit_note_", paramVal );
			
			if ( !gridColumnsUsg.isEmpty() )
				gridColumnsValidation();

		}
	}

	public void tabConfigs(String tabName, String tabBtnID, String mappingGridID, int paramVal) throws Exception
	{
		usageBasicDetails( tabName, tabBtnID );
		usageDetailConfig();
		int tabNo = paramVal + 1;
		String gridID = mappingGridID + tabNo;
		mappingConfig( gridID );
	}
	public void usageBasicDetails( String tabID, String tabBtnID ) throws Exception
	{
		TextBoxHelper.type( tabID, usage );
		ButtonHelper.click( tabBtnID );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ciActionObj.switchToTab( usage );

	}

	public void usageDetailConfig() throws Exception
	{
		TextBoxHelper.type( "PS_CITemplate_usageTab_sheetName_txtId", sheetNameUsg );
		TextBoxHelper.type( "PS_CITemplate_usgaeTab_start_txtID", start );
		TextBoxHelper.type( "PS_CITemplate_usageTab_end_txtID", end );
		TextBoxHelper.type( "PS_CITemplate_usageTab_startsFrom_txtID", startesFrom );

		TextBoxHelper.type( "PS_CITemplate_usgaeTab_column_txtID", column );
		TextBoxHelper.type( "PS_CITemplate_usgae_VeritalOffSet_txtID", verticalOffset );
		TextBoxHelper.type( "PS_CITemplate_usgae_horizontalOFfset_txtID", horitontalOffset );
		ComboBoxHelper.select( "PS_CITemplate_usage_verticalOffsetVal_comboID", verticalOffsetVal );
		ComboBoxHelper.select( "PS_CITemplate_usage_horizontalOffsetVal_comboID", horitontalOffsetVal );
		TextBoxHelper.type( "PS_CITemplate_usage_skiplines_txtID", skipLines );
		ComboBoxHelper.select( "PS_CITemplate_usage_inlineCompute_comboID", useInlineCompute );

		if ( ValidationHelper.isTrue( subTotal ) )
		{
			CheckBoxHelper.check( "PS_CITemplate_usgae_subtotal_checkbx" );
			TextBoxHelper.type( "PS_CITemplate_subtotalstart_txtID", subtotalStart );

			TextBoxHelper.type( "PS_CITemplate_columnsubtotal_txtID", columnSubtotal );
			TextBoxHelper.type( "PS_CITemplate_veriticalOffset_subtotal_txtID", verticalOffsetSubTotal );
			TextBoxHelper.type( "PS_CITemplate_horizontalOffset_subtotal_txtID", horitontalOffsetSubTotal );
			ComboBoxHelper.select( "PS_CITemplate_verticalOffset_subtotalal_comboID", verticalOffsetSubTotalVal );
			ComboBoxHelper.select( "PS_CITemplate_horizontalOffset_subtotal_comboID", horitontalOffsetSubTotalVal );
		}

		ComboBoxHelper.select( "PS_CITemplate_systemFieldList_comboID", systemFieldList );

		if ( ValidationHelper.isFalse( includeInTotal ) )
			CheckBoxHelper.uncheck( "PS_CITemplate_includeinTotal_chckbx" );
		if ( ValidationHelper.isFalse( crossFxRate ) )
			CheckBoxHelper.uncheck( "PS_CITemplate_crossFXRate_chckbox" );
		if ( ValidationHelper.isTrue( blankLines ) )
		{
			CheckBoxHelper.check( "PS_CITemplate_blanklines_chckbx" );
			TextBoxHelper.type( "PS_CITemplate_noOfBalnkLine_txtID", noOfBlankLines );
		}
	}

	public void mappingConfig( String gridID ) throws Exception
	{
		for ( int row = 0; row < cellUsgArr.length; row++ )
		{
			
			int rowVal = GridHelper.getRowCount( gridID );
			if ( systemFieldList.isEmpty() )
			{			
				
				ButtonHelper.click( "PS_CITemplate_mappingGrid_addbtnID" ); //check ifrow is 0
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				if ( ValidationHelper.isNotEmpty( invoiceFieldUsgArr[row] ) )
					GridHelper.updateGridTextBox( gridID, "PS_CITemplate_usg_invoiceFiled_txtID", row + 1, "Invoice Field", invoiceFieldUsgArr[row] );
				mappingGridDetailConfig( gridID, row + 1, row );
				

			}
			else if(!systemFieldList.isEmpty())
			{
				systemFieldListMappingassertions(gridID);
				int rowno = GridHelper.getRowNumber( gridID, invoiceFieldUsgArr[row], "Invoice Field" );// verify UI value also, seperate method
				if ( rowno != 0 )
					mappingGridDetailConfig( gridID, rowno, row );
				else
				{
					ButtonHelper.click( "PS_CITemplate_mappingGrid_addbtnID" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					if ( ValidationHelper.isNotEmpty( invoiceFieldUsgArr[row] ) )
						GridHelper.updateGridTextBox( gridID, "PS_CITemplate_usg_invoiceFiled_txtID", row + 1, "Invoice Field", invoiceFieldUsgArr[row] );
					mappingGridDetailConfig( gridID, row + 1, row );

				}
			}
		}
	}

	
	public void systemFieldListMappingassertions(String gridID) throws Exception
	{
		ArrayList<String> systemfieldlst = GridHelper.getColumnValues( gridID, "System Field" );
		ArrayList<String> invoiceFieldlst = GridHelper.getColumnValues( gridID, "Invoice Field" );
		String systemFieldActual = strObj.stringformation( systemfieldlst );
		String invoiceFieldActual = strObj.stringformation( invoiceFieldlst );
		ArrayList<String > uiVal = new ArrayList<String>();
		uiVal.add( systemFieldActual );
		uiVal.add( invoiceFieldActual );
		
		List<String > systemFieldExcelVal = Arrays.asList(systemFeildUsgArr);
		List<String > invoicFieldExcelVal = Arrays.asList(invoiceFieldUsgArr);
		String systemFieldExcel = strObj.stringformation( systemFieldExcelVal );
		String invoiceExcel = strObj.stringformation( invoicFieldExcelVal );
		ArrayList<String > excelVal = new ArrayList<String>();
		excelVal.add( systemFieldExcel );
		excelVal.add( invoiceExcel );
		for (int j =0;j<uiVal.size();j++)
		{
			String uiValue = uiVal.get( j );
			String excelValue = excelVal.get( j );
			if(excelValue.contains( uiValue ))
				Log4jHelper.logInfo( "Actual value : " + uiValue );
				Log4jHelper.logInfo( "expected value : " + excelValue );
				Log4jHelper.logInfo( "System field list and Invoice Field is validated successfully" );
		}
		
	}
	private void mappingGridDetailConfig( String gridID, int rowno, int row ) throws Exception //usageGrid
	{

		if ( ValidationHelper.isNotEmpty( cellUsg ) && ValidationHelper.isNotEmpty( cellUsgArr[row] ) )
			GridHelper.updateGridTextBox( gridID, "PS_CITemplate_usg_cell_txtID", rowno, "Cell", cellUsgArr[row] );
		if ( ValidationHelper.isNotEmpty( fieldTypeUsg ) && ValidationHelper.isNotEmpty( fieldTypeUsgArr[row] ) )
			GridHelper.updateGridComboBox( gridID, "PS_CITemplate_usg_fieldType_comboID", rowno, "Field Type", fieldTypeUsgArr[row] );
		if ( ValidationHelper.isNotEmpty( mandatoryUsg ) && ValidationHelper.isTrue( mandatoryUsgArr[row] ) )
			GridHelper.updateGridCheckBox( gridID, rowno, "Mandatory", mandatoryUsgArr[row] );
		if ( ValidationHelper.isNotEmpty( mappingUsg ) && ValidationHelper.isTrue( mappingUsgArr[row] ) )
			GridHelper.updateGridCheckBox( gridID, rowno, "Mapping", mappingUsgArr[row] );
		if ( ValidationHelper.isTrue( searchableUsg ) && ValidationHelper.isTrue( searchableUsgArr[row] ) )
			GridHelper.updateGridCheckBox( gridID, rowno, "Searchable", searchableUsgArr[row] );

		if ( ValidationHelper.isNotEmpty( fieldMappingUsg ) || ValidationHelper.isNotEmpty( dataTypeUsg ) )
			advanceOptionConfig( rowno, gridID, fieldMappingUsgArr[row], dataTypeUsgArr[row] , "PS_CITemplate_usg_cell_txtID");
		if ( ValidationHelper.isNotEmpty( defaultValUsg ) && ValidationHelper.isNotEmpty( defaultValUsgArr[row] ) && defaultValUsgArr[row].contains( "Band" ))
			defaultValueBand( rowno, gridID, fieldMappingUsgArr[row], dataTypeUsgArr[row], defaultValUsgArr[row] );
		if ( ValidationHelper.isNotEmpty( defaultValUsg ) && ValidationHelper.isNotEmpty( defaultValUsgArr[row] ) && defaultValUsgArr[row].contains( "Usage" ))
			defaultValueBilledUsage( rowno, gridID, fieldMappingUsgArr[row], dataTypeUsgArr[row], defaultValUsgArr[row] );
	}

	private void advanceOptionConfig( int row, String gridID, String fieldMapping, String dataTypeUsg , String textboxID) throws Exception
	{
		GridHelper.clickRow( gridID, row, "System Field" );
		GenericHelper.waitForLoadmask();
		if(!TextBoxHelper.isPresent(textboxID))
			GridHelper.clickRow( gridID, row, "Cell" );
		ButtonHelper.click( "PS_CITemplate_advanceOption_btnID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "Advance Option" );
		if(!fieldMapping.isEmpty())
			PSEntityComboHelper.selectUsingGridFilterTextBox( "invFieldMapping", "Field Mapping Search", "PS_CITemplate_advanceOptionTargetField_txtID", fieldMapping, "Target Field" );
		ComboBoxHelper.select( "PS_CITemplate_advanceOtptionDataType_comboID", dataTypeUsg );
		ButtonHelper.click( "PS_CITemplate_advanceOption_okBtnID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	private void defaultValueBand( int row, String gridID, String fieldMappingUsg, String dataTypeUsg, String defaultVal ) throws Exception
	{
		GridHelper.clickRow( gridID, row, "System Field" );
		GenericHelper.waitForLoadmask();
		ButtonHelper.click( "PS_CITemplate_defaultVal_btnID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "Default Value" );
		PSEntityComboHelper.selectUsingGridFilterTextBox( "Default Value", "Band Search", "bndName", defaultVal, "Name" );
		assertEquals( EntityComboHelper.getValue( "invFieldMapping" ), fieldMappingUsg );
		assertEquals( ComboBoxHelper.getValue( "PS_CITemplate_advanceOtptionDataType_comboID" ), dataTypeUsg );
		ButtonHelper.click( "PS_CITemplate_defaultal_okBtnID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}
	
	private void defaultValueBilledUsage( int row, String gridID, String fieldMappingUsg, String dataTypeUsg, String defaultVal ) throws Exception
	{
		GridHelper.clickRow( gridID, row, "System Field" );
		GenericHelper.waitForLoadmask();
		ButtonHelper.click( "PS_CITemplate_defaultVal_btnID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "Default Value" );
		/*EntityComboHelper.selectUsingGridFilterTextBox( "Default Value", "Band Search", "bndName", defaultVal, "Name" );
		assertEquals( EntityComboHelper.getValue( "invFieldMapping" ), fieldMappingUsg );
		assertEquals( ComboBoxHelper.getValue( "PS_CITemplate_advanceOtptionDataType_comboID" ), dataTypeUsg );*/
		TextBoxHelper.type( "Default Value", defaultVal );
		ButtonHelper.click( "PS_CITemplate_defaultal_okBtnID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	/*
	 * this method is to validate internal grids
	 */
	private void gridColumnsValidation() throws Exception
	{

		ArrayList<String> excelColumnNames = new ArrayList<String>();
		String[] searchGridColumnsArr = strObj.stringSplitFirstLevel( gridColumnsUsg );
		for ( int col = 0; col < searchGridColumnsArr.length; col++ )
		{
			excelColumnNames.add( searchGridColumnsArr[col] );
		}
		String excelColName = excelColumnNames.get( 0 );
		if ( excelColName.isEmpty() )
			excelColumnNames.remove( 0 );
		genericObj.totalColumns( excelColumnNames, "usageGrid", "grid_column_header_undefined_" );
	}

	public void initializeArray() throws Exception
	{
		invoiceFieldUsgArr = strObj.stringSplitFirstLevel( invoiceFieldUsg );
		systemFeildUsgArr = strObj.stringSplitFirstLevel( systemFeildUsg );
		cellUsgArr = strObj.stringSplitFirstLevel( cellUsg );
		fieldTypeUsgArr = strObj.stringSplitFirstLevel( fieldTypeUsg );
		mandatoryUsgArr = strObj.stringSplitFirstLevel( mandatoryUsg );
		mappingUsgArr = strObj.stringSplitFirstLevel( mappingUsg );
		searchableUsgArr = strObj.stringSplitFirstLevel( searchableUsg );
		defaultValUsgArr = strObj.stringSplitFirstLevel( defaultValUsg );
		fieldMappingUsgArr = strObj.stringSplitFirstLevel( fieldMappingUsg );
		dataTypeUsgArr = strObj.stringSplitFirstLevel( dataTypeUsg );
	}

	public void initialiseVariables( Map<String, String> map ) throws Exception
	{

		usage = ExcelHolder.getKey( map, "Usage" );
		start = ExcelHolder.getKey( map, "Start" );
		end = ExcelHolder.getKey( map, "End" );
		sheetNameUsg = ExcelHolder.getKey( map, "SheetNameUsg" );
		startesFrom = ExcelHolder.getKey( map, "StartesFrom" );
		column = ExcelHolder.getKey( map, "Column" );
		verticalOffset = ExcelHolder.getKey( map, "VerticalOffset" );
		horitontalOffset = ExcelHolder.getKey( map, "HoritontalOffset" );
		verticalOffsetVal = ExcelHolder.getKey( map, "VerticalOffsetVal" );
		horitontalOffsetVal = ExcelHolder.getKey( map, "HoritontalOffsetVal" );
		subTotal = ExcelHolder.getKey( map, "SubTotal" );
		skipLines = ExcelHolder.getKey( map, "SkipLines" );
		useInlineCompute = ExcelHolder.getKey( map, "UseInlineCompute" );
		subtotalStart = ExcelHolder.getKey( map, "SubtotalStart" );
		columnSubtotal = ExcelHolder.getKey( map, "ColumnSubtotal" );
		verticalOffsetSubTotal = ExcelHolder.getKey( map, "VerticalOffsetSubTotal" );
		horitontalOffsetSubTotal = ExcelHolder.getKey( map, "HoritontalOffsetSubTotal" );
		systemFieldList = ExcelHolder.getKey( map, "SystemFieldList" );
		verticalOffsetSubTotalVal = ExcelHolder.getKey( map, "VerticalOffsetSubTotalVal" );
		horitontalOffsetSubTotalVal = ExcelHolder.getKey( map, "HoritontalOffsetSubTotalVal" );
		includeInTotal = ExcelHolder.getKey( map, "IncludeInTotal" );
		crossFxRate = ExcelHolder.getKey( map, "CrossFxRate" );
		blankLines = ExcelHolder.getKey( map, "BlankLines" );
		noOfBlankLines = ExcelHolder.getKey( map, "NoOfBlankLines" );
		invoiceFieldUsg = ExcelHolder.getKey( map, "InvoiceFieldUsg" );
		systemFeildUsg = ExcelHolder.getKey( map, "SystemFeildUsg" );
		cellUsg = ExcelHolder.getKey( map, "CellUsg" );
		fieldTypeUsg = ExcelHolder.getKey( map, "FieldTypeUsg" );
		mandatoryUsg = ExcelHolder.getKey( map, "MandatoryUsg" );
		mappingUsg = ExcelHolder.getKey( map, "MappingUsg" );
		searchableUsg = ExcelHolder.getKey( map, "SearchableUsg" );
		defaultValUsg = ExcelHolder.getKey( map, "DefaultValUsg" );
		fieldMappingUsg = ExcelHolder.getKey( map, "FieldMappingUsg" );
		dataTypeUsg = ExcelHolder.getKey( map, "DataTypeUsg" );
		gridColumnsUsg = ExcelHolder.getKey( map, "GridColumnsUsg" );
	}
}
