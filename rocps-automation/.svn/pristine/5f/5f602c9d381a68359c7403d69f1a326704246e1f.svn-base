package com.subex.rocps.automation.helpers.application.matchandrate;

import java.util.Map;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class CrossFXRatesImpl extends PSAcceptanceTest
{

	protected String clientPartition;
	protected String crossFXRateGroup;
	protected String sourceCurrency;
	protected String targetCurrency;
	protected String validFrom;
	protected String rate;
	protected String createReverseRate;
	protected String reverseRate;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected Map<String, String> crossFXRatesimplMap = null;

	public CrossFXRatesImpl( Map<String, String> crossFxRatesMap ) throws Exception
	{
		this.crossFXRatesimplMap = crossFxRatesMap;
		initializeVariables( crossFXRatesimplMap );
	}

	/*
	 * This method is to create new Cross Fx Rates
	 */

	protected void createNewCrossFXRates() throws Exception
	{
		genericHelperObj.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask();
	}

	/*
	 * this method is for cross FX Rate Detail config
	 */
	protected void newCrossFXRate() throws Exception
	{
		ComboBoxHelper.select( "PS_Detail_crossFXRate_rateGroup_comboID", crossFXRateGroup );
		ComboBoxHelper.select( "PS_Detail_crossFXRate_sourcecurrency_comboID", sourceCurrency );
		ComboBoxHelper.select( "PS_Detail_crossFXRate_targetCurrency_comboID", targetCurrency );
		TextBoxHelper.type( "PS_Detail_crossFXRate_rate_txtID", rate );
		TextBoxHelper.type( "PS_Detail_crossFXRate_validFrom_comboID", validFrom );

		if ( ValidationHelper.isTrue( createReverseRate ) )

			CheckBoxHelper.check( "PS_Detail_crossFXRate_createreverseRate_chkbx" );
		//TextBoxHelper.type("PS_Detail_crossFXRate_reverseRate_txtID", reverseRate);
	}

	protected void editCrossFXRate() throws Exception
	{
		assertEquals( ComboBoxHelper.getValue( "PS_Detail_crossFXRate_rateGroup_comboID" ), crossFXRateGroup, "Crossfxrate group is not matched" );
		assertEquals( ComboBoxHelper.getValue( "PS_Detail_crossFXRate_sourcecurrency_comboID" ), sourceCurrency, "source currency is not matched" );
		assertEquals( ComboBoxHelper.getValue( "PS_Detail_crossFXRate_targetCurrency_comboID" ), targetCurrency, "target currency is not matched" );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_crossFXRate_validFrom_comboID" ), validFrom, "Valid  from is not matched" );
		assertTrue( CheckBoxHelper.isDisabled( "PS_Detail_crossFXRate_createreverseRate_chkbx" ), "Checkbox is found enabled" );
		if ( ValidationHelper.isNotEmpty( rate ) )
			TextBoxHelper.type( "PS_Detail_crossFXRate_rate_txtID", rate );
	}

	/*
	 * This method is to save cross FX Rate
	 */
	protected void saveCrossFXRates() throws Exception
	{
		genericHelperObj.detailSave( "PS_Detail_crossFXRate_save_btnID", crossFXRateGroup, "Cross Fx Rate Group" );
	}

	/*
	 * This method is to initialize instance variables
	 */
	protected void initializeVariables( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		crossFXRateGroup = ExcelHolder.getKey( map, "CrossFXRateGroup" );
		sourceCurrency = ExcelHolder.getKey( map, "SourceCurrency" );
		targetCurrency = ExcelHolder.getKey( map, "TargetCurrency" );
		validFrom = ExcelHolder.getKey( map, "ValidFrom" );
		rate = ExcelHolder.getKey( map, "Rate" );
		createReverseRate = ExcelHolder.getKey( map, "CreateReverseRate" );
		reverseRate = ExcelHolder.getKey( map, "ReverseRate" );

	}
}
