package com.subex.rocps.automation.helpers.application.roaming.roamingConfig;

import java.util.Map;

import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class RoamingCamelService extends PSAcceptanceTest
{
	protected Map<String, String> roamingCamelServiceDetailsMap = null;
	protected String isCamelSupportedFlg;
	protected String camelServiceLevel;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();

	/**Constructor
	 * @param roamingCamelServiceDetailsMap
	 */
	public RoamingCamelService( Map<String, String> roamingCamelServiceDetailsMap )
	{

		this.roamingCamelServiceDetailsMap = roamingCamelServiceDetailsMap;
	}

	/*This method is for initialize  variable*/
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		isCamelSupportedFlg = ExcelHolder.getKey( map, "IsCamelSupported" );
		camelServiceLevel = ExcelHolder.getKey( map, "CamelServiceLevel" );
	}

	/*This method is for configure Camel Service  */
	public void configCamelService(  ) throws Exception
	{

		TabHelper.gotoTab( "PS_Detail_roamingConfig_camelSericeTab_xpath" );
		initializeVariable( roamingCamelServiceDetailsMap );
		if ( ValidationHelper.isTrue( isCamelSupportedFlg ) )
		{
			CheckBoxHelper.check( "PS_Detail_roamingConfig_camelSericeTab_isCamelSupp_checkbx" );
			ComboBoxHelper.select( "PS_Detail_roamingConfig_camelSericeTab_camelServiceLevel_comboId", camelServiceLevel );
		}
	}

}
