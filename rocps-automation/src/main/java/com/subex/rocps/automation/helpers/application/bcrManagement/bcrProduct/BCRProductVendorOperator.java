package com.subex.rocps.automation.helpers.application.bcrManagement.bcrProduct;

import java.util.Map;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class BCRProductVendorOperator extends PSAcceptanceTest
{

	protected Map<String, String> bcrProdVendorOpDetMap;
	protected String selectedOperatorsr;

	/**Constructor
	 * @param bcrProdVendorOpDetMap
	 */
	public BCRProductVendorOperator( Map<String, String> bcrProdVendorOpDetMap )
	{
		this.bcrProdVendorOpDetMap = bcrProdVendorOpDetMap;
	}

	//Method for initializing instance variables
	public void initializeVariables( Map<String, String> map ) throws Exception
	{

		selectedOperatorsr = ExcelHolder.getKey( map, "VendorOpSelectedOperators" );
	}
}
