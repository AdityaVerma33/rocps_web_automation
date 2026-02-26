package com.subex.rocps.automation.helpers.application.roaming.roamingAgreemConfig;

import java.util.Map;

import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.automation.helpers.component.PropertyGridHelper;

public class IMSIFieldProperties implements FieldPropertiesStrategy
{
	protected Map<String, String> imsiFieldPropertiesMap = null;
	protected String imsiLength;

	/**Constructor
	 * @param imsiFieldPropertiesMap
	 */
	public IMSIFieldProperties( Map<String, String> imsiFieldPropertiesMap )
	{
		this.imsiFieldPropertiesMap = imsiFieldPropertiesMap;
	}

	/*This method is for initialize  basic variable*/
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		imsiLength = ExcelHolder.getKey( map, "FieldProperties_IMSI_Length" );
	}

	/*This method for config field  properties*/
	@Override
	public void configFieldProperties() throws Exception
	{
		initializeVariable( imsiFieldPropertiesMap );
		PropertyGridHelper.typeInTextBox( "IMSI Length *", imsiLength );
	}

}
