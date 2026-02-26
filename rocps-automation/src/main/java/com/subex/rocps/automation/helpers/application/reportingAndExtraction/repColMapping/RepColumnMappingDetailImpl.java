package com.subex.rocps.automation.helpers.application.reportingAndExtraction.repColMapping;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.TextBoxHelper;

public class RepColumnMappingDetailImpl extends PSAcceptanceTest
{
	protected Map<String, String> repColMapDetailsMap = null;
	protected String configName;
	protected String repCompMappingDfn;

	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();

	/**Constructor
	 * @param repColMapDetailsMap
	 */
	public RepColumnMappingDetailImpl( Map<String, String> repColMapDetailsMap )
	{
		this.repColMapDetailsMap = repColMapDetailsMap;
	}

	/*
	 * This method is for initialize variable
	 */
	protected void initializeVariable( Map<String, String> map ) throws Exception
	{
		configName = ExcelHolder.getKey( map, "ConfigurationName" );
		repCompMappingDfn = ExcelHolder.getKey( map, "RepCompMappingDfn" );

	}

	/*
	 * This method is for configuration column mapping
	 */
	public void configRepColumMapping() throws Exception
	{
		initializeVariable( repColMapDetailsMap );
		configRepColumMapDetFirstPanel();
	}

	/*
	 * This method is for modify column mapping
	 */
	public void modifyRepColumMapping() throws Exception
	{
		initializeVariable( repColMapDetailsMap );
		modifyRepColumMapDetFirstPanel();
	}

	/*
	 * This method is for configuration column mapping first panel
	 */
	protected void configRepColumMapDetFirstPanel() throws Exception
	{

		TextBoxHelper.type( "PS_Detail_repColMapping_configNm_textID", configName );
		ComboBoxHelper.select( "PS_Detail_repColMapping_repCompMapDfn_comboId", repCompMappingDfn );
	}

	/*
	 * This method is for modify column mapping first panel
	 */
	protected void modifyRepColumMapDetFirstPanel() throws Exception
	{

		assertEquals( TextBoxHelper.getValue( "PS_Detail_repColMapping_configNm_textID" ), configName, "Configyration name is not matched" );
		psDataComponentHelper.checkComboBoxDisabled( "PS_Detail_repColMapping_repCompMapDfn_comboId", repCompMappingDfn );
	}

}
