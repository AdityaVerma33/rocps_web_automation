package com.subex.rocps.automation.helpers.application.aggregation.aggregationconfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridCheckBoxHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.ImageHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.config.OR_Reader;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class AggregationFieldsSelectionDetailImpl extends PSAcceptanceTest
{

	protected String newVersionFl;
	protected String frequency;
	protected String fieldName;
	protected String customFieldName;
	protected String customFieldType;
	protected String displayName;
	protected String customFieldDisplayName;
	protected String type;
	protected String mappingValues;
	protected String entityOrFunction;
	protected String usageView;
	protected String skipAtParent;
	protected String searchableFL;
	protected String mandatoryFl;
	protected String partitionIndex;

	protected Map<String, String> map;
	protected List<String> fieldValueList = null;

	protected String[] fieldNameArr = null;
	protected String[] customFieldNameArr = null;
	protected String[] customFieldTypeArr = null;
	protected String[] displayNameArr = null;
	protected String[] customFieldDisplayNameArr = null;
	protected String[] typeArr = null;
	protected String[] mappingValuesArr = null;
	protected String[] entityOrFunctionArr = null;
	protected String[] usageViewArr = null;
	protected String[] skipAtParentArr = null;
	protected String[] searchableFLArr = null;
	protected String[] mandatoryFlArr = null;
	protected String[] partitionIndexArr = null;
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();

	public AggregationFieldsSelectionDetailImpl( Map<String, String> map ) throws Exception
	{

		this.map = map;

		initialiseVariables( map );
	}

	private void initialiseVariables( Map<String, String> map ) throws Exception
	{

		newVersionFl = ExcelHolder.getKey( map, "NewVersion" );
		frequency = ExcelHolder.getKey( map, "Frequency" );
		fieldName = ExcelHolder.getKey( map, "FieldName" );
		customFieldName = ExcelHolder.getKey( map, "CustomFieldName" );
		customFieldType = ExcelHolder.getKey( map, "CustomFieldType" );
		displayName = ExcelHolder.getKey( map, "DisplayName" );
		customFieldDisplayName = ExcelHolder.getKey( map, "CustomFieldDisplayName" );
		type = ExcelHolder.getKey( map, "Type" );
		mappingValues = ExcelHolder.getKey( map, "ValueMapping" );
		entityOrFunction = ExcelHolder.getKey( map, "Entity/Function" );
		usageView = ExcelHolder.getKey( map, "UsageView" );
		skipAtParent = ExcelHolder.getKey( map, "SkipAtParent" );
		searchableFL = ExcelHolder.getKey( map, "Searchable" );
		mandatoryFl = ExcelHolder.getKey( map, "Mandatory" );
		partitionIndex = ExcelHolder.getKey( map, "PartitionIndex" );

	}

	/*
	 * Method we are setting version flag and frequency dropdown
	 */
	public void versonAndFrequencySelection() throws Exception
	{

		if ( ValidationHelper.isTrue( newVersionFl ) )
			CheckBoxHelper.check( "PSDetail_agcNewVersion_chekBoxId" );

		ComboBoxHelper.select( "PSDetail_agcFrequency_comboId", frequency );
	}

	/*
	 * Method for configuring the grid with keys and values
	 */
	public void keyValueGridConfig() throws Exception
	{

		fieldNameArr = new PSStringUtils().stringSplitFirstLevel( fieldName );
		displayNameArr = new PSStringUtils().stringSplitFirstLevel( displayName );
		customFieldDisplayNameArr = new PSStringUtils().stringSplitFirstLevel( customFieldDisplayName );
		typeArr = new PSStringUtils().stringSplitFirstLevel( type );
		mappingValuesArr = new PSStringUtils().stringSplitFirstLevel( mappingValues );
		entityOrFunctionArr = new PSStringUtils().stringSplitFirstLevel( entityOrFunction );
		usageViewArr = new PSStringUtils().stringSplitFirstLevel( usageView );
		skipAtParentArr = new PSStringUtils().stringSplitFirstLevel( skipAtParent );
		searchableFLArr = new PSStringUtils().stringSplitFirstLevel( searchableFL );
		mandatoryFlArr = new PSStringUtils().stringSplitFirstLevel( mandatoryFl );
		partitionIndexArr = new PSStringUtils().stringSplitFirstLevel( partitionIndex );
		customFieldNameArr = new PSStringUtils().stringSplitFirstLevel( customFieldName );
		customFieldTypeArr = new PSStringUtils().stringSplitFirstLevel( customFieldType );

		List<String> aggUIColNamesList = GridHelper.getColumnValues( "PSDetail_agcKeyValue_gridId", "Field  Name" );

		fieldsConfig( aggUIColNamesList, fieldNameArr );
		customFieldConfig( aggUIColNamesList, customFieldNameArr );
		System.out.println();

	}

	/*
	 * Method for selecting fields without custom fields
	 */
	public void fieldsConfig( List<String> guiColNamesList, String[] fieldVal ) throws Exception
	{

		for ( int rowIndex = 0; rowIndex < fieldVal.length; rowIndex++ )
		{

			int configFieldRowNum;

			if ( guiColNamesList.contains( fieldVal[rowIndex] ) )
				configFieldRowNum = GridHelper.getRowNumber( "PSDetail_agcKeyValue_gridId", fieldNameArr[rowIndex] );
			else
			{
				addRow();
				configFieldRowNum = GridHelper.getRowCount( "PSDetail_agcKeyValue_gridId" );
			}

			selectRow( configFieldRowNum, rowIndex );
			selectMapFieldValues( mappingValuesArr[rowIndex] );
			GridHelper.clickRow( "PSDetail_agcKeyValue_gridId", configFieldRowNum, "Field  Name" );

			if ( !displayName.isEmpty() )
				setTypeDisplayName( displayNameArr[rowIndex], configFieldRowNum );

			if ( !type.isEmpty() )
				setKeyValType( typeArr[rowIndex], configFieldRowNum );

			String keyValConfig = GridHelper.getCellValue( "PSDetail_agcKeyValue_gridId", configFieldRowNum, "Type" );
			enityOrFunctionConfig( keyValConfig, fieldNameArr[rowIndex], configFieldRowNum );

			if ( !usageView.isEmpty() )
				usageViewConfig( keyValConfig, fieldNameArr[rowIndex], configFieldRowNum );

			if ( !skipAtParent.isEmpty() )
				setSkipAtParent( keyValConfig, fieldNameArr[rowIndex], configFieldRowNum );

			if ( !searchableFL.isEmpty() )
			{
				setSearchableFlag( keyValConfig, fieldNameArr[rowIndex], configFieldRowNum );
				setMandatoryFlag( keyValConfig, configFieldRowNum );
			}
			setPartitionIndexFlag( keyValConfig, configFieldRowNum );

		}
	}

	/*
	 * Method for configuring the custom fields
	 */
	public void customFieldConfig( List<String> guiColNamesList, String[] customFieldVal ) throws Exception
	{

		for ( int rowIndex = 0; rowIndex < customFieldVal.length; rowIndex++ )
		{

			int configFieldRowNum = 0;

			if ( guiColNamesList.contains( customFieldVal[rowIndex] ) )
			{

				configFieldRowNum = GridHelper.getRowNumber( "PSDetail_agcKeyValue_gridId", customFieldNameArr[rowIndex] );
				selectRow( configFieldRowNum, rowIndex );

				if ( !displayName.isEmpty() )
					setTypeDisplayName( customFieldDisplayNameArr[rowIndex], configFieldRowNum );

				if ( !type.isEmpty() )
					setKeyValType( customFieldTypeArr[rowIndex], configFieldRowNum );

				String keyValConfig = GridHelper.getCellValue( "PSDetail_agcKeyValue_gridId", configFieldRowNum, "Type" );
				enityOrFunctionConfig( keyValConfig, customFieldVal[rowIndex], configFieldRowNum );

				if ( !usageView.isEmpty() )
					usageViewConfig( keyValConfig, customFieldVal[rowIndex], configFieldRowNum );

				if ( !skipAtParent.isEmpty() )
					setSkipAtParent( keyValConfig, customFieldVal[rowIndex], configFieldRowNum );

				if ( !searchableFL.isEmpty() )
				{
					setSearchableFlag( customFieldTypeArr[rowIndex], customFieldVal[rowIndex], configFieldRowNum );
					setMandatoryFlag( keyValConfig, configFieldRowNum );
				}
			}

		}
	}

	/*
	 * Method for selecting the mapping values
	 */
	public void selectMapFieldValues( String mappingValue ) throws Exception
	{

		int rows = GridHelper.getRowCount( "PSDetail_agcEventType_gridId" );
		String gridColHeader = "Table  Column";
		if ( rows == 1 )
		{
			GridHelper.clickRow( "PSDetail_agcEventType_gridId", rows, gridColHeader );
			GridHelper.clickRow( "PSDetail_agcEventType_gridId", rows, gridColHeader );
			//ComboBoxHelper.select( "PSDetail_agcEventValMap_gridId", mappingValue );
			psDataComponentHelper.selectComboBoxVal( "PSDetail_agcEventValMap_gridId", mappingValue );

		}
		else
		{

			for ( int index = 1; index <= rows; index++ )
			{

				GridHelper.clickRow( "PSDetail_agcEventType_gridId", index, gridColHeader );
				GridHelper.clickRow( "PSDetail_agcEventType_gridId", index, gridColHeader );
				//ComboBoxHelper.select( "PSDetail_agcEventValMap_gridId", mappingValue );
				psDataComponentHelper.selectComboBoxVal( "PSDetail_agcEventValMap_gridId", mappingValue );

			}
		}

	}

	/*
	 * Method for selecting entity
	 */
	public void enityOrFunctionConfig( String typeVal, String functionVal, int rownum ) throws Exception
	{

		if ( Arrays.asList( entityOrFunctionArr ).contains( functionVal ) && typeVal.equals( "value" ) )
		{
			GridHelper.clickRow( "PSDetail_agcKeyValue_gridId", rownum, "Entity/ Function" );
			PSEntityComboHelper.selectUsingGridFilterTextBox( "PSDetail_agcentityOrFunction_entId", "Aggregation Function Instance Search", "PSPopUp_agcFunctionInst_gridColTxtId", "Sum", "Name" );
		}
	}

	/*
	 * Method for setting skipAtParent flag
	 */
	public void usageViewConfig( String typeVal, String usageViewComboVal, int rownum ) throws Exception
	{

		if ( typeVal.equals( "value" ) && !usageViewComboVal.isEmpty() )
		{
			GridHelper.clickRow( "PSDetail_agcKeyValue_gridId", rownum, "Usage  View" );
			ComboBoxHelper.select( "PSDetail_agcUsageView_ComboId", usageViewComboVal );
			GridHelper.clickRow( "PSDetail_agcUsageView_ComboId", rownum, "FieldName" );
		}
	}

	/*
	 * Method for setting skipAtParent flag
	 */
	public void setSkipAtParent( String typeVal, String fieldName, int rownum ) throws Exception
	{
		if ( ( typeVal.equals( "value" ) || typeVal.equals( "computed key" ) || typeVal.equals( "computed value" ) ) && Arrays.asList( skipAtParentArr ).contains( fieldName ) )
			GridHelper.clickRow( "PSDetail_agcKeyValue_gridId", rownum, "Skip  At  Parent" );
	}

	/*
	 * Method for setting searchable flag
	 */
	public void setSearchableFlag( String typeVal, String fieldName, int rownum ) throws Exception
	{

		if ( ( typeVal.equals( "value" ) || typeVal.equals( "key" ) ) && Arrays.asList( searchableFLArr ).contains( fieldName ) )
			GridHelper.clickRow( "PSDetail_agcKeyValue_gridId", rownum, "Searchable" );
	}

	/*
	 * Method for setting mandatory flag
	 */
	public void setMandatoryFlag( String fieldName, int rownum ) throws Exception
	{

		if ( Arrays.asList( searchableFLArr ).contains( fieldName ) && Arrays.asList( mandatoryFlArr ).contains( fieldName ) )
			GridHelper.clickRow( "PSDetail_agcKeyValue_gridId", rownum, "Mandatory" );

	}

	/*
	 * Method for setting parition index flag
	 */
	public void setPartitionIndexFlag( String fieldName, int rownum ) throws Exception
	{

		if ( Arrays.asList( partitionIndexArr ).contains( fieldName ) )
			GridHelper.clickRow( "PSDetail_agcKeyValue_gridId", rownum, "Partition  Index" );
	}

	/*
	 * Method for clicking add button
	 */
	public void addRow() throws Exception
	{

		ButtonHelper.click( "//div[text()='Add']" );
	}

	/*
	 * Method for seleting the row in the grid
	 */
	public void selectRow( int rowVal, int rownum ) throws Exception
	{
		if ( rownum == 0 )
			GridHelper.clickRow( "PSDetail_agcKeyValue_gridId", rowVal, "Field  Name" );
		else
		{
			GridHelper.clickRow( "PSDetail_agcKeyValue_gridId", rowVal, "Field  Name" );
			GridHelper.clickRow( "PSDetail_agcKeyValue_gridId", rowVal, "Field  Name" );
		}

	}

	/*
	 * Method to set display name for the fields configured
	 */
	public void setTypeDisplayName( String txtVal, int rownum ) throws Exception
	{

		if ( !txtVal.isEmpty() )
		{
			GridHelper.clickRow( "PSDetail_agcKeyValue_gridId", rownum, "Display  Name" );
			TextBoxHelper.type( "PSDetail_agcdisplayName_txtId", txtVal );
			GridHelper.clickRow( "PSDetail_agcKeyValue_gridId", rownum, "FieldName" );
		}
	}

	/*
	 * Method to set keyValue for the fields configured
	 */
	public void setKeyValType( String typeComboVal, int rownum ) throws Exception
	{

		if ( !typeComboVal.isEmpty() )
		{
			GridHelper.clickRow( "PSDetail_agcKeyValue_gridId", rownum, "Type" );
			ComboBoxHelper.select( "PSDetail_agctype_comboId", typeComboVal );
			GridHelper.clickRow( "PSDetail_agcKeyValue_gridId", rownum, "Field  Name" );
		}
	}

}