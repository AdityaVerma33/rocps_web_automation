package com.subex.rocps.automation.helpers.application.carrierinvoice.fieldmapping;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;

public class FieldMappingDetailImpl extends PSAcceptanceTest
{
protected String targetField;
protected String entity;
protected String entityColumn;
protected String actualColumn;
protected String type;
protected String mandatory;
protected String mapRequired;
protected String clientPartition;
protected Map<String, String> map;
PSGenericHelper genericHelperObj = new PSGenericHelper();

public FieldMappingDetailImpl( Map<String, String> map ) throws Exception
{

	this.map = map;

	initialiseVariables( map );
	
}
public void newFiledMaping() throws Exception
{
	genericHelperObj.clickNewAction( clientPartition );
	GenericHelper.waitForLoadmask( searchScreenWaitSec );
	assertEquals( NavigationHelper.getScreenTitle(), "New Field Mapping" );
}
	
	public void configureFieldMapping() throws Exception
	{
		TextBoxHelper.type( "pifmFieldName", targetField );
		PSEntityComboHelper.selectUsingGridFilterTextBox( "entityTbl", "Entity Search", "entEntity", entity, "Entity" );
		ComboBoxHelper.select( "tableColumn_gwt_uid_", entityColumn );
		assertEquals( TextBoxHelper.getValue( "pifmTableColName" ), actualColumn );
		assertEquals( ComboBoxHelper.getValue( "pifmType_gwt_uid_" ), type );
		
		if(ValidationHelper.isTrue( mandatory ))
			CheckBoxHelper.check( "pifmIsMandatory_InputElement" );
		
		if(ValidationHelper.isTrue( mapRequired ))
			CheckBoxHelper.check( "pifmIsMapRequired_InputElement" );
	}
	
	
	public void saveFieldMapping() throws Exception
	{
		ButtonHelper.click( "invFieldMappingDetail.save" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertTrue( GridHelper.isValuePresent( "SearchGrid", targetField , "Target Field"), "Field Mapping is not saved");
		Log4jHelper.logInfo( "Field Mapping is configured successfully for :"+targetField );
	}
	
	
	public void initialiseVariables( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		targetField = ExcelHolder.getKey( map, "TargetField" );
		entity = ExcelHolder.getKey( map, "Entity" );
		entityColumn = ExcelHolder.getKey( map, "EntityColumn" );
		actualColumn = ExcelHolder.getKey( map, "ActualColumn" );
		type = ExcelHolder.getKey( map, "Type" );
		mandatory = ExcelHolder.getKey( map, "Mandatory" );
		mapRequired = ExcelHolder.getKey( map, "MapRequired" );
	}
}
