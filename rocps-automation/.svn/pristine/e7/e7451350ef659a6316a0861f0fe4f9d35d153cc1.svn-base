package com.subex.rocps.automation.helpers.application.products.ipMeasurConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class IPMeasurConfigDetailImpl extends PSAcceptanceTest
{

	protected Map<String, String> ipMeasConfigDetailMap = null;
	protected String ipMeasuConfigName;
	protected String eventType;
	protected String component;
	protected String disableCalcComponentFlg;
	protected String component_property;
	protected String component_propertyArr[];
	protected String component_values;
	protected String component_valuesArr[];
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();

	/**Constructor
	 * @param ipMeasConfigDetailMap
	 */
	public IPMeasurConfigDetailImpl( Map<String, String> ipMeasConfigDetailMap )
	{

		this.ipMeasConfigDetailMap = ipMeasConfigDetailMap;
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{

		ipMeasuConfigName = ExcelHolder.getKey( map, "Name" );
		eventType = ExcelHolder.getKey( map, "EventType" );
		component = ExcelHolder.getKey( map, "Component" );
		disableCalcComponentFlg = ExcelHolder.getKey( map, "DisableCalculationComponent" );
		component_property = ExcelHolder.getKey( map, "Component_Property" );
		component_values = ExcelHolder.getKey( map, "Component_Value" );

	}

	/*
	 * This method is for configure IP Measuremnet
	 */
	public void configureIpMeasurment() throws Exception
	{
		initializeVariable( ipMeasConfigDetailMap );
		configureDetailPanel();
		configurePropertiesGrid();
		clickOnSave();
	}

	/*
	 * This method is for modify IP Measuremnet
	 */
	public void modifyIpMeasurment() throws Exception
	{
		initializeVariable( ipMeasConfigDetailMap );
		modifyDetailPanel();
		configurePropertiesGrid();
		clickOnSave();
	}

	/*
	 * This method is for configure details panel
	 */
	protected void configureDetailPanel() throws Exception
	{
		TextBoxHelper.type( "PS_Detail_ipMeasuConfig_name_txtId", ipMeasuConfigName );
		ComboBoxHelper.select( "PS_Detail_ipMeasuConfig_eventType_comboId", eventType );
		ComboBoxHelper.select( "PS_Detail_ipMeasuConfig_component_comboId", component );
		if ( ValidationHelper.isTrue( disableCalcComponentFlg ) )
			CheckBoxHelper.check( "PS_Detail_ipMeasuConfig_disableCalcComp" );
	}

	/*
	 * This method is for modify details panel
	 */
	protected void modifyDetailPanel() throws Exception
	{
		assertEquals( TextBoxHelper.getValue( "PS_Detail_ipMeasuConfig_name_txtId" ), ipMeasuConfigName, "Ip Measurement Configuration name is not matched" );
		psDataComponentHelper.modifyComboBox( "PS_Detail_ipMeasuConfig_eventType_comboId", eventType );
		psDataComponentHelper.modifyComboBox( "PS_Detail_ipMeasuConfig_component_comboId", component );
		if ( ValidationHelper.isTrue( disableCalcComponentFlg ) )
			CheckBoxHelper.check( "PS_Detail_ipMeasuConfig_disableCalcComp" );
		if ( ValidationHelper.isFalse( disableCalcComponentFlg ) )
			CheckBoxHelper.uncheck( "PS_Detail_ipMeasuConfig_disableCalcComp" );
	}

	/*
	 * This method is for configure properties grid
	 */
	protected void configurePropertiesGrid() throws Exception
	{
		String gridId = GenericHelper.getORProperty( "PS_Detail_ipMeasuConfig_properties_gridId" );
		component_propertyArr = psStringUtils.stringSplitFirstLevel( component_property );
		component_valuesArr = psStringUtils.stringSplitFirstLevel( component_values );
		List<String> getComponentProperty = getComponentProperty( gridId );
		for ( int i = 0; i < component_propertyArr.length; i++ )
		{
			assertTrue( getComponentProperty.contains( component_propertyArr[i] ), component_propertyArr[i] + " is not found in properties grid" );
			String expectedRowValue = component_propertyArr[i] + ", " + component_valuesArr[i];
			boolean isDataPresent = psDataComponentHelper.isDataPresentInGrid( gridId, expectedRowValue );
			if ( !isDataPresent )
				configPropertiesGridField( gridId, i + 1, component_valuesArr[i] );
			else
				Log4jHelper.logInfo( "The given cell value is already avilable with name: " + expectedRowValue + " in the properties grid" );
		}
	}

	/*
	 * This method is for configure properties grid field
	 */
	private void configPropertiesGridField( String gridId, int row, String componentValue ) throws Exception
	{
		GridHelper.clickRow( gridId, row, "Property" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( gridId, row, "Value" );
		psDataComponentHelper.selectComboBoxVal( "PS_Detail_ipMeasuConfig_properties_value_comboId", componentValue );

	}

	/*
	 * This method is for get component property
	 */
	private List<String> getComponentProperty( String gridId ) throws Exception
	{
		List<String> listComponentProperty = GridHelper.getColumnValues( gridId, "Property" );
		return listComponentProperty;
	}

	/*
	 * This method is for click on save
	 */
	private void clickOnSave() throws Exception
	{
		psGenericHelper.detailSave( "PS_Detail_ipMeasuConfig_save_btnId", ipMeasuConfigName, "Name" );
	}
}
