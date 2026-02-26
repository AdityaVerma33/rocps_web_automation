package com.subex.rocps.automation.helpers.application.roaming.roamingAgreemConfig;

import java.util.Map;

import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.PropertyGridHelper;

public class MINFieldProperties implements FieldPropertiesStrategy
{

	protected Map<String, String> minFieldPropertiesMap = null;
	protected String minLength;

	/**Constructor
	 * @param minFieldPropertiesMap
	 */
	public MINFieldProperties( Map<String, String> minFieldPropertiesMap )
	{

		this.minFieldPropertiesMap = minFieldPropertiesMap;
	}

	/*This method is for initialize  basic variable*/
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		minLength = ExcelHolder.getKey( map, "FieldProperties_MIN_Length" );
	}

	/*This method for config field  properties*/
	@Override
	public void configFieldProperties() throws Exception
	{

		initializeVariable( minFieldPropertiesMap );
		PropertyGridHelper.typeInTextBox( "MIN Length *", minLength );

	}

}
