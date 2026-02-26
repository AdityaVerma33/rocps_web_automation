package com.subex.rocps.automation.helpers.application.products.productInstance;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.report.Log4jHelper;

public class ProductInstItemMemberImpl extends PSAcceptanceTest
{

	protected Map<String, String> prodInstItemMemberImplMap = null;
	protected String instItemMemberNm;
	protected String productItemName;
	static PSGenericHelper psGenericHelper = new PSGenericHelper();

	/**Constructor
	 * @param prodInstItemMemberImplMap
	 */
	public ProductInstItemMemberImpl( Map<String, String> prodInstItemMemberImplMap )
	{

		this.prodInstItemMemberImplMap = prodInstItemMemberImplMap;
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeItemMemberVariable( Map<String, String> map ) throws Exception
	{
		instItemMemberNm = ExcelHolder.getKey( map, "Name" );
		productItemName = ExcelHolder.getKey( map, "ProductItemName" );
	}

	/*Method is to create Recurriing product instance item member without mandaotory*/
	public void createRecInstItemMemberWithoutMandatory() throws Exception
	{
		initializeItemMemberVariable( prodInstItemMemberImplMap );
		boolean isInstItemMemberPresent = isInstItemMemberPresent();
		if ( !isInstItemMemberPresent )
		{
			RecurringItemInstImpl recurringItemInstImpl = new RecurringItemInstImpl( prodInstItemMemberImplMap );
			selectionOfProductInstItemMember();
			ProductInstanceDetail.waitForInstancePage( "Recurring Item" );
			recurringItemInstImpl.configureRecurringInstItem();
			//ButtonHelper.click( "OKButton" );
			ProductInstanceDetail.clickOnButton();
			isInstItemMemberPresent = isInstItemMemberPresent();
			assertTrue( isInstItemMemberPresent, "Recurring instance item Member name is not saved with name :" + instItemMemberNm );
			Log4jHelper.logInfo( "Recurring instance item Member name is successfuly created for 'Recurring Item'  with name :" + instItemMemberNm );
		}
		else
			Log4jHelper.logInfo( "Recurring instance item Member name is already avilable  with name :" + instItemMemberNm );
	}

	/*Method is to create One Off product instance item member without mandaotory*/
	public void createOneOffInstItemMemberWithoutMandatory() throws Exception
	{
		initializeItemMemberVariable( prodInstItemMemberImplMap );
		boolean isInstItemMemberPresent = isInstItemMemberPresent();
		if ( !isInstItemMemberPresent )
		{
			OneOffItemInstImpl oneOffItemInstImpl = new OneOffItemInstImpl( prodInstItemMemberImplMap );
			selectionOfProductInstItemMember();
			ProductInstanceDetail.waitForInstancePage( "One Off Item" );
			oneOffItemInstImpl.configureOneOffInstItem();
			ProductInstanceDetail.clickOnButton();
			isInstItemMemberPresent = isInstItemMemberPresent();
			assertTrue( isInstItemMemberPresent, "One Off instance item Member name is not saved with name :" + instItemMemberNm );
			Log4jHelper.logInfo( "One Off instance item Member name is successfuly created for 'One Off Item'   with name :" + instItemMemberNm );
		}
		else
			Log4jHelper.logInfo( "One Off instance item Member name is already available  with name :" + instItemMemberNm );
	}

	/*Method is to select product on bundle tree*/
	public static void selectProductOnTree( String bundleNmWithVersionNm, String productName ) throws Exception
	{

		ProductUtil.selectChildOfParent( "PS_Detail_instance_treeName", bundleNmWithVersionNm, productName );
		psGenericHelper.waitforHeaderElement( "Type" );

	}

	/*Method is to check instance item member present*/
	private boolean isInstItemMemberPresent() throws Exception
	{
		psGenericHelper.waitforHeaderElement( "Account" );
		ButtonHelper.click( "ClearButton" );
		boolean isInstItemMemberPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_prodInst_InstItemMemberNameFilter_textID", instItemMemberNm, "Name" );
		return isInstItemMemberPresent;

	}

	/*Method is to select of product instance item member*/
	private void selectionOfProductInstItemMember() throws Exception
	{
		ProductInstanceActionImpl productInstanceActionImpl = new ProductInstanceActionImpl();
		productInstanceActionImpl.clickOnAction( "Common Tasks", "New" );
		ProductInstanceDetail.waitForPopupPage( "Product Definition Item Member" );
		ComboBoxHelper.select( "PS_Detail_prodInst_InstItemMember_DfnMember_comboID", productItemName );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "OKButton" );

	}
}
