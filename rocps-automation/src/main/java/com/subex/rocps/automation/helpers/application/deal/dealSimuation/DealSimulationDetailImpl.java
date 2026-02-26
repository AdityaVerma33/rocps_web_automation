package com.subex.rocps.automation.helpers.application.deal.dealSimuation;

import java.util.Map;

import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.RadioHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class DealSimulationDetailImpl extends PSAcceptanceTest
{
	protected Map<String, String> dealSimulationDetailsMap = null;
	protected String dealSimulationName;
	protected String bilateralModelling;
	protected String estimationProcessor;
	protected String dealSimulationType;
	protected String dealType;
	protected String description;
	protected String dealDetail_Account;
	protected String dealDetail_ContractNo;
	protected String dealDetail_DealPeriod;
	PSStringUtils psStringUtils = new PSStringUtils();
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	DataSelectionHelper dataSelectionHelper = new DataSelectionHelper();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();

	/**Constructor
	 * @param dealSimulationDetailsMap
	 */
	public DealSimulationDetailImpl( Map<String, String> dealSimulationDetailsMap )
	{
		this.dealSimulationDetailsMap = dealSimulationDetailsMap;
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		dealSimulationName = ExcelHolder.getKey( map, "SimulationName" );
		bilateralModelling = ExcelHolder.getKey( map, "BilateralModelling" );
		estimationProcessor = ExcelHolder.getKey( map, "EstimationProcessor" );
		dealSimulationType = ExcelHolder.getKey( map, "DealSimulationType" );
		dealType = ExcelHolder.getKey( map, "DealType" );
		description = ExcelHolder.getKey( map, "Description" );
	}

	/*
	 * This method is for initialize Deal detail variable
	 */
	private void initializeDealDetailVariable( Map<String, String> map ) throws Exception
	{
		dealDetail_Account = ExcelHolder.getKey( map, "DealDetail_Account" );
		dealDetail_ContractNo = ExcelHolder.getKey( map, "DealDetail_ContractNo" );
		dealDetail_DealPeriod = ExcelHolder.getKey( map, "DealDetail_DealPeriod" );

	}

	/*
	 * This method is for creation of Deal Simulation 
	 */
	public void createDealSimulation() throws Exception
	{
		initializeVariable( dealSimulationDetailsMap );
		configureNamePanel();
	}

	/*
	 * This method is for configure name panel
	 */
	protected void configureNamePanel() throws Exception
	{
		TextBoxHelper.type( "PS_Detail_DealSimulation_name_txtId", dealSimulationName );
		ComboBoxHelper.select( "PS_Detail_DealSimulation_bilatModelling_comboId", bilateralModelling );
		ComboBoxHelper.select( "PS_Detail_DealSimulation_estimProcessor_comboId", estimationProcessor );
		if ( dealSimulationType.contentEquals( "New" ) )
		{
			RadioHelper.click( "PS_Detail_DealSimulation_new_radioId" );
			RadioHelper.click( "PS_Detail_DealSimulation_new_radioId" );
			ComboBoxHelper.select( "PS_Detail_DealSimulation_dealType_comboId", dealType );
		}
		else
		{

			psDataComponentHelper.checkComboBoxDisabled( "PS_Detail_DealSimulation_dealType_comboId", "None" );
			configDealDeail();
		}
		TextAreaHelper.type( "PS_Detail_DealSimulation_descripton_txtareaId", description );
	}

	/*
	 * This method is for configure deal detail
	 */
	private void configDealDeail() throws Exception
	{
		initializeDealDetailVariable( dealSimulationDetailsMap );
		psGenericHelper.waitforEntityElement();
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		EntityComboHelper.clickEntityIcon( "PS_Detail_DealSimulation_deal_triggerId" );
		dataSelectionHelper.dealSearch( dealDetail_Account, dealDetail_ContractNo, dealDetail_DealPeriod );

	}
}
