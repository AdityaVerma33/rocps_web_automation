package com.subex.rocps.automation.helpers.application.roaming.roamingAgreemConfig;

import java.util.Map;

import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.PropertyGridHelper;

public class MDNFieldProperties implements FieldPropertiesStrategy
{
	protected Map<String, String> mdnFieldPropertiesMap = null;
	protected String mdnPrefixFormat;

	/**Constructor
	 * @param mdnFieldPropertiesMap
	 */
	public MDNFieldProperties( Map<String, String> mdnFieldPropertiesMap )
	{
		this.mdnFieldPropertiesMap = mdnFieldPropertiesMap;
	}

	/*This method is for initialize  basic variable*/
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		mdnPrefixFormat = ExcelHolder.getKey( map, "FieldProperties_MDN_prefixFormat" );
	}

	/*This method for config field  properties*/
	@Override
	public void configFieldProperties() throws Exception
	{
		initializeVariable( mdnFieldPropertiesMap );
		PropertyGridHelper.selectInComboBox( "MDN Prefix Format *", mdnPrefixFormat );

	}

}
