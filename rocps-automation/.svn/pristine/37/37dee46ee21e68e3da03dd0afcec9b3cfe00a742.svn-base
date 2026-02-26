package com.subex.rocps.automation.helpers.application.xdrextraction.XdrExtraTemplate;

import java.util.Map;

import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.filters.EnitityValuesSelectionHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class XDRExtractFilter extends PSAcceptanceTest
{
	protected Map<String, String> xdrFilterMap = null;
	protected String entities;
	protected String values;
	EnitityValuesSelectionHelper enitityValuesSelectionHelper = new EnitityValuesSelectionHelper();

	public XDRExtractFilter( Map<String, String> xdrFilterMap ) throws Exception
	{
		this.xdrFilterMap = xdrFilterMap;
		initializeVariable();

	}

	private void initializeVariable() throws Exception
	{
		entities = xdrFilterMap.get( "Entities" );
		values = xdrFilterMap.get( "Values" );
	}

	public void xdrExtractFilterTabConfig() throws Exception
	{

		TabHelper.gotoTab( GenericHelper.getORProperty( "PS_Detail_xdrExtTemp_extFilterTabXpath" ) );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertTrue( TabHelper.isSelected( "PS_Detail_xdrExtTemp_selectedExtFilterXpath" ), "Extract Filter tab is not selected in XDR Extract Template" );
		configEntitiesValue();
	}

	protected void configEntitiesValue() throws Exception
	{
		if ( ValidationHelper.isNotEmpty( entities ) )
			enitityValuesSelectionHelper.entityGridConfig( "PS_Detail_xdrExtTemp_entities_editor_comboId", "PS_Detail_xdrExtTemp_entities_gridId", "PS_Detail_xdrExtTemp_entities_add_btnId", entities, values, "PS_Detail_xdrExtTemp_value_gridId", "PS_Detail_xdrExtTemp_value_add_btnId", "Event Usage Request" );
	}

}
