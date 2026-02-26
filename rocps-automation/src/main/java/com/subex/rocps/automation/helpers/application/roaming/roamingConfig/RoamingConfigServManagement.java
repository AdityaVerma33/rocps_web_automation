package com.subex.rocps.automation.helpers.application.roaming.roamingConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class RoamingConfigServManagement extends PSAcceptanceTest
{
	protected Map<String, String> roamingConfigServManagDetailsMap = null;
	protected String roamingService;
	protected String roamingServiceArr[];
	protected String tdStartDt;
	protected String tdStartDtArr[];
	protected String tdEndDt;
	protected String tdEndDtArr[];
	protected String cdStartDt;
	protected String cdStartDtArr[];
	protected String cdEndDt;
	protected String cdEndDtArr[];
	protected String exclContext;
	protected String exclContextArr[];
	protected String isChargedFlg;
	protected String isChargedFlgArr[];
	protected String tariff;
	protected String tariffArr[];
	protected String discount;
	protected String discountArr[];
	protected String salesTaxGrp;
	protected String salesTaxGrpArr[];
	protected String isDefaultServiceFlg;
	protected String isDefaultServiceFlgArr[];

	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();

	/**Constructor
	 * @param roamingConfigServManagDetailsMap
	 */
	public RoamingConfigServManagement( Map<String, String> roamingConfigServManagDetailsMap )
	{

		this.roamingConfigServManagDetailsMap = roamingConfigServManagDetailsMap;
	}

	/*This method is for initialize  = variable*/
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		roamingService = ExcelHolder.getKey( map, "ServiceManag_Service" );
		tdStartDt = ExcelHolder.getKey( map, "ServiceManag_TdStartDate" );
		tdEndDt = ExcelHolder.getKey( map, "ServiceManag_TdEndDate" );
		cdStartDt = ExcelHolder.getKey( map, "ServiceManag_CdStartDate" );
		cdEndDt = ExcelHolder.getKey( map, "ServiceManag_CdEndDate" );
		isDefaultServiceFlg = ExcelHolder.getKey( map, "ServiceManag_DefaultServcFlg" );

	}

	private void initializeVarArrays() throws Exception
	{
		roamingServiceArr = psStringUtils.stringSplitFirstLevel( roamingService );
		tdStartDtArr = psStringUtils.stringSplitFirstLevel( tdStartDt );
		tdEndDtArr = psStringUtils.stringSplitFirstLevel( tdEndDt );
		cdStartDtArr = psStringUtils.stringSplitFirstLevel( cdStartDt );
		cdEndDtArr = psStringUtils.stringSplitFirstLevel( cdEndDt );
		isDefaultServiceFlgArr = psStringUtils.stringSplitFirstLevel( isDefaultServiceFlg );
	}

	public void configServManagement( String configType ) throws Exception
	{

		TabHelper.gotoTab( "PS_Detail_roamingConfig_serviceManagTab_xpath" );

		try
		{
			initializeVariable( roamingConfigServManagDetailsMap );
			String gridId = null;
			if ( configType.contentEquals( "Tap Out" ) )
				gridId = "PS_Detail_roamConfig_servManag_TapOut_gridId";
			if ( configType.contentEquals( "Tap In" ) )
				gridId = "PS_Detail_roamConfig_servManag_TapIn_gridId";
			if ( ValidationHelper.isNotEmpty( roamingService ) )
			{
				initializeVarArrays();
				List<String> getKeysOfServManFieldGrid = getKeysOfServManFieldGrid();
				for ( int i = 0; i < roamingServiceArr.length; i++ )
				{
					Map<String, ArrayList<String>> mapOfServiceFieldGrid = ProductUtil.getGridColumnValues( gridId, "grid_column_header_undefined_", getKeysOfServManFieldGrid );
					boolean isServicePresentInGrid = ProductUtil.isDataPresentInGrid( gridId, mapOfServiceFieldGrid, "Service", roamingServiceArr[i] );
					if ( !isServicePresentInGrid )
						configServManagField( i + 1, gridId, configType);
					else
						Log4jHelper.logInfo( "This 'Roaming service'   value: " + roamingServiceArr[i] + " is already present in this grid:" + GenericHelper.getORProperty( gridId ) );
				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	private void configServManagField( int row, String gridId, String configType ) throws Exception
	{

		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		discount = ExcelHolder.getKey( roamingConfigServManagDetailsMap, "Discount" );
		discountArr = psStringUtils.stringSplitFirstLevel( discount );
		if ( configType.contentEquals( "Tap Out" ) )
		{
			exclContext = ExcelHolder.getKey( roamingConfigServManagDetailsMap, "ServiceManag_ExclContext" );
			exclContextArr = psStringUtils.stringSplitFirstLevel( exclContext );
			configField( row, gridId, "PS_Detail_roamConfig_servManag_TapOut_add_btnId", "PS_Detail_roamConfig_servMan_TapOut_roamSer_combId", "PS_Detail_roamConfig_servMan_TapOut_tdStartDt_txtId", "PS_Detail_roamConfig_servMan_TapOut_tdEndDt_txtId", "PS_Detail_roamConfig_servMan_TapOut_cdStartDt_txtId", "PS_Detail_roamConfig_servMan_TapOut_cdEndDt_txtId" );
			configExclusionContextType( gridId, row, exclContextArr[row - 1] );
			if ( ValidationHelper.isNotEmpty( discountArr[row - 1] ) )
			{
				psGenericHelper.scrollforHeaderElement( gridId, "Discount" );
				GridHelper.updateGridComboBox( gridId, "PS_Detail_roamConfig_servManag_TapOut_discount_combId", row, "Discount", discountArr[row - 1] );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				psGenericHelper.scrollforHeaderElement( gridId, "Service" );
			}
		}
		if ( configType.contentEquals( "Tap In" ) )
		{
			isChargedFlg = ExcelHolder.getKey( roamingConfigServManagDetailsMap, "IsChargedFlg" );
			isChargedFlgArr = psStringUtils.stringSplitFirstLevel( isChargedFlg );
			tariff = ExcelHolder.getKey( roamingConfigServManagDetailsMap, "Tariff" );
			tariffArr = psStringUtils.stringSplitFirstLevel( tariff );
			salesTaxGrp = ExcelHolder.getKey( roamingConfigServManagDetailsMap, "SalesTaxGroup" );
			salesTaxGrpArr = psStringUtils.stringSplitFirstLevel( salesTaxGrp );
			configField( row, gridId, "PS_Detail_roamConfig_servManag_TapIn_add_btnId", "PS_Detail_roamConfig_servMan_TapIn_roamSer_combId", "PS_Detail_roamConfig_servMan_TapIn_tdStartDt_txtId", "PS_Detail_roamConfig_servMan_TapIn_tdEndDt_txtId", "PS_Detail_roamConfig_servMan_TapIn_cdStartDt_txtId", "PS_Detail_roamConfig_servMan_TapIn_cdEndDt_txtId" );
			configFieldTapInForIOT( gridId, row, isChargedFlgArr[row - 1], tariffArr[row - 1], discountArr[row - 1], salesTaxGrpArr[row - 1] );
		}

	}

	private void configField( int row, String gridId, String btnId, String roamingServiceComboId, String tdStartDtTxtId, String tdEndDtTxtId, String cdStartTxtId, String cdEndDtTxtId) throws Exception
	{
		ButtonHelper.click( btnId );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.updateGridComboBox( gridId, roamingServiceComboId, row, "Service", roamingServiceArr[row-1] );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		if ( ValidationHelper.isNotEmpty( tdStartDtArr[row-1] ) )
			GridHelper.updateGridTextBox( gridId, tdStartDtTxtId, row, "TD Start Date", tdStartDtArr[row-1] );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		if ( ValidationHelper.isNotEmpty( tdEndDtArr[row-1] ) )
			GridHelper.updateGridTextBox( gridId, tdEndDtTxtId, row, "TD End Date",  tdEndDtArr[row-1] );
		GridHelper.clickRow( gridId, row, "CD Start Date" );
		GridHelper.updateGridTextBox( gridId, cdStartTxtId, row, "CD Start Date", cdStartDtArr[row-1] );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( gridId, row, "CD End Date" );
		GridHelper.updateGridTextBox( gridId, cdEndDtTxtId, row, "CD End Date", cdEndDtArr[row-1] );
		if(ValidationHelper.isTrue( isDefaultServiceFlgArr[row-1] ))
			GridHelper.updateGridCheckBox( gridId, row, "Is  Default  Service?", isDefaultServiceFlgArr[row-1] );

	}

	private void configExclusionContextType( String gridId, int row, String exclusionContext ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( exclusionContext ) )
		{

			GridHelper.clickRow( gridId, row, "Exclusion  Contexts" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			ButtonHelper.clickIfEnabled( "PS_Detail_roamConfig_servMan_TapOut_exclContext_btnId" );
			configExclusionContextCallType( gridId, row, exclusionContext );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}

	}

	private void configFieldTapInForIOT( String gridId, int row, String isChargedFlg, String tariffName, String discount, String salesTaxGrp ) throws Exception
	{

		GridHelper.clickRow( gridId, row, "Is  Charged?" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.updateGridCheckBox( gridId, row, "Is  Charged?", isChargedFlg );
		if ( ValidationHelper.isTrue( isChargedFlg ) )
		{
			tariffEntitySelection( gridId, row, tariffName );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			GridHelper.updateGridComboBox( gridId, "PS_Detail_roamConfig_servManag_TapIn_discount_combId", row, "Discount", discount );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.scrollforHeaderElement( gridId, "Sales  Tax  Group" );
			GridHelper.updateGridComboBox( gridId, "PS_Detail_roamConfig_servManag_TapIn_salesTaxGrp_combId", row, "Sales  Tax  Group", salesTaxGrp );
			psGenericHelper.scrollforHeaderElement( gridId, "Service" );
		}
		GenericHelper.waitForLoadmask( searchScreenWaitSec );

	}

	private void tariffEntitySelection( String gridId, int row, String tariffName ) throws Exception
	{
		GridHelper.clickRow( gridId, row, "Tariff" );
		if ( !ElementHelper.isElementPresent( GenericHelper.getORProperty( "PS_Detail_roamConfig_servManag_TapIn_tariffEntityXpath" ) ) )
		{
			GridHelper.clickRow( gridId, row, "Tariff" );
			ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_roamConfig_servManag_TapIn_tariffEntityXpath" ), searchScreenWaitSec );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_roamConfig_servManag_TapIn_tariffEntityXpath" ), searchScreenWaitSec );
		EntityComboHelper.clickEntityIcon( "PS_Detail_roamConfig_servManag_TapIn_tariffEntityXpath" );
		DataSelectionHelper dataSelectionHelper = new DataSelectionHelper();
		dataSelectionHelper.tariffSelection( tariffName );
	}

	/*This method is for configuration Exclusion context */
	private void configExclusionContextCallType( String gridId, int row, String exclusionContext ) throws Exception
	{
		ElementHelper.waitForElement( "PS_Detail_roamConfig_servMan_TapOut_exclContext_detailxpath", searchScreenWaitSec );
		String[] exclusionContexteArr = new PSStringUtils().stringSplitSecondLevel( exclusionContext );
		ArrayList<String> callTypeCellValues = new ArrayList<String>();
		for ( String exclusionContextValue : exclusionContexteArr )
		{
			psGenericHelper.dualListSelectionWithTxtFilter( exclusionContextValue );
			callTypeCellValues.add( exclusionContextValue );
		}
		ButtonHelper.click( "PS_Detail_roamConfig_servMan_TapOut_exclContext_save_btnId" );
		ElementHelper.waitForClickableElement( "PS_Detail_roamingConfig_serviceManagTab_xpath", searchScreenWaitSec );
		validateCallTypeGridValues( gridId, callTypeCellValues );
	}

	/*This method is for validate call type cell valye for Exclusion Contexts grid*/
	private void validateCallTypeGridValues( String gridId, ArrayList<String> callTypeCellValues ) throws Exception
	{
		String callTypeCellValue = callTypeCellValues.toString();
		List<String> getKeysOfServManExclContextdGrid = getKeysOfServManExclContextdGrid();
		Map<String, ArrayList<String>> mapOfExclContMappingGrid = ProductUtil.getGridColumnValues( gridId, "grid_column_header_undefined_", getKeysOfServManExclContextdGrid );
		boolean isCallTypeCelleValuePresent = ProductUtil.isDataPresentInGrid( gridId, mapOfExclContMappingGrid, "Exclusion  Contexts", callTypeCellValue );
		assertTrue( isCallTypeCelleValuePresent, "Call Types value are not matched in 'Exclusion Contexts' mapping Grid" );
	}

	//Service Management  grid columns keys
	private List<String> getKeysOfServManFieldGrid()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "Service" );
		return listColumn;
	}

	//Service Management  grid columns keys
	private List<String> getKeysOfServManExclContextdGrid()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "Exclusion  Contexts" );
		return listColumn;
	}

}
