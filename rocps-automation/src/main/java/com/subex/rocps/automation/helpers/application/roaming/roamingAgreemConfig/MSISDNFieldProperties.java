package com.subex.rocps.automation.helpers.application.roaming.roamingAgreemConfig;

import java.util.Map;

import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class MSISDNFieldProperties implements FieldPropertiesStrategy
{
	protected Map<String, String> msisdnFieldPropertiesMap = null;
	protected String expectedFlag;
	protected String prefixFormat;

	/**Constructor
	 * @param msisdnFieldPropertiesMap
	 */
	public MSISDNFieldProperties( Map<String, String> msisdnFieldPropertiesMap )
	{
		this.msisdnFieldPropertiesMap = msisdnFieldPropertiesMap;
	}

	/*This method is for initialize  basic variable*/
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		expectedFlag = ExcelHolder.getKey( map, "FieldProperties_MSISDN_expected_Flag" );
		prefixFormat = ExcelHolder.getKey( map, "FieldProperties_MSISDN_prefixFormat" );
	}

	/*This method for config field  properties*/
	@Override
	public void configFieldProperties() throws Exception
	{
		initializeVariable( msisdnFieldPropertiesMap );
		if ( ValidationHelper.isTrue( expectedFlag ) )
			CheckBoxHelper.check( "PS_Detail_roamAgCon_fieldProp_MSISDN_expected_chckBx" );
		PropertyGridHelper.selectInComboBox( "MSISDN Prefix Format *", prefixFormat );
	}

}
