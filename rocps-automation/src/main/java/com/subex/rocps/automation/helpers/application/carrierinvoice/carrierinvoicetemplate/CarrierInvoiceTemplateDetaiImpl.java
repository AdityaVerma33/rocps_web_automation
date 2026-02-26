package com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoicetemplate;

import java.awt.AWTException;
import java.net.MalformedURLException;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;

public class CarrierInvoiceTemplateDetaiImpl extends PSAcceptanceTest
{
	protected String partition;
	protected String templateType;
	protected String templateName;
	protected String ciFileName;
	protected String dateFormat;
	protected String thousandSeperator;
	protected String decimalSeperator;
	protected String expression;
	protected String matchString;
	protected String fileName;
	protected String cell;
	protected String alias;	
	PSStringUtils strObj = new PSStringUtils();
	protected Map<String, String> map;
	CarrierInvoiceTemplateActionImpl ciActionObj = new CarrierInvoiceTemplateActionImpl();
	public CarrierInvoiceTemplateDetaiImpl( Map<String, String> map ) throws Exception
	{
		this.map = map;
		initialiseVariables( map );		
	}
	
	public void newCarrierInvoiceExcelTemplate() throws Exception
	{
		NavigationHelper.navigateToAction( "Common Tasks", "New", templateType );
		if(!partition.isEmpty())
			NavigationHelper.selectPartition(partition);
		GenericHelper.waitForLoadmask(detailScreenWaitSec);
		
	}
	
	public void basicDetailsConfig() throws Exception
	{
		TextBoxHelper.type( "PS_CITemplate_Name_txtID", templateName );
		if(!ciFileName.isEmpty())
			ciFileupload();
		ComboBoxHelper.select( "PS_CITemplate_dateFormat_comboID", dateFormat );
	}
	
	public void rulesConfig() throws Exception
	{
		String[] matchStringArr = strObj.stringSplitFirstLevel( matchString );
		String[] fileNameArr = strObj.stringSplitFirstLevel( fileName );
		String[] cellArr = strObj.stringSplitFirstLevel( cell );
		String[] aliasArr = strObj.stringSplitFirstLevel( alias );
		ciActionObj.switchToTab( "Rules" );
		
		TextBoxHelper.type( "PS_CITemplate_thousandSeperator_txtID", thousandSeperator );
		TextBoxHelper.type( "PS_CITemplate_decimalSeperator_txtID", decimalSeperator );
		
		
		for (int i=0; i < matchStringArr.length; i++)
		{
			ButtonHelper.click( "PS_CITemplate_matchString_addBtnID" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			GridHelper.updateGridTextBox( "PS_CITemplate_matchString_GridID", "PS_CITemplate_matchString_txtID", i+1, "Match String", matchStringArr[i] );
			if(ValidationHelper.isTrue( fileName ))
				GridHelper.updateGridCheckBox( "PS_CITemplate_matchString_GridID", i, "File Name", fileNameArr[i] );
			else
				GridHelper.updateGridTextBox( "PS_CITemplate_matchString_GridID", "PS_CITemplate_matchString_cell_txtID", i+1, "Cell", cellArr[i] );
			GridHelper.updateGridTextBox( "PS_CITemplate_matchString_GridID", "PS_CITemplate_matchString_alias_txtID", i+1, "Alias", aliasArr[i] );		
			
		}
		
		assertEquals(TextBoxHelper.getValue( "PS_CITemplate_expression_txtID"), expression );
	}
	
	/*
	 * This method is for CI file upload
	 */
	private void ciFileupload() throws  Exception
	{
		String filePath = automationPath + configProp.getProperty( "carrierInvoiecPath" ) + ciFileName;
		
		ButtonHelper.click( "trigger-scucFilePath" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		PSGenericHelper.psFileUploadSikuli("FileUpload_Browse", filePath);
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		ButtonHelper.click( "FileUpload-upload" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );

	}
	
	public void saveCarrierInvoiceTemplate() throws Exception
	{
		ButtonHelper.click( "excelInvoiceTemplateDetail.save" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertTrue( GridHelper.isValuePresent( "SearchGrid", templateName, "Name" ), "Carrier Invoice Template is not saved" );
		Log4jHelper.logInfo( "Carrier Invoice Template is saved suceessfully for :"+templateName );
	}
	
	
	public void initialiseVariables( Map<String, String> map ) throws Exception
	{
		partition = ExcelHolder.getKey( map, "Partition" );
		templateType = ExcelHolder.getKey( map, "TemplateType" );
		templateName = ExcelHolder.getKey( map, "TemplateName" );
		ciFileName = ExcelHolder.getKey( map, "CIFileName" );
		dateFormat = ExcelHolder.getKey( map, "DateFormat" );
		thousandSeperator = ExcelHolder.getKey( map, "ThousandSeperator" );
		decimalSeperator = ExcelHolder.getKey( map, "DecimalSeperator" );
		expression = ExcelHolder.getKey( map, "Expression" );
		matchString = ExcelHolder.getKey( map, "MatchString" );
		fileName = ExcelHolder.getKey( map, "FileName" );
		cell = ExcelHolder.getKey( map, "Cell" );
		alias = ExcelHolder.getKey( map, "Alias" );
	}

}
