package com.subex.rocps.automation.helpers.application.roaming.roamingConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class HUROutConfigTypeBasicDetails extends PSAcceptanceTest implements ConfigTypeBasicDetailsStrategy
{

	protected Map<String, String> hurOutConfTypeBasicDetailsMap = null;
	protected String disabHURGenFlg;
	protected String thresAmnt;
	protected String thresPeriod;
	protected String sendMethod;
	protected String emailAdd;
	protected String emailAddArr[];
	protected String faxNumber;
	protected String faxNumberArr[];
	protected String cdStartDate;
	protected String cdEndDate;

	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	RoamingConfigBasicDetails roamingConfigBasicDetails = new RoamingConfigBasicDetails();

	/**Constructor
	 * @param hurOutConfTypeBasicDetailsMap
	 */
	public HUROutConfigTypeBasicDetails( Map<String, String> hurOutConfTypeBasicDetailsMap )
	{
		this.hurOutConfTypeBasicDetailsMap = hurOutConfTypeBasicDetailsMap;
	}

	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		disabHURGenFlg = ExcelHolder.getKey( map, "DisableHURGenFlg" );
		thresAmnt = ExcelHolder.getKey( map, "ThresholdAmount" );
		thresPeriod = ExcelHolder.getKey( map, "ThresholdPeriod" );
		sendMethod = ExcelHolder.getKey( map, "SendMethod" );
		emailAdd = ExcelHolder.getKey( map, "EmailAdress" );
		faxNumber = ExcelHolder.getKey( map, "FaxNumber" );
	}

	/*This method is for initialize  date variable*/
	private void initializeDateVariables( Map<String, String> map ) throws Exception
	{
		cdStartDate = ExcelHolder.getKey( map, "CDStartDate" );
		cdEndDate = ExcelHolder.getKey( map, "CDEndDate" );
	}

	/*This method is for initialize  = variable*/
	@Override
	public void configureBasicDetails() throws Exception
	{
		initializeVariable( hurOutConfTypeBasicDetailsMap );
		configHURAdditionalDetails();
		configEmailConfigurations();
		configFaxConfigurations();
	}

	private void configHURAdditionalDetails() throws Exception
	{
		configDateDetails();
		if ( ValidationHelper.isTrue( disabHURGenFlg ) )
			CheckBoxHelper.check( "PS_Detail_roamConfig_BD_HUROUT_disabHurGen_chckBxId" );
		if ( ValidationHelper.isNotEmpty( thresAmnt ) )
			TextBoxHelper.type( "PS_Detail_roamConfig_BD_HUROUT_thresAmt_txtId", thresAmnt );
		else
			assertEquals( TextBoxHelper.getValue( "PS_Detail_roamConfig_BD_HUROUT_thresAmt_txtId" ), "0", "Threshold Amount(SDR) is not matched" );
		if ( ValidationHelper.isNotEmpty( thresPeriod ) )
			TextBoxHelper.type( "PS_Detail_roamConfig_BD_HUROUT_thresPeriod_txtId", thresPeriod );
		else
			assertEquals( TextBoxHelper.getValue( "PS_Detail_roamConfig_BD_HUROUT_thresPeriod_txtId" ), "24.0000", "Threshold Period (hours) is not matched" );
		ComboBoxHelper.select( "PS_Detail_roamConfig_BD_HUROUT_sendMethod_comboId", sendMethod );
	}

	private void configDateDetails() throws Exception
	{
		initializeDateVariables( hurOutConfTypeBasicDetailsMap );
		TextBoxHelper.type( "PS_Detail_roamConfig_hur_cdStartDt", cdStartDate );
		TextBoxHelper.type( "PS_Detail_roamConfig_hur_cdEndDt", cdEndDate );
	}

	private void configEmailConfigurations() throws Exception
	{
		try
		{
			if ( ValidationHelper.isNotEmpty( emailAdd ) )
			{
				emailAddArr = psStringUtils.stringSplitFirstLevel( emailAdd );

				List<String> getKeysOfEmailFieldGrid = getKeysOfEmailFieldGrid();
				for ( int i = 0; i < emailAddArr.length; i++ )
				{
					Map<String, ArrayList<String>> mapOfEmailFieldGrid = ProductUtil.getGridColumnValues( "PS_Detail_roamConfig_BD_HUROUT_emailConf_gridId", "grid_column_header_undefined_", getKeysOfEmailFieldGrid );
					boolean isEmailPresentInGrid = ProductUtil.isDataPresentInGrid( "PS_Detail_roamConfig_BD_HUROUT_emailConf_gridId", mapOfEmailFieldGrid, "Email  Address", emailAddArr[i] );
					if ( !isEmailPresentInGrid )
						configFieldGrid( i + 1, "PS_Detail_roamConfig_BD_HUROUT_emailConf_add_btnId", "PS_Detail_roamConfig_BD_HUROUT_emailConf_gridId", "PS_Detail_roamConfig_BD_HUROUT_emailConf_email_txtId", "Email  Address", emailAddArr[i] );
					else
						Log4jHelper.logInfo( "This Email address   value: " + emailAddArr[i] + " is already present in this grid:" + GenericHelper.getORProperty( "PS_Detail_roamConfig_BD_HUROUT_emailConf_gridId" ) );
				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	private void configFaxConfigurations() throws Exception
	{
		try
		{
			if ( ValidationHelper.isNotEmpty( faxNumber ) )
			{
				faxNumberArr = psStringUtils.stringSplitFirstLevel( faxNumber );

				List<String> getKeysOfFaxFieldGrid = getKeysOfFaxFieldGrid();
				for ( int i = 0; i < faxNumberArr.length; i++ )
				{
					Map<String, ArrayList<String>> mapOfFaxFieldGrid = ProductUtil.getGridColumnValues( "PS_Detail_roamConfig_BD_HUROUT_faxConf_gridId", "grid_column_header_undefined_", getKeysOfFaxFieldGrid );
					boolean isFaxNmPresentInGrid = ProductUtil.isDataPresentInGrid( "PS_Detail_roamConfig_BD_HUROUT_faxConf_gridId", mapOfFaxFieldGrid, "Fax  Number", faxNumberArr[i] );
					if ( !isFaxNmPresentInGrid )
						configFieldGrid( i + 1, "PS_Detail_roamConfig_BD_HUROUT_faxConf_add_btnId", "PS_Detail_roamConfig_BD_HUROUT_faxConf_gridId", "PS_Detail_roamConfig_BD_HUROUT_faxConf_fax_txtId", "Fax  Number", faxNumberArr[i] );
					else
						Log4jHelper.logInfo( "This Fax Number   value: " + faxNumberArr[i] + " is already present in this grid:" + GenericHelper.getORProperty( "PS_Detail_roamConfig_BD_HUROUT_faxConf_gridId" ) );
				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*private void configEmailFieldGrid( int row, String emailAdd ) throws Exception
	{
		ButtonHelper.click( "PS_Detail_roamConfig_BD_HUROUT_emailConf_add_btnId" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.updateGridTextBox( "PS_Detail_roamConfig_BD_HUROUT_emailConf_gridId", "PS_Detail_roamConfig_BD_HUROUT_emailConf_email_txtId", row, "Email  Address", emailAdd );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}
	
	private void configFaxNumberFieldGrid( int row, String faxNumber ) throws Exception
	{
		ButtonHelper.click( "PS_Detail_roamConfig_BD_HUROUT_faxConf_add_btnId" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.updateGridTextBox( "PS_Detail_roamConfig_BD_HUROUT_faxConf_gridId", "PS_Detail_roamConfig_BD_HUROUT_faxConf_fax_txtId", row, "Fax  Number", faxNumber );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}*/

	private void configFieldGrid( int row, String addBtnId, String gridId, String txtboxId, String columnName, String txtboxValue ) throws Exception
	{
		ButtonHelper.click( addBtnId );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.updateGridTextBox( gridId, txtboxId, row, columnName, txtboxValue );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	//Email  grid columns keys
	private List<String> getKeysOfEmailFieldGrid()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "Email  Address" );
		return listColumn;
	}

	//fax Number  grid columns keys
	private List<String> getKeysOfFaxFieldGrid()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "Fax  Number" );
		return listColumn;
	}

}
