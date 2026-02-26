package com.subex.rocps.automation.helpers.application.matchandrate.eventmodellinginstance;

import java.util.Map;

import org.openqa.selenium.By;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

public class EventModInstDetailImpl extends PSAcceptanceTest
{

	protected Map<String, String> evenModInstDetailTabMap;
	protected String eventName;
	protected String outputPrifix;
	protected String errorPrifix;
	protected String definition;
	protected String partSubUsageGp;
	protected String errorUsageGp;
	protected String tableTypeCellValue;
	protected String crtNwInstBtnFlag;
	protected String tableDefn;
	protected String schema;
	protected String tableNm;
	protected String displNm;
	protected String alias;
	protected String partColmn;
	protected String subPartColmn;
	protected String tableTypeCellArr[];
	protected String crtNwInstBtnFlagArr[];
	protected String tableDefnArr[];
	protected String tableNmArr[];
	protected String aliasArr[];
	protected String partColmnArr[];
	protected String subPartColmnArr[];
	protected String displNmArr[];
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();

	public EventModInstDetailImpl( Map<String, String> evenModInstDetailTabMap ) throws Exception
	{

		this.evenModInstDetailTabMap = evenModInstDetailTabMap;
		initInstVariables();
	}

	//Method for initializing instance variables
	public void initInstVariables() throws Exception
	{
		eventName = evenModInstDetailTabMap.get( "Name" );
		outputPrifix = evenModInstDetailTabMap.get( "OutputPrefix" );
		errorPrifix = evenModInstDetailTabMap.get( "ErrorPrefix" );
		definition = evenModInstDetailTabMap.get( "Definition" );
		partSubUsageGp = evenModInstDetailTabMap.get( "PartiSubUsageGp" );
		errorUsageGp = evenModInstDetailTabMap.get( "ErrorUsageGp" );
		tableTypeCellValue = evenModInstDetailTabMap.get( "TableTypeCellValue" );
		crtNwInstBtnFlag = evenModInstDetailTabMap.get( "CrtNewInstBtnFlag" );
		tableDefn = evenModInstDetailTabMap.get( "TableDefinition" );
		schema = evenModInstDetailTabMap.get( "Schema" );
		tableNm = evenModInstDetailTabMap.get( "TableName" );
		displNm = evenModInstDetailTabMap.get( "DisplayName" );
		alias = evenModInstDetailTabMap.get( "Alias" );
		partColmn = evenModInstDetailTabMap.get( "PartitionColmn" );
		subPartColmn = evenModInstDetailTabMap.get( "SubPartColmn" );
		if ( ValidationHelper.isNotEmpty( tableTypeCellValue ) )
			tableTypeCellArr = psStringUtils.stringSplitFirstLevel( tableTypeCellValue );
		if ( ValidationHelper.isNotEmpty( crtNwInstBtnFlag ) )
			crtNwInstBtnFlagArr = psStringUtils.stringSplitFirstLevel( crtNwInstBtnFlag );
		if ( ValidationHelper.isNotEmpty( tableDefn ) )
			tableDefnArr = psStringUtils.stringSplitFirstLevel( tableDefn );
		if ( ValidationHelper.isNotEmpty( tableNm ) )
			tableNmArr = psStringUtils.stringSplitFirstLevel( tableNm );
		if ( ValidationHelper.isNotEmpty( displNm ) )
			displNmArr = psStringUtils.stringSplitFirstLevel( displNm );
		if ( ValidationHelper.isNotEmpty( alias ) )
			aliasArr = psStringUtils.stringSplitFirstLevel( alias );
		if ( ValidationHelper.isNotEmpty( partColmn ) )
			partColmnArr = psStringUtils.stringSplitFirstLevel( partColmn );
		if ( ValidationHelper.isNotEmpty( subPartColmn ) )
			subPartColmnArr = psStringUtils.stringSplitFirstLevel( subPartColmn );
	}

	//Method to configure EventModellingInstance detail
	public void openEventModelInstDetail() throws Exception
	{
		TextBoxHelper.type( "PS_Detail_EveModInst_name_txtID", eventName );
		TextBoxHelper.type( "PS_Detail_EveModInst_outputPrefix_txtID", outputPrifix );
		TextBoxHelper.type( "PS_Detail_EveModInst_errorPrefix_txtID", errorPrifix );
		ComboBoxHelper.select( "PS_Detail_EveModInst_definition_comboID", definition );
		GenericHelper.waitForLoadmask();
		ComboBoxHelper.select( "PS_Detail_EveModInst_panel1", "PS_Detail_EveModInst_partSubUsgGpPanel1_comboID", partSubUsageGp );
		ComboBoxHelper.select( "PS_Detail_EveModInst_panel1", "PS_Detail_EveModInst_errorUsgGp_comboID", errorUsageGp );
		GenericHelper.waitForLoadmask();
		ComboBoxHelper.select( "PS_Detail_EveModInst_panel2", "PS_Detail_EveModInst_partSubUsgGpPanel2_comboID", partSubUsageGp );
	}

	//Method to check the createInstBtnIsEnableOrNot
	public void chckCrtInstBtnEnableOrNot() throws Exception
	{
		for ( int i = 0; i < tableTypeCellArr.length; i++ )
		{
			if ( GridHelper.getRowNumber( "PS_Detail_EveModInst_panel1_GridID", tableTypeCellArr[i], "Type" ) > 0 )
			{
				GridHelper.clickRow( "PS_Detail_EveModInst_panel1_GridID", tableTypeCellArr[i], "Type" );
				GenericHelper.waitForLoadmask();
				boolean isCreateInstBtn = ButtonHelper.isEnabled( "PS_Detail_EveModInst_panel1_CrtNwInst" );
				// boolean isCreateInstBtn=ElementHelper.getElement(GenericHelper.getORProperty("PS_Detail_EveModInst_panel2_CrtNwInst")).isEnabled();
				assertEquals( String.valueOf( isCreateInstBtn ), crtNwInstBtnFlagArr[i], "Create new inst button is not as expected for given " + tableTypeCellArr[i] );
			}
			else if ( GridHelper.getRowNumber( "PS_Detail_EveModInst_panel2_GridID", tableTypeCellArr[i], "Type" ) > 0 )
			{
				GridHelper.clickRow( "PS_Detail_EveModInst_panel2_GridID", tableTypeCellArr[i], "Type" );
				GenericHelper.waitForLoadmask();
				boolean isCreateInstBtn = ButtonHelper.isEnabled( "PS_Detail_EveModInst_panel2_CrtNwInst" );
				//boolean isCreateInstBtn=ElementHelper.getElement("PS_Detail_EveModInst_panel2_CrtNwInst").isEnabled();
				assertEquals( String.valueOf( isCreateInstBtn ), crtNwInstBtnFlagArr[i], "Create new inst button is not as expected for given " + tableTypeCellArr[i] );
			}
		}
	}

	//Method to configure EventModellingInstance Table Instance
	public void configureTableInstanceDetail() throws Exception
	{

		try
		{
			for ( int i = 0; i < tableTypeCellArr.length; i++ )
			{
				if ( GridHelper.getRowNumber( "PS_Detail_EveModInst_panel1_GridID", tableTypeCellArr[i], "Type" ) > 0 )
				{
					createTableInstance( "PS_Detail_EveModInst_panel1_GridID", "PS_Detail_EveModInst_panel1_CrtNwInst", i );
				}
				else if ( GridHelper.getRowNumber( "PS_Detail_EveModInst_panel2_GridID", tableTypeCellArr[i], "Type" ) > 0 )
				{
					createTableInstance( "PS_Detail_EveModInst_panel2_GridID", "PS_Detail_EveModInst_panel2_CrtNwInst", i );
					;
				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public void createTableInstance( String gridId, String crtNwInstBtnId, int row ) throws Exception
	{
		GridHelper.clickRow( gridId, tableTypeCellArr[row], "Type" );
		ButtonHelper.click( gridId, crtNwInstBtnId );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "Table Instance" );
		//String tableDefnTxt=ElementHelper.getElement(GenericHelper.getORProperty("PS_Detail_EveModInst_TableInst_tableDefn_ComboXpath")).getText();
		String tableDefnTxt = driver.findElement( By.xpath( GenericHelper.getORProperty( "PS_Detail_EveModInst_TableInst_tableDefn_ComboXpath" ) ) ).getText();
		assertEquals( tableDefnTxt, tableDefnArr[row] );
		Log4jHelper.logInfo( "Actual Table Defn for " + tableTypeCellArr[row] + " table type is: " + tableDefnTxt + "\nExpected Table Defn: " + tableDefnArr[row] );
		ComboBoxHelper.select( "PS_Detail_EveModInst_TableInst_GridWrapper", "PS_Detail_EveModInst_TableInst_schemea_ComboID", schema );
		TextBoxHelper.type( "PS_Detail_EveModInst_TableInst_GridWrapper", "PS_Detail_EveModInst_TableInst_tableNm_txtID", tableNmArr[row] );
		TextBoxHelper.type( "PS_Detail_EveModInst_TableInst_GridWrapper", "PS_Detail_EveModInst_TableInst_displNm_txtID", displNmArr[row] );
		TextBoxHelper.type( "PS_Detail_EveModInst_TableInst_GridWrapper", "PS_Detail_EveModInst_TableInst_alias_txtID", aliasArr[row] );
		ComboBoxHelper.select( "PS_Detail_EveModInst_TableInst_GridWrapper", "PS_Detail_EveModInst_TableInst_partColmn_ComboID", partColmnArr[row] );
		ComboBoxHelper.select( "PS_Detail_EveModInst_TableInst_GridWrapper", "PS_Detail_EveModInst_TableInst_subpartColmn_ComboID", subPartColmnArr[row] );
		GenericHelper.waitForLoadmask();
		ButtonHelper.click( "PS_Detail_EveModInst_TableInst_GridWrapper", "PS_Detail_EveModInst_TableInst_Save" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "New Event Modelling Instance" );
	}

	public void saveEventModelInstance( String eventName ) throws Exception
	{
		ButtonHelper.click( "PS_Detail_EveModInst_Save" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.isElementPresent( "PS_Detail_EveModInst_SearchPanelID" );
		boolean isNewAddedEMDefPresent = GridHelper.isValuePresent( "PS_Detail_EveModInst_SearchGrid", eventName, "Name" );
		assertTrue( isNewAddedEMDefPresent );
	}

}
