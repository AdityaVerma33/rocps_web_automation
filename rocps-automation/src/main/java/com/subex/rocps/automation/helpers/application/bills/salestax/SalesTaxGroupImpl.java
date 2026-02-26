package com.subex.rocps.automation.helpers.application.bills.salestax;

import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

import com.subex.rocps.automation.helpers.application.filters.GridFilterHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

public class SalesTaxGroupImpl extends PSAcceptanceTest
{
	protected String name;
	protected String country;
	protected Map<String, String> salesGrpMap = null;
	protected String clientPartition;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();

	/*
	 * public SalesTaxGroupImpl(Map<String, String> salesGrpMap) throws Exception {
	 * 
	 * this.salesGrpMap = salesGrpMap; initializeVariables(salesGrpMap);
	 * 
	 * }
	 */

	/*
	 * This Method is for creating new Sales Tax Group
	 */
	public void createNew( String clientPartition ) throws Exception
	{
		genericHelperObj.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask();

	}

	/*
	 * This Method is for Basic Details of Sales Tax Group
	 */
	public void basicDetails( String name, String country ) throws Exception
	{
		assertEquals( NavigationHelper.getScreenTitle(), "New Sales Tax Group" );
		TextBoxHelper.type( "PS_details_taxGroup_name", name );
		ComboBoxHelper.select( "PS_details_taxGroup_country", country );

	}

	/*
	 * This Method is for Basic Details of Sales Tax Group
	 */
	public void editbasicDetails( String name, String country ) throws Exception
	{
		assertEquals( NavigationHelper.getScreenTitle(), "Edit Sales Tax Group" );
		assertEquals( TextBoxHelper.getValue( "PS_details_taxGroup_name" ), name, "'Sales Tax Group' name is not matched" );
		if ( ValidationHelper.isNotEmpty( country ) )
			ComboBoxHelper.select( "PS_details_taxGroup_country", country );
	}

	/*
	 * This Method is for addition of Sales Tax to Sales Tax Group and selecting Tax
	 * on Tax "Y".equals(taxOnTax[i])
	 */
	public void addSalesTax( String salesTaxName, String taxOnTax ) throws Exception
	{

		String spliregex = new PSStringUtils().regexFirstLevelDelimeter();
		String[] salesTaxNamesArr = salesTaxName.split( spliregex, -1 );
		String[] taxOnTaxArr = taxOnTax.split( spliregex, -1 );
		for ( int i = 0; i < salesTaxNamesArr.length; i++ )
		{
			if ( !isSalesTaxPresentInGrid( salesTaxNamesArr[i] ) )
				configureAddSalesTax( salesTaxNamesArr[i], taxOnTaxArr[i] );

		}
	}

	/*
	 * This Method is for configure add sales tax
	 */
	private void configureAddSalesTax( String salesTaxName, String taxOnTax ) throws Exception
	{

		ButtonHelper.click( "PS_details_taxGroup_add" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		genericHelperObj.waitforPopupHeaderElement( "Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "PS_details_taxGroup_salesGroupGrid", "Detail_salesTaxgroup_gridID", salesTaxName, "Name" );
		GenericHelper.waitForLoadmask();
		ButtonHelper.click( "PS_details_taxGroup_ok" );
		GenericHelper.waitForLoadmask();
		assertTrue( NavigationHelper.getScreenTitle().contains( "Sales Tax Group" ), "Detail screen title not found" );
		if ( ValidationHelper.isTrue( taxOnTax ) )
		{
			int row = GridHelper.getRowNumber( "salestaxgrouptGrid", salesTaxName, "Name" );
			GridHelper.clickRow( "salestaxgrouptGrid", row, "Tax On Tax" );
			// CheckBoxHelper.check("PS_Details_taxGroup_checkBox");
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}

	}

	/*
	 * This Method is to find sales tax is present in the grid
	 */
	private boolean isSalesTaxPresentInGrid( String salesTaxNm ) throws Exception
	{
		try
		{
			int totalRow = GridHelper.getRowCount( "salestaxgrouptGrid" );
			for ( int i = 0; i < totalRow; i++ )
			{
				String name = GridHelper.getCellValue( "salestaxgrouptGrid", i + 1, "Name" );
				if ( name.equals( salesTaxNm ) )
					return true;

			}
			return false;
		}
		catch ( Exception e )
		{

			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	/*
	 * This Method is for saving the Sales Tax Group
	 */

	public void saveSalesTaxGroup( String name ) throws Exception
	{
		genericHelperObj.detailSave( "PS_details_taxGroup_save", name, "Name" );
	}

	/*
	 * method for clicking delete action in Sales Tax Group search screen
	 */
	public void clickDeleteAction( String name ) throws Exception
	{

		genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Delete" );
		GenericHelper.waitForLoadmask();
	}

	/*
	 * method for clicking un delete action in Sales Tax Group search screen
	 */
	public void clickUnDeleteAction( String name ) throws Exception
	{
		genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Undelete" );
		GenericHelper.waitForLoadmask();
	}

}
