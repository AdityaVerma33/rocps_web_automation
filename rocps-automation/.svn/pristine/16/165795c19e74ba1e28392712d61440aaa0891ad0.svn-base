package com.subex.rocps.automation.helpers.application.bills.salestax;

import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class SalesTaxRateImpl extends PSAcceptanceTest
{
	protected String taxGroup;
	protected String fromDate;
	protected String rate;
	protected String clientPartition;
	protected Map<String, String> salesRateMap = null;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();

	public SalesTaxRateImpl( Map<String, String> salesRateMap ) throws Exception
	{

		this.salesRateMap = salesRateMap;
		initializeVariables( salesRateMap );

	}

	public void initializeVariables( Map<String, String> salesRateMap ) throws Exception
	{
		taxGroup = ExcelHolder.getKey( salesRateMap, "SalesTaxName" );
		fromDate = ExcelHolder.getKey( salesRateMap, "FromDate" );
		rate = ExcelHolder.getKey( salesRateMap, "Rate" );
		clientPartition = ExcelHolder.getKey( salesRateMap, "Partition" );
	}

	/*
	 * This Method is for creating new Sales Tax
	 */
	public void createNew() throws Exception
	{
		genericHelperObj.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );

	}

	/*
	 * This Method is for Basic Details of Sales Tax Rate
	 */

	public void basicDetails() throws Exception
	{
		assertEquals( NavigationHelper.getScreenTitle(), "New Sales Tax Rate" );
		ComboBoxHelper.select( "PS_Details_taxRate_salesTaxGroup", taxGroup );
		TextBoxHelper.type( "PS_details_taxRate_fromDate", fromDate );
		TextBoxHelper.type( "PS_details_taxRate_rate", rate );

	}

	/*
	 * This Method is for Basic Details of Sales Tax Rate
	 */

	public void editbasicDetails() throws Exception
	{
		assertEquals( NavigationHelper.getScreenTitle(), "Edit Sales Tax Rate" );
		assertEquals( ComboBoxHelper.getValue( "PS_Details_taxRate_salesTaxGroup" ), taxGroup, "'Sales Tax' name is not matched" );
		assertEquals( TextBoxHelper.getValue( "PS_details_taxRate_fromDate" ), fromDate, "'From date' name is not matched" );
		if ( ValidationHelper.isNotEmpty( rate ) )
			TextBoxHelper.type( "PS_details_taxRate_rate", rate );

	}

}
