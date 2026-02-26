package com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoicetemplate;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoice.CarrierInvoiceActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.RadioHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class SummaryDetailImpl extends PSAcceptanceTest
{
	protected String sheetName;
	protected String extractBillProfile;
	protected String directBillProfile;
	protected String derivedBillProfile;
	protected String invoiceField;
	protected String systemField;
	protected String fromFileName;
	protected String mandatory;
	protected String cell;
	protected String excelVal;
	protected String cellSummary;
	protected String gridColumnsSummary;
	protected String dataType;
	PSStringUtils strObj = new PSStringUtils();
	protected Map<String, String> map;
	PSGenericHelper genericObj = new PSGenericHelper();
	CarrierInvoiceTemplateActionImpl ciActionObj = new CarrierInvoiceTemplateActionImpl();

	public SummaryDetailImpl( Map<String, String> map ) throws Exception
	{
		this.map = map;
		initialiseVariables( map );
	}

	public void summaryTabConfig() throws Exception
	{
		summaryBasicConfig();
		if(!gridColumnsSummary.isEmpty())
			gridColumnsValidation();
		if(!invoiceField.isEmpty())
			summaryDetailConfig();
	}
	private void summaryBasicConfig() throws Exception //protected 
	{

		ciActionObj.switchToTab( "Summary" );
		TextBoxHelper.type( "PS_CITemplate_sheetName_txtID", sheetName );
		if ( ValidationHelper.isFalse( directBillProfile ) )
			
			CheckBoxHelper.uncheck( "PS_CITemplate_directBillProfile_chckBx_id" );
		else if ( ValidationHelper.isTrue( derivedBillProfile ) )
		{
			CheckBoxHelper.check( "PS_CITemplate_derivedbillProfile_chckbx_id" );
			if ( ValidationHelper.isNotEmpty( extractBillProfile ) )
				RadioHelper.click( "PS_CITemplate_extractBillProfile" );
				//CheckBoxHelper.check( "PS_CITemplate_extractBillProfile" );//radiohelper
			ComboBoxHelper.select( "invSummaryDfn_component_gwt_uid_", extractBillProfile );
		}
	}

	private void summaryDetailConfig() throws Exception
	{

		String[] invoiceFieldArr = strObj.stringSplitFirstLevel( invoiceField );
		String[] systemFieldArr = strObj.stringSplitFirstLevel( systemField );
		String[] mandatoryArr = strObj.stringSplitFirstLevel( mandatory );
		String[] cellArr = strObj.stringSplitFirstLevel( cellSummary );
		String[] excelValArr = strObj.stringSplitFirstLevel( excelVal );
		String[] dataTypeArr = strObj.stringSplitFirstLevel( dataType );
		String[] systemFieldValArr = {"Bill Profile","Currency","From Date","Invoice Date","Invoice Due Date","Invoice Number","Net Charges","Reference","To Date","Total Amount"};
		String[] systemFieldVal1Arr = {"Account","Bill Profile","Currency","From Date","Invoice Date","Invoice Due Date","Invoice Number","Net Charges","Reference","To Date","Total Amount"};
		
	
		for(int i=0; i < systemFieldArr.length;i++)
		{
			if(ValidationHelper.isTrue( derivedBillProfile ))
				assertEquals( GridHelper.getCellValue( "PS_CITemplate_summary_GridID", i+1, "System Field" ), systemFieldVal1Arr[i] );
			else
				assertEquals( GridHelper.getCellValue( "PS_CITemplate_summary_GridID", i+1, "System Field" ), systemFieldValArr[i] );
		}
		for ( int row = 0; row < invoiceFieldArr.length; row++ )
		{
			
			int rowVal = GridHelper.getRowNumber( "PS_CITemplate_summary_GridID", systemFieldArr[row], "System Field" );
			if ( ValidationHelper.isNotEmpty( invoiceFieldArr[row] ) )
				GridHelper.updateGridTextBox( "PS_CITemplate_summary_GridID", "PS_CITemplate_summaryInvoiceField_txtID", rowVal, "Invoice Field", invoiceFieldArr[row] );
			if ( ValidationHelper.isNotEmpty( mandatory ) && ValidationHelper.isNotEmpty( mandatoryArr[row] ) )
				GridHelper.updateGridCheckBox( "PS_CITemplate_summary_GridID", rowVal, "Mandatory", mandatoryArr[row] );
			if ( ValidationHelper.isNotEmpty( cellArr[row] ) )
				GridHelper.updateGridTextBox( "PS_CITemplate_summary_GridID", "PS_CITemplate_summaryCell_txtID", rowVal, "Cell", cellArr[row] );
			if ( ValidationHelper.isNotEmpty( dataType ) && ValidationHelper.isNotEmpty( dataTypeArr[row] ) )
				sourceFieldTypeAction( rowVal, systemFieldArr[row], dataTypeArr[row] );
		}
	}

	private void sourceFieldTypeAction( int row, String systemField, String dataType ) throws Exception
	{

		GridHelper.clickRow( "PS_CITemplate_summary_GridID", row, "System Field" );
	//	NavigationHelper.navigateToAction( "Source Field Type" );
		ButtonHelper.click( "summaryToolbar.sourceFieldType" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "Source field type" );
		assertEquals( TextBoxHelper.getValue( "PS_CITemplate_summarySystemField_txtID" ), systemField );
		ComboBoxHelper.select( "PS_CITemplate_summaryDataType_comboID", dataType );
		ButtonHelper.click( "PS_CITemplate_summary_advanceOptions_okbtn" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
	}
	
	/*
	 * this method is to validate internal grids
	 */
	private void gridColumnsValidation() throws Exception
	{

		ArrayList<String> excelColumnNames = new ArrayList<String>();
		String[] searchGridColumnsArr = strObj.stringSplitFirstLevel( gridColumnsSummary );
		for ( int col = 0; col < searchGridColumnsArr.length; col++ )
		{
			excelColumnNames.add( searchGridColumnsArr[col] );
		}
		genericObj.totalColumns( excelColumnNames, "summaryGrid", "grid_column_header_undefined_" );
	}

	public void initialiseVariables( Map<String, String> map ) throws Exception
	{

		sheetName = ExcelHolder.getKey( map, "SheetName" );
		extractBillProfile = ExcelHolder.getKey( map, "ExtractBillProfile" );
		directBillProfile = ExcelHolder.getKey( map, "DirectBillProfile" );
		derivedBillProfile = ExcelHolder.getKey( map, "DerivedBillProfile" );
		invoiceField = ExcelHolder.getKey( map, "InvoiceField" );
		systemField = ExcelHolder.getKey( map, "SystemField" );
		mandatory = ExcelHolder.getKey( map, "Mandatory" );
		fromFileName = ExcelHolder.getKey( map, "FromFileName" );
		cellSummary = ExcelHolder.getKey( map, "CellSummary" );
		excelVal = ExcelHolder.getKey( map, "ExcelVal" );
		dataType = ExcelHolder.getKey( map, "DataType" );
		gridColumnsSummary = ExcelHolder.getKey( map, "GridColumnsSummary" );
	}
}
