package com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoicetemplate;

import java.util.ArrayList;
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
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;

public class ManualCITemplateTabDetailsImpl extends PSAcceptanceTest
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
	protected String partition;
	protected String systemFieldList;
	protected String templateType;
	protected String templateName;
	protected String usage;
	protected String tableInstance;
	protected String includeInTotal;
	protected String inlineComponent;
	protected String column;
	protected String invoiceField;
	protected String systemField;
	protected String fieldType;
	protected String mandatory;	
	protected String searchable;
	protected String defaultValUsg;
	protected String fieldMapping;
	protected String dataType;
	protected String gridColumnsUsg;
	protected String[] columnArr;
	protected String[] invoiceFieldArr;
	protected String[] systemFieldArr;
	protected String[] fieldTypeArr;
	protected String[] mandatoryArr;	
	protected String[] searchableArr;
	protected String[] defaultValUsgArr;
	protected String[] fieldMappingArr;
	protected String[] dataTypeArr;
	PSStringUtils strObj = new PSStringUtils();
	protected Map<String, String> map;
	CarrierInvoiceTemplateActionImpl ciActionObj = new CarrierInvoiceTemplateActionImpl();
	PSGenericHelper genericObj = new PSGenericHelper();

	public ManualCITemplateTabDetailsImpl( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		excelData = new ExcelReader();
		usgExcel = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( usgExcel );
		colSize = excelHolderObj.totalColumns();

	}
	
	public void newCarrierInvoiceManualTemplate(String partition, String templateType, String templateName) throws Exception
	{
		NavigationHelper.navigateToAction( "Common Tasks", "New", templateType );
		if(!partition.isEmpty())
			NavigationHelper.selectPartition(partition);
		GenericHelper.waitForLoadmask(detailScreenWaitSec);
		Thread.sleep(1000);
		assertEquals( NavigationHelper.getScreenTitle(), "New Manual Invoice Template" );
		TextBoxHelper.type( "pintName", templateName );
	}

	public void usagTabConfiguration( String tabName ) throws Exception
	{
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			usgMap = excelHolderObj.dataMap( paramVal );
			initialiseVariables( usgMap );
			initializeArray();
			if ( tabName.contentEquals( "Usage" ) )				
				tabConfigs( "usageTabName", "addUsageTab", "usage_", paramVal );
			
			if ( tabName.contentEquals( "Non-Usage" ) )			
				tabConfigs("nonUsageTabName", "addNonUsageTab", "non_usage_", paramVal );
			
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
		if(!tableInstance.isEmpty())
			PSEntityComboHelper.selectUsingGridFilterTextBox( "inputTableInst", "Table Instance Search", "tinDisplayName", tableInstance, "Display Name" );
		else
			ComboBoxHelper.select( "systemFieldList_gwt_uid_", systemFieldList );
		
		if(ValidationHelper.isFalse( includeInTotal ))
			CheckBoxHelper.uncheck( "ptdpIncludeInTotal_InputElement" );
		
		ComboBoxHelper.select( "component_gwt_uid_", inlineComponent );
	}
	
	
	
	public void mappingConfig( String gridID ) throws Exception
	{
		for ( int row = 0; row < columnArr.length; row++ )
		{
			if ( systemFieldList.isEmpty() )
			{
				ButtonHelper.click( "usageToolbar.add" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				GridHelper.updateGridComboBox( gridID, "columnCombo_gwt_uid_", row+1, "Column", columnArr[row] );
				GridHelper.updateGridTextBox( gridID, "uFieldEditor", row + 1, "Invoice Field", invoiceFieldArr[row] );	
				mappingGridDetailConfig( gridID, row + 1, row );

			}
			else
			{
				int rowno = GridHelper.getRowNumber( gridID, invoiceFieldArr[row], "Invoice Field" );
				if ( rowno != 0 )
				{
					assertEquals( GridHelper.getCellValue( gridID, rowno, "Column" ), columnArr[row] );
					assertEquals( GridHelper.getCellValue( gridID, rowno, "Invoice Field" ), invoiceFieldArr[row] );
					assertEquals( GridHelper.getCellValue( gridID, rowno, "System Field" ), systemFieldArr[row] );
				
					mappingGridDetailConfig( gridID, rowno, row );
				}
				
			}
		}
	}

	private void mappingGridDetailConfig( String gridID, int rowno, int row ) throws Exception //usageGrid
	{
				
			GridHelper.updateGridComboBox( gridID, "fieldColumnCombo_gwt_uid_", rowno, "Field Type", fieldTypeArr[row] );
		if ( ValidationHelper.isNotEmpty( mandatory ) && ValidationHelper.isTrue( mandatoryArr[row] ) )
			GridHelper.updateGridCheckBox( gridID, rowno, "Mandatory", mandatoryArr[row] );
		
		if ( ValidationHelper.isTrue( searchable ) && ValidationHelper.isTrue( searchableArr[row] ) )
			GridHelper.updateGridCheckBox( gridID, rowno, "Searchable", searchableArr[row] );

		if (ValidationHelper.isNotEmpty( fieldMapping ) && ValidationHelper.isNotEmpty( dataType ) )
			advanceOptionConfig( rowno, gridID, fieldMappingArr[row], dataTypeArr[row] ,"fieldColumnCombo_gwt_uid_");
		}

	
	private void advanceOptionConfig( int row, String gridID, String fieldMapping, String dataTypeUsg , String comboID) throws Exception
	{
		GridHelper.clickRow( gridID, row, "Field Type" );
		GenericHelper.waitForLoadmask();	
		if (!ComboBoxHelper.isPresent(comboID))
			GridHelper.clickRow( gridID, row, "Invoice Field" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "PS_CITemplate_advanceOption_btnID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		//assertEquals( NavigationHelper.getScreenTitle(), "Advance Option" );
		String entityXpath = "//*[@id='advanceOptionDetail']//div[contains(@id,'trigger')]";
		if(!ElementHelper.isElementPresent( entityXpath))
			ElementHelper.waitForElement( entityXpath, 120 );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		PSEntityComboHelper.selectUsingGridFilterTextBox( "invFieldMapping", "Field Mapping Search", "PS_CITemplate_advanceOptionTargetField_txtID", fieldMapping, "Target Field" );
		ComboBoxHelper.select( "PS_CITemplate_advanceOtptionDataType_comboID", dataTypeUsg );
		ButtonHelper.click( "PS_CITemplate_advanceOption_okBtnID" );
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

	public void saveCarrierInvoiceTemplate(String templateName) throws Exception
	{
		//ButtonHelper.click( "manualInvoiceTemplateDetail.save" );
		Thread.sleep(1000);
		genericObj.detailSave( "manualInvoiceTemplateDetail.save", templateName, "Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		/*
		assertTrue( GridHelper.isValuePresent( "SearchGrid", templateName, "Name" ), "Carrier Invoice Manual Template is not saved" );*/
		Log4jHelper.logInfo( "Carrier Invoice Template is saved suceessfully for :"+templateName );
	}
	public void initializeArray() throws Exception
	{
		columnArr = strObj.stringSplitFirstLevel( column );
		invoiceFieldArr = strObj.stringSplitFirstLevel( invoiceField );
		systemFieldArr = strObj.stringSplitFirstLevel( systemField );
		fieldTypeArr = strObj.stringSplitFirstLevel( fieldType );
		mandatoryArr = strObj.stringSplitFirstLevel( mandatory );		
		searchableArr = strObj.stringSplitFirstLevel( searchable );
		defaultValUsgArr = strObj.stringSplitFirstLevel( defaultValUsg );
		fieldMappingArr = strObj.stringSplitFirstLevel( fieldMapping );
		dataTypeArr = strObj.stringSplitFirstLevel( dataType );
	}

	public void initialiseVariables( Map<String, String> map ) throws Exception
	{

		systemFieldList = ExcelHolder.getKey( map, "SystemFieldList" );
		//templateType = ExcelHolder.getKey( map, "TemplateType" );
		//templateName = ExcelHolder.getKey( map, "TemplateName" );
		usage = ExcelHolder.getKey( map, "Usage" );
		tableInstance = ExcelHolder.getKey( map, "TableInstance" );
		includeInTotal = ExcelHolder.getKey( map, "IncludeInTotal" );
		inlineComponent = ExcelHolder.getKey( map, "InlineComponent" );
		column = ExcelHolder.getKey( map, "Column" );
		invoiceField = ExcelHolder.getKey( map, "InvoiceField" );
		systemField = ExcelHolder.getKey( map, "SystemField" );
		fieldType = ExcelHolder.getKey( map, "FieldType" );
		mandatory = ExcelHolder.getKey( map, "Mandatory" );	
		searchable = ExcelHolder.getKey( map, "Searchable" );
		defaultValUsg = ExcelHolder.getKey( map, "DefaultValUsg" );
		fieldMapping = ExcelHolder.getKey( map, "FieldMapping" );
		dataType = ExcelHolder.getKey( map, "DataType" );
		gridColumnsUsg = ExcelHolder.getKey( map, "GridColumnsUsg" );
		
		
	}
}
